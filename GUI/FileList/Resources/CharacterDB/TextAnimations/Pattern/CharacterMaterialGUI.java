package GUI.FileList.Resources.CharacterDB.TextAnimations.Pattern;

import GUI.FileInfo.CharacterDataBase.TextAnimations.CharacterPatternMaterialInfoGUI;
import GUI.FileList.FileList;
import ResourceManagers.CharacterDatabaseManager.TextAnimationList.Material;
import bFM.Settings;

public class CharacterMaterialGUI extends FileList
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 8400620010309992583L;
	Material file;
	CharacterPartGUI parent;
	public CharacterMaterialGUI(Material file, CharacterPartGUI parent) 
	{
		super.file = file;
		this.file = file;
		padding = parent.getPadding() + Settings.indentSize;
		initializeAll();
	}
	protected void initializeAll() 
	{
		initializeListGUI("Material: " + file.getName());
		addActions();
	}
	protected void initializeInfoGUI() 
	{
		infoGUI = new CharacterPatternMaterialInfoGUI(file);
	}
	protected void addActions() 
	{
		addMouseListener();
		add(actions);
	}
	public void update()
	{
		fileName.setText("Material: " + file.getName());
	}
}