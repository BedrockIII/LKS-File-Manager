package GUI.FileList.SystemData.MapData;

import GUI.FileInfo.MenuDB.MapData.MapPOIInfoGUI;
import GUI.FileList.FileList;
import SystemDataManagers.MenuDB.MapData.MapPOIData;

@SuppressWarnings("serial")
public class MapPOIFileList extends FileList
{
	MapPOIManagerFileList parent;
	MapPOIData Data;
	public MapPOIFileList(MapPOIData Data, int padding, MapPOIManagerFileList parent)
	{
		this.parent = parent;
		this.padding = padding;
		this.Data = Data;
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
		// TODO Add Delete Action
		add(actions);
		addMouseListener();
	}
	public void update()
	{
		fileName.setText("\"" + Data.getName() + "\"");
		super.update();
	}
}
