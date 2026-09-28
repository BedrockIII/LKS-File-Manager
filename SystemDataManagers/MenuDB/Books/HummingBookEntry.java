package SystemDataManagers.MenuDB.Books;

import bFM.Data;
import bFM.Utils;

public class HummingBookEntry implements Data
	{
		String name = "New Song Entry";
		String text = "New Song Description";
		String image = "hanauta1";
		int hummingBookFlag = -1;
		public HummingBookEntry(String name, String text, String image, int hummingBookFlag)
		{
			this.name = name;
			this.text = text;
			this.image = image;
			this.hummingBookFlag = hummingBookFlag;
		}
		public HummingBookEntry(String line)
		{
			this.name = Utils.formatString(line);
		}
		public HummingBookEntry() 
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
			else if(line.indexOf("<<Humming Book Flag>>") != -1)
			{
				hummingBookFlag = Utils.formatInt(line);
			}
		}
		public String toString()
		{
			String ret = "<<Humming Entry Name>> \"" + Utils.toFormatedString(name) + "\"\n";
			ret += "\t<<Description>> \"" + Utils.toFormatedString(text) + "\"\n";
			ret += "\t<<Image>> \"" + Utils.toFormatedString(image) + "\"\n";
			ret += "\t<<Humming Book Flag>> \"" + hummingBookFlag + "\"\n";
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
		public void setHummingBookFlag(int hummingBookFlag) 
		{
			this.hummingBookFlag = hummingBookFlag;
		}
		public int getHummingBookFlag() 
		{
			return hummingBookFlag;
		}
	}