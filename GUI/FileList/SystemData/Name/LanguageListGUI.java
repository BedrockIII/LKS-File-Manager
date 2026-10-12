package GUI.FileList.SystemData.Name;

import GUI.FileList.CollapseableFileList;
import SystemDataManagers.MenuDB.Name.Language;
import bFM.Settings;

@SuppressWarnings("serial")
public class LanguageListGUI extends CollapseableFileList
{
	Language manager;
	String name;
	public LanguageListGUI(Language manager, int padding, String name) 
	{
		this.manager = manager;
		this.name = name;
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
		if(name.equals("Japanese"))
		{
			subEntries.add(new KeyboardListGUI(manager.getCapitals(), padding + Settings.indentSize, "Keyboard 1"));
			subEntries.add(new KeyboardListGUI(manager.getLowercase(), padding + Settings.indentSize, "Keyboard 2"));
			subEntries.add(new KeyboardListGUI(manager.getSpecial(), padding + Settings.indentSize, "Keyboard 3"));
		}
		else
		{
			subEntries.add(new KeyboardListGUI(manager.getCapitals(), padding + Settings.indentSize, "Capitals"));
			subEntries.add(new KeyboardListGUI(manager.getLowercase(), padding + Settings.indentSize, "Lowercase"));
			subEntries.add(new KeyboardListGUI(manager.getSpecial(), padding + Settings.indentSize, "Special"));
		}
	}
	protected void initializeInfoGUI()
	{
		//TODO
		//infoGUI = new HummingBookListInfoGUI(manager);
	}
	protected void addActions() 
	{
		addExportAction();
		add(actions);
		addMouseListener();
	}
}
