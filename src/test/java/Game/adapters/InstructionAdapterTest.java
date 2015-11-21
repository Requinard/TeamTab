package Game.adapters;

import Game.Instruction;
import Game.Panel;
import Game.PanelTypeEnum;
import Game.Player;
import junit.framework.TestCase;

/**
 * Created by David on 11/16/2015.
 */
public class InstructionAdapterTest extends TestCase {

    public void testToString() throws Exception {
        Panel panel = new Panel(1, 1, 1, "test", PanelTypeEnum.Button);
        Player player = new Player("Kaj", "0.0.0.0");
        Instruction instruction = new Instruction(panel, 1, player);
        String result = InstructionAdapter.toString(instruction);

        assertEquals("Instruction string did not match supplied instruction", JsonAdapter.toString(instruction, Instruction.class), result);
    }

    public void testToObject() throws Exception {

    }
}