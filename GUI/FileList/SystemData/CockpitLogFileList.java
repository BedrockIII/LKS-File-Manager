package GUI.FileList.SystemData;

import java.util.ArrayList;

import javax.swing.JMenuItem;
import javax.swing.filechooser.FileNameExtensionFilter;

import GUI.FileList.CollapseableFileList;
import GUI.FileList.FileList;
import SystemDataManagers.MenuDB.CockpitLogManager;
import SystemDataManagers.MenuDB.LogEntry;
import bFM.Settings;

@SuppressWarnings("serial")
public class CockpitLogFileList extends CollapseableFileList
{
	private ArrayList<LogEntry> Entries = new ArrayList<LogEntry>();
	CockpitLogManager manager;
	public CockpitLogFileList(CockpitLogManager file, int padding) 
	{
		manager = file;
		this.padding = padding;
		this.file = file;
		initializeAll();
	}
	protected void initializeAll() 
	{
		System.out.print("Opening Cockpit Log Binary File: █");
		fileTypes = new FileNameExtensionFilter("Cockpit Log Binary File", "bin");
		initializeListGUI("Cockpit Log Manager");
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
		for(LogEntry object : Entries)
		{
			subEntries.add(new LogEntryGUI(object, padding + Settings.indentSize, this));
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
		JMenuItem newZone = new JMenuItem("Create New Log Entry");
		newZone.addActionListener(e -> 
		{
			LogEntry entry = new LogEntry();
			Entries.add(entry);
			subEntries.add(new LogEntryGUI(entry, padding + Settings.indentSize, this));
			reAddComponents();
		});
		//actions.add(newZone);
	}
	private void addExportBJBAction()
	{
		//actions.add(GUIUtils.createExportAction("Export Entries as .bit text file", "HummingBook.bit", "Bedrock's Intermediate Text File", manager::toIntermediateText));
	}
	private void addImportBJBAction()
	{
		//actions.add(GUIUtils.createImportAction("Import Entries from .bit text file", "Bedrock's Intermediate Text File", "bit", manager::importFromIntermediateText, this));
	}
	private void addReplaceBJBAction()
	{
		//actions.add(GUIUtils.createImportAction("Replace Entries from .bit text file", "Bedrock's Intermediate Text File", "bit", manager::replaceFromIntermediateText, this));
	}
	private void addReplaceRawAction()
	{
		//actions.add(GUIUtils.createImportAction("Replace Entries from raw Humming.bin file", "Humming Book Database Binary File", "bin", manager::replaceFromData, this));
	}
	public class LogEntryGUI extends FileList
	{
		LogEntry file;
		CockpitLogFileList parent;
		public LogEntryGUI(LogEntry entry, int padding, CockpitLogFileList parent)
		{
			this.padding = padding;
			this.file = entry;
			this.parent = parent;
			initializeAll();
		}
		protected void initializeAll() 
		{
			initializeListGUI("Entry: \"" + file.getText() + "\"");
			//initializeInfoGUI();
			addActions();
		}
		protected void initializeInfoGUI() 
		{
			//TODO
			//this.infoGUI = new HummingEntryInfoGUI(file);
		}
		protected void addActions() 
		{
			add(actions);
			addMouseListener();
		}
		public void update()
		{
			fileName.setText("Entry: \"" + file.getText() + "\"");
			super.update();
		}
	}
}
