package SystemDataManagers.MenuDB.Books.WonderSpotBook;

import bFM.Data;
import bFM.Utils;

public class WonderSpot implements Data
{
	String name = "New Wonderspot";
	String text = "New Wonderspot Description";
	String image = "sp01";
	String debugText = "Detail Text (Unused)";
	public WonderSpot(String name, String text, String image, String debugText)
	{
		this.name = name;
		this.text = text;
		this.image = image;
		this.debugText = debugText;
	}
	public WonderSpot(String line)
	{
		this.name = Utils.formatString(line);
	}
	public WonderSpot() 
	{
		// Use Defaults
	}
	public void addLine(String line)
	{
		if(line.indexOf("<<Description>>") != -1)
		{
			text = Utils.formatString(line);
		}
		else if(line.indexOf("<<Image>>") != -1)
		{
			image = Utils.formatString(line);
		}
		else if(line.indexOf("<<Debug Description>>") != -1)
		{
			debugText = Utils.formatString(line);
		}
	}
	public String toString()
	{
		String ret = "<<Wonder Spot Name>> \"" + Utils.toFormatedString(name) + "\"\n";
		ret += "\t<<Description>> \"" + Utils.toFormatedString(text) + "\"\n";
		ret += "\t<<Image>> \"" + Utils.toFormatedString(image) + "\"\n";
		ret += "\t<<Debug Description>> \"" + Utils.toFormatedString(debugText) + "\"\n";
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
		throw new UnsupportedOperationException("toBytes() should not be called on type " + this.getClass());
	}
	public void setName(String name) 
	{
		this.name = Utils.formatStringChars(name);
	}
	public String getName() 
	{
		return Utils.toFormatedString(name);
	}
	public int getSize() 
	{
		throw new UnsupportedOperationException("getSize() should not be called on type " + this.getClass());
	}
	public void setText(String text) 
	{
		this.text = Utils.formatStringChars(text);
	}
	public String getText() 
	{
		return Utils.toFormatedString(text);
	}
	public void setImage(String image) 
	{
		this.image = Utils.formatStringChars(image);
	}
	public String getImage() 
	{
		return Utils.toFormatedString(image);
	}
	public void setDebugText(String debugText) 
	{
		this.debugText = Utils.formatStringChars(debugText);
	}
	public String getDebugText() 
	{
		return Utils.toFormatedString(debugText);
	}
}