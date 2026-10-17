package SystemDataManagers.MenuDB.Unused.NPCMemo;

import bFM.Data;
import bFM.Utils;

public class KingLevel implements Data
{
	String name = "New Level";
	String text = "New Level Description";
	public KingLevel(String name, String text)
	{
		this.text = text;
		this.name = name;
	}
	public KingLevel(String line)
	{
		this.name = Utils.formatString(line);
	}
	public void addLine(String line)
	{
		if(line.indexOf("<<King Level Description>>") != -1)
		{
			this.text = Utils.formatString(line);
		}
	}
	public KingLevel()
	{
		//Use Defaults
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
	public String toBit()
	{
		String ret = "<<King Level>> \"" + name + "\"\n";
		ret += "\t<<King Level Description>> " + text + "\n";
		return ret;
	}
	public String getName()
	{
		return name;
	}
	public void setName(String name)
	{
		this.name = name;
	}
	public String getText()
	{
		return text;
	}
	public void setText(String text)
	{
		this.text = text;
	}
}
