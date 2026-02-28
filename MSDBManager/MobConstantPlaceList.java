package MSDBManager;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.ArrayList;

public class MobConstantPlaceList 
{
	short startNum1 = 0;//first 2 bytes,always 0001
	short startNum2 = 0;//next 2 bytes amount of things in list
	ArrayList<MobConstantPlace> cp = new ArrayList<MobConstantPlace>();
	public MobConstantPlaceList(byte[] data)
	{
		startNum1 = ByteBuffer.wrap(data).order(ByteOrder.BIG_ENDIAN).getShort();
		startNum2 = ByteBuffer.wrap(data).order(ByteOrder.BIG_ENDIAN).getShort(2);
		for(int i = 4; i<data.length; i+=40)
		{
			//cp.add(new MobConstantPlace(Arrays.copyOfRange(data, i, i+40)));
		}
	}
	public String toString()
	{
		String ret = "Num " + startNum2 + "\n";
		for(int i = 0; i<cp.size(); i++)
		{
			ret = ret + cp.get(i).toString();
		}
		return ret;
	}
}
