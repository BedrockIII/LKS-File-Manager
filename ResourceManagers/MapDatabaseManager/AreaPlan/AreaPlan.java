package ResourceManagers.MapDatabaseManager.AreaPlan;

import java.nio.ByteBuffer;

import bFM.Data;

public class AreaPlan implements Data
{
	public final static int AREA_PLAN_SIZE = 32;
	int index;//4 Bytes, 0 ofs
	
	int num1;//2 Bytes, 4 ofs
	int num2;//2 Bytes, 6 ofs
	int num3;//2 Bytes, 8 ofs
	int num4;//2 Bytes, 10 ofs
	int num5;//2 Bytes, 12 ofs
	int num6;//2 Bytes, 14 ofs
	int num7;//2 Bytes, 16 ofs
	int num8;//2 Bytes, 18 ofs
	public AreaPlan(byte[] data)
	{
		initializeFromBytes(data);
	}
	public AreaPlan()
	{
		index = -1;
		num1 = 0;
		num2 = 0;
		num3 = -1;
		num4 = -1;
		num5 = -1;
		num6 = -1;
		num7 = -1;
		num8 = -1;
	}
	private void initializeFromBytes(byte[] dataArr)
	{
		ByteBuffer data = ByteBuffer.wrap(dataArr);
		index = data.getInt(0);
		num1 = data.getShort(4);
		num2 = data.getShort(6);
		num3 = data.getShort(8);
		num4 = data.getShort(10);
		num5 = data.getShort(12);
		num6 = data.getShort(14);
		num7 = data.getShort(16);
		num8 = data.getShort(18);
	}
	public void setData(byte[] data) 
	{
		initializeFromBytes(data);
	}
	public byte[] toBytes() 
	{
		ByteBuffer ret = ByteBuffer.allocate(AREA_PLAN_SIZE);
		ret.putInt(0, index);
		ret.putShort(4, (short) num1);
		ret.putShort(6, (short) num2);
		ret.putShort(8, (short) num3);
		ret.putShort(10, (short) num4);
		ret.putShort(12, (short) num5);
		ret.putShort(14, (short) num6);
		ret.putShort(16, (short) num7);
		ret.putShort(18, (short) num8);
		return ret.array();
	}
	public int getSize() 
	{
		return AREA_PLAN_SIZE;
	}
}
