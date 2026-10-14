package SystemDataManagers.MenuDB.Books.MonsterBook;

import bFM.Data;
import bFM.Utils;

public class MonsterMod implements Data
{
	String name = "";
	int val = -1;
	public MonsterMod(String name, int val)
	{
		this.name = name;
		this.val = val;
	}
	public MonsterMod(String line)
	{
		this.val = Utils.formatInt(line);
	}
	public void addLine(String line)
	{
		if(line.indexOf("<<Monster Mod Code>>") != -1)
		{
			val = Utils.formatInt(line);
		}
	}
	public MonsterMod() {}
	public String toString()
	{
		return "<<Monster Mod Name>> " + name + "\n" + "<<Monster Mod Code>> " + val + "\n";
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
		this.name = name;
	}
	public String getName() 
	{
		return name;
	}
	public int getSize() 
	{
		throw new UnsupportedOperationException("getSize() should not be called on type " + this.getClass());
	}
	}