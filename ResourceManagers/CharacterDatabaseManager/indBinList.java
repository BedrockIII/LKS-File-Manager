package ResourceManagers.CharacterDatabaseManager;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;

import bFM.OpenedFile;

public class indBinList implements OpenedFile
{
	ArrayList<ind> list = new ArrayList<ind>();
	protected indBinList(byte[] data)
	{
		ByteBuffer bytes = ByteBuffer.wrap(data);
		for(int i = 16; i < data.length; i+=196)
		{
			list.add(new ind(bytes.slice(i, 180)));
			//System.out.println(list.get(list.size()-1));
			//list.get(list.size()-1).printUniques();
		}
	}
	protected indBinList(List<String> lines) 
	{
		for(String line : lines)
		{
			if(line.indexOf("Index")!=-1)
			{
				list.add(new ind(line));
			}
		}
	}
	public String toString()
	{
		String ret = "";
		for(ind index : list)
		{
			if(ret.length()>0) ret += '\n';
			ret += index.toString();
		}
		return ret;
	}
	public byte[] toBytes()
	{
		byte[] ret = bFM.Utils.longToBytes(list.size(), 4);
		ret = bFM.Utils.mergeArrays(ret, bFM.Utils.longToBytes(32, 2));
		ret = bFM.Utils.mergeArrays(ret, new byte[10]);
		for(ind index : list)
		{
			ret = bFM.Utils.mergeArrays(ret, index.toBytes());
			ret = bFM.Utils.mergeArrays(ret, new byte[16]);
		}
		return ret;
	}
	public String getNameByCode(int jobCode)
	{
		for(ind index : list)
		{
			if(index.jobCode == jobCode)
			{
				return index.getName();
			}
		}
		System.err.println("The Job Code " + jobCode + " has no defined index");
		return "Invalid";
	}
	public ArrayList<ind> getIndicies() 
	{
		return list;
	}
	public int getAmountOfIndicies() 
	{
		return list.size();
	}
	public ind getByCode(int jobCode) 
	{
		for(ind index : list)
		{
			if(index.jobCode == jobCode)
			{
				return index;
			}
		}
		throw new IllegalArgumentException("The Job Code " + jobCode + " has no defined index");
	}
	public void addJob(int jobCode, String name) 
	{
		list.add(new ind(jobCode, name));
	}
	public ind getLastObject() 
	{
		return list.get(list.size() - 1);
	}
	public void removeIndex(ind file) 
	{
		int code = file.getJobCode();
		for(int i = list.size()-1; i >= 0; i--)
		{
			if(list.get(i).getJobCode() == code)
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
