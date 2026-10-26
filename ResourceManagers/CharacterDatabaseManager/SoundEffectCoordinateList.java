package ResourceManagers.CharacterDatabaseManager;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;

import bFM.OpenedFile;

public class SoundEffectCoordinateList implements OpenedFile
{
	ArrayList<SoundEffectCoordinate> list = new ArrayList<SoundEffectCoordinate>();
	protected SoundEffectCoordinateList(byte[] data)
	{
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
		for(String line : lines)
		{
			if(line.indexOf("Sound Effect Coordinate")!=-1)
			{
				list.add(new SoundEffectCoordinate(line));
			}
		}
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
	public boolean equals(String name) 
	{
		throw new UnsupportedOperationException("equals() should not be called on type " + this.getClass());
	}
	public void setName(String name) 
	{
		throw new UnsupportedOperationException("setName(String name) should not be called on type " + this.getClass());
	}
	public String getName() 
	{
		throw new UnsupportedOperationException("getName() should not be called on type " + this.getClass());
	}
	public void setData(byte[] data) 
	{
		throw new UnsupportedOperationException("setData(byte[] data) should not be called on type " + this.getClass());
	}
	public int getSize() 
	{
		throw new UnsupportedOperationException("getSize() should not be called on type " + this.getClass());
	}
}
