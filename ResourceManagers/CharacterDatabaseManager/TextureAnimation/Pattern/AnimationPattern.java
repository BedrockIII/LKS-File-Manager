package ResourceManagers.CharacterDatabaseManager.TextureAnimation.Pattern;

import bFM.Data;
import bFM.Nameable;

public class AnimationPattern implements Data, Nameable
{
	String name = "Ptn";
	int num1 = 0;
	int num2 = 0;
	public AnimationPattern()
	{
		
	}
	AnimationPattern(String data)
	{
		String[] vals = data.substring(data.indexOf("N ") + 2).split(" ");
		name = vals[0];
		num1 = Integer.parseInt(vals[1]);
		num2 = Integer.parseInt(vals[2]);
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
	public String toString()
	{
		return "PATTERN " + name + " " + num1 + " " + num2 + "\r\n";
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