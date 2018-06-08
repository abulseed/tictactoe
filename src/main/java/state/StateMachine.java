package state;

import commands.Command;
import exceptions.ValidationException;
import utils.Fields;
import utils.Props;

public class StateMachine {
  private Caretaker caretaker;
  private Originator originator;

  private int getPlaygroundSize() throws NumberFormatException {
    return Integer.parseInt(Props.readProp(Fields.SIZE_OF_PLAYGROUND));
  }

  private StateMachine() throws NumberFormatException {
    int size = getPlaygroundSize();
    this.originator = new Originator(size, size);
    this.caretaker = new Caretaker();
  }

  private static class StateMachineHolder {
    private static final StateMachine INSTANCE = new StateMachine();
  }

  public static StateMachine getInstance() throws NumberFormatException {
    return StateMachineHolder.INSTANCE;
  }

  public void mutateState(Command command) throws ValidationException {
    originator.mutateState(command);
    caretaker.addMemento(originator.save());
  }

  public String[][] getState() {
    return caretaker.getMemento().getState();
  }
}