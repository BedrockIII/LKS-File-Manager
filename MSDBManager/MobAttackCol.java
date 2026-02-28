package MSDBManager;

import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.Arrays;

public class MobAttackCol 
{
	short num0;
	short num1;
	String name;//16 bytes
	float num2;
	float num3;
	float num4;
	float num5;
	public MobAttackCol(byte[] data)
	{
		num0 = ByteBuffer.wrap(data).order(ByteOrder.BIG_ENDIAN).getShort(0);
		num1 = ByteBuffer.wrap(data).order(ByteOrder.BIG_ENDIAN).getShort(2);
		try {
			name = new String(bFM.Utils.removeEmptySpace(Arrays.copyOfRange(data, 4, 20)), "SHIFT-JIS");
		} catch (UnsupportedEncodingException e) {e.printStackTrace();}
		num2 = ByteBuffer.wrap(data).order(ByteOrder.BIG_ENDIAN).getFloat(20);
		num3 = ByteBuffer.wrap(data).order(ByteOrder.BIG_ENDIAN).getFloat(24);
		num4 = ByteBuffer.wrap(data).order(ByteOrder.BIG_ENDIAN).getFloat(28);
		num5 = ByteBuffer.wrap(data).order(ByteOrder.BIG_ENDIAN).getFloat(32);
	}
	public String toString()
	{
		return ""+num0 +" ,"+num1 +" ,\""+name+"\","+num2 + " ,"+num3 + " ,"+num4 + " ,"+num5 + "\n";
	}
}
