package ResourceManagers.CharacterDatabaseManager.TextureAnimation.Animation;

import ResourceManagers.CharacterDatabaseManager.TextureAnimation.Pattern.PartManager;
import bFM.Data;
import bFM.Nameable;

public class Animation implements Data, Nameable
{
	String name = "";
	PartManager part = null;
	public Animation() 
	{
		name = "New Animation";
		part = new PartManager();
	}
	public Animation(String line) 
	{
		name = line.substring(line.indexOf(' ') + 1);
	}
	public void addLine(String line) 
	{
		if(line.indexOf("PARTS") != -1)
		{
			part = new PartManager(line.substring(line.lastIndexOf(' ') + 1));
		}
		else if(line.indexOf("PATTERN ") != -1)
		{
			part.addPattern(line);
		}
	}
	public String toString()
	{
		String ret = "NAME " + name + "\r\n";
		ret += part.toString();
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
	public PartManager getPart() 
	{
		return part;
	}
}