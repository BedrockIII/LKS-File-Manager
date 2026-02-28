package MSDBManager;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

public class MobAreaData 
{
	short areaCode;
	short num1;
	short groupCode1;
	short groupCode2;
	short groupCode3;
	short groupCode4;
	public MobAreaData(byte[] data)
	{
		areaCode = ByteBuffer.wrap(data).order(ByteOrder.BIG_ENDIAN).getShort(0);
		num1 = ByteBuffer.wrap(data).order(ByteOrder.BIG_ENDIAN).getShort(2);
		groupCode1 = ByteBuffer.wrap(data).order(ByteOrder.BIG_ENDIAN).getShort(4);
		groupCode2 = ByteBuffer.wrap(data).order(ByteOrder.BIG_ENDIAN).getShort(6);
		groupCode3 = ByteBuffer.wrap(data).order(ByteOrder.BIG_ENDIAN).getShort(8);
		groupCode4 = ByteBuffer.wrap(data).order(ByteOrder.BIG_ENDIAN).getShort(10);
	}
	public MobAreaData(short code, short num1, short group1, short group2, short group3, short group4) 
	{
		areaCode = code;
		this.num1 = num1;
		groupCode1 = group1;
		groupCode2 = group2;
		groupCode3 = group3;
		groupCode4 = group4;
	}
	public String toBrm()
	{
		return "Random Area:  "+areaCode +" ,"+num1 +" ,"+groupCode1 +" ,"+groupCode2 +" ,"+groupCode3 +" ,"+groupCode4 +";\n";
	}
	public String toString()
	{
		return "DAT "+areaCode +" ,"+num1 +" ,"+groupCode1 +" ,"+groupCode2 +" ,"+groupCode3 +" ,"+groupCode4 +"\n";
	}
	public byte[] toBytes()
	{
		byte[] ret = toByteArr(areaCode, 2);
		ret = mergeArrays(ret, toByteArr(num1, 2));
		ret = mergeArrays(ret, toByteArr(groupCode1, 2));
		ret = mergeArrays(ret, toByteArr(groupCode2, 2));
		ret = mergeArrays(ret, toByteArr(groupCode3, 2));
		ret = mergeArrays(ret, toByteArr(groupCode4, 2));
		return ret;
	}
	private byte[] toByteArr(int input, int arrLength) 
	{
		if(input>=0)
		{
			byte[] ret = new byte[arrLength];
			for(int i = 1; i<=arrLength; i++)
			{
				ret[arrLength-i] = (byte) (input%256);
				input/=256;
				
				
			}
			return ret;
		}
		if(input==-1)
			return new byte[]{(byte) 0xff, (byte) 0xff};
		return toByteArr(65536+input, arrLength);
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
	public int groupCode1() {
		// TODO Auto-generated method stub
		return groupCode1;
	}
	public int groupCode2() {
		// TODO Auto-generated method stub
		return groupCode2;
	}
	public int groupCode3() {
		// TODO Auto-generated method stub
		return groupCode3;
	}
	public int groupCode4() {
		// TODO Auto-generated method stub
		return groupCode4;
	}
}
