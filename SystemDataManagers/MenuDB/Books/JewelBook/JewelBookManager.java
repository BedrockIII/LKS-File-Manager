package SystemDataManagers.MenuDB.Books.JewelBook;

import java.util.ArrayList;
import java.util.List;

import PCKGManager.PCKGManager;
import bFM.OpenedFile;
import bFM.Utils;

public class JewelBookManager implements OpenedFile
{
	ArrayList<JewelEntry> Entries = new ArrayList<JewelEntry>();
	public JewelBookManager(byte[] data)
	{
		initializeFromBytes(data);
	}
	public JewelBookManager(List<String> lines)
	{
		initializeFromLines(lines);
	}
	private void initializeFromBytes(byte[] data)
	{
		PCKGManager pack = new PCKGManager(data);
		List<String> names = Utils.extractStringsNoFormatting(pack.getFile("Name"));
		List<String> texts = Utils.extractStringsNoFormatting(pack.getFile("Text"));
		List<String> images = Utils.extractStringsNoFormatting(pack.getFile("Image"));
		List<String> details = Utils.extractStringsNoFormatting(pack.getFile("GourmetImage"));
		int entryCount = names.size();
		if(entryCount > texts.size() || entryCount > images.size() || entryCount > details.size())
		{
			throw new IllegalArgumentException("Jewel Book Pack is incorrectly formatted. \n"
					+ "Name Count: " + entryCount + "\n"
					+ "Text Count: " + texts.size() + "\n"
					+ "Image Count: " + images.size() + "\n"
					+ "Gourmet Image Count: " + details.size() + "\n");
		}
		else if(entryCount != texts.size() || entryCount != images.size() || entryCount != details.size())
		{
			System.err.print("Jewel Book Pack is incorrectly formatted. \n"
					+ "Name Count: " + entryCount + "\n"
					+ "Text Count: " + texts.size() + "\n"
					+ "Image Count: " + images.size() + "\n"
					+ "Gourmet Image Count: " + details.size() + "\n");
		}
		for(int i = 0; i < entryCount; i++)
		{
			Entries.add(new JewelEntry(names.get(i), texts.get(i), images.get(i), details.get(i)));
		}
	}
	private void initializeFromLines(List<String> lines)
	{
		JewelEntry lastEntry = null;
		for(String line : lines)
		{
			if(line.indexOf("<<Jewel Name>>") != -1)
			{
				lastEntry = new JewelEntry(line);
				Entries.add(lastEntry);
			}
			else if(lastEntry != null) lastEntry.addLine(line);
		}
	}
	public String toString()
	{
		String ret = "Bedrock's Jewel Book Intermediate File v1.0\n";
		for(JewelEntry entry : Entries)
		{
			ret += entry.toString();
		}
		return ret;
	}
	public boolean equals(String name) 
	{
		System.err.println("equals() should not be called on type " + this.getClass());
		return name.equals("Jewel.bin");
	}
	public void setData(byte[] data) 
	{
		throw new UnsupportedOperationException("setData(byte[] data) should not be called on type " + this.getClass());
	}
	public byte[] toBytes() 
	{
		PCKGManager pack = new PCKGManager(getName());
		byte[] nameBin = new byte[0];
		for(JewelEntry entry : Entries)
		{
			nameBin = Utils.mergeArrays(nameBin, Utils.mergeArrays(Utils.encodeStringToBytes(entry.name), (byte)0x00));
		}
		byte[] textBin = new byte[0];
		for(JewelEntry entry : Entries)
		{
			textBin = Utils.mergeArrays(textBin, Utils.mergeArrays(Utils.encodeStringToBytes(entry.text), (byte)0x00));
		}
		byte[] imageBin = new byte[0];
		for(JewelEntry entry : Entries)
		{
			imageBin = Utils.mergeArrays(imageBin, Utils.mergeArrays(Utils.encodeStringToBytes(entry.image), (byte)0x00));
		}
		byte[] detailBin = new byte[0];
		for(JewelEntry entry : Entries)
		{
			detailBin = Utils.mergeArrays(detailBin, Utils.mergeArrays(Utils.encodeStringToBytes(entry.debugText), (byte)0x00));
		}
		pack.addFile("Name", nameBin);
		pack.addFile("Text", textBin);
		pack.addFile("Image", imageBin);
		pack.addFile("GourmetImage", detailBin);
		return pack.toBytes();
	}
	public void setName(String name) 
	{
		throw new UnsupportedOperationException("setName(String name) should not be called on type " + this.getClass());
	}
	public String getName() 
	{
		return "Jewel.bin";
	}
	public int getSize() 
	{
		return toBytes().length;
	}
	public ArrayList<JewelEntry> getEntries() 
	{
		return Entries;
	}
	public void replaceFromData(byte[] data)
	{
		Entries.removeAll(Entries);
		initializeFromBytes(data);
	}
	public void replaceFromBJB(byte[] data)
	{
		List<String> lines = Utils.bytesToStrs(data);
		Entries.removeAll(Entries);
		initializeFromLines(lines);
	}
	public void importFromBJB(byte[] data)
	{
		List<String> lines = Utils.bytesToStrs(data);
		initializeFromLines(lines);
	}
	public byte[] toBAB()
	{
		return Utils.encodeStringToBytes(toString());
	}
}
