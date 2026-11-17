package ResourceManagers.MapDatabaseManager.CollisionAttributes;

import java.nio.ByteBuffer;

import bFM.Data;
import bFM.Utils;

public class CollisionAttribute implements Data
{
	public final static int COLLISION_ATTRIBUTE_SIZE = 96;
	String Name;//16 Bytes, 0 ofs
	String String1;//24 Bytes,16 ofs
	String String2;//24 Bytes, 40 ofs
	int num2a;//1 Bytes, 64 ofs
	int num2b;//1 Bytes, 65 ofs
	int num2c;//1 Bytes, 66 ofs
	public CollisionAttribute(byte[] data)
	{
		initializeFromBytes(data);
	}
	public CollisionAttribute()
	{
		Name = "";
		String1 = "";
		String2 = "";
		num2a = 0;
		num2b = 0;
		num2c = 0;
	}
	private void initializeFromBytes(byte[] dataArr)
	{
		ByteBuffer data = ByteBuffer.wrap(dataArr);
		byte[] NameBytes = new byte[16];
		data.get(0, NameBytes, 0, 16);
		byte[] String1Bytes = new byte[24];
		data.get(16, String1Bytes, 0, 24);
		byte[] String2Bytes = new byte[24];
		data.get(40, String2Bytes, 0, 24);
		Name = Utils.decodeBytesToString(NameBytes);
		String1 = Utils.decodeBytesToString(String1Bytes);
		String2 = Utils.decodeBytesToString(String2Bytes);
		
		num2a = data.get(64);
		num2b = data.get(65);
		num2c = data.get(66);
	}
	public void setData(byte[] data) 
	{
		initializeFromBytes(data);
	}
	public byte[] toBytes() 
	{
		ByteBuffer ret = ByteBuffer.allocate(COLLISION_ATTRIBUTE_SIZE);
		ret.put(0, Utils.encodeStringToBytes(Name), 0, Math.min(Name.length(), 15));
		ret.put(16, Utils.encodeStringToBytes(String1), 0, Math.min(String1.length(), 23));
		ret.put(64, (byte) num2a);
		ret.put(65, (byte) num2b);
		ret.put(66, (byte) num2c);
		return ret.array();
	}
	public int getSize() 
	{
		return COLLISION_ATTRIBUTE_SIZE;
	}
}
