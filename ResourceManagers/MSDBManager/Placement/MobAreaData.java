package ResourceManagers.MSDBManager.Placement;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

import bFM.Data;
import bFM.Utils;

public class MobAreaData implements Data
{
	short areaCode;
	short num1;
	short groupCode1;
	short groupCode2;
	short groupCode3;
	short groupCode4;
	public MobAreaData(byte[] data)
	{
		areaCode = ByteBuffer.wrap(data).order(ByteOrder.BIG_ENDIAN).getShort(0);
		num1 = ByteBuffer.wrap(data).order(ByteOrder.BIG_ENDIAN).getShort(2);
		groupCode1 = ByteBuffer.wrap(data).order(ByteOrder.BIG_ENDIAN).getShort(4);
		groupCode2 = ByteBuffer.wrap(data).order(ByteOrder.BIG_ENDIAN).getShort(6);
		groupCode3 = ByteBuffer.wrap(data).order(ByteOrder.BIG_ENDIAN).getShort(8);
		groupCode4 = ByteBuffer.wrap(data).order(ByteOrder.BIG_ENDIAN).getShort(10);
	}
	public MobAreaData(short code, short num1, short group1, short group2, short group3, short group4) 
	{
		areaCode = code;
		this.num1 = num1;
		groupCode1 = group1;
		groupCode2 = group2;
		groupCode3 = group3;
		groupCode4 = group4;
	}
	public MobAreaData(int code) 
	{
		areaCode = (short) code;
		num1 = 3;
		groupCode1 = -1;
		groupCode2 = -1;
		groupCode3 = -1;
		groupCode4 = -1;
	}
	public String toBrm()
	{
		return "Random Area:  "+areaCode +" ,"+num1 +" ,"+groupCode1 +" ,"+groupCode2 +" ,"+groupCode3 +" ,"+groupCode4 +";\n";
	}
	public String toString()
	{
		return "DAT "+areaCode +" ,"+num1 +" ,"+groupCode1 +" ,"+groupCode2 +" ,"+groupCode3 +" ,"+groupCode4 +"\n";
	}
	public byte[] toBytes()
	{
		byte[] ret = Utils.toByteArr(areaCode, 2);
		ret = Utils.mergeArrays(ret, Utils.toByteArr(num1, 2));
		ret = Utils.mergeArrays(ret, Utils.toByteArr(groupCode1, 2));
		ret = Utils.mergeArrays(ret, Utils.toByteArr(groupCode2, 2));
		ret = Utils.mergeArrays(ret, Utils.toByteArr(groupCode3, 2));
		ret = Utils.mergeArrays(ret, Utils.toByteArr(groupCode4, 2));
		return ret;
	}
	public int groupCode1() {
		// TODO Auto-generated method stub
		return groupCode1;
	}
	public int groupCode2() {
		// TODO Auto-generated method stub
		return groupCode2;
	}
	public int groupCode3() {
		// TODO Auto-generated method stub
		return groupCode3;
	}
	public int groupCode4() {
		// TODO Auto-generated method stub
		return groupCode4;
	}
	public boolean equals(String name) 
	{
		throw new UnsupportedOperationException("equals() should not be called on type " + this.getClass());
	}
	public void setData(byte[] data) 
	{
		throw new UnsupportedOperationException("setData(byte[] data) should not be called on type " + this.getClass());
	}
	public void setName(String name) 
	{
		throw new UnsupportedOperationException("setName(String name) should not be called on type " + this.getClass());
	}
	public String getName() 
	{
		throw new UnsupportedOperationException("getName() should not be called on type " + this.getClass());
	}
	public int getSize() 
	{
		return 12;
	}
	public short getNum1() {
		return num1;
	}
	public void setNum1(int num1) {
		this.num1 = (short) num1;
	}
	public void setGroupCode1(int groupCode1) {
		this.groupCode1 = (short) groupCode1;
	}
	public void setGroupCode2(int groupCode2) {
		this.groupCode2 = (short) groupCode2;
	}
	public void setGroupCode3(int groupCode3) {
		this.groupCode3 = (short) groupCode3;
	}
	public void setGroupCode4(int groupCode4) {
		this.groupCode4 = (short) groupCode4;
	}
}
