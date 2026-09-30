package SystemDataManagers.MenuDB;

import java.util.ArrayList;
import java.util.List;

import bFM.Data;
import bFM.OpenedFile;
import bFM.Utils;

public class MenuStringManager implements OpenedFile
{
	ArrayList<MenuString> Messages = new ArrayList<MenuString>();
	public MenuStringManager(byte[] data)
	{
		for(String s : Utils.extractStrings(data))
		{
			Messages.add(new MenuString(s));
		}
	}
	public MenuStringManager(List<String> lines)
	{
		for(int i = 0; i<lines.size(); i++)
		{
			if(lines.get(i).indexOf("<<String ")!=-1&&lines.get(i).indexOf(">>")!=-1)
			{
				Messages.add(new MenuString(Utils.formatString(lines.get(i))));
			}
		}
	}
	public byte[] toBytes()
	{
		byte[] messageBytes = new byte[0];
		for(int i = 0; i<Messages.size(); i++)
		{
			messageBytes = bFM.Utils.mergeArrays(messageBytes,bFM.Utils.mergeArrays(Messages.get(i).toBytes(),(byte)0x00));
		}
		return messageBytes;
	}
	public String toString()
	{
		String ret = "";
		for(int i = 0; i<Messages.size(); i++)
		{
			ret += "<<String " + (i+1) + ">> \"" + Messages.get(i) + "\"\n";
		}
		return ret;
	}
	public boolean equals(String name) 
	{
		throw new UnsupportedOperationException("equals() should not be called on type " + this.getClass());
	}
	public void setData(byte[] data) 
	{
		Messages.removeAll(Messages);
		for(String s : Utils.extractStrings(data))
		{
			Messages.add(new MenuString(s));
		}
	}
	public void setName(String name) 
	{
		throw new UnsupportedOperationException("setName(String name) should not be called on type " + this.getClass());
	}
	public String getName() 
	{
		return "MenuString.bin";
	}
	public int getSize() 
	{
		return toBytes().length;
	}
	public ArrayList<MenuString> getEntries()
	{
		return Messages;
	}
	public static class MenuString implements Data
	{
		String text = "";
		public MenuString(String text)
		{
			this.text = text;
		}
		public String getText()
		{
			// TODO Auto-generated method stub
			return text;
		}
		public void setText(String text)
		{
			this.text = text;
		}
		public void setData(byte[] data)
		{
			
		}
		public byte[] toBytes()
		{
			return Utils.encodeStringToBytes(Utils.formatStringChars(text));
		}
		public int getSize()
		{
			return toBytes().length;
		}
	}
}
