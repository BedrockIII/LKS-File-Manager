package GUI.FileList.SystemData.MonsterBook;

import GUI.FileInfo.MenuDB.MonsterBook.MonsterBookEntryInfoGUI;
import GUI.FileList.FileList;
import SystemDataManagers.MenuDB.Books.MonsterBook.MonsterBook;
import bFM.Settings;

@SuppressWarnings("serial")
public class MonsterBookEntryListGUI extends FileList
{
	MonsterBookEntryManagerListGUI parent;
	MonsterBook entry;
	public MonsterBookEntryListGUI(MonsterBook entry, MonsterBookEntryManagerListGUI parent)
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
		infoGUI = new MonsterBookEntryInfoGUI(entry);
	}
	protected void addActions()
	{
		addDeleteAction("Delete Monster Book Entry", parent, parent.manager);
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
