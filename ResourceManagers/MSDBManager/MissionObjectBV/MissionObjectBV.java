package ResourceManagers.MSDBManager.MissionObjectBV;

import bFM.Data;

public class MissionObjectBV implements Data
{
	byte[] assign, header, mapJump, NPCPreset;
	public MissionObjectBV(byte[] assign, byte[] header, byte[] mapJump, byte[] NPCPreset)
	{
		this.assign = assign;
		this.header = header;
		this.mapJump = mapJump;
		this.NPCPreset = NPCPreset;
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
		throw new UnsupportedOperationException("toBytes() should not be called on type " + this.getClass());
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
		throw new UnsupportedOperationException("getSize() should not be called on type " + this.getClass());
	}
	public byte[] getAssign() 
	{
		// TODO Auto-generated method stub
		return assign;
	}
	public byte[] getHeader() {
		// TODO Auto-generated method stub
		return header;
	}
	public byte[] getMapJump() {
		// TODO Auto-generated method stub
		return mapJump;
	}
	public byte[] getNPCPreset() {
		// TODO Auto-generated method stub
		return NPCPreset;
	}
}
