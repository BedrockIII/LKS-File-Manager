package CommonFiles.Collision;

import java.nio.ByteBuffer;

import bFM.Data;

class CollisionHeader implements Data
{
	CollisionList List;
	public CollisionHeader(ColReader file, ByteBuffer data, int headerPos) 
	{
		ColReader.DebugPrint("Header Position: " + headerPos);
		//ColReader.DebugPrint("List Exists: " + ());
		List = new CollisionList(file, data, headerPos + 4);
	}
	public CollisionHeader(int referenceValue) 
	{
		if(referenceValue > 0)
		{
			amount = 1;
			pos = 0;
		}
		else 
		{
			amount = 0;
			pos = 0;
		}
	}
	public boolean equals(String name) 
	{
		throw new UnsupportedOperationException("equals() should not be called on type " + this.getClass());
	}
	public int getListAmount() 
	{
		return amount;
	}
	public void setListPos(int num) 
	{
		pos = num;
	}
	public int getListPos() 
	{
		return pos;
	}
	public void setData(byte[] data) 
	{
		throw new UnsupportedOperationException("setData(byte[] data) should not be called on type " + this.getClass());
	}
	public byte[] toBytes()
	{
		ByteBuffer ret = ByteBuffer.allocate(32);
		ret.putInt(0, amount * 2);
		ret.putInt(4, pos);
		return ret.array();
	}
	public void setName(String name) 
	{
		throw new UnsupportedOperationException("setName(String name) should not be called on type " + this.getClass());
	}
	public String getName() 
	{
		throw new UnsupportedOperationException("getName() should not be called on type " + this.getClass());
	}
	public int getSize() 
	{
		return 32;
	}
}