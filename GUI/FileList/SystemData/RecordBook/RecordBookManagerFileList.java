package GUI.FileList.SystemData.RecordBook;

import java.util.ArrayList;

import javax.swing.filechooser.FileNameExtensionFilter;

import GUI.FileInfo.MenuDB.Record.RecordBookManagerInfoGUI;
import GUI.FileList.CollapseableFileList;
import SystemDataManagers.MenuDB.Books.RecordBook.RecordBookEntry;
import SystemDataManagers.MenuDB.Books.RecordBook.RecordBookManager;
import bFM.BITFileList;
import bFM.GUIUtils;

@SuppressWarnings("serial")
public class RecordBookManagerFileList extends CollapseableFileList implements BITFileList
{
	private ArrayList<RecordBookEntry> Entries = new ArrayList<RecordBookEntry>();
	RecordBookManager manager;
	public RecordBookManagerFileList(RecordBookManager file, int padding) 
	{
		manager = file;
		this.padding = padding;
		this.file = file;
		initializeAll();
	}
	protected void initializeAll() 
	{
		System.out.print("Opening Record Book Binary File: █");
		fileTypes = new FileNameExtensionFilter("Record Book Binary File", "bin");
		initializeListGUI("Record Book Manager");
		System.out.print("█");
		initializeSubGUI();
		System.out.print("█");
		initializeInfoGUI();
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
		for(RecordBookEntry object : Entries)
		{
			subEntries.add(new RecordBookEntryFileList(object, this));
		}
	}
	protected void initializeInfoGUI()
	{
		infoGUI = new RecordBookManagerInfoGUI(manager);
	}
	protected void addActions() 
	{
		addExportAction();
		addChildAction("Add Record", manager.getEntries(), RecordBookEntry::new, RecordBookEntryFileList::new);
		addReplaceRawAction();
		addExportBITAction();
		addImportBITAction();
		addReplaceBITAction();
		add(actions);
		addMouseListener();
	}
	private void addReplaceRawAction()
	{
		actions.add(GUIUtils.createImportAction("Replace Entries from raw Record.bin file", "Record Book Database Binary File", "bin", manager::replaceFromData, this));
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
