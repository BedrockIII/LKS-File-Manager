package ResourceManagers.MSDBManager.Definition;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.Arrays;

import bFM.Utils;

public class MobAttackElem 
{
	//Elements
	final static int NO_ELEMENT = 0;
	final static int POSION = 1;
	final static int PARALYSIS = 2;
	final static int SLOWNESS = 3;
	final static int SLEEP = 4;
	final static int CONFUSION = 5;
	final static int DESPAIR = 6;
	final static int BURN = 7;
	final static int FREEZE = 8;
	final static int RAGE = 9;
	final static int BLACKNESS = 10;
	//Hit Effects
	final static int NO_EFFECT = 0;
	final static int NO_EFFECT_2 = 1;
	final static int ROLL_OVER = 2;
	final static int ROLL_OVER_2 = 3;
	final static int ROLL_OVER_3 = 4;
	final static int aaaaa = 5;
	final static int wef = 6;
	final static int wegw = 7;
	final static int TP_TO_KING = 8;
	final static int wgewg = 9;
	final static int wr = 10;
	final static int BLOW_AWAY = 11;
	final static int RUN_TO_KING = 16;
	
	
	int index;//2
	int Damage;//2
	int atkNum2;//2
	int HitTypes;//2
	int ElementalType;//2
	int atkNum5;//2
	String name;//16 bytes
	float atkNum6;
	byte atkNum7;
	byte atkNum8;
	byte HitEffect; //IE roll or blow away
	byte atkNum10;
	public MobAttackElem(byte[] data)
	{
		index = bFM.Utils.getShort(data, 0);
		Damage = bFM.Utils.getShort(data, 2);
		atkNum2 = bFM.Utils.getShort(data, 4);
		HitTypes = bFM.Utils.getShort(data, 6);
		ElementalType = bFM.Utils.getShort(data, 8);
		atkNum5 = bFM.Utils.getShort(data, 10);
		name = Utils.decodeBytesToString(bFM.Utils.removeEmptySpace(Arrays.copyOfRange(data, 12, 28)));
		atkNum6 = ByteBuffer.wrap(data).order(ByteOrder.BIG_ENDIAN).getFloat(28);
		atkNum7 = data[32];
		atkNum8 = data[33];
		HitEffect = data[34];
		atkNum10 = data[35];
	}
	public MobAttackElem(String line)
	{
		// Parse from CSV
		String[] data = Utils.toStrArr(line);
		index = Utils.strToInt(data[0]);
		Damage = Utils.strToInt(data[1]);
		atkNum2 = Utils.strToInt(data[2]);
		HitTypes = Utils.strToInt(data[3]);
		ElementalType = Utils.strToInt(data[4]);
		atkNum5 = Utils.strToInt(data[5]);
		name = Utils.formatString(data[6]);
		atkNum6 = Utils.strToFloat(data[7]);
		atkNum7 = (byte) Utils.strToInt(data[8]);
		atkNum8 = (byte) Utils.strToInt(data[9]);
		HitEffect = (byte) Utils.strToInt(data[10]);
		atkNum10 = (byte) Utils.strToInt(data[11]);
	}
	public String toString()
	{
		return "Attack Element " + index + ", " + Damage + ", " + atkNum2 + ", " + HitTypes + 
				", " + ElementalType + ", " + atkNum5 + ", \"" + name + "\", " + atkNum6 + ", " + atkNum7 + ", " + atkNum8 + ", " + HitEffect + ", " + atkNum10+"\n";
	}
	public byte[] toBytes() 
	{
		byte[] ret = Utils.toByteArr(index, 2);
		ret = Utils.mergeArrays(ret, Utils.toByteArr(Damage, 2));
		ret = Utils.mergeArrays(ret, Utils.toByteArr(atkNum2, 2));
		ret = Utils.mergeArrays(ret, Utils.toByteArr(HitTypes, 2));
		ret = Utils.mergeArrays(ret, Utils.toByteArr(ElementalType, 2));
		ret = Utils.mergeArrays(ret, Utils.toByteArr(atkNum5, 2));
		ret = Utils.mergeArrays(ret, Utils.encodeStringToBytes(name));
		byte[] finalRet = new byte[28];
		for(int i = 0; i < finalRet.length && i < ret.length; i++)
		{
			finalRet[i] = ret[i];
		}
		finalRet = Utils.mergeArrays(finalRet, ByteBuffer.allocate(4).putFloat(atkNum6).array());
		finalRet = Utils.mergeArrays(finalRet, atkNum7);
		finalRet = Utils.mergeArrays(finalRet, atkNum8);
		finalRet = Utils.mergeArrays(finalRet, HitEffect);
		finalRet = Utils.mergeArrays(finalRet, atkNum10);
		return finalRet;
	}
}
