package players;

import commands.Command;
import state.StateMachine;
import utils.Fields;
import utils.Props;
import utils.WeightedQuickUnionUF;

public class Judge {
  private int virtualTopP1;
  private int virtualBottomP1;
  private int virtualRightP1;
  private int virtualLeftP1;
  private int virtualTopP2;
  private int virtualBottomP2;
  private int virtualRightP2;
  private int virtualLeftP2;
  private int virtualTopCPU;
  private int virtualBottomCPU;
  private int virtualRightCPU;
  private int virtualLeftCPU;

  private int length;
  private int unionFindIndex;

  private boolean firstRow;
  private boolean lastRow;
  private boolean firstCol;
  private boolean lastCol;

  private int right;
  private int left;
  private int top;
  private int bottom;
  private int topRight;
  private int topLeft;
  private int bottomRight;
  private int bottomLeft;

  public Judge() {
    length = Integer.parseInt(Props.readProp(Fields.SIZE_OF_PLAYGROUND));
    virtualTopP1 = (length * length);
    virtualBottomP1 = (length * length) + 1;
    virtualRightP1 = (length * length) + 2;
    virtualLeftP1 = (length * length) + 3;

    virtualTopP2 = (length * length) + 4;
    virtualBottomP2 = (length * length) + 5;
    virtualRightP2 = (length * length) + 6;
    virtualLeftP2 = (length * length) + 7;

    virtualTopCPU = (length * length) + 8;
    virtualBottomCPU = (length * length) + 9;
    virtualRightCPU = (length * length) + 10;
    virtualLeftCPU = (length * length) + 11;
  }

  public boolean winner(Player player) throws Exception {
    try {
      Command lastCommand = player.getLastCommand();
      int x = lastCommand.getxCoo(), y = lastCommand.getyCoo();
      String mark = lastCommand.getMark(), name = player.getName();
      this.locatePlayerMove(x, y);
      this.adjustPlayerMove(x, y, mark, name);

      return this.playerIsWinner(name);
    } catch (Exception e) {
      e.printStackTrace(System.out);
      throw e;
    }
  }

  private boolean playerIsWinner(String name) {
    int topleftUF = (0 * length) + 0;
    int toprightUF = (0 * length) + (length - 1);
    int bottomrightUF = ((length - 1) * length) + (length - 1);
    int bottomleftUF = ((length - 1) * length) + 0;

    boolean result = WeightedQuickUnionUF.getInstance().connected(topleftUF, bottomrightUF)
        || WeightedQuickUnionUF.getInstance().connected(toprightUF, bottomleftUF);

    if ("P1".equals(name)) {
      result = result || WeightedQuickUnionUF.getInstance().connected(virtualTopP1, virtualBottomP1)
          || WeightedQuickUnionUF.getInstance().connected(virtualLeftP1, virtualRightP1);
    }
    if ("P2".equals(name)) {
      result = result || WeightedQuickUnionUF.getInstance().connected(virtualTopP2, virtualBottomP2)
          || WeightedQuickUnionUF.getInstance().connected(virtualLeftP2, virtualRightP2);
    }
    if ("CPU".equals(name)) {
      result = result || WeightedQuickUnionUF.getInstance().connected(virtualTopCPU, virtualBottomCPU)
          || WeightedQuickUnionUF.getInstance().connected(virtualLeftCPU, virtualRightCPU);
    }
    return result;
  }

  private void adjustPlayerMove(int x, int y, String mark, String name) {
    if (this.firstRow) {
      if ("P1".equals(name)) {
        WeightedQuickUnionUF.getInstance().union(this.virtualTopP1, this.unionFindIndex);
      }
      if ("P2".equals(name)) {
        WeightedQuickUnionUF.getInstance().union(this.virtualTopP2, this.unionFindIndex);
      }
      if ("CPU".equals(name)) {
        WeightedQuickUnionUF.getInstance().union(this.virtualTopCPU, this.unionFindIndex);
      }
    }

    if (this.lastRow) {
      if ("P1".equals(name)) {
        WeightedQuickUnionUF.getInstance().union(this.virtualBottomP1, this.unionFindIndex);
      }
      if ("P2".equals(name)) {
        WeightedQuickUnionUF.getInstance().union(this.virtualBottomP2, this.unionFindIndex);
      }
      if ("CPU".equals(name)) {
        WeightedQuickUnionUF.getInstance().union(this.virtualBottomCPU, this.unionFindIndex);
      }
    }

    if (this.firstCol) {
      if ("P1".equals(name)) {
        WeightedQuickUnionUF.getInstance().union(this.virtualLeftP1, this.unionFindIndex);
      }
      if ("P2".equals(name)) {
        WeightedQuickUnionUF.getInstance().union(this.virtualLeftP2, this.unionFindIndex);
      }
      if ("CPU".equals(name)) {
        WeightedQuickUnionUF.getInstance().union(this.virtualLeftCPU, this.unionFindIndex);
      }
    }

    if (this.lastCol) {
      if ("P1".equals(name)) {
        WeightedQuickUnionUF.getInstance().union(this.virtualRightP1, this.unionFindIndex);
      }
      if ("P2".equals(name)) {
        WeightedQuickUnionUF.getInstance().union(this.virtualRightP2, this.unionFindIndex);
      }
      if ("CPU".equals(name)) {
        WeightedQuickUnionUF.getInstance().union(this.virtualRightCPU, this.unionFindIndex);
      }
    }

    if (this.indexInUFBound(this.right) && mark.equals(StateMachine.getInstance().getState()[x][y + 1])) {
      WeightedQuickUnionUF.getInstance().union(this.unionFindIndex, this.right);
    }

    if (this.indexInUFBound(this.left) && mark.equals(StateMachine.getInstance().getState()[x][y - 1])) {
      WeightedQuickUnionUF.getInstance().union(this.unionFindIndex, this.left);
    }

    if (this.indexInUFBound(this.top) && mark.equals(StateMachine.getInstance().getState()[x - 1][y])) {
      WeightedQuickUnionUF.getInstance().union(this.unionFindIndex, this.top);
    }

    if (this.indexInUFBound(this.bottom) && mark.equals(StateMachine.getInstance().getState()[x + 1][y])) {
      WeightedQuickUnionUF.getInstance().union(this.unionFindIndex, this.bottom);
    }

    if (this.indexInUFBound(this.topRight) && mark.equals(StateMachine.getInstance().getState()[x - 1][y + 1])) {
      WeightedQuickUnionUF.getInstance().union(this.unionFindIndex, this.topRight);
    }

    if (this.indexInUFBound(this.topLeft) && mark.equals(StateMachine.getInstance().getState()[x - 1][y - 1])) {
      WeightedQuickUnionUF.getInstance().union(this.unionFindIndex, this.topLeft);
    }

    if (this.indexInUFBound(this.bottomRight) && mark.equals(StateMachine.getInstance().getState()[x + 1][y + 1])) {
      WeightedQuickUnionUF.getInstance().union(this.unionFindIndex, this.bottomRight);
    }

    System.out.println(this.bottomLeft);
    if (this.indexInUFBound(this.bottomLeft) && mark.equals(StateMachine.getInstance().getState()[x + 1][y - 1])) {
      WeightedQuickUnionUF.getInstance().union(this.unionFindIndex, this.bottomLeft);
    }
  }

  private boolean indexInUFBound(int index) {
    return index < (length * length) + 4 && index >= 0;
  }

  private void locatePlayerMove(int row, int col) {
    this.firstRow = row == 0;
    this.lastRow = row == length - 1;
    this.firstCol = col == 0;
    this.lastCol = col == length - 1;

    this.unionFindIndex = (row * length) + col;

    this.right = this.lastCol ? -1 : this.unionFindIndex + 1;
    this.left = this.firstCol ? -1 : this.unionFindIndex - 1;
    this.top = this.firstRow ? -1 : this.unionFindIndex - length;
    this.bottom = this.lastRow ? -1 : this.unionFindIndex + length;
    this.topRight = this.firstRow || this.lastCol ? -1 : this.unionFindIndex - (length - 1);
    this.topLeft = this.firstRow || this.firstCol ? -1 : this.unionFindIndex - (length + 1);
    this.bottomRight = this.lastRow || this.lastCol ? -1 : this.unionFindIndex + (length + 1);
    this.bottomLeft = this.lastRow || this.firstCol ? -1 : this.unionFindIndex + (length - 1);
  }
}