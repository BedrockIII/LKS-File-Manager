package SystemDataManagers.MenuDB.MapData;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;

import bFM.Data;
import bFM.Utils;

public class MapQuestInfoManager implements Data
{
	ArrayList<MapQuestInfo> MapQuests = new ArrayList<MapQuestInfo>();
	public MapQuestInfoManager(byte[] NameData, byte[] FlagData)
	{
		List<String> Names = Utils.bytesToStrs(NameData);
		ByteBuffer Flags = ByteBuffer.wrap(FlagData);
		String Name = "";
		int Flag1 = -1;
		int Flag2 = -1;
		int Flag3 = -1;
		int Flag4 = -1;
		int Flag5 = -1;
		int Flag6 = -1;
		int Flag7 = -1;
		int Flag8 = -1;
		int Flag9 = -1;
		int Flag10 = -1;
		for(int i = 0; i < Names.size(); i++)
		{
			Name = Names.get(i);
			if(Flags.remaining()>=40)
			{
				Flag1 = Flags.getInt();
				Flag2 = Flags.getInt();
				Flag3 = Flags.getInt();
				Flag4 = Flags.getInt();
				Flag5 = Flags.getInt();
				Flag6 = Flags.getInt();
				Flag7 = Flags.getInt();
				Flag8 = Flags.getInt();
				Flag9 = Flags.getInt();
				Flag10 = Flags.getInt();
			}
			else
			{
				System.err.println(String.format("Map Quest DB has too few Flags. Has %d, Tried to get %d", Flags.limit()/40, i));
				Flag1 = -1;
				Flag2 = -1;
				Flag3 = -1;
				Flag4 = -1;
				Flag5 = -1;
			}
			MapQuests.add(new MapQuestInfo(Name, Flag1, Flag2, Flag3, Flag4, Flag5, 
					Flag6, Flag7, Flag8, Flag9, Flag10));
		}
	}
	public byte[] toNameBytes() 
	{
		byte[] ret = null;
		for(MapQuestInfo m : MapQuests) ret = Utils.mergeArrays(ret, m.toNameBytes());
		return ret;
	}
	public byte[] toFlagBytes() 
	{
		byte[] ret = null;
		for(MapQuestInfo m : MapQuests) ret = Utils.mergeArrays(ret, m.toFlagBytes());
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
	public ArrayList<MapQuestInfo> getQuests()
	{
		return MapQuests;
	}
}
