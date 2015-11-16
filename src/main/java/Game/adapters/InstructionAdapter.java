package Game.adapters;

import Game.Instruction;

public class InstructionAdapter {

	/**
	 *
	 * @param instruction Instrction that needs to be turned into a string
	 */
	public static String toString(Instruction instruction) {
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append("t:instruction;").append(instruction.getPanel().getId() + ";").append(instruction.getIntendedValue() + ";").append(instruction.getWasExecutedCorrectly() + ";");

		return stringBuilder.toString();
	}

	/**
	 *
	 * @param input String that needs to be turned into an instruction object
	 */
	public Instruction toObject(String input) {
		// TODO - implement InstructionAdapter.toObject
		throw new UnsupportedOperationException();
	}

}