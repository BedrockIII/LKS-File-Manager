package GUI.FileList;

import GUI.FileList.Resources.CharacterDataBaseList;
import GUI.FileList.Resources.ItemDatabaseList;
import GUI.FileList.Resources.MissionObjectDatabase;
import GUI.FileList.SystemData.AnimalBookFileList;
import GUI.FileList.SystemData.CameraZoneListGUI;
import GUI.FileList.SystemData.CockpitLogFileList;
import GUI.FileList.SystemData.HummingBookFileList;
import GUI.FileList.SystemData.JewelBookFileList;
import GUI.FileList.SystemData.KingdomPlanFileList;
import GUI.FileList.SystemData.MenuStringFileList;
import GUI.FileList.SystemData.WonderSpotFileList;
import GUI.FileList.SystemData.CastlePicture.CastlePictureManagerFileList;
import GUI.FileList.SystemData.ChairMessage.ThroneMessageFileList;
import GUI.FileList.SystemData.MapData.MapDataManagerFileList;
import GUI.FileList.SystemData.RecordBook.RecordBookManagerFileList;
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
import SystemDataManagers.MenuDB.Books.RecordBook.RecordBookManager;
import SystemDataManagers.MenuDB.CameraData.CameraZoneList;
import SystemDataManagers.MenuDB.CastlePicture.CastlePictureManager;
import SystemDataManagers.MenuDB.ChairMessage.ChairMessageManager;
import SystemDataManagers.MenuDB.KingdomPlanManager.kingdomPlanManager;
import SystemDataManagers.MenuDB.MapData.MapDataManager;
import VMC.VMCConverter;
import WorldFileManager.FixedPointManager;
import bFM.OpenedFile;
import colReader.ColReader;

public class FileListFactory 
{
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
		else if (file instanceof AnimalManager)
		{
			return new AnimalBookFileList((AnimalManager) file, padding);
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
