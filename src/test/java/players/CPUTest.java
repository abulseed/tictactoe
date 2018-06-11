package players;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;

import commands.Command;
import exceptions.ValidationException;
import state.StateMachine;
import utils.Props;

public class CPUTest {
  private int size;

  @Before
  public void setup() throws NumberFormatException, Exception {
    this.size = Props.readGridSize();
  }

  @Test
  public void cpuPlayerCreatedSuccessfully() throws Exception {
    CPU cpu = new CPU();
    assertNotNull(cpu.humanPlayers);

    Player human1 = mock(Player.class);
    Player human2 = mock(Player.class);
    cpu.injectHumanPlayer(human1);
    cpu.injectHumanPlayer(human2);

    assertEquals(2, cpu.humanPlayers.size());
  }

  @Test
  public void cpuMakeOnlyOneMoveIfNoWinOrBlockHappens() throws Exception {
    CPU cpu = new CPU();
    Player human1 = new Player();
    Player human2 = new Player();
    StateMachine stateMachine = StateMachine.getInstance();
    human1.injectStateMachine(stateMachine);
    human2.injectStateMachine(stateMachine);
    human1.execute(new Command(0, 0, human1.getMark()));
    human2.execute(new Command(0, 1, human2.getMark()));
    cpu.injectHumanPlayer(human1);
    cpu.injectHumanPlayer(human2);
    cpu.injectStateMachine(stateMachine);

    cpu.makeAMove();

    assertEquals(1, cpu.commands.size());
  }

  @Test
  public void cpuCanWin() throws Exception {
    CPU cpu = new CPU();
    Player human1 = new Player();
    Player human2 = new Player();
    cpu.injectHumanPlayer(human1);
    cpu.injectHumanPlayer(human2);

    Judge judge = new Judge();
    StateMachine stateMachine = mock(StateMachine.class);
    when(stateMachine.getState()).thenReturn(new String[this.size][this.size]);
    cpu.injectStateMachine(stateMachine);
    for (int i = 0; i < this.size; i++) {
      cpu.makeAMove();
      judge.locateCurrentPlayerMove(cpu);
    }
    assertTrue(judge.isWinner(cpu));
  }

  @Test
  public void cpuCanBlock() throws Exception {
    CPU cpu = new CPU();
    Player human1 = new Player();
    Player human2 = new Player();
    cpu.injectHumanPlayer(human1);
    cpu.injectHumanPlayer(human2);

    Judge judge = new Judge();
    StateMachine stateMachine = StateMachine.getInstance();
    stateMachine.mutateState(new Command(0, 3, "O"));
    cpu.injectStateMachine(stateMachine);
    human1.injectStateMachine(stateMachine);
    cpu.setMark("W");
    for (int i = 0; i < this.size; i++) {
      cpu.makeAMove();
      judge.locateCurrentPlayerMove(cpu);
      try {
        Command command = new Command(1, i, "X");
        human1.execute(command);
        judge.locateCurrentPlayerMove(human1);
      } catch (ValidationException e) {
        e.printStackTrace(System.out);
      }
    }

    assertFalse(judge.isWinner(human1));
    assertFalse(judge.isWinner(cpu));
  }
}