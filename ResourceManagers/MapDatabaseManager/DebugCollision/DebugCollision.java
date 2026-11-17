package ResourceManagers.MapDatabaseManager.DebugCollision;

import java.util.ArrayList;

import bFM.Data;

public class DebugCollision implements Data
{
	//DAT
	int buildingCode = -1;
	int num1 = 1;
	int num2 = -1;
	int num3 = -1;
	int num4 = 0;
	int num5 = 0;
	//int placeCount = -1;
	ArrayList<DebugCollisionPlacement> placements = new ArrayList<DebugCollisionPlacement>();
	public DebugCollision(String DatList)
	{
		String[] datLine = bFM.Utils.toStrArr(DatList);
		if(datLine.length!=7)return;
		
		buildingCode = bFM.Utils.strToInt(datLine[0]);
		
		num1 = bFM.Utils.strToInt(datLine[1]);
		num2 = bFM.Utils.strToInt(datLine[2]);
		num3 = bFM.Utils.strToInt(datLine[3]);
		num4 = bFM.Utils.strToInt(datLine[4]);
		num5 = bFM.Utils.strToInt(datLine[5]);
	}
	public void addLine(String list)
	{
		if(list.indexOf("PLACE ")!=-1)
		{
			placements.add(new DebugCollisionPlacement(list));
		}
	}
	public String toString()
	{// + "," + 
		String ret = "DAT " + buildingCode + "," + num1 + "," + num2 + "," + num3 + "," + num4 + "," + num5 + "," + placements.size() + ";\r\n";
		for(DebugCollisionPlacement place : placements)
			ret += place.toString();
		return ret;
	}
	public void setData(byte[] data) 
	{
		throw new UnsupportedOperationException("setData(byte[] data) should not be called on type " + this.getClass());
	}
	public byte[] toBytes() 
	{
		throw new UnsupportedOperationException("toBytes() should not be called on type " + this.getClass());
	}
	public int getSize() 
	{
		throw new UnsupportedOperationException("getSize() should not be called on type " + this.getClass());
	}
}
