package MSDBManager;

import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.Arrays;

public class MobAi 
{
	short num0; //First 2 Bytes
	short num1; //Next 2 Bytes
	short num2; //Next 2 Bytes
	short num3; //Next 2 Bytes
	short num4; //Next 2 Bytes
	short num5; //Next 2 Bytes
	short num6; //Next 2 Bytes
	short num7; //Next 2 Bytes
	short num8; //Next 2 Bytes
	short num9; //Next 2 Bytes
	String AiType; //Next 32 Bytes
	public MobAi(byte[] data)
	{
		num0 = ByteBuffer.wrap(data).order(ByteOrder.BIG_ENDIAN).getShort(0);
		num1 = ByteBuffer.wrap(data).order(ByteOrder.BIG_ENDIAN).getShort(2);
		num2 = ByteBuffer.wrap(data).order(ByteOrder.BIG_ENDIAN).getShort(4);
		num3 = ByteBuffer.wrap(data).order(ByteOrder.BIG_ENDIAN).getShort(6);
		num4 = ByteBuffer.wrap(data).order(ByteOrder.BIG_ENDIAN).getShort(8);
		num5 = ByteBuffer.wrap(data).order(ByteOrder.BIG_ENDIAN).getShort(10);
		num6 = ByteBuffer.wrap(data).order(ByteOrder.BIG_ENDIAN).getShort(12);
		num7 = ByteBuffer.wrap(data).order(ByteOrder.BIG_ENDIAN).getShort(14);
		num8 = ByteBuffer.wrap(data).order(ByteOrder.BIG_ENDIAN).getShort(16);
		num9 = ByteBuffer.wrap(data).order(ByteOrder.BIG_ENDIAN).getShort(18);
		
		try {
			AiType = new String(bFM.Utils.removeEmptySpace(Arrays.copyOfRange(data, 20, 52)), "SHIFT-JIS");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public String toString()
	{
		return ""+num0 +" ,"+num1 +" ,"+num2 +" ,"+num3 +" ,"+num4 +" ,"+num5 +" ,"+num6 +" ,"+num7 +" ,"+num8 +" ,"+num9 +" ,\""+AiType+"\"\n";
	}
}

