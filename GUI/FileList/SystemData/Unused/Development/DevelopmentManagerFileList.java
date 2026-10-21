package GUI.FileList.SystemData.Unused.Development;

import javax.swing.filechooser.FileNameExtensionFilter;

import GUI.FileInfo.MenuDB.Development.DevelopmentManagerFileInfo;
import GUI.FileList.CollapseableFileList;
import SystemDataManagers.MenuDB.Unused.Development.Development;
import SystemDataManagers.MenuDB.Unused.Development.DevelopmentManager;
import bFM.GUIUtils;

@SuppressWarnings("serial")
public class DevelopmentManagerFileList extends CollapseableFileList
{
	DevelopmentManager manager;
	public DevelopmentManagerFileList(DevelopmentManager file, int padding) 
	{
		manager = file;
		this.padding = padding;
		this.file = file;
		initializeAll();
	}
	protected void initializeAll() 
	{
		System.out.print("Opening (Unused) Development Binary File: █");
		fileTypes = new FileNameExtensionFilter("Development Binary File", "bin");
		initializeListGUI("(Unused) Development Manager");
		System.out.print("█");
		initializeSubGUI();
		System.out.print("█");
		//initializeInfoGUI();
		System.out.print("█");
		addActions();
		System.out.print("█");
		reAddComponents();
		System.out.println("█\nComplete!");
	}
	public void initializeSubGUI()
	{
		subEntries.removeAll(subEntries);
		for(Development object : manager.getEntries())
		{
			subEntries.add(new DevelopmentListGUI(object, this));
		}
	}
	protected void initializeInfoGUI()
	{
		infoGUI = new DevelopmentManagerFileInfo(manager);
	}
	protected void addActions() 
	{
		addExportAction();
		addReplaceRawAction();
		addChildAction("Add Development", manager.getEntries(), Development::new, DevelopmentListGUI::new);
		add(actions);
		addMouseListener();
	}
	private void addReplaceRawAction()
	{
		actions.add(GUIUtils.createImportAction("Replace Entries from raw Movie.bin file", "Movie Book Database Binary File", "bin", manager::replaceFromData, this));
	}
}
