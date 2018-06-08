package board;

import org.junit.Test;
import static org.junit.Assert.*;

public class GameBoardTest {
  @Test
  public void stateMachineIsSuccessfullyCreated() {
    GameBoard gameBoard = new GameBoard(10, 10);
    assertNotNull("State Machine is created.", gameBoard.stateMachine);
  }
}