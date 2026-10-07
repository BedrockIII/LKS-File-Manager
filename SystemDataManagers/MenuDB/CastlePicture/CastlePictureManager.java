package SystemDataManagers.MenuDB.CastlePicture;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;

import PCKGManager.PCKGManager;
import bFM.OpenedFile;
import bFM.Utils;

public class CastlePictureManager implements OpenedFile
{
	ArrayList<CastlePicture> Entries = new ArrayList<CastlePicture>();
	public CastlePictureManager(byte[] data)
	{
		PCKGManager CockpitLog = new PCKGManager(data);
		ArrayList<String> Messages = bFM.Utils.extractStrings(CockpitLog.getFile("Image"));
		ByteBuffer flagBuffer = ByteBuffer.wrap(CockpitLog.getFile("ValueOther"));
		for(int i = 0; i < Messages.size(); i++)
		{
			Entries.add(new CastlePicture(Messages.get(i), flagBuffer.getInt(i*24), flagBuffer.getInt(i*24 + 4), 
					flagBuffer.getInt(i*24 + 8), flagBuffer.getInt(i*24 + 12), flagBuffer.getInt(i*24 + 16), 
					flagBuffer.getInt(i*24 + 24)));
		}
	}
	public CastlePictureManager(List<String> lines)
	{
		CastlePicture lastEntry = null;
		for(int i = 0; i<lines.size(); i++)
		{
			if(lines.get(i).indexOf("<<Castle Picture Image>>")!=-1)
			{
				lastEntry = new CastlePicture(lines.get(i));
				Entries.add(lastEntry);
			}
			else
			{
				if(lastEntry == null)
				{
					throw new IllegalArgumentException("Error Importing from Text. A Flag is Defined Before an Image (line " + i + ")");
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
			valBin = Utils.mergeArrays(valBin, ByteBuffer.allocate(24).putInt(Entries.get(i).flag1).putInt(Entries.get(i).flag2).putInt(Entries.get(i).flag3).putInt(Entries.get(i).flag4).putInt(Entries.get(i).flag5).putInt(Entries.get(i).flag6).array());
		}
		CockpitLog.addFile("Image", messageBytes);
		CockpitLog.addFile("ValueOther", valBin);
		
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
		return toBytes().length;
	}
	public ArrayList<CastlePicture> getEntries()
	{
		return Entries;
	}
	public void setName(String name) 
	{
		throw new UnsupportedOperationException("setName(String name) should not be called on type " + this.getClass());
	}
	public String getName() 
	{
		return "CastlePicture.bin";
	}
	public boolean equals(String name)
	{
		throw new UnsupportedOperationException("equals(String name) should not be called on type " + this.getClass());
	}

}
