package GUI.FileList.SystemData.Name;

import GUI.FileList.CollapseableFileList;
import SystemDataManagers.MenuDB.Name.NameManager;
import bFM.Settings;

@SuppressWarnings("serial")
public class NameManagerFileList extends CollapseableFileList
{
	NameManager manager;
	public NameManagerFileList(NameManager names, int padding) 
	{
		manager = names;
		this.padding = padding;
		initializeAll();
	}
	protected void initializeAll() 
	{
		initializeListGUI("Name Manager");
		initializeSubGUI();
		addActions();
		reAddComponents();
	}
	public void initializeSubGUI()
	{
		subEntries.removeAll(subEntries);
		subEntries.add(new NameListListGUI(manager.getFirstNames(), padding + Settings.indentSize, "Auto First Names"));
		subEntries.add(new NameListListGUI(manager.getLastNames(), padding + Settings.indentSize, "Auto Last Names"));
		subEntries.add(new NameListListGUI(manager.getNGName(), padding + Settings.indentSize, "NG List"));
		
		subEntries.add(new LanguageListGUI(manager.getJapanese(), padding + Settings.indentSize, "Japanese"));
		subEntries.add(new LanguageListGUI(manager.getEnglish(), padding + Settings.indentSize, "English"));
		subEntries.add(new LanguageListGUI(manager.getFrench(), padding + Settings.indentSize, "French"));
		subEntries.add(new LanguageListGUI(manager.getGerman(), padding + Settings.indentSize, "German"));
		subEntries.add(new LanguageListGUI(manager.getItalian(), padding + Settings.indentSize, "Italian"));
		subEntries.add(new LanguageListGUI(manager.getSpanish(), padding + Settings.indentSize, "Spanish"));
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
