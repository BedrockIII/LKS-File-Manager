package ResourceManagers.MSDBManager;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import ResourceManagers.MSDBManager.DropTable.DropItem;
import bFM.Utils;

public class MobDropTableList 
{
	final static int ITEMTABLESIZE = 48;
	ArrayList<DropTable> items = new ArrayList<DropTable>();
	public MobDropTableList(byte[] data)
	{
		for(int i = 8; i<data.length; i+=ITEMTABLESIZE)
		{
			items.add(new DropTable(Arrays.copyOfRange(data, i, i+ITEMTABLESIZE)));
		}
	}
	public MobDropTableList(List<String> lines) 
	{
		DropTable lastTable = null;
		DropItem lastItem = null;
		for(String line : lines)
		{
			if(line.indexOf("<<Drop Table>>") != -1)
			{
				lastTable = new DropTable(line);
				items.add(lastTable);
			}
			else if(line.indexOf("<<Item ID>>") != -1)
			{
				if(lastTable == null) throw new IllegalArgumentException("Item ID Cannot be Defined before first Table ID is Defined");
				lastItem = new DropItem(line);
				lastTable.addItem(lastItem);
			}
			else if(line.indexOf("<<Drop Weight>>") != -1)
			{
				if(lastItem == null) throw new IllegalArgumentException("Item Weight Cannot be Defined before Item ID");
				lastItem.addWeight(line);
			}
		}
	}
	public byte[] toBytes()
	{
		byte[] ret = Utils.toByteArr(items.size(), 2);
		ret = Utils.mergeArrays(ret, new byte[6]);
		for(DropTable i : items)
		{
			ret = Utils.mergeArrays(ret, i.toBytes());
		}
		return ret;
		
	}
	public String toBMos() 
	{
		String ret = "";
		for(DropTable i : items)
		{
			ret += i.toBMos();
		}
		return ret;
	}
}
