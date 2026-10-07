package SystemDataManagers.MenuDB.Books.RecordBook;

import bFM.Data;
import bFM.Utils;

public class RecordBookEntry implements Data
	{
		String name = "New Record Entry";
		String level0 = "New Record Description 0";
		String level1 = "New Record Description 1";
		String level2 = "New Record Description 2";
		String level3 = "New Record Description 3";
		String image = "hanauta1";
		int level1Count = -1;
		int level2Count = -1;
		int level3Count = -1;
		public RecordBookEntry(String name, String level0, String level1, String level2, String level3, String image, int level1Count, int level2Count, int level3Count)
		{
			this.name = name;
			this.level0 = level0;
			this.level1 = level1;
			this.level2 = level2;
			this.level3 = level3;
			this.image = image;
			this.level1Count = level1Count;
			this.level2Count = level2Count;
			this.level3Count = level3Count;
		}
		public RecordBookEntry(String line)
		{
			this.name = Utils.formatString(line);
		}
		public RecordBookEntry() 
		{
			// Use Defaults
		}
		public void addLine(String line)
		{
			if(line.indexOf("<<Description Level 0>>") != -1)
			{
				level0 = Utils.formatString(line);
			}
			else if(line.indexOf("<<Description Level 1>>") != -1)
			{
				level1 = Utils.formatString(line);
			}
			else if(line.indexOf("<<Description Level 2>>") != -1)
			{
				level2 = Utils.formatString(line);
			}
			else if(line.indexOf("<<Description Level 3>>") != -1)
			{
				level3 = Utils.formatString(line);
			}
			else if(line.indexOf("<<Image>>") != -1)
			{
				image = Utils.formatString(line);
			}
			else if(line.indexOf("<<Level 1 Count>>") != -1)
			{
				level1Count = Utils.formatInt(line);
			}
			else if(line.indexOf("<<Level 2 Count>>") != -1)
			{
				level2Count = Utils.formatInt(line);
			}
			else if(line.indexOf("<<Level 3 Count>>") != -1)
			{
				level3Count = Utils.formatInt(line);
			}
		}
		public String toString()
		{
			String ret = "<<Humming Entry Name>> \"" + Utils.toFormatedString(name) + "\"\n";
			ret += "\t<<Description Level 0>> \"" + Utils.toFormatedString(level0) + "\"\n";
			ret += "\t<<Level 1 Count>> \"" + level1Count + "\"\n";
			ret += "\t<<Description Level 1>> \"" + Utils.toFormatedString(level1) + "\"\n";
			ret += "\t<<Level 2 Count>> \"" + level2Count + "\"\n";
			ret += "\t<<Description Level 2>> \"" + Utils.toFormatedString(level2) + "\"\n";
			ret += "\t<<Level 3 Count>> \"" + level3Count + "\"\n";
			ret += "\t<<Description Level 3>> \"" + Utils.toFormatedString(level3) + "\"\n";
			ret += "\t<<Image>> \"" + Utils.toFormatedString(image) + "\"\n";
			
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
		public void setLevel0Text(String text) 
		{
			level0 = Utils.formatStringChars(text);
		}
		public void setLevel1Text(String text) 
		{
			level1 = Utils.formatStringChars(text);
		}
		public void setLevel2Text(String text) 
		{
			level2 = Utils.formatStringChars(text);
		}
		public void setLevel3Text(String text) 
		{
			level3 = Utils.formatStringChars(text);
		}
		public String getLevel0Text() 
		{
			return Utils.toFormatedString(level0);
		}
		public String getLevel1Text() 
		{
			return Utils.toFormatedString(level1);
		}
		public String getLevel2Text() 
		{
			return Utils.toFormatedString(level2);
		}
		public String getLevel3Text() 
		{
			return Utils.toFormatedString(level3);
		}
		public void setImage(String image) 
		{
			this.image = Utils.formatStringChars(image);
		}
		public String getImage() 
		{
			return Utils.toFormatedString(image);
		}
		public void setLevel1Count(int count) 
		{
			this.level1Count = count;
		}
		public void setLevel2Count(int count) 
		{
			this.level2Count = count;
		}
		public void setLevel3Count(int count) 
		{
			this.level3Count = count;
		}
		public int getLevel1Count() 
		{
			return level1Count;
		}
		public int getLevel2Count() 
		{
			return level2Count;
		}
		public int getLevel3Count() 
		{
			return level3Count;
		}
	}