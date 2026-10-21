package GUI.FileList.SystemData.Unused.NPCMemo;

import GUI.FileInfo.MenuDB.NPCMemo.ActionManagerInfoGUI;
import GUI.FileList.CollapseableFileList;
import SystemDataManagers.MenuDB.Unused.NPCMemo.ActionMemo;
import SystemDataManagers.MenuDB.Unused.NPCMemo.ActionMemoManager;

@SuppressWarnings("serial")
public class ActionManagerListGUI extends CollapseableFileList
{
	ActionMemoManager manager;
	String Name;
	public ActionManagerListGUI(ActionMemoManager manager, int padding, String Name) 
	{
		this.manager = manager;
		this.padding = padding;
		this.file = manager;
		this.Name = Name;
		initializeAll();
	}
	protected void initializeAll() 
	{
		initializeListGUI(Name + " NPC Actions");
		initializeSubGUI();
		addActions();
		reAddComponents();
	}
	public void initializeSubGUI()
	{
		subEntries.removeAll(subEntries);
		for(ActionMemo a : manager.getEntries())
		{
			subEntries.add(new ActionListGUI(a, this));
		}
		
	}
	protected void initializeInfoGUI()
	{
		infoGUI = new ActionManagerInfoGUI(manager);
	}
	protected void addActions() 
	{
		addChildAction("Add NPC Action", manager.getEntries(), ActionMemo::new, ActionListGUI::new);
		add(actions);
		addMouseListener();
	}
}
