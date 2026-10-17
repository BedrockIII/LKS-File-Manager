package GUI.FileList.SystemData.Unused.NPCMemo;

import GUI.FileList.CollapseableFileList;
import SystemDataManagers.MenuDB.Unused.NPCMemo.ActionMemo;
import SystemDataManagers.MenuDB.Unused.NPCMemo.ActionMemoManager;
import bFM.Settings;

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
			subEntries.add(new ActionListGUI(a, padding + Settings.indentSize, this));
		}
		
	}
	protected void initializeInfoGUI()
	{
		//TODO
		//infoGUI = new MapAreaManagerInfoGUI(manager);
	}
	protected void addActions() 
	{
		add(actions);
		addMouseListener();
	}
}
