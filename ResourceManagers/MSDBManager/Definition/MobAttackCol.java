package ResourceManagers.MSDBManager.Definition;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.charset.Charset;
import java.util.Arrays;

import bFM.Data;
import bFM.Nameable;
import bFM.Utils;

public class MobAttackCol implements Data, Nameable 
{
	int attackCode;
	int num1;
	String boneAttachmentName;//16 bytes
	float num2;
	float num3;
	float num4;
	float num5;
	public MobAttackCol(byte[] data)
	{
		attackCode = ByteBuffer.wrap(data).order(ByteOrder.BIG_ENDIAN).getShort(0);
		num1 = ByteBuffer.wrap(data).order(ByteOrder.BIG_ENDIAN).getShort(2);
		boneAttachmentName = Utils.decodeBytesToString(bFM.Utils.removeEmptySpace(Arrays.copyOfRange(data, 4, 20)));
		num2 = ByteBuffer.wrap(data).order(ByteOrder.BIG_ENDIAN).getFloat(20);
		num3 = ByteBuffer.wrap(data).order(ByteOrder.BIG_ENDIAN).getFloat(24);
		num4 = ByteBuffer.wrap(data).order(ByteOrder.BIG_ENDIAN).getFloat(28);
		num5 = ByteBuffer.wrap(data).order(ByteOrder.BIG_ENDIAN).getFloat(32);
	}
	public MobAttackCol(String line)
	{
		// Parse from CSV
		String[] data = Utils.toStrArr(line);
		attackCode = Utils.strToInt(data[0]);
		num1 = Utils.strToInt(data[1]);
		boneAttachmentName = Utils.formatString(data[2]);
		num2 = Utils.strToFloat(data[3]);
		num3 = Utils.strToFloat(data[4]);
		num4 = Utils.strToFloat(data[5]);
		num5 = Utils.strToFloat(data[6]);
	}
	public String toString()
	{
		return "Attack Collision "+attackCode +": "+num1 +", \""+boneAttachmentName+"\", "+num2 + ", "+num3 + ", "+num4 + ", "+num5 + "\n";
	}
	public byte[] toBytes() 
	{
		byte[] ret = Utils.toByteArr(attackCode, 2);
		ret = Utils.mergeArrays(ret, Utils.toByteArr(num1, 2));
		try {
			ret = Utils.mergeArrays(ret, Utils.encodeStringToBytes(boneAttachmentName, Charset.forName("Shift-JIS")));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		byte[] finalRet = new byte[20];
		for(int i = 0; i < finalRet.length && i < ret.length; i++)
		{
			finalRet[i] = ret[i];
		}
		finalRet = Utils.mergeArrays(finalRet, ByteBuffer.allocate(4).putFloat(num2).array());
		finalRet = Utils.mergeArrays(finalRet, ByteBuffer.allocate(4).putFloat(num3).array());
		finalRet = Utils.mergeArrays(finalRet, ByteBuffer.allocate(4).putFloat(num4).array());
		finalRet = Utils.mergeArrays(finalRet, ByteBuffer.allocate(4).putFloat(num5).array());
		return finalRet;
	}
	public void setData(byte[] data) 
	{
		throw new UnsupportedOperationException("setData(byte[] data) should not be called on type " + this.getClass());
	}
	public int getSize()
	{
		return toBytes().length;
	}
	public boolean equals(String name)
	{
		return boneAttachmentName.equals(name);
	}
	public void setName(String name)
	{
		boneAttachmentName = name;
	}
	public int getNum1() {
		return num1;
	}
	public void setNum1(int num1) {
		this.num1 = num1;
	}
	public float getNum2() {
		return num2;
	}
	public void setNum2(float num2) {
		this.num2 = num2;
	}
	public float getNum3() {
		return num3;
	}
	public void setNum3(float num3) {
		this.num3 = num3;
	}
	public float getNum4() {
		return num4;
	}
	public void setNum4(float num4) {
		this.num4 = num4;
	}
	public float getNum5() {
		return num5;
	}
	public void setNum5(float num5) {
		this.num5 = num5;
	}
	public void setAttackCode(int attackCode) {
		this.attackCode = attackCode;
	}
	public String getName()
	{
		return boneAttachmentName;
	}
}
