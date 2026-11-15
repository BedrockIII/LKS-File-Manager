package ResourceManagers.CharacterDatabaseManager.TextureAnimation.Pattern;

import java.util.ArrayList;

import bFM.Data;
import bFM.Nameable;

public class PatternPart implements Data, Nameable
{
	ArrayList<Part> Parts = new ArrayList<Part>();
	Part lastPart;
	String name;
	public PatternPart() 
	{
		name = "New Pattern";
	}
	public PatternPart(String line) 
	{
		name = line.substring(line.indexOf(' ') + 1);
	}
	public void addLine(String line) 
	{
		if(line.indexOf("PARTS") != -1)
		{
			lastPart = new Part(line);
			Parts.add(lastPart);
		}
		else
		{
			lastPart.addLine(line);
		}
	}
	public String toString()
	{
		String ret = "NAME " + name + "\r\n";
		for(Part part : Parts)
		{
			ret += part.toString();
		}
		return ret;
	}
	public int getPartsCount() 
	{
		return Parts.size();
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
	public ArrayList<Part> getParts() 
	{
		return Parts;
	}
}