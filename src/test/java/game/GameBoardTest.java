package game;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Before;
import org.junit.Test;

import commands.Command;
import state.StateMachine;
import utils.Fields;
import utils.Props;

public class GameBoardTest {
  private int size;

  @Before
  public void setup() throws NumberFormatException, Exception {
    this.size = Integer.parseInt(Props.readProp(Fields.SIZE_OF_PLAYGROUND));
  }

  @Test
  public void stateMachineIsSuccessfullyCreated() throws Exception {
    GameBoard gameBoard = new GameBoard();
    StateMachine stateMachine = StateMachine.getInstance();
    stateMachine.mutateState(new Command(0, 0, "X"));
    gameBoard.injectStateMachine(stateMachine);
    assertNotNull("State Machine is created.", gameBoard.stateMachine);
    assertEquals(this.size, gameBoard.stateMachine.getState().length);
  }

  @Test
  public void gameBoardIsDrawedCorrectly() throws Exception {
    GameBoard gameBoard = new GameBoard();
    StateMachine stateMachine = StateMachine.getInstance();
    stateMachine.mutateState(new Command(0, 0, "X"));
    gameBoard.injectStateMachine(stateMachine);

    gameBoard.draw();
  }
}