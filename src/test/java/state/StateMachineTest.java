package state;

import org.junit.Test;

import commands.Command;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class StateMachineTest {
  @Test
  public void stateMutatesWithTheRightCalls() throws Exception {
    Caretaker mockCaretaker = mock(Caretaker.class);
    Originator mockOriginator = mock(Originator.class);

    StateMachine stateMachine = new StateMachine(mockCaretaker, mockOriginator);
    Command mockCommand = new Command(0, 0, "X");
    stateMachine.mutateState(mockCommand);

    verify(mockOriginator, atLeastOnce()).mutateState(mockCommand);
    verify(mockCaretaker, atLeastOnce()).addMemento(mockOriginator.save());
    // assertTrue(stateMachine.getState()[0][0].equals("X"));
  }

  @Test
  public void stateMutatesWithTheCorrectValue() throws Exception {
    StateMachine stateMachine = StateMachine.getInstance();

    Command mockCommand = new Command(0, 0, "X");
    stateMachine.mutateState(mockCommand);

    assertEquals("X", stateMachine.getState()[0][0]);
  }
}