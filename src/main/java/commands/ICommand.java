package commands;

import exceptions.ValidationException;

public interface ICommand {
  void execute(Command command) throws Exception;
}