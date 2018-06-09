package players;

import java.util.ArrayList;
import java.util.List;

import commands.Command;
import commands.ICommand;
import exceptions.ValidationException;
import paths.HorizontalPath;
import paths.IPath;
import paths.MajorDiagonalPath;
import paths.MinorDiagonalPath;
import paths.VerticalPath;
import state.StateMachine;
import utils.Fields;
import utils.Props;

public class Player implements ICommand {
  private List<IPath> winningPaths = new ArrayList<>();

  private List<Command> commands = new ArrayList<>();

  private String name;
  private String mark;

  private int length;

  public Player() {
    this.length = Integer.parseInt(Props.readProp(Fields.SIZE_OF_PLAYGROUND));

    for (int i = 0; i < length; i++) {
      this.winningPaths.add(new HorizontalPath(i));
      this.winningPaths.add(new VerticalPath(i));
    }
    this.winningPaths.add(new MajorDiagonalPath());
    this.winningPaths.add(new MinorDiagonalPath());
  }

  public boolean didIWin() {
    boolean winnerPath = this.winningPaths.stream().filter(path -> this.length == path.getCurrentSize()).findFirst()
        .isPresent();
    return winnerPath;
  }

  @Override
  public void execute(Command command) throws ValidationException {
    this.addNewCommand(command);
    StateMachine.getInstance().mutateState(command);
  }

  public List<IPath> getWinningPaths() {
    return this.winningPaths;
  }

  /**
   * @return the command
   */
  public Command getLastCommand() {
    return this.commands.get(this.commands.size() - 1);
  }

  /**
   * @param command the command to set
   */
  public void addNewCommand(Command command) {
    this.commands.add(command);
  }

  /**
   * @return the name
   */
  public String getName() {
    return name;
  }

  /**
   * @param name the name to set
   */
  public void setName(String name) {
    this.name = name;
  }

  /**
   * @return the mark
   */
  public String getMark() {
    return mark;
  }

  /**
   * @param mark the mark to set
   */
  public void setMark(String mark) {
    this.mark = mark;
  }
}