package GUI.FileList.Resources.CharacterDB.TextAnimations.Animation;

import GUI.FileInfo.CharacterDataBase.TextAnimations.CharacterAnimationPatternInfoGUI;
import GUI.FileList.FileList;
import ResourceManagers.CharacterDatabaseManager.TextAnimationList.AnimationPattern;
import bFM.Settings;

public class CharacterAnimationPatternGUI extends FileList
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -9013782295482635642L;
	AnimationPattern file;
	CharacterAnimationPartList parent;
	CharacterAnimationPatternGUI(AnimationPattern file, CharacterAnimationPartList parent) 
	{
		this.file = file;
		super.file = file;
		this.parent= parent;
		padding = Settings.indentSize + parent.getPadding();
		initializeAll();
	}
	protected void initializeAll() 
	{
		initializeListGUI("Pattern: " + file.getName());
		addActions();
	}
	protected void initializeInfoGUI() 
	{
		infoGUI = new CharacterAnimationPatternInfoGUI(file);
	}
	protected void addActions() 
	{
		addDeleteAction("Delete Animation Pattern", parent, parent.file.getPatterns());
		addMoveUpAction(parent, parent.file.getPatterns());
		addMoveDownAction(parent, parent.file.getPatterns());
		addMouseListener();
		add(actions);
	}
	public void update()
	{
		fileName.setText("Pattern: " + file.getName());
	}
}