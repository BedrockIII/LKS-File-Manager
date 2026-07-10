package ResourceManagers.MSDBManager.Definition;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.Arrays;

import bFM.Utils;

public class MobAttackCol 
{
	short index;
	short num1;
	String name;//16 bytes
	float num2;
	float num3;
	float num4;
	float num5;
	public MobAttackCol(byte[] data)
	{
		index = ByteBuffer.wrap(data).order(ByteOrder.BIG_ENDIAN).getShort(0);
		num1 = ByteBuffer.wrap(data).order(ByteOrder.BIG_ENDIAN).getShort(2);
		name = Utils.decodeBytesToString(bFM.Utils.removeEmptySpace(Arrays.copyOfRange(data, 4, 20)));
		num2 = ByteBuffer.wrap(data).order(ByteOrder.BIG_ENDIAN).getFloat(20);
		num3 = ByteBuffer.wrap(data).order(ByteOrder.BIG_ENDIAN).getFloat(24);
		num4 = ByteBuffer.wrap(data).order(ByteOrder.BIG_ENDIAN).getFloat(28);
		num5 = ByteBuffer.wrap(data).order(ByteOrder.BIG_ENDIAN).getFloat(32);
	}
	public String toString()
	{
		return "Attack Collision "+index +": "+num1 +", \""+name+"\", "+num2 + ", "+num3 + ", "+num4 + ", "+num5 + "\n";
	}
}
