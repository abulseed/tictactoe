package paths;

import java.util.ArrayList;
import java.util.List;

import commands.Command;

public class VerticalPath extends IPath {
  private int localY;

  public VerticalPath(int y) {
    this.localY = y;
    initCommandsList();
  }

  @Override
  public boolean checkJoiningCondition(Command command) {
    int y = command.getyCoo();
    return this.localY == y;
  }
}