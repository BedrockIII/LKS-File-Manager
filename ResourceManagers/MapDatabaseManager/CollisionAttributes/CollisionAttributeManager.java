package ResourceManagers.MapDatabaseManager.CollisionAttributes;

import java.nio.ByteBuffer;
import java.util.ArrayList;

import bFM.Data;

public class CollisionAttributeManager implements Data
{
	ArrayList<CollisionAttribute> Objects = new ArrayList<CollisionAttribute>();
	public CollisionAttributeManager()
	{
		
	}
	public CollisionAttributeManager(byte[] data)
	{
		initializeFromBytes(data);
	}
	private void initializeFromBytes(byte[] dataArr)
	{
		ByteBuffer data = ByteBuffer.wrap(dataArr);
		int ObjNum = data.getInt(0);
		byte[] objData = new byte[CollisionAttribute.COLLISION_ATTRIBUTE_SIZE];
		for(int i = 0; i < ObjNum; i++)
		{
			data.get(16 + i * CollisionAttribute.COLLISION_ATTRIBUTE_SIZE, objData);
			Objects.add(new CollisionAttribute(objData));
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
			ret.put(16 + i * CollisionAttribute.COLLISION_ATTRIBUTE_SIZE, Objects.get(i).toBytes());
		}
		return ret.array();
	}
	public int getSize() 
	{
		return Objects.size() * CollisionAttribute.COLLISION_ATTRIBUTE_SIZE + 16;
	}
}
