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
		// TODO Add Delete Action
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
