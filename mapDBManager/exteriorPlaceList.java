package mapDBManager;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class exteriorPlaceList 
{
	private static boolean filter = false;
	private static int xMin = 0;
	private static int zMin = 0;
	private static int xMax = 9999;
	private static int zMax = 9999;
	private static boolean AllOutside = false;
	ArrayList<exteriorPlace> places = new ArrayList<exteriorPlace>();
	ArrayList<SubDManager> sdata = new ArrayList<SubDManager>();
	public exteriorPlaceList(List<String> Lines)
	{
		for(int i = 0; i<Lines.size(); i++)
		{
			String[] Line = splitList(Lines.get(i));
			if(Line.length==13)
			{
				exteriorPlace place = new exteriorPlace(Line);
				if(!filter||place.fitsFilter(xMin, xMax, zMin, zMax, AllOutside))
				{
					System.out.println("Fits Filter");
					places.add(place);
				}
				
			}
			else if(Line.length==4)
			{
				sdata.add(new SubDManager(Line));
			}
		}
	}
	public static void filter(int xMin1, int xMax1, int zMin1, int zMax1, boolean AllOutside1)
	{
		xMin = xMin1;
		zMin = zMin1;
		xMax = xMax1;
		zMax = zMax1;
		AllOutside = AllOutside1;
		filter = true;
	}
	private static String[] splitList(String listStr)
	{//Split List into String Array
		final String validChars = "1234567890.-";
		ArrayList<String> list = new ArrayList<String>();
		String word = "";
		for(int i = 0; i < listStr.length(); i++)
		{
			if(validChars.indexOf(listStr.charAt(i))!=-1)
			{
				word+=listStr.charAt(i);
			}
			else if(listStr.charAt(i)==','||listStr.charAt(i)==';')
			{
				if(word.length()>0)
				{
					list.add(word);
					word = "";
				}
			}
		}
		if(word.length()>0)
		{
			list.add(word);
		}
		String[] ret = new String[list.size()];
		for(int i = 0; i < ret.length; i++)
		{
			ret[i]=list.get(i);
		}
		System.out.println(Arrays.toString(ret));
		return ret;
	}
	public String toString()
	{
		String ret = "NUM " + places.size() + ";\r\n";
		for(int i = 0; i<places.size(); i++)
		{
			ret +=places.get(i).toString();
		}
		for(int i = 0; i<sdata.size(); i++)
		{
			ret +=sdata.get(i).toString();
		}
		return ret;
	}
}
