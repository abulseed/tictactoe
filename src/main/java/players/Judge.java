package players;

import paths.PathFinder;

public class Judge {

  public boolean winner(Player player) throws Exception {
    try {
      PathFinder.locatePlayerMove(player);
      return this.isWinner(player);
    } catch (Exception e) {
      e.printStackTrace(System.out);
      throw e;
    }
  }

  public boolean isWinner(Player player) throws Exception {
    try {
      return player.didIWin();
    } catch (Exception e) {
      throw e;
    }
  }
}