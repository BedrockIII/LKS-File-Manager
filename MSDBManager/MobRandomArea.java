package MSDBManager;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

public class MobRandomArea 
{
	int areaCode;
	int pointCount;
	int randomPointIndex;
	int zero;//always 0, keeping for the sake of "Why Not"
	public MobRandomArea(byte[] data)
	{
		areaCode = ByteBuffer.wrap(data).order(ByteOrder.BIG_ENDIAN).getShort(0);
		pointCount = ByteBuffer.wrap(data).order(ByteOrder.BIG_ENDIAN).getShort(2);
		randomPointIndex = ByteBuffer.wrap(data).order(ByteOrder.BIG_ENDIAN).getShort(4);
		zero = ByteBuffer.wrap(data).order(ByteOrder.BIG_ENDIAN).getShort(6);
	}
	public MobRandomArea(int code, int pointCount, int index) 
	{
		areaCode = code;
		this.pointCount = pointCount;
		randomPointIndex = index;
		zero = 0;
	}
	public byte[] toBytes()
	{
		byte[] ret = toByteArr(areaCode, 2);
		ret = mergeArrays(ret, toByteArr(pointCount, 2));
		ret = mergeArrays(ret, toByteArr(randomPointIndex, 2));
		ret = mergeArrays(ret, toByteArr(zero, 2));
		return ret;
	}
	public String toBrm()
	{
		return "Random Monster: "+areaCode +", "+pointCount +", ";
	}
	public String toString()
	{
		return "DAT2 "+areaCode +" ,"+pointCount +" ,"+randomPointIndex +" ,"+zero +"\n";
	}
	private byte[] toByteArr(int input, int arrLength) 
	{
		byte[] ret = new byte[arrLength];
		for(int i = 1; i<=arrLength; i++)
		{
			ret[arrLength-i] = (byte) (input%256);
			input/=256;
			//System.out.println(ret[arrLength-i]);
			
		}
		return ret;
	}
	private static byte[] mergeArrays(byte[] main, byte[] add)
	{
		if(add==null) return main;
		if(main==null) return add;
		byte[] ret = new byte[main.length+add.length];
		for(int i = 0; i < main.length; i++)
		{
			ret[i] = main[i];
		}
		for(int i = 0; i < add.length; i++)
		{
			ret[i+main.length] = add[i];
		}
		return ret;
	}
	public int getPointIndex()  
	{
		return randomPointIndex;
	}
	
}
