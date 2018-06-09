package game;

import state.StateMachine;

public class GameBoard {
  private StateMachine stateMachine;

  public GameBoard() throws Exception {
    try {
      this.stateMachine = StateMachine.getInstance();
    } catch (Exception e) {
      throw e;
    }
  }

  public void draw() throws Exception {
    try {
      String[][] matrix = this.stateMachine.getState();
      for (int i = 0; i < matrix.length; i++) {
        for (int j = 0; j < matrix.length; j++) {
          String entry = matrix[i][j];
          System.out.print(entry == null || entry.isEmpty() ? "#    " : entry + "    ");
        }
        System.out.println();
      }
    } catch (Exception e) {
      throw e;
    }
  }
}