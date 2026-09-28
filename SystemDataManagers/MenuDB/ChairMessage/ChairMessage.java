package SystemDataManagers.MenuDB.ChairMessage;

import bFM.Data;
import bFM.Utils;

public class ChairMessage implements Data
	{
		String message = "Throne Message";
		public ChairMessage(String message, byte[] flags)
		{
			this.message = message;
			//TODO Figure out how flags work
		}
		public ChairMessage(String line)
		{
			this.message = Utils.formatString(message);
		}
		public ChairMessage() 
		{
			// Use Defaults
		}
		public void addLine(String line)
		{
			//TODO
			if(line.indexOf("<<Description>>") != -1)
			{
				
			}
			else if(line.indexOf("<<Image>>") != -1)
			{
				
			}
			else if(line.indexOf("<<Debug Description>>") != -1)
			{
				
			}
		}
		public String toString()
		{
			String ret = "<<Throne Message>> \"" + Utils.toFormatedString(message) + "\"\n";
			//TODO
			//ret += "\t<<Description>> \"" + Utils.toFormatedString(text) + "\"\n";
			//ret += "\t<<Image>> \"" + Utils.toFormatedString(image) + "\"\n";
			//ret += "\t<<Debug Description>> \"" + Utils.toFormatedString(debugText) + "\"\n";
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
		public int getSize() 
		{
			throw new UnsupportedOperationException("getSize() should not be called on type " + this.getClass());
		}
}