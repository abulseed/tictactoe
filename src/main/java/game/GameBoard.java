package game;

import state.StateMachine;

public class GameBoard {
  private StateMachine stateMachine;

  public GameBoard() {
    this.stateMachine = StateMachine.getInstance();
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