package GUI.FileList.SystemData.MenuString;

import GUI.FileInfo.MenuDB.MenuString.MenuStringInfo;
import GUI.FileList.FileList;
import SystemDataManagers.MenuDB.MenuString.MenuString;
import bFM.Settings;

@SuppressWarnings("serial")
public class MenuStringListGUI extends FileList
{
	MenuString file;
	MenuStringFileList parent;
	public MenuStringListGUI(MenuString entry, MenuStringFileList parent)
	{
		padding = parent.getPadding() + Settings.indentSize;
		this.file = entry;
		super.file = entry;
		this.parent = parent;
		initializeAll();
	}
	protected void initializeAll() 
	{
		initializeListGUI("\""+ file.getText() + "\"");
		addActions();
	}
	protected void initializeInfoGUI() 
	{
		this.infoGUI = new MenuStringInfo(file);
	}
	protected void addActions() 
	{
		addDeleteAction("Delete Menu String", parent, parent.manager.getEntries());
		addMoveUpAction(parent, parent.manager.getEntries());
		addMoveDownAction(parent, parent.manager.getEntries());
		add(actions);
		addMouseListener();
	}
	public void update()
	{
		fileName.setText("\""+ file.getText() + "\"");
		super.update();
	}
}