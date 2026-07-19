package GUI.FileList;

import GUI.FileList.Resources.CharacterDataBaseList;
import GUI.FileList.Resources.ItemDatabaseList;
import GUI.FileList.SystemData.CameraZoneListGUI;
import GUI.FileList.SystemData.KingdomPlanFileList;
import GUI.FileList.SystemData.WonderSpotFileList;
import PCKGManager.PCKGManager;
import ResourceManagers.CharacterDatabaseManager.CharacterDataBaseManager;
import ResourceManagers.ItemDatabaseManager.itemDatabaseManager;
import SystemDataManagers.MenuDB.WonderSpotManager;
import SystemDataManagers.MenuDB.CameraData.CameraZoneList;
import SystemDataManagers.MenuDB.KingdomPlanManager.kingdomPlanManager;
import WorldFileManager.fpInterpreter;
import bFM.OpenedFile;
import colReader.ColReader;

public class FileListFactory 
{
	public static FileList makeListGUI(OpenedFile file, int padding, CollapseableFileList parent)
	{
		if(file instanceof fpInterpreter)
		{
			return new FixedPoint(file, padding, parent);
		}
		if(file instanceof ColReader)
		{
			return new Collision(file,padding, parent);
		}
		else if (file instanceof PCKGManager)
		{
			return new Package((PCKGManager)file, padding);
		}
		else if (file instanceof kingdomPlanManager)
		{
			return new KingdomPlanFileList((kingdomPlanManager) file, padding);
		}
		else if (file instanceof CharacterDataBaseManager)
		{
			return new CharacterDataBaseList(file, padding);
		}
		else if (file instanceof itemDatabaseManager)
		{
			return new ItemDatabaseList((itemDatabaseManager) file, padding);
		}
		else if (file instanceof CameraZoneList)
		{
			return new CameraZoneListGUI((CameraZoneList) file, padding);
		}
		else if (file instanceof WonderSpotManager)
		{
			return new WonderSpotFileList((WonderSpotManager) file, padding);
		}
		return new Generic(file, padding, parent);
	}
}
