package GUI.FileList.SystemData.MapData;

import GUI.FileInfo.MenuDB.MapData.MapQuestManagerInfoGUI;
import GUI.FileList.CollapseableFileList;
import SystemDataManagers.MenuDB.MapData.MapQuestInfo;
import SystemDataManagers.MenuDB.MapData.MapQuestInfoManager;

@SuppressWarnings("serial")
public class MapQuestManagerFileList extends CollapseableFileList
{
	MapQuestInfoManager manager;
	public MapQuestManagerFileList(MapQuestInfoManager m, int padding) 
	{
		manager = m;
		this.padding = padding;
		this.file = m;
		initializeAll();
	}
	protected void initializeAll() 
	{
		initializeListGUI("(Unused?) Quest Data");
		initializeSubGUI();
		addActions();
		reAddComponents();
	}
	public void initializeSubGUI()
	{
		subEntries.removeAll(subEntries);
		for(MapQuestInfo q : manager.getQuests())
		{
			subEntries.add(new MapQuestFileList(q, this));
		}
		
	}
	protected void initializeInfoGUI()
	{
		infoGUI = new MapQuestManagerInfoGUI(manager);
	}
	protected void addActions() 
	{
		addChildAction("Add Quest", manager.getQuests(), MapQuestInfo::new, MapQuestFileList::new);
		add(actions);
		addMouseListener();
	}
}
