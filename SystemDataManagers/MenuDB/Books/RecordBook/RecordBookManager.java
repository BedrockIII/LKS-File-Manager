package SystemDataManagers.MenuDB.Books.RecordBook;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;

import PCKGManager.PCKGManager;
import bFM.OpenedFile;
import bFM.Utils;

public class RecordBookManager implements OpenedFile
{
	ArrayList<RecordBookEntry> Entries = new ArrayList<RecordBookEntry>();
	public RecordBookManager(byte[] data)
	{
		initializeFromBytes(data);
	}
	public RecordBookManager(List<String> lines)
	{
		initializeFromLines(lines);
	}
	private void initializeFromBytes(byte[] data)
	{
		PCKGManager pack = new PCKGManager(data);
		List<String> names = Utils.extractStringsNoFormatting(pack.getFile("Name"));
		List<String> level0Texts = Utils.extractStringsNoFormatting(pack.getFile("Text"));
		List<String> level1Texts = Utils.extractStringsNoFormatting(pack.getFile("Val"));
		List<String> level2Texts = Utils.extractStringsNoFormatting(pack.getFile("String1"));
		List<String> level3Texts = Utils.extractStringsNoFormatting(pack.getFile("String2"));
		List<String> images = Utils.extractStringsNoFormatting(pack.getFile("Image"));
		byte[] flags = pack.getFile("num1");
		int entryCount = names.size();
		if(entryCount > level0Texts.size() || entryCount > level1Texts.size() || entryCount > level2Texts.size() 
				|| entryCount > level3Texts.size() || entryCount > images.size() || entryCount > flags.length/12)
		{
			throw new IllegalArgumentException("Record Book Pack is incorrectly formatted. \n"
					+ "Name Count: " + entryCount + "\n"
					+ "Level 0  Page Count: " + level0Texts.size() + "\n"
					+ "Level 1  Page Count: " + level1Texts.size() + "\n"
					+ "Level 2  Page Count: " + level2Texts.size() + "\n"
					+ "Level 3  Page Count: " + level3Texts.size() + "\n"
					+ "Image Count: " + images.size() + "\n"
					+ "Flag Count: " + flags.length/12 + "\n");
		}
		else if(entryCount != level0Texts.size() || entryCount != level1Texts.size() || entryCount != level2Texts.size() || 
				entryCount != level3Texts.size() || entryCount != images.size() || entryCount != flags.length/12)
		{
			System.err.print("Record Book Pack is incorrectly formatted. \n"
					+ "Name Count: " + entryCount + "\n"
					+ "Level 0  Page Count: " + level0Texts.size() + "\n"
					+ "Level 1  Page Count: " + level1Texts.size() + "\n"
					+ "Level 2  Page Count: " + level2Texts.size() + "\n"
					+ "Level 3  Page Count: " + level3Texts.size() + "\n"
					+ "Image Count: " + images.size() + "\n"
					+ "Flag Count: " + flags.length/12 + "\n");
		}
		ByteBuffer flagBuffer = ByteBuffer.wrap(flags);
		for(int i = 0; i < entryCount; i++)
		{
			Entries.add(new RecordBookEntry(names.get(i), level0Texts.get(i),
					level1Texts.get(i), level2Texts.get(i), level3Texts.get(i),
					images.get(i), flagBuffer.getInt(i*12), flagBuffer.getInt(i*12+4), 
					flagBuffer.getInt(i*12+8)));
		}
	}
	private void initializeFromLines(List<String> lines)
	{
		RecordBookEntry lastEntry = null;
		for(String line : lines)
		{
			if(line.indexOf("<<Humming Entry Name>>") != -1)
			{
				lastEntry = new RecordBookEntry(line);
				Entries.add(lastEntry);
			}
			else if(lastEntry != null) lastEntry.addLine(line);
		}
	}
	public String toString()
	{
		String ret = "Bedrock's Humming Book Intermediate File v1.0\n";
		for(RecordBookEntry entry : Entries)
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
		for(RecordBookEntry entry : Entries)
		{
			nameBin = Utils.mergeArrays(nameBin, Utils.mergeArrays(Utils.encodeStringToBytes(entry.name), (byte)0x00));
		}
		byte[] text0Bin = new byte[0];
		for(RecordBookEntry entry : Entries)
		{
			text0Bin = Utils.mergeArrays(text0Bin, Utils.mergeArrays(Utils.encodeStringToBytes(entry.level0), (byte)0x00));
		}
		byte[] text1Bin = new byte[0];
		for(RecordBookEntry entry : Entries)
		{
			text1Bin = Utils.mergeArrays(text1Bin, Utils.mergeArrays(Utils.encodeStringToBytes(entry.level1), (byte)0x00));
		}
		byte[] text2Bin = new byte[0];
		for(RecordBookEntry entry : Entries)
		{
			text2Bin = Utils.mergeArrays(text2Bin, Utils.mergeArrays(Utils.encodeStringToBytes(entry.level2), (byte)0x00));
		}
		byte[] text3Bin = new byte[0];
		for(RecordBookEntry entry : Entries)
		{
			text3Bin = Utils.mergeArrays(text3Bin, Utils.mergeArrays(Utils.encodeStringToBytes(entry.level3), (byte)0x00));
		}
		byte[] imageBin = new byte[0];
		for(RecordBookEntry entry : Entries)
		{
			imageBin = Utils.mergeArrays(imageBin, Utils.mergeArrays(Utils.encodeStringToBytes(entry.image), (byte)0x00));
		}
		byte[] valBin = new byte[0];
		for(RecordBookEntry entry : Entries)
		{
			valBin = Utils.mergeArrays(valBin, ByteBuffer.allocate(12).putInt(entry.level1Count).putInt(entry.level2Count).putInt(entry.level3Count).array());
		}
		pack.addFile("Name", nameBin);
		pack.addFile("Text", text0Bin);
		pack.addFile("Val", text1Bin);
		pack.addFile("String1", text2Bin);
		pack.addFile("String2", text3Bin);
		pack.addFile("num1", valBin);
		pack.addFile("Image", imageBin);
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
	
	public ArrayList<RecordBookEntry> getEntries() 
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
