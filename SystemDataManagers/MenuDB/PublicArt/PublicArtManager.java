package SystemDataManagers.MenuDB.PublicArt;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;

import PCKGManager.PCKGManager;
import bFM.IntermediateFileFormat;
import bFM.OpenedFile;
import bFM.Utils;

public class PublicArtManager implements OpenedFile, IntermediateFileFormat
{
	public static final String FileName = "PublicArt.bin";
	ArrayList<PublicArt> Entries = new ArrayList<PublicArt>();
	public PublicArtManager(byte[] data)
	{
		initializeFromBytes(data);
	}
	public PublicArtManager(List<String> lines)
	{
		initializeFromLines(lines);
	}
	private void initializeFromBytes(byte[] data)
	{
		PCKGManager pack = new PCKGManager(data);
		List<String> names = Utils.extractStringsNoFormatting(pack.getFile("Name"));
		List<String> texts = Utils.extractStringsNoFormatting(pack.getFile("Text"));
		List<String> descriptions = Utils.extractStringsNoFormatting(pack.getFile("Detail"));
		List<String> images = Utils.extractStringsNoFormatting(pack.getFile("Image"));
		byte[] hummingBookFlags = pack.getFile("Val");
		int entryCount = names.size();
		if(entryCount > texts.size() || entryCount > images.size() || entryCount > hummingBookFlags.length/4)
		{
			throw new IllegalArgumentException("Public Art Pack is incorrectly formatted. \n"
					+ "Name Count: " + entryCount + "\n"
					+ "Text Count: " + texts.size() + "\n"
					+ "Image Count: " + images.size() + "\n"
					+ "Description Count: " + descriptions.size() + "\n"
					+ "Flag Count: " + hummingBookFlags.length/4 + "\n");
		}
		else if(entryCount != texts.size() || entryCount != images.size() || entryCount != hummingBookFlags.length/4)
		{
			System.err.print("Public Art Pack is incorrectly formatted. \n"
					+ "Name Count: " + entryCount + "\n"
					+ "Text Count: " + texts.size() + "\n"
					+ "Image Count: " + images.size() + "\n"
					+ "Description Count: " + descriptions.size() + "\n"
					+ "Flag Count: " + hummingBookFlags.length/4 + "\n");
		}
		ByteBuffer flagBuffer = ByteBuffer.wrap(hummingBookFlags);
		for(int i = 0; i < entryCount; i++)
		{
			Entries.add(new PublicArt(names.get(i), texts.get(i), images.get(i), descriptions.get(i), flagBuffer.getInt(i*4)));
		}
	}
	private void initializeFromLines(List<String> lines)
	{
		PublicArt lastEntry = null;
		for(String line : lines)
		{
			if(line.indexOf("<<Public Art Name>>") != -1)
			{
				lastEntry = new PublicArt(line);
				Entries.add(lastEntry);
			}
			else if(lastEntry != null) lastEntry.addLine(line);
		}
	}
	public String toString()
	{
		String ret = "Bedrock's Public Art Intermediate File v1.0\n";
		for(PublicArt entry : Entries)
		{
			ret += entry.toString();
		}
		return ret;
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
		byte[] nameBin = new byte[0];
		for(PublicArt entry : Entries)
		{
			nameBin = Utils.mergeArrays(nameBin, Utils.mergeArrays(Utils.encodeStringToBytes(entry.name), (byte)0x00));
		}
		byte[] textBin = new byte[0];
		for(PublicArt entry : Entries)
		{
			textBin = Utils.mergeArrays(textBin, Utils.mergeArrays(Utils.encodeStringToBytes(entry.text), (byte)0x00));
		}
		byte[] imageBin = new byte[0];
		for(PublicArt entry : Entries)
		{
			imageBin = Utils.mergeArrays(imageBin, Utils.mergeArrays(Utils.encodeStringToBytes(entry.image), (byte)0x00));
		}
		byte[] valBin = new byte[0];
		for(PublicArt entry : Entries)
		{
			valBin = Utils.mergeArrays(valBin, ByteBuffer.allocate(4).putInt(entry.activationFlag).array());
		}
		byte[] detailBin = new byte[0];
		for(PublicArt entry : Entries)
		{
			detailBin = Utils.mergeArrays(detailBin, Utils.mergeArrays(Utils.encodeStringToBytes(entry.description), (byte)0x00));
		}
		pack.addFile("Name", nameBin);
		pack.addFile("Text", textBin);
		pack.addFile("Val", valBin);
		pack.addFile("Image", imageBin);
		pack.addFile("Detail", detailBin);
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
	
	public ArrayList<PublicArt> getEntries() 
	{
		return Entries;
	}
	public void replaceFromData(byte[] data)
	{
		Entries.removeAll(Entries);
		initializeFromBytes(data);
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
		return "PublicArt.bit";
	}
}
