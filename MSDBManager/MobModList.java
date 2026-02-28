package MSDBManager;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.ArrayList;
import java.util.Arrays;

public class MobModList 
{
	short startNum1 = 0;//first 2 bytes,always 0001
	short startNum2 = 0;//next 2 bytes amount of things in list
	ArrayList<MobMod> Mod = new ArrayList<MobMod>();
	public MobModList(byte[] data)
	{
		startNum1 = ByteBuffer.wrap(data).order(ByteOrder.BIG_ENDIAN).getShort();
		startNum2 = ByteBuffer.wrap(data).order(ByteOrder.BIG_ENDIAN).getShort(2);
		for(int i = 4; i<data.length-51; i+=88)
		{
			Mod.add(new MobMod(Arrays.copyOfRange(data, i, i+88)));
		}
	}
	public String toString()
	{
		String ret = "Num " + startNum2 + "\n";
		for(int i = 0; i<Mod.size(); i++)
		{
			ret = ret + Mod.get(i).toString();
		}
		return ret;
	}
	public String toHP()
	{
		String ret = "Num " + startNum2 + "\n";
		for(int i = 0; i<Mod.size(); i++)
		{
			ret = ret + Mod.get(i).toHP();
		}
		return ret;
	}
	public byte[] toBytes()
	{
		byte[] ret = ByteBuffer.allocate(4).putShort((short)1).array();
		ret = mergeArrays(ret, ByteBuffer.allocate(4).putFloat((short)Mod.size()).array());
		for(int i = 0; i< Mod.size();i++)
		{
			ret = mergeArrays(ret, Mod.get(i).toBytes());
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
