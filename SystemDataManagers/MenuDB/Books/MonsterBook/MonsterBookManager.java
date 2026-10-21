package SystemDataManagers.MenuDB.Books.MonsterBook;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;

import PCKGManager.PCKGManager;
import bFM.OpenedFile;
import bFM.Utils;

public class MonsterBookManager implements OpenedFile
{
	public final static String FileName = "MonsterBook.bin";
	ArrayList<MonsterMod> HpEntries = new ArrayList<MonsterMod>();
	ArrayList<MonsterBook> BookEntries = new ArrayList<MonsterBook>();
	ArrayList<MonsterLog> LogEntries = new ArrayList<MonsterLog>();
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
		List<String> detail = Utils.extractStringsNoFormatting(pack.getFile("Detail"));
		List<String> string1s = Utils.extractStringsNoFormatting(pack.getFile("LogString1"));
		List<String> names = Utils.extractStringsNoFormatting(pack.getFile("MonsterName"));
		List<String> texts = Utils.extractStringsNoFormatting(pack.getFile("MonsterText"));
		List<String> images = Utils.extractStringsNoFormatting(pack.getFile("MonsterImage"));
		byte[] hps = pack.getFile("MonsterHp");
		byte[] vals = pack.getFile("LogVal");
		int entryCount = detail.size();
		if(entryCount > texts.size() || entryCount > images.size() || entryCount > string1s.size())
		{
			throw new IllegalArgumentException("Monster Book Pack is incorrectly formatted. \n"
					+ "Name Count: " + entryCount + "\n"
					+ "Description Count: " + texts.size() + "\n"
					+ "Image Count: " + images.size() + "\n");
		}
		else if(entryCount != texts.size() || entryCount != images.size() || entryCount != string1s.size())
		{
			System.err.print("Monster Book Pack is incorrectly formatted. \n"
					+ "Name Count: " + entryCount + "\n"
					+ "Description Count: " + texts.size() + "\n"
					+ "Image Count: " + images.size() + "\n");
		}
		for(int i = 0; i < entryCount; i++)
		{
			BookEntries.add(new MonsterBook(detail.get(i), texts.get(i), images.get(i)));
		}
		entryCount = names.size();
		if(entryCount > hps.length/4)
		{
			throw new IllegalArgumentException("Monster Book Pack is incorrectly formatted. \n"
					+ "Name Count: " + entryCount + "\n"
					+ "Value Count: " + hps.length/4 + "\n");
		}
		else if(entryCount != hps.length/4)
		{
			System.err.print("Monster Book Pack is incorrectly formatted. \n"
					+ "Name Count: " + entryCount + "\n"
					+ "Value Count: " + hps.length/4 + "\n");
		}
		ByteBuffer hpData = ByteBuffer.wrap(hps);
		for(int i = 0; i < names.size(); i++)
		{
			HpEntries.add(new MonsterMod(names.get(i), hpData.getInt()));
		}
		entryCount = string1s.size();
		ByteBuffer valData = ByteBuffer.wrap(vals);
		if(entryCount != vals.length/4)
		{
			System.err.print("Monster Book Pack is incorrectly formatted. \n"
					+ "Log String Count: " + entryCount + "\n"
					+ "Val Count: " + vals.length/4 + "\n");
		}
		for(int i = 0; i < entryCount; i++)
		{
			if(i>=vals.length/4) //Do nada
			{
				
			}
			else LogEntries.add(new MonsterLog(string1s.get(i), valData.getInt(i*4)));
		}
	}
	private void initializeFromLines(List<String> lines)
	{
		MonsterMod lastModEntry = null;
		MonsterBook lastBookEntry = null;
		MonsterLog lastLogEntry = null;
		for(String line : lines)
		{
			if(line.indexOf("<<Book Entry Name>>") != -1)
			{
				lastBookEntry = new MonsterBook(line);
				BookEntries.add(lastBookEntry);
			}
			else if(lastBookEntry != null && line.indexOf("<<Book") != -1) lastBookEntry.addLine(line);
			else if(line.indexOf("<<Monster Log Text>>") != -1)
			{
				lastLogEntry = new MonsterLog(line);
				LogEntries.add(lastLogEntry);
			}
			else if(lastLogEntry != null && line.indexOf("<<Log") != -1) lastLogEntry.addLine(line);
			if(line.indexOf("<<Monster Mod Name>>") != -1)
			{
				lastModEntry = new MonsterMod(line);
				HpEntries.add(lastModEntry);
			}
			else if(lastModEntry != null && line.indexOf("<<Mod") != -1) lastModEntry.addLine(line);
		}
	}
	public String toString()
	{
		String ret = "Bedrock's Monster Book Intermediate File v1.0\n";
		for(MonsterBook entry : BookEntries)
		{
			ret += entry.toString();
		}
		for(MonsterLog entry : LogEntries)
		{
			ret += entry.toString();
		}
		for(MonsterMod entry : HpEntries)
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
		byte[] LogStringBin = new byte[0];
		for(MonsterLog entry : LogEntries)
		{
			LogStringBin = Utils.mergeArrays(LogStringBin, Utils.mergeArrays(Utils.encodeStringToBytes(entry.name), (byte)0x00));
		}
		LogStringBin = Utils.mergeArrays(LogStringBin, (byte)0x00);
		byte[] LogValBin = new byte[0];
		for(MonsterLog entry : LogEntries)
		{
			LogValBin = Utils.mergeArrays(LogValBin, ByteBuffer.allocate(4).putInt(entry.val).array());
		}
		byte[] MonsterNameBin = new byte[0];
		for(MonsterMod entry : HpEntries)
		{
			MonsterNameBin = Utils.mergeArrays(MonsterNameBin, Utils.mergeArrays(Utils.encodeStringToBytes(entry.name), (byte)0x00));
		}
		byte[] MonsterTextBin = new byte[0];
		for(MonsterBook entry : BookEntries)
		{
			MonsterTextBin = Utils.mergeArrays(MonsterTextBin, Utils.mergeArrays(Utils.encodeStringToBytes(entry.description), (byte)0x00));
		}
		ByteBuffer MonsterHPBin = ByteBuffer.allocate(HpEntries.size() * 4);
		for(MonsterMod entry : HpEntries)
		{
			MonsterHPBin.putInt(entry.val);
		}
		byte[] MonsterImageBin = new byte[0];
		for(MonsterBook entry : BookEntries)
		{
			MonsterImageBin = Utils.mergeArrays(MonsterImageBin, Utils.mergeArrays(Utils.encodeStringToBytes(entry.image), (byte)0x00));
		}
		byte[] DetailBin = new byte[0];
		for(MonsterBook entry : BookEntries)
		{
			DetailBin = Utils.mergeArrays(DetailBin, Utils.mergeArrays(Utils.encodeStringToBytes(entry.name), (byte)0x00));
		}
		pack.addFile("LogString1", LogStringBin);
		pack.addFile("LogVal", LogValBin);
		pack.addFile("MonsterName", MonsterNameBin);
		pack.addFile("MonsterText", MonsterTextBin);
		pack.addFile("MonsterHp", MonsterHPBin.array());
		pack.addFile("MonsterImage", MonsterImageBin);
		pack.addFile("Detail", DetailBin);
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
	public ArrayList<MonsterLog> getLogEntries() 
	{
		return LogEntries;
	}
	public ArrayList<MonsterMod> getHpEntries() 
	{
		return HpEntries;
	}
	public ArrayList<MonsterBook> getBookEntries() 
	{
		return BookEntries;
	}
	public void replaceFromData(byte[] data)
	{
		HpEntries.removeAll(HpEntries);
		BookEntries.removeAll(BookEntries);
		LogEntries.removeAll(LogEntries);
		initializeFromBytes(data);
	}
	public void replaceFromIntermediateText(byte[] data)
	{
		List<String> lines = Utils.bytesToStrs(data);
		HpEntries.removeAll(HpEntries);
		BookEntries.removeAll(BookEntries);
		LogEntries.removeAll(LogEntries);
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
