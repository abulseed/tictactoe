package game;

import java.util.ArrayList;

import commands.Command;
import players.Judge;
import players.Player;
import state.StateMachine;
import utils.Fields;
import utils.GameConsole;
import utils.Props;

public class GameEngine {
  protected StateMachine stateMachine;
  protected ArrayList<Player> players = new ArrayList<>();
  protected GameBoard gameBoard;

  public GameEngine() throws Exception {
    try {
      this.stateMachine = StateMachine.getInstance();
      this.gameBoard = new GameBoard();
    } catch (Exception e) {
      throw e;
    }
  }

  public void registerPlayers() throws Exception {
    try {
      Player player1 = new Player();
      player1.setName("P1");
      player1.setMark(Props.readProp(Fields.P1));

      Player player2 = new Player();
      player2.setName("P2");
      player2.setMark(Props.readProp(Fields.P2));

      Player player3 = new Player();
      player3.setName("CPU");
      player3.setMark(Props.readProp(Fields.CPU));

      players.add(player1);
      players.add(player2);
      players.add(player3);
    } catch (Exception e) {
      throw e;
    }
  }

  public void startGame() throws Exception {
    try {
      GameConsole.openConsole();
      GameConsole.showMessage("Welcome!");
      while (true) {
        for (Player player : players) {
          GameConsole.showMessage(player.getName() + ", Please enter your move:");

          while (true) {
            try {
              int[] coo = GameConsole.readCoordinates();
              int x = coo[0], y = coo[1];
              Command command = new Command(x, y, player.getMark());
              player.execute(command);
              this.gameBoard.draw();
              Judge judge = new Judge();
              if (judge.winner(player)) {
                GameConsole.showMessage("Congratulations " + player.getName());
                GameConsole.closeConsole();
                System.exit(0);
              }
              break;
            } catch (Exception e) {
              GameConsole.showMessage(e.getMessage());
              GameConsole.showMessage("Please try again.");
            }
          }
        }
      }
    } catch (Exception e) {
      throw e;
    } finally {
      GameConsole.closeConsole();
    }
  }
}