package GUI.FileList.Resources.CharacterDB.Join;

import java.util.ArrayList;

import javax.swing.filechooser.FileNameExtensionFilter;

import GUI.FileInfo.CharacterDataBase.CharacterJoinListInfoGUI;
import GUI.FileList.CollapseableFileList;
import ResourceManagers.CharacterDatabaseManager.JoinBinList;
import ResourceManagers.CharacterDatabaseManager.join;

public class CharacterJoinFileList extends CollapseableFileList
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1898253101748690201L;
	ArrayList<join> objects = new ArrayList<join>();
	JoinBinList file;
	public CharacterJoinFileList(JoinBinList file, int padding) 
	{
		this.padding = padding;
		this.file = file;
		super.file = file;
		initializeAll();
	}
	protected void initializeAll()
	{
		fileTypes = new FileNameExtensionFilter("List File", "lst");
		initializeListGUI("Character Join Manager");
		initializeSubGUI();
		addActions();
		reAddComponents();
	}
	protected void initializeInfoGUI() 
	{
		this.infoGUI = new CharacterJoinListInfoGUI((JoinBinList) file);
	}
	public void initializeSubGUI()  
	{
		for(join object : file.getObjects())
		{
			subEntries.add(new JoinListGUI(object, this));
		}
	}
	protected void addActions()
	{
		addChildAction("Add New Character Sound Effect", objects, join::new, JoinListGUI::new);
		addMouseListener();
		add(actions);
	}
}