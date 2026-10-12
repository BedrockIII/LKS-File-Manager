package SystemDataManagers.MenuDB.Books.MovieBook;

import bFM.Data;
import bFM.Utils;

public class MovieBook implements Data
	{
		String name = "New Movie Entry";
		String preText = "New Pre Movie Dialog";
		String postText = "New Post Movie Dialog";
		String description = "hanauta1";
		String image = "mov_No01";
		String movie = "Mov_05";
		public MovieBook(String name, String preText, String postText, String description, String image, String movie)
		{
			this.name = name;
			this.preText = preText;
			this.postText = postText;
			this.description = description;
			this.image = image;
			this.movie = movie;
		}
		public MovieBook(String line)
		{
			this.name = Utils.formatString(line);
		}
		public MovieBook() 
		{
			// Use Defaults
		}
		public void addLine(String line)
		{
			if(line.indexOf("<<Description>>") != -1)
			{
				description = Utils.formatString(line);
			}
			else if(line.indexOf("<<Pre Watch Description>>") != -1)
			{
				preText = Utils.formatString(line);
			}
			else if(line.indexOf("<<Post Watch Description>>") != -1)
			{
				postText = Utils.formatString(line);
			}
			else if(line.indexOf("<<Image>>") != -1)
			{
				image = Utils.formatString(line);
			}
			else if(line.indexOf("<<Movie File Name>>") != -1)
			{
				movie = Utils.formatString(line);
			}
		}
		public String toString()
		{
			String ret = "<<Movie Name>> \"" + Utils.toFormatedString(name) + "\"\n";
			ret += "\t<<Description>> \"" + Utils.toFormatedString(description) + "\"\n";
			ret += "\t<<Pre Watch Description>> \"" + Utils.toFormatedString(preText) + "\"\n";
			ret += "\t<<Post Watch Description>> \"" + Utils.toFormatedString(postText) + "\"\n";
			ret += "\t<<Image>> \"" + Utils.toFormatedString(image) + "\"\n";
			ret += "\t<<Movie File Name>> \"" + movie + "\"\n";
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