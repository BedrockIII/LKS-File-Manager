package SystemDataManagers.MenuDB.CastlePicture;

import bFM.Data;
import bFM.Utils;

public class CastlePicture implements Data
{
	String text = "";
	int flag1 = 0;
	int flag2 = 0;
	int flag3 = -1;
	int flag4 = -1;
	int flag5 = 0;
	int flag6 = 0;
	public CastlePicture(String text, int flag1, int flag2, int flag3, int flag4, int flag5, int flag6)
	{
		this.text = text;
		this.flag1 = flag1;
		this.flag2 = flag2;
		this.flag3 = flag3;
		this.flag4 = flag4;
		this.flag5 = flag5;
		this.flag6 = flag6;
	}
	public CastlePicture(String line)
	{
		this.text = Utils.formatString(line);
	}
	public CastlePicture()
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
		String ret = "<<Castle Picture Image>> \"" + text + "\"\n";
		if(flag1!=0)
		{
			ret += "\t<<Flag 1>> " + flag1 + "\n";
		}
		if(flag2!=0)
		{
			ret += "\t<<Flag 2>> " + flag2 + "\n";
		}
		if(flag3!=-1)
		{
			ret += "\t<<Flag 3>> " + flag3 + "\n";
		}
		if(flag4!=-1)
		{
			ret += "\t<<Flag 4>> " + flag4 + "\n";
		}
		if(flag5!=0)
		{
			ret += "\t<<Flag 5>> " + flag5 + "\n";
		}
		if(flag6!=0)
		{
			ret += "\t<<Flag 6>> " + flag6 + "\n";
		}
		return ret;
	}
	public void addLine(String line)
	{
		if(line.indexOf("<<Flag 1>>")!=-1)
		{
			flag1 = Utils.formatInt(line);
		}
		if(line.indexOf("<<Flag 2>>")!=-1)
		{
			flag2 = Utils.formatInt(line);
		}
		if(line.indexOf("<<Flag 3>>")!=-1)
		{
			flag3 = Utils.formatInt(line);
		}
		if(line.indexOf("<<Flag 4>>")!=-1)
		{
			flag4 = Utils.formatInt(line);
		}
		if(line.indexOf("<<Flag 5>>")!=-1)
		{
			flag5 = Utils.formatInt(line);
		}
		if(line.indexOf("<<Flag 6>>")!=-1)
		{
			flag6 = Utils.formatInt(line);
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
