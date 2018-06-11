package players;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import org.junit.Before;
import org.junit.Test;

import commands.Command;
import state.StateMachine;
import utils.Fields;
import utils.Props;

public class PlayerTest {
  private int size;

  @Before
  public void setup() throws NumberFormatException, Exception {
    this.size = Integer.parseInt(Props.readProp(Fields.SIZE_OF_PLAYGROUND));
  }

  @Test
  public void winningPathesAreInitialized() throws Exception {
    Player p = new Player();
    assertEquals((this.size * 2) + 2, p.winningPaths.size());
  }

  @Test
  public void commandExecutesCorrectly() throws Exception {
    Player p = new Player();
    StateMachine stateMachine = mock(StateMachine.class);
    Command command = new Command(0, 1, "X");

    p.injectStateMachine(stateMachine);
    p.execute(command);

    verify(stateMachine, atLeastOnce()).mutateState(command);
    assertEquals(1, p.commands.size());
  }

  @Test
  public void aPlayerCanWin() throws Exception {
    Player p = new Player();
    Judge judge = new Judge();
    StateMachine stateMachine = mock(StateMachine.class);
    p.injectStateMachine(stateMachine);
    for (int i = 0; i < this.size; i++) {
      Command command = new Command(0, i, "X");
      p.execute(command);
      verify(stateMachine, atLeastOnce()).mutateState(command);
      judge.locateCurrentPlayerMove(p);
    }
    assertTrue(judge.isWinner(p));
  }
}