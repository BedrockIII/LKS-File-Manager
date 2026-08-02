package ResourceManagers.MSDBManager.Definition;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.charset.Charset;
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
	public byte[] toBytes() 
	{
		byte[] ret = Utils.toByteArr(index, 2);
		ret = Utils.mergeArrays(ret, Utils.toByteArr(num1, 2));
		try {
			ret = Utils.mergeArrays(ret, Utils.encodeStringToBytes(name, Charset.forName("Shift-JIS")));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		byte[] finalRet = new byte[20];
		for(int i = 0; i < finalRet.length && i < ret.length; i++)
		{
			finalRet[i] = ret[i];
		}
		finalRet = Utils.mergeArrays(finalRet, ByteBuffer.allocate(4).putFloat(num2).array());
		finalRet = Utils.mergeArrays(finalRet, ByteBuffer.allocate(4).putFloat(num3).array());
		finalRet = Utils.mergeArrays(finalRet, ByteBuffer.allocate(4).putFloat(num4).array());
		finalRet = Utils.mergeArrays(finalRet, ByteBuffer.allocate(4).putFloat(num5).array());
		return finalRet;
	}
}
