package MSDBManager;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.ArrayList;
import java.util.Arrays;

public class MobObjectList 
{
	short startNum1 = 0;//first 2 bytes,always 0001
	short startNum2 = 0;//next 2 bytes amount of things in list
	ArrayList<MobObject> object = new ArrayList<MobObject>();
	public MobObjectList(byte[] data)
	{
		startNum1 = ByteBuffer.wrap(data).order(ByteOrder.BIG_ENDIAN).getShort();
		startNum2 = ByteBuffer.wrap(data).order(ByteOrder.BIG_ENDIAN).getShort(2);
		for(int i = 4; i<data.length; i+=40)
		{
			object.add(new MobObject(Arrays.copyOfRange(data, i, i+40)));
		}
	}
	public String toString()
	{
		String ret = "Num " + startNum2 + "\n";
		for(int i = 0; i<object.size(); i++)
		{
			ret = ret + object.get(i).toString();
		}
		return ret;
	}
}
