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
    public static Instruction toObject(String input) {
        return (Instruction) JsonAdapter.toObject(input, Instruction.class);
    }

    public static List<Instruction> toObjects(String input) {
        return (List<Instruction>) JsonAdapter.toObject(input, ArrayList.class);
    }

    /**
     * Author Kamil Wasylkiewicz
     *
     * @param instruction the instruction that needs to be made sendable
     * @return the reference to the instruction
     */
    public static Instruction makeSendable(Instruction instruction) {
        Instruction tempinstruction = new Instruction(instruction.getPanel(), instruction.getIntendedValue(), instruction.getPlayer());
        return tempinstruction;
    }

    /**
     * Author Kamil Wasylkiewicz
     * @param instructions the list of the instrucitons the needs to be made sendable
     * @return the reference to the instruction list
     */
    public static List<Instruction> makeSendable(List<Instruction> instructions) {
        List<Instruction> tempListInstruction = new ArrayList<>();
        for (Instruction instruction : instructions) {
            Instruction tempinstruction = new Instruction(instruction.getPanel(), instruction.getIntendedValue(), instruction.getPlayer());
            tempListInstruction.add(instruction);
        }
        return tempListInstruction;
    }



}