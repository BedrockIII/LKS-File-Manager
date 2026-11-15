package ResourceManagers.CharacterDatabaseManager.TextureAnimation.Pattern;

import java.util.ArrayList;

import bFM.Data;
import bFM.Nameable;

public class PartManager implements Data, Nameable
{
	String name = "Part";
	ArrayList<AnimationPattern> patterns = new ArrayList<AnimationPattern>();
	public PartManager(String name) 
	{
		this.name = name;
	}
	public PartManager()
	{
		name = "New Part";
	}
	public void addPattern(String Pattern) 
	{
		if(Pattern.indexOf("LOOP") != -1) return;
		patterns.add(new AnimationPattern(Pattern));
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
		String ret = "  PARTS " + name + "\r\n";
		ret += "  PATTERN_NUM " + (patterns.size() + 1) + "\r\n";
		for(AnimationPattern pattern : patterns)
		{//4 Spaces
			ret += "    " + pattern.toString();
		}
		ret += "    PATTERN LOOP  \r\n";
		return ret;
	}
	public ArrayList<AnimationPattern> getPatterns() 
	{
		return patterns;
	}
}