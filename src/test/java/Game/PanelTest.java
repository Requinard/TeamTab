package Game;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by david on 12-10-15.
 * @author Frank Hartman
 */
public class PanelTest {
    Panel panel1;
    Panel panel2;
    Instruction instruction1;
    Instruction instruction2;

    @Before
    public void setUp() throws Exception {
        panel1 = new Panel(1, 1, "a", 0, 1, instruction1);
        panel2 = new Panel(2, 1, "b", 0, 3, instruction2);
        instruction1 = new Instruction(panel1, "Click on", 0); //newvalue moet nog in de game logic afgehandeld worden
        instruction2 = new Instruction(panel2, "Click off", 1);
        instruction1.setPanel(panel1);
        instruction2.setPanel(panel2);

    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void testGetId() throws Exception {
        assertEquals(1, panel1.getId());
        assertEquals(2, panel2.getId());
    }

    @Test
    public void testGetType() throws Exception {
        assertEquals(1, panel1.getType());
        assertEquals(1, panel2.getType());
    }

    @Test
    public void testGetText() throws Exception {
        assertEquals("a", panel1.getText());
        assertEquals("b", panel2.getText());
    }

    @Test
    public void testGetMin() throws Exception {
        assertEquals(0, panel1.getMin());
        assertEquals(0, panel2.getMin());
    }

    @Test
    public void testGetMax() throws Exception {
        assertEquals(1, panel1.getMax());
        assertEquals(3, panel2.getMax());
    }

    @Test
    public void testGetCurrent() throws Exception {
        panel1.setCurrent(1);
        assertEquals(1, panel1.getCurrent());
    }

    @Test
    public void testSetCurrent() throws Exception {

    }

    @Test
    public void testGetInstruction() throws Exception {
        //assertEquals(instruction1, panel1.getInstruction());
    }
}