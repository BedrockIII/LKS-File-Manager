package MSDBManager;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

public class MobRectangle 
{
	float num0;
	float num1;
	float num2;
	float num3;
	public MobRectangle(byte[] data)
	{
		num0 = ByteBuffer.wrap(data).order(ByteOrder.BIG_ENDIAN).getFloat(0);
		num1 = ByteBuffer.wrap(data).order(ByteOrder.BIG_ENDIAN).getFloat(4);
		num2 = ByteBuffer.wrap(data).order(ByteOrder.BIG_ENDIAN).getFloat(8);
		num3 = ByteBuffer.wrap(data).order(ByteOrder.BIG_ENDIAN).getFloat(12);
	}
	public String toString()
	{
		return ""+num0 +" ,"+num1 +" ,"+num2 +" ,"+num3 +"\n";
	}
}
