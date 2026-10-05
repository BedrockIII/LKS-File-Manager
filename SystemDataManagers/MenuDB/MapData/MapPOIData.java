package SystemDataManagers.MenuDB.MapData;

import java.nio.ByteBuffer;

import bFM.Data;
import bFM.Utils;

public class MapPOIData implements Data
{
	String name = "";
	String message = "";
	String image = "";
	short PosX = 0;
	short PosY = 0;
	int flag1 = -1;
	int ActivationFlag1 = -1;
	int ActivationFlag2 = -1;
	int DeactivationFlag1 = -1;
	int DeactivationFlag2 = -1;
	int flag6 = -1;
	public MapPOIData(String name, String message, String image, short posX, short posY, int flag1, int flag2, int flag3, int flag4, int flag5, int flag6)
	{
		this.name = name;
		this.message = message;
		this.image = image;
		this.PosX = posX;
		this.PosY = posY;
		this.flag1 = flag1;
		this.ActivationFlag1 = flag2;
		this.ActivationFlag2 = flag3;
		this.DeactivationFlag1 = flag4;
		this.DeactivationFlag2 = flag5;
		this.flag6 = flag6;
	}
	public byte[] toNameBytes() 
	{
		return Utils.mergeArrays(Utils.encodeStringToBytes(name), (byte)0x00);
	}
	public byte[] toMessageBytes() 
	{
		return Utils.mergeArrays(Utils.encodeStringToBytes(message), (byte)0x00);
	}
	public byte[] toImageBytes() 
	{
		return Utils.mergeArrays(Utils.encodeStringToBytes(image), (byte)0x00);
	}
	public byte[] toPositionBytes()
	{
		ByteBuffer ret = ByteBuffer.allocate(4);
		ret.putShort(PosX);
		ret.putShort(PosY);
		return ret.array();
	}
	public byte[] toFlagBytes() 
	{
		ByteBuffer ret = ByteBuffer.allocate(24);
		ret.putInt(flag1);
		ret.putInt(ActivationFlag1);
		ret.putInt(ActivationFlag2);
		ret.putInt(DeactivationFlag1);
		ret.putInt(DeactivationFlag2);
		ret.putInt(flag6);
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
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public short getPosX() {
		return PosX;
	}
	public void setPosX(short posX) {
		PosX = posX;
	}
	public void setPosX(int posX) {
		PosX = (short)posX;
	}
	public short getPosY() {
		return PosY;
	}
	public void setPosY(short posY) {
		PosY = posY;
	}
	public void setPosY(int posY) {
		PosY = (short)posY;
	}
	public int getFlag1() {
		return flag1;
	}
	public void setFlag1(int flag1) {
		this.flag1 = flag1;
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
		return DeactivationFlag1;
	}
	public void setFlag4(int flag4) {
		this.DeactivationFlag1 = flag4;
	}
	public int getFlag5() {
		return DeactivationFlag2;
	}
	public void setFlag5(int flag5) {
		this.DeactivationFlag2 = flag5;
	}
	public int getFlag6() {
		return flag6;
	}
	public void setFlag6(int flag6) {
		this.flag6 = flag6;
	}
	public void setName(String name) {
		this.name = name;
	}
}
