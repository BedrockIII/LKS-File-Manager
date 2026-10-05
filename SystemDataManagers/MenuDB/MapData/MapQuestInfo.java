package SystemDataManagers.MenuDB.MapData;

import java.nio.ByteBuffer;
import java.nio.charset.Charset;

import bFM.Data;
import bFM.Utils;

public class MapQuestInfo implements Data
{
	String name = "";
	int flag1 = -1;
	int flag2 = -1;
	int flag3 = -1;
	int flag4 = -1;
	int flag5 = -1;
	int flag6 = -1;
	int flag7 = -1;
	int flag8 = -1;
	int flag9 = -1;
	int flag10 = -1;
	public MapQuestInfo(String name, int flag1, int flag2, int flag3, int flag4, int flag5, int flag6, int flag7, int flag8, int flag9, int flag10)
	{
		this.name = name;
		this.flag1 = flag1;
		this.flag2 = flag2;
		this.flag3 = flag3;
		this.flag4 = flag4;
		this.flag5 = flag5;
		this.flag6 = flag6;
		this.flag7 = flag7;
		this.flag8 = flag8;
		this.flag9 = flag9;
		this.flag10 = flag10;
	}
	protected byte[] toNameBytes() 
	{
		return Utils.mergeArrays(name.getBytes(Charset.forName("Shift-JIS")), (byte)0x00);
	}
	protected byte[] toFlagBytes() 
	{
		ByteBuffer ret = ByteBuffer.allocate(40);
		ret.putInt(flag1);
		ret.putInt(flag2);
		ret.putInt(flag3);
		ret.putInt(flag4);
		ret.putInt(flag5);
		ret.putInt(flag6);
		ret.putInt(flag7);
		ret.putInt(flag8);
		ret.putInt(flag9);
		ret.putInt(flag10);
		return ret.array();
	}
	public void setData(byte[] data) 
	{
		throw new UnsupportedOperationException("setData(byte[] data) should not be called on type " + this.getClass());
	}
	public byte[] toBytes() 
	{
		throw new UnsupportedOperationException("toBytes() should not be called on type " + this.getClass());
	}
	public int getSize() 
	{
		throw new UnsupportedOperationException("getSize() should not be called on type " + this.getClass());
	}
	public String getName()
	{
		return name;
	}
	public int getFlag1() {
		return flag1;
	}
	public void setFlag1(int flag1) {
		this.flag1 = flag1;
	}
	public int getFlag2() {
		return flag2;
	}
	public void setFlag2(int flag2) {
		this.flag2 = flag2;
	}
	public int getFlag3() {
		return flag3;
	}
	public void setFlag3(int flag3) {
		this.flag3 = flag3;
	}
	public int getFlag4() {
		return flag4;
	}
	public void setFlag4(int flag4) {
		this.flag4 = flag4;
	}
	public int getFlag5() {
		return flag5;
	}
	public void setFlag5(int flag5) {
		this.flag5 = flag5;
	}
	public int getFlag6() {
		return flag6;
	}
	public void setFlag6(int flag6) {
		this.flag6 = flag6;
	}
	public int getFlag7() {
		return flag7;
	}
	public void setFlag7(int flag7) {
		this.flag7 = flag7;
	}
	public int getFlag8() {
		return flag8;
	}
	public void setFlag8(int flag8) {
		this.flag8 = flag8;
	}
	public int getFlag9() {
		return flag9;
	}
	public void setFlag9(int flag9) {
		this.flag9 = flag9;
	}
	public int getFlag10() {
		return flag10;
	}
	public void setFlag10(int flag10) {
		this.flag10 = flag10;
	}
	public void setName(String name) {
		this.name = name;
	}
}
