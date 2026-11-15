package ResourceManagers.CharacterDatabaseManager.TextureAnimation.Pattern;

import java.util.ArrayList;

import bFM.Data;
import bFM.Nameable;

public class Part implements Data, Nameable
{
	String name = "Part";
	Material mat;
	ArrayList<Pattern> patterns = new ArrayList<Pattern>();
	public Part() 
	{
		name = "New Part";
		mat = new Material();
	}
	public Part(String line) 
	{
		name = line.substring(line.indexOf("S ") + 2);
	}
	public void addLine(String line) 
	{
		if(line.indexOf("MATERIAL ") != -1)
		{
			mat = new Material(line);
		}
		else if(line.indexOf("WIDTH ") != -1)
		{
			mat.addLine(line);
		}
		else if(line.indexOf("HEIGHT ") != -1)
		{
			mat.addLine(line);
		}
		else if(line.indexOf("PATTERN ") != -1)
		{
			patterns.add(new Pattern(line));
		}
	}
	public String toString()
	{
		String ret = "  PARTS " + name + "\r\n";
		ret += mat.toString();
		ret += "    PATTERN_NUM " + patterns.size() + "\r\n";
		for(Pattern p : patterns)
		{
			ret += p.toString();
		}
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
	public Material getMaterial() 
	{
		return mat;
	}
	public ArrayList<Pattern> getPatterns() 
	{
		return patterns;
	}
}