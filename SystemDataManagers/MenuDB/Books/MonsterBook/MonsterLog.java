package SystemDataManagers.MenuDB.Books.MonsterBook;

import bFM.Data;
import bFM.Utils;

public class MonsterLog implements Data
	{
		String name = "New Log Entry";
		int val = 0;
		public MonsterLog(String name, int val)
		{
			this.name = name;
			this.val = val;
		}
		public MonsterLog(String line)
		{
			this.name = Utils.formatString(line);
		}
		public MonsterLog() 
		{
			// Use Defaults
		}
		public void addLine(String line)
		{
			if(line.indexOf("<<Log Flag>>") != -1)
			{
				val = Utils.formatInt(line);
			}
		}
		public String toString()
		{
			String ret = "<<Monster Log Text>> \"" + Utils.toFormatedString(name) + "\"\n";
			ret += "\t<<Log Flag>> " + val + "\n";
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