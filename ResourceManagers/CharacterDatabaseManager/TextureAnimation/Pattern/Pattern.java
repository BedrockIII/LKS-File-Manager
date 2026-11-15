package ResourceManagers.CharacterDatabaseManager.TextureAnimation.Pattern;

import bFM.Data;
import bFM.Nameable;

public class Pattern implements Data, Nameable
{
	int index;
	String name = "Pattern";
	int num1;
	int num2;
	public Pattern() 
	{
		index = 0;
		num1 = 0;
		num2 = 0;
	}
	public Pattern(String line) 
	{
		String[] data = line.substring(line.indexOf("N ") + 2).split(" ");
		index = bFM.Utils.strToInt(data[0]);
		name = data[1];
		num1 = bFM.Utils.strToInt(data[2]);
		num2 = bFM.Utils.strToInt(data[3]);
	}
	public String toString()
	{
		return "      PATTERN " + index + " " + name + " " + num1 + " " + num2 + "\r\n";
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
		throw new UnsupportedOperationException("setData(byte[] data) should not be called on type " + this.getClass());
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
	public int getIndex() 
	{
		return index;
	}
	public void setIndex(int index)
	{
		this.index = index;
	}
	public int getNum1() 
	{
		return num1;
	}
	public void setNum1(int num1)
	{
		this.num1 = num1;
	}

	public int getNum2() 
	{
		return num2;
	}
	public void setNum2(int num2)
	{
		this.num2 = num2;
	}
}