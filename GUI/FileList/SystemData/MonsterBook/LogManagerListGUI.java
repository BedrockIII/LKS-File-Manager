package GUI.FileList.SystemData.MonsterBook;

import java.util.ArrayList;

import GUI.FileList.CollapseableFileList;
import SystemDataManagers.MenuDB.Books.MonsterBook.MonsterLog;
import bFM.Settings;

@SuppressWarnings("serial")
public class LogManagerListGUI extends CollapseableFileList
{
	ArrayList<MonsterLog> manager;
	public LogManagerListGUI(ArrayList<MonsterLog> manager, int padding) 
	{
		this.manager = manager;
		this.padding = padding;
		initializeAll();
	}
	protected void initializeAll() 
	{
		initializeListGUI("(Unused?) Log Data");
		initializeSubGUI();
		addActions();
		reAddComponents();
	}
	public void initializeSubGUI()
	{
		subEntries.removeAll(subEntries);
		for(MonsterLog a : manager)
		{
			subEntries.add(new LogListGUI(a, padding + Settings.indentSize, this));
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
