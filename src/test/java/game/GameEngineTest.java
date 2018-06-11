package game;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import players.AbstractPlayer;
import players.CPU;
import players.Judge;
import players.Player;
import state.StateMachine;
import utils.GameConsole;

public class GameEngineTest {
  @Test
  public void playersAreRegiteredCorrectly() throws Exception {
    List<AbstractPlayer> mockPlayers = new ArrayList<>();

    GameEngine gameEngine = new GameEngine(mockPlayers);
    gameEngine.registerPlayers();
    assertTrue(mockPlayers.size() == 3);
  }

  @Test
  public void startGameWorksAsExpected() throws Exception {
    Player mockPlayer1 = mock(Player.class);
    when(mockPlayer1.getName()).thenReturn("Mock1");
    when(mockPlayer1.getMark()).thenReturn("X");
    // when(mockPlayer1.execute(mock(Command.class))).thenReturn("X");
    Player mockPlayer2 = mock(Player.class);
    when(mockPlayer2.getName()).thenReturn("Mock2");
    when(mockPlayer2.getMark()).thenReturn("O");
    CPU mockPlayer3 = mock(CPU.class);
    when(mockPlayer3.getName()).thenReturn("Mock3");
    when(mockPlayer3.getMark()).thenReturn("W");
    List<AbstractPlayer> mockPlayers = Arrays.asList(mockPlayer1, mockPlayer2, mockPlayer3);

    GameBoard mockBoard = mock(GameBoard.class);

    StateMachine mocksStateMachine = mock(StateMachine.class);

    GameConsole mockConsole = mock(GameConsole.class);
    int[] coo = { 1, 2 };
    when(mockConsole.readCoordinates()).thenReturn(coo);

    Judge judge = mock(Judge.class);
    doAnswer(new Answer<Boolean>() {
      int count = 0;

      @Override
      public Boolean answer(InvocationOnMock invocation) throws Throwable {
        count++;
        if (count == 3) {
          return true;
        }
        return false;
      }

    }).when(judge).locateCurrentPlayerMove(mockPlayer1);

    GameEngine gameEngine = new GameEngine(mockBoard, mockConsole, judge, mocksStateMachine, mockPlayers);
    gameEngine.startGame();

    verify(mockConsole, atLeastOnce()).showMessage("Welcome!");
    verify(mockConsole, atLeast(3)).showMessage(mockPlayer1.getName() + ", Please enter your move:");
    verify(mockConsole, atLeast(2)).showMessage(mockPlayer2.getName() + ", Please enter your move:");
    verify(mockConsole, atLeast(2)).showMessage(mockPlayer3.getName() + ", Please enter your move:");
    verify(mockConsole, atLeast(2)).showMessage("CPU is making a move");
    verify(mockConsole, atLeastOnce()).showMessage("Congratulations " + mockPlayer1.getName());
    verify(mockConsole, atLeastOnce()).closeConsole();
    verify(mockConsole, atLeast(5)).readCoordinates();

    verify(judge, atLeast(3)).locateCurrentPlayerMove(mockPlayer1);

    verify(mockBoard, atLeast(7)).draw();
  }
}