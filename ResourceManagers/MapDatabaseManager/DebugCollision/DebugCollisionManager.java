package ResourceManagers.MapDatabaseManager.DebugCollision;

import java.util.ArrayList;
import java.util.List;

import bFM.OpenedFile;
import bFM.Utils;

public class DebugCollisionManager implements OpenedFile
{
	public static final String FileName = "dbCol0.lst";
	ArrayList<DebugCollision> Collisions = new ArrayList<DebugCollision>();
	public DebugCollisionManager(byte[] data)
	{
		initializeFromLines(Utils.bytesToStrs(data));
	}
	public DebugCollisionManager(List<String> lines)
	{
		initializeFromLines(lines);
	}
	private void initializeFromLines(List<String> lines)
	{
		Collisions.removeAll(Collisions);
		DebugCollision lastCollision = null;
		for(String line : lines)
		{
			if(line.indexOf("DAT ")!=-1)
			{
				lastCollision = new DebugCollision(line);
				Collisions.add(lastCollision);
			}
			else
			{
				lastCollision.addLine(line);
			}
		}
	}
	public String toString()
	{
		String ret = "NUM " + Collisions.size() + ";\r\n";
		for(DebugCollision col : Collisions)
			ret += col;
		ret += "\r\n";
		return ret;
	}
	public byte[] toBytes()
	{
		return Utils.encodeStringToBytes(toString());
	}
	public boolean equals(String name) 
	{
		throw new UnsupportedOperationException("equals() should not be called on type " + this.getClass());
	}
	public void setName(String name) 
	{
		throw new UnsupportedOperationException("setName(String name) should not be called on type " + this.getClass());
	}
	public String getName() 
	{
		return FileName;
	}
	public void setData(byte[] data) 
	{
		initializeFromLines(Utils.bytesToStrs(data));
	}
	public int getSize() 
	{
		return toBytes().length;
	}
}
