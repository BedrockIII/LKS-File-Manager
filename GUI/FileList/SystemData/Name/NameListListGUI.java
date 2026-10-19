package GUI.FileList.SystemData.Name;

import GUI.FileInfo.MenuDB.Name.GeneratedNameManagerInfoGUI;
import GUI.FileList.CollapseableFileList;
import SystemDataManagers.MenuDB.Name.GeneratedName;
import SystemDataManagers.MenuDB.Name.NameList;

@SuppressWarnings("serial")
public class NameListListGUI extends CollapseableFileList
{
	String name;
	NameList manager;
	public NameListListGUI(NameList names, int padding, String name) 
	{
		this.name = name;
		manager = names;
		file = names;
		this.padding = padding;
		initializeAll();
	}
	protected void initializeAll() 
	{
		initializeListGUI(name);
		initializeSubGUI();
		addActions();
		reAddComponents();
	}
	public void initializeSubGUI()
	{
		subEntries.removeAll(subEntries);
		for(GeneratedName a : manager.getNames())
		{
			subEntries.add(new AutoNameListGUI(a, this));
		}
	}
	protected void initializeInfoGUI()
	{
		infoGUI = new GeneratedNameManagerInfoGUI(manager);
	}
	protected void addActions() 
	{
		addChildAction("Add New Name", manager.getNames(), GeneratedName::new, AutoNameListGUI::new);
		add(actions);
		addMouseListener();
	}
}
