package players;

import java.util.ArrayList;
import java.util.List;

import commands.Command;
import paths.HorizontalPath;
import paths.IPath;
import paths.MajorDiagonalPath;
import paths.MinorDiagonalPath;
import paths.VerticalPath;
import state.StateMachine;
import utils.Fields;
import utils.Props;

public class Player extends AbstractPlayer {

  public Player() throws Exception {
    super();
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
}