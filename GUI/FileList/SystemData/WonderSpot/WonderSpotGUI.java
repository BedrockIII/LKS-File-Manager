package GUI.FileList.SystemData.WonderSpot;

import GUI.FileInfo.MenuDB.WonderSpot.WonderSpotInfoGUI;
import GUI.FileList.FileList;
import SystemDataManagers.MenuDB.Books.WonderSpotBook.WonderSpot;
import bFM.Settings;

@SuppressWarnings("serial")
public class WonderSpotGUI extends FileList
{
	WonderSpot entry;
	WonderSpotFileList parent;
	public WonderSpotGUI(WonderSpot file, WonderSpotFileList parent)
	{
		this.padding = parent.getPadding() + Settings.indentSize;
		this.file = file;
		entry = file;
		this.parent = parent;
		initializeAll();
	}
	protected void initializeAll() 
	{
		initializeListGUI("Spot: \"" + entry.getName() + "\"");
		addActions();
	}
	protected void initializeInfoGUI() 
	{
		infoGUI = new WonderSpotInfoGUI(entry);
	}
	protected void addActions() 
	{
		addDeleteAction("Delete Wonder Spot", parent, parent.manager.getSpots());
		addMoveUpAction(parent, parent.manager.getSpots());
		addMoveDownAction(parent, parent.manager.getSpots());
		add(actions);
		addMouseListener();
	}
	public void update()
	{
		fileName.setText("Spot: \"" + entry.getName() + "\"");
		super.update();
	}
}