package ResourceManagers.CharacterDatabaseManager.TextureAnimation.Pattern;

import java.util.ArrayList;
import java.util.List;

import bFM.Data;
import bFM.Nameable;

public class PatternList implements Data, Nameable
{
	ArrayList<PatternPart> Patterns = new ArrayList<PatternPart>();
	public PatternList(byte[] data) 
	{
		List<String> lines = bFM.Utils.bytesToStrs(data);
		PatternPart Pattern = null;
		for(String line : lines)
		{
			if(line.indexOf("NAME") != -1)
			{
				Pattern = new PatternPart(line);
				Patterns.add(Pattern);
			}
			else if(line.indexOf("PARTSCOUNT") != -1)
			{
				//Ignore
			}
			else
			{
				if(Pattern!=null)Pattern.addLine(line);
				else System.err.println("Error Importing Animation: PatternList constructor, " + this.getClass());
			}
		}
	}
	public PatternList() 
	{
		Patterns = new ArrayList<PatternPart>();
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
		throw new UnsupportedOperationException("setName(String name) should not be called on type " + this.getClass());
	}
	public String getName() 
	{
		return "Pattern Data";
	}
	public int getSize() 
	{
		throw new UnsupportedOperationException("getSize() should not be called on type " + this.getClass());
	}
	public String toString()
	{
		int partsCount = 0;
		for(PatternPart p : Patterns)
		{
			partsCount += p.getPartsCount();
		}
		String ret = "PARTSCOUNT " + partsCount + "\r\n";
		for(PatternPart p : Patterns)
		{
			ret += p.toString();
		}
		ret += "END\r\n";
		return ret;
	}
	public ArrayList<PatternPart> getPatterns() 
	{
		return Patterns;
	}
}