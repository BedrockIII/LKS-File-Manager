package MSDBManager;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

public class MobConstantPlace 
{
	int index = -1;
	float xPos; //First 2 Bytes
	float yPos; //Next 2 Bytes
	float zPos; //Next 2 Bytes
	float rotation; //Next 2 Bytes
	float num4; //Next 2 Bytes
	float num5; //Next 2 Bytes
	int MobGrouptCode2; //Next 2 Bytes
	int MobGroupCode1; //Next 2 Bytes
	int activationFlag2; //Next 2 Bytes
	int num12; //Next 2 Bytes
	int activationFlag1; //Next 2 Bytes
	int clearFlag; //Next 2 Bytes
	int deactivationFlag; //Next 2 Bytes
	int num11; //Next 2 Bytes
	public MobConstantPlace(byte[] data, int index)
	{
		this.index = index;
		xPos = ByteBuffer.wrap(data).order(ByteOrder.BIG_ENDIAN).getFloat(0);
		yPos = ByteBuffer.wrap(data).order(ByteOrder.BIG_ENDIAN).getFloat(4);
		zPos = ByteBuffer.wrap(data).order(ByteOrder.BIG_ENDIAN).getFloat(8);
		rotation = ByteBuffer.wrap(data).order(ByteOrder.BIG_ENDIAN).getFloat(12);
		num4 = ByteBuffer.wrap(data).order(ByteOrder.BIG_ENDIAN).getFloat(16);
		num5 = ByteBuffer.wrap(data).order(ByteOrder.BIG_ENDIAN).getFloat(20);
		MobGroupCode1 = bFM.Utils.getShort(data, 24);
		activationFlag1 = bFM.Utils.getShort(data, 26);
		MobGrouptCode2 = bFM.Utils.getShort(data, 28);
		activationFlag2 = bFM.Utils.getShort(data, 30);
		clearFlag = bFM.Utils.getShort(data, 32);
		deactivationFlag = bFM.Utils.getShort(data, 34);
		num11 = bFM.Utils.getShort(data, 36);
		num12 = bFM.Utils.getShort(data, 38);
	}
	public MobConstantPlace(String line)
	{
		String goodNumbers = "1234567890.-,";
		String tempLine = "";
		for(int i = 0; i<line.length(); i++)
		{
			if(goodNumbers.indexOf(line.charAt(i))!=' ') tempLine = tempLine + line.charAt(i);
		}
		line = tempLine;
		int index = line.indexOf(',');
		xPos = Float.valueOf(line.substring(0, index));
		line = line.substring(index,line.length()-1);
		index = line.indexOf(',');
		yPos = Float.valueOf(line.substring(0, index));
		line = line.substring(index,line.length()-1);
		index = line.indexOf(',');
		zPos = Float.valueOf(line.substring(0, index));
		line = line.substring(index,line.length()-1);
		index = line.indexOf(',');
		rotation = Float.valueOf(line.substring(0, index));
		line = line.substring(index,line.length()-1);
		index = line.indexOf(',');
		num4 = Float.valueOf(line.substring(0, index));
		line = line.substring(index,line.length()-1);
		index = line.indexOf(',');
		num5 = Float.valueOf(line.substring(0, index));
		line = line.substring(index,line.length()-1);
		index = line.indexOf(',');
		MobGroupCode1 = Integer.valueOf(line.substring(0, index)).shortValue();
		line = line.substring(index,line.length()-1);
		index = line.indexOf(',');
		activationFlag1 = Integer.valueOf(line.substring(0, index)).shortValue();
		line = line.substring(index,line.length()-1);
		index = line.indexOf(',');
		MobGrouptCode2 = Integer.valueOf(line.substring(0, index)).shortValue();
		line = line.substring(index,line.length()-1);
		index = line.indexOf(',');
		activationFlag2 = Integer.valueOf(line.substring(0, index)).shortValue();
		line = line.substring(index,line.length()-1);
		index = line.indexOf(',');
		clearFlag = Integer.valueOf(line.substring(0, index)).shortValue();
		line = line.substring(index,line.length()-1);
		index = line.indexOf(',');
		deactivationFlag = Integer.valueOf(line.substring(0, index)).shortValue();
		line = line.substring(index,line.length()-1);
		index = line.indexOf(',');
		num11 = Integer.valueOf(line.substring(0, index)).shortValue();
		line = line.substring(index,line.length()-1);
		index = line.indexOf(',');
		if(index!=-1)num12 = Integer.valueOf(line.substring(0, index)).shortValue();
		else num12 = Integer.valueOf(line).shortValue();
	}

	public MobConstantPlace(float placeXPos, float placeYPos, float placeZPos, float placeRotation, float float2,
			float float3, short groupIndex1, short activationFlag1, short groupIndex2, short activationFlag2, short clearFlag,
			short deactivationFlag, short num42, short num52) 
	{
		xPos = placeXPos;
		yPos = placeYPos;
		zPos = placeZPos;
		rotation = placeRotation;
		num4 = float2;
		num5 = float3;
		MobGroupCode1 = groupIndex1;
		MobGrouptCode2 = groupIndex2;
		this.activationFlag1 = activationFlag1;
		this.activationFlag2 = activationFlag2;
		this.clearFlag = clearFlag;
		this.deactivationFlag = deactivationFlag;
		num11 = num42;
		num12 = num52;
		
	}
	public MobConstantPlace(int index, float placeXPos, float placeYPos, float placeZPos, float placeRotation, float float2,
			float float3, short groupIndex1, short activationFlag1, short groupIndex2, short activationFlag2, short clearFlag,
			short deactivationFlag, short num42, short num52) 
	{
		this.index = index;
		xPos = placeXPos;
		yPos = placeYPos;
		zPos = placeZPos;
		rotation = placeRotation;
		num4 = float2;
		num5 = float3;
		MobGroupCode1 = groupIndex1;
		MobGrouptCode2 = groupIndex2;
		this.activationFlag1 = activationFlag1;
		this.activationFlag2 = activationFlag2;
		this.clearFlag = clearFlag;
		this.deactivationFlag = deactivationFlag;
		num11 = num42;
		num12 = num52;
		
	}
	public int getIndex()
	{
		return index;
	}
	public String toString()
	{
		return ""+xPos +", "+yPos +", "+zPos +", "+rotation +", "+num4 +", "+num5 +", "+MobGroupCode1 +", "+activationFlag1 +", "+MobGrouptCode2 +", "+activationFlag2 +", "+clearFlag + ", "+
				deactivationFlag +", "+num11 +", "+num12 +"\n";
	}
	public String toBMos()
	{
		if(index == -1)
		{
			return "Constant Monster: "+xPos +", "+yPos +", "+zPos +", "+rotation +", "+num4 +", "+num5 +", "+activationFlag1+", "+activationFlag2  +", "+clearFlag + ", "+
					deactivationFlag +", "+num11 +", "+num12 +"\n";
		}
		return "Constant Monster: "+index+", "+xPos +", "+yPos +", "+zPos +", "+rotation +", "+num4 +", "+num5 +", "+activationFlag1+", "+activationFlag2  +", "+clearFlag + ", "+
		deactivationFlag +", "+num11 +", "+num12 +"\n";
	}
	public int getGroup1()
	{
		return  MobGroupCode1;
	}
	public int getGroup2()
	{
		return MobGrouptCode2;
	}
	
	public byte[] toBytes()
	{
		byte[] ret = ByteBuffer.allocate(4).putFloat(xPos).array();
		ret = bFM.Utils.mergeArrays(ret, ByteBuffer.allocate(4).putFloat(yPos).array());
		ret = bFM.Utils.mergeArrays(ret, ByteBuffer.allocate(4).putFloat(zPos).array());
		ret = bFM.Utils.mergeArrays(ret, ByteBuffer.allocate(4).putFloat(rotation).array());
		ret = bFM.Utils.mergeArrays(ret, ByteBuffer.allocate(4).putFloat(num4).array());
		ret = bFM.Utils.mergeArrays(ret, ByteBuffer.allocate(4).putFloat(num5).array());
		ret = bFM.Utils.mergeArrays(ret, bFM.Utils.toByteArr(MobGroupCode1, 2));
		ret = bFM.Utils.mergeArrays(ret, bFM.Utils.toByteArr(activationFlag1, 2));
		ret = bFM.Utils.mergeArrays(ret, bFM.Utils.toByteArr(MobGrouptCode2, 2));
		ret = bFM.Utils.mergeArrays(ret, bFM.Utils.toByteArr(activationFlag2, 2));
		ret = bFM.Utils.mergeArrays(ret, bFM.Utils.toByteArr(clearFlag, 2));
		ret = bFM.Utils.mergeArrays(ret, bFM.Utils.toByteArr(deactivationFlag, 2));
		ret = bFM.Utils.mergeArrays(ret, bFM.Utils.toByteArr(num11, 2));
		ret = bFM.Utils.mergeArrays(ret, bFM.Utils.toByteArr(num12, 2));
		//if(ret.length!=40)System.out.println("constplc wrong length! " + Arrays.toString(ret));
		if(ret.length!=40)System.out.println(this.toString());
		else 
		{
			//System.out.println("Index: "+ index);
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
