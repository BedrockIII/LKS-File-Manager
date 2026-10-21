package GUI.FileList.SystemData.Unused.NPCMemo;

import GUI.FileInfo.MenuDB.NPCMemo.KingLevelManagerInfoGUI;
import GUI.FileList.CollapseableFileList;
import SystemDataManagers.MenuDB.Unused.NPCMemo.KingLevel;
import SystemDataManagers.MenuDB.Unused.NPCMemo.KingLevelManager;

@SuppressWarnings("serial")
public class KingLevelManagerListGUI extends CollapseableFileList
{
	KingLevelManager manager;
	public KingLevelManagerListGUI(KingLevelManager manager, int padding) 
	{
		this.manager = manager;
		this.padding = padding;
		this.file = manager;
		initializeAll();
	}
	protected void initializeAll() 
	{
		initializeListGUI("King Levels");
		initializeSubGUI();
		addActions();
		reAddComponents();
	}
	public void initializeSubGUI()
	{
		subEntries.removeAll(subEntries);
		for(KingLevel a : manager.getEntries())
		{
			subEntries.add(new KingLevelListGUI(a, this));
		}
		
	}
	protected void initializeInfoGUI()
	{
		infoGUI = new KingLevelManagerInfoGUI(manager);
	}
	protected void addActions() 
	{
		addChildAction("Add King Level", manager.getEntries(), KingLevel::new, KingLevelListGUI::new);
		add(actions);
		addMouseListener();
	}
}
