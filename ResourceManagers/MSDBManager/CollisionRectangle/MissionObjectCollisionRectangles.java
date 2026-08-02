package ResourceManagers.MSDBManager.CollisionRectangle;

import bFM.Data;

public class MissionObjectCollisionRectangles implements Data
{
	byte[] rects, rWall, rGround, cWall, cGround;
	public MissionObjectCollisionRectangles(byte[] rects, byte[] rWall, byte[] rGround, byte[] cWall, byte[] cGround) 
	{
		this.rects=rects;
		this.rWall=rWall;
		this.rGround=rGround;
		this.cWall=cWall;
		this.cGround = cGround;
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
	public byte[] getRectList() {
		// TODO Auto-generated method stub
		return rects;
	}
	public byte[] getRandWall() {
		// TODO Auto-generated method stub
		return rWall;
	}
	public byte[] getRandGround() {
		// TODO Auto-generated method stub
		return rGround;
	}
	public byte[] getConstWall() {
		// TODO Auto-generated method stub
		return cWall;
	}
	public byte[] getConstGround() {
		// TODO Auto-generated method stub
		return cGround;
	}
}
