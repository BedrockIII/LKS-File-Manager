package GUI.FileList.Resources.CharacterDB.Resource;

import java.util.ArrayList;

import javax.swing.filechooser.FileNameExtensionFilter;

import GUI.FileInfo.CharacterDataBase.CharacterResourceListGUI;
import GUI.FileList.CollapseableFileList;
import ResourceManagers.CharacterDatabaseManager.CharacterBody;
import ResourceManagers.CharacterDatabaseManager.CharacterFace;
import ResourceManagers.CharacterDatabaseManager.CharacterResourceList;

public class CharacterResourceAssignmentList extends CollapseableFileList
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 7155105760559263077L;
	ArrayList<CharacterBody> bodies = new ArrayList<CharacterBody>();
	ArrayList<CharacterFace> faces = new ArrayList<CharacterFace>();
	CharacterResourceList file;
	public CharacterResourceAssignmentList(CharacterResourceList file, int padding) 
	{
		this.padding = padding;
		this.file = file;
		super.file = file;
		initializeAll();
	}
	protected void initializeAll()
	{
		fileTypes = new FileNameExtensionFilter("List File", "lst");
		initializeListGUI("Character Resource Manager");
		initializeSubGUI();
		addActions();
		reAddComponents();
	}
	protected void initializeInfoGUI() 
	{
		infoGUI = new CharacterResourceListGUI((CharacterResourceList) file);
	}
	public void initializeSubGUI() 
	{
		subEntries.add(new CharacterBodyManagerListGUI(file.getBodies(), this));
		subEntries.add(new CharacterFaceManagerListGUI(file.getFaces(), this));
	}
	protected void addActions()
	{
		addMouseListener();
		add(actions);
	}
}