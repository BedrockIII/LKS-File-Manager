package bFM;

import GUI.FileList.CollapseableFileList;
import GUI.FileList.Collision;
import GUI.FileList.EventListGUI;
import GUI.FileList.FileList;
import GUI.FileList.FixedPoint;
import GUI.FileList.Generic;
import GUI.FileList.Package;
import GUI.FileList.Resources.ItemDatabaseList;
import GUI.FileList.Resources.MissionObjectDatabase;
import GUI.FileList.Resources.CharacterDB.CharacterDataBaseFileList;
import GUI.FileList.SystemData.AnimalBook.AnimalBookFileList;
import GUI.FileList.SystemData.CameraZone.CameraZoneListGUI;
import GUI.FileList.SystemData.CastlePicture.CastlePictureManagerFileList;
import GUI.FileList.SystemData.ChairCamera.ChairCameraManagerFileList;
import GUI.FileList.SystemData.ChairMessage.ThroneMessageFileList;
import GUI.FileList.SystemData.CockpitLog.CockpitLogFileList;
import GUI.FileList.SystemData.Explanation.ExplanationManagerFileList;
import GUI.FileList.SystemData.HummingBook.HummingBookFileList;
import GUI.FileList.SystemData.JewelBook.JewelBookFileList;
import GUI.FileList.SystemData.KingdomPlan.KingdomPlanFileList;
import GUI.FileList.SystemData.MapData.MapDataManagerFileList;
import GUI.FileList.SystemData.MenuString.MenuStringFileList;
import GUI.FileList.SystemData.MonsterBook.MonsterBookManagerFileList;
import GUI.FileList.SystemData.MovieBook.MovieBookManagerFileList;
import GUI.FileList.SystemData.Name.NameManagerFileList;
import GUI.FileList.SystemData.PublicArt.PublicArtManagerFileList;
import GUI.FileList.SystemData.RecordBook.RecordBookManagerFileList;
import GUI.FileList.SystemData.Unused.Development.DevelopmentManagerFileList;
import GUI.FileList.SystemData.Unused.NPCMemo.NPCMemoManagerFileList;
import GUI.FileList.SystemData.WonderSpot.WonderSpotFileList;
import PCKGManager.PCKGManager;
import ResourceManagers.CharacterDatabaseManager.CharacterDataBaseManager;
import ResourceManagers.ItemDatabaseManager.itemDatabaseManager;
import ResourceManagers.MSDBManager.MSDBManager;
import SystemDataManagers.MenuDB.Books.AnimalBook.AnimalBookManager;
import SystemDataManagers.MenuDB.Books.HummingBook.HummingBookManager;
import SystemDataManagers.MenuDB.Books.JewelBook.JewelBookManager;
import SystemDataManagers.MenuDB.Books.MonsterBook.MonsterBookManager;
import SystemDataManagers.MenuDB.Books.MovieBook.MovieBookManager;
import SystemDataManagers.MenuDB.Books.RecordBook.RecordBookManager;
import SystemDataManagers.MenuDB.Books.WonderSpotBook.WonderSpotManager;
import SystemDataManagers.MenuDB.CameraData.CameraZoneList;
import SystemDataManagers.MenuDB.CastlePicture.CastlePictureManager;
import SystemDataManagers.MenuDB.ChairCamera.ChairCameraManager;
import SystemDataManagers.MenuDB.ChairMessage.ChairMessageManager;
import SystemDataManagers.MenuDB.CockpitLog.CockpitLogManager;
import SystemDataManagers.MenuDB.Explanation.ExplanationManager;
import SystemDataManagers.MenuDB.KingdomPlanManager.kingdomPlanManager;
import SystemDataManagers.MenuDB.MapData.MapDataManager;
import SystemDataManagers.MenuDB.MenuString.MenuStringManager;
import SystemDataManagers.MenuDB.Name.NameManager;
import SystemDataManagers.MenuDB.PublicArt.PublicArtManager;
import SystemDataManagers.MenuDB.Unused.Development.DevelopmentManager;
import SystemDataManagers.MenuDB.Unused.NPCMemo.NPCMemoManager;
import VMC.VMCConverter;
import WorldFileManager.FixedPointManager;
import colReader.ColReader;

public class FileTypeUtils
{
	public static String getFileType(OpenedFile file) 
	{
		return getFileType(file.getName(), file.toBytes());
	}

	
	public static OpenedFile makeFile(String name, byte[] file) 
	{
		String fileType = getFileType(name, file);
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
				return new AnimalBookManager(file);
			}
			catch (Exception e)
			{
				System.err.println("Could Not Parse " + fileType + " File. Is it the right Version?");
				e.printStackTrace();
				return new PCKGManager(file, name);
			}
		}else if (fileType.equals("JewelBookDB"))
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
		}else if (fileType.equals("HummingBookDB"))
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
		}else if (fileType.equals("MapDataDB"))
		{
			try
			{
				return new MapDataManager(file);
			}
			catch (Exception e)
			{
				System.err.println("Could Not Parse " + fileType + " File. Is it the right Version?");
				e.printStackTrace();
				return new PCKGManager(file, name);
			}
		}else if (fileType.equals("RecordBookDB"))
		{
			try
			{
				return new RecordBookManager(file);
			}
			catch (Exception e)
			{
				System.err.println("Could Not Parse " + fileType + " File. Is it the right Version?");
				e.printStackTrace();
				return new PCKGManager(file, name);
			}
		}else if (fileType.equals("CastlePictureDB"))
		{
			try
			{
				return new CastlePictureManager(file);
			}
			catch (Exception e)
			{
				System.err.println("Could Not Parse " + fileType + " File. Is it the right Version?");
				e.printStackTrace();
				return new PCKGManager(file, name);
			}
		}else if (fileType.equals("PublicArtDB"))
		{
			try
			{
				return new PublicArtManager(file);
			}
			catch (Exception e)
			{
				System.err.println("Could Not Parse " + fileType + " File. Is it the right Version?");
				e.printStackTrace();
				return new PCKGManager(file, name);
			}
		}else if (fileType.equals("ExplanationDB"))
		{
			try
			{
				return new ExplanationManager(file);
			}
			catch (Exception e)
			{
				System.err.println("Could Not Parse " + fileType + " File. Is it the right Version?");
				e.printStackTrace();
				return new PCKGManager(file, name);
			}
		}else if (fileType.equals("ChairCameraDB"))
		{
			try
			{
				return new ChairCameraManager(file);
			}
			catch (Exception e)
			{
				System.err.println("Could Not Parse " + fileType + " File. Is it the right Version?");
				e.printStackTrace();
				return new PCKGManager(file, name);
			}
		}else if (fileType.equals("MovieBookDB"))
		{
			try
			{
				return new MovieBookManager(file);
			}
			catch (Exception e)
			{
				System.err.println("Could Not Parse " + fileType + " File. Is it the right Version?");
				e.printStackTrace();
				return new PCKGManager(file, name);
			}
		}else if (fileType.equals("DevelopmentDB"))
		{
			try
			{
				return new DevelopmentManager(file);
			}
			catch (Exception e)
			{
				System.err.println("Could Not Parse " + fileType + " File. Is it the right Version?");
				e.printStackTrace();
				return new PCKGManager(file, name);
			}
		}else if (fileType.equals("NameDB"))
		{
			try
			{
				return new NameManager(file);
			}
			catch (Exception e)
			{
				System.err.println("Could Not Parse " + fileType + " File. Is it the right Version?");
				e.printStackTrace();
				return new PCKGManager(file, name);
			}
		}else if (fileType.equals("MonsterBookDB"))
		{
			try
			{
				return new MonsterBookManager(file);
			}
			catch (Exception e)
			{
				System.err.println("Could Not Parse " + fileType + " File. Is it the right Version?");
				e.printStackTrace();
				return new PCKGManager(file, name);
			}
		}else if (fileType.equals("NPCMemoDB"))
		{
			try
			{
				return new NPCMemoManager(file);
			}
			catch (Exception e)
			{
				System.err.println("Could Not Parse " + fileType + " File. Is it the right Version?");
				e.printStackTrace();
				return new PCKGManager(file, name);
			}
		}
		else if (fileType.equals("MissionDB"))
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
		}else if (fileType.equals("MenuStringDB"))
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


	public static String getFileType(String name, byte[] file) 
	{
		if(PCKGManager.isPAC(file))
		{
			if(name.equals("KingdomPlan.bin")||name.equals("Kingdom Plan Config"))
			{
				return "KingdomPlanDB";
			}
			else if(name.indexOf("chrDB") != -1||name.equals("Character Data Base"))
			{
				return "CharacterDB";
			}
			else if(name.indexOf("itemDB") != -1||name.equals("Item Data Base"))
			{
				if(name.indexOf("itemDB") != -1 && name.length() > 8)
				{
					System.out.println("Setting Language to " + name.charAt(8));
					Settings.LanguageCode = Integer.parseInt("" + name.charAt(8));
				}
				return "ItemDB";
			}
			else  if(name.indexOf("msDB") != -1||name.equals("Mission Object Data Base"))
			{
				return "MissionDB";
			}
			else if(name.equals("CameraData.bin")||name.equals("Camera Zone Config"))
			{
				return "CameraZoneDB";
			}
			else if(name.equals("Album.bin"))
			{
				return "WonderSpotDB";
			}
			else if(name.equals("AnimalBook.bin"))
			{
				return "AnimalBookDB";
			}
			else if(name.equals("Jewel.bin"))
			{
				return "JewelBookDB";
			}
			else if(name.equals("Humming.bin"))
			{
				return "HummingBookDB";
			}
			else if(name.equals("CockpitLog.bin"))
			{
				return "CockpitLogDB";
			}
			else if(name.equals("ChairMessage.bin"))
			{
				return "ChairMessageDB";
			}
			else if(name.equals("mapdata.bin"))
			{
				return "MapDataDB";
			}
			else if(name.equals("Record.bin"))
			{
				return "RecordBookDB";
			}
			else if(name.equals("CastlePicture.bin"))
			{
				return "CastlePictureDB";
			}
			else if(name.equals(PublicArtManager.FileName))
			{
				return "PublicArtDB";
			}
			else if(name.equals(ExplanationManager.FileName))
			{
				return "ExplanationDB";
			}
			else if(name.equals(ChairCameraManager.FileName))
			{
				return "ChairCameraDB";
			}
			else if(name.equals(MovieBookManager.FileName))
			{
				return "MovieBookDB";
			}
			else if(name.equals(DevelopmentManager.FileName))
			{
				return "DevelopmentDB";
			}
			else if(name.equals(NameManager.FileName))
			{
				return "NameDB";
			}
			else if(name.equals(MonsterBookManager.FileName))
			{
				return "MonsterBookDB";
			}
			else if(name.equals(NPCMemoManager.FileName))
			{
				return "NPCMemoDB";
			}
			//Check if special TODO
			//else return "Package"
			return "Package";
		}
		else if(colReader.ColReader.isCollisionFile(file))
		{
			return "Collision";
		}
		else if(WorldFileManager.FixedPointManager.isFixedPointFile(file))
		{
			return "Fixed Point";
		}
		else if(VMCConverter.isVMC(file))
		{
			return "Virtual Machine Code";
		}
		else if(name.indexOf("MenuString.bin") != -1)
		{
			return "MenuStringDB";
		}
		return "Todo";
	}


	public static FileList makeListGUI(OpenedFile file, int padding, CollapseableFileList parent)
	{
		if(file instanceof FixedPointManager)
		{
			return new FixedPoint(file, padding, parent);
		}
		else if(file instanceof ColReader)
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
			return new CharacterDataBaseFileList((CharacterDataBaseManager)file, padding);
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
		else if (file instanceof AnimalBookManager)
		{
			return new AnimalBookFileList((AnimalBookManager) file, padding);
		}
		else if (file instanceof JewelBookManager)
		{
			return new JewelBookFileList((JewelBookManager) file, padding);
		}
		else if (file instanceof HummingBookManager)
		{
			return new HummingBookFileList((HummingBookManager) file, padding);
		}
		else if (file instanceof CockpitLogManager)
		{
			return new CockpitLogFileList((CockpitLogManager) file, padding);
		}
		else if (file instanceof MenuStringManager)
		{
			return new MenuStringFileList((MenuStringManager) file, padding);
		}
		else if (file instanceof ChairMessageManager)
		{
			return new ThroneMessageFileList((ChairMessageManager) file, padding);
		}
		else if (file instanceof MapDataManager)
		{
			return new MapDataManagerFileList((MapDataManager) file, padding);
		}
		else if (file instanceof RecordBookManager)
		{
			return new RecordBookManagerFileList((RecordBookManager) file, padding);
		}
		else if (file instanceof CastlePictureManager)
		{
			return new CastlePictureManagerFileList((CastlePictureManager) file, padding);
		}
		else if (file instanceof PublicArtManager)
		{
			return new PublicArtManagerFileList((PublicArtManager) file, padding);
		}
		else if (file instanceof ExplanationManager)
		{
			return new ExplanationManagerFileList((ExplanationManager) file, padding);
		}
		else if (file instanceof ChairCameraManager)
		{
			return new ChairCameraManagerFileList((ChairCameraManager) file, padding);
		}
		else if (file instanceof MovieBookManager)
		{
			return new MovieBookManagerFileList((MovieBookManager) file, padding);
		}
		else if (file instanceof DevelopmentManager)
		{
			return new DevelopmentManagerFileList((DevelopmentManager) file, padding);
		}
		else if (file instanceof NameManager)
		{
			return new NameManagerFileList((NameManager) file, padding);
		}
		else if (file instanceof MonsterBookManager)
		{
			return new MonsterBookManagerFileList((MonsterBookManager) file, padding);
		}
		else if (file instanceof NPCMemoManager)
		{
			return new NPCMemoManagerFileList((NPCMemoManager) file, padding);
		}
		else if(file instanceof MSDBManager)
		{
			return new MissionObjectDatabase((MSDBManager) file, padding);
		}
		else if(file instanceof VMCConverter)
		{
			return new EventListGUI((VMCConverter) file, padding);
		}
		return new Generic(file, padding, parent);
	}

}
