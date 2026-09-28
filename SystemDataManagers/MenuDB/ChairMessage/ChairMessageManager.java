package SystemDataManagers.MenuDB.ChairMessage;

import java.util.ArrayList;
import java.util.List;

import PCKGManager.PCKGManager;
import bFM.OpenedFile;
import bFM.Utils;

public class ChairMessageManager implements OpenedFile
{
	ArrayList<ChairMessage> VerdeEntries = new ArrayList<ChairMessage>();
	ArrayList<ChairMessage> HowserEntries = new ArrayList<ChairMessage>();
	ArrayList<ChairMessage> LiamEntries = new ArrayList<ChairMessage>();
	public ChairMessageManager(byte[] data)
	{
		initializeFromBytes(data);
	}
	public ChairMessageManager(List<String> lines)
	{
		initializeFromLines(lines);
	}
	private void initializeFromBytes(byte[] data)
	{
		PCKGManager pack = new PCKGManager(data);
		List<String> verdeMessages = Utils.extractStringsNoFormatting(pack.getFile("Verude"));
		List<String> howserMessages = Utils.extractStringsNoFormatting(pack.getFile("Hauzar"));
		List<String> liamMessages = Utils.extractStringsNoFormatting(pack.getFile("Riamu"));
		byte[] verdeFlags = pack.getFile("VerudeFlag");
		byte[] howserFlags = pack.getFile("HauzarFlag");
		byte[] liamFlags = pack.getFile("RiamuFlag");
		initializeFromBytes(VerdeEntries, verdeFlags, verdeMessages);
		initializeFromBytes(HowserEntries, howserFlags, howserMessages);
		initializeFromBytes(LiamEntries, liamFlags, liamMessages);
	}
	private void initializeFromBytes(ArrayList<ChairMessage> Entries, byte[] flagData, List<String> messageData)
	{
		
	}
	private void initializeFromLines(List<String> lines)
	{
		//TODO
	}
	public String toString()
	{
		//TODO
		return "Error Message";
	}
	public void setData(byte[] data) 
	{
		throw new UnsupportedOperationException("setData(byte[] data) should not be called on type " + this.getClass());
	}
	public byte[] toBytes() 
	{
		PCKGManager pack = new PCKGManager(getName());
		//TODO
		return null;
	}
	public void setName(String name) 
	{
		throw new UnsupportedOperationException("setName(String name) should not be called on type " + this.getClass());
	}
	public String getName() 
	{
		return "ChairMessage.bin";
	}
	public int getSize() 
	{
		return toBytes().length;
	}
	public ArrayList<ChairMessage> getVerdeEntries() 
	{
		return VerdeEntries;
	}
	public ArrayList<ChairMessage> getHowserEntries() 
	{
		return HowserEntries;
	}
	public ArrayList<ChairMessage> getLiamEntries() 
	{
		return LiamEntries;
	}
	public void replaceFromData(byte[] data)
	{
		VerdeEntries.removeAll(VerdeEntries);
		HowserEntries.removeAll(HowserEntries);
		LiamEntries.removeAll(LiamEntries);
		initializeFromBytes(data);
	}
	public void replaceFromIntermediateText(byte[] data)
	{
		List<String> lines = Utils.bytesToStrs(data);
		VerdeEntries.removeAll(VerdeEntries);
		HowserEntries.removeAll(HowserEntries);
		LiamEntries.removeAll(LiamEntries);
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
	@Override
	public boolean equals(String name)
	{
		throw new UnsupportedOperationException("equals(String name) should not be called on type " + this.getClass());
	}
}
