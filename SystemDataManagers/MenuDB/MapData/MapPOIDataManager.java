package SystemDataManagers.MenuDB.MapData;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;

import bFM.Data;
import bFM.Utils;

public class MapPOIDataManager implements Data
{
	ArrayList<MapPOIData> MapPOIs = new ArrayList<MapPOIData>();
	public MapPOIDataManager(byte[] NameData, byte[] MessageData, byte[] ImageData, byte[] PositionData, byte[] FlagData)
	{
		List<String> Names = Utils.bytesToStrs(NameData);
		List<String> Messages = Utils.bytesToStrs(MessageData);
		List<String> Images = Utils.bytesToStrs(ImageData);
		ByteBuffer Positions = ByteBuffer.wrap(PositionData);
		ByteBuffer Flags = ByteBuffer.wrap(FlagData);
		String Name = "";
		String Message = "";
		String Image = "";
		short PosX = 0;
		short PosY = 0;
		int Flag1 = -1;
		int Flag2 = -1;
		int Flag3 = -1;
		int Flag4 = -1;
		int Flag5 = -1;
		int Flag6 = -1;
		for(int i = 0; i < Names.size(); i++)
		{
			Name = Names.get(i);
			if(Messages.size() > i)
			{
				Message = Messages.get(i);
			}
			else
			{
				System.err.println(String.format("Map POI DB has too few Messages. Has %d, Tried to get %d", Images.size(), i));
				Message = "";
			}
			if(Images.size() > i)
			{
				Image = Images.get(i);
			}
			else
			{
				System.err.println(String.format("Map POI DB has too few Images. Has %d, Tried to get %d", Images.size(), i));
				Image = "";
			}
			if(Flags.remaining()>=24)
			{
				Flag1 = Flags.getInt();
				Flag2 = Flags.getInt();
				Flag3 = Flags.getInt();
				Flag4 = Flags.getInt();
				Flag5 = Flags.getInt();
				Flag6 = Flags.getInt();
			}
			else
			{
				System.err.println(String.format("Map POI DB has too few Flags. Has %d, Tried to get %d", Flags.limit()/24, i));
				Flag1 = -1;
				Flag2 = -1;
				Flag3 = -1;
				Flag4 = -1;
				Flag5 = -1;
				Flag6 = -1;
			}
			if(Positions.remaining()>=4)
			{
				PosX = Positions.getShort();
				PosY = Positions.getShort();
			}
			else
			{
				System.err.println(String.format("Map POI DB has too few Position Data. Has %d, Tried to get %d", Flags.limit()/4, i));
				PosX = 0;
				PosY = 0;
			}
			MapPOIs.add(new MapPOIData(Name, Message, Image, PosX, PosY, Flag1, Flag2, Flag3, Flag4, Flag5, Flag6));
		}
	}
	protected byte[] toNameBytes() 
	{
		byte[] ret = null;
		for(MapPOIData m : MapPOIs) ret = Utils.mergeArrays(ret, m.toNameBytes());
		return ret;
	}
	protected byte[] toMessageBytes()
	{
		byte[] ret = null;
		for(MapPOIData m : MapPOIs) ret = Utils.mergeArrays(ret, m.toMessageBytes());
		return ret;
	}
	protected byte[] toImageBytes() 
	{
		byte[] ret = null;
		for(MapPOIData m : MapPOIs) ret = Utils.mergeArrays(ret, m.toImageBytes());
		return ret;
	}
	protected byte[] toPositionBytes() 
	{
		byte[] ret = null;
		for(MapPOIData m : MapPOIs) ret = Utils.mergeArrays(ret, m.toPositionBytes());
		return ret;
	}
	protected byte[] toFlagBytes() 
	{
		byte[] ret = null;
		for(MapPOIData m : MapPOIs) ret = Utils.mergeArrays(ret, m.toFlagBytes());
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
	public ArrayList<MapPOIData> getPOIs()
	{
		return MapPOIs;
	}
}
