package ResourceManagers.MapDatabaseManager.GroundConfiguration;

import java.nio.charset.Charset;

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
		String ret = "DEF ";
		if(x < 10) ret += "0" + x;
		else ret += x;
		if(y < 10) ret += "0" + y;
		else ret += y;
		ret+= ";\r\n";
		if(hasSea) ret += "SEA;\r\n";
		ret += "MEM " + mem + ";\r\n";
		return ret;
	}
	public void setData(byte[] data) 
	{
		throw new UnsupportedOperationException("setData(byte[] data) should not be called on type " + this.getClass());
	}
	public byte[] toBytes() 
	{
		try
		{
			return Utils.encodeStringToBytes(toString(), Charset.forName("ASCII"));
		} catch (Exception e)
		{
			//To String should always be english ascii characters
			throw new IllegalArgumentException("This Should Never Happen");
		}
	}
	public int getSize() 
	{
		throw new UnsupportedOperationException("getSize() should not be called on type " + this.getClass());
	}
}
