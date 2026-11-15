package ResourceManagers.MapDatabaseManager.GroundConfiguration;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

import bFM.Data;
import bFM.Utils;

public class GroundMemoryAssignmentManager implements Data
{
	ArrayList<GroundMemoryAssignment> WorldGrids = new ArrayList<GroundMemoryAssignment>();
	public GroundMemoryAssignmentManager()
	{
		
	}
	public GroundMemoryAssignmentManager(byte[] data)
	{
		initializeFromLines(Utils.bytesToStrs(data));
	}
	public GroundMemoryAssignmentManager(List<String> lines)
	{
		initializeFromLines(lines);
	}
	public void setData(byte[] data) 
	{
		initializeFromLines(Utils.bytesToStrs(data));
	}
	
	private void initializeFromLines(List<String> lines)
	{
		GroundMemoryAssignment lastGrid = null;
		for(String line : lines)
		{
			if(line.indexOf("DEF") != -1)
			{
				lastGrid = new GroundMemoryAssignment(line);
				WorldGrids.add(lastGrid);
			}
			if(line.indexOf("MEM") != -1)
			{
				if(lastGrid == null)
				{
					throw new IllegalArgumentException("Memory Defined Before Grid!!!");
				}
				lastGrid.setMemory(Utils.formatInt(line));
			}
			if(line.indexOf("SEA") != -1)
			{
				if(lastGrid == null)
				{
					throw new IllegalArgumentException("Sea Defined Before Grid!!!");
				}
				lastGrid.setSea(true);
			}
		}
	}
	public String toString()
	{
		String ret = "";
		for(GroundMemoryAssignment Grid : WorldGrids)
		{
			ret += Grid.toString();
		}
		return ret;
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
