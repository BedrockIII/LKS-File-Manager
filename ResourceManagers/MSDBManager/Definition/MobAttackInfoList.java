package ResourceManagers.MSDBManager.Definition;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.ArrayList;
import java.util.Arrays;

public class MobAttackInfoList 
{
	short startNum1 = 0;//first 2 bytes,always 0001
	short startNum2 = 0;//next 2 bytes amount of things in list
	ArrayList<MobAttackInfo> AttackInfo = new ArrayList<MobAttackInfo>();
	public MobAttackInfoList(byte[] data)
	{
		if(data.length<4) return;
		startNum1 = ByteBuffer.wrap(data).order(ByteOrder.BIG_ENDIAN).getShort();
		startNum2 = ByteBuffer.wrap(data).order(ByteOrder.BIG_ENDIAN).getShort(2);
		for(int i = 4; i<data.length; i+=92)
		{
			AttackInfo.add(new MobAttackInfo(Arrays.copyOfRange(data, i, i+92)));
		}
	}
	public String toString()
	{
		String ret = "Num " + startNum2 + "\n";
		for(int i = 0; i<AttackInfo.size(); i++)
		{
			ret = ret + AttackInfo.get(i).toString();
		}
		return ret;
	}
}
