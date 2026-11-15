package bFM.InfoManagers;

import java.util.ArrayList;
import java.util.List;

import bFM.Utils;

public class CharacterInfoManager 
{
	private static ArrayList<Character> characters = new ArrayList<Character>();
	public record Character(int jobCode, StringBuilder name) 
	{
		public void setName(String name)
		{
			this.name.setLength(0);
			this.name.append(name);
		}
		public String toString()
		{
			return jobCode + "\t" + name + "\t\n";
		}
	};
	private static void sortList()
	{
		characters.sort((flag1, flag2) -> Integer.compare(flag1.jobCode(), flag2.jobCode()));
	}
	private static Character binarySearch(List<Character> flagList, int flag)
	{
		if(flagList.size()==0) return null;
		int index = flagList.size()/2;
		int indexFlag = flagList.get(index).jobCode();
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
	public static Character getCharacter(int jobCode)
	{
		Character ret = binarySearch(characters, jobCode);
		if(ret == null)
		{
			ret = new Character(jobCode, new StringBuilder("Job " + jobCode));
			sortList();
			characters.add(ret);
		}
		return ret;
	}
	public static String getCharacterName(int jobCode)
	{
		Character ret = binarySearch(characters, jobCode);
		if(ret == null)
		{
			return "Invalid Job Code";
		}
		return ret.name.toString();
	}
	public static void importCharacters(byte[] data)
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
				else if(splitList.length != 2) throw new IllegalArgumentException("Line has wrong amount of arguments!!!\n");
				else characters.add(new Character(Integer.parseInt(splitList[0]),new StringBuilder(splitList[1])));
			}
			catch(Exception e)
			{
				System.err.println("Unable to parse Saved Character Code Line");
				e.printStackTrace();
			}
		}
		sortList();
	}
	public static String getCharacterList() 
	{
		String ret = characters.size() + "\n";
		for(Character f : characters)
		{
			ret += f.toString();
		}
		ret += "END\n";
		return ret;
	}
}
