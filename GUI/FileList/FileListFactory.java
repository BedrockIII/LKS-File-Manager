package GUI.FileList;

import GUI.GUI;
import GUI.FileInfo.MenuDB.KingdomPlan.KingdomPlanFileList;
import PCKGManager.OpenedFile;
import PCKGManager.PCKGManager;
import SystemDataManagers.KingdomPlanManager.kingdomPlanManager;
import colReader.ColReader;

public class FileListFactory 
{
	public static Generic makeListGUI(OpenedFile file, int padding)
	{
		//if(file instanceof FixedPoint)
		//{
			//files.add(new FixedPoint(packageFile,files.size(),padding+5,i));
		//}
		if(file instanceof ColReader)
		{
			return new Collision(file,padding+GUI.indentSize);
		}
		else if (file instanceof PCKGManager)
		{
			return new Package((PCKGManager)file, padding);
		}
		else if (file instanceof kingdomPlanManager)
		{
			return new KingdomPlanFileList(file, padding+GUI.indentSize);
		}
		return new Generic(file, padding+GUI.indentSize);
	}
}
