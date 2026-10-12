package SystemDataManagers.MenuDB.Books.MonsterBook;

import java.util.ArrayList;
import java.util.List;

import PCKGManager.PCKGManager;
import bFM.OpenedFile;
import bFM.Utils;

public class MonsterBookManager implements OpenedFile
{
	public final static String FileName = "MonsterBook.bin";
	ArrayList<MovieBook> Entries = new ArrayList<MovieBook>();
	public MonsterBookManager(byte[] data)
	{
		initializeFromBytes(data);
	}
	public MonsterBookManager(List<String> lines)
	{
		initializeFromLines(lines);
	}
	private void initializeFromBytes(byte[] data)
	{
		PCKGManager pack = new PCKGManager(data);
		List<String> string1s = Utils.extractStringsNoFormatting(pack.getFile("String1"));
		List<String> string2s = Utils.extractStringsNoFormatting(pack.getFile("String2"));
		List<String> names = Utils.extractStringsNoFormatting(pack.getFile("Name"));
		List<String> texts = Utils.extractStringsNoFormatting(pack.getFile("Text"));
		List<String> images = Utils.extractStringsNoFormatting(pack.getFile("Image"));
		List<String> vals = Utils.extractStringsNoFormatting(pack.getFile("Val"));
		int entryCount = names.size();
		if(entryCount > texts.size() || entryCount > images.size() || entryCount > string1s.size() || entryCount > string2s.size()  || entryCount > vals.size())
		{
			throw new IllegalArgumentException("Movie Book Pack is incorrectly formatted. \n"
					+ "Name Count: " + entryCount + "\n"
					+ "Text Count: " + texts.size() + "\n"
					+ "Pre Description Count: " + string1s.size() + "\n"
					+ "Post Description Count: " + string2s.size() + "\n"
					+ "Image Count: " + images.size() + "\n"
					+ "Movie File Count: " + vals.size() + "\n");
		}
		else if(entryCount != texts.size() || entryCount != images.size() || entryCount != string1s.size() || entryCount != string2s.size() || entryCount != vals.size())
		{
			System.err.print("Movie Book Pack is incorrectly formatted. \n"
					+ "Name Count: " + entryCount + "\n"
					+ "Text Count: " + texts.size() + "\n"
					+ "Pre Description Count: " + string1s.size() + "\n"
					+ "Post Description Count: " + string2s.size() + "\n"
					+ "Image Count: " + images.size() + "\n"
					+ "Movie File Count: " + vals.size() + "\n");
		}
		for(int i = 0; i < entryCount; i++)
		{
			Entries.add(new MovieBook(names.get(i), string1s.get(i), string2s.get(i), texts.get(i), images.get(i), vals.get(i)));
		}
	}
	private void initializeFromLines(List<String> lines)
	{
		MovieBook lastEntry = null;
		for(String line : lines)
		{
			if(line.indexOf("<<Movie Name>>") != -1)
			{
				lastEntry = new MovieBook(line);
				Entries.add(lastEntry);
			}
			else if(lastEntry != null) lastEntry.addLine(line);
		}
	}
	public String toString()
	{
		String ret = "Bedrock's Movies Book Intermediate File v1.0\n";
		for(MovieBook entry : Entries)
		{
			ret += entry.toString();
		}
		return ret;
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
		byte[] nameBin = new byte[0];
		for(MovieBook entry : Entries)
		{
			nameBin = Utils.mergeArrays(nameBin, Utils.mergeArrays(Utils.encodeStringToBytes(entry.name), (byte)0x00));
		}
		byte[] str1 = new byte[0];
		for(MovieBook entry : Entries)
		{
			str1 = Utils.mergeArrays(str1, Utils.mergeArrays(Utils.encodeStringToBytes(entry.preText), (byte)0x00));
		}
		byte[] str2 = new byte[0];
		for(MovieBook entry : Entries)
		{
			str2 = Utils.mergeArrays(str2, Utils.mergeArrays(Utils.encodeStringToBytes(entry.postText), (byte)0x00));
		}
		byte[] textBin = new byte[0];
		for(MovieBook entry : Entries)
		{
			textBin = Utils.mergeArrays(textBin, Utils.mergeArrays(Utils.encodeStringToBytes(entry.description), (byte)0x00));
		}
		byte[] imageBin = new byte[0];
		for(MovieBook entry : Entries)
		{
			imageBin = Utils.mergeArrays(imageBin, Utils.mergeArrays(Utils.encodeStringToBytes(entry.image), (byte)0x00));
		}
		byte[] valBin = new byte[0];
		for(MovieBook entry : Entries)
		{
			valBin = Utils.mergeArrays(valBin, Utils.mergeArrays(Utils.encodeStringToBytes(entry.movie), (byte)0x00));
		}
		pack.addFile("String1", str1);
		pack.addFile("String2", str2);
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
		return FileName;
	}
	public int getSize() 
	{
		return toBytes().length;
	}
	
	public ArrayList<MovieBook> getEntries() 
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
