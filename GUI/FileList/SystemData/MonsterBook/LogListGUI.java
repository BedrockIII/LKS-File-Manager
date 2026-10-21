package GUI.FileList.SystemData.MonsterBook;

import GUI.FileInfo.MenuDB.MonsterBook.LogInfoGUI;
import GUI.FileList.FileList;
import SystemDataManagers.MenuDB.Books.MonsterBook.MonsterLog;
import bFM.Settings;

@SuppressWarnings("serial")
public class LogListGUI extends FileList
{
	LogManagerListGUI parent;
	MonsterLog entry;
	public LogListGUI(MonsterLog entry, LogManagerListGUI parent)
	{
		padding = parent.getPadding() + Settings.indentSize;
		this.entry = entry;
		super.file = entry;
		this.parent = parent;
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
		addDeleteAction("Delete Monster Log Entry", parent, parent.manager);
		addMoveUpAction(parent, parent.manager);
		addMoveDownAction(parent, parent.manager);
		add(actions);
		addMouseListener();
	}
	public void update()
	{
		fileName.setText("\"" + entry.getName() + "\"");
		super.update();
	}
}
