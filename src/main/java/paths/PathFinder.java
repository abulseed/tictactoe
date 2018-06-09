package paths;

import commands.Command;
import players.Player;

public class PathFinder {
  public static void locatePlayerMove(Player player) throws Exception {
    try {
      Command command = player.getLastCommand();
      player.getWinningPaths().stream().forEach(path -> {
        try {
          path.handleCommand(command);
        } catch (Exception e) {
          e.printStackTrace();
        }
      });
    } catch (Exception e) {
      throw e;
    }
  }
}