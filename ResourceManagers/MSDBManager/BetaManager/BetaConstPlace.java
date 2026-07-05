package ResourceManagers.MSDBManager.BetaManager;

import java.nio.ByteBuffer;
import ResourceManagers.MSDBManager.Placement.MobConstantPlace;

public class BetaConstPlace extends MobConstantPlace
{
	public BetaConstPlace(ByteBuffer data, int version)
	{
		switch(version)
		{
		case 1:
		{
			//initializeVersion1(data);
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
			initializeVersion10(data);
			break;
		}
		case 11:
		{
			initializeVersion10(data);
			break;
		}
		}
		
	}

	@SuppressWarnings("unused")
	private void initializeVersion1(ByteBuffer data) 
	{
		// DNE
		
	}
	private void initializeVersion3(ByteBuffer data) 
	{
		if(data.remaining()<32)return;
		xPos = data.getFloat();
		yPos = data.getFloat();
		zPos = data.getFloat();
		rotation = data.getFloat();
		spawnRadius = data.getFloat();
		num5 = 0f;
		MobGroupCode1 = bFM.Utils.getShort(data);
		activationFlag1 = bFM.Utils.getShort(data);
		MobGrouptCode2 = bFM.Utils.getShort(data);
		activationFlag2 = bFM.Utils.getShort(data);
		clearFlag = bFM.Utils.getShort(data);
		deactivationFlag = -1;
		num11 = 0;
		num12 = bFM.Utils.getShort(data);
	}
	private void initializeVersion10(ByteBuffer data) 
	{
		if(data.remaining()<32)return;
		xPos = data.getFloat();
		yPos = data.getFloat();
		zPos = data.getFloat();
		rotation = data.getFloat();
		spawnRadius = data.getFloat();
		num5 = 0f;
		MobGroupCode1 = bFM.Utils.getShort(data);
		activationFlag1 = bFM.Utils.getShort(data);
		MobGrouptCode2 = bFM.Utils.getShort(data);
		activationFlag2 = bFM.Utils.getShort(data);
		clearFlag = bFM.Utils.getShort(data);
		deactivationFlag = bFM.Utils.getShort(data);
		num11 = bFM.Utils.getShort(data);
		num12 = bFM.Utils.getShort(data);
	}
}
