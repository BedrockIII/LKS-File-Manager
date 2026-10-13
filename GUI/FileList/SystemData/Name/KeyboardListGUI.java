package GUI.FileList.SystemData.Name;

import GUI.FileInfo.MenuDB.Name.KeyboardInfoGUI;
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
		infoGUI = new KeyboardInfoGUI(keyboard);
	}
	protected void addActions()
	{
		add(actions);
		addMouseListener();
	}
}
