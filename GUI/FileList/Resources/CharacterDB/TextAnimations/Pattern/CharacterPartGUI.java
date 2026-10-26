package GUI.FileList.Resources.CharacterDB.TextAnimations.Pattern;

import GUI.FileInfo.CharacterDataBase.TextAnimations.CharacterPatternPartInfoGUI;
import GUI.FileList.CollapseableFileList;
import ResourceManagers.CharacterDatabaseManager.TextAnimationList.Part2;
import ResourceManagers.CharacterDatabaseManager.TextAnimationList.Pattern;
import bFM.Settings;

public class CharacterPartGUI extends CollapseableFileList
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 2949184465719223083L;
	CharacterPatternGUI parent;
	Part2 file;
	public CharacterPartGUI(Part2 file, CharacterPatternGUI parent) 
	{
		this.parent = parent;
		super.file = file;
		this.file = file;
		padding = parent.getPadding() + Settings.indentSize;
		initializeAll();
	}
	protected void initializeAll() 
	{
		initializeListGUI("Part: " + file.getName());
		initializeSubGUI();
		addActions();
		reAddComponents();
	}
	protected void initializeInfoGUI() 
	{
		infoGUI = new CharacterPatternPartInfoGUI(file);
	}
	protected void addActions() 
	{
		addDeleteAction("Delete Part", parent, parent.file.getParts());
		addMoveUpAction(parent, parent.file.getParts());
		addMoveDownAction(parent, parent.file.getParts());
		addChildAction("Add New Pattern", file.getPatterns(), Pattern::new, CharacterPatternDetailGUI::new);
		addMouseListener();
		add(actions);
	}
	public void initializeSubGUI()
	{
		subEntries.add(new CharacterMaterialGUI(file.getMaterial(), this));
		for(Pattern part : file.getPatterns())
		{
			subEntries.add(new CharacterPatternDetailGUI(part, this));
		}
	}
	public void update()
	{
		fileName.setText("Part: " + file.getName());
		super.update();
	}
}