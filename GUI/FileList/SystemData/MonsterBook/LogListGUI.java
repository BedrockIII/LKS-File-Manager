package GUI.FileList.SystemData.MonsterBook;

import GUI.FileInfo.MenuDB.MonsterBook.LogInfoGUI;
import GUI.FileList.FileList;
import SystemDataManagers.MenuDB.Books.MonsterBook.MonsterLog;

@SuppressWarnings("serial")
public class LogListGUI extends FileList
{
	LogManagerListGUI parent;
	MonsterLog entry;
	public LogListGUI(MonsterLog entry, int padding, LogManagerListGUI parent)
	{
		this.parent = parent;
		this.padding = padding;
		this.entry = entry;
		file = entry;
		initializeAll();
	}
	protected void initializeAll()
	{
		initializeListGUI("\"" + entry.getName() + "\"");
		addActions();
	}
	protected void initializeInfoGUI()
	{
		infoGUI = new LogInfoGUI(entry);
	}
	protected void addActions()
	{
		// TODO Add Delete Action
		add(actions);
		addMouseListener();
	}
	public void update()
	{
		fileName.setText("\"" + entry.getName() + "\"");
		super.update();
	}
}
