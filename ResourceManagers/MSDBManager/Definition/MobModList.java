package ResourceManagers.MSDBManager.Definition;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MobModList 
{
	final static String BmosVersion = "Bedrock's Mission Object Definition File Version: 1.0";
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
	public MobModList(List<String> data, boolean isBMos) 
	{
		if(isBMos)
		{
			initializeFromLines(data);
			return;
		}
		for(int i = 0; i<data.size(); i++)
		{
			Mod.add(new MobMod(data.get(i)));
		}
	}
	public String toString()
	{
		String ret = "";
		//String ret = "Num " + startNum2 + "\n";
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
		byte[] ret = ByteBuffer.allocate(2).putShort((short)1).array();
		ret = mergeArrays(ret, ByteBuffer.allocate(2).putShort((short)Mod.size()).array());
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
	public String toBMos()
	{
		String ret = BmosVersion  + '\n';
		for(MobMod mod : Mod)
		{
			ret += mod.toBMos();
		}
		return ret;
	}
	private void initializeFromLines(List<String> lines)
	{
		MobMod lastMod = null;
		for(String line : lines)
		{
			if(line.indexOf("Bedrock's Mission Object Definition File Version:") != -1 && line.indexOf(BmosVersion)==-1)
			{
				System.err.println("Warning: Wrong File Version");
			}
			else if(line.indexOf("<<Mission Object ID>>") != -1)
			{
				lastMod = new MobMod();
				lastMod.addLine(line);
				Mod.add(lastMod);
			}
			else if(lastMod != null)
			{
				lastMod.addLine(line);
			}
		}
	}
}
