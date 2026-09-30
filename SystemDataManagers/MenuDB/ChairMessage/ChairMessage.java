package SystemDataManagers.MenuDB.ChairMessage;

import bFM.Data;
import bFM.Utils;

public class ChairMessage implements Data
	{
		String message = "Throne Message";
		int flag;
		public ChairMessage(String message, int flag)
		{
			this.message = message;
			this.flag = flag;
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
			if(line.indexOf("<<Flag>>") != -1)
			{
				flag = Utils.formatFlag(line);
			}
		}
		public String toString()
		{
			String ret = "<<Throne Message>> \"" + Utils.toFormatedString(message) + "\"\n";
			ret += "\t<<Flag>> " + flag + "\n";
			return ret;
		}
		public int getFlag()
		{
			return flag;
		}
		public String getText()
		{
			return message;
		}
		public void setFlag(int flag)
		{
			this.flag = flag;
		}
		public void setText(String text)
		{
			this.message = text;
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