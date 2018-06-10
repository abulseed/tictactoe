package state;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.*;

import org.junit.Before;
import org.junit.Test;

import commands.Command;
import utils.Fields;
import utils.Props;

public class OriginatorTest {
  private int size;

  @Before
  public void setup() throws NumberFormatException, Exception {
    this.size = Integer.parseInt(Props.readProp(Fields.SIZE_OF_PLAYGROUND));
  }

  @Test
  public void originatorDoesStateMutationCorrectly() throws Exception {
    Originator originator = new Originator();
    Command command = new Command(1, 0, "X");

    originator.mutateState(command);

    assertEquals(originator.state[1][0], "X");
    assertEquals(originator.state.length, this.size);
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
    assertArrayEquals(originator.state, state);
  }
}