package ResourceManagers.MSDBManager.Definition;

import java.nio.ByteBuffer;
import java.util.Arrays;

import bFM.Utils;

public class MobAttackInfo 
{
	String word1;//16
	String word2;//16
	float num0; //4
	float num1; //4
	float num2; //4
	float num3; //4
	float num4;//4
	float num5; //4
	float num6;//4
	float num7;//4
	float num8;//4
	float num9;//4
	float num10;//4
	float num11;//4
	int attackCode;//2
	int num13;//2
	byte num14;
	byte num15;
	byte num16;
	byte num17;
	byte num18;
	byte num19;
	byte num20;
	byte num21;
	public MobAttackInfo(byte[] data)
	{
		ByteBuffer data2 = ByteBuffer.wrap(data);
		word1 = Utils.decodeBytesToString(bFM.Utils.removeEmptySpace(Arrays.copyOfRange(data, 0, 16)));
		word2 = Utils.decodeBytesToString(bFM.Utils.removeEmptySpace(Arrays.copyOfRange(data, 16, 32)));
		num0 = data2.getFloat(32);
		num1 = data2.getFloat(36);
		num2 = data2.getFloat(40);
		num3 = data2.getFloat(44);
		num4 = data2.getFloat(48);
		num5 = data2.getFloat(52);
		num6 = data2.getFloat(56);
		num7 = data2.getFloat(60);
		num8 = data2.getFloat(64);
		num9 = data2.getFloat(68);
		num10 = data2.getFloat(72);
		num11 = data2.getFloat(76);
		attackCode = Utils.getShort(data, 80);
		num13 = Utils.getShort(data, 82);
		num14 = data[84];
		num15 = data[85];
		num16 = data[86];
		num17 = data[87];
		num18 = data[88];
		num19 = data[89];
		num20 = data[90];
		num21 = data[91];
	}
	public String toString()
	{
		return "Mob Attack Info " +attackCode+": \"" + word1 + "\", \"" + word2 + "\", " + num0 + ", " + num1 + ", " +
				num2 + ", " +num3 + ", " +num4 + ", " +num5 + ", " +num6 + ", " +num7 + ", " +num8 + ", " + 
				num9 + ", " +num10 + ", " +num11 + ", " +num13 + ", " +num14 + ", " +
				num15 + ", " +num16 + ", " +num17 + ", " +num18 + ", " +num19 + ", " +num20 + ", " +
				num21 + "\n";
	}
}
