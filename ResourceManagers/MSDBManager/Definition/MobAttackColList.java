package ResourceManagers.MSDBManager.Definition;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import bFM.Utils;

public class MobAttackColList 
{
	short startNum1 = 0;//first 2 bytes,always 0001
	short startNum2 = 0;//next 2 bytes amount of things in list
	ArrayList<MobAttackCol> AttackCol = new ArrayList<MobAttackCol>();
	public MobAttackColList(byte[] data)
	{
		if(data.length<4) return;
		startNum1 = ByteBuffer.wrap(data).order(ByteOrder.BIG_ENDIAN).getShort();
		startNum2 = ByteBuffer.wrap(data).order(ByteOrder.BIG_ENDIAN).getShort(2);
		for(int i = 4; i<data.length; i+=36)
		{
			AttackCol.add(new MobAttackCol(Arrays.copyOfRange(data, i, i+36)));
		}
	}
	public MobAttackColList(List<String> lines)
	{
		for(String line : lines)
		{
			if(line.indexOf("Attack Collision") != -1)AttackCol.add(new MobAttackCol(line));
		}
	}
	public String toString()
	{
		String ret = "Num " + startNum2 + "\n";
		for(int i = 0; i<AttackCol.size(); i++)
		{
			ret = ret + AttackCol.get(i).toString();
		}
		return ret;
	}
	public byte[] toBytes()
	{
		byte[] ret = new byte[]{0,1};
		ret = Utils.mergeArrays(ret, Utils.toByteArr(AttackCol.size(), 2));
		for(MobAttackCol col : AttackCol)
			ret = Utils.mergeArrays(ret, col.toBytes());
		return ret;
	}
}
