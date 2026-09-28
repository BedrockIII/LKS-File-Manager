package SystemDataManagers.MenuDB.Books;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;

import PCKGManager.PCKGManager;
import bFM.OpenedFile;
import bFM.Utils;

public class HummingBookManager implements OpenedFile
{
	ArrayList<HummingBookEntry> Entries = new ArrayList<HummingBookEntry>();
	public HummingBookManager(byte[] data)
	{
		initializeFromBytes(data);
	}
	public HummingBookManager(List<String> lines)
	{
		initializeFromLines(lines);
	}
	private void initializeFromBytes(byte[] data)
	{
		PCKGManager pack = new PCKGManager(data);
		List<String> names = Utils.extractStringsNoFormatting(pack.getFile("Name"));
		List<String> texts = Utils.extractStringsNoFormatting(pack.getFile("Text"));
		List<String> images = Utils.extractStringsNoFormatting(pack.getFile("Image"));
		byte[] hummingBookFlags = pack.getFile("Val");
		int entryCount = names.size();
		if(entryCount > texts.size() || entryCount > images.size() || entryCount > hummingBookFlags.length/4)
		{
			throw new IllegalArgumentException("Humming Book Pack is incorrectly formatted. \n"
					+ "Name Count: " + entryCount + "\n"
					+ "Text Count: " + texts.size() + "\n"
					+ "Image Count: " + images.size() + "\n"
					+ "Flag Count: " + hummingBookFlags.length/4 + "\n");
		}
		else if(entryCount != texts.size() || entryCount != images.size() || entryCount != hummingBookFlags.length/4)
		{
			System.err.print("Humming Book Pack is incorrectly formatted. \n"
					+ "Name Count: " + entryCount + "\n"
					+ "Text Count: " + texts.size() + "\n"
					+ "Image Count: " + images.size() + "\n"
					+ "Flag Count: " + hummingBookFlags.length/4 + "\n");
		}
		ByteBuffer flagBuffer = ByteBuffer.wrap(hummingBookFlags);
		for(int i = 0; i < entryCount; i++)
		{
			Entries.add(new HummingBookEntry(names.get(i), texts.get(i), images.get(i), flagBuffer.getInt(i*4)));
		}
	}
	private void initializeFromLines(List<String> lines)
	{
		HummingBookEntry lastEntry = null;
		for(String line : lines)
		{
			if(line.indexOf("<<Humming Entry Name>>") != -1)
			{
				lastEntry = new HummingBookEntry(line);
				Entries.add(lastEntry);
			}
			else if(lastEntry != null) lastEntry.addLine(line);
		}
	}
	public String toString()
	{
		String ret = "Bedrock's Humming Book Intermediate File v1.0\n";
		for(HummingBookEntry entry : Entries)
		{
			ret += entry.toString();
		}
		return ret;
	}
	public boolean equals(String name) 
	{
		System.err.println("equals() should not be called on type " + this.getClass());
		return name.equals("Humming.bin");
	}
	public void setData(byte[] data) 
	{
		throw new UnsupportedOperationException("setData(byte[] data) should not be called on type " + this.getClass());
	}
	public byte[] toBytes() 
	{
		PCKGManager pack = new PCKGManager(getName());
		byte[] nameBin = new byte[0];
		for(HummingBookEntry entry : Entries)
		{
			nameBin = Utils.mergeArrays(nameBin, Utils.mergeArrays(Utils.encodeStringToBytes(entry.name), (byte)0x00));
		}
		byte[] textBin = new byte[0];
		for(HummingBookEntry entry : Entries)
		{
			textBin = Utils.mergeArrays(textBin, Utils.mergeArrays(Utils.encodeStringToBytes(entry.text), (byte)0x00));
		}
		byte[] imageBin = new byte[0];
		for(HummingBookEntry entry : Entries)
		{
			imageBin = Utils.mergeArrays(imageBin, Utils.mergeArrays(Utils.encodeStringToBytes(entry.image), (byte)0x00));
		}
		byte[] valBin = new byte[0];
		for(HummingBookEntry entry : Entries)
		{
			valBin = Utils.mergeArrays(valBin, ByteBuffer.allocate(4).putInt(entry.hummingBookFlag).array());
		}
		pack.addFile("Name", nameBin);
		pack.addFile("Text", textBin);
		pack.addFile("Image", imageBin);
		pack.addFile("Val", valBin);
		return pack.toBytes();
	}
	public void setName(String name) 
	{
		throw new UnsupportedOperationException("setName(String name) should not be called on type " + this.getClass());
	}
	public String getName() 
	{
		return "Humming.bin";
	}
	public int getSize() 
	{
		return toBytes().length;
	}
	
	public ArrayList<HummingBookEntry> getEntries() 
	{
		return Entries;
	}
	public void replaceFromData(byte[] data)
	{
		Entries.removeAll(Entries);
		initializeFromBytes(data);
	}
	public void replaceFromIntermediateText(byte[] data)
	{
		List<String> lines = Utils.bytesToStrs(data);
		Entries.removeAll(Entries);
		initializeFromLines(lines);
	}
	public void importFromIntermediateText(byte[] data)
	{
		List<String> lines = Utils.bytesToStrs(data);
		initializeFromLines(lines);
	}
	public byte[] toIntermediateText()
	{
		return Utils.encodeStringToBytes(toString());
	}
}
