package players;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import commands.Command;
import paths.HorizontalPath;
import paths.IPath;
import paths.MajorDiagonalPath;
import paths.MinorDiagonalPath;
import paths.PathFinder;
import paths.VerticalPath;

public class CPU extends AbstractPlayer {
  protected List<Player> humanPlayers;
  private PathFinder pathFinder;

  public CPU() throws Exception {
    this(new ArrayList<>(), new PathFinder());
  }

  public CPU(List<Player> humanPlayers, PathFinder pathFinder) throws Exception {
    super();
    this.humanPlayers = humanPlayers;
    this.pathFinder = pathFinder;
  }

  @Override
  public void execute(Command command) throws Exception {
    try {
      this.addNewCommand(command);
      this.stateMachine.mutateState(command);
    } catch (Exception e) {
      throw e;
    }
  }

  public void makeAMove() throws Exception {
    boolean res = tryWin() || tryBlock() || selectBestMove();
    System.out.println("CPU made a move? " + res);
  }

  public void injectHumanPlayer(Player player) {
    this.humanPlayers.add(player);
  }

  public boolean tryWin() {
    List<IPath> prospects = this.winningPaths.stream().filter(path -> (this.length - 1) == path.getCurrentSize())
        .collect(Collectors.toList());

    for (IPath path : prospects) {
      try {
        if (this.pathFinder.navigatePath(this, path)) {
          return true;
        }
      } catch (Exception e) {
        e.printStackTrace();
      }
    }
    return false;
  }

  public boolean tryBlock() {
    for (Player player : this.humanPlayers) {
      List<IPath> humanProspects = player.winningPaths.stream()
          .filter(path -> (this.length - 1) == path.getCurrentSize()).collect(Collectors.toList());

      for (IPath path : humanProspects) {
        try {
          if (this.pathFinder.navigatePath(this, path)) {
            return true;
          }
        } catch (Exception e) {
          e.printStackTrace();
        }
      }
    }
    return false;
  }

  public boolean selectBestMove() throws Exception {
    for (int i = 0; i < this.length; i++) {
      for (int j = 0; j < this.length; j++) {
        if (null == this.stateMachine.getState()[i][j]) {
          Command command = new Command(i, j, this.getMark());
          this.execute(command);
          return true;
        }
      }
    }
    return false;
  }

  public boolean handleBlockingHumanPath(IPath humanPath) throws Exception {
    if (humanPath instanceof HorizontalPath) {
      int x = ((HorizontalPath) humanPath).getLocalX();
      List<int[]> usedCoo = humanPath.getCommands().stream()
          .map(command -> new int[] { command.getxCoo(), command.getyCoo() }).collect(Collectors.toList());
      for (int i = 0; i < this.length; i++) {
        final int target = i;
        boolean usedByMe = usedCoo.stream().filter(coo -> target == coo[1]).findAny().isPresent();
        if (!usedByMe && null == this.stateMachine.getState()[x][target]) {
          Command cmd = new Command(x, target, this.getMark());
          this.execute(cmd);
          return true;
        }
      }
    }

    else if (humanPath instanceof VerticalPath) {
      int y = ((VerticalPath) humanPath).getLocalY();
      List<int[]> usedCoo = humanPath.getCommands().stream()
          .map(command -> new int[] { command.getxCoo(), command.getyCoo() }).collect(Collectors.toList());
      for (int i = 0; i < this.length; i++) {
        final int target = i;
        boolean usedByMe = usedCoo.stream().filter(coo -> target == coo[0]).findAny().isPresent();
        if (!usedByMe && null == this.stateMachine.getState()[target][y]) {
          Command cmd = new Command(target, y, this.getMark());
          this.execute(cmd);
          return true;
        }
      }
    }

    else if (humanPath instanceof MajorDiagonalPath) {
      List<int[]> usedCoo = humanPath.getCommands().stream()
          .map(command -> new int[] { command.getxCoo(), command.getyCoo() }).collect(Collectors.toList());
      for (int i = 0; i < this.length; i++) {
        final int target = i;
        boolean usedByMe = usedCoo.stream().filter(coo -> target == coo[0] && target == coo[1]).findAny().isPresent();
        if (!usedByMe && null == this.stateMachine.getState()[target][target]) {
          Command cmd = new Command(target, target, this.getMark());
          this.execute(cmd);
          return true;
        }
      }
    }

    else if (humanPath instanceof MinorDiagonalPath) {
      List<int[]> usedCoo = humanPath.getCommands().stream()
          .map(command -> new int[] { command.getxCoo(), command.getyCoo() }).collect(Collectors.toList());
      for (int i = 0, j = length - 1; i < length; i++, j--) {
        final int targetX = i;
        final int targetY = j;
        boolean usedByMe = usedCoo.stream().filter(coo -> targetX == coo[0] && targetY == coo[1]).findAny().isPresent();
        if (!usedByMe && null == this.stateMachine.getState()[targetX][targetY]) {
          Command cmd = new Command(targetX, targetY, this.getMark());
          this.execute(cmd);
          return true;
        }
      }
    }
    return false;
  }

  public boolean handleWinningPath(IPath path) throws Exception {
    if (path instanceof HorizontalPath) {
      int x = ((HorizontalPath) path).getLocalX();
      List<int[]> usedCoo = path.getCommands().stream()
          .map(command -> new int[] { command.getxCoo(), command.getyCoo() }).collect(Collectors.toList());
      for (int i = 0; i < this.length; i++) {
        final int target = i;
        boolean usedByMe = usedCoo.stream().filter(coo -> target == coo[1]).findAny().isPresent();
        if (!usedByMe && null == this.stateMachine.getState()[x][target]) {
          Command cmd = new Command(x, target, this.getMark());
          this.execute(cmd);
          return true;
        }
      }
    }

    else if (path instanceof VerticalPath) {
      int y = ((VerticalPath) path).getLocalY();
      List<int[]> usedCoo = path.getCommands().stream()
          .map(command -> new int[] { command.getxCoo(), command.getyCoo() }).collect(Collectors.toList());
      for (int i = 0; i < this.length; i++) {
        final int target = i;
        boolean usedByMe = usedCoo.stream().filter(coo -> target == coo[0]).findAny().isPresent();
        if (!usedByMe && null == this.stateMachine.getState()[target][y]) {
          Command cmd = new Command(target, y, this.getMark());
          this.execute(cmd);
          return true;
        }
      }
    }

    else if (path instanceof MajorDiagonalPath) {
      List<int[]> usedCoo = path.getCommands().stream()
          .map(command -> new int[] { command.getxCoo(), command.getyCoo() }).collect(Collectors.toList());
      for (int i = 0; i < this.length; i++) {
        final int target = i;
        boolean usedByMe = usedCoo.stream().filter(coo -> target == coo[0] && target == coo[1]).findAny().isPresent();
        if (!usedByMe && null == this.stateMachine.getState()[target][target]) {
          Command cmd = new Command(target, target, this.getMark());
          this.execute(cmd);
          return true;
        }
      }
    }

    else if (path instanceof MinorDiagonalPath) {
      List<int[]> usedCoo = path.getCommands().stream()
          .map(command -> new int[] { command.getxCoo(), command.getyCoo() }).collect(Collectors.toList());
      for (int i = 0, j = length - 1; i < length; i++, j--) {
        final int targetX = i;
        final int targetY = j;
        boolean usedByMe = usedCoo.stream().filter(coo -> targetX == coo[0] && targetY == coo[1]).findAny().isPresent();
        if (!usedByMe && null == this.stateMachine.getState()[targetX][targetY]) {
          Command cmd = new Command(targetX, targetY, this.getMark());
          this.execute(cmd);
          return true;
        }
      }
    }
    return false;
  }
}