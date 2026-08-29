package VMC;

import java.util.ArrayList;

import VMC.vmInstruction.vmLabel;
import VMC.vmInstruction.vmPush;
import bFM.Utils;

public class VMCDecompiler
{
	public static ArrayList<vmInstruction> addLabels(VMCConverter vmc)
	{
		ArrayList<vmInstruction> ret = addLabels(vmc.getInstructions());
		int index = getLabelIndex(ret, vmc.getNum());
		addLabel(ret, index, "LAB_" + vmc.getNum());
		setStringReferences(vmc.getInstructions(), vmc.getStrings());
		return ret;
	}
	public static ArrayList<vmInstruction> addLabels(ArrayList<vmInstruction> instructions)
	{
		ArrayList<vmInstruction> ret = new ArrayList<vmInstruction>(instructions);
		for(int i = 0; i < instructions.size(); i++)
		{
			vmInstruction code = instructions.get(i);
			switch(code.getInstructionType())
			{
			case vmInstruction.CODE_COMPARE_JUMP:
			case vmInstruction.CODE_JUMP:
				code.addLabel(getLabel(ret, code.getValue(),1));
				break;
			case vmInstruction.CODE_CALL:
				int index = code.getValue();
				vmLabel label = getLabel(ret, index, 2);
				code.addLabel(label);
			default:
				break;
			}
		}
		return ret;
	}
	private static vmLabel getLabel(ArrayList<vmInstruction> instructions, int value, int labelType)
	{
		int index = getLabelIndex(instructions, value);
		if(instructions.get(index) instanceof vmLabel) return (vmLabel) instructions.get(index);
		if(labelType==1)addLabel(instructions, index, "LAB_" + index);
		if(labelType==2)addLabel(instructions, index, "FUN_" + index);
		return (vmLabel) instructions.get(index);
	}
	private static int getLabelIndex(ArrayList<vmInstruction> instructions, int index)
	{
		int count = 0;
		for(int i = 0; i < instructions.size(); i++)
		{
			if(count == index)
			{
				return i;
			}
			else if(count > index)
			{
				System.err.println("Overshot index in VMCDecompiler.getLabelIndex() VMCDecompiler\nTarget: " + index + "\nResult: " + count + "\n");
				return i--;
			}
			count += instructions.get(i).getInstructionSize();
		}
		System.err.println("Did not reach Index in VMCDecompiler.getLabelIndex() \nTarget: " + index + "\nResult: " + count + "\n");
		return 0;
	}
	private static void addLabel(ArrayList<vmInstruction> instructions, int index, String name)
	{
		if(instructions.get(index) instanceof vmLabel) return;
		instructions.add(index, new vmLabel(name));
	}
	private static void setStringReferences(VMCConverter vmc)
	{
		setStringReferences(vmc.getInstructions(), vmc.getStrings());
	}
	private static void setStringReferences(ArrayList<vmInstruction> instructions, ArrayList<VMString> strings)
	{
		for(int i = 0; i < instructions.size(); i++)
		{
			vmInstruction code = instructions.get(i);
			switch(code.getInstructionType())
			{
			case vmInstruction.CODE_PUSH:
				if(((vmPush)code).getMode()==vmInstruction.instructionCodeString)
					((vmPush)code).setValue(getString(instructions, strings, ((vmPush)code).getValueI()));
			default:
				break;
			}
		}
	}
	private static String getString(ArrayList<vmInstruction> instructions, ArrayList<VMString> strings, int index)
	{
		int count = 0/4;
		for(int i = 0; i < instructions.size(); i++)
		{
			count += instructions.get(i).getInstructionSize();
		}
		if(count > index)
		{
			throw new IllegalArgumentException("String Index is out of bounds in VMCDecompiler.getStringIndex() VMCDecompiler\nTarget: " + index + "\nResult: " + count + "\n");
		}
		for(int i = 0; i < strings.size(); i++)
		{
			if(count == index)
			{
				Utils.DebugPrint("Found String \"" + strings.get(i) + "\" at: " + index);
				return strings.get(i).toString();
			}
			else if(count > index)
			{
				System.err.println("Overshot String index in VMCDecompiler.getStringIndex() VMCDecompiler\nTarget: " + index + "\nResult: " + count + "");
				Utils.DebugPrint("Using String \"" + strings.get(i) + "\" at: " + count );
				return strings.get(i).toString();
			}
			count += strings.get(i).getBlockSize();
		}
		System.err.println("Did not reach String Index in VMCDecompiler.getStringIndex()\nTarget: " + index + "\nResult: " + count + "\n");
		return "Failed to Get String";
	}
}
