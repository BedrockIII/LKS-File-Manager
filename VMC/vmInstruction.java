package VMC;

import java.nio.ByteBuffer;

public abstract class vmInstruction 
{
	final private static String[] instrutionTypes = new String[] 
			{"Label","Load","Address","Push","Pop",
			"Assign","Add","Subtract","Multiply","Divide",
			"Modulus","Invert","Compare: ","Jump: ","Compare Jump",
			"Call","Return","Print","External","Halt","Suspend" }; // Names of instructions for equals
	final private static int instructionCodeInt = 0;
	final private static int instructionCodeFloat = 1;
	final private static int instructionCodeString = 2;
	final private static int instructionCodePointer = 3;
	final private int opCode;
	final private int size;
	protected vmInstruction(int opCode, int size) 
	{
		//Stack max seems to be 130
		this.opCode = opCode;
		this.size = size;
	}
	public byte[] toBytes()
	{
		//Not Represented in byte form
		return null;
	}
	public boolean equals(int opCode)
	{
		//Returns true if the instruction's code matches
		return this.opCode == opCode;
	}
	public boolean equals(String instruction)
	{
		int index = -1;
		for(int i = 0; i < instrutionTypes.length; i++)
		{
			if(instrutionTypes[i].toUpperCase().equals(instruction.toUpperCase()))
			{
				index = i;
				break;
			}
		}
		return index == opCode;
	}
	public static vmInstruction createInstruction(String line) 
	{
		vmInstruction ret = new vmLabel(line);
		int operationType = 0;
		for(int i = 0; i < instrutionTypes.length; i++)
		{
			if(line.toLowerCase().indexOf(instrutionTypes[i].toLowerCase())!=-1)
			{
				operationType = i;
				break;
			}
		}
		switch (operationType)
		{
		case 0:
			// Label, or No Operation 
			// Already a label so no need for redeclaration
			break;
		case 1:
			// Load Instruction
			ret = new vmLoad(line);
			break;
		case 2:
			// Address Instruction
			ret = new vmAddress(line);
			break;
		case 3:
			// Push Instruction
			// Add a value to the Stack
			// 3 modes, Int, Float, and another idk, maybe pointer
			ret = new vmPush(line);
			break;
		case 4:
			// Pop Instruction
			// Remove the most recent variable from the Stack
			ret = new vmPop(line);
			break;
		case 5:
			// Assign Instruction
			ret = new vmAssign(line);
			break;
		case 6:
			// Add Instruction
			// Add (and remove) the top 2 values from the stack then add the result to the top of the stack
			ret = new vmAdd(line);
			break;
		case 7:
			// Subtract Instruction
			// Subtract (and remove) the top 2 values from the stack then add the result to the top of the stack
			ret = new vmSubtract(line);
			break;
		case 8:
			// Multiply Instruction
			// Multiply (and remove) the top 2 values from the stack then add the result to the top of the stack
			ret = new vmMultiply(line);
			break;
		case 9:
			// Divide Instruction
			// Add (and remove) the top 2 values from the stack then add the result to the top of the stack
			ret = new vmDivide(line);
			break;
		case 10:
			// Modulus Instruction
			// Modulus (and remove) the top 2 values from the stack then add the result to the top of the stack
			ret = new vmRemainder(line);
			break;
		case 11:
			// Invert Instruction
			// Invert (and remove) the top 2 values from the stack then add the result to the top of the stack
			ret = new vmInvert(line);
			break;
		case 12:
			ret = new vmCompare(line);
			break;
		case 13:
			ret = new vmJump(line);
			break;
		case 14:
			ret = new vmCompareJump(line);
			break;
		case 15:
			ret = new vmCall(line);
			break;
		case 16:
			ret = new vmReturn(line);
			break;
		case 17:
			ret = new vmPrint(line);
			break;
		case 18:
			ret = new vmExternal(line);
			break;
		case 19:
			ret = new vmHalt(line);
			break;
		case 20:
			ret = new vmSuspend(line);
			break;
		}
		return ret;
	}
	public static vmInstruction createInstruction(ByteBuffer data)
	{
		if(data.remaining() < 4) return null;
		int operationType = data.getInt();
		vmInstruction ret = new vmLabel(data);
		switch (operationType)
		{
		case 0:
			// Label, or No Operation 
			// Already a label so no need for redeclaration
			break;
		case 1:
			// Load Instruction
			ret = new vmLoad(data);
			break;
		case 2:
			// Address Instruction
			ret = new vmAddress(data);
			break;
		case 3:
			// Push Instruction
			// Add a value to the Stack
			// 3 modes, Int, Float, and another idk, maybe pointer
			ret = new vmPush(data);
			break;
		case 4:
			// Pop Instruction
			// Remove the most recent variable from the Stack
			ret = new vmPop(data);
			break;
		case 5:
			// Assign Instruction
			ret = new vmAssign(data);
			break;
		case 6:
			// Add Instruction
			// Add (and remove) the top 2 values from the stack then add the result to the top of the stack
			ret = new vmAdd(data);
			break;
		case 7:
			// Subtract Instruction
			// Subtract (and remove) the top 2 values from the stack then add the result to the top of the stack
			ret = new vmSubtract(data);
			break;
		case 8:
			// Multiply Instruction
			// Multiply (and remove) the top 2 values from the stack then add the result to the top of the stack
			ret = new vmMultiply(data);
			break;
		case 9:
			// Divide Instruction
			// Add (and remove) the top 2 values from the stack then add the result to the top of the stack
			ret = new vmDivide(data);
			break;
		case 10:
			// Modulus Instruction
			// Modulus (and remove) the top 2 values from the stack then add the result to the top of the stack
			ret = new vmRemainder(data);
			break;
		case 11:
			// Invert Instruction
			// Invert (and remove) the top 2 values from the stack then add the result to the top of the stack
			ret = new vmInvert(data);
			break;
		case 12:
			ret = new vmCompare(data);
			break;
		case 13:
			ret = new vmJump(data);
			break;
		case 14:
			ret = new vmCompareJump(data);
			break;
		case 15:
			ret = new vmCall(data);
			break;
		case 16:
			ret = new vmReturn(data);
			break;
		case 17:
			ret = new vmPrint(data);
			break;
		case 18:
			ret = new vmExternal(data);
			break;
		case 19:
			ret = new vmHalt(data);
			break;
		case 20:
			ret = new vmSuspend(data);
			break;
		}
		
		
		return ret;
	}
	private static class vmLabel extends vmInstruction
	{
		static final int opCode = 0;
		static final int size = 0;
		private vmLabel(ByteBuffer data) 
		{
			super(opCode, size);
		}
		private vmLabel(String line) 
		{
			super(opCode, size);
		}
		public byte[] toBytes()
		{
			//Not Represented in byte form
			return null;
		}
		public String toString()
		{
			return "Label\n";
		}
	}
	private static class vmLoad extends vmInstruction
	{
		static final int opCode = 1;
		static final int size = 3;
		int value;
		int mode;
		private vmLoad(ByteBuffer data)
		{
			super(opCode, size);
			value = data.getInt();
			mode = data.getInt();
		}
		private vmLoad(String line) 
		{
			super(opCode, size);
			String[] data = line.split(",");
			value = bFM.Utils.strToInt(data[0]);
			mode = bFM.Utils.strToInt(data[1]);
		}
		public byte[] toBytes()
		{
			byte[] ret = bFM.Utils.toByteArr(opCode, 4);
			ret = bFM.Utils.mergeArrays(ret, bFM.Utils.toByteArr(value, 4));
			ret = bFM.Utils.mergeArrays(ret, bFM.Utils.toByteArr(mode, 4));
			return ret;
		}
		public String toString()
		{
			return "Load " + value + ", " + mode + "\n";
		}
	}
	private static class vmAddress extends vmInstruction
	{
		static final int opCode = 2;
		static final int size = 3;
		int value;
		int mode;
		private vmAddress(ByteBuffer data)
		{
			super(opCode, size);
			value = data.getInt();
			mode = data.getInt();
		}
		private vmAddress(String line) 
		{
			super(opCode, size);
			String[] data = line.split(" ");
			value = bFM.Utils.strToInt(data[1]);
			mode = bFM.Utils.strToInt(data[2]);
		}
		public byte[] toBytes()
		{
			byte[] ret = bFM.Utils.toByteArr(opCode, 4);
			ret = bFM.Utils.mergeArrays(ret, ByteBuffer.allocate(4).putInt(value).array());
			ret = bFM.Utils.mergeArrays(ret, bFM.Utils.toByteArr(mode, 4));
			return ret;
		}
		public String toString()
		{
			return "Address " + value + ", " + mode + "\n";
		}
	}
	private static class vmPush extends vmInstruction
	{
		static final int opCode = 3;
		static final int size = 3;
		int valueInt;
		float valueFloat;
		int mode;
		private vmPush(ByteBuffer data)
		{
			super(opCode, size);
			int pos = data.position();
			mode = data.getInt(pos + 4);
			if(mode == 0)
			{
				valueInt = data.getInt(pos);
			}
			else if(mode == 1)
			{
				valueFloat = data.getFloat(pos);
			}
			else if(mode == 2)
			{
				valueInt = data.getInt(pos);
			}
			else throw new IllegalArgumentException("Push Instruction Type is Invalid Mode: " + mode);
			data.position(pos+8);
		}
		private vmPush(String line)
		{
			super(opCode, size);
			if(line.indexOf("Int")!= -1)
			{
				mode = instructionCodeInt;
				valueInt = bFM.Utils.strToInt(line);
			}
			else if(line.indexOf("Float")!= -1)
			{
				mode = instructionCodeFloat;
				valueFloat = bFM.Utils.strToFloat(line);
			}
			else if(line.indexOf("String")!= -1)
			{
				mode = instructionCodeString;
				valueInt = bFM.Utils.strToInt(line);
			}
			else
			{
				mode = -1;
				throw new IllegalArgumentException("Push Instruction Type is Invalid Mode: " + mode);
			}
		}
		public byte[] toBytes()
		{
			byte[] ret = bFM.Utils.toByteArr(opCode, 4);
			if(mode==instructionCodeFloat)
			{
				ret = bFM.Utils.mergeArrays(ret, ByteBuffer.allocate(4).putFloat(valueFloat).array());
			}
			else
			{
				ret = bFM.Utils.mergeArrays(ret, ByteBuffer.allocate(4).putInt(valueInt).array());
			}
			ret = bFM.Utils.mergeArrays(ret, bFM.Utils.toByteArr(mode, 4));
			return ret;
		}
		public String toString()
		{
			if(mode == instructionCodeInt)
			{
				return "Push Int " + valueInt + "\n";
			}
			else if(mode == instructionCodeFloat)
			{
				return "Push Float " + valueFloat + "\n";
			}
			else if(mode == instructionCodeString)
			{
				return "Push String " + valueInt + "\n";
			}
			throw new IllegalArgumentException("Push Instruction Type is Invalid Mode: " + mode);
		}
	}
	private static class vmPop extends vmInstruction
	{
		static final int opCode = 4;
		static final int size = 1;
		private vmPop(ByteBuffer data)
		{
			super(opCode, size);
		}
		private vmPop(String line) 
		{
			super(opCode, size);
			String[] data = line.split(" ");
		}
		public byte[] toBytes()
		{
			byte[] ret = bFM.Utils.toByteArr(opCode, 4);
			return ret;
		}
		public String toString()
		{
			return "Pop\n";
		}
	}
	private static class vmAssign extends vmInstruction
	{
		static final int opCode = 5;
		static final int size = 1;
		private vmAssign(ByteBuffer data)
		{
			super(opCode, size);
		}
		private vmAssign(String line) 
		{
			super(opCode, size);
			String[] data = line.split(" ");
		}
		public byte[] toBytes()
		{
			byte[] ret = bFM.Utils.toByteArr(opCode, 4);
			return ret;
		}
		public String toString()
		{
			return "Assign\n";
		}
	}
	private static class vmAdd extends vmInstruction
	{
		static final int opCode = 6;
		static final int size = 1;
		private vmAdd(ByteBuffer data)
		{
			super(opCode, size);
		}
		private vmAdd(String line) 
		{
			super(opCode, size);
			String[] data = line.split(" ");
		}
		public byte[] toBytes()
		{
			byte[] ret = bFM.Utils.toByteArr(opCode, 4);
			return ret;
		}
		public String toString()
		{
			return "Add\n";
		}
	}
	private static class vmSubtract extends vmInstruction
	{
		static final int opCode = 7;
		static final int size = 1;
		private vmSubtract(ByteBuffer data)
		{
			super(opCode, size);
		}
		private vmSubtract(String line) 
		{
			super(opCode, size);
			String[] data = line.split(" ");
		}
		public byte[] toBytes()
		{
			byte[] ret = bFM.Utils.toByteArr(opCode, 4);
			return ret;
		}
		public String toString()
		{
			return "Subtract\n";
		}
	}
	private static class vmMultiply extends vmInstruction
	{
		static final int opCode = 8;
		static final int size = 1;
		private vmMultiply(ByteBuffer data)
		{
			super(opCode, size);
		}
		private vmMultiply(String line) 
		{
			super(opCode, size);
			String[] data = line.split(" ");
		}
		public byte[] toBytes()
		{
			byte[] ret = bFM.Utils.toByteArr(opCode, 4);
			return ret;
		}
		public String toString()
		{
			return "Multiply\n";
		}
	}
	private static class vmDivide extends vmInstruction
	{
		static final int opCode = 9;
		static final int size = 1;
		private vmDivide(ByteBuffer data)
		{
			super(opCode, size);
		}
		private vmDivide(String line) 
		{
			super(opCode, size);
			String[] data = line.split(" ");
		}
		public byte[] toBytes()
		{
			byte[] ret = bFM.Utils.toByteArr(opCode, 4);
			return ret;
		}
		public String toString()
		{
			return "Divide\n";
		}
	}
	private static class vmRemainder extends vmInstruction
	{
		static final int opCode = 10;
		static final int size = 1;
		private vmRemainder(ByteBuffer data)
		{
			super(opCode, size);
		}
		private vmRemainder(String line) 
		{
			super(opCode, size);
			String[] data = line.split(" ");
		}
		public byte[] toBytes()
		{
			byte[] ret = bFM.Utils.toByteArr(opCode, 4);
			return ret;
		}
		public String toString()
		{
			return "Modulus\n";
		}
	}
	private static class vmInvert extends vmInstruction
	{
		static final int opCode = 11;
		static final int size = 1;
		private vmInvert(ByteBuffer data)
		{
			super(opCode, size);
		}
		private vmInvert(String line) 
		{
			super(opCode, size);
			String[] data = line.split(" ");
		}
		public byte[] toBytes()
		{
			byte[] ret = bFM.Utils.toByteArr(opCode, 4);
			return ret;
		}
		public String toString()
		{
			return "Invert\n";
		}
	}
	private static class vmCompare extends vmInstruction
	{
		static final int opCode = 12;
		static final int size = 2;
		int value;
		private vmCompare(ByteBuffer data)
		{
			super(opCode, size);
			value = data.getInt();
		}
		private vmCompare(String line) 
		{
			super(opCode, size);
			String[] data = line.split(" ");
			value = bFM.Utils.strToInt(data[1]);
		}
		public byte[] toBytes()
		{
			byte[] ret = bFM.Utils.toByteArr(opCode, 4);
			ret = bFM.Utils.mergeArrays(ret, bFM.Utils.toByteArr(value, 4));
			return ret;
		}
		public String toString()
		{
			return "Compare: " + value + "\n";
		}
	}
	private static class vmJump extends vmInstruction
	{
		static final int opCode = 13;
		static final int size = 2;
		int value;
		private vmJump(ByteBuffer data)
		{
			super(opCode, size);
			value = data.getInt();
		}
		private vmJump(String line) 
		{
			super(opCode, size);
			String[] data = line.split(" ");
			value = bFM.Utils.strToInt(data[1]);
		}
		public byte[] toBytes()
		{
			byte[] ret = bFM.Utils.toByteArr(opCode, 4);
			ret = bFM.Utils.mergeArrays(ret, ByteBuffer.allocate(4).putInt(value).array());
			return ret;
		}
		public String toString()
		{
			return "Jump: " + value + "\n";
		}
	}
	private static class vmCompareJump extends vmInstruction
	{
		static final int opCode = 14;
		static final int size = 3;
		int value;
		int mode;
		private vmCompareJump(ByteBuffer data)
		{
			super(opCode, size);
			value = data.getInt();
			mode = data.getInt();
		}
		private vmCompareJump(String line) 
		{
			super(opCode, size);
			String[] data = line.split(",");
			value = bFM.Utils.strToInt(data[0]);
			mode = bFM.Utils.strToInt(data[1]);
		}
		public byte[] toBytes()
		{
			byte[] ret = bFM.Utils.toByteArr(opCode, 4);
			ret = bFM.Utils.mergeArrays(ret, bFM.Utils.toByteArr(value, 4));
			ret = bFM.Utils.mergeArrays(ret, bFM.Utils.toByteArr(mode, 4));
			return ret;
		}
		public String toString()
		{
			return "Compare Jump " + value + ", " + mode + "\n";
		}
	}
	private static class vmCall extends vmInstruction
	{
		static final int opCode = 15;
		static final int size = 3;
		int value;
		int mode;
		private vmCall(ByteBuffer data)
		{
			super(opCode, size);
			value = data.getInt();
			mode = data.getInt();
		}
		private vmCall(String line) 
		{
			super(opCode, size);
			String[] data = line.split(" ");
			value = bFM.Utils.strToInt(data[1]);
			mode = bFM.Utils.strToInt(data[2]);
		}
		public byte[] toBytes()
		{
			byte[] ret = bFM.Utils.toByteArr(opCode, 4);
			ret = bFM.Utils.mergeArrays(ret, ByteBuffer.allocate(4).putInt(value).array());
			ret = bFM.Utils.mergeArrays(ret, bFM.Utils.toByteArr(mode, 4));
			return ret;
		}
		public String toString()
		{
			return "Call " + value + ", " + mode + "\n";
		}
	}
	private static class vmReturn extends vmInstruction
	{
		static final int opCode = 16;
		static final int size = 1;
		private vmReturn(ByteBuffer data)
		{
			super(opCode, size);
		}
		private vmReturn(String line) 
		{
			super(opCode, size);
		}
		public byte[] toBytes()
		{
			byte[] ret = bFM.Utils.toByteArr(opCode, 4);
			return ret;
		}
		public String toString()
		{
			return "Return\n";
		}
	}
	private static class vmPrint extends vmInstruction
	{
		static final int opCode = 17;
		static final int size = 2;
		int ArgumentCount;
		private vmPrint(ByteBuffer data)
		{
			super(opCode, size);
			ArgumentCount = data.getInt();
		}
		private vmPrint(String line) 
		{
			super(opCode, size);
			String[] data = line.split(" ");
			ArgumentCount = bFM.Utils.strToInt(data[1]);
		}
		public byte[] toBytes()
		{
			byte[] ret = bFM.Utils.toByteArr(opCode, 4);
			ret = bFM.Utils.mergeArrays(ret, bFM.Utils.toByteArr(ArgumentCount, 4));
			return ret;
		}
		public String toString()
		{
			return "Print " + ArgumentCount + "\n";
		}
	}
	private static class vmExternal extends vmInstruction
	{
		static final int opCode = 18;
		static final int size = 2;
		int instructionsBackForFunctionIndex; // How many instructions back is the index of the function for the game to call?
		private vmExternal(ByteBuffer data)
		{
			super(opCode, size);
			instructionsBackForFunctionIndex = data.getInt();
		}
		private vmExternal(String line) 
		{
			super(opCode, size);
			String[] data = line.split(" ");
			instructionsBackForFunctionIndex = bFM.Utils.strToInt(data[1]);
		}
		public byte[] toBytes()
		{
			byte[] ret = bFM.Utils.toByteArr(opCode, 4);
			ret = bFM.Utils.mergeArrays(ret, bFM.Utils.toByteArr(instructionsBackForFunctionIndex, 4));
			return ret;
		}
		public String toString()
		{
			return "External " + instructionsBackForFunctionIndex + "\n";
		}
	}
	private static class vmHalt extends vmInstruction
	{
		static final int opCode = 19;
		static final int size = 1;
		private vmHalt(ByteBuffer data)
		{
			super(opCode, size);
		}
		private vmHalt(String line) 
		{
			super(opCode, size);
		}
		public byte[] toBytes()
		{
			byte[] ret = bFM.Utils.toByteArr(opCode, 4);
			return ret;
		}
		public String toString()
		{
			return "Halt\n";
		}
	}
	private static class vmSuspend extends vmInstruction
	{
		static final int opCode = 20;
		static final int size = 1;
		private vmSuspend(ByteBuffer data)
		{
			super(opCode, size);
		}
		private vmSuspend(String line) 
		{
			super(opCode, size);
		}
		public byte[] toBytes()
		{
			byte[] ret = bFM.Utils.toByteArr(opCode, 4);
			return ret;
		}
		public String toString()
		{
			return "Suspend\n";
		}
	}
}