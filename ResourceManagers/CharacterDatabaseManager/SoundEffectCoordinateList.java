package ResourceManagers.CharacterDatabaseManager;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;

import bFM.Data;
import bFM.GenericFile;

public class SoundEffectCoordinateList extends GenericFile
{
	ArrayList<SoundEffectCoordinate> list = new ArrayList<SoundEffectCoordinate>();
	protected SoundEffectCoordinateList(byte[] data)
	{
		name = "Sound Effect Coordinate List";
		ByteBuffer bytes = ByteBuffer.wrap(data);
		for(int i = 4; i < data.length; i+=8)
		{
			list.add(new SoundEffectCoordinate(bytes.slice(i, 8)));
			//System.out.println(list.get(list.size()-1));
			//list.get(list.size()-1).printUniques();
		}
	}
	protected SoundEffectCoordinateList(List<String> lines) 
	{
		name = "Sound Effect Coordinate List";
		for(String line : lines)
		{
			if(line.indexOf("Sound Effect Coordinate")!=-1)
			{
				list.add(new SoundEffectCoordinate(line));
			}
		}
	}
	protected SoundEffectCoordinateList() 
	{
		name = "Sound Effect Coordinate List";
	}
	public String toString()
	{
		String ret = "";
		for(SoundEffectCoordinate sec : list)
		{
			if(ret.length()>0) ret += '\n';
			ret += sec.toString();
		}
		return ret;
	}
	public byte[] toBytes()
	{
		byte[] ret = bFM.Utils.longToBytes(list.size(), 2);
		ret = bFM.Utils.mergeArrays(ret, new byte[2]);
		for(SoundEffectCoordinate sec : list)
		{
			ret = bFM.Utils.mergeArrays(ret, sec.toBytes());
		}
		return ret;
	}
	
	
	public class SoundEffectCoordinate implements Data
	{
		byte num0 = 0; //This is wrong, each byte is a number
		byte num1 = 0;
		byte num2 = 0;
		byte num3 = 0;
		byte num4 = 0;
		byte num5 = 0;
		byte num6 = 0;
		byte num7 = 0;
		public SoundEffectCoordinate(ByteBuffer data) 
		{
			num0 = data.get(0);
			num1 = data.get(1);
			num2 = data.get(2);
			num3 = data.get(3);
			num4 = data.get(4);
			num5 = data.get(5);
			num6 = data.get(6);
			num7 = data.get(7);
		}
		public SoundEffectCoordinate(String line) 
		{
			String[] data = bFM.Utils.toStrArr(line);
			num0 = (byte) bFM.Utils.strToInt(data[0]);
			num1 = (byte) bFM.Utils.strToInt(data[1]);
			num2 = (byte) bFM.Utils.strToInt(data[2]);
			num3 = (byte) bFM.Utils.strToInt(data[3]);
			num4 = (byte) bFM.Utils.strToInt(data[4]);
			num5 = (byte) bFM.Utils.strToInt(data[5]);
			num6 = (byte) bFM.Utils.strToInt(data[6]);
			num7 = (byte) bFM.Utils.strToInt(data[7]);
		}
		public SoundEffectCoordinate() 
		{
			//Do Nothing
		}
		public String toString()
		{
			return "Sound Effect Coordinate: " + num1 + ", " + num2 + ", " + num3 + ", " + num4 + ", " + num5 + ", " + num6 + ", " + num7 + "\n";
			
		}
		//8 bytes
		public boolean equals(String name) 
		{
			throw new UnsupportedOperationException("equals() should not be called on type " + this.getClass());
		}
		public void setData(byte[] data) 
		{
			throw new UnsupportedOperationException("setData(byte[] data) should not be called on type " + this.getClass());
		}
		public byte[] toBytes() 
		{
			ByteBuffer ret = ByteBuffer.allocate(8);
			ret.put(0, num0);
			ret.put(1, num1);
			ret.put(2, num2);
			ret.put(3, num3);
			ret.put(4, num4);
			ret.put(5, num5);
			ret.put(6, num6);
			ret.put(7, num7);
			return ret.array();
		}
		public void setName(String name) 
		{
			throw new UnsupportedOperationException("setName(String name) should not be called on type " + this.getClass());
		}
		public String getName() 
		{
			return "Coordinate";
		}
		public int getSize()
		{
			throw new UnsupportedOperationException("getSize() should not be called on type " + this.getClass());
		}
		
		public int getNum1()
		{
			return num1;
		}
		public void setNum1(int num)
		{
			num1 = (byte) num;
		}

		public int getNum2()
		{
			return num2;
		}
		public void setNum2(int num)
		{
			num2 = (byte) num;
		}

		public int getNum3()
		{
			return num3;
		}
		public void setNum3(int num)
		{
			num3 = (byte) num;
		}

		public int getNum4()
		{
			return num4;
		}
		public void setNum4(int num)
		{
			num4 = (byte) num;
		}

		public int getNum5()
		{
			return num5;
		}
		public void setNum5(int num)
		{
			num5 = (byte) num;
		}

		public int getNum6()
		{
			return num6;
		}
		public void setNum6(int num)
		{
			num6 = (byte) num;
		}

		public int getNum7()
		{
			return num7;
		}
		public void setNum7(int num)
		{
			num7 = (byte) num;
		}
		public int getNum0() 
		{
			return num0;
		}
		public void setNum0(int num)
		{
			num0=(byte) num;
		}
	}
	public ArrayList<SoundEffectCoordinate> getObjects() 
	{
		return list;
	}
	public void addCoordinate() 
	{
		list.add(new SoundEffectCoordinate());
	}
	public SoundEffectCoordinate getLastObject() 
	{
		return list.get(list.size()-1);
	}
	public int getAmountOfCoordinates() 
	{
		return list.size();
	}
	public void removeCoordinate(SoundEffectCoordinate file) 
	{
		list.remove(file);
	}

}
