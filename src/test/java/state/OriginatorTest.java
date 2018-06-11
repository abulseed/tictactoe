package state;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;

import commands.Command;
import utils.Props;

public class OriginatorTest {
  private int size;

  @Before
  public void setup() throws NumberFormatException, Exception {
    this.size = Props.readGridSize();
  }

  @Test
  public void originatorDoesStateMutationCorrectly() throws Exception {
    Originator originator = new Originator();
    Command command = new Command(1, 0, "X");

    originator.mutateState(command);

    assertEquals("X", originator.state[1][0]);
    assertEquals(this.size, originator.state.length);
  }

  @Test
  public void originatorSavesRestoreMementoCorrectly() throws Exception {
    Originator originator = new Originator();
    assertTrue(originator.save() instanceof Memento);
    assertArrayEquals(originator.state, originator.save().getState());

    String[][] state = new String[this.size][this.size];
    Memento mockMemento = mock(Memento.class);
    when(mockMemento.getState()).thenReturn(state);

    originator.restore(mockMemento);
    assertArrayEquals(state, originator.state);
  }
}