package bFM;

import PCKGManager.PCKGManager;
import ResourceManagers.CharacterDatabaseManager.CharacterDataBaseManager;
import ResourceManagers.ItemDatabaseManager.itemDatabaseManager;
import ResourceManagers.MSDBManager.MSDBManager;
import SystemDataManagers.MenuDB.CockpitLogManager;
import SystemDataManagers.MenuDB.MenuStringManager;
import SystemDataManagers.MenuDB.Books.AnimalManager;
import SystemDataManagers.MenuDB.Books.HummingBookManager;
import SystemDataManagers.MenuDB.Books.JewelBookManager;
import SystemDataManagers.MenuDB.Books.WonderSpotManager;
import SystemDataManagers.MenuDB.CameraData.CameraZoneList;
import SystemDataManagers.MenuDB.ChairMessage.ChairMessageManager;
import SystemDataManagers.MenuDB.KingdomPlanManager.kingdomPlanManager;
import VMC.VMCConverter;
import WorldFileManager.FixedPointManager;
import colReader.ColReader;

public interface OpenedFile extends Data, Nameable
{
	//Different than Raw Data for some reason..? Idk it feels right
	public static OpenedFile makeFile(String name, byte[] file) 
	{
		String fileType = Utils.getFileType(name, file);
		if(fileType.equals("Fixed Point"))
		{
			return new FixedPointManager(file, name);
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
		}else if (fileType.equals("AnimalBookDB"))
		{
			try
			{
				return new AnimalManager(file);
			}
			catch (Exception e)
			{
				System.err.println("Could Not Parse " + fileType + " File. Is it the right Version?");
				e.printStackTrace();
				return new PCKGManager(file, name);
			}
		}
		else if (fileType.equals("JewelBookDB"))
		{
			try
			{
				return new JewelBookManager(file);
			}
			catch (Exception e)
			{
				System.err.println("Could Not Parse " + fileType + " File. Is it the right Version?");
				e.printStackTrace();
				return new PCKGManager(file, name);
			}
		}
		else if (fileType.equals("HummingBookDB"))
		{
			try
			{
				return new HummingBookManager(file);
			}
			catch (Exception e)
			{
				System.err.println("Could Not Parse " + fileType + " File. Is it the right Version?");
				e.printStackTrace();
				return new PCKGManager(file, name);
			}
		}else if (fileType.equals("CockpitLogDB"))
		{
			try
			{
				return new CockpitLogManager(file);
			}
			catch (Exception e)
			{
				System.err.println("Could Not Parse " + fileType + " File. Is it the right Version?");
				e.printStackTrace();
				return new PCKGManager(file, name);
			}
		}else if (fileType.equals("ChairMessageDB"))
		{
			try
			{
				return new ChairMessageManager(file);
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
		else if (fileType.equals("MenuStringDB"))
		{
			try
			{
				return new MenuStringManager(file);
			}
			catch (Exception e)
			{
				System.err.println("Could Not Parse " + fileType + " File. Is it the right Version?");
				e.printStackTrace();
			}
		}
		return new GenericFile(name, file);
	}
}
