package GUI.FileList.Resources.CharacterDB.TextAnimations;

import GUI.FileInfo.CharacterDataBase.TextAnimations.CharacterFaceAnimationInfoGUI;
import GUI.FileList.CollapseableFileList;
import GUI.FileList.Resources.CharacterDB.TextAnimations.Animation.CharacterAnimationList;
import GUI.FileList.Resources.CharacterDB.TextAnimations.Pattern.CharacterPatternList;
import ResourceManagers.CharacterDatabaseManager.TextAnimationList;

public class CharacterTextAnimationList extends CollapseableFileList
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 7180581046777407792L;
	TextAnimationList file;
	public CharacterTextAnimationList(TextAnimationList file, int padding) 
	{
		this.file = file;
		super.file = file;
		this.padding = padding;
		initializeAll();
	}
	protected void initializeAll()
	{
		initializeListGUI("Character Face Animation Manager");
		initializeSubGUI();
		addActions();
		reAddComponents();
	}
	protected void addActions()
	{
		addMouseListener();
		add(actions);
		update();
	}
	protected void initializeInfoGUI() 
	{
		infoGUI = new CharacterFaceAnimationInfoGUI(file);
	}
	public void initializeSubGUI()  
	{
		subEntries.add(new CharacterAnimationList(file.getAnimations(), this));
		subEntries.add(new CharacterPatternList(file.getPatterns(), this));
	}
}