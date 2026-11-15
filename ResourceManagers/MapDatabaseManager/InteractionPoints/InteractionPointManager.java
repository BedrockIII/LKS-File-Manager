package ResourceManagers.MapDatabaseManager.InteractionPoints;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

import bFM.Data;
import bFM.Utils;

public class InteractionPointManager implements Data
{
	ArrayList<InteractionPointBuildingDefinition> Buildings = new ArrayList<InteractionPointBuildingDefinition>();
	public InteractionPointManager()
	{
		//Nothing to do
	}
	public InteractionPointManager(byte[] data)
	{
		initializeFromLines(Utils.bytesToStrs(data));
	}
	private void initializeFromLines(List<String> lines)
	{
		InteractionPointBuildingDefinition lastBuilding = null;
		InteractionPointList lastList = null;
		InteractionPoint lastPoint;
		for(String line : lines)
		{
			if(line.indexOf("KEY") != -1)
			{
				lastBuilding = new InteractionPointBuildingDefinition(line);
				Buildings.add(lastBuilding);
			}
			else if(line.indexOf("LST") != -1)
			{
				if(lastBuilding == null)
				{
					throw new IllegalArgumentException("List is defined before Key\n" + line + "\n");
				}
				lastList = new InteractionPointList(line);
				lastBuilding.Lists.add(lastList);
			}
			else if(line.indexOf("PNT") != -1)
			{
				if(lastList == null)
				{
					throw new IllegalArgumentException("Point is defined before List\n" + line + "\n");
				}
				lastPoint = new InteractionPoint(line);
				lastList.Points.add(lastPoint);
			}
		}
	}
	public String toString()
	{
		String ret = "";
		for(InteractionPointBuildingDefinition Building : Buildings)
		{
			ret += Building.toString();
		}
		return ret.stripTrailing();
	}
	public void setData(byte[] data) 
	{
		initializeFromLines(Utils.bytesToStrs(data));
	}
	public byte[] toBytes() 
	{
		try
		{
			return Utils.encodeStringToBytes(toString(), Charset.forName("ASCII"));
		} catch (Exception e)
		{
			//To String should always be english ascii characters
			throw new IllegalArgumentException("This Should Never Happen");
		}
	}
	public int getSize() 
	{
		return toBytes().length;
	}
}
