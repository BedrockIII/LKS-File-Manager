package ResourceManagers.MSDBManager.Definition;

import java.nio.ByteBuffer;
import java.util.Arrays;

import bFM.Utils;

public class MobRes 
{
	String location; //First 32 Bytes
	String name; //Next 32 Bytes
	int code; //Next 2 Bytes
	int aiCode; //Next 2 Bytes
	int num3; //Next 2 Bytes
	int num4; //Next 2 Bytes
	int num5; //Next 2 Bytes
	int num6; //Next 2 Bytes
	float soundSizeOrVolume1; //Next 4 Bytes
	float soundSizeOrVolume2; //Next 4 Bytes
	float num7;
	String soundType; //Next 16 Bytes
	public MobRes(byte[] data) 
	{
		location = Utils.decodeBytesToString(bFM.Utils.removeEmptySpace(Arrays.copyOfRange(data, 0, 32)));
		name = Utils.decodeBytesToString(bFM.Utils.removeEmptySpace(Arrays.copyOfRange(data, 32, 64)));
		code = Utils.getShort(data, 64);
		aiCode = Utils.getShort(data, 66);
		num3 = Utils.getShort(data, 68);
		num4 = Utils.getShort(data, 70);
		num5 = Utils.getShort(data, 72);
		num6 = Utils.getShort(data, 74);
		soundSizeOrVolume1 = ByteBuffer.wrap(data).getFloat(76);
		soundSizeOrVolume2 = ByteBuffer.wrap(data).getFloat(80);
		num7 = ByteBuffer.wrap(data).getFloat(84);
		soundType = Utils.decodeBytesToString(bFM.Utils.removeEmptySpace(Arrays.copyOfRange(data, 88, 104)));
	}
	public String toString()
	{
		return "Monster Resource " + code +": \"" +location + "\", \"" +name + "\", " +aiCode + ", " +num3 + ", " +num4 + ", " +num5 + ", " +num6 + ", " +soundSizeOrVolume1+ ", " +soundSizeOrVolume2+ ", " + num7 + ", \"" +soundType+"\"\n";
	}
}
