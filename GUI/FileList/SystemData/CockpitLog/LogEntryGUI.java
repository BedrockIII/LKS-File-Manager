package GUI.FileList.SystemData.CockpitLog;

import GUI.FileInfo.MenuDB.CockpitLog.CockpitLogInfoGUI;
import GUI.FileList.FileList;
import SystemDataManagers.MenuDB.CockpitLog.LogEntry;

@SuppressWarnings("serial")
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
		infoGUI = new CockpitLogInfoGUI(file);
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