package commands;

public interface ICommand {
  void execute(Command command) throws Exception;
}