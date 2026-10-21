package SystemDataManagers.MenuDB.Books.AnimalBook;

import java.util.ArrayList;
import java.util.List;

import PCKGManager.PCKGManager;
import bFM.IntermediateFileFormat;
import bFM.OpenedFile;
import bFM.Utils;

public class AnimalBookManager implements OpenedFile, IntermediateFileFormat
{
	ArrayList<AnimalEntry> Entries = new ArrayList<AnimalEntry>();
	public AnimalBookManager(byte[] data)
	{
		initializeFromBytes(data);
	}
	public AnimalBookManager(List<String> lines)
	{
		initializeFromLines(lines);
	}
	private void initializeFromBytes(byte[] data)
	{
		PCKGManager pack = new PCKGManager(data);
		List<String> names = Utils.extractStringsNoFormatting(pack.getFile("Name"));
		List<String> texts = Utils.extractStringsNoFormatting(pack.getFile("Text"));
		List<String> images = Utils.extractStringsNoFormatting(pack.getFile("Image"));
		List<String> details = Utils.extractStringsNoFormatting(pack.getFile("Detail"));
		int entryCount = names.size();
		if(entryCount > texts.size() || entryCount > images.size() || entryCount > details.size())
		{
			throw new IllegalArgumentException("Animal Book Pack is incorrectly formatted. \n"
					+ "Name Count: " + entryCount + "\n"
					+ "Text Count: " + texts.size() + "\n"
					+ "Image Count: " + images.size() + "\n"
					+ "Detail Count: " + details.size() + "\n");
		}
		else if(entryCount != texts.size() || entryCount != images.size() || entryCount != details.size())
		{
			System.err.print("Animal Book Pack is incorrectly formatted. \n"
					+ "Name Count: " + entryCount + "\n"
					+ "Text Count: " + texts.size() + "\n"
					+ "Image Count: " + images.size() + "\n"
					+ "Detail Count: " + details.size() + "\n");
		}
		for(int i = 0; i < entryCount; i++)
		{
			Entries.add(new AnimalEntry(names.get(i), texts.get(i), images.get(i), details.get(i)));
		}
	}
	private void initializeFromLines(List<String> lines)
	{
		AnimalEntry lastEntry = null;
		for(String line : lines)
		{
			if(line.indexOf("<<Animal Name>>") != -1)
			{
				lastEntry = new AnimalEntry(line);
				Entries.add(lastEntry);
			}
			else if(lastEntry != null) lastEntry.addLine(line);
		}
	}
	public String toString()
	{
		String ret = "Bedrock's Animal Book Intermediate File v1.0\n";
		for(AnimalEntry entry : Entries)
		{
			ret += entry.toString();
		}
		return ret;
	}
	public boolean equals(String name) 
	{
		System.err.println("equals() should not be called on type " + this.getClass());
		return name.equals("AnimalBook.bin");
	}
	public void setData(byte[] data) 
	{
		throw new UnsupportedOperationException("setData(byte[] data) should not be called on type " + this.getClass());
	}
	public byte[] toBytes() 
	{
		PCKGManager pack = new PCKGManager(getName());
		byte[] nameBin = new byte[0];
		for(AnimalEntry entry : Entries)
		{
			nameBin = Utils.mergeArrays(nameBin, Utils.mergeArrays(Utils.encodeStringToBytes(entry.name), (byte)0x00));
		}
		byte[] textBin = new byte[0];
		for(AnimalEntry entry : Entries)
		{
			textBin = Utils.mergeArrays(textBin, Utils.mergeArrays(Utils.encodeStringToBytes(entry.text), (byte)0x00));
		}
		byte[] imageBin = new byte[0];
		for(AnimalEntry entry : Entries)
		{
			imageBin = Utils.mergeArrays(imageBin, Utils.mergeArrays(Utils.encodeStringToBytes(entry.image), (byte)0x00));
		}
		byte[] detailBin = new byte[0];
		for(AnimalEntry entry : Entries)
		{
			detailBin = Utils.mergeArrays(detailBin, Utils.mergeArrays(Utils.encodeStringToBytes(entry.debugText), (byte)0x00));
		}
		pack.addFile("Name", nameBin);
		pack.addFile("Text", textBin);
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
		return "AnimalBook.bin";
	}
	public int getSize() 
	{
		return toBytes().length;
	}
	public ArrayList<AnimalEntry> getEntries() 
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
	@Override
	public String getBITName()
	{
		return "AnimalBook.bit";
	}
}
