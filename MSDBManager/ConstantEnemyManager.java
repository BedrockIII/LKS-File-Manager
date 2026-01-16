package MSDBManager;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ConstantEnemyManager 
{
	boolean ignoreEmpty =  true;
	ArrayList<MobConstantPlace> Places = new ArrayList<MobConstantPlace>();
	ArrayList<MobGroup> Groups = new ArrayList<MobGroup>();
	ArrayList<MobObject> Objects = new ArrayList<MobObject>();
	ArrayList<MobRandomArea> Areas = new ArrayList<MobRandomArea>();
	ArrayList<MobAreaData> AreaDatas = new ArrayList<MobAreaData>();
	ArrayList<MobRandomPoint> Points = new ArrayList<MobRandomPoint>();
	ArrayList<MobGroup> UsedGroups = new ArrayList<MobGroup>();
	boolean hideFillerSpots = false;
	boolean filterOut = false;
	boolean filter = false;
	int modCode = -1;
	int xMin, xMax, zMin, zMax;
	int trialCnt = 0;
	public ConstantEnemyManager(byte[] placeData, byte[] GroupData, byte[] ObjectData, byte[] AreaData, byte[] PointData, byte[] EnemyTypeData)
	{
		importPlaceData(placeData);
		importGroupData(GroupData);
		importObjectData(ObjectData);
		importRandomAreas(AreaData);
		importRandomPoints(PointData);
		importAreaData(EnemyTypeData);
	}
	private void bMosObject(String[] line)
	{
		float float4 = Float.valueOf(line[0]).floatValue();
		float float5 = Float.valueOf(line[1]).floatValue();
		float float6 = Float.valueOf(line[2]).floatValue();
		float float7 = Float.valueOf(line[3]).floatValue();
		float float8 = Float.valueOf(line[4]).floatValue();
		short num3 =Integer.valueOf(line[5]).shortValue();
		short num6 =Integer.valueOf(line[6]).shortValue();
		float float9 = Float.valueOf(line[7]).floatValue();
		float float10 = Float.valueOf(line[8]).floatValue();
		short num7 =Integer.valueOf(line[9]).shortValue();
		short num8 =Integer.valueOf(line[10]).shortValue();
		short num9 =Integer.valueOf(line[11]).shortValue();
		short num10 =Integer.valueOf(line[12]).shortValue();
		Objects.add(new MobObject(float4, float5, float6, float7, float8, num3, num6, float9, float10, num7, num8, num9, num10));
	}
	private int bMosGroup(List<String> lines, String[] line, int index)
	{
		int newIndex = -1;
		int objectCount = 0;
		int firstObjectIndex = -1;
		for(int i = index; i<lines.size(); i++)
		{
			if(lines.get(i).indexOf("Constant Object:")==-1)
			{//if not an Object Line
				break;
			}
			if(firstObjectIndex==-1)
			{
				firstObjectIndex=(int) Objects.size();
			}
			bMosObject(splitList(lines.get(i)));
			objectCount++;
			newIndex=i;
		}
		if(line.length == 6)
		{
			int num11 =Integer.valueOf(line[0]).intValue();
			int num12 =Integer.valueOf(line[1]).intValue();
			int num13 =Integer.valueOf(line[2]).intValue();
			int num14 =Integer.valueOf(line[3]).intValue();
			int num15 =Integer.valueOf(line[4]).intValue();
			float num16 =Float.valueOf(line[5]).floatValue();
			//int num17 =Integer.valueOf(line[6]).intValue();
			Groups.add(new MobGroup((int)Groups.size(), num11, firstObjectIndex, objectCount, num12, num13, num14, num15, num16, true));
			return newIndex;
		} else 
		{
			int groupCode =Integer.valueOf(line[0]).intValue();
			int num12 =Integer.valueOf(line[1]).intValue();
			int num13 =Integer.valueOf(line[2]).intValue();
			int num14 =Integer.valueOf(line[3]).intValue();
			int num15 =Integer.valueOf(line[4]).intValue();
			int num16 =Integer.valueOf(line[5]).intValue();
			float num17 =Float.valueOf(line[6]).floatValue();
			//int num18 =Integer.valueOf(line[7]).intValue();
			Groups.add(new MobGroup(groupCode, num12, firstObjectIndex, objectCount, num13, num14, num15, num16, num17));
		}
		return newIndex;//return new index
		
	}
	private int bMosConstant(List<String> lines, String[] line, int index)
	{//index should be +1
		short groupIndex1 = -1;
		short groupIndex2 = -1;
		int newIndex = -1;
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
			 PlaceXPos = Float.valueOf(line[0]).floatValue();
			 PlaceYPos = Float.valueOf(line[1]).floatValue();
			 PlaceZPos = Float.valueOf(line[2]).floatValue();
			 placeRotation = Float.valueOf(line[3]).floatValue();
			 float2 = Float.valueOf(line[4]).floatValue();
			 float3 = Float.valueOf(line[5]).floatValue();
			 activationFlag1 = Integer.valueOf(line[6]).shortValue();
			 activationFlag2 = Integer.valueOf(line[7]).shortValue();
			 clearFlag = Integer.valueOf(line[8]).shortValue();
			 deactivationFlag = Integer.valueOf(line[9]).shortValue();
			 num4 = Integer.valueOf(line[10]).shortValue();
			 num5 = Integer.valueOf(line[11]).shortValue();
		} else if(line.length==13)
		{
			 placeIndex = Integer.valueOf(line[0]).shortValue();;
			 PlaceXPos = Float.valueOf(line[1]).floatValue();
			 PlaceYPos = Float.valueOf(line[2]).floatValue();
			 PlaceZPos = Float.valueOf(line[3]).floatValue();
			 placeRotation = Float.valueOf(line[4]).floatValue();
			 float2 = Float.valueOf(line[5]).floatValue();
			 float3 = Float.valueOf(line[6]).floatValue();
			 activationFlag1 = Integer.valueOf(line[7]).shortValue();
			 activationFlag2 = Integer.valueOf(line[8]).shortValue();
			 clearFlag = Integer.valueOf(line[9]).shortValue();
			 deactivationFlag = Integer.valueOf(line[10]).shortValue();
			 num4 = Integer.valueOf(line[11]).shortValue();
			 num5 = Integer.valueOf(line[12]).shortValue();
		}
		
		for(int i = index; i<lines.size(); i++)
		{
			if(lines.get(i).indexOf("Constant Monster:")!=-1||lines.get(i).indexOf("Random Area:")!=-1||lines.get(i).indexOf("Unsorted Group:")!=-1)
			{
				if(groupIndex2==-1)
				{
					groupIndex2=groupIndex1;
				}
				Places.add(new MobConstantPlace(placeIndex, PlaceXPos, PlaceYPos, PlaceZPos, placeRotation, float2, float3, groupIndex1, activationFlag1, groupIndex2, activationFlag2, clearFlag, deactivationFlag, num4, num5));
				break;
			}
			else if(lines.get(i).indexOf("Monster Group:")!=-1)
			{
				i = (short)bMosGroup(lines, splitList(lines.get(i)), i+1);
				if(groupIndex1==-1)
				{
					groupIndex1 = (short) Groups.get(Groups.size()-1).getCode();
				}
				else
				{
					groupIndex2 = (short) Groups.get(Groups.size()-1).getCode();
				}
			}
			else if(lines.get(i).indexOf("Null Group:")!=-1)
			{
				if(groupIndex1==-1)
				{
					groupIndex1 = Integer.valueOf(splitList(lines.get(i))[0]).shortValue();
				}
				else
				{
					groupIndex2 = Integer.valueOf(splitList(lines.get(i))[0]).shortValue();
				}
			}
			newIndex = i;
			
		}
		
		return newIndex;//return new index
	}
	private int bMosOther(List<String> lines, String[] line, int index) 
	{//(short)bMosGroup(lines, splitList(lines.get(i)), i+1);
		int newIndex = -1;
		short groupCode =Integer.valueOf(line[0]).shortValue();
		short objectCount = 0;
		short firstObjectIndex = -1;
		for(int i = index; i<lines.size(); i++)
		{
			if(lines.get(i).indexOf("Unsorted Object:")==-1)
			{//if not an Object Line
				break;
			}
			if(firstObjectIndex==-1)
			{
				firstObjectIndex=(short) Objects.size();
			}
			bMosObject(splitList(lines.get(i)));
			objectCount++;
			newIndex=i;
		}
		short num12 =Integer.valueOf(line[1]).shortValue();
		short num13 =Integer.valueOf(line[2]).shortValue();
		short num14 =Integer.valueOf(line[3]).shortValue();
		short num15 =Integer.valueOf(line[4]).shortValue();
		short num16 =Integer.valueOf(line[5]).shortValue();
		float num17 =Float.valueOf(line[6]).floatValue();
		//short num18 =Integer.valueOf(line[7]).shortValue();
		Groups.add(new MobGroup(groupCode, num12, firstObjectIndex, objectCount, num13, num14, num15, num16, num17));
		return newIndex;//return new index
	}
	private int bMosRandom(List<String> lines, String[] line, int index)
	{
		int areaCode = -1;
		int num0 = -1;
		int groupCode1 = -1;
		int groupCode2 = -1;
		int groupCode3 = -1;
		int groupCode4 = -1;
		
		areaCode = Integer.valueOf(line[0]).shortValue();
		num0 = Integer.valueOf(line[1]).shortValue();
		groupCode1 = Integer.valueOf(line[2]).shortValue();
		groupCode2 = Integer.valueOf(line[3]).shortValue();
		groupCode3 = Integer.valueOf(line[4]).shortValue();
		groupCode4 = Integer.valueOf(line[5]).shortValue();
		int randomPointIndex = Points.size();
		int randomPointCount = 0;
		
		AreaDatas.add(new MobAreaData((short)areaCode, (short)num0, (short)groupCode1, (short)groupCode2, (short)groupCode3, (short)groupCode4));
		
		//to skip any comments
		
		while(index<lines.size() && lines.get(index).indexOf("Random Position")==-1)
		{
			index++;
			
		}
		
		while(index<lines.size() && lines.get(index).indexOf("Random Position")!=-1)
		{
			bMosRandomPoint( lines, splitList(lines.get(index)), index);
			index++;
			randomPointCount++;
			
		}
		
		Areas.add(new MobRandomArea(areaCode, randomPointCount, randomPointIndex));
		return index-1;
	}
	private void bMosRandomPoint(List<String> lines, String[] line, int index) 
	{
		if(line.length!=6)
		{
			System.out.println("Wrong length Random Point on line " + index);
			return;
		}
		float xPos = -1;
		float yPos = -1;
		float zPos = -1;
		float rotation = -1;
		short ActivationFlag = -1;
		short DeactivationFlag = -1;
		
		xPos = Float.valueOf(line[0]).floatValue();
		yPos = Float.valueOf(line[1]).floatValue();
		zPos = Float.valueOf(line[2]).floatValue();
		rotation = Float.valueOf(line[3]).floatValue();
		ActivationFlag = Short.valueOf(line[4]).shortValue();
		DeactivationFlag = Short.valueOf(line[5]).shortValue();
		
		Points.add(new MobRandomPoint(xPos, yPos, zPos, rotation, ActivationFlag, DeactivationFlag));
	}
	private void importAreaData(byte[] data) 
	{
		for(int i = 4; i<data.length; i+=12)
		{
			AreaDatas.add(new MobAreaData(Arrays.copyOfRange(data, i, i+12)));
		}
		
	}
	private void importRandomAreas(byte[] data)
	{
		for(int i = 4; i<data.length; i+=8)
		{
			Areas.add(new MobRandomArea(Arrays.copyOfRange(data, i, i+8)));
		}
	}
	private void importRandomPoints(byte[] data)
	{
		for(int i = 4; i<data.length; i+=20)
		{
			Points.add(new MobRandomPoint(Arrays.copyOfRange(data, i, i+20)));
		}
	}
	private void importObjectData(byte[] data) 
	{
		for(int i = 4; i<data.length; i+=40)
		{
			Objects.add(new MobObject(Arrays.copyOfRange(data, i, i+40)));
		}
	}
	private void importGroupData(byte[] data) 
	{
		for(int i = 4; i<data.length; i+=20)
		{
			Groups.add(new MobGroup(Arrays.copyOfRange(data, i, i+20)));
		}
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
		byte[] ret = bFM.Utils.toByteArr(1,2);
		ret = bFM.Utils.mergeArrays(ret, bFM.Utils.toByteArr(Points.size(), 2));
		for( int i = 0; i < Points.size(); i++)
		{
			ret = bFM.Utils.mergeArrays(ret, Points.get(i).toBytes());
		}
		return ret;
	}
	public byte[] getAreas()
	{
		byte[] ret = bFM.Utils.toByteArr(1,2);
		ret = bFM.Utils.mergeArrays(ret, bFM.Utils.toByteArr(Areas.size(), 2));
		for( int i = 0; i < Areas.size(); i++)
		{
			ret = bFM.Utils.mergeArrays(ret, Areas.get(i).toBytes());
		}
		return ret;
	}
	public byte[] getAreaDatas() 
	{
		byte[] ret = bFM.Utils.toByteArr(1,2);
		ret = bFM.Utils.mergeArrays(ret, bFM.Utils.toByteArr(AreaDatas.size(), 2));
		for( int i = 0; i < AreaDatas.size(); i++)
		{
			ret = bFM.Utils.mergeArrays(ret, AreaDatas.get(i).toBytes());
		}
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
		ret = bFM.Utils.mergeArrays(ret, bFM.Utils.toByteArr(Groups.size(), 2));
		for( int i = 0; i < Groups.size(); i++)
		{
			ret = bFM.Utils.mergeArrays(ret, Groups.get(i).toBytes());
		}
		return ret;
	}
	public byte[] getObjects()
	{
		byte[] ret = bFM.Utils.toByteArr(1,2);
		ret = bFM.Utils.mergeArrays(ret, bFM.Utils.toByteArr(Objects.size(), 2));
		
		for( int i = 0; i < Objects.size(); i++)
		{
			ret = bFM.Utils.mergeArrays(ret, Objects.get(i).toBytes());
		}
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
	public ConstantEnemyManager(List<String> list)
	{
		for(int i = 0; i<list.size(); i++)
		{
			if(list.get(i).indexOf("Random Area:")!=-1)
			{
				i = bMosRandom(list, splitList(list.get(i)), i+1);
			}
			else if(list.get(i).indexOf("Constant Monster:")!=-1)
			{
				i = bMosConstant(list, splitList(list.get(i)), i+1);
			}
			else if(list.get(i).indexOf("Unsorted Group:")!=-1)
			{
				i = bMosOther(list, splitList(list.get(i)), i+1);
			}
			
		}
		fixGroupIndex();
	}
	private void fixGroupIndex() 
	{
		for(int i = 0; i<Groups.size(); i++)
		{
			if(Groups.get(i).generatedIndex())
			{
				for(int j = 0; j< Groups.size(); j++)
				{
					if(i!=j&&Groups.get(i).getCode()==Groups.get(j).getCode())
					{
						Groups.get(i).newCode();
						j=0;
						trialCnt++;
						System.out.println("Remade Index:" +trialCnt);
					}
				}
			}
		}
	}
	private static String[] splitList(String listStr)
	{//Split List into String Array
		final String validChars = "1234567890.-";
		ArrayList<String> list = new ArrayList<String>();
		String word = "";
		for(int i = 0; i < listStr.length(); i++)
		{
			if(validChars.indexOf(listStr.charAt(i))!=-1)
			{
				word+=listStr.charAt(i);
			}
			else if(listStr.charAt(i)==' ')
			{
				if(word.length()>0)
				{
					list.add(word);
					word = "";
				}
			}
		}
		list.add(word);
		String[] ret = new String[list.size()];
		for(int i = 0; i < ret.length; i++)
		{
			ret[i]=list.get(i);
		}
		return ret;
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
				} else if(searchedIndex==sortPlaces.size())
				{
					//System.out.println(sortPlaces.size());
					//sortPlaces.add(new MobConstantPlace((float)9999.0,(float)0.0,(float)9999.0,(float)0.0,(float)40.0,(float)40.0,(short)61400, (short)61400, (short)-1,(short)-1,(short)-1,(short)-1,(short)0,(short)0 ));
				}
			}
			
			searchedIndex++;
		}
		System.out.println("Old: "+ Places.size());
		for(int i = 0;i<sortPlaces.size();i++)
		{
			//System.out.print(sortPlaces.get(i).toBMos());
		}
		for(int i = 0;i<Places.size();i++)
		{
			System.out.print(Places.get(i).toBMos());
		}
		Places=sortPlaces;
		System.out.println("Start: "+ places);
		System.out.println("End: "+ sortPlaces.size());
	}
	private String toStringCST()
	{
		String ret = "Constant Monsters: \n";
		for(int i = 0; i<Places.size(); i++)
		{
			int groupIndex1 = -1;
			int groupIndex2 = -1;
			for(int j = 0; j<Groups.size(); j++)
			{
				if(Groups.get(j).getCode()==Places.get(i).getGroup1()) groupIndex1 = j;
				if(Groups.get(j).getCode()==Places.get(i).getGroup2()) groupIndex2 = j;
			}
			//System.out.println(Areas.get(i).toBrm() + Points.get(Areas.get(i).getPointIndex()).toBrm());
			if(!filter)
			{
				if(Groups.size()==0) break;
				//System.out.print(Places.get(i).toBMos());
				//System.out.println(Places.get(i).getGroup1());
				ret+=Places.get(i).toBMos();
				if(groupIndex1!=-1)
				{
					ret += Groups.get(groupIndex1).bMos();
					for(int j = 0; j<Groups.get(groupIndex1).getObjectCount(); j++)
					{
						if(modCode==-1||Objects.get(Groups.get(groupIndex1).getObjectIndex()+j).getModCode()==modCode)
						{
							ret+= Objects.get(Groups.get(groupIndex1).getObjectIndex()+j).bMos();
						}
						
					}
				}
				else ret+= "\tNull Group: " + Places.get(i).getGroup1() + "\n";
				if(Places.get(i).getGroup1()!=Places.get(i).getGroup2())
				{
					if(groupIndex2!=-1)
					{
						ret += Groups.get(groupIndex2).bMos() ;
						for(int j = 0; j<Groups.get(groupIndex2).getObjectCount(); j++)
						{
							if(modCode==-1||Objects.get(Groups.get(groupIndex2).getObjectIndex()+j).getModCode()==modCode)
							{
								ret+= Objects.get(Groups.get(groupIndex2).getObjectIndex()+j).bMos() ;
							}
						}
					}
					else ret+= "\tNull Group: " + Places.get(i).getGroup2() + "\n";
				}
				//if(Places.get(i).getGroup1()>=50000) System.out.println("hit");
				if(groupIndex1!=-1)UsedGroups.add(Groups.get(groupIndex1));
				if(groupIndex2!=-1)UsedGroups.add(Groups.get(groupIndex2));
				
			}
			else if(Places.get(i).fitsFilter( xMin,  xMax,  zMin,  zMax,  filterOut,  hideFillerSpots))
			{
				ret+=Places.get(i).toBMos();
				if(groupIndex1!=-1)
				{
					ret += Groups.get(groupIndex1).bMos();
					for(int j = 0; j<Groups.get(groupIndex1).getObjectCount(); j++)
					{
						ret+= Objects.get(Groups.get(groupIndex1).getObjectIndex()+j).bMos() ;
					}
				}
				else ret+= "\tNull Group: " + Places.get(i).getGroup1() + "\n";
				if(Places.get(i).getGroup1()!=Places.get(i).getGroup2())
				{
					if(groupIndex2!=-1)
					{
						ret += Groups.get(groupIndex2).bMos() ;
						for(int j = 0; j<Groups.get(groupIndex2).getObjectCount(); j++)
						{
							ret+= Objects.get(Groups.get(groupIndex2).getObjectIndex()+j).bMos() ;
						}
					}
					else ret+= "\tNull Group: " + Places.get(i).getGroup2() + "\n";
				}
				//if(Places.get(i).getGroup1()>=50000) System.out.println("hit");
				if(groupIndex1!=-1)UsedGroups.add(Groups.get(groupIndex1));
				if(groupIndex2!=-1)UsedGroups.add(Groups.get(groupIndex2));
			} else
			{
				if(groupIndex1!=-1)UsedGroups.add(Groups.get(groupIndex1));
				if(groupIndex2!=-1)UsedGroups.add(Groups.get(groupIndex2));
			}
			//System.out.print(ret);
		}
		//System.out.println(Groups.size());
		return ret;
	}
	private String toStringRND()
	{
		String ret = "Random Monsters: \n";
		for(int i = 0; i<AreaDatas.size(); i++)
		{
			ret+= AreaDatas.get(i).toBrm();
			for(int j = Areas.get(i).getPointIndex(); j<Areas.get(i).getPointIndex()+Areas.get(i).pointCount; j++)
			{
				//if there is no filter, or it fits the filter
				if(!filter || Points.get(j).fitsFilter( xMin,  xMax,  zMin,  zMax,  filterOut,  hideFillerSpots))
				{
					ret+= Points.get(j).toBrm();
				}
			}
			
		}
		return ret;
	}
	private String toStringMISC()
	{
		String ret = "Other Groups: \n";
		for(int i = 0; i<Groups.size(); i++)
		{
			//if(Groups.get(i).getCode()>=50000) System.out.println("hit");
			if(UsedGroups.indexOf(Groups.get(i))==-1) 
			{ 
				ret += Groups.get(i).bMos2();
				for(int j = 0; j<Groups.get(i).getObjectCount(); j++)
				{
					if(modCode==-1||Objects.get(Groups.get(i).getObjectIndex()+j).getModCode()==modCode)
					{
						ret+= Objects.get(Groups.get(i).getObjectIndex()+j).bMos2();
						//System.out.println(Objects.get(Groups.get(i).getObjectIndex()+j).bMos());
					}
				}
	
				
			}
		}
		System.out.println(Objects.size());
		return ret;
	}
	public String toString()
	{
		
		UsedGroups = new ArrayList<MobGroup>();
		String ret ="";
		ret += toStringCST();
		ret += toStringRND();
		ret += toStringMISC();
		return ret;
	}
	
	public void setFilterCode(int modCode)
	{
		this.modCode = modCode;
	}

}
