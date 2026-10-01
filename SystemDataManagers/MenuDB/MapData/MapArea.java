package SystemDataManagers.MenuDB.MapData;

import java.nio.ByteBuffer;

import bFM.Data;
import bFM.Utils;

public class MapArea implements Data
{
	String name = "";
	String image = "";
	int flag1 = -1;
	int flag2 = -1;
	int flag3 = -1;
	int flag4 = -1;
	int flag5 = -1;
	public MapArea(String name, String image, int flag1, int flag2, int flag3, int flag4, int flag5)
	{
		this.name = name;
		this.image = image;
		this.flag1 = flag1;
		this.flag2 = flag2;
		this.flag3 = flag3;
		this.flag4 = flag4;
		this.flag5 = flag5;
	}
	public byte[] toNameBytes() 
	{
		return Utils.mergeArrays(Utils.encodeStringToBytes(name), (byte)0x00);
	}
	public byte[] toImageBytes() 
	{
		return Utils.mergeArrays(Utils.encodeStringToBytes(image), (byte)0x00);
	}
	public byte[] toFlagBytes() 
	{
		ByteBuffer ret = ByteBuffer.allocate(20);
		ret.putInt(flag1);
		ret.putInt(flag2);
		ret.putInt(flag3);
		ret.putInt(flag4);
		ret.putInt(flag5);
		return ret.array();
	}
	public void setData(byte[] data) 
	{
		throw new UnsupportedOperationException("setData(byte[] data) should not be called on type " + this.getClass());
	}
	public byte[] toBytes() 
	{
		throw new UnsupportedOperationException("toBytes() should not be called on type " + this.getClass());
	}
	public int getSize() 
	{
		throw new UnsupportedOperationException("getSize() should not be called on type " + this.getClass());
	}
}
