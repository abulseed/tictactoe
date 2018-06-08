package state;

import commands.Command;

public class StateMachine {
  private Caretaker caretaker;
  private Originator originator;

  public StateMachine(int x, int y) {
    this.originator = new Originator(x, y);
    this.caretaker = new Caretaker();
  }

  public void mutateState(Command command) {
    originator.mutateState(command);
    caretaker.addMemento(originator.save());
  }

  public String[][] getState() {
    return caretaker.getMemento().getState();
  }
}