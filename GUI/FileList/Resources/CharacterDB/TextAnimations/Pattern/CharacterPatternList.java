package GUI.FileList.Resources.CharacterDB.TextAnimations.Pattern;

import GUI.FileInfo.CharacterDataBase.TextAnimations.CharacterPatternListInfoGUI;
import GUI.FileList.CollapseableFileList;
import GUI.FileList.Resources.CharacterDB.TextAnimations.CharacterTextAnimationList;
import ResourceManagers.CharacterDatabaseManager.TextAnimationList.PatternList;
import ResourceManagers.CharacterDatabaseManager.TextAnimationList.PatternPart;
import bFM.Settings;

public class CharacterPatternList extends CollapseableFileList
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -3064127008907385133L;
	CharacterTextAnimationList parent;
	PatternList file;
	public CharacterPatternList(PatternList file, CharacterTextAnimationList parent) 
	{
		super.file = file;
		this.file = file;
		this.parent = parent;
		padding = parent.getPadding() + Settings.indentSize;
		initializeAll();
	}
	protected void initializeAll() 
	{
		initializeListGUI("Pattern List");
		initializeSubGUI();
		addActions();
		reAddComponents();
	}
	protected void initializeInfoGUI() 
	{
		infoGUI = new CharacterPatternListInfoGUI(file);
	}
	protected void addActions() 
	{
		addChildAction("Add New Pattern", file.getPatterns(), PatternPart::new, CharacterPatternGUI::new);
		addMouseListener();
		add(actions);
	}
	public void initializeSubGUI()  
	{
		for(PatternPart pattern : file.getPatterns())
		{
			subEntries.add(new CharacterPatternGUI(pattern, this));
		}
	}
}