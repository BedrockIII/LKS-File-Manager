package MSDBManager;

import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.ArrayList;
import java.util.Arrays;

public class MobResAsn 
{
	short startNum1 = 0;//first 2 bytes,always 0001
	short startNum2 = 0;//next 2 bytes amount of things in list
	ArrayList<MobRes> Res = new ArrayList<MobRes>();
	public MobResAsn(byte[] data)
	{
		startNum1 = ByteBuffer.wrap(data).order(ByteOrder.BIG_ENDIAN).getShort();
		startNum2 = ByteBuffer.wrap(data).order(ByteOrder.BIG_ENDIAN).getShort(2);
		for(int i = 4; i<data.length-99; i+=104)
		{
			try {
				Res.add(new MobRes(Arrays.copyOfRange(data, i, i+104)));
			} catch (UnsupportedEncodingException e) {}
		}
	}
	public String toString()
	{
		String ret = "Num " + startNum2 + "\n";
		for(int i = 0; i<Res.size(); i++)
		{
			ret = ret + Res.get(i).toString();
		}
		return ret;
	}
}
