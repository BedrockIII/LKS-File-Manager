package GUI.FileList.SystemData.ChairCamera;

import GUI.FileInfo.MenuDB.ChairCamera.ChairCameraInfoGUI;
import GUI.FileList.FileList;
import SystemDataManagers.MenuDB.ChairCamera.ChairCamera;
import bFM.Settings;

@SuppressWarnings("serial")
public class ChairCameraListGUI extends FileList
{
	ChairCamera entry;
	ChairCameraLevelListGUI parent;
	public ChairCameraListGUI(ChairCamera entry, ChairCameraLevelListGUI parent)
	{
		this.padding = parent.getPadding() + Settings.indentSize;
		this.file = entry;
		this.entry = entry;
		this.parent = parent;
		initializeAll();
	}
	protected void initializeAll() 
	{
		initializeListGUI("Camera");
		addActions();
	}
	protected void initializeInfoGUI() 
	{
		this.infoGUI = new ChairCameraInfoGUI(entry);
	}
	protected void addActions() 
	{
		addDeleteAction("Delete Chair Camera", parent, parent.Entries);
		addMoveUpAction(parent, parent.Entries);
		addMoveDownAction(parent, parent.Entries);
		add(actions);
		addMouseListener();
	}
}