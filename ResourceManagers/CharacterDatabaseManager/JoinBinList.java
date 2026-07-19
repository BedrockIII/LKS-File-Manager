package ResourceManagers.CharacterDatabaseManager;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;

import bFM.Data;
import bFM.GenericFile;


public class JoinBinList extends GenericFile
{
	ArrayList<join> list = new ArrayList<join>();
	protected JoinBinList(byte[] data)
	{
		name = "Character Join List";
		ByteBuffer bytes = ByteBuffer.wrap(data);
		for(int i = 8; i < data.length; i+=32)
		{
			list.add(new join(bytes.slice(i, 32)));
			//System.out.println(list.get(list.size()-1));
			//list.get(list.size()-1).printUniques();
		}
	}
	protected JoinBinList(List<String> lines) 
	{
		name = "Character Join List";
		for(String line : lines)
		{
			if(line.indexOf("Join")!=-1)
			{
				list.add(new join(line));
			}
		}
	}
	protected JoinBinList() 
	{
		name = "Character Join List";
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
	public byte[] toBytes()
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
	public class join implements Data
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
		public join(int index) 
		{
			this.index = index;
		}
		public byte[] toBytes() 
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
		public boolean equals(String name) 
		{
			throw new UnsupportedOperationException("equals() should not be called on type " + this.getClass());
		}
		public void setData(byte[] data) 
		{
			throw new UnsupportedOperationException("setData(byte[] data) should not be called on type " + this.getClass());
		}
		public void setName(String name) 
		{
			throw new UnsupportedOperationException("setName(String name) should not be called on type " + this.getClass());
		}
		public String getName() 
		{
			return "Join " + index;
		}
		public int getSize() 
		{
			return 28;
		}
		public int getIndex() 
		{
			return index;
		}
		public void setIndex(int num) 
		{
			index = num;
		}
		
		public int getNum1() 
		{
			return num1;
		}
		public void setNum1(int num) 
		{
			num1 = num;
		}

		public int getNum2() 
		{
			return num2;
		}
		public void setNum2(int num) 
		{
			num2 = num;
		}

		public int getNum3() 
		{
			return num3;
		}
		public void setNum3(int num) 
		{
			num3 = num;
		}

		public int getNum4() 
		{
			return num4;
		}
		public void setNum4(int num) 
		{
			num4 = num;
		}

		public int getNum5() 
		{
			return num5;
		}
		public void setNum5(int num) 
		{
			num5 = num;
		}

		public int getNum6() 
		{
			return num6;
		}
		public void setNum6(int num) 
		{
			num6 = num;
		}

		public int getNum7() 
		{
			return num7;
		}
		public void setNum7(int num) 
		{
			num7 = num;
		}

		public int getNum8() 
		{
			return num8;
		}
		public void setNum8(int num) 
		{
			num8 = num;
		}

		public int getNum9() 
		{
			return num9;
		}
		public void setNum9(int num) 
		{
			num9 = num;
		}

		public float getXPos() 
		{
			return xPos;
		}
		public void setXPos(float num) 
		{
			xPos = num;
		}

		public float getYPos() 
		{
			return yPos;
		}
		public void setYPos(float num) 
		{
			yPos = num;
		}

		public float getZPos() 
		{
			return zPos;
		}
		public void setZPos(float num) 
		{
			zPos = num;
		}
	}
	public ArrayList<join> getObjects() 
	{
		return list;
	}
	public void addJob(int index) 
	{
		list.add(new join(index));
	}
	public join getLastObject() 
	{
		return list.get(list.size()-1);
	}
	public int getAmountOfJoins() 
	{
		return list.size();
	}
	public void removeJoin(join file) 
	{
		int code = file.getIndex();
		for(int i = list.size()-1; i >= 0; i--)
		{
			if(list.get(i).getIndex() == code)
			{
				list.remove(i);
			}
		}
	}
}
