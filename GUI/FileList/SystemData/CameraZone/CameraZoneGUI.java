package GUI.FileList.SystemData.CameraZone;

import GUI.FileInfo.MenuDB.CameraZone.CameraZoneInfoGUI;
import GUI.FileList.FileList;
import SystemDataManagers.MenuDB.CameraData.CameraZone;
import bFM.Settings;

@SuppressWarnings("serial")
public class CameraZoneGUI extends FileList
{
	CameraZone file;
	CameraZoneListGUI parent;
	public CameraZoneGUI(CameraZone file, CameraZoneListGUI parent) 
	{
		this.padding = parent.getPadding() + Settings.indentSize;
		this.file = file;
		super.file = file;
		this.parent = parent;
		initializeAll();
	}
	protected void initializeAll() 
	{
		initializeListGUI(file.getName());
		//initializeInfoGUI();
		addActions();
	}
	protected void initializeInfoGUI() 
	{
		this.infoGUI = new CameraZoneInfoGUI(file);
	}
	protected void addActions() 
	{
		addDeleteAction("Delete Camera Zone", parent, parent.manager.getZones());
		addMoveUpAction(parent, parent.manager.getZones());
		addMoveDownAction(parent, parent.manager.getZones());
		add(actions);
		addMouseListener();
	}
	public void update()
	{
		fileName.setText(file.getName());
		super.update();
	}
}