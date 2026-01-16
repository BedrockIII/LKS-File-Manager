package SystemDataManagers;

import java.nio.ByteBuffer;

public class RandomQuestLocation 
{
	String Name = "Place";
	int xPos = -1;
	int yPos = -1;
	int zPos = -1;
	public RandomQuestLocation(String name, int x, int y, int z)
	{
		Name = name;
		xPos = x;
		yPos = y;
		zPos = z;
	}
	public RandomQuestLocation(String nameLine)
	{
		
		String name = "";
		String lineAfterHeader = nameLine.substring(nameLine.indexOf(">"));
		int startIndex = lineAfterHeader.indexOf('\"');
		int endIndex = lineAfterHeader.lastIndexOf('\"');
		if(startIndex!=-1&&endIndex!=-1)
		{
			name = lineAfterHeader.substring(startIndex+1, endIndex);
		}
		else
		{
			name = lineAfterHeader;
		}
		Name = name;
		xPos = -1;
		yPos = -1;
		zPos = -1;
	}
	public String toString()
	{
		String ret = "<Position Name> \"" + Name + "\"\n";
		ret += "<Position Coordinates> (" + xPos + ", " + yPos + ", " + zPos + ")\n";
		return ret;
	}
	public void setCoords(String line)
	{
		String validChars = "-1234567890";
		String num = "0";
		xPos = -1;
		yPos = -1;
		zPos = -1;
		if(line.indexOf('(')!=-1&&line.indexOf(')')!=-1)
		{
			for(int i = line.indexOf("("); i < line.length(); i++)
			{
				if(validChars.indexOf(line.charAt(i))!=-1)
				{
					num += line.charAt(i);
				}
				else if(line.charAt(i)==',')
				{
					if(xPos == -1)
					{
						xPos = Integer.parseInt(num);
						
					}
					else
					{
						yPos = Integer.parseInt(num);
					}
					num = "0";
				}
				else if(line.charAt(i)==')')
				{
					zPos = Integer.parseInt(num);
					return;
				}
			}
		}
		else
		{
			bFM.Utils.DebugPrint("Line Formatting Incorrect");
			return;
		}
	}
	public byte[] getName()
	{
		return bFM.Utils.mergeArrays(Name.getBytes(), new byte[1]);
	}
	public String getNameString()
	{
		return Name;
	}
	public byte[] getCoords()
	{
		byte[] ret = ByteBuffer.allocate(2).putShort((short) xPos).array();
		return bFM.Utils.mergeArrays(ret, bFM.Utils.mergeArrays(ByteBuffer.allocate(2).putShort((short) yPos).array(),ByteBuffer.allocate(2).putShort((short) zPos).array()));
	}
	public RandomQuestLocation() 
	{
		Name = "Place";
		xPos = -1;
		yPos = -1;
		zPos = -1;
	}
}
