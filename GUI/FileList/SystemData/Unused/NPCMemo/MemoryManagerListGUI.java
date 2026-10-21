package GUI.FileList.SystemData.Unused.NPCMemo;

import GUI.FileInfo.MenuDB.NPCMemo.MemoryManagerInfoGUI;
import GUI.FileList.CollapseableFileList;
import SystemDataManagers.MenuDB.Unused.NPCMemo.MemoryMemo;
import SystemDataManagers.MenuDB.Unused.NPCMemo.MemoryMemoManager;

@SuppressWarnings("serial")
public class MemoryManagerListGUI extends CollapseableFileList
{
	MemoryMemoManager manager;
	String Name;
	public MemoryManagerListGUI(MemoryMemoManager manager, int padding, String Name) 
	{
		this.manager = manager;
		this.padding = padding;
		this.file = manager;
		this.Name = Name;
		initializeAll();
	}
	protected void initializeAll() 
	{
		initializeListGUI(Name + " NPC Memories");
		initializeSubGUI();
		addActions();
		reAddComponents();
	}
	public void initializeSubGUI()
	{
		subEntries.removeAll(subEntries);
		for(MemoryMemo a : manager.getEntries())
		{
			subEntries.add(new MemoryListGUI(a, this));
		}
		
	}
	protected void initializeInfoGUI()
	{
		infoGUI = new MemoryManagerInfoGUI(manager);
	}
	protected void addActions() 
	{
		add(actions);
		addChildAction("Add Memory", manager.getEntries(), MemoryMemo::new, MemoryListGUI::new);
		addMouseListener();
	}
}
