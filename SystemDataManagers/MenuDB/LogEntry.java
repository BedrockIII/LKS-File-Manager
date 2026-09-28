package SystemDataManagers.MenuDB;

import bFM.Data;
import bFM.Utils;

public class LogEntry implements Data
{
	String text = "";
	int flag = 0;
	public LogEntry(String text, int flag)
	{
		this.text = text;
		this.flag = flag;
	}
	public LogEntry(String line)
	{
		this.text = Utils.formatString(line);
	}
	public LogEntry()
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
		String ret = "<<Log Entry>> \"" + text + "\"\n";
		if(flag!=0)
		{
			ret += "\t<<Flag>> " + flag + "\n";
		}
		return ret;
	}
	public void addLine(String line)
	{
		if(line.indexOf("<<Flag>>")!=-1)
		{
			flag = Utils.formatInt(line);
		}
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
