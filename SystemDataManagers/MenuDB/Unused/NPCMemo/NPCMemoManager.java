package SystemDataManagers.MenuDB.Unused.NPCMemo;

import java.util.List;

import CommonFiles.PCKGManager.PCKGManager;
import bFM.OpenedFile;
import bFM.Utils;

public class NPCMemoManager implements OpenedFile
{
	public final static String FileName = "NPCMemo.bin";
	MemoryMemoManager WomenMemory;
	MemoryMemoManager MenMemory;
	ActionMemoManager WomenAction;
	ActionMemoManager MenAction;
	KingLevelManager KingLevel;
	public NPCMemoManager(byte[] data)
	{
		initializeFromBytes(data);
	}
	public NPCMemoManager(List<String> lines)
	{
		throw new IllegalArgumentException("BIT Format Not Created Yet");
	}
	private void initializeFromBytes(byte[] data)
	{
		PCKGManager pack = new PCKGManager(data);
		WomenMemory = new MemoryMemoManager(pack.getFile("MemoryMemoWoman"));
		MenMemory = new MemoryMemoManager(pack.getFile("MemoryMemoMan"));
		WomenAction = new ActionMemoManager(pack.getFile("ActionMemoWoman"));
		MenAction = new ActionMemoManager(pack.getFile("ActionMemoMan"));
		KingLevel = new KingLevelManager(pack.getFile("Name"), pack.getFile("Text"));
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
		pack.addFile("ActionMemoMan", MenAction.toBytes());
		pack.addFile("ActionMemoWoman", WomenAction.toBytes());
		pack.addFile("MemoryMemoMan", MenMemory.toBytes());
		pack.addFile("MemoryMemoWoman", WomenMemory.toBytes());
		pack.addFile("Name", KingLevel.toNameBytes());
		pack.addFile("Text", KingLevel.toTextBytes());
		return pack.getFile();
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
	public void replaceFromData(byte[] data)
	{
		initializeFromBytes(data);
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
	public MemoryMemoManager getWomanMemoryManager()
	{
		return WomenMemory;
	}
	public MemoryMemoManager getManMemoryManager()
	{
		return MenMemory;
	}
	public ActionMemoManager getWomanActionManager()
	{
		return WomenAction;
	}
	public ActionMemoManager getManActionManager()
	{
		return MenAction;
	}
	public KingLevelManager getLevelManager()
	{
		return KingLevel;
	}
}
