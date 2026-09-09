package bFM;

import java.util.ArrayList;
import java.util.List;

public class GroupCategoryManager
{
	private static ArrayList<Category> Categories = new ArrayList<Category>();
	public record Category(int id, StringBuilder name) 
	{
		public void setName(String name)
		{
			this.name.setLength(0);
			this.name.append(name);
		}
		public String toString()
		{
			return id + "\t" + name + "\t\n";
		}
	};
	private static void sortList()
	{
		Categories.sort((flag1, flag2) -> Integer.compare(flag1.id(), flag2.id()));
	}
	private static Category binarySearch(List<Category> flagList, int flag)
	{
		if(flagList.size()==0) return null;
		int index = flagList.size()/2;
		int indexFlag = flagList.get(index).id();
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
	private static Category addCategory(int flag)
	{
		Category ret = new Category(flag, new StringBuilder("Group Category " + flag));
		Categories.add(ret);
		return ret;
	}
	public static Category getCategory(int id)
	{
		Category ret = binarySearch(Categories, id);
		if(ret == null)
		{
			ret = addCategory(id);
			sortList();
		}
		return ret;
	}
	public static void importCategories(byte[] data)
	{
		List<String> lines = Utils.bytesToStrs(data);
		for(String line : lines)
		{
			try
			{
				String[] splitList = line.split("\t");
				if(splitList.length != 2) throw new IllegalArgumentException("Line has wrong amount of arguments!!!\n");
				else Categories.add(new Category(Integer.parseInt(splitList[0]),new StringBuilder(splitList[1])));
			}
			catch(Exception e)
			{
				System.err.println("Unable to parse Flag Line");
				e.printStackTrace();
			}
		}
		sortList();
	}
	public static String getCategoryList() 
	{
		String ret = "";
		for(Category f : Categories)
		{
			ret += f.toString();
		}
		return ret;
	}
}
