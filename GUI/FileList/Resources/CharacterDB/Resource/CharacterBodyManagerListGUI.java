package GUI.FileList.Resources.CharacterDB.Resource;

import java.util.ArrayList;
import GUI.FileList.CollapseableFileList;
import ResourceManagers.CharacterDatabaseManager.CharacterBody;
import bFM.Settings;

@SuppressWarnings("serial")
public class CharacterBodyManagerListGUI extends CollapseableFileList
{
	ArrayList<CharacterBody> entries;
	CharacterResourceAssignmentList parent;
	public CharacterBodyManagerListGUI(ArrayList<CharacterBody> file, CharacterResourceAssignmentList parent) 
	{
		this.parent = parent;
		entries = file;
		padding = parent.getPadding() + Settings.indentSize;
		initializeAll();
	}
	
	protected void initializeAll()
	{
		initializeListGUI("Body Resources");
		initializeSubGUI();
		addActions();
		reAddComponents();
	}
	protected void initializeInfoGUI() 
	{
		//TODO
		//this.infoGUI = new SoundEffectListInfoGUI((SoundEffectCoordinateList) file);
	}
	public void initializeSubGUI()  
	{
		for(CharacterBody object : entries)
		{
			subEntries.add(new CharacterBodyListGUI(object, this));
		}
	}
	protected void addActions()
	{
		addChildAction("Add New Character Body", entries, CharacterBody::new, CharacterBodyListGUI::new);
		addMouseListener();
		add(actions);
		update();
	}
}