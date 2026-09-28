package SystemDataManagers.MenuDB;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;

import PCKGManager.PCKGManager;
import bFM.OpenedFile;
import bFM.Utils;

public class CockpitLogManager implements OpenedFile
{
	ArrayList<LogEntry> Entries = new ArrayList<LogEntry>();
	public CockpitLogManager(byte[] data)
	{
		PCKGManager CockpitLog = new PCKGManager(data);
		ArrayList<String> Messages = bFM.Utils.extractStrings(CockpitLog.getFile("CockpitLog"));
		ByteBuffer flagBuffer = ByteBuffer.wrap(CockpitLog.getFile("Val"));
		for(int i = 0; i < Messages.size(); i++)
		{
			Entries.add(new LogEntry(Messages.get(i), flagBuffer.getInt(i*4)));
		}
	}
	public CockpitLogManager(List<String> lines)
	{
		LogEntry lastEntry = null;
		for(int i = 0; i<lines.size(); i++)
		{
			if(lines.get(i).indexOf("<<Log Entry>>")!=-1)
			{
				lastEntry = new LogEntry(lines.get(i));
				Entries.add(lastEntry);
			}
			if(lines.get(i).indexOf("<<Flag>>")!=-1)
			{
				if(lastEntry == null)
				{
					throw new IllegalArgumentException("Error Importing from Text. A Flag is Defined Before a Log (line " + i + ")");
				}
				else
				{
					lastEntry.addLine(lines.get(i));
				}
			}
		}
	}
	public byte[] toBytes()
	{
		PCKGManager CockpitLog = new PCKGManager();
		byte[] messageBytes = new byte[0];
		for(int i = 0; i<Entries.size(); i++)
		{
			messageBytes = Utils.mergeArrays(messageBytes, Utils.mergeArrays(Utils.encodeStringToBytes(Entries.get(i).text), (byte)0x00));
		}
		byte[] valBin = new byte[0];
		for(int i = 0; i<Entries.size(); i++)
		{
			valBin = Utils.mergeArrays(valBin, ByteBuffer.allocate(4).putInt(Entries.get(i).flag).array());
		}
		CockpitLog.addFile("Val", valBin);
		CockpitLog.addFile("CockpitLog", messageBytes);
		return CockpitLog.getFile();
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
		throw new UnsupportedOperationException("getSize() should not be called on type " + this.getClass());
	}
	public ArrayList<LogEntry> getEntries()
	{
		return Entries;
	}
	public void setName(String name) 
	{
		throw new UnsupportedOperationException("setName(String name) should not be called on type " + this.getClass());
	}
	public String getName() 
	{
		return "CockpitLog.bin";
	}
	public boolean equals(String name)
	{
		throw new UnsupportedOperationException("equals(String name) should not be called on type " + this.getClass());
	}

}
