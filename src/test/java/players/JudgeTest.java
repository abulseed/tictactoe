package players;

import org.junit.Test;
import org.mockito.Mockito;

import paths.PathFinder;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class JudgeTest {
  @Test
  public void isWinnerReturnsExpectedValue() throws Exception {
    PathFinder mockPathFinder = Mockito.spy(PathFinder.class);
    Judge testJudge = new Judge(mockPathFinder);
    Player mockPlayer = mock(Player.class);

    when(mockPlayer.didIWin()).thenReturn(true);
    assertTrue(testJudge.isWinner(mockPlayer));

    when(mockPlayer.didIWin()).thenReturn(false);
    assertFalse(testJudge.isWinner(mockPlayer));
  }

  @Test
  public void playerMoveLocatedAndWinnerReturns() throws Exception {
    PathFinder mockPathFinder = spy(PathFinder.class);
    Judge testJudge = new Judge(mockPathFinder);
    Player mockPlayer = mock(Player.class);
    when(mockPlayer.didIWin()).thenReturn(true);

    testJudge.winner(mockPlayer);

    verify(mockPathFinder, times(1)).locatePlayerMove(mockPlayer);
    assertTrue(testJudge.winner(mockPlayer));
  }
}