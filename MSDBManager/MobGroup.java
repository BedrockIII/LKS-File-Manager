package MSDBManager;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

public class MobGroup //probably who has attack bonus
{
	int groupIndex; //First 2 Bytes
	int num1; //Next 2 Bytes
	int objectIndex; //Next 2 Bytes
	int objectCount; //Next 2 Bytes
	int num4; //Next 2 Bytes
	int groupNumber; //Next 2 Bytes
	int num6; //Next 2 Bytes
	int num7; //Next 2 Bytes
	float num8; //Next 4 Bytes
	int num9; //Next 2 Bytes
	boolean changableIndex = false;
	public MobGroup(byte[] data)
	{
		groupIndex = (int)bFM.Utils.getShort(data, 0);
		num1 = (int)bFM.Utils.getShort(data, 2);
		objectIndex = (int)bFM.Utils.getShort(data, 4);
		objectCount = (int)bFM.Utils.getShort(data, 6);
		num4 = (int)bFM.Utils.getShort(data, 8);
		groupNumber = (int)bFM.Utils.getShort(data, 10);
		num6 = (int)bFM.Utils.getShort(data, 12);
		num7 = (int)bFM.Utils.getShort(data, 14);
		num8 = (ByteBuffer.wrap(data).order(ByteOrder.BIG_ENDIAN).getFloat(16));
	}
	public boolean groupIndex(int code)
	{
		if(code==groupIndex)return true;
		return false;
	}
	public MobGroup(int num12, int num2, int objectIndex, int num11, int num122, int num13, int num14, int num15, float num16) 
	{
		groupIndex = num12;
		num1 = num2;
		this.objectIndex = objectIndex;
		objectCount = num11;
		num4 = num122;
		groupNumber = num13;
		num6 = num14;
		num7 = num15;
		num8 = num16;
	}
	public MobGroup(int num12, int num2, int objectIndex, int num11, int num122, int num13, int num14, int num15, float num16, boolean b) {
		groupIndex = num12;
		num1 = num2;
		this.objectIndex = objectIndex;
		objectCount = num11;
		num4 = num122;
		groupNumber = num13;
		num6 = num14;
		num7 = num15;
		num8 = num16;
		changableIndex = b;
	}
	public boolean generatedIndex()
	{
		return changableIndex;
	}
	public String toString()
	{
		return ""+groupIndex +", "+num1 +", "+objectIndex +", "+objectCount +", "+num4 +", "+groupNumber +", "+num6 +", "+num7 +", "+num8 +"\n";
	}
	public byte[] toBytes() 
	{
		byte[] ret = bFM.Utils.toByteArr(groupIndex,2);
		ret = bFM.Utils.mergeArrays(ret, bFM.Utils.toByteArr(num1,2));
		ret = bFM.Utils.mergeArrays(ret, bFM.Utils.toByteArr(objectIndex,2));
		ret = bFM.Utils.mergeArrays(ret, bFM.Utils.toByteArr(objectCount,2));
		ret = bFM.Utils.mergeArrays(ret, bFM.Utils.toByteArr(num4,2));
		ret = bFM.Utils.mergeArrays(ret, bFM.Utils.toByteArr(groupNumber,2));
		ret = bFM.Utils.mergeArrays(ret, bFM.Utils.toByteArr(num6,2));
		ret = bFM.Utils.mergeArrays(ret, bFM.Utils.toByteArr(num7,2));
		ret = bFM.Utils.mergeArrays(ret, ByteBuffer.allocate(4).putFloat(num8).array());
		return ret;
	}
	public int getObjectCount()
	{
		return objectCount;
	}
	public String bMos() 
	{
		return "\tMonster Group: "+ groupIndex + ", "+num1+", "+num4 +", "+groupNumber +", "+num6 +", "+num7 +", "+num8 +"\n";
	}	
	public int getObjectIndex() 
	{
		return objectIndex;
	}
	public int getCode() {
		// TODO Auto-generated method stub
		return groupIndex;
	}
	public String bMos2() 
	{
		return "Unsorted Group: "+groupIndex +", "+num1+", "+num4 +", "+groupNumber +", "+num6 +", "+num7 +", "+num8+"\n";
	}
	public void newCode() 
	{
		groupIndex = (int)(Short.MAX_VALUE*Math.random());
	}
}
