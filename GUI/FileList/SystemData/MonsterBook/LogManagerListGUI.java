package GUI.FileList.SystemData.MonsterBook;

import java.util.ArrayList;

import GUI.FileInfo.MenuDB.MonsterBook.LogManagerInfoGUI;
import GUI.FileList.CollapseableFileList;
import SystemDataManagers.MenuDB.Books.MonsterBook.MonsterLog;

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
			subEntries.add(new LogListGUI(a, this));
		}
		
	}
	protected void initializeInfoGUI()
	{
		infoGUI = new LogManagerInfoGUI(manager);
	}
	protected void addActions() 
	{
		addChildAction("Add Monster Log", manager, MonsterLog::new, LogListGUI::new);
		add(actions);
		addMouseListener();
	}
}
