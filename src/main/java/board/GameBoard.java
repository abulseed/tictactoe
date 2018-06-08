package board;

import state.StateMachine;

public class GameBoard {
  protected StateMachine stateMachine;

  public GameBoard(int x, int y) {
    this.stateMachine = new StateMachine(x, y);
  }

  public void draw() {
    String[][] matrix = this.stateMachine.getState();
    for (int i = 0; i < matrix.length; i++) {
      for (int j = 0; j < matrix.length; j++) {
        String entry = matrix[i][j];
        System.out.print(entry == null || entry.isEmpty() ? "#    " : entry + "    ");
      }
      System.out.println();
    }
  }
}