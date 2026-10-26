package GUI.FileList.Resources.CharacterDB.Index;

import GUI.FileInfo.CharacterDataBase.CharacterIndexListInfoGUI;
import GUI.FileList.CollapseableFileList;
import ResourceManagers.CharacterDatabaseManager.ind;
import ResourceManagers.CharacterDatabaseManager.indBinList;

@SuppressWarnings("serial")
public class IndexFileList extends CollapseableFileList
{
	indBinList file;
	public IndexFileList(indBinList file, int padding) 
	{
		this.padding = padding;
		this.file = file;
		super.file = file;
		initializeAll();
	}
	protected void initializeAll()
	{
		initializeListGUI("Character Indicies Manager");
		initializeSubGUI();
		addActions();
		reAddComponents();
	}
	protected void initializeInfoGUI() 
	{
		infoGUI = new CharacterIndexListInfoGUI(file);
	}
	public void initializeSubGUI()  
	{
		for(ind object : file.getIndicies())
		{
			subEntries.add(new IndexListGUI(object, this));
		}
	}
	protected void addActions()
	{
		addChildAction("Add New Character Index", file.getIndicies(), ind::new, IndexListGUI::new);
		addMouseListener();
		add(actions);
	}
}