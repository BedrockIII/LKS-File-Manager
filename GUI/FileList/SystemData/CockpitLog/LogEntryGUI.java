package GUI.FileList.SystemData.CockpitLog;

import GUI.FileInfo.MenuDB.CockpitLog.CockpitLogInfoGUI;
import GUI.FileList.FileList;
import SystemDataManagers.MenuDB.CockpitLog.LogEntry;
import bFM.Settings;

@SuppressWarnings("serial")
public class LogEntryGUI extends FileList
{
	LogEntry entry;
	CockpitLogFileList parent;
	public LogEntryGUI(LogEntry entry, CockpitLogFileList parent)
	{
		this.padding = parent.getPadding() + Settings.indentSize;
		this.entry = entry;
		this.file = entry;
		this.parent = parent;
		initializeAll();
	}
	protected void initializeAll() 
	{
		initializeListGUI("Entry: \"" + entry.getText() + "\"");
		addActions();
	}
	protected void initializeInfoGUI() 
	{
		infoGUI = new CockpitLogInfoGUI(entry);
	}
	protected void addActions() 
	{
		addDeleteAction("Delete Log Entry", parent, parent.manager.getEntries());
		addMoveUpAction(parent, parent.manager.getEntries());
		addMoveDownAction(parent, parent.manager.getEntries());
		add(actions);
		addMouseListener();
	}
	public void update()
	{
		fileName.setText("Entry: \"" + entry.getText() + "\"");
		super.update();
	}
}