package GUI.FileList.Resources.CharacterDB.Join;

import GUI.FileInfo.CharacterDataBase.CharacterJoinInfo;
import GUI.FileList.FileList;
import ResourceManagers.CharacterDatabaseManager.join;
import bFM.Settings;

class JoinListGUI extends FileList
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 7454702057627637390L;
	CharacterJoinFileList parent;
	join object;
	public JoinListGUI(join object, CharacterJoinFileList parent) 
	{
		this.parent = parent;
		this.object = object;
		padding = parent.getPadding() + Settings.indentSize;
		file = object;
		initializeAll();
	}
	protected void initializeAll()
	{
		initializeListGUI(object.getName());
		addActions();
	}
	protected void initializeInfoGUI() 
	{
		infoGUI = new CharacterJoinInfo(object);
	}
	protected void addActions()
	{
		addDeleteAction("Delete Character Join", parent, parent.file.getObjects());
		addMoveUpAction(parent, parent.file.getObjects());
		addMoveDownAction(parent, parent.file.getObjects());
		addMouseListener();
		add(actions);
	}
	public void update()
	{
		fileName.setText(object.getName());
	}
}