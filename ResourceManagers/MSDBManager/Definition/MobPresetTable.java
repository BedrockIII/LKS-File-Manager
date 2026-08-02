package ResourceManagers.MSDBManager.Definition;

import java.nio.ByteBuffer;

import bFM.Utils;

public class MobPresetTable 
{
	float num0; //4
	float num1; //4
	float num2; //4
	float num3; //4
	float num4;//4
	float num5; //4
	public MobPresetTable(byte[] data)
	{
		ByteBuffer data2 = ByteBuffer.wrap(data);
		num0 = data2.getFloat(0);
		num1 = data2.getFloat(4);
		num2 = data2.getFloat(8);
		num3 = data2.getFloat(12);
		num4 = data2.getFloat(16);
		num5 = data2.getFloat(20);
	
	}
	public String toString()
	{
		return "Mob Preset Table: " + num0 + ", " + num1 + ", " +
				num2 + ", " +num3 + ", " +num4 + ", " +num5 + "\n";
	}
	public byte[] toBytes() 
	{
		byte[] ret = ByteBuffer.allocate(4).putFloat(num0).array();
		ret = Utils.mergeArrays(ret, ByteBuffer.allocate(4).putFloat(num1).array());
		ret = Utils.mergeArrays(ret, ByteBuffer.allocate(4).putFloat(num2).array());
		ret = Utils.mergeArrays(ret, ByteBuffer.allocate(4).putFloat(num3).array());
		ret = Utils.mergeArrays(ret, ByteBuffer.allocate(4).putFloat(num4).array());
		ret = Utils.mergeArrays(ret, ByteBuffer.allocate(4).putFloat(num5).array());
		return ret;
	}
}
