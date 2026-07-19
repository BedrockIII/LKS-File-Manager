package ResourceManagers.MSDBManager;

import java.nio.ByteBuffer;

import bFM.Utils;

public class DropTable 
{
	int DropTableID = -1;
	DropItem[] items = new DropItem[4];
	public DropTable(byte[] data)
	{
		ByteBuffer data2 = ByteBuffer.wrap(data);
		DropTableID = data2.getInt(0);
		data2.position(16);
		for(int i = 0; i < 4; i++)
		{
			DropItem item = new DropItem();
			item.itemCode = data2.getInt();
			item.weight = data2.getInt();
			items[i] = item;
		}
	}
	public DropTable(String line)
	{
		DropTableID = Utils.formatFlag(line);
		for(int i = 0; i < 4; i++)
		{
			items[i] = new DropItem();
		}
	}
	public byte[] toBytes()
	{
		byte[] ret = ByteBuffer.allocate(16).putInt(0, DropTableID).array();
		for(DropItem i : items)
		{
			ret = Utils.mergeArrays(ret, i.toBytes());
		}
		return ret;
	}
	public String toBMos()
	{
		String ret = "<<Drop Table>> " + DropTableID + "\n";
		for(DropItem i : items)
		{
			ret += i.toBMos();
		}
		return ret;
	}
	public void addItem(DropItem lastItem) 
	{
		for(int i = 0; i < 4; i++)
		{
			if(items[i] == null || items[i].isNull())
			{
				items[i] = lastItem;
				return;
			}
		}
		throw new IllegalArgumentException("Table ID " + DropTableID + " cannot have more than 4 Item Drops");
	}
	protected static class DropItem
	{
		int itemCode = 0;
		int weight = 0;
		public DropItem(String line) 
		{
			itemCode = Utils.formatFlag(line);
			weight = 0;
		}
		public DropItem() 
		{
			itemCode = 0;
			weight = 0;
		}
		public String toBMos()
		{
			if(isNull()) return "";
			String ret = "\t<<Item ID>> " + itemCode + "\n";
			ret+= "\t<<Drop Weight>> " + weight + "\n";
			return ret;
		}
		public boolean isNull()
		{
			return itemCode == 0 && weight == 0;
		}
		public byte[] toBytes()
		{
			return ByteBuffer.allocate(8).putInt(0, itemCode).putInt(4, weight).array();
		}
		public void addWeight(String line) 
		{
			weight = Utils.formatFlag(line);
		}
	}
}
