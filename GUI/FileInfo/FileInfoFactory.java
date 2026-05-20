package GUI.FileInfo;

import GUI.FileInfo.MenuDB.KingdomPlan.KingdomPlanAreaSelectorGUI;
import PCKGManager.OpenedFile;
import PCKGManager.PCKGManager;
import SystemDataManagers.KingdomPlanManager.kingdomPlanManager;
import colReader.ColReader;

public class FileInfoFactory 
{
	public static GenericFileInfoGUI makeInfoGUI(OpenedFile file)
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
		return new GenericFileInfoGUI(file);
	}
	//FileInfoFactory.makeInfoGUI
}
