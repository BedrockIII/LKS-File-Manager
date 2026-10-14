package SystemDataManagers.MenuDB.Books.MonsterBook;

import bFM.Data;
import bFM.Utils;

public class MonsterBook implements Data
	{
		String name = "New UMA Entry";
		String description = "New UMA Description";
		String image = "oni_normal";
		public MonsterBook(String name, String description, String image)
		{
			this.name = name;
			this.description = description;
			this.image = image;
		}
		public MonsterBook(String line)
		{
			this.name = Utils.formatString(line);
		}
		public MonsterBook() 
		{
			// Use Defaults
		}
		public void addLine(String line)
		{
			if(line.indexOf("<<Book Description>>") != -1)
			{
				description = Utils.formatString(line);
			}
			else if(line.indexOf("<<Book Image>>") != -1)
			{
				image = Utils.formatString(line);
			}
		}
		public String toString()
		{
			String ret = "<<Book Entry Name>> \"" + Utils.toFormatedString(name) + "\"\n";
			ret += "\t<<Book Description>> \"" + Utils.toFormatedString(description) + "\"\n";
			ret += "\t<<Book Image>> \"" + Utils.toFormatedString(image) + "\"\n";
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
	}