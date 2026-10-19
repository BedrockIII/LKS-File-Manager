package GUI.FileList.SystemData.MapData;

import GUI.FileInfo.MenuDB.MapData.MapPOIManagerInfoGUI;
import GUI.FileList.CollapseableFileList;
import SystemDataManagers.MenuDB.MapData.MapPOIData;
import SystemDataManagers.MenuDB.MapData.MapPOIDataManager;

@SuppressWarnings("serial")
public class MapPOIManagerFileList extends CollapseableFileList
{
	MapPOIDataManager manager;
	String fileList;
	public MapPOIManagerFileList(MapPOIDataManager file, int padding, String fileList) 
	{
		manager = file;
		this.padding = padding;
		this.file = file;
		this.fileList = fileList;
		initializeAll();
	}
	protected void initializeAll() 
	{
		initializeListGUI(fileList);
		initializeSubGUI();
		addActions();
		reAddComponents();
	}
	public void initializeSubGUI()
	{
		subEntries.removeAll(subEntries);
		for(MapPOIData m : manager.getPOIs())
		{
			subEntries.add(new MapPOIFileList(m, this));
		}
	}
	protected void initializeInfoGUI()
	{
		infoGUI = new MapPOIManagerInfoGUI(manager);
	}
	protected void addActions() 
	{
		addChildAction("Add POI", manager.getPOIs(), MapPOIData::new, MapPOIFileList::new);
		add(actions);
		addMouseListener();
	}
}
