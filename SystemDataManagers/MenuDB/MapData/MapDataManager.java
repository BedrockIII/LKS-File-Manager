package SystemDataManagers.MenuDB.MapData;

import PCKGManager.PCKGManager;
import bFM.OpenedFile;

public class MapDataManager implements OpenedFile
{
	MapPOIDataManager Level0;
	MapPOIDataManager Level1;
	MapPOIDataManager Level2;
	MapPOIDataManager Level3;
	MapAreaManager AreaData;
	MapQuestInfoManager QuestData;
	//ArrayList<MapQuestData> QuestData = new ArrayList<MapQuestData>();
	//Map Level
			//Title
			//Position short x, short y
			//Message
			//Image
			//Flag (5 -1 ints, 1 non -1 int)
	public MapDataManager(byte[] data)
	{
		initializeFromBytes(data);
	}
	private void initializeFromBytes(byte[] data)
	{
		PCKGManager mapdata = new PCKGManager(data);
		AreaData = new MapAreaManager(mapdata.getFile("AreaName"), mapdata.getFile("AreaImage"), mapdata.getFile("AreaValue"));
		QuestData = new MapQuestInfoManager(mapdata.getFile("QuestImage"), mapdata.getFile("QuestValue"));
		Level0 =  new MapPOIDataManager(mapdata.getFile("MapTitle0"), mapdata.getFile("MapMessage0"),
				mapdata.getFile("MapImage0"), mapdata.getFile("MapPosition0"), mapdata.getFile("MapFlag0"));
		Level1 =  new MapPOIDataManager(mapdata.getFile("MapTitle1"), mapdata.getFile("MapMessage1"),
				mapdata.getFile("MapImage1"), mapdata.getFile("MapPosition1"), mapdata.getFile("MapFlag1"));
		Level2 =  new MapPOIDataManager(mapdata.getFile("MapTitle2"), mapdata.getFile("MapMessage2"),
				mapdata.getFile("MapImage2"), mapdata.getFile("MapPosition2"), mapdata.getFile("MapFlag2"));
		Level3 =  new MapPOIDataManager(mapdata.getFile("MapTitle3"), mapdata.getFile("MapMessage3"),
				mapdata.getFile("MapImage3"), mapdata.getFile("MapPosition3"), mapdata.getFile("MapFlag3"));
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
		mapdata.addFile("MapTitle0", Level0.toNameBytes());
		mapdata.addFile("MapMessage0", Level0.toMessageBytes());
		mapdata.addFile("MapFlag0", Level0.toFlagBytes());
		mapdata.addFile("MapPosition0", Level0.toPositionBytes());
		mapdata.addFile("MapImage0", Level0.toImageBytes());
		
		mapdata.addFile("MapTitle1", Level1.toNameBytes());
		mapdata.addFile("MapMessage1", Level1.toMessageBytes());
		mapdata.addFile("MapFlag1", Level1.toFlagBytes());
		mapdata.addFile("MapPosition1", Level1.toPositionBytes());
		mapdata.addFile("MapImage1", Level1.toImageBytes());
		
		mapdata.addFile("MapTitle2", Level2.toNameBytes());
		mapdata.addFile("MapMessage2", Level2.toMessageBytes());
		mapdata.addFile("MapFlag2", Level2.toFlagBytes());
		mapdata.addFile("MapPosition2", Level2.toPositionBytes());
		mapdata.addFile("MapImage2", Level2.toImageBytes());
		
		mapdata.addFile("MapTitle3", Level3.toNameBytes());
		mapdata.addFile("MapMessage3", Level3.toMessageBytes());
		mapdata.addFile("MapFlag3", Level3.toFlagBytes());
		mapdata.addFile("MapPosition3", Level3.toPositionBytes());
		mapdata.addFile("MapImage3", Level3.toImageBytes());
		
		mapdata.addFile("AreaName", AreaData.toNameBytes());
		mapdata.addFile("AreaImage", AreaData.toImageBytes());
		mapdata.addFile("AreaValue", AreaData.toFlagBytes());
		
		mapdata.addFile("QuestImage", QuestData.toNameBytes());
		mapdata.addFile("QuestValue", QuestData.toFlagBytes());
		
		return mapdata.getFile();
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
	public MapPOIDataManager getPOILevel0()
	{
		return Level0;
	}
	public MapPOIDataManager getPOILevel1()
	{
		return Level1;
	}
	public MapPOIDataManager getPOILevel2()
	{
		return Level2;
	}
	public MapPOIDataManager getPOILevel3()
	{
		return Level3;
	}
	public MapAreaManager getAreaData()
	{
		return AreaData;
	}
	public MapQuestInfoManager getQuestData()
	{
		return QuestData;
	}
}
