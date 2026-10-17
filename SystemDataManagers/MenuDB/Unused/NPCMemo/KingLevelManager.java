package SystemDataManagers.MenuDB.Unused.NPCMemo;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

import bFM.Data;
import bFM.Utils;

public class KingLevelManager implements Data
{
	ArrayList<KingLevel> Entries = new ArrayList<KingLevel>();
	public KingLevelManager(byte[] nameData, byte[] textData)
	{
		ArrayList<String> Names = Utils.extractStrings(nameData);
		ArrayList<String> Descriptions = Utils.extractStrings(textData);
		for(int i = 0; i < Names.size(); i++)
		{
			Entries.add(new KingLevel(Names.get(i), Descriptions.get(i)));
		}
	}
	public KingLevelManager(List<String> lines)
	{
		KingLevel lastLevel = null;
		for(int i = 0; i<lines.size(); i++)
		{
			if(lines.get(i).indexOf("<<King Level>>")!=-1)
			{
				lastLevel = new KingLevel(lines.get(i));
				Entries.add(lastLevel);
			}
			else if(lines.get(i).indexOf("<<King Level")!=-1)
			{
				if(lastLevel == null)
				{
					throw new IllegalArgumentException("Error Importing from Text. A Level Description is Defined Before a Level (line " + i + ")");
				}
				else
				{
					lastLevel.addLine(lines.get(i));
				}
			}
		}
	}
	public byte[] toBytes() 
	{
		throw new UnsupportedOperationException("toBytes() should not be called on type " + this.getClass());
	}
	public String toString()
	{
		String ret = "";
		for(int i = 0; i<Entries.size(); i++)
		{
			ret += Entries.get(i).toBit();
		}
		return ret;
	}
	public void setData(byte[] data) 
	{
		throw new UnsupportedOperationException("setData(byte[] data) should not be called on type " + this.getClass());
	}
	public int getSize() 
	{
		return toBytes().length;
	}
	public ArrayList<KingLevel> getEntries()
	{
		return Entries;
	}
	public byte[] toNameBytes()
	{
		byte[] ret = new byte[0];
		for(int i = 0; i<Entries.size(); i++)
		{
			try
			{
				ret = Utils.mergeArrays(ret, Utils.mergeArrays(Utils.encodeStringToBytes(Entries.get(i).name, Charset.forName("Shift-JIS")), (byte)0x00));
			} catch (Exception e)
			{
				e.printStackTrace();
			}
		}
		return ret;
	}
	public byte[] toTextBytes()
	{
		byte[] ret = new byte[0];
		for(int i = 0; i<Entries.size(); i++)
		{
			try
			{
				ret = Utils.mergeArrays(ret, Utils.mergeArrays(Utils.encodeStringToBytes(Utils.formatStringChars(Entries.get(i).text), Charset.forName("Shift-JIS")), (byte)0x00));
			} catch (Exception e)
			{
				e.printStackTrace();
			}
		}
		return ret;
	}
}
