package GUI.FileList.Resources.CharacterDB.SoundEffect;

import GUI.FileInfo.CharacterDataBase.SoundEffectListInfoGUI;
import GUI.FileList.CollapseableFileList;
import ResourceManagers.CharacterDatabaseManager.SoundEffectCoordinate;
import ResourceManagers.CharacterDatabaseManager.SoundEffectCoordinateList;

@SuppressWarnings("serial")
public class SoundEffectFileList extends CollapseableFileList
{
	SoundEffectCoordinateList file;
	public SoundEffectFileList(SoundEffectCoordinateList file, int padding) 
	{
		this.padding = padding;
		this.file = file;
		super.file = file;
		initializeAll();
	}
	
	protected void initializeAll()
	{
		initializeListGUI("Character Sound Effect Manager");
		initializeSubGUI();
		addActions();
		reAddComponents();
	}
	protected void initializeInfoGUI() 
	{
		this.infoGUI = new SoundEffectListInfoGUI((SoundEffectCoordinateList) file);
	}
	public void initializeSubGUI()  
	{
		for(SoundEffectCoordinate object : file.getObjects())
		{
			subEntries.add(new SoundEffectListGUI(object, this));
		}
	}
	protected void addActions()
	{
		addChildAction("Add New Character Sound Effect", file.getObjects(), SoundEffectCoordinate::new, SoundEffectListGUI::new);
		addMouseListener();
		add(actions);
	}
}