package GUI.FileList.SystemData.PublicArt;

import GUI.FileInfo.MenuDB.PublicArt.PublicArtInfoGUI;
import GUI.FileList.FileList;
import SystemDataManagers.MenuDB.PublicArt.PublicArt;
import bFM.Settings;

@SuppressWarnings("serial")
public class PublicArtListGUI extends FileList
{
	PublicArt file;
	PublicArtManagerFileList parent;
	public PublicArtListGUI(PublicArt entry, PublicArtManagerFileList parent)
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
		this.infoGUI = new PublicArtInfoGUI(file);
	}
	protected void addActions() 
	{
		addDeleteAction("Delete Public Art", parent, parent.manager.getEntries());
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