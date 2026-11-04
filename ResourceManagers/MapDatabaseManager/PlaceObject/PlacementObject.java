package ResourceManagers.MapDatabaseManager.PlaceObject;

import java.nio.ByteBuffer;

import bFM.Data;
import bFM.Utils;

public class PlacementObject implements Data
{
	public final static int PLACEMENT_OBJECT_SIZE = 96;
	String FilePath;//32 Bytes, 0 ofs
	String VFP_Name;//16 Bytes, 32 ofs
	int num1;//4 Bytes, 52 ofs
	int num2;//4 Bytes, 64 ofs
	int num3;//4 Bytes, 80 ofs
	int num4;//4 Bytes, 88 ofs
	int num5;//4 Bytes, 92 ofs
	public PlacementObject(byte[] data)
	{
		initializeFromBytes(data);
	}
	public PlacementObject()
	{
		FilePath = "";
		VFP_Name = "";
		num1 = 0;
		num2 = 0;
		num3 = 0;
		num4 = 0;
		num5 = 0;
	}
	private void initializeFromBytes(byte[] dataArr)
	{
		ByteBuffer data = ByteBuffer.wrap(dataArr);
		byte[] pathBytes = new byte[32];
		data.get(pathBytes, 0, 32);
		byte[] nameBytes = new byte[16];
		data.get(nameBytes, 32, 16);
		FilePath = Utils.decodeBytesToString(pathBytes);
		VFP_Name = Utils.decodeBytesToString(nameBytes);
		num1 = data.getInt(52);
		num2 = data.getInt(64);
		num3 = data.getInt(80);
		num4 = data.getInt(88);
		num5 = data.getInt(92);
	}
	public void setData(byte[] data) 
	{
		initializeFromBytes(data);
	}
	public byte[] toBytes() 
	{
		ByteBuffer ret = ByteBuffer.allocate(PLACEMENT_OBJECT_SIZE);
		ret.put(0, Utils.encodeStringToBytes(FilePath), 0, 32);
		ret.put(32, Utils.encodeStringToBytes(VFP_Name), 0, 16);
		ret.putInt(52, num1);
		ret.putInt(64, num2);
		ret.putInt(80, num3);
		ret.putInt(88, num4);
		ret.putInt(92, num5);
		return ret.array();
	}
	public int getSize() 
	{
		return PLACEMENT_OBJECT_SIZE;
	}
}
