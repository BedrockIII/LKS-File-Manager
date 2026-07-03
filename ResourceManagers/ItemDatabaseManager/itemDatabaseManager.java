package ResourceManagers.ItemDatabaseManager;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import PCKGManager.PCKGManager;
import bFM.OpenedFile;
import bFM.Utils;

public class itemDatabaseManager implements OpenedFile
{
	private ArrayList<Item> items = new ArrayList<Item>();
	String name = "itemDB3_1.pac";
	public itemDatabaseManager(byte[] data)
	{
		initializeFromBytes(data);
	}
	public itemDatabaseManager(String name, byte[] data)
	{
		this.name = name;
		initializeFromBytes(data);
	}
	private void initializeFromBytes(byte[] data)
	{
		//Initializes an Item Manager using the raw, encrypted, byte code
		PCKGManager itemDB = new PCKGManager(data);
		byte[] itemBin = itemDB.getFile("item1.bin"); // Get the important file used to create the Item objects
		byte[] itemPlaceBin = itemDB.getFile("itemPlace.bin"); // Get the Placement File
		byte[] itemList = itemDB.getFile("item1.lst"); // Get the Text File
		byte[] itemSubList = itemDB.getFile("item1sub.lst"); // Get the Equipment File
		initializeItemsFromBin(itemBin);
		addItemSubListData(itemSubList);
		addItemPlacementData(itemPlaceBin);
		addItemListData(itemList);
	}
	private void addItemListData(byte[] itemList) 
	{
		// Add Item List Data to all Corresponding Items
		String file;
		file = Utils.decodeBytesToString(itemList);
		String[] Lines = file.split("\r\n");
		ArrayList<String> ItemLines = new ArrayList<String>();
		for(String Line : Lines)
		{
			if(Line.length()>1&&Line.charAt(0)=='@')
			{
				if(ItemLines.size()>0)
				{
					int itemCode = Integer.parseInt(ItemLines.get(0).substring(1));//Gets the item code from the first line
					for(Item i : items)
					{
						if(itemCode == i.getItemCode())
						{
							i.addItemList(ItemLines);
						}
					}
					ItemLines = new ArrayList<String>();
				}
			}
			ItemLines.add(Line);
		}
		int itemCode = Integer.parseInt(ItemLines.get(0).substring(1));
		for(Item i : items)
		{
			if(itemCode == i.getItemCode())
			{
				i.addItemList(ItemLines);
			}
		}
	}
	private void addItemPlacementData(byte[] itemPlaceBin) 
	{
		//Add Placement Data
		//Skip first 16 bytes which store size info
		for(int i = 16; i<itemPlaceBin.length; i+=28)
		{
			byte[] itemPlaceData = Arrays.copyOfRange(itemPlaceBin, i, i+28);
			int itemCode = bFM.Utils.getShort(itemPlaceData, 2);
			for(Item item : items)
			{
				if(itemCode==item.getItemCode())
				{
					item.addPlacement(itemPlaceData);
					break;
				}
			}
		}
	}
	private void addItemSubListData(byte[] itemSubList) 
	{
		//Add all the lines in the item sub list to the corresponding files
		String file;
		file = Utils.decodeBytesToString(itemSubList);
		String[] Lines = file.split("\r\n");
		for(String Line : Lines)
		{
			for(Item i : items)
			{
				if(Line.indexOf("HITSE "+ i.getItemCode()+",")!=-1)
				{
					i.addHitSoundEffectData(Line);
					break;
				}
				if(Line.indexOf("WEP " + i.getItemCode()+",")!=-1)
				{
					i.addWeaponData(Line);
					break;
				}
			}
		}
	}
	private void initializeItemsFromBin(byte[] itemBin)
	{
		//Item Bin is as follows
		//First Four Bytes: Amount of Items
		//Next 12: Filler 0's
		//Each Item is 84 bytes in size
		for(int i = 16; i<itemBin.length; i+=84)
		{
			Item item = new Item(Arrays.copyOfRange(itemBin, i, i+84));
			items.add(item);
		}
	}
	public itemDatabaseManager(List<String> lines)
	{
		initializeFromLines(lines);
		
	}
	private void initializeFromLines(List<String> lines)
	{
		//TODO, do a loop through all lines that adds the line to the last item made, or makes a new item
		//Initializes an Item Manager using the extracted textfile, for re-encryption
		
		Item lastItemCreated = null;
		for(String Line : lines)
		{
			if(Line.indexOf("LKS Item Database File Version")!=-1)
			{
				if(Line.indexOf("LKS Item Database File Version 1.1")==-1)
				{
					System.err.println("Text Based Item Database file is wrong version. Expected: 1.1 " + Line);
				}
			}
			if(Line.indexOf("<<Item>>")!=-1)
			{
				lastItemCreated = new Item(Line);
				items.add(lastItemCreated);//Can add instantly because it is the same object
			}
			else if(Line.indexOf("<<")!=-1 && Line.indexOf(">>")!=-1)
			{
				lastItemCreated.addItemVariableLine(Line);
			}
		}
	}
	public String toString()
	{
		//If I need to explain this, you dont need to read it.
		String ret = "LKS Item Database File Version 1.0\n";
		for(Item i : items)
		{
			ret += i;
		}
		return ret;
	}
	public byte[] toBytes()
	{
		//return an encoded byte Array
		PCKGManager itemDB = new PCKGManager("Item Database");
		itemDB.addFile("item1.bin", getItemBin());//No Difference!!!
		itemDB.addFile("item1.lst", getItemList());//No Difference!!!
		itemDB.addFile("item1sub.lst", getItemSubList());//No Difference!!!
		itemDB.addFile("itemPlace.bin", getItemPlacementData());//.34% difference
		return itemDB.getFile();
	}
	private byte[] getItemSubList() 
	{
		//returns the item1sub.lst file
		byte[] ret = null;
		for(Item i : items)
		{
			ret = Utils.mergeArrays(ret, i.getSubList());
		}
		return ret;
	}
	private byte[] getItemList() 
	{
		// returns the item1.lst file
		byte[] ret = null;
		for(Item i : items)
		{
			ret = Utils.mergeArrays(ret, i.getList());
		}
		
		return ret;
	}
	private byte[] getItemPlacementData() 
	{
		// returns the itemPlace.bin file
		byte[] ret = new byte[14];
		int placeCount = 0;
		for(Item i : items)
		{
			byte[] newArr = i.getPlaceBytes();
			ret = bFM.Utils.mergeArrays(ret, newArr);
			if(newArr.length==28) placeCount++;
		}
		return bFM.Utils.mergeArrays(bFM.Utils.toByteArr(placeCount, 2), ret);
	}
	private byte[] getItemBin() 
	{
		// creates the item1.bin file
		byte[] ret = bFM.Utils.toByteArr(items.size(), 4);
		ret = bFM.Utils.mergeArrays(ret, new byte[12]);
		for(Item i : items)
		{
			ret = bFM.Utils.mergeArrays(ret, i.getItemBytes());
		}
		return ret;
	}
	public boolean equals(String name) 
	{
		throw new UnsupportedOperationException("equals() should not be called on type " + this.getClass());
	}
	public void setData(byte[] data) 
	{
		throw new UnsupportedOperationException("setData(byte[] data) should not be called on type " + this.getClass());
	}
	public void setName(String name) 
	{
		this.name = name;
	}
	public String getName() 
	{
		return name;
	}
	public int getSize() 
	{
		throw new UnsupportedOperationException("getSize() should not be called on type " + this.getClass());
	}
	public ArrayList<Item> getItems() 
	{
		return items;
	}
	public void replaceFromText(byte[] data) 
	{
		List<String> lines = bFM.Utils.bytesToStrs(data);
		replaceFromText(lines);
	}
	public void replaceFromText(List<String> lines) 
	{
		items.removeAll(items);
		initializeFromLines(lines);
	}
	public byte[] toItemBytes()
	{
		return Utils.encodeStringToBytes(toString());
	}
	public byte[] toLng()
	{
		byte[] ret = Utils.encodeStringToBytes("LKS Item Database Translation File\n");
		for(Item i : items)
		{
			ret = Utils.mergeArrays(ret, i.toLng());
		}
		return ret;
	}
	public void importLng(byte[] data)
	{
		List<String> lines = bFM.Utils.bytesToStrs(data);
		int code = -1;
		Item lastItemCode = null;
		for(String line : lines)
		{
			if(line.indexOf("<<Item Code>>")!=-1)
			{
				code = Utils.formatFlag(line);
				lastItemCode = getItemByCode(code);
			}
			else if(line.indexOf("<<Newslog Name>>")!=-1)
			{
				if(lastItemCode == null) Utils.DebugPrint("Couldn't Import Item Newslog Name as Item Code: " + code + " is not defined in the current file");
				else lastItemCode.addItemVariableLine(line);
			}
			else if(line.indexOf("<<Display Name>>")!=-1)
			{
				if(lastItemCode == null) Utils.DebugPrint("Couldn't Import Item Display Name as Item Code: " + code + " is not defined in the current file");
				else lastItemCode.addItemVariableLine(line);
			}
			else if(line.indexOf("<<Item Description>>")!=-1)
			{
				if(lastItemCode == null) Utils.DebugPrint("Couldn't Import Item Description as Item Code: " + code + " is not defined in the current file");
				else lastItemCode.addItemVariableLine(line);
			}
		}
	}
	public Item getItemByCode(int itemCode)
	{
		for(Item i : items)
		{
			if(i.itemCode == itemCode)
			{
				return i;
			}
		}
		return null;
	}
}
