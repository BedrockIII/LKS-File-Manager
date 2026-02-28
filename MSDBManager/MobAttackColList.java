package MSDBManager;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.ArrayList;
import java.util.Arrays;

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
	public String toString()
	{
		String ret = "Num " + startNum2 + "\n";
		for(int i = 0; i<AttackCol.size(); i++)
		{
			ret = ret + AttackCol.get(i).toString();
		}
		return ret;
	}
}
