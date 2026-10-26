package GUI.FileList.Resources.CharacterDB.Resource;

import java.util.ArrayList;
import GUI.FileList.CollapseableFileList;
import ResourceManagers.CharacterDatabaseManager.CharacterFace;
import bFM.Settings;

@SuppressWarnings("serial")
public class CharacterFaceManagerListGUI extends CollapseableFileList
{
	ArrayList<CharacterFace> entries;
	CharacterResourceAssignmentList parent;
	public CharacterFaceManagerListGUI(ArrayList<CharacterFace> file, CharacterResourceAssignmentList parent) 
	{
		padding = parent.getPadding() + Settings.indentSize;
		this.parent = parent;
		this.entries = file;
		initializeAll();
	}
	
	protected void initializeAll()
	{
		initializeListGUI("Face Resources");
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
		for(CharacterFace object : entries)
		{
			subEntries.add(new CharacterFaceListGUI(object, this));
		}
	}
	protected void addActions()
	{
		addChildAction("Add New Character Face", entries, CharacterFace::new, CharacterFaceListGUI::new);
		addMouseListener();
		add(actions);
		update();
	}
}