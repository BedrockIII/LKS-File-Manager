package GUI.FileList.SystemData.Name;

import GUI.FileList.CollapseableFileList;
import SystemDataManagers.MenuDB.Name.NameList;
import bFM.Settings;

@SuppressWarnings("serial")
public class NameListListGUI extends CollapseableFileList
{
	String name;
	NameList manager;
	public NameListListGUI(NameList names, int padding, String name) 
	{
		this.name = name;
		manager = names;
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
		for(String a : manager.getNames())
		{
			subEntries.add(new AutoNameListGUI(a, padding + Settings.indentSize, this));
		}
		
	}
	protected void initializeInfoGUI()
	{
		//TODO
		//infoGUI = new HummingBookListInfoGUI(manager);
	}
	protected void addActions() 
	{
		add(actions);
		addMouseListener();
	}
}
