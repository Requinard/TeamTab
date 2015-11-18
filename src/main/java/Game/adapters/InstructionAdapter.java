package Game.adapters;

import Game.Instruction;

import java.util.ArrayList;
import java.util.List;

public class InstructionAdapter {

    /**
     * @param instruction Instrction that needs to be turned into a string
     */
    public static String toString(Instruction instruction) {
        return JsonAdapter.toString(instruction, Instruction.class);
    }

    public static String toString(List<Instruction> instructionList) {
        return JsonAdapter.toString(instructionList, ArrayList.class);
    }

    /**
     * @param input String that needs to be turned into an instruction object
     */
    public Instruction toObject(String input) {
        return (Instruction) JsonAdapter.toObject(input, Instruction.class);
    }

    public List<Instruction> toObjects(String input) {
        return (List<Instruction>) JsonAdapter.toObject(input, ArrayList.class);
    }

}