package SystemDataManagers.MenuDB.Unused.Development;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;

import PCKGManager.PCKGManager;
import bFM.OpenedFile;
import bFM.Utils;

public class DevelopmentManager implements OpenedFile
{
	public final static String FileName = "Development.bin";
	ArrayList<Development> Entries = new ArrayList<Development>();
	public DevelopmentManager(byte[] data)
	{
		initializeFromBytes(data);
	}
	private void initializeFromBytes(byte[] data)
	{
		PCKGManager pack = new PCKGManager(data);
		List<String> icons = Utils.extractStringsNoFormatting(pack.getFile("Icon"));
		List<String> images = Utils.extractStringsNoFormatting(pack.getFile("Image"));
		byte[] pos = pack.getFile("Pos");
		byte[] id = pack.getFile("Id");
		int entryCount = icons.size();
		if(entryCount > images.size() || entryCount > id.length/2)
		{
			throw new IllegalArgumentException("Development Pack is incorrectly formatted. \n"
					+ "Icon Count: " + entryCount + "\n"
					+ "Image Count: " + images.size() + "\n"
					+ "Pos Count: " + pos.length/6 + "\n"
					+ "ID Count: " + id.length/2 + "\n");
		}
		else if(entryCount != images.size() || entryCount != pos.length/6 || entryCount != id.length/2)
		{
			System.err.print("Development Pack is incorrectly formatted. \n"
					+ "Icon Count: " + entryCount + "\n"
					+ "Image Count: " + images.size() + "\n"
					+ "Pos Count: " + pos.length/6 + "\n"
					+ "ID Count: " + id.length/2 + "\n");
		}
		for(int i = 0; i < entryCount; i++)
		{
			if(i < pos.length/6)
			{
				Entries.add(new Development(icons.get(i), images.get(i), Utils.getShort(id, i*2), 
						Utils.getShort(pos, i*6), Utils.getShort(pos, i*6 + 2), Utils.getShort(pos, i*6 + 4)));
			}
			else
			{
				Entries.add(new Development(icons.get(i), images.get(i), Utils.getShort(id, i*2), 
						-1, 0, 0));
			}
		}
	}
	public boolean equals(String name) 
	{
		System.err.println("equals() should not be called on type " + this.getClass());
		return name.equals(FileName);
	}
	public void setData(byte[] data) 
	{
		throw new UnsupportedOperationException("setData(byte[] data) should not be called on type " + this.getClass());
	}
	public byte[] toBytes() 
	{
		PCKGManager pack = new PCKGManager(getName());
		byte[] iconBin = new byte[0];
		for(Development entry : Entries)
		{
			iconBin = Utils.mergeArrays(iconBin, Utils.mergeArrays(Utils.encodeStringToBytes(entry.icon), (byte)0x00));
		}
		byte[] imageBin = new byte[0];
		for(Development entry : Entries)
		{
			imageBin = Utils.mergeArrays(imageBin, Utils.mergeArrays(Utils.encodeStringToBytes(entry.image), (byte)0x00));
		}
		byte[] posBin = new byte[0];
		for(Development entry : Entries)
		{
			posBin = Utils.mergeArrays(posBin, entry.getPosBytes());
		}
		byte[] idBin = new byte[0];
		for(Development entry : Entries)
		{
			idBin = Utils.mergeArrays(idBin, ByteBuffer.allocate(2).putShort((short)entry.buildingCode).array());
		}
		pack.addFile("Image", imageBin);
		pack.addFile("Id", idBin);
		pack.addFile("Pos", posBin);
		pack.addFile("Icon", iconBin);
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
	
	public ArrayList<Development> getEntries() 
	{
		return Entries;
	}
	public void replaceFromData(byte[] data)
	{
		Entries.removeAll(Entries);
		initializeFromBytes(data);
	}
}
