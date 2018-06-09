import game.GameEngine;

/*
 * This Java source file was generated by the Gradle 'init' task.
 */
public class App {
  public static void main(String[] args) {
    try {
      GameEngine gameEngine = new GameEngine();
      gameEngine.registerPlayers();
      gameEngine.startGame();
    } catch (Exception e) {
      e.printStackTrace(System.out);
      System.exit(0);
    }
  }
}
