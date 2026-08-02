package ResourceManagers.MSDBManager.Placement;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

import ResourceManagers.ItemDatabaseManager.Item;
import ResourceManagers.ItemDatabaseManager.itemDatabaseManager;
import ResourceManagers.MSDBManager.ExtractionTester;
import bFM.Data;

public class MobObject implements Data
{
	public enum ObjectDefault
	{
		OBJECT("Inanimate Object"),
		STILLUMA("Stationary Enemy"),
		WANDERUMA("Wandering Enemy");
		
		
		final String text;
		ObjectDefault(String text)
		{
			this.text = text;
		}
		public String toString()
		{
			return text;
		}
	}
	protected float xOffset; //First 2 Bytes //these first 3 MAY be the offsets for the target points, like where onii men throw pots
	protected float yOffset; //Next 2 Bytes
	protected float zOffset; //Next 2 Bytes
	protected float rotation; //Next 2 Bytes//probably the actual rotation
	protected float num4; //Next 2 Bytes
	protected int mobModNumber; //Next 2 Bytes
	protected int numberOfSubObjects; //Next 2 Bytes
	protected float RadiusOfView; //Next 2 Bytes, normally same as num4
	protected float DegreesOfView; //Next 2 Bytes
	protected int AiCode; //Next 2 Bytes
	protected int deathEffects; //Next 2 Bytes, 1 or 0. 1 = kill rest of group, 0 = not do that
	protected int enemyDrop; //Spawn this mob Code when dead
	protected int itemDrop; //Spawn this item Code when dead
	protected MobObject()
	{
	}
	public MobObject(byte[] data)
	{
		xOffset = ByteBuffer.wrap(data).order(ByteOrder.BIG_ENDIAN).getFloat(0);
		yOffset = ByteBuffer.wrap(data).order(ByteOrder.BIG_ENDIAN).getFloat(4);
		zOffset = ByteBuffer.wrap(data).order(ByteOrder.BIG_ENDIAN).getFloat(8);
		rotation = ByteBuffer.wrap(data).order(ByteOrder.BIG_ENDIAN).getFloat(12);
		num4 = ByteBuffer.wrap(data).order(ByteOrder.BIG_ENDIAN).getFloat(16);
		mobModNumber = (int)getShort(data, 20);
		numberOfSubObjects = (int)getShort(data, 22);
		RadiusOfView = ByteBuffer.wrap(data).order(ByteOrder.BIG_ENDIAN).getFloat(24);
		DegreesOfView = ByteBuffer.wrap(data).order(ByteOrder.BIG_ENDIAN).getFloat(28);
		AiCode = (int)getShort(data, 32);
		deathEffects = (int)getShort(data, 34);
		enemyDrop = (int)getShort(data, 36);
		itemDrop = (int)getShort(data, 38);
	}
	private int getShort(byte[] data, int index)
	{
		if(data==null)
		{
			return -1;
		}
		if(data.length<index+2)
		{
			return -1;
		}
		if(ByteBuffer.wrap(data).order(ByteOrder.BIG_ENDIAN).getShort(0)==-1)
		{
			return -1;
		}
		int ret = (int)data[index];
		if(ret<0)ret+=256;
		ret*=256;
		int ret2 =(int)data[index+1];
		if(ret2<0)ret2+=256;
		if(ret+ret2==65535) return -1;
		return ret+ret2;
	}
	public MobObject(String line)
	{
		String goodNumbers = "1234567890-.,";
		String tempLine = "";
		for(int i = 0; i<line.length(); i++)
		{
			if(goodNumbers.indexOf(line.charAt(i))!=' ') tempLine = tempLine + line.charAt(i);
		}
		line = tempLine;
		int index = line.indexOf(',');
		xOffset = Float.valueOf(line.substring(0, index));
		line = line.substring(index,line.length()-1);
		index = line.indexOf(',');
		yOffset = Float.valueOf(line.substring(0, index));
		line = line.substring(index,line.length()-1);
		index = line.indexOf(',');
		zOffset = Float.valueOf(line.substring(0, index));
		line = line.substring(index,line.length()-1);
		index = line.indexOf(',');
		rotation = Float.valueOf(line.substring(0, index));
		line = line.substring(index,line.length()-1);
		index = line.indexOf(',');
		num4 = Float.valueOf(line.substring(0, index));
		line = line.substring(index,line.length()-1);
		index = line.indexOf(',');
		mobModNumber = Integer.valueOf(line.substring(0, index)).shortValue();
		line = line.substring(index,line.length()-1);
		index = line.indexOf(',');
		numberOfSubObjects = Integer.valueOf(line.substring(0, index)).shortValue();
		line = line.substring(index,line.length()-1);
		index = line.indexOf(',');
		RadiusOfView = Float.valueOf(line.substring(0, index));
		line = line.substring(index,line.length()-1);
		index = line.indexOf(',');
		DegreesOfView = Float.valueOf(line.substring(0, index));
		line = line.substring(index,line.length()-1);
		index = line.indexOf(',');
		AiCode = Integer.valueOf(line.substring(0, index)).shortValue();
		line = line.substring(index,line.length()-1);
		index = line.indexOf(',');
		deathEffects = Integer.valueOf(line.substring(0, index)).shortValue();
		line = line.substring(index,line.length()-1);
		index = line.indexOf(',');
		enemyDrop = Integer.valueOf(line.substring(0, index)).shortValue();
		line = line.substring(index,line.length()-1);
		index = line.indexOf(',');
		if(index!=-1)itemDrop = Integer.valueOf(line.substring(0, index)).shortValue();
		else itemDrop = Integer.valueOf(line).shortValue();
	}
	public MobObject(float float4, float float5, float float6, float float7, float float8, short num32, short num62,
			float float9, float float10, short num7, short num82, short num92, short enemyDrop2) 
	{
		xOffset = float4;
		yOffset = float5;
		zOffset = float6;
		rotation = float7;
		num4 = float8;
		mobModNumber = num32;
		numberOfSubObjects = num62;
		RadiusOfView = float9;
		DegreesOfView = float10;
		AiCode = num7;
		deathEffects = num82;
		enemyDrop = num92;
		itemDrop = enemyDrop2;
	}
	public MobObject(ObjectDefault objectType) 
	{
		xOffset = 0;
		yOffset = 0;
		zOffset = 0;
		rotation = 0;
		mobModNumber = 1;
		num4 = (float) 6.0;
		numberOfSubObjects = 0;
		deathEffects = 0;
		enemyDrop = -1;
		itemDrop = -1;
		switch(objectType)
		{
		
		case STILLUMA:
			RadiusOfView = 5;
			DegreesOfView = 270;
			AiCode = 2001;
			break;
		case WANDERUMA:
			RadiusOfView = 5;
			DegreesOfView = 270;
			AiCode = 1001;
			break;
		case OBJECT:
			//Default
		default:
			RadiusOfView = 0;
			DegreesOfView = 0;
			AiCode = 0;
			break;
		
		}
	}
	public boolean equals(float float4, float float5, float float6, float float7, float float8, short num32, short num62, float float9, float float10, short num7, short num82, short num92, short enemyDrop2) 
	{
		if(xOffset != float4) return false;
		if(yOffset != float5) return false;
		if(zOffset != float6) return false;
		if(rotation != float7) return false;
		if(num4 != float8) return false;
		if(mobModNumber != num32) return false;
		if(numberOfSubObjects != num62) return false;
		if(RadiusOfView != float9) return false;
		if(DegreesOfView != float10) return false;
		if(AiCode != num7) return false;
		if(deathEffects != num82) return false;
		if(enemyDrop != num92) return false;
		if(itemDrop != enemyDrop2) return false;
		return true;
	}
	public String toString()
	{
		return "Random Object: "+xOffset +", "+yOffset +", "+zOffset +", "+rotation +", "+num4 +", "+mobModNumber+", "+numberOfSubObjects +", "+RadiusOfView +", "+DegreesOfView +", "+AiCode +", "+deathEffects+", "+enemyDrop+", "+itemDrop+"\n";
	}
	public static byte[] mergeArrays(byte[] main, byte[] add)
	{
		if(add==null) return main;
		if(main==null) return add;
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
	private byte[] toByteArr(int input, int arrLength) 
	{
		if(input>=0)
		{
			byte[] ret = new byte[arrLength];
			for(int i = 1; i<=arrLength; i++)
			{
				ret[arrLength-i] = (byte) (input%256);
				input/=256;
				
				
			}
			return ret;
		}
		if(input==-1)
			return new byte[]{(byte) 0xff, (byte) 0xff};
		return toByteArr(65536+input, arrLength);
	}
	public byte[] toBytes()
	{
		byte[] ret = ByteBuffer.allocate(4).putFloat(xOffset).array();
		ret = mergeArrays(ret, ByteBuffer.allocate(4).putFloat(yOffset).array());
		ret = mergeArrays(ret, ByteBuffer.allocate(4).putFloat(zOffset).array());
		ret = mergeArrays(ret, ByteBuffer.allocate(4).putFloat(rotation).array());
		ret = mergeArrays(ret, ByteBuffer.allocate(4).putFloat(num4).array());
		ret = mergeArrays(ret, toByteArr(mobModNumber,2));
		ret = mergeArrays(ret, toByteArr(numberOfSubObjects,2));
		ret = mergeArrays(ret, ByteBuffer.allocate(4).putFloat(RadiusOfView).array());
		ret = mergeArrays(ret, ByteBuffer.allocate(4).putFloat(DegreesOfView).array());
		ret = mergeArrays(ret, toByteArr(AiCode,2));
		ret = mergeArrays(ret, toByteArr(deathEffects,2));
		ret = mergeArrays(ret, toByteArr(enemyDrop,2));
		ret = mergeArrays(ret, toByteArr(itemDrop,2));
		return ret;
	}
	public String bMos() 
	{
		return "\t\tMission Object: "+xOffset +", "+yOffset +", "+zOffset +", "+rotation +", "+num4 +", "+mobModNumber+", "+numberOfSubObjects +", "+RadiusOfView +", "+DegreesOfView +", "+AiCode +", "+deathEffects+", "+enemyDrop+", "+itemDrop +"\n";
	}
	public int getModCode() 
	{
		return mobModNumber;
	}
	public String toItem() 
	{
		if(itemDrop == -1 || itemDrop == 0) return "";
		String ret = ", Item Code: " + itemDrop;
		if(ExtractionTester.items != null)
		{
			itemDatabaseManager items = ExtractionTester.items;
			Item item = items.getItemByCode(itemDrop);
			ret += ", " + item.getNewslogName();
			if((itemDrop >= 2340 && itemDrop <= 2506) || (itemDrop >= 2574 && itemDrop <= 2576))//if special item unsubmitted
			{
				ret += ", 0";
			}
			else
			{
				ret += ", " + item.getPrice();
			}
		}
		return ret;
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
		throw new UnsupportedOperationException("getSize() should not be called on type " + this.getClass());
	}
	public float getxOffset() {
		return xOffset;
	}
	public void setxOffset(float xOffset) {
		this.xOffset = xOffset;
	}
	public float getyOffset() {
		return yOffset;
	}
	public void setyOffset(float yOffset) {
		this.yOffset = yOffset;
	}
	public float getzOffset() {
		return zOffset;
	}
	public void setzOffset(float zOffset) {
		this.zOffset = zOffset;
	}
	public float getRotation() {
		return rotation;
	}
	public void setRotation(float rotation) {
		this.rotation = rotation;
	}
	public float getNum4() {
		return num4;
	}
	public void setNum4(float num4) {
		this.num4 = num4;
	}
	public int getMobModNumber() {
		return mobModNumber;
	}
	public void setMobModNumber(int mobModNumber) {
		this.mobModNumber = mobModNumber;
	}
	public int getNumberOfSubObjects() {
		return numberOfSubObjects;
	}
	public void setNumberOfSubObjects(int numberOfSubObjects) {
		this.numberOfSubObjects = numberOfSubObjects;
	}
	public float getRadiusOfView() {
		return RadiusOfView;
	}
	public void setRadiusOfView(float radiusOfView) {
		RadiusOfView = radiusOfView;
	}
	public float getDegreesOfView() {
		return DegreesOfView;
	}
	public void setDegreesOfView(float degreesOfView) {
		DegreesOfView = degreesOfView;
	}
	public int getAiCode() {
		return AiCode;
	}
	public void setAiCode(int aiCode) {
		AiCode = aiCode;
	}
	public int getDeathEffects() {
		return deathEffects;
	}
	public void setDeathEffects(int deathEffects) {
		this.deathEffects = deathEffects;
	}
	public int getEnemyDrop() {
		return enemyDrop;
	}
	public void setEnemyDrop(int enemyDrop) {
		this.enemyDrop = enemyDrop;
	}
	public int getItemDrop() {
		return itemDrop;
	}
	public void setItemDrop(int itemDrop) {
		this.itemDrop = itemDrop;
	}
}
