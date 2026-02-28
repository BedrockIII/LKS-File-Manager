package MSDBManager;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.ArrayList;
import java.util.Arrays;

public class MobRandomPointList 
{
	short startNum1 = 0;//first 2 bytes,always 0001
	short startNum2 = 0;//next 2 bytes amount of things in list
	ArrayList<MobRandomPoint> rp = new ArrayList<MobRandomPoint>();
	public MobRandomPointList(byte[] data)
	{
		startNum1 = ByteBuffer.wrap(data).order(ByteOrder.BIG_ENDIAN).getShort();
		startNum2 = ByteBuffer.wrap(data).order(ByteOrder.BIG_ENDIAN).getShort(2);
		for(int i = 4; i<data.length; i+=20)
		{
			rp.add(new MobRandomPoint(Arrays.copyOfRange(data, i, i+20)));
		}
	}
	public String toString()
	{
		String ret = "Num " + startNum2 + "\n";
		for(int i = 0; i<rp.size(); i++)
		{
			ret = ret + rp.get(i).toString();
		}
		return ret;
	}
	public byte[] toBytes()
	{
		byte[] ret = ByteBuffer.allocate(4).putShort((short)1).array();
		ret = mergeArrays(ret, ByteBuffer.allocate(4).putFloat((short)rp.size()).array());
		for(int i = 0; i< rp.size();i++)
		{
			ret = mergeArrays(ret, rp.get(i).toBytes());
		}
		return ret;
	}
	public static byte[] mergeArrays(byte[] main, byte[] add)
	{
		if(add==null) return main;
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
}
