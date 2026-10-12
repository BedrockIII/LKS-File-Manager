package GUI.FileList.SystemData.Name;

import GUI.FileList.FileList;
import SystemDataManagers.MenuDB.Name.Keyboard;

@SuppressWarnings("serial")
public class KeyboardListGUI extends FileList
{
	String name;
	Keyboard keyboard;
	public KeyboardListGUI(Keyboard keyboard, int padding, String name)
	{
		this.name = name;
		this.padding = padding;
		this.keyboard = keyboard;
		initializeAll();
	}
	protected void initializeAll()
	{
		initializeListGUI(name);
		addActions();
	}
	protected void initializeInfoGUI()
	{
		//TODO
	}
	protected void addActions()
	{
		add(actions);
		addMouseListener();
	}
}
