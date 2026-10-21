package SystemDataManagers.MenuDB.Unused.NPCMemo;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

import bFM.Data;
import bFM.Utils;

public class ActionMemoManager implements Data
{
	ArrayList<ActionMemo> Entries = new ArrayList<ActionMemo>();
	public ActionMemoManager(byte[] data)
	{
		ArrayList<String> Messages = Utils.extractStrings(data);
		for(int i = 0; i < Messages.size(); i++)
		{
			Entries.add(new ActionMemo(Messages.get(i)));
		}
	}
	public ActionMemoManager(List<String> lines)
	{
		for(int i = 0; i<lines.size(); i++)
		{
			if(lines.get(i).indexOf("<<Action Memo>>")!=-1)
			{
				Entries.add(new ActionMemo(lines.get(i)));
			}
		}
	}
	public byte[] toBytes()
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
	public ArrayList<ActionMemo> getEntries()
	{
		return Entries;
	}

}
