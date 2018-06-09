package state;

import commands.Command;
import exceptions.ValidationException;

public class Originator {
  private String state[][];

  public Originator(int x, int y) {
    this.state = new String[x][y];
  }

  public void mutateState(Command command) throws Exception {
    try {
      if (this.state[command.getxCoo()][command.getyCoo()] != null) {
        throw new ValidationException("Place is already marked");
      }
      this.state[command.getxCoo()][command.getyCoo()] = command.getMark();
    } catch (Exception e) {
      throw e;
    }
  }

  public Memento save() {
    return new Memento(state);
  }

  public void restore(Memento m) {
    state = m.getState();
  }
}