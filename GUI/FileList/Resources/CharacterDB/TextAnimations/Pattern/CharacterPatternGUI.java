package GUI.FileList.Resources.CharacterDB.TextAnimations.Pattern;

import GUI.FileInfo.CharacterDataBase.TextAnimations.CharacterPatternInfoGUI;
import GUI.FileList.CollapseableFileList;
import ResourceManagers.CharacterDatabaseManager.TextureAnimation.Pattern.Part;
import ResourceManagers.CharacterDatabaseManager.TextureAnimation.Pattern.PatternPart;
import bFM.Settings;

public class CharacterPatternGUI extends CollapseableFileList
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 275494804475656862L;
	CharacterPatternList parent;
	PatternPart file;
	public CharacterPatternGUI(PatternPart file, CharacterPatternList parent) 
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
		initializeSubGUI();
		addActions();
		reAddComponents();
	}
	protected void initializeInfoGUI() 
	{
		infoGUI = new CharacterPatternInfoGUI(file);
	}
	protected void addActions() 
	{
		addDeleteAction("Delete Pattern", parent, parent.file.getPatterns());
		addMoveUpAction(parent, parent.file.getPatterns());
		addMoveDownAction(parent, parent.file.getPatterns());
		addChildAction("Add New Part", file.getParts(), Part::new, CharacterPartGUI::new);
		addMouseListener();
		add(actions);
	}
	public void initializeSubGUI()
	{
		for(Part part : file.getParts())
		{
			subEntries.add(new CharacterPartGUI(part, this));
		}
	}
	public void update()
	{
		fileName.setText("Pattern: " + file.getName());
		super.update();
	}
}