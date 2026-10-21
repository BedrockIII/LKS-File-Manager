package GUI.FileList.SystemData.JewelBook;

import GUI.FileInfo.MenuDB.JewelBook.JewelEntryInfoGUI;
import GUI.FileList.FileList;
import SystemDataManagers.MenuDB.Books.JewelBook.JewelEntry;
import bFM.Settings;

@SuppressWarnings("serial")
public class JewelGUI extends FileList
{
	JewelEntry file;
	JewelBookFileList parent;
	public JewelGUI(JewelEntry entry, JewelBookFileList parent)
	{
		padding = parent.getPadding() + Settings.indentSize;
		this.file = entry;
		super.file = entry;
		this.parent = parent;
		initializeAll();
	}
	protected void initializeAll() 
	{
		initializeListGUI("Jewel: \"" + file.getName() + "\"");
		initializeInfoGUI();
		addActions();
	}
	protected void initializeInfoGUI() 
	{
		this.infoGUI = new JewelEntryInfoGUI(file);
	}
	protected void addActions() 
	{
		addDeleteAction("Delete Jewel Entry", parent, parent.manager.getEntries());
		addMoveUpAction(parent, parent.manager.getEntries());
		addMoveDownAction(parent, parent.manager.getEntries());
		add(actions);
		addMouseListener();
	}
	public void update()
	{
		fileName.setText("Jewel: \"" + file.getName() + "\"");
		super.update();
	}
}