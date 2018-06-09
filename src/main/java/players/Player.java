package players;

import java.util.ArrayList;

import commands.Command;
import commands.ICommand;
import exceptions.ValidationException;
import state.StateMachine;
import utils.WeightedQuickUnionUF;

public class Player implements ICommand {
  private ArrayList<Command> commands = new ArrayList<>();
  private String name;
  private String mark;

  @Override
  public void execute(Command command) throws ValidationException {
    this.addNewCommand(command);
    StateMachine.getInstance().mutateState(command);
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