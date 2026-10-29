package SystemDataManagers.MenuDB.Explanation;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;

import CommonFiles.PCKGManager.PCKGManager;
import bFM.IntermediateFileFormat;
import bFM.OpenedFile;
import bFM.Utils;

public class ExplanationManager implements OpenedFile, IntermediateFileFormat
{
	public final static String FileName = "Explanation.bin";
	ArrayList<Explanation> Entries = new ArrayList<Explanation>();
	public ExplanationManager(byte[] data)
	{
		initializeFromBytes(data);
	}
	public ExplanationManager(List<String> lines)
	{
		initializeFromLines(lines);
	}
	private void initializeFromBytes(byte[] data)
	{
		PCKGManager pack = new PCKGManager(data);
		List<String> names = Utils.extractStringsNoFormatting(pack.getFile("ListName"));
		List<String> texts = Utils.extractStringsNoFormatting(pack.getFile("ListText"));
		List<String> images1 = Utils.extractStringsNoFormatting(pack.getFile("ListImage1"));
		List<String> images2 = Utils.extractStringsNoFormatting(pack.getFile("ListImage2"));
		byte[] flags = pack.getFile("Val");
		int entryCount = names.size();
		if(entryCount > texts.size() || entryCount > images1.size() || entryCount > images2.size() || entryCount > flags.length/20)
		{
			throw new IllegalArgumentException("Explanation Pack is incorrectly formatted. \n"
					+ "Name Count: " + entryCount + "\n"
					+ "Text Count: " + texts.size() + "\n"
					+ "Image1 Count: " + images1.size() + "\n"
					+ "Image2 Count: " + images2.size() + "\n"
					+ "Flag Count: " + flags.length/20 + "\n");
		}
		else if(entryCount != texts.size() || entryCount != images1.size() ||entryCount != images2.size() || entryCount != flags.length/20)
		{
			System.err.print("Explanation Pack is incorrectly formatted. \n"
					+ "Name Count: " + entryCount + "\n"
					+ "Text Count: " + texts.size() + "\n"
					+ "Image1 Count: " + images1.size() + "\n"
					+ "Image2 Count: " + images2.size() + "\n"
					+ "Flag Count: " + flags.length/20 + "\n");
		}
		ByteBuffer flagBuffer = ByteBuffer.wrap(flags);
		for(int i = 0; i < entryCount; i++)
		{
			Entries.add(new Explanation(names.get(i), texts.get(i), images1.get(i), images2.get(i), flagBuffer.getInt(i*20), flagBuffer.getInt(i*20 + 4)
					, flagBuffer.getInt(i*20 + 8), flagBuffer.getInt(i*20 + 12), flagBuffer.getInt(i*20 + 16)));
		}
	}
	private void initializeFromLines(List<String> lines)
	{
		Explanation lastEntry = null;
		for(String line : lines)
		{
			if(line.indexOf("<<Explanation Name>>") != -1)
			{
				lastEntry = new Explanation(line);
				Entries.add(lastEntry);
			}
			else if(lastEntry != null) lastEntry.addLine(line);
		}
	}
	public String toString()
	{
		String ret = "Bedrock's Explanation Intermediate File v1.0\n";
		for(Explanation entry : Entries)
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
		for(Explanation entry : Entries)
		{
			nameBin = Utils.mergeArrays(nameBin, Utils.mergeArrays(Utils.encodeStringToBytes(entry.name), (byte)0x00));
		}
		byte[] textBin = new byte[0];
		for(Explanation entry : Entries)
		{
			textBin = Utils.mergeArrays(textBin, Utils.mergeArrays(Utils.encodeStringToBytes(entry.text), (byte)0x00));
		}
		byte[] image1Bin = new byte[0];
		for(Explanation entry : Entries)
		{
			image1Bin = Utils.mergeArrays(image1Bin, Utils.mergeArrays(Utils.encodeStringToBytes(entry.image1), (byte)0x00));
		}
		byte[] image2Bin = new byte[0];
		for(Explanation entry : Entries)
		{
			image2Bin = Utils.mergeArrays(image2Bin, Utils.mergeArrays(Utils.encodeStringToBytes(entry.image2), (byte)0x00));
		}
		byte[] valBin = new byte[0];
		for(Explanation entry : Entries)
		{
			valBin = Utils.mergeArrays(valBin, ByteBuffer.allocate(20).putInt(entry.num1).putInt(entry.num2).putInt(entry.num3).putInt(entry.num4).putInt(entry.num5).array());
		}
		pack.addFile("ListName", nameBin);
		pack.addFile("ListText", textBin);
		pack.addFile("ListImage1", image1Bin);
		pack.addFile("ListImage2", image2Bin);
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
	
	public ArrayList<Explanation> getEntries() 
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
		return "Explanation.bit";
	}
}
