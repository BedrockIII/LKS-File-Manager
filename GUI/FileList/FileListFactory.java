package GUI.FileList;

import GUI.FileInfo.MenuDB.KingdomPlan.KingdomPlanFileList;
import PCKGManager.PCKGManager;
import ResourceManagers.CharacterDatabaseManager.CharacterDataBaseManager;
import SystemDataManagers.KingdomPlanManager.kingdomPlanManager;
import WorldFileManager.fpInterpreter;
import bFM.OpenedFile;
import colReader.ColReader;

public class FileListFactory 
{
	public static FileList makeListGUI(OpenedFile file, int padding)
	{
		if(file instanceof fpInterpreter)
		{
			return new FixedPoint(file, padding);
		}
		if(file instanceof ColReader)
		{
			return new Collision(file,padding);
		}
		else if (file instanceof PCKGManager)
		{
			return new Package((PCKGManager)file, padding);
		}
		else if (file instanceof kingdomPlanManager)
		{
			return new KingdomPlanFileList(file, padding);
		}
		else if (file instanceof CharacterDataBaseManager)
		{
			return new CharacterDataBaseList(file, padding);
		}
		return new Generic(file, padding);
	}
}
