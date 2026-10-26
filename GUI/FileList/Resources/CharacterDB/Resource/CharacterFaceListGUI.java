package GUI.FileList.Resources.CharacterDB.Resource;

import GUI.FileInfo.CharacterDataBase.CharacterFaceInfoGUI;
import GUI.FileList.FileList;
import ResourceManagers.CharacterDatabaseManager.CharacterFace;
import bFM.Settings;

public class CharacterFaceListGUI extends FileList
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 2432344340375284704L;
	CharacterFaceManagerListGUI parent;
	CharacterFace object;
	public CharacterFaceListGUI(CharacterFace object, CharacterFaceManagerListGUI parent) 
	{
		padding = parent.getPadding() + Settings.indentSize;
		file = object;
		this.parent = parent;
		this.object = object;
		initializeAll();
	}
	protected void initializeAll()
	{
		initializeListGUI(object.getName());
		addActions();
	}
	protected void initializeInfoGUI() 
	{
		infoGUI = new CharacterFaceInfoGUI(object);
	}
	protected void addActions()
	{
		addDeleteAction("Delete Face Resource", parent, parent.entries);
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