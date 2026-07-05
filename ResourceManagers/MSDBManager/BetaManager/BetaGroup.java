package ResourceManagers.MSDBManager.BetaManager;

import java.nio.ByteBuffer;

import ResourceManagers.MSDBManager.Placement.MobGroup;

public class BetaGroup extends MobGroup
{
	public BetaGroup(ByteBuffer data, int version)
	{
		switch(version)
		{
		case 1:
		{
			initializeVersion1(data);
			break;
		}
		case 3:
		{
			initializeVersion3(data);
			break;
		}
		case 4:
		{
			initializeVersion3(data);
			break;
		}
		case 5:
		{
			initializeVersion3(data);
			break;
		}
		case 6:
		{
			initializeVersion3(data);
			break;
		}
		case 7:
		{
			initializeVersion3(data);
			break;
		}
		case 8:
		{
			initializeVersion3(data);
			break;
		}
		case 9:
		{
			initializeVersion3(data);
			break;
		}
		case 10:
		{
			initializeVersion3(data);
			break;
		}
		case 11:
		{
			initializeVersion3(data);
			break;
		}
		}
		
	}
	private void initializeVersion3(ByteBuffer data) 
	{
		groupIndex = bFM.Utils.getShort(data);
		num1 = bFM.Utils.getShort(data);
		objectIndex = bFM.Utils.getShort(data);
		objectCount = bFM.Utils.getShort(data);
		num4 = bFM.Utils.getShort(data);
		groupNumber = bFM.Utils.getShort(data);
		num6 = -1;
		num7 = 0;
		num8 = data.getFloat();
	}
	private void initializeVersion1(ByteBuffer data) 
	{
		groupIndex = bFM.Utils.getShort(data);
		num1 = bFM.Utils.getShort(data);
		objectIndex = bFM.Utils.getShort(data);
		objectCount = bFM.Utils.getShort(data);
		num4 = bFM.Utils.getShort(data);
		groupNumber = bFM.Utils.getShort(data);
		num6 = -1;
		num7 = 0;
		num8 = 5.0f;
	}
}
