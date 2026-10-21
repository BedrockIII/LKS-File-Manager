package GUI.FileList.SystemData.MapData;

import GUI.FileInfo.MenuDB.MapData.MapAreaManagerInfoGUI;
import GUI.FileList.CollapseableFileList;
import SystemDataManagers.MenuDB.MapData.MapArea;
import SystemDataManagers.MenuDB.MapData.MapAreaManager;

@SuppressWarnings("serial")
public class MapAreaManagerFileList extends CollapseableFileList
{
	MapAreaManager manager;
	public MapAreaManagerFileList(MapAreaManager mapAreaManager, int padding) 
	{
		manager = mapAreaManager;
		this.padding = padding;
		this.file = mapAreaManager;
		initializeAll();
	}
	protected void initializeAll() 
	{
		initializeListGUI("Area Data");
		initializeSubGUI();
		addActions();
		reAddComponents();
	}
	public void initializeSubGUI()
	{
		subEntries.removeAll(subEntries);
		for(MapArea a : manager.getAreas())
		{
			subEntries.add(new MapAreaFileList(a, this));
		}
		
	}
	protected void initializeInfoGUI()
	{
		infoGUI = new MapAreaManagerInfoGUI(manager);
	}
	protected void addActions() 
	{
		addChildAction("Add Area", manager.getAreas(), MapArea::new, MapAreaFileList::new);
		add(actions);
		addMouseListener();
	}
}
