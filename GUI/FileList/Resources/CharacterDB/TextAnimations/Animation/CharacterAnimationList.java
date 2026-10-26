package GUI.FileList.Resources.CharacterDB.TextAnimations.Animation;

import GUI.FileInfo.CharacterDataBase.TextAnimations.CharacterAnimationListInfoGUI;
import GUI.FileList.CollapseableFileList;
import GUI.FileList.Resources.CharacterDB.TextAnimations.CharacterTextAnimationList;
import ResourceManagers.CharacterDatabaseManager.TextAnimationList.Animation;
import ResourceManagers.CharacterDatabaseManager.TextAnimationList.AnimationList;
import bFM.Settings;

public class CharacterAnimationList extends CollapseableFileList
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -7123480642008736050L;
	CharacterTextAnimationList parent;
	AnimationList file;
	public CharacterAnimationList(AnimationList file, CharacterTextAnimationList parent) 
	{
		this.file = file;
		super.file = file;
		padding = Settings.indentSize + parent.getPadding();
		initializeAll();
	}
	protected void initializeAll()
	{
		initializeListGUI("Animations List");
		initializeSubGUI();
		addActions();
		reAddComponents();
	}
	protected void initializeInfoGUI() 
	{
		this.infoGUI = new CharacterAnimationListInfoGUI((AnimationList) file);
	}
	public void initializeSubGUI()  
	{
		for(Animation animation : file.getAnimations())
		{
			subEntries.add(new CharacterAnimationGUI(animation, this));
		}
	}
	protected void addActions()
	{
		addChildAction("Add New Animation", file.getAnimations(), Animation::new, CharacterAnimationGUI::new);
		addMouseListener();
		add(actions);
	}
}