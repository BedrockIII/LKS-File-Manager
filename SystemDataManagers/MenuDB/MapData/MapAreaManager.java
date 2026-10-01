package SystemDataManagers.MenuDB.MapData;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;

import bFM.Data;
import bFM.Utils;

public class MapAreaManager implements Data
{
	ArrayList<MapArea> MapAreas = new ArrayList<MapArea>();
	public MapAreaManager(byte[] NameData, byte[] ImageData, byte[] FlagData)
	{
		List<String> Names = Utils.bytesToStrs(NameData);
		List<String> Images = Utils.bytesToStrs(ImageData);
		ByteBuffer Flags = ByteBuffer.wrap(FlagData);
		String Name = "";
		String Image = "";
		int Flag1 = -1;
		int Flag2 = -1;
		int Flag3 = -1;
		int Flag4 = -1;
		int Flag5 = -1;
		for(int i = 0; i < Names.size(); i++)
		{
			Name = Names.get(i);
			if(Images.size() > i)
			{
				Image = Images.get(i);
			}
			else
			{
				System.err.println(String.format("Map Area DB has too few Images. Has %d, Tried to get %d", Images.size(), i));
				Image = "";
			}
			if(Flags.remaining()>20)
			{
				Flag1 = Flags.getInt();
				Flag2 = Flags.getInt();
				Flag3 = Flags.getInt();
				Flag4 = Flags.getInt();
				Flag4 = Flags.getInt();
			}
			else
			{
				System.err.println(String.format("Map Area DB has too few Flags. Has %d, Tried to get %d", Flags.limit()/20, i));
				Flag1 = -1;
				Flag2 = -1;
				Flag3 = -1;
				Flag4 = -1;
				Flag5 = -1;
			}
			MapAreas.add(new MapArea(Name, Image, Flag1, Flag2, Flag3, Flag4, Flag5));
		}
	}
	public byte[] toNameBytes() 
	{
		byte[] ret = null;
		for(MapArea m : MapAreas) ret = Utils.mergeArrays(ret, m.toNameBytes());
		return ret;
	}
	public byte[] toImageBytes() 
	{
		byte[] ret = null;
		for(MapArea m : MapAreas) ret = Utils.mergeArrays(ret, m.toImageBytes());
		return ret;
	}
	public byte[] toFlagBytes() 
	{
		byte[] ret = null;
		for(MapArea m : MapAreas) ret = Utils.mergeArrays(ret, m.toFlagBytes());
		return ret;
	}
	public void setData(byte[] data) 
	{
		throw new UnsupportedOperationException("setData(byte[] data) should not be called on type " + this.getClass());
	}
	public byte[] toBytes() 
	{
		throw new UnsupportedOperationException("toBytes() should not be called on type " + this.getClass());
	}
	public int getSize() 
	{
		throw new UnsupportedOperationException("getSize() should not be called on type " + this.getClass());
	}
}
