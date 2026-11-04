package ResourceManagers.MapDatabaseManager.PlaceObject;

import java.nio.ByteBuffer;
import java.util.ArrayList;

import bFM.Data;

public class PlacementObjectManager implements Data
{
	ArrayList<PlacementObject> Objects = new ArrayList<PlacementObject>();
	public PlacementObjectManager()
	{
		
	}
	public PlacementObjectManager(byte[] data)
	{
		initializeFromBytes(data);
	}
	private void initializeFromBytes(byte[] dataArr)
	{
		ByteBuffer data = ByteBuffer.wrap(dataArr);
		int ObjNum = data.getInt(0);
		byte[] objData = new byte[PlacementObject.PLACEMENT_OBJECT_SIZE];
		for(int i = 0; i < ObjNum; i++)
		{
			data.get(16 + i * PlacementObject.PLACEMENT_OBJECT_SIZE, objData);
		}
	}
	public void setData(byte[] data) 
	{
		Objects.removeAll(Objects);
		initializeFromBytes(data);
	}
	public byte[] toBytes() 
	{
		ByteBuffer ret = ByteBuffer.allocate(getSize());
		ret.putInt(0, Objects.size());
		for(int i = 0; i < Objects.size(); i++)
		{
			ret.put(16 + i * PlacementObject.PLACEMENT_OBJECT_SIZE, Objects.get(i).toBytes());
		}
		return ret.array();
	}
	public int getSize() 
	{
		return Objects.size() * PlacementObject.PLACEMENT_OBJECT_SIZE + 16;
	}
}
