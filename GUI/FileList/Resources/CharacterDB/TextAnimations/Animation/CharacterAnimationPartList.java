package GUI.FileList.Resources.CharacterDB.TextAnimations.Animation;

import GUI.FileInfo.CharacterDataBase.TextAnimations.CharacterAnimationPartInfoGUI;
import GUI.FileList.CollapseableFileList;
import ResourceManagers.CharacterDatabaseManager.TextAnimationList.AnimationPattern;
import ResourceManagers.CharacterDatabaseManager.TextAnimationList.Part;
import bFM.Settings;

@SuppressWarnings("serial")
public class CharacterAnimationPartList extends CollapseableFileList
{
	CharacterAnimationGUI parent;
	Part file;
	public CharacterAnimationPartList(Part file, CharacterAnimationGUI parent) 
	{
		this.parent = parent;
		this.file = file;
		super.file = file;
		padding = Settings.indentSize + parent.getPadding();
		initializeAll();
	}
	protected void initializeAll() 
	{
		initializeListGUI("Part: " + file.getName());
		initializeSubGUI();
		//initializeInfoGUI();
		addActions();
		reAddComponents();
	}
	protected void initializeInfoGUI() 
	{
		infoGUI = new CharacterAnimationPartInfoGUI(file);
	}
	public void update()
	{
		fileName.setText("Part: " + file.getName());
		super.update();
	}
	public void initializeSubGUI()  
	{
		for(AnimationPattern pattern : file.getPatterns())
		{
			subEntries.add(new CharacterAnimationPatternGUI(pattern, this));
		}
	}
	protected void addActions() 
	{
		addChildAction("Add New Animation Pattern", file.getPatterns(), AnimationPattern::new, CharacterAnimationPatternGUI::new);
		addMouseListener();
		add(actions);
	}
}