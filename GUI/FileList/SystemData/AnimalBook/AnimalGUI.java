package GUI.FileList.SystemData.AnimalBook;

import GUI.FileInfo.MenuDB.AnimalBook.AnimalEntryInfoGUI;
import GUI.FileList.FileList;
import SystemDataManagers.MenuDB.Books.AnimalBook.AnimalEntry;
import bFM.Settings;

@SuppressWarnings("serial")
public class AnimalGUI extends FileList
{
	AnimalEntry entry;
	AnimalBookFileList parent;
	public AnimalGUI(AnimalEntry entry, AnimalBookFileList parent)
	{
		this.padding = parent.getPadding() + Settings.indentSize;
		this.file = entry;
		this.entry = entry;
		this.parent = parent;
		initializeAll();
	}
	protected void initializeAll() 
	{
		initializeListGUI("Entry: \"" + entry.getName() + "\"");
		addActions();
	}
	protected void initializeInfoGUI() 
	{
		infoGUI = new AnimalEntryInfoGUI(entry);
	}
	protected void addActions() 
	{
		addDeleteAction("Delete Animal Book Page", parent, parent.manager.getEntries());
		addMoveUpAction(parent, parent.manager.getEntries());
		addMoveDownAction(parent, parent.manager.getEntries());
		add(actions);
		addMouseListener();
	}
	public void update()
	{
		fileName.setText("Entry: \"" + entry.getName() + "\"");
		super.update();
	}
}