package SystemDataManagers.MenuDB.MapData;

import PCKGManager.PCKGManager;
import bFM.OpenedFile;

public class MapDataManager implements OpenedFile
{
	//ArrayList<MapData> level0 = new ArrayList<MapData>();
	//ArrayList<MapData> level1 = new ArrayList<MapData>();
	//ArrayList<MapData> level2 = new ArrayList<MapData>();
	//ArrayList<MapData> level3 = new ArrayList<MapData>();
	//ArrayList<MapAreaData> AreaData = new ArrayList<MapAreaData>();
	//ArrayList<MapQuestData> QuestData = new ArrayList<MapQuestData>();
	//Map Level
			//Title
			//Position short x, short y
			//Message
			//Image
			//Flag (5 -1 ints, 1 non -1 int)
		//Area
			//Name
			//Image
			//Value
		//Quest(Unused???)
			//Image(Unused???)
			//Value(8 -1 int Flags, 2 non -1 int Flags)
	public MapDataManager(byte[] data)
	{
		initializeFromBytes(data);
	}
	private void initializeFromBytes(byte[] data)
	{
		PCKGManager mapdata = new PCKGManager(data);
	}
	public boolean equals(String name) 
	{
		throw new UnsupportedOperationException("equals() should not be called on type " + this.getClass());
	}
	public void setData(byte[] data) 
	{
		//Clear all lists
		initializeFromBytes(data);
		throw new UnsupportedOperationException("setData(byte[] data) should not be called on type " + this.getClass());
	}
	public byte[] toBytes() 
	{
		PCKGManager mapdata = new PCKGManager();
		throw new UnsupportedOperationException("toBytes() should not be called on type " + this.getClass());
	}
	public void setName(String name) 
	{
		throw new UnsupportedOperationException("setName(String name) should not be called on type " + this.getClass());
	}
	public String getName() 
	{
		return "mapdata.bin";
	}
	public int getSize() 
	{
		return toBytes().length;
	}
}
