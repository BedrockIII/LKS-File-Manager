package BrresManager.ModelManager;

import java.nio.ByteBuffer;

import BrresManager.Pointer;
import bFM.Data;

public class VEC3 implements Data
{
	float x;
	float y;
	float z;
	protected VEC3(ByteBuffer data, Pointer pointer) 
	{
		x = data.getFloat(pointer.getPosition());
		y = data.getFloat(pointer.getPosition() + 4);
		z = data.getFloat(pointer.getPosition() + 8);
	}
	public boolean equals(String name) 
	{
		throw new UnsupportedOperationException("equals() should not be called on type " + this.getClass());
	}
	public void setData(byte[] data) 
	{
		throw new UnsupportedOperationException("setData(byte[] data) should not be called on type " + this.getClass());
	}
	public byte[] toBytes() 
	{
		ByteBuffer ret = ByteBuffer.allocate(getSize());
		ret.putFloat(x);
		ret.putFloat(y);
		ret.putFloat(z);
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
		return 12;
	}
	public String toString()
	{
		return "Vertex: " + x + ", " + y + ", " + z + "\n";
	}
}
