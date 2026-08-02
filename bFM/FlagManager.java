package bFM;

import java.util.ArrayList;
import java.util.List;

public class FlagManager 
{
	private static ArrayList<Flag> bitFlags = new ArrayList<Flag>();
	public record Flag(int flag, StringBuilder DebugName, StringBuilder name) 
	{
		public void setName(String name)
		{
			this.name.setLength(0);
			this.name.append(name);
		}
		public void setDebugName(String name)
		{
			this.DebugName.setLength(0);
			this.DebugName.append(name);
		}
		public String toString()
		{
			return flag + "\t" + DebugName + "\t" + name + "\t\n";
		}
	};
	private static void sortBitFlagList()
	{
		bitFlags.sort((flag1, flag2) -> Integer.compare(flag1.flag(), flag2.flag()));
	}
	private static Flag binarySearch(List<Flag> flagList, int flag)
	{
		if(flagList.size()==0) return null;
		int index = flagList.size()/2;
		int indexFlag = flagList.get(index).flag();
		if(flagList.size()==1 && indexFlag != flag)
		{
			return null;
		}
		if(flag < indexFlag) //left
		{
			return binarySearch(flagList.subList(0, index), flag);
		}
		if(indexFlag < flag) //right
		{
			return binarySearch(flagList.subList(index, flagList.size()), flag);
		}
		return flagList.get(index);
	}
	private static Flag addFlag(List<Flag> flagList,int flag)
	{
		Flag ret = new Flag(flag, new StringBuilder("Flag " + flag), new StringBuilder("FLAG_" + flag));
		flagList.add(ret);
		return ret;
	}
	public static Flag getBitFlag(int flag)
	{
		Flag ret = binarySearch(bitFlags, flag);
		if(ret == null)
		{
			ret = addFlag(bitFlags, flag);
			sortBitFlagList();
		}
		return ret;
	}
	public static void importBitFlags(byte[] data)
	{
		List<String> lines = Utils.bytesToStrs(data);
		for(String line : lines)
		{
			try
			{
				String[] splitList = line.split("\t");
				if(splitList.length <= 1)
				{
					//Likely a data line
				}
				else if(splitList.length != 3) throw new IllegalArgumentException("Line has wrong amount of arguments!!!\n");
				else bitFlags.add(new Flag(Integer.parseInt(splitList[0]),new StringBuilder(splitList[1]),new StringBuilder(splitList[2])));
			}
			catch(Exception e)
			{
				System.err.println("Unable to parse Flag Line");
				e.printStackTrace();
			}
		}
		sortBitFlagList();
	}
	public static String getBitFlagList() 
	{
		String ret = bitFlags.size() + "\n";
		for(Flag f : bitFlags)
		{
			ret += f.toString();
		}
		ret += "END\n";
		return ret;
	}
}
