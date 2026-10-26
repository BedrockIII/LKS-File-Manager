package ResourceManagers.CharacterDatabaseManager;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;

import bFM.OpenedFile;


public class JoinBinList implements OpenedFile
{
	ArrayList<join> list = new ArrayList<join>();
	protected JoinBinList(byte[] data)
	{
		ByteBuffer bytes = ByteBuffer.wrap(data);
		for(int i = 8; i < data.length; i+=32)
		{
			list.add(new join(bytes.slice(i, 32)));
			//System.out.println(list.get(list.size()-1));
			//list.get(list.size()-1).printUniques();
		}
	}
	protected JoinBinList(List<String> lines) 
	{
		for(String line : lines)
		{
			if(line.indexOf("Join")!=-1)
			{
				list.add(new join(line));
			}
		}
	}
	protected JoinBinList() 
	{
	}
	public String toString()
	{
		String ret = "";
		for(join Join : list)
		{
			if(ret.length()>0) ret += '\n';
			ret += Join.toString();
		}
		return ret;
	}
	public byte[] toBytes()
	{
		byte[] ret = bFM.Utils.longToBytes(list.size(), 2);
		ret = bFM.Utils.mergeArrays(ret, new byte[6]);
		for(join Join : list)
		{
			ret = bFM.Utils.mergeArrays(ret, Join.toBytes());
			ret = bFM.Utils.mergeArrays(ret, new byte[4]);
		}
		return ret;
	}
	public ArrayList<join> getObjects() 
	{
		return list;
	}
	public void addJob(int index) 
	{
		list.add(new join(index));
	}
	public join getLastObject() 
	{
		return list.get(list.size()-1);
	}
	public int getAmountOfJoins() 
	{
		return list.size();
	}
	public void removeJoin(join file) 
	{
		int code = file.getIndex();
		for(int i = list.size()-1; i >= 0; i--)
		{
			if(list.get(i).getIndex() == code)
			{
				list.remove(i);
			}
		}
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
		throw new UnsupportedOperationException("getName() should not be called on type " + this.getClass());
	}
	public void setData(byte[] data) 
	{
		throw new UnsupportedOperationException("setData(byte[] data) should not be called on type " + this.getClass());
	}
	public int getSize() 
	{
		throw new UnsupportedOperationException("getSize() should not be called on type " + this.getClass());
	}
}
