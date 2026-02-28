package MSDBManager;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.ArrayList;
import java.util.Arrays;

public class MobRandomAreaList 
{
	short startNum1 = 0;//first 2 bytes,always 0001
	short startNum2 = 0;//next 2 bytes amount of things in list
	ArrayList<MobRandomArea> ra = new ArrayList<MobRandomArea>();
	public MobRandomAreaList(byte[] data)
	{
		startNum1 = ByteBuffer.wrap(data).order(ByteOrder.BIG_ENDIAN).getShort();
		startNum2 = ByteBuffer.wrap(data).order(ByteOrder.BIG_ENDIAN).getShort(2);
		for(int i = 4; i<data.length; i+=8)
		{
			ra.add(new MobRandomArea(Arrays.copyOfRange(data, i, i+8)));
		}
	}
	public String toString()
	{
		String ret = "Num " + startNum2 + "\n";
		for(int i = 0; i<ra.size(); i++)
		{
			ret = ret + ra.get(i).toString();
		}
		return ret;
	}
}
