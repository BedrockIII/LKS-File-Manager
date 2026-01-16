package itemManager;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.ArrayList;

public class itemPlace 
{
	int itemCode;
	float x;
	float y;
	float z;
	short activationFlag;
	short deactivationFlag;
	public itemPlace(byte[] data)
	{
		itemCode = getShort(data, 2);
		x = ByteBuffer.wrap(data).order(ByteOrder.BIG_ENDIAN).getFloat(4);
		y = ByteBuffer.wrap(data).order(ByteOrder.BIG_ENDIAN).getFloat(8);
		z = ByteBuffer.wrap(data).order(ByteOrder.BIG_ENDIAN).getFloat(12);
		activationFlag = (short) getShort(data, 16);
		deactivationFlag = (short) getShort(data, 18);
	}
	public itemPlace(String line)
	{
		String[] words = splitList(line);
		itemCode = Integer.valueOf(words[0]);
		x = Float.valueOf(words[1]);
		y = Float.valueOf(words[2]);
		z = Float.valueOf(words[3]);
		activationFlag = Integer.valueOf(words[4]).shortValue();
		deactivationFlag = Integer.valueOf(words[5]).shortValue();
	}
	private static String[] splitList(String listStr)
	{//Split List into String Array
		final String validChars = "1234567890.-";
		ArrayList<String> list = new ArrayList<String>();
		String word = "";
		for(int i = 0; i < listStr.length(); i++)
		{
			if(validChars.indexOf(listStr.charAt(i))!=-1)
			{
				word+=listStr.charAt(i);
			}
			else if(listStr.charAt(i)==' ')
			{
				if(word.length()>0)
				{
					list.add(word);
					word = "";
				}
			}
		}
		list.add(word);
		String[] ret = new String[list.size()];
		for(int i = 0; i < ret.length; i++)
		{
			ret[i]=list.get(i);
		}
		return ret;
	}
	private int getShort(byte[] data, int index)
	{
		if(data==null)
		{
			return -1;
		}
		if(data.length<index+2)
		{
			return -1;
		}
		if(ByteBuffer.wrap(data).order(ByteOrder.BIG_ENDIAN).getShort(0)==-1)
		{
			return -1;
		}
		int ret = (int)data[index];
		if(ret<0)ret+=256;
		ret*=256;
		int ret2 =(int)data[index+1];
		if(ret2<0)ret2+=256;
		if(ret+ret2==65535) return -1;
		return ret+ret2;
	}
	public static byte[] mergeArrays(byte[] main, byte[] add)
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
	public byte[] toBytes()
	{
		byte[] ret = toByteArr(itemCode, 4);
		ret = mergeArrays(ret, ByteBuffer.allocate(4).putFloat(x).array());
		ret = mergeArrays(ret, ByteBuffer.allocate(4).putFloat(y).array());
		ret = mergeArrays(ret, ByteBuffer.allocate(4).putFloat(z).array());
		ret = mergeArrays(ret, toByteArr(activationFlag, 2));
		ret = mergeArrays(ret, toByteArr(deactivationFlag, 2));
		ret = mergeArrays(ret, toByteArr(0, 8));
		return ret;
	}
	public String toString()
	{
		return "Item Place: " + itemCode + ", " + x + ", " + y + ", " + z + ", " + activationFlag + ", " + deactivationFlag + "\n";
	}
}
