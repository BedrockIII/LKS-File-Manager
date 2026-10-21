package GUI.FileList.SystemData.MenuString;

import javax.swing.filechooser.FileNameExtensionFilter;

import GUI.FileInfo.MenuDB.MenuString.MenuStringManagerInfoGUI;
import GUI.FileList.CollapseableFileList;
import SystemDataManagers.MenuDB.MenuString.MenuString;
import SystemDataManagers.MenuDB.MenuString.MenuStringManager;
import bFM.BITFileList;
import bFM.GUIUtils;

@SuppressWarnings("serial")
public class MenuStringFileList extends CollapseableFileList implements BITFileList
{
	MenuStringManager manager;
	public MenuStringFileList(MenuStringManager file, int padding) 
	{
		manager = file;
		this.padding = padding;
		this.file = file;
		initializeAll();
	}
	protected void initializeAll() 
	{
		System.out.print("Opening Menu String Binary File: █");
		fileTypes = new FileNameExtensionFilter("Menu String Binary File", "bin");
		initializeListGUI("Menu String Manager");
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
		for(MenuString object : manager.getEntries())
		{
			subEntries.add(new MenuStringListGUI(object, this));
		}
	}
	protected void initializeInfoGUI()
	{
		infoGUI = new MenuStringManagerInfoGUI(manager);
	}
	protected void addActions() 
	{
		addExportAction();
		addChildAction("Add Menu String", manager.getEntries(), MenuString::new, MenuStringListGUI::new);
		addReplaceRawAction();
		addExportBITAction();
		addImportBITAction();
		addReplaceBITAction();
		add(actions);
		addMouseListener();
	}
	public void addExportBITAction()
	{
		actions.add(createExportBITAction(manager));
	}
	public void addImportBITAction()
	{
		actions.add(createImportBITAction(manager, this));
	}
	public void addReplaceBITAction()
	{
		actions.add(createReplaceBITAction(manager, this));
	}
	private void addReplaceRawAction()
	{
		actions.add(GUIUtils.createImportAction("Replace Entries from raw MenuString.bin file", "Menu String Database Binary File", "bin", manager::setData, this));
	}
}
