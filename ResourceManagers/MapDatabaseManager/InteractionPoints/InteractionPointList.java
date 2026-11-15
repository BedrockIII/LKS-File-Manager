package ResourceManagers.MapDatabaseManager.InteractionPoints;

import java.util.ArrayList;

import bFM.Data;
import bFM.Utils;

public class InteractionPointList implements Data
{
	int index;
	ArrayList<InteractionPoint> Points = new ArrayList<InteractionPoint>();
	public InteractionPointList()
	{
		index = -1;
	}
	InteractionPointList(String line)
	{
		initializeFromLine(line);
	}
	private void initializeFromLine(String line)
	{
		String[] data = line.split(",");
		if(data.length != 2)
		{
			throw new UnsupportedOperationException(String.format("Line has wrong amount of entries. Expected 2, Had: %d \n%s\n", data.length, line));
		}
		index = Utils.formatInt(data[0]);
	}
	public String toString()
	{
		String ret = String.format("LST %d,%d;\r\n", index, Points.size());
		for(InteractionPoint Point : Points)
		{
			ret += Point.toString();
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
