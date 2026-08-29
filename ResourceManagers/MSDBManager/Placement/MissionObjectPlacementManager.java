package ResourceManagers.MSDBManager.Placement;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import bFM.Data;
import bFM.Utils;

public class MissionObjectPlacementManager implements Data
{
	boolean ignoreEmpty =  true;
	ArrayList<MobConstantPlace> Places = new ArrayList<MobConstantPlace>();
	ArrayList<MobGroup> Groups = new ArrayList<MobGroup>();
	//ArrayList<MobObject> Objects = new ArrayList<MobObject>();
	ArrayList<MobRandomArea> Areas = new ArrayList<MobRandomArea>();
	
	
	ArrayList<MobGroup> UsedGroups = new ArrayList<MobGroup>();
	ArrayList<Integer> GroupIDs = new ArrayList<Integer>();
	boolean hideFillerSpots = false;
	boolean filterOut = false;
	boolean filter = false;
	int modCode = -1;
	int xMin, xMax, zMin, zMax;
	int trialCnt = 0;
	public MissionObjectPlacementManager(byte[] placeData, byte[] GroupData, byte[] ObjectData, byte[] AreaData, byte[] PointData, byte[] EnemyTypeData)
	{
		GroupIDs = new ArrayList<Integer>();
		ArrayList<MobObject> Objects = importObjectData(ObjectData);
		importPlaceData(placeData);
		importGroupData(GroupData, Objects, Places);
		
		ArrayList<MobRandomPoint> Points = importRandomPoints(PointData);
		ArrayList<MobAreaData> AreaDatas = importAreaData(EnemyTypeData);
		importRandomAreas(AreaData, Points, AreaDatas);
	}
	public void importBMos(byte[] data)
	{
		parseBMosLines(Utils.bytesToStrs(data));
	}
	public void replaceBMos(byte[] data)
	{
		Places.removeAll(Places);
		Groups.removeAll(Groups);
		Areas.removeAll(Areas);
		parseBMosLines(Utils.bytesToStrs(data));
	}
 	private MobObject parseObjectLine(String[] line)
	{
		float float4 = Utils.formatFloat(line[0]);
		float float5 = Utils.formatFloat(line[1]);
		float float6 = Utils.formatFloat(line[2]);
		float float7 = Utils.formatFloat(line[3]);
		float float8 = Utils.formatFloat(line[4]);
		short num3 =Utils.formatInt(line[5]).shortValue();
		short num6 =Utils.formatInt(line[6]).shortValue();
		float float9 = Utils.formatFloat(line[7]);
		float float10 = Utils.formatFloat(line[8]);
		short num7 =Utils.formatInt(line[9]).shortValue();
		short num8 =Utils.formatInt(line[10]).shortValue();
		short num9 =Utils.formatInt(line[11]).shortValue();
		short num10 =Utils.formatInt(line[12]).shortValue();
		return new MobObject(float4, float5, float6, float7, float8, num3, num6, float9, float10, num7, num8, num9, num10);
	}
	int bMosDefaultGroupID = 60000;
	private MobGroup parseBMosGroup(String lineData)
	{
		int groupID, num12, num13, groupCode, num15, num16;
		float num17;
		String[] line = Utils.toStrArr(lineData);
		if(line.length == 7)
		{
			groupID =Utils.formatInt(line[0]).intValue();
			num12 =Utils.formatInt(line[1]).intValue();
			num13 =Utils.formatInt(line[2]).intValue();
			groupCode =Utils.formatInt(line[3]).intValue();
			num15 =Utils.formatInt(line[4]).intValue();
			num16 =Utils.formatInt(line[5]).intValue();
			num17 =Utils.formatFloat(line[6]);
		}
		else
		{
			groupID = bMosDefaultGroupID++;
			num12 =Utils.formatInt(line[0]).intValue();
			num13 =Utils.formatInt(line[1]).intValue();
			groupCode =Utils.formatInt(line[2]).intValue();
			num15 =Utils.formatInt(line[3]).intValue();
			num16 =Utils.formatInt(line[4]).intValue();
			num17 =Utils.formatFloat(line[5]);
		}
		MobGroup ret = new MobGroup(groupID, num12, num13, groupCode, num15, num16, num17);
		Groups.add(ret);
		registerGroupIDFromInt(groupCode);
		return ret;
	}
	private void parseBMosLines(List<String> lines)
	{
		GroupIDs = new ArrayList<Integer>();
		MobRandomArea lastArea = null;
		MobGroup lastGroup = null;
		MobConstantPlace lastPlace = null;
		for(String line : lines)
		{
			System.out.println(line);
			if(line.indexOf("Random Area:")!=-1)
			{
				lastArea = parseRandomArea(line);
			}
			if(line.indexOf("Random Position:")!=-1)
			{
				lastArea.add(parseRandomPoint(line));
			}
			else if(line.indexOf("Mission Group:")!=-1)
			{
				lastGroup = parseBMosGroup(line);
				lastPlace.addGroup(lastGroup);
			}
			else if(line.indexOf("Null Group:")!=-1)
			{
				lastPlace.addNullGroup(line);
			}
			else if(line.indexOf("Mission Object:")!=-1)
			{
				lastGroup.addObject(parseObjectLine(Utils.toStrArr(line)));
			}
			else if(line.indexOf("Constant Placement:")!=-1)
			{
				lastPlace = parseConstantPlacementLine(Utils.toStrArr(line));
			}
			else if(line.indexOf("Unsorted Group:")!=-1)
			{
				lastGroup = parseBMosGroup(line);
			}
		}
	}
	private MobConstantPlace parseConstantPlacementLine(String[] line)
	{
		int placeIndex = -1;
		float PlaceXPos = -1;
		float PlaceYPos = -1;
		float PlaceZPos = -1;
		float placeRotation = -1;
		float float2 = -1;
		float float3 = -1;
		short activationFlag1 = -1;
		short activationFlag2 = -1;
		short clearFlag = -1;
		short deactivationFlag = -1;
		short num4 = -1;
		short num5 = -1;
		if(line.length==12)
		{
			 placeIndex = -1;
			 PlaceXPos = Utils.formatFloat(line[0]);
			 PlaceYPos = Utils.formatFloat(line[1]);
			 PlaceZPos = Utils.formatFloat(line[2]);
			 placeRotation = Utils.formatFloat(line[3]);
			 float2 = Utils.formatFloat(line[4]);
			 float3 = Utils.formatFloat(line[5]);
			 activationFlag1 = Utils.formatInt(line[6]).shortValue();
			 activationFlag2 = Utils.formatInt(line[7]).shortValue();
			 clearFlag = Utils.formatInt(line[8]).shortValue();
			 deactivationFlag = Utils.formatInt(line[9]).shortValue();
			 num4 = Utils.formatInt(line[10]).shortValue();
			 num5 = Utils.formatInt(line[11]).shortValue();
		} else if(line.length==13)
		{
			 placeIndex = Utils.formatInt(line[0]).shortValue();;
			 PlaceXPos = Utils.formatFloat(line[1]);
			 PlaceYPos = Utils.formatFloat(line[2]);
			 PlaceZPos = Utils.formatFloat(line[3]);
			 placeRotation = Utils.formatFloat(line[4]);
			 float2 = Utils.formatFloat(line[5]);
			 float3 = Utils.formatFloat(line[6]);
			 activationFlag1 = Utils.formatInt(line[7]).shortValue();
			 activationFlag2 = Utils.formatInt(line[8]).shortValue();
			 clearFlag = Utils.formatInt(line[9]).shortValue();
			 deactivationFlag = Utils.formatInt(line[10]).shortValue();
			 num4 = Utils.formatInt(line[11]).shortValue();
			 num5 = Utils.formatInt(line[12]).shortValue();
		}
		MobConstantPlace ret = new MobConstantPlace(placeIndex, PlaceXPos, PlaceYPos, PlaceZPos, 
				placeRotation, float2, float3, (short)-1, activationFlag1, (short)-1, 
				activationFlag2, clearFlag, deactivationFlag, num4, num5);
		Places.add(ret);
		return ret;
	}
	private MobRandomArea parseRandomArea(String lineData)
	{
		String[] line = Utils.toStrArr(lineData);
		int areaCode = Utils.formatInt(line[0]).shortValue();
		int num0 = Utils.formatInt(line[1]).shortValue();
		int groupCode1 = Utils.formatInt(line[2]).shortValue();
		int groupCode2 = Utils.formatInt(line[3]).shortValue();
		int groupCode3 = Utils.formatInt(line[4]).shortValue();
		int groupCode4 = Utils.formatInt(line[5]).shortValue();
		MobRandomArea Area = new MobRandomArea(areaCode);
		Areas.add(Area);
		MobAreaData data = new MobAreaData((short)areaCode, (short)num0, (short)groupCode1, (short)groupCode2, (short)groupCode3, (short)groupCode4);
		Area.add(data);
		return Area;
	}
	private MobRandomPoint parseRandomPoint(String lineData) 
	{
		String[] line = Utils.toStrArr(lineData);
		if(line.length!=6)
		{
			System.err.println("Wrong length Random Point detected");
		}
		
		float xPos = Utils.formatFloat(line[0]);
		float yPos = Utils.formatFloat(line[1]);
		float zPos = Utils.formatFloat(line[2]);
		float rotation = Utils.formatFloat(line[3]);
		short ActivationFlag = Utils.formatInt(line[4]).shortValue();
		short DeactivationFlag = Utils.formatInt(line[5]).shortValue();
		
		return new MobRandomPoint(xPos, yPos, zPos, rotation, ActivationFlag, DeactivationFlag);
	}
	private ArrayList<MobAreaData> importAreaData(byte[] data) 
	{
		ArrayList<MobAreaData> AreaDatas = new ArrayList<MobAreaData>();
		for(int i = 4; i<data.length; i+=12)
		{
			AreaDatas.add(new MobAreaData(Arrays.copyOfRange(data, i, i+12)));
		}
		return AreaDatas;
	}
	private void importRandomAreas(byte[] data, ArrayList<MobRandomPoint> Points, ArrayList<MobAreaData> Data)
	{
		MobRandomArea lastArea = null;
		for(int i = 4; i<data.length; i+=8)
		{
			lastArea = new MobRandomArea(Arrays.copyOfRange(data, i, i+8), Points, Data);
			Areas.add(lastArea);
			for(MobAreaData datum : Data)
			{
				if(lastArea.areaCode == datum.areaCode)
				{
					lastArea.add(datum);
					break;
				}
			}
		}
	}
	private ArrayList<MobRandomPoint> importRandomPoints(byte[] data)
	{
		ArrayList<MobRandomPoint> Points = new ArrayList<MobRandomPoint>();
		for(int i = 4; i<data.length; i+=20)
		{
			Points.add(new MobRandomPoint(Arrays.copyOfRange(data, i, i+20)));
		}
		return Points;
	}
	private ArrayList<MobObject> importObjectData(byte[] data) 
	{
		ArrayList<MobObject> Objects = new ArrayList<MobObject>();
		for(int i = 4; i<data.length; i+=40)
		{
			Objects.add(new MobObject(Arrays.copyOfRange(data, i, i+40)));
		}
		return Objects;
	}
	private void importGroupData(byte[] data, ArrayList<MobObject> Objects, ArrayList<MobConstantPlace> Places) 
	{
		for(int i = 4; i<data.length; i+=20)
		{
			Groups.add(new MobGroup(Arrays.copyOfRange(data, i, i+20), Objects));
			registerGroupIDFromGroup(Groups.get(Groups.size()-1));
			
		}
		for(MobConstantPlace c : Places)
		{
			for(MobGroup g : Groups)
			{
				if(g.getGroupIndex()==c.getGroup2())
				{
					c.addGroup(g);
				}
			}
		}
	}
	private void registerGroupIDFromGroup(MobGroup g)
	{
		registerGroupIDFromInt(g.getGroupNumber());
		
	}
	private void registerGroupIDFromInt(int num)
	{
		for(int i : GroupIDs)
		{
			if(i==num) return;
		}
		GroupIDs.add(num);
	}
	private void importPlaceData(byte[] data) 
	{
		for(int i = 4; i<data.length; i+=40)
		{
			Places.add(new MobConstantPlace(Arrays.copyOfRange(data, i, i+40), Places.size()));
		}
		//System.out.println("Constant Places Count: " + Places.size());
	}
	public byte[] getPoints()
	{
		byte[] ret = Utils.toByteArr(1,2);
		byte[] data = null;
		int pointCount = 0;
		for(MobRandomArea a : Areas)
		{
			data = Utils.mergeArrays(data, a.getPointBytes());
			pointCount += a.getPointCount();
		}
		
		ret = Utils.mergeArrays(ret, Utils.toByteArr(pointCount, 2));
		ret = Utils.mergeArrays(ret, data);
		
		return ret;
	}
	public byte[] getAreas()
	{
		byte[] ret = bFM.Utils.toByteArr(1,2);
		int pointCount = 0;
		ret = bFM.Utils.mergeArrays(ret, bFM.Utils.toByteArr(Areas.size(), 2));
		for( int i = 0; i < Areas.size(); i++)
		{
			ret = bFM.Utils.mergeArrays(ret, Areas.get(i).toBytes(pointCount));
			pointCount += Areas.get(i).getPointCount();
		}
		return ret;
	}
	public byte[] getAreaDatas() 
	{
		byte[] ret = bFM.Utils.toByteArr(1,2);
		byte[] data = null;
		int areaDataCount = 0;
		for(MobRandomArea a : Areas)
		{
			data = Utils.mergeArrays(data, a.getAreaData().toBytes());
			areaDataCount += 1;
		}
		
		ret = Utils.mergeArrays(ret, Utils.toByteArr(areaDataCount, 2));
		ret = Utils.mergeArrays(ret, data);
		
		return ret;
	}
	public byte[] getConstantPlaces()
	{
		byte[] ret = bFM.Utils.toByteArr(1,2);
		sortPlacesByIndex();
		ret = bFM.Utils.mergeArrays(ret, bFM.Utils.toByteArr(Places.size(), 2));
		for( int i = 0; i < Places.size(); i++)
		{
			ret = bFM.Utils.mergeArrays(ret, Places.get(i).toBytes());
		}
		return ret;
	}
	public byte[] getGroups()
	{
		byte[] ret = bFM.Utils.toByteArr(1,2);
		int objectCount = 0;
		ret = bFM.Utils.mergeArrays(ret, bFM.Utils.toByteArr(Groups.size(), 2));
		for(MobGroup g : Groups)
		{
			ret = bFM.Utils.mergeArrays(ret, g.toBytes(objectCount));
			objectCount += g.getObjectCount();
		}
		return ret;
	}
	public byte[] getObjects()
	{
		byte[] ret = Utils.toByteArr(1,2);
		byte[] data = null;
		int objectCount = 0;
		for(MobGroup g : Groups)
		{
			data = Utils.mergeArrays(data, g.getObjectBytes());
			objectCount += g.getObjectCount();
		}
		
		ret = Utils.mergeArrays(ret, Utils.toByteArr(objectCount, 2));
		ret = Utils.mergeArrays(ret, data);
		
		return ret;
	}
	public void setConstraints(int xMin, int xMax, int zMin, int zMax, boolean filterOut, boolean hideFillerSpots) 
	{
		this.hideFillerSpots = hideFillerSpots;
		this.filterOut = filterOut;
		filter = true;
		this.xMin = xMin;
		this.xMax = xMax;
		this.zMin = zMin;
		this.zMax = zMax;
	}
	public MissionObjectPlacementManager(List<String> list)
	{
		parseBMosLines(list);
	}
	private void sortPlacesByIndex()
	{
		ArrayList<MobConstantPlace> sortPlaces = new ArrayList<MobConstantPlace>();
		int places = Places.size();
		int searchedIndex = 0;
		while( Places.size()>0)
		{
			
			for(int i = 0; i<Places.size(); i++)
			{
				if(Places.get(i).getIndex()==searchedIndex)
				{
					sortPlaces.add(Places.remove(i));
					//System.out.println(sortPlaces.get(searchedIndex));
					break;
				} else if(i+1==Places.size())
				{
					for(int j = 0; j<Places.size(); j++)
					{
						if(Places.get(j).getIndex()==-1)
						{
							sortPlaces.add(Places.remove(j));
							//System.out.println(sortPlaces.size());
							break;
						}
					}
				}
			}
			
			searchedIndex++;
		}
		//System.out.println("Old: "+ Places.size());
		for(int i = 0;i<sortPlaces.size();i++)
		{
			//System.out.print(sortPlaces.get(i).toBMos());
		}
		for(int i = 0;i<Places.size();i++)
		{
			System.out.print(Places.get(i).toBMos());
		}
		Places=sortPlaces;
		//System.out.println("Start: "+ places);
		//System.out.println("End: "+ sortPlaces.size());
	}
	private String toStringCST()
	{
		ArrayList<MobGroup> unprintedGroups = new ArrayList<MobGroup>(Groups);
		String ret = "Constant Monsters: \n";
		for(MobConstantPlace place : Places)
		{
			int groupIndex1 = -1;
			int groupIndex2 = -1;
			for(int j = 0; j<Groups.size(); j++)
			{
				if(Groups.get(j).getCode()==place.getGroup1()) groupIndex1 = j;
				if(Groups.get(j).getCode()==place.getGroup2()) groupIndex2 = j;
			}
			ret+=place.toBMos();
			if(groupIndex1!=-1)
			{
				ret += Groups.get(groupIndex1).bMos();
				unprintedGroups.remove(Groups.get(groupIndex1));
			}
			else ret+= "\tNull Group: " + place.getGroup1() + "\n";
			if(place.getGroup1()!=place.getGroup2())
			{
				if(groupIndex2!=-1)
				{
					ret += Groups.get(groupIndex2).bMos();
					unprintedGroups.remove(Groups.get(groupIndex2));
				}
				else ret+= "\tNull Group: " + place.getGroup2() + "\n";
			}
		}
		for(MobGroup group : unprintedGroups)
		{
			ret += group.bMos2();
		};
		return ret;
	}
	private String toStringRND()
	{
		String ret = "Random Monsters: \n";
		for(MobRandomArea Area : Areas)
		{
			ret+= Area.toBrm();
		}
		return ret;
	}
	public String toString()
	{
		
		UsedGroups = new ArrayList<MobGroup>();
		String ret ="";
		ret += toStringCST();
		ret += toStringRND();
		return ret;
	}
	public byte[] toBMos()
	{
		return Utils.encodeStringToBytes(toString());
	}
	public void setFilterCode(int modCode)
	{
		this.modCode = modCode;
	}
	public boolean equals(String name) 
	{
		throw new UnsupportedOperationException("equals() should not be called on type " + this.getClass());
	}
	public void setData(byte[] data) 
	{
		throw new UnsupportedOperationException("setData(byte[] data) should not be called on type " + this.getClass());
	}
	public byte[] toBytes() 
	{
		throw new UnsupportedOperationException("toBytes() should not be called on type " + this.getClass());
	}
	public void setName(String name) 
	{
		throw new UnsupportedOperationException("setName(String name) should not be called on type " + this.getClass());
	}
	public String getName() 
	{
		throw new UnsupportedOperationException("getName() should not be called on type " + this.getClass());
	}
	public int getSize() 
	{
		throw new UnsupportedOperationException("getSize() should not be called on type " + this.getClass());
	}
	public ArrayList<MobRandomArea> getAreaList() 
	{
		return Areas;
	}
	public ArrayList<MobGroup> getMobGroups() 
	{
		return Groups;
	}
	public ArrayList<Integer> getGroupCodes() 
	{
		return GroupIDs;
	}
	public ArrayList<MobGroup> getGroupsByCode(int code) 
	{
		ArrayList<MobGroup> ret = new ArrayList<MobGroup>();
		for(MobGroup group : Groups)
		{
			if(group.getGroupNumber() == code)
			{
				ret.add(group);
			}
		}
		return ret;
	}
	public int getIndexForGroup(int code) 
	{
		for(MobGroup group : Groups)
		{
			if(group.getGroupIndex() == code)
			{
				return getIndexForGroup(code + 1);
			}
		}
		return code;
	}
	public void removeGroup(MobGroup data)
	{
		Groups.remove(data);
		Places.remove(data.getPlacement());
		//System.out.println(data.bMos2());
	}
	public ArrayList<MobConstantPlace> getMobPlacements()
	{
		return Places;
	}
}
