package ResourceManagers.MSDBManager.Placement;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

import bFM.Data;
import bFM.Utils;

public class MobConstantPlace implements Data
{
	protected int index = -1;
	protected float xPos; //First 2 Bytes
	protected float yPos; //Next 2 Bytes
	protected float zPos; //Next 2 Bytes
	protected float rotation; //Next 2 Bytes
	protected float spawnRadius; //Next 2 Bytes
	protected float num5; //Next 2 Bytes DespawnRadius???
	protected int MobGrouptCode2; //Next 2 Bytes
	protected int MobGroupCode1; //Next 2 Bytes
	protected int activationFlag2; //Next 2 Bytes
	protected int num12; //Next 2 Bytes
	protected int activationFlag1; //Next 2 Bytes
	protected int clearFlag; //Next 2 Bytes
	protected int deactivationFlag; //Next 2 Bytes
	protected int itemCode;//Next 2 Bytes
	public MobConstantPlace()
	{
		xPos = 0; //First 2 Bytes
		yPos = 0; //Next 2 Bytes
		zPos = 0; //Next 2 Bytes
		rotation = 0; //Next 2 Bytes
		spawnRadius = 30; //Next 2 Bytes
		num5 = 40; //Next 2 Bytes
		MobGrouptCode2 = 0; //Next 2 Bytes
		MobGroupCode1 = 0; //Next 2 Bytes
		activationFlag2 = -1; //Next 2 Bytes
		num12 = 0; //Next 2 Bytes
		activationFlag1 = -1; //Next 2 Bytes
		clearFlag = -1; //Next 2 Bytes
		deactivationFlag = -1; //Next 2 Bytes
		itemCode = -1;//
	}
	public MobConstantPlace(byte[] data, int index)
	{
		this.index = index;
		xPos = ByteBuffer.wrap(data).order(ByteOrder.BIG_ENDIAN).getFloat(0);
		yPos = ByteBuffer.wrap(data).order(ByteOrder.BIG_ENDIAN).getFloat(4);
		zPos = ByteBuffer.wrap(data).order(ByteOrder.BIG_ENDIAN).getFloat(8);
		rotation = ByteBuffer.wrap(data).order(ByteOrder.BIG_ENDIAN).getFloat(12);
		spawnRadius = ByteBuffer.wrap(data).order(ByteOrder.BIG_ENDIAN).getFloat(16);
		num5 = ByteBuffer.wrap(data).order(ByteOrder.BIG_ENDIAN).getFloat(20);
		MobGroupCode1 = bFM.Utils.getShort(data, 24);
		activationFlag1 = bFM.Utils.getShort(data, 26);
		MobGrouptCode2 = bFM.Utils.getShort(data, 28);
		activationFlag2 = bFM.Utils.getShort(data, 30);
		clearFlag = bFM.Utils.getShort(data, 32);
		deactivationFlag = bFM.Utils.getShort(data, 34);
		itemCode = bFM.Utils.getShort(data, 36);
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
		spawnRadius = Float.valueOf(line.substring(0, index));
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
		itemCode = Integer.valueOf(line.substring(0, index)).shortValue();
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
		spawnRadius = float2;
		num5 = float3;
		MobGroupCode1 = groupIndex1;
		MobGrouptCode2 = groupIndex2;
		this.activationFlag1 = activationFlag1;
		this.activationFlag2 = activationFlag2;
		this.clearFlag = clearFlag;
		this.deactivationFlag = deactivationFlag;
		itemCode = num42;
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
		spawnRadius = float2;
		num5 = float3;
		MobGroupCode1 = groupIndex1;
		MobGrouptCode2 = groupIndex2;
		this.activationFlag1 = activationFlag1;
		this.activationFlag2 = activationFlag2;
		this.clearFlag = clearFlag;
		this.deactivationFlag = deactivationFlag;
		itemCode = num42;
		num12 = num52;
		
	}
	public int getIndex()
	{
		return index;
	}
	public String toString()
	{
		return ""+xPos +", "+yPos +", "+zPos +", "+rotation +", "+spawnRadius +", "+num5 +", "+MobGroupCode1 +", "+activationFlag1 +", "+MobGrouptCode2 +", "+activationFlag2 +", "+clearFlag + ", "+
				deactivationFlag +", "+itemCode +", "+num12 +"\n";
	}
	public String toBMos()
	{
		if(index == -1)
		{
			return "Constant Placement: "+xPos +", "+yPos +", "+zPos +", "+rotation +", "+spawnRadius +", "+num5 +", "+activationFlag1+", "+activationFlag2  +", "+clearFlag + ", "+
					deactivationFlag +", "+itemCode +", "+num12 +"\n";
		}
		return "Constant Placement: "+index+", "+xPos +", "+yPos +", "+zPos +", "+rotation +", "+spawnRadius +", "+num5 +", "+activationFlag1+", "+activationFlag2  +", "+clearFlag + ", "+
		deactivationFlag +", "+itemCode +", "+num12 +"\n";
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
		ret = bFM.Utils.mergeArrays(ret, ByteBuffer.allocate(4).putFloat(spawnRadius).array());
		ret = bFM.Utils.mergeArrays(ret, ByteBuffer.allocate(4).putFloat(num5).array());
		ret = bFM.Utils.mergeArrays(ret, bFM.Utils.toByteArr(MobGroupCode1, 2));
		ret = bFM.Utils.mergeArrays(ret, bFM.Utils.toByteArr(activationFlag1, 2));
		ret = bFM.Utils.mergeArrays(ret, bFM.Utils.toByteArr(MobGrouptCode2, 2));
		ret = bFM.Utils.mergeArrays(ret, bFM.Utils.toByteArr(activationFlag2, 2));
		ret = bFM.Utils.mergeArrays(ret, bFM.Utils.toByteArr(clearFlag, 2));
		ret = bFM.Utils.mergeArrays(ret, bFM.Utils.toByteArr(deactivationFlag, 2));
		ret = bFM.Utils.mergeArrays(ret, bFM.Utils.toByteArr(itemCode, 2));
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
	public String toItem() 
	{
		if(itemCode==-1 || itemCode==0)
		{
			System.out.println("Const: " + index + " No Item\n"); 
			return "";
		}
		return "Constant Place Index: " + index + ", Item Code: " + itemCode + "\n";
	}
	public void addNullGroup(String line) 
	{
		int id = Utils.strToInt(line);
		if(MobGroupCode1 == -1) {MobGroupCode1 = id; MobGrouptCode2 = id;}
		else if(MobGrouptCode2 == MobGroupCode1) MobGrouptCode2 = id;
		else throw new IllegalArgumentException("Added too many Groups to Constant Place\n");
	}
	public void addGroup(MobGroup group) 
	{
		int id = group.getCode();
		if(MobGroupCode1 == -1 || id == MobGroupCode1) {MobGroupCode1 = id; MobGrouptCode2 = id;}
		else if(MobGrouptCode2 == MobGroupCode1 || id == MobGrouptCode2) MobGrouptCode2 = id;
		else throw new IllegalArgumentException("Added too many Groups to Constant Place\n");
		group.registerPlacement(this);
	}
	public void setGroupID(int oldId, int id) 
	{
		if(MobGroupCode1 == oldId) MobGroupCode1 = id;
		else if(MobGrouptCode2 == oldId) MobGrouptCode2 = id;
		else throw new IllegalArgumentException("Constant Place did not have group " + oldId + " -> " + id + " registerd. ID 1: " + MobGroupCode1 + " ID 2: " + MobGrouptCode2 + "\n");
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
		return 40;
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
	public float getSpawnRadius() {
		return spawnRadius;
	}
	public void setSpawnRadius(float spawnRadius) {
		this.spawnRadius = spawnRadius;
	}
	public float getNum5() {
		return num5;
	}
	public void setNum5(float num5) {
		this.num5 = num5;
	}
	public int getActivationFlag2() {
		return activationFlag2;
	}
	public void setActivationFlag2(int activationFlag2) {
		this.activationFlag2 = activationFlag2;
	}
	public int getNum12() {
		return num12;
	}
	public void setNum12(int num12) {
		this.num12 = num12;
	}
	public int getActivationFlag1() {
		return activationFlag1;
	}
	public void setActivationFlag1(int activationFlag1) {
		this.activationFlag1 = activationFlag1;
	}
	public int getClearFlag() {
		return clearFlag;
	}
	public void setClearFlag(int clearFlag) {
		this.clearFlag = clearFlag;
	}
	public int getDeactivationFlag() {
		return deactivationFlag;
	}
	public void setDeactivationFlag(int deactivationFlag) {
		this.deactivationFlag = deactivationFlag;
	}
	public int getItemCode() {
		return itemCode;
	}
	public void setItemCode(int itemCode) {
		this.itemCode = itemCode;
	}
	public void setIndex(int index) {
		this.index = index;
	}
}
