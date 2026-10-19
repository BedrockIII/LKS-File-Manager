package GUI.FileList.SystemData.Name;

import GUI.FileInfo.MenuDB.Name.NameInfoGUI;
import GUI.FileList.FileList;
import SystemDataManagers.MenuDB.Name.GeneratedName;
import bFM.Settings;

@SuppressWarnings("serial")
public class AutoNameListGUI extends FileList
{
	GeneratedName name;
	NameListListGUI parent;
	public AutoNameListGUI(GeneratedName name, NameListListGUI parent)
	{
		this.name = name;
		file = name;
		this.padding = parent.getPadding() + Settings.indentSize;
		this.parent = parent;
		initializeAll();
	}
	protected void initializeAll()
	{
		initializeListGUI(name.getName());
		addActions();
	}
	protected void initializeInfoGUI()
	{
		infoGUI = new NameInfoGUI(name);
	}
	protected void addActions()
	{
		addDeleteAction("Delete Generated Name", parent, parent.manager.getNames());
		addMoveUpAction(parent, parent.manager.getNames());
		addMoveDownAction(parent, parent.manager.getNames());
		add(actions);
		addMouseListener();
	}
	public void update()
	{
		fileName.setText(name.getName());
		super.update();
	}
}
