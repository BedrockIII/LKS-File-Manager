package GUI.FileList.SystemData.MonsterBook;

import java.util.ArrayList;

import GUI.FileInfo.MenuDB.MonsterBook.MonsterBookEntryManagerInfoGUI;
import GUI.FileList.CollapseableFileList;
import SystemDataManagers.MenuDB.Books.MonsterBook.MonsterBook;
import bFM.Settings;

@SuppressWarnings("serial")
public class MonsterBookEntryManagerListGUI extends CollapseableFileList
{
	ArrayList<MonsterBook> manager;
	public MonsterBookEntryManagerListGUI(ArrayList<MonsterBook> manager, int padding) 
	{
		this.manager = manager;
		this.padding = padding;
		initializeAll();
	}
	protected void initializeAll() 
	{
		initializeListGUI("Book Entries");
		initializeSubGUI();
		addActions();
		reAddComponents();
	}
	public void initializeSubGUI()
	{
		subEntries.removeAll(subEntries);
		for(MonsterBook a : manager)
		{
			subEntries.add(new MonsterBookEntryListGUI(a, padding + Settings.indentSize, this));
		}
		
	}
	protected void initializeInfoGUI()
	{
		infoGUI = new MonsterBookEntryManagerInfoGUI(manager);
	}
	protected void addActions() 
	{
		addExportAction();
		add(actions);
		addMouseListener();
	}
}
