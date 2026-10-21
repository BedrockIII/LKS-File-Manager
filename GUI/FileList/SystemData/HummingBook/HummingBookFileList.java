package GUI.FileList.SystemData.HummingBook;

import javax.swing.filechooser.FileNameExtensionFilter;

import GUI.FileInfo.MenuDB.HummingBook.HummingBookListInfoGUI;
import GUI.FileList.CollapseableFileList;
import SystemDataManagers.MenuDB.Books.HummingBook.HummingBookEntry;
import SystemDataManagers.MenuDB.Books.HummingBook.HummingBookManager;
import bFM.BITFileList;
import bFM.GUIUtils;

@SuppressWarnings("serial")
public class HummingBookFileList extends CollapseableFileList implements BITFileList
{
	HummingBookManager manager;
	public HummingBookFileList(HummingBookManager file, int padding) 
	{
		manager = file;
		this.padding = padding;
		this.file = file;
		initializeAll();
	}
	protected void initializeAll() 
	{
		System.out.print("Opening Humming Book Binary File: █");
		fileTypes = new FileNameExtensionFilter("Humming Book Binary File", "bin");
		initializeListGUI("Humming Book Manager");
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
		for(HummingBookEntry object : manager.getEntries())
		{
			subEntries.add(new HumGUI(object, this));
		}
	}
	protected void initializeInfoGUI()
	{
		infoGUI = new HummingBookListInfoGUI(manager);
	}
	protected void addActions() 
	{
		addExportAction();
		addChildAction("Add Hum Entry", manager.getEntries(), HummingBookEntry::new, HumGUI::new);
		addReplaceRawAction();
		addExportBITAction();
		addImportBITAction();
		addReplaceBITAction();
		add(actions);
		addMouseListener();
	}
	private void addReplaceRawAction()
	{
		actions.add(GUIUtils.createImportAction("Replace Hums from raw Humming.bin file", "Humming Book Database Binary File", "bin", manager::replaceFromData, this));
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
}
