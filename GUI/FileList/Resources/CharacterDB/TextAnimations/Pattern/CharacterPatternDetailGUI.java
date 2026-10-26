package GUI.FileList.Resources.CharacterDB.TextAnimations.Pattern;

import GUI.FileInfo.CharacterDataBase.TextAnimations.CharacterPartPatternInfoGUI;
import GUI.FileList.FileList;
import ResourceManagers.CharacterDatabaseManager.TextAnimationList.Pattern;
import bFM.Settings;

public class CharacterPatternDetailGUI extends FileList
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3016581088998973830L;
	Pattern file;
	CharacterPartGUI parent;
	public CharacterPatternDetailGUI(Pattern file, CharacterPartGUI parent) 
	{
		this.parent = parent;
		this.file = file;
		super.file = file;
		padding = parent.getPadding() + Settings.indentSize;
		initializeAll();
	}
	protected void initializeAll() 
	{
		initializeListGUI("Pattern: " + file.getName());
		addActions();
	}
	protected void initializeInfoGUI() 
	{
		infoGUI = new CharacterPartPatternInfoGUI(file);
	}
	protected void addActions() 
	{
		addDeleteAction("Delete Pattern Detail", parent, parent.file.getPatterns());
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