package GUI.FileList.Resources.CharacterDB.Resource;

import GUI.FileInfo.CharacterDataBase.CharacterBodyInfoGUI;
import GUI.FileList.FileList;
import ResourceManagers.CharacterDatabaseManager.CharacterBody;
import bFM.Settings;

public class CharacterBodyListGUI extends FileList
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 3031428057442683814L;
	CharacterBodyManagerListGUI parent;
	CharacterBody object;
	public CharacterBodyListGUI(CharacterBody object, CharacterBodyManagerListGUI parent)
	{
		padding = parent.getPadding() + Settings.indentSize;
		this.parent = parent;
		this.object = object;
		file = object;
		this.initializeAll();
	}
	protected void initializeAll()
	{
		initializeListGUI(object.getName());
		addActions();
	}
	protected void initializeInfoGUI() 
	{
		infoGUI = new CharacterBodyInfoGUI(object);
	}
	protected void addActions()
	{
		addDeleteAction("Delete Body Resource", parent, parent.entries);
		addMoveUpAction(parent, parent.entries);
		addMoveDownAction(parent, parent.entries);
		addMouseListener();
		add(actions);
		update();
	}
	public void update()
	{
		fileName.setText(object.getName());
		super.update();
	}
}