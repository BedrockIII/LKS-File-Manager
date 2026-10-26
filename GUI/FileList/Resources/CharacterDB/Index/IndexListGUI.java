package GUI.FileList.Resources.CharacterDB.Index;

import GUI.FileInfo.CharacterDataBase.CharacterIndexInfoGUI;
import GUI.FileList.FileList;
import ResourceManagers.CharacterDatabaseManager.ind;
import bFM.Settings;

@SuppressWarnings("serial")
class IndexListGUI extends FileList
{
	ind object;
	IndexFileList parent;
	public IndexListGUI(ind object, IndexFileList parent) 
	{
		this.padding = parent.getPadding() + Settings.indentSize;
		file = object;
		this.parent = parent;
		this.object = object;
		this.initializeAll();
	}
	protected void initializeAll()
	{
		initializeListGUI(object.getName());
		addActions();
	}
	protected void addActions()
	{
		addDeleteAction("Delete Character Index", parent, parent.file.getIndicies());
		addMoveUpAction(parent, parent.file.getIndicies());
		addMoveDownAction(parent, parent.file.getIndicies());
		addMouseListener();
		add(actions);
		update();
	}
	protected void initializeInfoGUI() 
	{
		infoGUI = new CharacterIndexInfoGUI(object);
	}
	public void update()
	{
		fileName.setText(object.getName());
	}
}