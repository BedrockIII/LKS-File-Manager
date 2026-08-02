package ResourceManagers.MSDBManager.Placement;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

import bFM.Data;
import bFM.Utils;

public class MobRandomPoint implements Data
{
	float xPos;
	float yPos;
	float zPos;
	float rotation;
	short ActivationFlag;
	short DeactivationFlag;
	public MobRandomPoint(byte[] data)
	{
		xPos = ByteBuffer.wrap(data).order(ByteOrder.BIG_ENDIAN).getFloat(0);
		yPos = ByteBuffer.wrap(data).order(ByteOrder.BIG_ENDIAN).getFloat(4);
		zPos = ByteBuffer.wrap(data).order(ByteOrder.BIG_ENDIAN).getFloat(8);
		rotation = ByteBuffer.wrap(data).order(ByteOrder.BIG_ENDIAN).getFloat(12);
		ActivationFlag = ByteBuffer.wrap(data).order(ByteOrder.BIG_ENDIAN).getShort(16);
		DeactivationFlag = ByteBuffer.wrap(data).order(ByteOrder.BIG_ENDIAN).getShort(18);
	}
	public MobRandomPoint(float xPos2, float yPos2, float zPos2, float rotation, short activationFlag, short deactivationFlag) 
	{
		xPos = xPos2;
		yPos = yPos2;
		zPos = zPos2;
		this.rotation = rotation;
		this.ActivationFlag = activationFlag;
		this.DeactivationFlag = deactivationFlag;
	}
	public MobRandomPoint() 
	{
		xPos = 9999;
		yPos= 9999;
		zPos = 9999;
		rotation = 0;
		ActivationFlag = -1;
		DeactivationFlag = -1;
	}
	public String toBrm()
	{
		return "\tRandom Position: "+xPos +", "+yPos +", "+zPos +", "+rotation +", "+ActivationFlag +", "+DeactivationFlag +";\n";
	}
	public String toString()
	{
		return ""+xPos +" ,"+yPos +" ,"+zPos +" ,"+rotation +", "+ActivationFlag +", "+DeactivationFlag +"\n";
	}
	public byte[] toBytes()
	{
		byte[] ret = ByteBuffer.allocate(4).putFloat(xPos).array();
		ret = Utils.mergeArrays(ret, ByteBuffer.allocate(4).putFloat(yPos).array());
		ret = Utils.mergeArrays(ret, ByteBuffer.allocate(4).putFloat(zPos).array());
		ret = Utils.mergeArrays(ret, ByteBuffer.allocate(4).putFloat(rotation).array());
		ret = Utils.mergeArrays(ret, ByteBuffer.allocate(2).putShort(ActivationFlag).array());
		ret = Utils.mergeArrays(ret, ByteBuffer.allocate(2).putShort(DeactivationFlag).array());
		return ret;
	}
	public boolean fitsFilter(int xMin, int xMax, int zMin, int zMax, boolean filterOut, boolean hideFillerSpots) 
	{
		if(hideFillerSpots&&xPos == 9999&&yPos == 0&&zPos == 9999)
		{
			return false;
		}
		if(filterOut&&(xMax>xPos)&&(xPos>xMin)&&(zMax>zPos)&&(zPos>zMin))
		{
			return false;
		}
		if(!filterOut&&!((xMax>xPos)&&(xPos>xMin)&&(zMax>zPos)&&(zPos>zMin)))
		{
			return false;
		}
		return true;
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
		return 20;
	}
	public float getxPos() {
		return xPos;
	}
	public void setxPos(float xPos) {
		this.xPos = xPos;
	}
	public float getyPos() {
		return yPos;
	}
	public void setyPos(float yPos) {
		this.yPos = yPos;
	}
	public float getzPos() {
		return zPos;
	}
	public void setzPos(float zPos) {
		this.zPos = zPos;
	}
	public float getRotation() {
		return rotation;
	}
	public void setRotation(float rotation) {
		this.rotation = rotation;
	}
	public short getActivationFlag() {
		return ActivationFlag;
	}
	public void setActivationFlag(int activationFlag) {
		ActivationFlag = (short) activationFlag;
	}
	public short getDeactivationFlag() {
		return DeactivationFlag;
	}
	public void setDeactivationFlag(int deactivationFlag) {
		DeactivationFlag = (short) deactivationFlag;
	}
}
