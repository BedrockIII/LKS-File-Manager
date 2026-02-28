package MSDBManager;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

public class MobRandomPoint 
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
		ret = mergeArrays(ret, ByteBuffer.allocate(4).putFloat(yPos).array());
		ret = mergeArrays(ret, ByteBuffer.allocate(4).putFloat(zPos).array());
		ret = mergeArrays(ret, ByteBuffer.allocate(4).putFloat(rotation).array());
		ret = mergeArrays(ret, ByteBuffer.allocate(2).putShort(ActivationFlag).array());
		ret = mergeArrays(ret, ByteBuffer.allocate(2).putShort(DeactivationFlag).array());
		return ret;
	}
	public static byte[] mergeArrays(byte[] main, byte[] add)
	{
		if(add==null) return main;
		byte[] ret = new byte[main.length+add.length];
		for(int i = 0; i < main.length; i++)
		{
			ret[i] = main[i];
		}
		for(int i = 0; i < add.length; i++)
		{
			ret[i+main.length] = add[i];
		}
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
}
