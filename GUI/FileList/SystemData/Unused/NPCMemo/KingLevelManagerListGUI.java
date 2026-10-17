package GUI.FileList.SystemData.Unused.NPCMemo;

import GUI.FileList.CollapseableFileList;
import SystemDataManagers.MenuDB.Unused.NPCMemo.KingLevel;
import SystemDataManagers.MenuDB.Unused.NPCMemo.KingLevelManager;
import bFM.Settings;

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
			subEntries.add(new KingLevelListGUI(a, padding + Settings.indentSize, this));
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
