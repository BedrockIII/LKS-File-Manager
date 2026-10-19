package GUI.FileList.SystemData.MapData;

import GUI.FileInfo.MenuDB.MapData.MapAreaInfoGUI;
import GUI.FileList.FileList;
import SystemDataManagers.MenuDB.MapData.MapArea;
import bFM.Settings;

@SuppressWarnings("serial")
public class MapAreaFileList extends FileList
{
	MapAreaManagerFileList parent;
	MapArea Area;
	public MapAreaFileList(MapArea Area, MapAreaManagerFileList parent)
	{
		this.parent = parent;
		padding = parent.getPadding() + Settings.indentSize;
		this.Area = Area;
		file = Area;
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
		addDeleteAction("Delete Area", parent, parent.manager.getAreas());
		addMoveUpAction(parent, parent.manager.getAreas());
		addMoveDownAction(parent, parent.manager.getAreas());
		add(actions);
		addMouseListener();
	}
	public void update()
	{
		fileName.setText("\"" + Area.getName() + "\"");
		super.update();
	}
}
