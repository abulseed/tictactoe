package game;

import java.io.Console;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import commands.Command;
import exceptions.ValidationException;
import player.Player;
import state.StateMachine;
import utils.Fields;
import utils.GameConsole;
import utils.Props;

public class GameEngine {
  protected StateMachine stateMachine;
  protected ArrayList<Player> players = new ArrayList<>();
  protected Player currentPlayer = new Player();
  protected GameBoard gameBoard;

  public GameEngine() {
    this.stateMachine = StateMachine.getInstance();
    this.gameBoard = new GameBoard();
    registerPlayers();
  }

  public void registerPlayers() {
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
  }

  public void startGame() {
    GameConsole.openConsole();
    while (true) {
      System.out.println("Welcome!");
      for (Player player : players) {
        this.currentPlayer = player;
        GameConsole.showMessage(player.getName() + ", Please enter your move:");

        while (true) {
          try {
            int[] coo = GameConsole.readCoordinates();
            int x = coo[0], y = coo[1];
            Command command = new Command(x, y, this.currentPlayer.getMark());
            this.currentPlayer.execute(command);
            this.gameBoard.draw();
            break;
          } catch (Exception e) {
            GameConsole.showMessage(e.getMessage());
            GameConsole.showMessage("Please try again.");
          }
        }
      }
    }
  }
}