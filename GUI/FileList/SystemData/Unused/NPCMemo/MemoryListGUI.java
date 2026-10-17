package GUI.FileList.SystemData.Unused.NPCMemo;

import GUI.FileList.FileList;
import SystemDataManagers.MenuDB.Unused.NPCMemo.MemoryMemo;
import bFM.Settings;

@SuppressWarnings("serial")
public class MemoryListGUI extends FileList
{
	MemoryManagerListGUI parent;
	MemoryMemo Memory;
	public MemoryListGUI(MemoryMemo Memory, MemoryManagerListGUI parent)
	{
		this.parent = parent;
		this.padding = parent.getPadding() + Settings.indentSize;
		this.Memory = Memory;
		file = Memory;
		initializeAll();
	}
	protected void initializeAll()
	{
		initializeListGUI("\"" + Memory.getName() + "\"");
		addActions();
	}
	protected void initializeInfoGUI()
	{
		//TODO
		//infoGUI = new MapAreaInfoGUI(Level);
	}
	protected void addActions()
	{
		addDeleteAction(parent, parent.manager.getEntries(), "Delete Memory");
		addMoveUpAction(parent, parent.manager.getEntries());
		addMoveDownAction(parent, parent.manager.getEntries());
		add(actions);
		addMouseListener();
	}
	public void update()
	{
		fileName.setText("\"" + Memory.getName() + "\"");
		super.update();
	}
}
