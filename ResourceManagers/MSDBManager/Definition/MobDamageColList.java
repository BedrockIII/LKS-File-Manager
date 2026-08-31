package ResourceManagers.MSDBManager.Definition;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import bFM.Utils;

public class MobDamageColList 
{
	short startNum1 = 0;//first 2 bytes,always 0001
	short startNum2 = 0;//next 2 bytes amount of things in list
	ArrayList<MobDamageCol> DamageCol = new ArrayList<MobDamageCol>();
	public MobDamageColList(byte[] data)
	{
		if(data.length<4) return;
		startNum1 = ByteBuffer.wrap(data).order(ByteOrder.BIG_ENDIAN).getShort();
		startNum2 = ByteBuffer.wrap(data).order(ByteOrder.BIG_ENDIAN).getShort(2);
		for(int i = 4; i<data.length; i+=40)
		{
			DamageCol.add(new MobDamageCol(Arrays.copyOfRange(data, i, i+40)));
		}
	}
	public MobDamageColList(List<String> lines)
	{
		for(String line : lines)
		{
			if(line.indexOf("Mob Damage Collision") != -1)DamageCol.add(new MobDamageCol(line));
		}
	}
	public String toString()
	{
		String ret = "Num " + startNum2 + "\n";
		for(int i = 0; i<DamageCol.size(); i++)
		{
			ret = ret + DamageCol.get(i).toString();
		}
		return ret;
	}
	public byte[] toBytes() 
	{
		byte[] ret = new byte[]{0,1};
		ret = Utils.mergeArrays(ret, Utils.longToBytes(DamageCol.size(), 2));
		for(MobDamageCol col : DamageCol)
			ret = Utils.mergeArrays(ret, col.toBytes());
		return ret;
	}
}
