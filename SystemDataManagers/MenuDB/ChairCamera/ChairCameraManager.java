package SystemDataManagers.MenuDB.ChairCamera;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import PCKGManager.PCKGManager;
import bFM.OpenedFile;
import bFM.Utils;

public class ChairCameraManager implements OpenedFile
{
	public static final String FileName = "ChairCamera.bin";
	ArrayList<ChairCamera> EntriesLevel1 = new ArrayList<ChairCamera>();
	ArrayList<ChairCamera> EntriesLevel2 = new ArrayList<ChairCamera>();
	ArrayList<ChairCamera> EntriesLevel3 = new ArrayList<ChairCamera>();
	public ChairCameraManager(byte[] data)
	{
		initializeFromBytes(data);
	}
	private void initializeFromBytes(byte[] data)
	{
		PCKGManager pack = new PCKGManager(data);
		byte[] Level1 = pack.getFile("List1");
		byte[] Level2 = pack.getFile("List2");
		byte[] Level3 = pack.getFile("List3");
		
		initializeFromBytes(Level1, EntriesLevel1);
		initializeFromBytes(Level2, EntriesLevel2);
		initializeFromBytes(Level3, EntriesLevel3);
	}
	private void initializeFromBytes(byte[] data, ArrayList<ChairCamera> Entries)
	{
		ByteBuffer dataBuffer = ByteBuffer.wrap(data);
		while(dataBuffer.hasRemaining())
		{
			Entries.add(new ChairCamera(dataBuffer));
		}
	}
	public boolean equals(String name) 
	{
		System.err.println("equals() should not be called on type " + this.getClass());
		return name.equals("PublicArt.bin");
	}
	public void setData(byte[] data) 
	{
		throw new UnsupportedOperationException("setData(byte[] data) should not be called on type " + this.getClass());
	}
	public byte[] toBytes() 
	{
		PCKGManager pack = new PCKGManager(getName());
		byte[] level1 = new byte[0];
		byte[] level2 = new byte[0];
		byte[] level3 = new byte[0];
		for(ChairCamera entry : EntriesLevel1)
		{
			level1 = Utils.mergeArrays(level1, entry.toBytes());
		}
		for(ChairCamera entry : EntriesLevel2)
		{
			level2 = Utils.mergeArrays(level2, entry.toBytes());
		}
		for(ChairCamera entry : EntriesLevel3)
		{
			level3 = Utils.mergeArrays(level3, entry.toBytes());
		}
		pack.addFile("List1", level1);
		pack.addFile("List2", level2);
		pack.addFile("List2", level3);
		return pack.toBytes();
	}
	public void setName(String name) 
	{
		throw new UnsupportedOperationException("setName(String name) should not be called on type " + this.getClass());
	}
	public String getName() 
	{
		return FileName;
	}
	public int getSize() 
	{
		return toBytes().length;
	}
	public ArrayList<ChairCamera> getLevel1Entries() 
	{
		return EntriesLevel1;
	}
	public ArrayList<ChairCamera> getLevel2Entries() 
	{
		return EntriesLevel2;
	}
	public ArrayList<ChairCamera> getLevel3Entries() 
	{
		return EntriesLevel3;
	}
	public void replaceFromData(byte[] data)
	{
		EntriesLevel1.removeAll(EntriesLevel1);
		EntriesLevel2.removeAll(EntriesLevel2);
		EntriesLevel3.removeAll(EntriesLevel3);
		initializeFromBytes(data);
	}
}
