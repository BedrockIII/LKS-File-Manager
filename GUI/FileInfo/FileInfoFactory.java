package GUI.FileInfo;

import GUI.FileInfo.MenuDB.KingdomPlan.KingdomPlanAreaSelectorGUI;
import PCKGManager.PCKGManager;
import SystemDataManagers.MenuDB.KingdomPlanManager.kingdomPlanManager;
import WorldFileManager.fpInterpreter;
import bFM.Data;
import colReader.ColReader;

public class FileInfoFactory 
{
	public static GenericFileInfoGUI makeInfoGUI(Data file)
	{
		if(file == null) return null;
		if(file instanceof kingdomPlanManager)
		{
			return new KingdomPlanAreaSelectorGUI((kingdomPlanManager)file);
		}
		else if(file instanceof PCKGManager)
		{
			return new PackageInfoGUI((PCKGManager)file);
		}
		else if(file instanceof ColReader)
		{
			return new CollisionInfoGUI((ColReader)file);
		}
		else if(file instanceof fpInterpreter)
		{
			return new FixedPointInfoGUI((fpInterpreter)file);
		}
		return new GenericFileInfoGUI(file);
	}
	//FileInfoFactory.makeInfoGUI
}
