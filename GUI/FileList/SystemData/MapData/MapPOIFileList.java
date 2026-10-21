package GUI.FileList.SystemData.MapData;

import GUI.FileInfo.MenuDB.MapData.MapPOIInfoGUI;
import GUI.FileList.FileList;
import SystemDataManagers.MenuDB.MapData.MapPOIData;
import bFM.Settings;

@SuppressWarnings("serial")
public class MapPOIFileList extends FileList
{
	MapPOIManagerFileList parent;
	MapPOIData Data;
	public MapPOIFileList(MapPOIData Data, MapPOIManagerFileList parent)
	{
		this.parent = parent;
		padding = parent.getPadding() + Settings.indentSize;
		this.Data = Data;
		file = Data;
		initializeAll();
	}
	protected void initializeAll()
	{
		initializeListGUI("\"" + Data.getName() + "\"");
		addActions();
	}
	protected void initializeInfoGUI()
	{
		infoGUI = new MapPOIInfoGUI(Data);
	}
	protected void addActions()
	{
		addDeleteAction("Delete POI", parent, parent.manager.getPOIs());
		addMoveUpAction(parent, parent.manager.getPOIs());
		addMoveDownAction(parent, parent.manager.getPOIs());
		add(actions);
		addMouseListener();
	}
	public void update()
	{
		fileName.setText("\"" + Data.getName() + "\"");
		super.update();
	}
}
