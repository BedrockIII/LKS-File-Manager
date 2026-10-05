package GUI.FileList.SystemData.MapData;

import GUI.FileInfo.MenuDB.MapData.MapAreaInfoGUI;
import GUI.FileList.FileList;
import SystemDataManagers.MenuDB.MapData.MapArea;

@SuppressWarnings("serial")
public class MapAreaFileList extends FileList
{
	MapAreaManagerFileList parent;
	MapArea Area;
	public MapAreaFileList(MapArea Area, int padding, MapAreaManagerFileList parent)
	{
		this.parent = parent;
		this.padding = padding;
		this.Area = Area;
		initializeAll();
	}
	protected void initializeAll()
	{
		initializeListGUI("\"" + Area.getName() + "\"");
		addActions();
	}
	protected void initializeInfoGUI()
	{
		infoGUI = new MapAreaInfoGUI(Area);
	}
	protected void addActions()
	{
		// TODO Add Delete Action
		add(actions);
		addMouseListener();
	}
	public void update()
	{
		fileName.setText("\"" + Area.getName() + "\"");
		super.update();
	}
}
