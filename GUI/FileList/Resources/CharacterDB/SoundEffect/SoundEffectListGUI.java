package GUI.FileList.Resources.CharacterDB.SoundEffect;

import GUI.FileInfo.CharacterDataBase.CharacterSoundEffectInfoGUI;
import GUI.FileList.FileList;
import ResourceManagers.CharacterDatabaseManager.SoundEffectCoordinate;
import bFM.Settings;

class SoundEffectListGUI extends FileList
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 4817937175265641521L;
	SoundEffectFileList parent;
	SoundEffectCoordinate object;
	public SoundEffectListGUI(SoundEffectCoordinate object, SoundEffectFileList parent) 
	{
		this.object = object;
		this.parent = parent;
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
		infoGUI = new CharacterSoundEffectInfoGUI(object);
	}
	protected void addActions()
	{
		addDeleteAction("Delete Character Sound Effect", parent, parent.file.getObjects());
		addMoveUpAction(parent, parent.file.getObjects());
		addMoveDownAction(parent, parent.file.getObjects());
		addMouseListener();
		add(actions);
	}
}