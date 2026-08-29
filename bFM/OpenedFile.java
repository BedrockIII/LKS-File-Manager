package bFM;

import PCKGManager.PCKGManager;
import ResourceManagers.CharacterDatabaseManager.CharacterDataBaseManager;
import ResourceManagers.ItemDatabaseManager.itemDatabaseManager;
import ResourceManagers.MSDBManager.MSDBManager;
import SystemDataManagers.MenuDB.WonderSpotManager;
import SystemDataManagers.MenuDB.CameraData.CameraZoneList;
import SystemDataManagers.MenuDB.KingdomPlanManager.kingdomPlanManager;
import VMC.VMCConverter;
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
		else if(fileType.equals("Virtual Machine Code"))
		{
			return new VMCConverter(name, file);
		}
		else if (fileType.equals("Package"))
		{
			return new PCKGManager(file, name);
		}else if (fileType.equals("KingdomPlanDB"))
		{
			try
			{
				return new kingdomPlanManager(file);
			}
			catch (Exception e)
			{
				System.err.println("Could Not Parse " + fileType + " File. Is it the right Version?");
				e.printStackTrace();
				return new PCKGManager(file, name);
			}
				
		}else if (fileType.equals("CharacterDB"))
		{
			try
			{
				return new CharacterDataBaseManager(file);
			}
			catch (Exception e)
			{
				System.err.println("Could Not Parse " + fileType + " File. Is it the right Version?");
				e.printStackTrace();
				return new PCKGManager(file, name);
			}
			
		}
		else if (fileType.equals("ItemDB"))
		{
			try
			{
				return new itemDatabaseManager(name, file);
			}
			catch (Exception e)
			{
				System.err.println("Could Not Parse " + fileType + " File. Is it the right Version?");
				e.printStackTrace();
				return new PCKGManager(file, name);
			}
			
		}else if (fileType.equals("CameraZoneDB"))
		{
			try
			{
				return new CameraZoneList(file);
			}
			catch (Exception e)
			{
				System.err.println("Could Not Parse " + fileType + " File. Is it the right Version?");
				e.printStackTrace();
				return new PCKGManager(file, name);
			}
		}else if (fileType.equals("WonderSpotDB"))
		{
			try
			{
				return new WonderSpotManager(file);
			}
			catch (Exception e)
			{
				System.err.println("Could Not Parse " + fileType + " File. Is it the right Version?");
				e.printStackTrace();
				return new PCKGManager(file, name);
			}
		}else if (fileType.equals("MissionDB"))
		{
			try
			{
				return new MSDBManager(file);
			}
			catch (Exception e)
			{
				System.err.println("Could Not Parse " + fileType + " File. Is it the right Version?");
				e.printStackTrace();
				return new PCKGManager(file, name);
			}
		}
		
		return new GenericFile(name, file);
	}
}
