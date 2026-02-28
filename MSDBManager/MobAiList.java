package MSDBManager;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.ArrayList;
import java.util.Arrays;

public class MobAiList 
{
	short startNum1 = 0;//first 2 bytes,always 0001
	short startNum2 = 0;//next 2 bytes amount of things in list
	ArrayList<MobAi> Ai = new ArrayList<MobAi>();
	public MobAiList(byte[] data)
	{
		if(data.length<4) return;
		startNum1 = ByteBuffer.wrap(data).order(ByteOrder.BIG_ENDIAN).getShort();
		startNum2 = ByteBuffer.wrap(data).order(ByteOrder.BIG_ENDIAN).getShort(2);
		for(int i = 4; i<data.length-51; i+=52)
		{
			Ai.add(new MobAi(Arrays.copyOfRange(data, i, i+52)));
		}
	}
	public String toString()
	{
		String ret = "Num " + startNum2 + "\n";
		for(int i = 0; i<Ai.size(); i++)
		{
			ret = ret + Ai.get(i).toString();
		}
		return ret;
	}
}
