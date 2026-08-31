package ResourceManagers.MSDBManager.Definition;

import java.nio.charset.Charset;
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
	public MobAi(String line)
	{
		// Parse from CSV
		String[] data = Utils.toStrArr(line);
		AiType = Utils.formatString(data[0]);
		num0 = Utils.strToInt(data[1]);
		num1 = Utils.strToInt(data[2]);
		num2 = Utils.strToInt(data[3]);
		num3 = Utils.strToInt(data[4]);
		num4 = Utils.strToInt(data[5]);
		num5 = Utils.strToInt(data[6]);
		num6 = Utils.strToInt(data[7]);
		num7 = Utils.strToInt(data[8]);
		num8 = Utils.strToInt(data[9]);
		num9 = Utils.strToInt(data[10]);
	}
	public String toString()
	{
		return "Mob AI \""+AiType+"\": "+num0 +" ,"+num1 +" ,"+num2 +" ,"+num3 +" ,"+num4 +" ,"+num5 +" ,"+num6 +" ,"+num7 +" ,"+num8 +" ,"+num9 +"\n";
	}
	public byte[] toBytes() 
	{
		byte[] ret = Utils.toByteArr(num0, 2);
		ret = Utils.mergeArrays(ret, Utils.toByteArr(num1, 2));
		ret = Utils.mergeArrays(ret, Utils.toByteArr(num2, 2));
		ret = Utils.mergeArrays(ret, Utils.toByteArr(num3, 2));
		ret = Utils.mergeArrays(ret, Utils.toByteArr(num4, 2));
		ret = Utils.mergeArrays(ret, Utils.toByteArr(num5, 2));
		ret = Utils.mergeArrays(ret, Utils.toByteArr(num6, 2));
		ret = Utils.mergeArrays(ret, Utils.toByteArr(num7, 2));
		ret = Utils.mergeArrays(ret, Utils.toByteArr(num8, 2));
		ret = Utils.mergeArrays(ret, Utils.toByteArr(num9, 2));
		try {
			ret = Utils.mergeArrays(ret, Utils.encodeStringToBytes(AiType, Charset.forName("Shift-JIS")));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		byte[] finalRet = new byte[52];
		for(int i = 0; i < finalRet.length && i < ret.length; i++)
		{
			finalRet[i] = ret[i];
		}
		return finalRet;
	}
}

