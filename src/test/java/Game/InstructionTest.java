package Game;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by david on 12-10-15.
 */
public class InstructionTest {

    Instruction in1;
    Instruction in2;
    Panel pan1;
    Panel pan2;
    @Before
    public void setUp() throws Exception {
        in1 = new Instruction(pan1, "Click on", 0); //newvalue moet nog in de game logic afgehandeld worden
        in2 = new Instruction(pan2, "Click off", 1);
        pan1 = new Panel(1, 1, "a", 0, 1, in1);
        pan2 = new Panel(2, 1, "b", 0, 1, in2);
        in1.setPanel(pan1);
    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void testToString() throws Exception {
        assertEquals("Click on : a", in1.toString());
        assertEquals("Click off", in2.toString());
    }
}