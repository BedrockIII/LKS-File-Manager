package ResourceManagers.MapDatabaseManager.DebugCollision;

import bFM.Data;
import bFM.Utils;

public class DebugCollisionPlacement implements Data
{
	float xPos;
	float yPos;
	float zPos;
	float rotation;
	float xSize;
	float ySize;
	float zSize;
	public DebugCollisionPlacement()
	{
		xPos = 0;
		yPos = 0;
		zPos = 0;
		rotation = 0;
	}
	DebugCollisionPlacement(String line)
	{
		initializeFromLine(line);
	}
	private void initializeFromLine(String line)
	{
		String[] data = line.split(",");
		if(data.length != 7)
		{
			throw new UnsupportedOperationException(String.format("Line has wrong amount of entries. Expected 7, Had: %d \n%s\n", data.length, line));
		}
		xPos = Utils.formatFloat(data[0]);
		yPos = Utils.formatFloat(data[1]);
		zPos = Utils.formatFloat(data[2]);
		rotation = Utils.formatFloat(data[3]);
		xSize = Utils.formatFloat(data[4]);
		ySize = Utils.formatFloat(data[5]);
		zSize = Utils.formatFloat(data[6]);
	}
	public String toString()
	{
		return String.format("PLACE %.1f ,%.1f ,%.1f ,%.1f ,%.1f ,%.1f ,%.1f ;\r\n", xPos, yPos, zPos, rotation, xSize, ySize, zSize);
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
