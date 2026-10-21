package SystemDataManagers.MenuDB.MenuString;

import java.util.ArrayList;
import java.util.List;

import bFM.IntermediateFileFormat;
import bFM.OpenedFile;
import bFM.Utils;

public class MenuStringManager implements OpenedFile, IntermediateFileFormat
{
	ArrayList<MenuString> Entries = new ArrayList<MenuString>();
	public MenuStringManager(byte[] data)
	{
		initializeFromBytes(data);
	}
	public MenuStringManager(List<String> lines)
	{
		initializeFromLines(lines);
	}
	private void initializeFromBytes(byte[] data)
	{
		for(String s : Utils.extractStrings(data))
		{
			Entries.add(new MenuString(s));
		}
	}
	private void initializeFromLines(List<String> lines)
	{
		for(int i = 0; i<lines.size(); i++)
		{
			if(lines.get(i).indexOf("<<String ")!=-1&&lines.get(i).indexOf(">>")!=-1)
			{
				Entries.add(new MenuString(Utils.formatString(lines.get(i))));
			}
		}
	}
	public byte[] toBytes()
	{
		byte[] messageBytes = new byte[0];
		for(int i = 0; i<Entries.size(); i++)
		{
			messageBytes = bFM.Utils.mergeArrays(messageBytes,bFM.Utils.mergeArrays(Entries.get(i).toBytes(),(byte)0x00));
		}
		return messageBytes;
	}
	public String toString()
	{
		String ret = "";
		for(int i = 0; i<Entries.size(); i++)
		{
			ret += "<<String " + (i+1) + ">> \"" + Entries.get(i) + "\"\n";
		}
		return ret;
	}
	public boolean equals(String name) 
	{
		throw new UnsupportedOperationException("equals() should not be called on type " + this.getClass());
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
		return Entries;
	}
	public void initializeFromBIT(List<String> lines)
	{
		Entries.removeAll(Entries);
		initializeFromLines(lines);
	}
	public void importFromBIT(List<String> lines)
	{
		initializeFromLines(lines);
	}
	public byte[] toBIT()
	{
		return Utils.encodeStringToBytes(toString());
	}
	public String getBITName()
	{
		return "MenuString.bit";
	}
	public void setData(byte[] data)
	{
		Entries.removeAll(Entries);
		initializeFromBytes(data);
	}
}
