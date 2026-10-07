package GUI.FileList.SystemData.RecordBook;

import java.util.ArrayList;

import javax.swing.JMenuItem;
import javax.swing.filechooser.FileNameExtensionFilter;

import GUI.FileList.CollapseableFileList;
import SystemDataManagers.MenuDB.Books.RecordBook.RecordBookEntry;
import SystemDataManagers.MenuDB.Books.RecordBook.RecordBookManager;
import bFM.GUIUtils;
import bFM.Settings;

@SuppressWarnings("serial")
public class RecordBookManagerFileList extends CollapseableFileList
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
			subEntries.add(new RecordBookEntryFileList(object, padding + Settings.indentSize, this));
		}
	}
	protected void initializeInfoGUI()
	{
		//TODO
		//infoGUI = new HummingBookListInfoGUI(manager);
	}
	protected void addActions() 
	{
		addExportAction();
		addReplaceRawAction();
		addExportBJBAction();
		addImportBJBAction();
		addReplaceBJBAction();
		addZoneAction();
		add(actions);
		addMouseListener();
	}
	private void addZoneAction()
	{
		JMenuItem newZone = new JMenuItem("Create New Record");
		newZone.addActionListener(e -> 
		{
			RecordBookEntry entry = new RecordBookEntry();
			Entries.add(entry);
			subEntries.add(new RecordBookEntryFileList(entry, padding + Settings.indentSize, this));
			reAddComponents();
		});
		actions.add(newZone);
	}
	private void addExportBJBAction()
	{
		actions.add(GUIUtils.createExportAction("Export Entries as .bit text file", "RecordBook.bit", "Bedrock's Intermediate Text File", manager::toIntermediateText));
	}
	private void addImportBJBAction()
	{
		actions.add(GUIUtils.createImportAction("Import Entries from .bit text file", "Bedrock's Intermediate Text File", "bit", manager::importFromIntermediateText, this));
	}
	private void addReplaceBJBAction()
	{
		actions.add(GUIUtils.createImportAction("Replace Entries from .bit text file", "Bedrock's Intermediate Text File", "bit", manager::replaceFromIntermediateText, this));
	}
	private void addReplaceRawAction()
	{
		actions.add(GUIUtils.createImportAction("Replace Entries from raw Record.bin file", "Record Book Database Binary File", "bin", manager::replaceFromData, this));
	}
	public void removeEntry(RecordBookEntryFileList gui) 
	{
		subEntries.remove(gui);
		Entries.remove(gui.file);
		reAddComponents();
	}
}
