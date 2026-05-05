package ResourceManagers.CharacterDatabaseManager;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;


class JoinBinList
{
	ArrayList<join> list = new ArrayList<join>();
	protected JoinBinList(byte[] data)
	{
		ByteBuffer bytes = ByteBuffer.wrap(data);
		for(int i = 8; i < data.length; i+=32)
		{
			list.add(new join(bytes.slice(i, 32)));
			//System.out.println(list.get(list.size()-1));
			//list.get(list.size()-1).printUniques();
		}
	}
	public JoinBinList(List<String> lines) 
	{
		for(String line : lines)
		{
			if(line.indexOf("Join")!=-1)
			{
				list.add(new join(line));
			}
		}
	}
	public String toString()
	{
		String ret = "";
		for(join Join : list)
		{
			if(ret.length()>0) ret += '\n';
			ret += Join.toString();
		}
		return ret;
	}
	protected byte[] toBytes()
	{
		byte[] ret = bFM.Utils.longToBytes(list.size(), 2);
		ret = bFM.Utils.mergeArrays(ret, new byte[6]);
		for(join Join : list)
		{
			ret = bFM.Utils.mergeArrays(ret, Join.toBytes());
			ret = bFM.Utils.mergeArrays(ret, new byte[4]);
		}
		return ret;
	}
	private class join
	{
		int index = 0;
		int num1 = 0;
		int num2 = 0;
		int num3 = 0;
		int num4 = 0;
		int num5 = 0;
		int num6 = 0;
		int num7 = -1;
		int num8 = -1;
		int num9 = -1;
		float xPos = 0;
		float yPos = 0;
		float zPos = 0;
		private join(ByteBuffer data) 
		{
			index = data.getShort(0);
			num1 = data.get(2);
			num2 = data.get(3);
			num3 = data.get(4);
			num4 = data.get(5);
			num5 = data.getShort(6);
			num6 = data.getShort(8);
			num7 = data.getShort(10);
			num8 = data.getShort(12);
			num9 = data.getShort(14);
			xPos = data.getFloat(16);
			yPos = data.getFloat(20);
			zPos = data.getFloat(24);
		}
		private join(String line)
		{
			arrayIndex = 0;
			String[] data = bFM.Utils.toStrArr(line);
			index = bFM.Utils.strToInt(readNextValue(data));
			num1 = bFM.Utils.strToInt(readNextValue(data));
			num2 = bFM.Utils.strToInt(readNextValue(data));
			num3 = bFM.Utils.strToInt(readNextValue(data));
			num4 = bFM.Utils.strToInt(readNextValue(data));
			num5 = bFM.Utils.strToInt(readNextValue(data));
			num6 = bFM.Utils.strToInt(readNextValue(data));
			num7 = bFM.Utils.strToInt(readNextValue(data));
			num8 = bFM.Utils.strToInt(readNextValue(data));
			num9 = bFM.Utils.strToInt(readNextValue(data));
			xPos = Float.parseFloat(readNextValue(data));
			yPos = Float.parseFloat(readNextValue(data));
			zPos = Float.parseFloat(readNextValue(data));
		}
		private byte[] toBytes() 
		{
			byte[] ret = bFM.Utils.longToBytes(index, 2);
			ret = bFM.Utils.mergeArrays(ret, new byte[]{(byte)num1, (byte)num2, (byte)num3, (byte)num4});
			ret = bFM.Utils.mergeArrays(ret, bFM.Utils.toByteArr(num5, 2));
			ret = bFM.Utils.mergeArrays(ret, bFM.Utils.toByteArr(num6, 2));
			ret = bFM.Utils.mergeArrays(ret, bFM.Utils.toByteArr(num7, 2));
			ret = bFM.Utils.mergeArrays(ret, bFM.Utils.toByteArr(num8, 2));
			ret = bFM.Utils.mergeArrays(ret, bFM.Utils.toByteArr(num9, 2));
			ret = bFM.Utils.mergeArrays(ret, ByteBuffer.allocate(4).putFloat(xPos).array());
			ret = bFM.Utils.mergeArrays(ret, ByteBuffer.allocate(4).putFloat(yPos).array());
			ret = bFM.Utils.mergeArrays(ret, ByteBuffer.allocate(4).putFloat(zPos).array());
			return ret;
		}
		int arrayIndex = 0;
		private String readNextValue(String[] data)
		{
			if(data.length<=arrayIndex) return "";
			String ret = data[arrayIndex];
			arrayIndex++;
			return ret;
		}
		public String toString()
		{
			String ret = "Join " + index + ", " + num1 + ", " + num2 + ", " + num3 + ", " + num4 + ", " + num5 + ", " + 
					 num6 + ", "+ num7 + ", " + num8 + ", " + num9 + ", " + xPos + ", " + yPos + ", " + zPos;
			return ret;
		}
	}
}
