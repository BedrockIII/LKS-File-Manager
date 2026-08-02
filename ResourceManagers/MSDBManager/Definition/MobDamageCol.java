package ResourceManagers.MSDBManager.Definition;

import java.nio.ByteBuffer;
import java.util.Arrays;

import bFM.Utils;

public class MobDamageCol 
{
	String word1;//32
	float num0; //4
	float num1; //4
	public MobDamageCol(byte[] data)
	{
		ByteBuffer data2 = ByteBuffer.wrap(data);
		word1 = Utils.decodeBytesToString(bFM.Utils.removeEmptySpace(Arrays.copyOfRange(data, 0, 32)));
		num0 = data2.getFloat(32);
		num1 = data2.getFloat(36);
	}
	public String toString()
	{
		return "Mob Damage Collision: \"" + word1 + "\", " + num0 + ", " + num1 + "\n";
	}
	public byte[] toBytes() 
	{
		byte[] ret = Utils.encodeStringToBytes(word1);
		byte[] finalRet = new byte[32];
		for(int i = 0; i < finalRet.length && i < ret.length; i++)
		{
			finalRet[i] = ret[i];
		}
		finalRet = Utils.mergeArrays(finalRet, ByteBuffer.allocate(4).putFloat(num0).array());
		finalRet = Utils.mergeArrays(finalRet, ByteBuffer.allocate(4).putFloat(num1).array());	
		return finalRet;
	}
}
