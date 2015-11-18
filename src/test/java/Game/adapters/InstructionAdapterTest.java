package Game.adapters;

import Game.Instruction;
import Game.Panel;
import Game.PanelTypeEnum;
import junit.framework.TestCase;

/**
 * Created by David on 11/16/2015.
 */
public class InstructionAdapterTest extends TestCase {

    public void testToString() throws Exception {
        Panel p = new Panel(1, 1, 1, "test", PanelTypeEnum.Button);
        Instruction instruction = new Instruction(p, 1);
        String result = InstructionAdapter.toString(instruction);

        assertEquals("Instrction string did not match supplied instruction", JsonAdapter.toString(instruction, Instruction.class), result);
    }

    public void testToObject() throws Exception {

    }
}