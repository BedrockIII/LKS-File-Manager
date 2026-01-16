package itemManager;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class itemPlaceManager 
{
	ArrayList<itemPlace> items = new ArrayList<itemPlace>();
	public itemPlaceManager(byte[] data)
	{
		for(int i = 16; i<data.length; i+=28)
		{
			items.add(new itemPlace(Arrays.copyOfRange(data, i, i+28)));
		}
	}
	public itemPlaceManager(List<String> lines)
	{
		for(int i = 0; i<lines.size(); i++)
		{
			if(lines.get(i).indexOf("Item Place:")!=-1)
			{
				items.add(new itemPlace(lines.get(i)));
			}
		}
	}
	public String toString()
	{
		String ret = "Item Placement File \n";
		for(int i = 0; i<items.size(); i++)
		{
			ret = ret + items.get(i).toString();
		}
		return ret;
	}
	public byte[] toBytes()
	{
		byte[] ret = bFM.Utils.toByteArr(items.size(), 2);
		ret = bFM.Utils.mergeArrays(ret, bFM.Utils.toByteArr(0, 14));
		for(int i = 0; i< items.size();i++)
		{
			ret = bFM.Utils.mergeArrays(ret, items.get(i).toBytes());
		}
		return ret;
	}
}
