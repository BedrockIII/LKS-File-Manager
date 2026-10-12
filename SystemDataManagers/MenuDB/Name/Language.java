package SystemDataManagers.MenuDB.Name;

import bFM.Data;

public class Language implements Data
{
	Keyboard Capitals;
	Keyboard Lowercase;
	Keyboard Special;
	Language(byte[] capitals, byte[] lowercase, byte[] special)
	{
		Capitals = new Keyboard(capitals);
		Lowercase = new Keyboard(lowercase);
		Special = new Keyboard(special);
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
	public Keyboard getCapitals()
	{
		return Capitals;
	}
	public Keyboard getLowercase()
	{
		return Lowercase;
	}
	public Keyboard getSpecial()
	{
		return Special;
	}
}
