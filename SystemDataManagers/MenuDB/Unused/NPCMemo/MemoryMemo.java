package SystemDataManagers.MenuDB.Unused.NPCMemo;

import bFM.Data;
import bFM.Utils;

public class MemoryMemo implements Data
{
	String name = "New Memory";
	public MemoryMemo(String name)
	{
		this.name = Utils.formatString(name);
	}
	public MemoryMemo()
	{
		//Use Defaults
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
	public String toBit()
	{
		String ret = "<<Memory Memo>> \"" + name + "\"\n";
		return ret;
	}
	public String getName()
	{
		return name;
	}
	public void setName(String name)
	{
		this.name = name;
	}
}
