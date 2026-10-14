package GUI.FileList.SystemData.MonsterBook;

import GUI.FileList.FileList;
import SystemDataManagers.MenuDB.Books.MonsterBook.MonsterBook;

@SuppressWarnings("serial")
public class MonsterBookEntryListGUI extends FileList
{
	MonsterBookEntryManagerListGUI parent;
	MonsterBook entry;
	public MonsterBookEntryListGUI(MonsterBook entry, int padding, MonsterBookEntryManagerListGUI parent)
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
		//infoGUI = new MapAreaInfoGUI(hp);
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
