package SystemDataManagers.MenuDB.MapData;

import java.nio.ByteBuffer;

import bFM.Data;
import bFM.Utils;

public class MapArea implements Data
{
	String name = "";
	String image = "";
	int MapLevel = -1;
	int ActivationFlag1 = -1;
	int ActivationFlag2 = -1;
	int ActivationFlag3 = -1;
	int flag5 = -1;
	public MapArea(String name, String image, int flag1, int flag2, int flag3, int flag4, int flag5)
	{
		this.name = name;
		this.image = image;
		this.MapLevel = flag1;
		this.ActivationFlag1 = flag2;
		this.ActivationFlag2 = flag3;
		this.ActivationFlag3 = flag4;
		this.flag5 = flag5;
	}
	protected byte[] toNameBytes() 
	{
		return Utils.mergeArrays(Utils.encodeStringToBytes(name), (byte)0x00);
	}
	protected byte[] toImageBytes() 
	{
		return Utils.mergeArrays(Utils.encodeStringToBytes(image), (byte)0x00);
	}
	protected byte[] toFlagBytes() 
	{
		ByteBuffer ret = ByteBuffer.allocate(20);
		ret.putInt(MapLevel);
		ret.putInt(ActivationFlag1);
		ret.putInt(ActivationFlag2);
		ret.putInt(ActivationFlag3);
		ret.putInt(flag5);
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
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public int getFlag1() {
		return MapLevel;
	}
	public void setFlag1(int flag1) {
		this.MapLevel = flag1;
	}
	public int getFlag2() {
		return ActivationFlag1;
	}
	public void setFlag2(int flag2) {
		this.ActivationFlag1 = flag2;
	}
	public int getFlag3() {
		return ActivationFlag2;
	}
	public void setFlag3(int flag3) {
		this.ActivationFlag2 = flag3;
	}
	public int getFlag4() {
		return ActivationFlag3;
	}
	public void setFlag4(int flag4) {
		this.ActivationFlag3 = flag4;
	}
	public int getFlag5() {
		return flag5;
	}
	public void setFlag5(int flag5) {
		this.flag5 = flag5;
	}
	public void setName(String name) {
		this.name = name;
	}
}
