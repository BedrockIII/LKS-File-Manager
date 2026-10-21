package GUI.FileList.SystemData.Unused.Development;

import GUI.FileInfo.MenuDB.Development.DevelopmentInfoGUI;
import GUI.FileList.FileList;
import SystemDataManagers.MenuDB.Unused.Development.Development;
import bFM.Settings;

@SuppressWarnings("serial")
public class DevelopmentListGUI extends FileList
{
	Development file;
	DevelopmentManagerFileList parent;
	public DevelopmentListGUI(Development entry, DevelopmentManagerFileList parent)
	{
		padding = parent.getPadding() + Settings.indentSize;
		this.file = entry;
		super.file = entry;
		this.parent = parent;
		initializeAll();
	}
	protected void initializeAll() 
	{
		initializeListGUI("Development: \"" + file.getIcon() + "\"");
		//initializeInfoGUI();
		addActions();
	}
	protected void initializeInfoGUI() 
	{
		this.infoGUI = new DevelopmentInfoGUI(file);
	}
	protected void addActions() 
	{
		addDeleteAction("Delete Development", parent, parent.manager.getEntries());
		addMoveUpAction(parent, parent.manager.getEntries());
		addMoveDownAction(parent, parent.manager.getEntries());
		add(actions);
		addMouseListener();
	}
	public void update()
	{
		fileName.setText("Development: \"" + file.getIcon() + "\"");
		super.update();
	}
}