package SystemDataManagers.MenuDB.Name;

import bFM.Data;

public class GeneratedName implements Data
{
	String Name;
	public GeneratedName()
	{
		Name = "New Name";
	}
	public GeneratedName(String Name)
	{
		this.Name = Name;
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
	public String getName()
	{
		return Name;
	}
	public void setName(String Name)
	{
		this.Name = Name;
	}
}
