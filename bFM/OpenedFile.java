package bFM;

import PCKGManager.PCKGManager;
import ResourceManagers.CharacterDatabaseManager.CharacterDataBaseManager;
import ResourceManagers.ItemDatabaseManager.itemDatabaseManager;
import SystemDataManagers.KingdomPlanManager.kingdomPlanManager;
import WorldFileManager.fpInterpreter;
import colReader.ColReader;

public interface OpenedFile extends Data
{
	//Different than Raw Data for some reason..? Idk it feels right
	public static OpenedFile makeFile(String name, byte[] file) 
	{
		String fileType = bFM.Utils.getFileType(name, file);
		if(fileType.equals("Fixed Point"))
		{
			return new fpInterpreter(file, name);
		}
		else if(fileType.equals("Collision"))
		{
			return new ColReader(file,name);
		}
		else if (fileType.equals("Package"))
		{
			return new PCKGManager(file, name);
		}else if (fileType.equals("KingdomPlanDB"))
		{
			return new kingdomPlanManager(file);
		}else if (fileType.equals("CharacterDB"))
		{
			return new CharacterDataBaseManager(file);
		}
		else if (fileType.equals("ItemDB"))
		{
			return new itemDatabaseManager(name, file);
		}
		return new GenericFile(name, file);
	}
}
