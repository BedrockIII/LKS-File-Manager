package ResourceManagers.MSDBManager.Definition;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.Arrays;

import bFM.Utils;

public class MobAttackElem 
{
	int index;//2
	int num1;//2
	int num2;//2
	int num3;//2
	int num4;//2
	int num5;//2
	String name;//16 bytes
	float num6;
	byte num7;
	byte num8;
	byte num9;
	byte num10;
	public MobAttackElem(byte[] data)
	{
		index = bFM.Utils.getShort(data, 0);
		num1 = bFM.Utils.getShort(data, 2);
		
		num2 = bFM.Utils.getShort(data, 4);
		num3 = bFM.Utils.getShort(data, 6);
		num4 = bFM.Utils.getShort(data, 8);
		num5 = bFM.Utils.getShort(data, 10);
		name = Utils.decodeBytesToString(bFM.Utils.removeEmptySpace(Arrays.copyOfRange(data, 12, 28)));
		num6 = ByteBuffer.wrap(data).order(ByteOrder.BIG_ENDIAN).getFloat(28);
		num7 = data[32];
		num8 = data[33];
		num9 = data[34];
		num10 = data[35];
	}
	public String toString()
	{
		return "Attack Element " + index + ": " + num1 + ", " + num2 + ", " + num3 + 
				", " + num4 + ", " + num5 + ", \"" + name + "\", " + num6 + ", " + num7 + ", " + num8 + ", " + num9 + ", " + num10 + ", " + "\n";
	}
}
