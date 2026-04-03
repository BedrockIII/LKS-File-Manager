package itemManager;

import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import PCKGManager.PCKGManager;

public class itemDatabaseManager 
{
	private ArrayList<Item> items = new ArrayList<Item>();
	public itemDatabaseManager(byte[] data)
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
		file = new String(itemList);
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
		try 
		{
			file = new String(itemSubList, "Shift-JIS");
		} catch (UnsupportedEncodingException e) 
		{
			file = new String(itemSubList);
		}
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
		//TODO, do a loop through all lines that adds the line to the last item made, or makes a new item
		//Initializes an Item Manager using the extracted textfile, for re-encryption
		
	}
	public String toString()
	{
		String ret = "LKS Item Database File Version 1.0\n";
		for(Item i : items)
		{
			ret += i;
		}
		return ret;
	}
}
