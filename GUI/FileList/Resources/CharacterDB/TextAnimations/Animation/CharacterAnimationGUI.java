package GUI.FileList.Resources.CharacterDB.TextAnimations.Animation;

import GUI.FileInfo.CharacterDataBase.TextAnimations.CharacterAnimationInfoGUI;
import GUI.FileList.CollapseableFileList;
import ResourceManagers.CharacterDatabaseManager.TextAnimationList.Animation;
import bFM.Settings;

public class CharacterAnimationGUI extends CollapseableFileList
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -9141239628027663613L;
	CharacterAnimationList parent;
	Animation file;
	public CharacterAnimationGUI(Animation file, CharacterAnimationList parent) 
	{
		this.parent = parent;
		this.file = file;
		super.file = file;
		padding = Settings.indentSize + parent.getPadding();
		initializeAll();
	}
	protected void initializeAll()
	{
		initializeListGUI("Animation: " + file.getName());
		initializeSubGUI();
		addActions();
		reAddComponents();
	}
	protected void initializeInfoGUI() 
	{
		infoGUI = new CharacterAnimationInfoGUI(file);
	}
	public void update()
	{
		fileName.setText("Animation: " + file.getName());
		super.update();
	}
	public void initializeSubGUI()  
	{
		subEntries.add(new CharacterAnimationPartList(file.getPart(), this));
	}
	protected void addActions()
	{
		addDeleteAction("Delete Animation", parent, parent.file.getAnimations());
		addMoveUpAction(parent, parent.file.getAnimations());
		addMoveDownAction(parent, parent.file.getAnimations());
		addMouseListener();
		add(actions);
	}
}