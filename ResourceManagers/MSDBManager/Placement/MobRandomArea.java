package ResourceManagers.MSDBManager.Placement;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.ArrayList;

import bFM.Data;
import bFM.Utils;

public class MobRandomArea implements Data
{
	int areaCode;
	//int pointCount;
	//int randomPointIndex;
	int zero;//always 0, keeping for the sake of "Why Not"
	MobAreaData areaData;
	ArrayList<MobRandomPoint> Points = new ArrayList<MobRandomPoint>();
	public MobRandomArea(byte[] data, ArrayList<MobRandomPoint> allPoints, ArrayList<MobAreaData> datas)
	{
		areaCode = ByteBuffer.wrap(data).order(ByteOrder.BIG_ENDIAN).getShort(0);
		int pointCount = ByteBuffer.wrap(data).order(ByteOrder.BIG_ENDIAN).getShort(2);
		int randomPointIndex = ByteBuffer.wrap(data).order(ByteOrder.BIG_ENDIAN).getShort(4);
		zero = ByteBuffer.wrap(data).order(ByteOrder.BIG_ENDIAN).getShort(6);
		for(int i = 0; i < pointCount; i ++)
		{
			Points.add(allPoints.get(i + randomPointIndex));
		}
		for(MobAreaData datum : datas)
		{
			if(datum.areaCode==areaCode)
			{
				areaData = datum;
			}
		}
	}
	public MobRandomArea(int code) 
	{
		areaCode = code;
		zero = 0;
		areaData = new MobAreaData(code);
	}
	public byte[] toBytes(int randomPointIndex)
	{
		byte[] ret = Utils.toByteArr(areaCode, 2);
		ret = Utils.mergeArrays(ret, Utils.toByteArr(Points.size(), 2));
		ret = Utils.mergeArrays(ret, Utils.toByteArr(randomPointIndex, 2));
		ret = Utils.mergeArrays(ret, Utils.toByteArr(zero, 2));
		return ret;
	}
	public String toBrm()
	{
		String ret = areaData.toBrm();
		for( MobRandomPoint Point : Points)
		{
			ret += Point.toBrm();
		}
		return ret;
	}
	public String toString()
	{
		return "DAT2 "+areaCode +" ,"+ 0 +" ,"+ 0 +" ,"+zero +"\n";
	}
	public void add(MobAreaData datum) 
	{
		areaData = datum;
	}
	public void add(MobRandomPoint mobRandomPoint) 
	{
		Points.add(mobRandomPoint);
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
		throw new UnsupportedOperationException("getName() should not be called on type " + this.getClass());
	}
	public int getSize() 
	{
		return 8;
	}
	@Override
	public byte[] toBytes() 
	{
		throw new UnsupportedOperationException("toBytes() should not be called on type " + this.getClass());
	}
	public int getPointCount() 
	{
		return Points.size();
	}
	public byte[] getPointBytes() 
	{
		byte[] ret = null;
		for(MobRandomPoint p : Points)
		{
			ret = Utils.mergeArrays(ret, p.toBytes());
		}
		return ret;
	}
	public MobAreaData getAreaData() 
	{
		return areaData;
	}
	public int getCode() 
	{
		return areaCode;
	}
	public ArrayList<MobRandomPoint> getPoints() 
	{
		return Points;
	}
	public void setCode(int code)
	{
		areaCode = code;
		areaData.areaCode = (short) code;
	}
}
