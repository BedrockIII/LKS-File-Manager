package ResourceManagers.CharacterDatabaseManager.TextureAnimation.Pattern;

import bFM.Data;
import bFM.Nameable;

public class Material implements Data, Nameable
{
	String name = "Material";
	int width = -1;
	int height = -1;
	public Material() 
	{
		name = "New Material";
	}
	public Material(String line) 
	{
		name = line.substring(line.indexOf("L ") + 2);
	}
	public void addLine(String line) 
	{
		if(line.indexOf("WIDTH ") != -1)
		{
			width = bFM.Utils.strToInt(line);
		}
		else if(line.indexOf("HEIGHT ") != -1)
		{
			height = bFM.Utils.strToInt(line);
		}
	}
	public String toString()
	{
		String ret = "   MATERIAL " + name + "\r\n";
		ret += "   WIDTH     " + width + "\r\n";
		ret += "   HEIGHT    " + height + "\r\n";
		return ret;
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
	public int getWidth() 
	{
		return width;
	}
	public void setWidth(int width)
	{
		this.width = width;
	}

	public int getHeight() 
	{
		return height;
	}
	public void setHeight(int height)
	{
		this.height = height;
	}
}