package GUI.FileList.SystemData.HummingBook;

import GUI.FileInfo.MenuDB.HummingBook.HummingEntryInfoGUI;
import GUI.FileList.FileList;
import SystemDataManagers.MenuDB.Books.HummingBook.HummingBookEntry;
import bFM.Settings;

@SuppressWarnings("serial")
public class HumGUI extends FileList
{
	HummingBookEntry file;
	HummingBookFileList parent;
	public HumGUI(HummingBookEntry entry, HummingBookFileList parent)
	{
		padding = parent.getPadding() + Settings.indentSize;
		this.file = entry;
		super.file = entry;
		this.parent = parent;
		initializeAll();
	}
	protected void initializeAll() 
	{
		initializeListGUI("Entry: \"" + file.getName() + "\"");
		addActions();
	}
	protected void initializeInfoGUI() 
	{
		this.infoGUI = new HummingEntryInfoGUI(file);
	}
	protected void addActions() 
	{
		addDeleteAction("Delete Hum Entry", parent, parent.manager.getEntries());
		addMoveUpAction(parent, parent.manager.getEntries());
		addMoveDownAction(parent, parent.manager.getEntries());
		add(actions);
		addMouseListener();
	}
	public void update()
	{
		fileName.setText("Entry: \"" + file.getName() + "\"");
		super.update();
	}
}