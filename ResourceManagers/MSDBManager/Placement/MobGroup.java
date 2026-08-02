package ResourceManagers.MSDBManager.Placement;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.ArrayList;

import ResourceManagers.MSDBManager.Placement.MobObject.ObjectDefault;
import bFM.Data;
import bFM.Utils;

public class MobGroup implements Data//probably who has attack bonus
{
	protected int groupIndex; //First 2 Bytes
	protected int num1; //Next 2 Bytes
	//protected int objectIndex; //Next 2 Bytes
	//protected int objectCount; //Next 2 Bytes
	protected int num4; //Next 2 Bytes
	protected int groupNumber; //Next 2 Bytes
	protected int num6; //Next 2 Bytes
	protected int num7; //Next 2 Bytes
	protected float num8; //Next 4 Bytes
	protected int num9; //Next 2 Bytes
	ArrayList<MobObject> Objects = new ArrayList<MobObject>();
	MobConstantPlace placement = null;
	protected MobGroup()
	{
		Objects = new ArrayList<MobObject>();
	}
	public MobGroup(byte[] data, ArrayList<MobObject> allObjects)
	{
		Objects = new ArrayList<MobObject>();
		groupIndex = (int)bFM.Utils.getShort(data, 0);
		num1 = (int)bFM.Utils.getShort(data, 2);
		int objectIndex = (int)bFM.Utils.getShort(data, 4);
		int objectCount = (int)bFM.Utils.getShort(data, 6);
		num4 = (int)bFM.Utils.getShort(data, 8);
		groupNumber = (int)bFM.Utils.getShort(data, 10);
		num6 = (int)bFM.Utils.getShort(data, 12);
		num7 = (int)bFM.Utils.getShort(data, 14);
		num8 = (ByteBuffer.wrap(data).order(ByteOrder.BIG_ENDIAN).getFloat(16));
		for(int i = 0; i < objectCount; i ++)
		{
			Objects.add(allObjects.get(i + objectIndex));
		}
	}
	public boolean groupIndex(int code)
	{
		if(code==groupIndex)return true;
		return false;
	}
	public MobGroup(int groupIndex, int num2, int num122, int groupNumber, int num14, int num15, float num16) 
	{
		this.groupIndex = groupIndex;
		this.num1 = num2;
		this.num4 = num122;
		this.groupNumber = groupNumber;
		this.num6 = num14;
		this.num7 = num15;
		this.num8 = num16;
	}
	public MobGroup(int index, int groupNumber2) 
	{
		groupIndex = index;
		num1 = 3; //Next 2 Bytes
		num4 = 0; //Next 2 Bytes
		num6 = -1; //Next 2 Bytes
		num7 = 0; //Next 2 Bytes
		num8 = (float) 5.0; //Next 4 Bytes
		num9 = 0;
		groupNumber = groupNumber2;
	}
	public String toString()
	{
		if(placement != null) return placement.toBMos() + bMos();
		return bMos2();
	}
	public byte[] toBytes(int objectIndex) 
	{
		byte[] ret = bFM.Utils.toByteArr(groupIndex,2);
		ret = bFM.Utils.mergeArrays(ret, bFM.Utils.toByteArr(num1,2));
		ret = bFM.Utils.mergeArrays(ret, bFM.Utils.toByteArr(objectIndex,2));
		ret = bFM.Utils.mergeArrays(ret, bFM.Utils.toByteArr(Objects.size(),2));
		ret = bFM.Utils.mergeArrays(ret, bFM.Utils.toByteArr(num4,2));
		ret = bFM.Utils.mergeArrays(ret, bFM.Utils.toByteArr(groupNumber,2));
		ret = bFM.Utils.mergeArrays(ret, bFM.Utils.toByteArr(num6,2));
		ret = bFM.Utils.mergeArrays(ret, bFM.Utils.toByteArr(num7,2));
		ret = bFM.Utils.mergeArrays(ret, ByteBuffer.allocate(4).putFloat(num8).array());
		return ret;
	}
	public int getObjectCount()
	{
		return Objects.size();
	}
	public String bMos() 
	{
		String ret =  "\tMission Group: "+ groupIndex + ", "+num1+", "+num4 +", "+groupNumber +", "+num6 +", "+num7 +", "+num8 +"\n";
		for(MobObject object : Objects)
		{
			ret += object.bMos();
		}
		return ret;
	}	
	public int getCode() 
	{
		return groupIndex;
	}
	public String bMos2() 
	{
		String ret =  "Unsorted Group: "+groupIndex +", "+num1+", "+num4 +", "+groupNumber +", "+num6 +", "+num7 +", "+num8+"\n";
		for(MobObject object : Objects)
		{
			ret += object.bMos();
		}
		return ret;
	}
	public void setGroupID(int id) 
	{
		int oldId = groupIndex;
		groupIndex = id;
		if(placement!=null)placement.setGroupID(oldId, id);
	}
	public int getGroupNumber()
	{
		return groupNumber;
	}
	public byte[] getObjectBytes() 
	{
		byte[] ret = null;
		for(MobObject o : Objects)
		{
			ret = Utils.mergeArrays(ret, o.toBytes());
		}
		return ret;
	}
	public void addObject(MobObject parseObjectLine) 
	{
		Objects.add(parseObjectLine);
	}
	public void registerPlacement(MobConstantPlace mobConstantPlace) 
	{
		if(placement != null) System.err.println("Group ID: " + groupIndex + " already has a registered Constant Placement\n");
		placement = mobConstantPlace;
	}
	public ArrayList<MobObject> getObjects() 
	{
		// TODO Auto-generated method stub
		return Objects;
	}
	public boolean equals(String name) 
	{
		throw new UnsupportedOperationException("equals() should not be called on type " + this.getClass());
	}
	public void setData(byte[] data) 
	{
		throw new UnsupportedOperationException("setData(byte[] data) should not be called on type " + this.getClass());
	}
	public byte[] toBytes() 
	{
		throw new UnsupportedOperationException("toBytes() should not be called on type " + this.getClass());
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
		throw new UnsupportedOperationException("getSize() should not be called on type " + this.getClass());
	}
	public void unregisterPlacement() 
	{
		placement = null;
	}
	public int getGroupIndex() {
		return groupIndex;
	}
	public void setGroupIndex(int groupIndex) {
		this.groupIndex = groupIndex;
	}
	public int getNum1() {
		return num1;
	}
	public void setNum1(int num1) {
		this.num1 = num1;
	}
	public int getNum4() {
		return num4;
	}
	public void setNum4(int num4) {
		this.num4 = num4;
	}
	public int getNum6() {
		return num6;
	}
	public void setNum6(int num6) {
		this.num6 = num6;
	}
	public int getNum7() {
		return num7;
	}
	public void setNum7(int num7) {
		this.num7 = num7;
	}
	public float getNum8() {
		return num8;
	}
	public void setNum8(float num8) {
		this.num8 = num8;
	}
	public int getNum9() {
		return num9;
	}
	public void setNum9(int num9) {
		this.num9 = num9;
	}
	public void setGroupNumber(int groupNumber) {
		this.groupNumber = groupNumber;
	}
	public MobConstantPlace getPlacement() {
		// TODO Auto-generated method stub
		return placement;
	}
	public MobObject addObject(ObjectDefault objectType) 
	{
		MobObject obj = new MobObject(objectType);
		Objects.add(obj);
		return obj;
	}
}
