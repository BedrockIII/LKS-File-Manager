package GUI.FileList.SystemData.AnimalBook;

import java.util.ArrayList;

import javax.swing.filechooser.FileNameExtensionFilter;

import GUI.FileInfo.MenuDB.AnimalBook.AnimalBookListInfoGUI;
import GUI.FileList.CollapseableFileList;
import SystemDataManagers.MenuDB.Books.AnimalBook.AnimalBookManager;
import SystemDataManagers.MenuDB.Books.AnimalBook.AnimalEntry;
import bFM.BITFileList;
import bFM.GUIUtils;

@SuppressWarnings("serial")
public class AnimalBookFileList extends CollapseableFileList implements BITFileList
{
	private ArrayList<AnimalEntry> Entries = new ArrayList<AnimalEntry>();
	AnimalBookManager manager;
	public AnimalBookFileList(AnimalBookManager file, int padding) 
	{
		manager = file;
		this.padding = padding;
		this.file = file;
		initializeAll();
	}
	protected void initializeAll() 
	{
		System.out.print("Opening Animal Book Binary File: █");
		fileTypes = new FileNameExtensionFilter("Animal Book Binary File", "bin");
		initializeListGUI("Animal Book Manager");
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
		Entries = manager.getEntries();
		for(AnimalEntry object : Entries)
		{
			subEntries.add(new AnimalGUI(object, this));
		}
	}
	protected void initializeInfoGUI()
	{
		infoGUI = new AnimalBookListInfoGUI(manager);
	}
	protected void addActions() 
	{
		addExportAction();
		addChildAction("Add Animal Spot", manager.getEntries(), AnimalEntry::new, AnimalGUI::new);
		addReplaceRawAction();
		addExportBITAction();
		addImportBITAction();
		addReplaceBITAction();
		add(actions);
		addMouseListener();
	}
	private void addReplaceRawAction()
	{
		actions.add(GUIUtils.createImportAction("Replace Entries from raw AnimalBook.bin file", "Animal Book Database Binary File", "bin", manager::replaceFromData, this));
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
