package ResourceManagers.MapDatabaseManager.GroundConfiguration;

import bFM.Data;
import bFM.Utils;

public class GroundMemoryAssignment implements Data
{
	int x = -1;
	int y = -1;
	int mem = 0;
	boolean hasSea = false;
	public GroundMemoryAssignment(String line)
	{
		int pos = Utils.formatInt(line);
		x = pos/100;
		y = pos%100;
	}
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
	public int getMemory() {
		return mem;
	}
	public void setMemory(int mem) {
		this.mem = mem;
	}
	public boolean getSea() {
		return hasSea;
	}
	public void setSea(boolean hasSea) {
		this.hasSea = hasSea;
	}
	public String toString()
	{
		String ret = "DEF " + x + "" + y + ";\n";
		ret += "MEM " + mem + ";\n";
		if(hasSea) ret += "SEA;\n";
		return ret;
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
}
