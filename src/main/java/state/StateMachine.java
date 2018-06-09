package state;

import commands.Command;
import utils.Fields;
import utils.Props;

public class StateMachine {
  private Caretaker caretaker;
  private Originator originator;

  private int getPlaygroundSize() throws Exception {
    try {
      return Integer.parseInt(Props.readProp(Fields.SIZE_OF_PLAYGROUND));
    } catch (Exception e) {
      throw e;
    }
  }

  private StateMachine() throws Exception {
    try {
      int size = getPlaygroundSize();
      this.originator = new Originator(size, size);
      this.caretaker = new Caretaker();
    } catch (Exception e) {
      throw e;
    }
  }

  private static class StateMachineHolder {
    private static final StateMachine INSTANCE() throws Exception {
      try {
        return new StateMachine();
      } catch (Exception e) {
        throw e;
      }
    };
  }

  public static StateMachine getInstance() throws Exception {
    try {
      return StateMachineHolder.INSTANCE();
    } catch (Exception e) {
      throw e;
    }
  }

  public void mutateState(Command command) throws Exception {
    try {
      originator.mutateState(command);
      caretaker.addMemento(originator.save());
    } catch (Exception e) {
      throw e;
    }
  }

  public String[][] getState() {
    return caretaker.getMemento().getState();
  }
}