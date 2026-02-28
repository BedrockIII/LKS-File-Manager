package MSDBManager;

import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.util.Arrays;

public class MobRes 
{
	String location; //First 32 Bytes
	String name; //Next 32 Bytes
	short code; //Next 2 Bytes
	short aiCode; //Next 2 Bytes
	short num3; //Next 2 Bytes
	short num4; //Next 2 Bytes
	short num5; //Next 2 Bytes
	short num6; //Next 2 Bytes
	float soundSizeOrVolume1; //Next 4 Bytes
	float soundSizeOrVolume2; //Next 4 Bytes
	String soundType; //Next 16 Bytes
	public MobRes(byte[] data) throws UnsupportedEncodingException 
	{
		location = new String(bFM.Utils.removeEmptySpace(Arrays.copyOfRange(data, 0, 32)));
		name = new String(bFM.Utils.removeEmptySpace(Arrays.copyOfRange(data, 32, 64)), "SHIFT-JIS");
		code = ByteBuffer.wrap(data).getShort(64);
		aiCode = ByteBuffer.wrap(data).getShort(66);
		num3 = ByteBuffer.wrap(data).getShort(68);
		num4 = ByteBuffer.wrap(data).getShort(70);
		num5 = ByteBuffer.wrap(data).getShort(72);
		num6 = ByteBuffer.wrap(data).getShort(74);
		soundSizeOrVolume1 = ByteBuffer.wrap(data).getFloat(76);
		soundSizeOrVolume2 = ByteBuffer.wrap(data).getFloat(80);
		soundType = new String(bFM.Utils.removeEmptySpace(Arrays.copyOfRange(data, 84, 100)), "SHIFT-JIS");
	}
	public String toString()
	{
		return "\"" +location + "\", \"" +name + "\", " +code + ", " +aiCode + ", " +num3 + ", " +num4 + ", " +num5 + ", " +num6 + ", " +soundSizeOrVolume1+ ", " +soundSizeOrVolume2+ ", \"" +soundType+"\"\n";
	}
}
