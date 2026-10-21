package GUI.FileList.SystemData.CockpitLog;

import java.util.ArrayList;

import javax.swing.filechooser.FileNameExtensionFilter;

import GUI.FileInfo.MenuDB.CockpitLog.CockpitLogManagerInfoGUI;
import GUI.FileList.CollapseableFileList;
import SystemDataManagers.MenuDB.CockpitLog.CockpitLogManager;
import SystemDataManagers.MenuDB.CockpitLog.LogEntry;
import bFM.BITFileList;
import bFM.GUIUtils;

@SuppressWarnings("serial")
public class CockpitLogFileList extends CollapseableFileList implements BITFileList
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
			subEntries.add(new LogEntryGUI(object, this));
		}
	}
	protected void initializeInfoGUI()
	{
		infoGUI = new CockpitLogManagerInfoGUI(manager);
	}
	protected void addActions() 
	{
		addExportAction();
		addReplaceRawAction();
		addExportBITAction();
		addImportBITAction();
		addReplaceBITAction();
		addChildAction("Add New Log", Entries, LogEntry::new, LogEntryGUI::new);
		add(actions);
		addMouseListener();
	}
	private void addReplaceRawAction()
	{
		actions.add(GUIUtils.createImportAction("Replace Entries from raw CockpitLog.bin file", "Cockpit Log Database Binary File", "bin", manager::replaceFromData, this));
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
