package ResourceManagers.MapDatabaseManager.InteractionPoints;

import bFM.Data;
import bFM.Utils;

public class InteractionPoint implements Data
{
	float xPos;
	float yPos;
	float zPos;
	float rotation;
	public InteractionPoint()
	{
		xPos = 0;
		yPos = 0;
		zPos = 0;
		rotation = 0;
	}
	InteractionPoint(String line)
	{
		initializeFromLine(line);
	}
	private void initializeFromLine(String line)
	{
		String[] data = line.split(",");
		if(data.length != 4)
		{
			throw new UnsupportedOperationException(String.format("Line has wrong amount of entries. Expected 4, Had: %d \n%s\n", data.length, line));
		}
		xPos = Utils.formatFloat(data[0]);
		yPos = Utils.formatFloat(data[1]);
		zPos = Utils.formatFloat(data[2]);
		try
		{
			rotation = Utils.formatFloat(data[3]);
		}
		catch (NumberFormatException e)
		{
			rotation = 0;
		}
	}
	public String toString()
	{
		return String.format("PNT %.2f ,%.2f ,%.2f ,%.2f ;\r\n", xPos, yPos, zPos, rotation);
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
