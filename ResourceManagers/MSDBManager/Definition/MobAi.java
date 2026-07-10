package ResourceManagers.MSDBManager.Definition;

import java.util.Arrays;

import bFM.Utils;

public class MobAi 
{
	int num0; //First 2 Bytes
	int num1; //Next 2 Bytes
	int num2; //Next 2 Bytes
	int num3; //Next 2 Bytes
	int num4; //Next 2 Bytes
	int num5; //Next 2 Bytes
	int num6; //Next 2 Bytes
	int num7; //Next 2 Bytes
	int num8; //Next 2 Bytes
	int num9; //Next 2 Bytes
	String AiType; //Next 32 Bytes
	public MobAi(byte[] data)
	{
		num0 = Utils.getShort(data, 0);
		num1 = Utils.getShort(data, 2);
		num2 = Utils.getShort(data, 4);
		num3 = Utils.getShort(data, 6);
		num4 = Utils.getShort(data, 8);
		num5 = Utils.getShort(data, 10);
		num6 = Utils.getShort(data, 12);
		num7 = Utils.getShort(data, 14);
		num8 = Utils.getShort(data, 16);
		num9 = Utils.getShort(data, 18);
		AiType = Utils.decodeBytesToString(bFM.Utils.removeEmptySpace(Arrays.copyOfRange(data, 20, 52)));
	}
	public String toString()
	{
		return "Mob AI \""+AiType+"\": "+num0 +" ,"+num1 +" ,"+num2 +" ,"+num3 +" ,"+num4 +" ,"+num5 +" ,"+num6 +" ,"+num7 +" ,"+num8 +" ,"+num9 +"\n";
	}
}

