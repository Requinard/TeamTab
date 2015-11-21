package Game;

import junit.framework.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by David on 11/16/2015.
 */
public class InstructionTest {
    Panel panel;
    Instruction instruction;
    Player player;

    @Before
    public void setUp() throws Exception {
        panel = new Panel(1, 0, 1, "cut wire", PanelTypeEnum.values()[1]);
        player = new Player("Kaj", "0.0.0.0");
        instruction = new Instruction(panel, 1, player);
    }

    @Test
    public void testGetPanel() throws Exception {
        Assert.assertEquals("Can not get the panels of a instruction", panel, instruction.getPanel());
    }

    @Test
    public void testGetIntendedValue() throws Exception {
        Assert.assertEquals("Can not get the intended value of a instruction", 1, instruction.getIntendedValue());
    }

    @Test
    public void testGetPlayer() throws Exception {
        Assert.assertEquals("Can not get the player of a instruction", player, instruction.getPlayer());
    }

    @Test
    public void testGetWasExecutedCorrectly() throws Exception {
        Assert.assertEquals("Can not get the value of WasExecutedCorrectly", false, instruction.getWasExecutedCorrectly());
    }

    @Test
    public void testSetWasExecutedCorrectly() throws Exception {
        instruction.setWasExecutedCorrectly(true);
        Assert.assertEquals("Can not set the value of WasExecutedCorrectly", true, instruction.getWasExecutedCorrectly());
    }

}