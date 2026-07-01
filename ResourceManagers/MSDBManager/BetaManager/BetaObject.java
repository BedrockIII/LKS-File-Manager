package ResourceManagers.MSDBManager.BetaManager;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

import ResourceManagers.MSDBManager.MobObject;

public class BetaObject extends MobObject
{
	public BetaObject(ByteBuffer data, int version)
	{
		switch(version)
		{
		case 1:
		{
			//initializeVersion1(data);
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
			initializeVersion5(data);
			break;
		}
		case 6:
		{
			initializeVersion5(data);
			break;
		}
		case 7:
		{
			initializeVersion7(data);
			break;
		}
		case 8:
		{
			initializeVersion7(data);
			break;
		}
		case 9:
		{
			initializeVersion7(data);
			break;
		}
		case 10:
		{
			initializeVersion7(data);
			break;
		}
		case 11:
		{
			initializeVersion7(data);
			break;
		}
		}
		
	}

	private void initializeVersion5(ByteBuffer data) 
	{
		xOffset = data.getFloat();
		yOffset = data.getFloat();
		zOffset = data.getFloat();
		rotation = data.getFloat();
		num4 = data.getFloat();
		mobModNumber = bFM.Utils.getShort(data);
		numberOfSubObjects = bFM.Utils.getShort(data);
		RadiusOfView = data.getFloat();
		DegreesOfView = data.getFloat();
		AiCode = bFM.Utils.getShort(data);
		deathEffects = bFM.Utils.getShort(data);
		enemyDrop = 0;
		itemDrop = 0;
	}

	private void initializeVersion3(ByteBuffer data) 
	{
		if(data.remaining()<24)return;
		xOffset = data.getFloat();
		yOffset = data.getFloat();
		zOffset = data.getFloat();
		rotation = data.getFloat();
		num4 = data.getFloat();
		mobModNumber = bFM.Utils.getShort(data);
		numberOfSubObjects = bFM.Utils.getShort(data);
		RadiusOfView = 0;
		DegreesOfView = 0;
		AiCode = 0;
		deathEffects = 0;
		enemyDrop = 0;
		itemDrop = 0;
	}
	private void initializeVersion7(ByteBuffer data) 
	{
		xOffset = data.getFloat();
		yOffset = data.getFloat();
		zOffset = data.getFloat();
		rotation = data.getFloat();
		num4 = data.getFloat();
		mobModNumber = bFM.Utils.getShort(data);
		numberOfSubObjects = bFM.Utils.getShort(data);
		RadiusOfView = data.getFloat();
		DegreesOfView = data.getFloat();
		AiCode = bFM.Utils.getShort(data);
		deathEffects = bFM.Utils.getShort(data);
		enemyDrop = bFM.Utils.getShort(data);
		itemDrop = bFM.Utils.getShort(data);
	}
}
