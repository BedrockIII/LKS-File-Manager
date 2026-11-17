package ResourceManagers.MapDatabaseManager.AreaPlan;

import java.nio.ByteBuffer;
import java.util.ArrayList;

import bFM.Data;

public class AreaPlanManager implements Data
{
	ArrayList<AreaPlan> Objects = new ArrayList<AreaPlan>();
	public AreaPlanManager()
	{
		
	}
	public AreaPlanManager(byte[] data)
	{
		initializeFromBytes(data);
	}
	private void initializeFromBytes(byte[] dataArr)
	{
		ByteBuffer data = ByteBuffer.wrap(dataArr);
		int ObjNum = data.getInt(0);
		byte[] objData = new byte[AreaPlan.AREA_PLAN_SIZE];
		for(int i = 0; i < ObjNum; i++)
		{
			data.get(16 + i * AreaPlan.AREA_PLAN_SIZE, objData);
			Objects.add(new AreaPlan(objData));
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
			ret.put(16 + i * AreaPlan.AREA_PLAN_SIZE, Objects.get(i).toBytes());
		}
		return ret.array();
	}
	public int getSize() 
	{
		return Objects.size() * AreaPlan.AREA_PLAN_SIZE + 16;
	}
}
