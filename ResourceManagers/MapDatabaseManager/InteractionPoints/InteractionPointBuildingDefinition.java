package ResourceManagers.MapDatabaseManager.InteractionPoints;

import java.util.ArrayList;

import bFM.Data;
import bFM.Utils;

public class InteractionPointBuildingDefinition implements Data
{
	int key;
	ArrayList<InteractionPointList> Lists = new ArrayList<InteractionPointList>();
	public InteractionPointBuildingDefinition()
	{
		key = -1;
	}
	InteractionPointBuildingDefinition(String line)
	{
		initializeFromLine(line);
	}
	private void initializeFromLine(String line)
	{
		String[] data = line.split(",");
		if(data.length != 1)
		{
			throw new UnsupportedOperationException(String.format("Line has wrong amount of entries. Expected 1, Had: %d \n%s\n", data.length, line));
		}
		key = Utils.formatInt(data[0]);
	}
	public String toString()
	{
		String ret = String.format("KEY %d;\r\n", key);
		for(InteractionPointList List : Lists)
		{
			ret += List.toString();
		}
		return ret;
	}
	public void setData(byte[] data) 
	{
		initializeFromLine(Utils.decodeBytesToString(data));
	}
	public byte[] toBytes() 
	{
		throw new UnsupportedOperationException("toBytes() should not be called on type " + this.getClass());
	}
	public int getSize() 
	{
		return toBytes().length;
	}
}
