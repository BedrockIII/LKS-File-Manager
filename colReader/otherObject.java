package colReader;

import java.nio.ByteBuffer;
import java.util.Arrays;

public class otherObject {
	byte[] data;
	String name;
	float num1;
	float num2;
	float num3;
	float num4;
	float num5;
	float num6;
	float num7;
	float num8;
	float num9;
	float num10;
	float num11;
	public otherObject(byte[] data)
	{
		name = new String(Arrays.copyOfRange(data, 0, byteArrIndex(data,(byte)0)));
		num1 = ByteBuffer.wrap(data).getFloat(76);
		num2 = ByteBuffer.wrap(data).getFloat(80);
		num3 = ByteBuffer.wrap(data).getFloat(84);
		num4 = ByteBuffer.wrap(data).getFloat(88);
		num5 = ByteBuffer.wrap(data).getFloat(92);
		num6 = ByteBuffer.wrap(data).getFloat(96);
		num7 = ByteBuffer.wrap(data).getFloat(100);
		num8 = ByteBuffer.wrap(data).getFloat(104);
		num9 = ByteBuffer.wrap(data).getFloat(108);
		num10 = ByteBuffer.wrap(data).getFloat(124);
		num11 = ByteBuffer.wrap(data).getFloat(128);
	}
	public otherObject(String name, int preset)
	{
		this.name = name;
		if(preset==0) 
		{
			this.name = "DMY_1229149135_0";
			num1 = 0;
			num2 = 0;
			num3 = 0;
			num4 = 0;
			num5 = 0;
			num6 = 0;
			num7 = 0;
			num8 = 0;
			num9 = 0;
			num10 = 0;
			num11 = 0;
		} else if(preset==1)
		{
			this.name = "lambert1";
			num1 = 1;
			num2 = (float)0.5;
			num3 = (float)0.5;
			num4 = (float)0.5;
			num5 = 1;
			num6 = 1;
			num7 = 1;
			num8 = 1;
			num9 = 1;
			num10 = 1;
			num11 = 1;
		} else if(preset==2)
		{
			this.name = "col_m";
			num1 = 1;
			num2 = 1;
			num3 = (float)0.708;
			num4 = (float)0.84227127;
			num5 = 1;
			num6 = 1;
			num7 = 1;
			num8 = 1;
			num9 = 1;
			num10 = 1;
			num11 = 1;
		}
		
	}
	public otherObject()
	{
		name = "object";
		num1 = 1;
		num2 = 1;
		num3 = 1;
		num4 = 1;
		num5 = 1;
		num6 = 1;
		num7 = 1;
		num8 = 1;
		num9 = 1;
		num10 = 1;
		num11 = 1;
	}
	public int byteArrIndex(byte[] arr, byte is)
	{
		for(int i = 0; i<arr.length; i++)
		if(arr[i]==is) return i;
		return -1;
	}
	public byte[] getObjects()
	{
		byte[] ret = name.getBytes();
		ret = bFM.Utils.mergeArrays(ret, new byte[76-name.length()]);
		ret = bFM.Utils.mergeArrays(ret, ByteBuffer.allocate(4).putFloat(num1).array());
		ret = bFM.Utils.mergeArrays(ret, ByteBuffer.allocate(4).putFloat(num2).array());
		ret = bFM.Utils.mergeArrays(ret, ByteBuffer.allocate(4).putFloat(num3).array());
		ret = bFM.Utils.mergeArrays(ret, ByteBuffer.allocate(4).putFloat(num4).array());
		ret = bFM.Utils.mergeArrays(ret, ByteBuffer.allocate(4).putFloat(num5).array());
		ret = bFM.Utils.mergeArrays(ret, ByteBuffer.allocate(4).putFloat(num6).array());
		ret = bFM.Utils.mergeArrays(ret, ByteBuffer.allocate(4).putFloat(num7).array());
		ret = bFM.Utils.mergeArrays(ret, ByteBuffer.allocate(4).putFloat(num8).array());
		
		ret = bFM.Utils.mergeArrays(ret, ByteBuffer.allocate(4).putFloat(num9).array());
		ret = bFM.Utils.mergeArrays(ret, new byte[12]);
		ret = bFM.Utils.mergeArrays(ret, ByteBuffer.allocate(4).putFloat(num10).array());
		ret = bFM.Utils.mergeArrays(ret, ByteBuffer.allocate(4).putFloat(num11).array());
		ret = bFM.Utils.mergeArrays(ret, new byte[28]);
		return ret;
	}

}
