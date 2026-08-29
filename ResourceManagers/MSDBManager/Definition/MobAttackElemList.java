package ResourceManagers.MSDBManager.Definition;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import bFM.Utils;

public class MobAttackElemList
{
	short startNum1 = 0;//first 2 bytes,always 0001
	short startNum2 = 0;//next 2 bytes amount of things in list
	ArrayList<MobAttackElem> AttackElement = new ArrayList<MobAttackElem>();
	public MobAttackElemList(byte[] data)
	{
		if(data.length<4) return;
		startNum1 = ByteBuffer.wrap(data).order(ByteOrder.BIG_ENDIAN).getShort();
		startNum2 = ByteBuffer.wrap(data).order(ByteOrder.BIG_ENDIAN).getShort(2);
		for(int i = 4; i<data.length; i+=36)
		{
			AttackElement.add(new MobAttackElem(Arrays.copyOfRange(data, i, i+36)));
		}
	}
	public MobAttackElemList(List<String> lines)
	{
		for(String line : lines)
		{
			if(line.indexOf("Attack Element") != -1)AttackElement.add(new MobAttackElem(line));
		}
	}
	public String toString()
	{
		String ret = "Num " + startNum2 + "\n";
		for(int i = 0; i<AttackElement.size(); i++)
		{
			ret = ret + AttackElement.get(i).toString();
		}
		return ret;
	}
	public byte[] toBytes() 
	{
		byte[] ret = new byte[]{0,1};
		ret = Utils.mergeArrays(ret, Utils.toByteArr(AttackElement.size(), 2));
		for(MobAttackElem element : AttackElement)
			ret = Utils.mergeArrays(ret, element.toBytes());
		return ret;
	}
}
