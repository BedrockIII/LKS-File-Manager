package GUI.FileList.SystemData.MapData;

import GUI.FileInfo.MenuDB.MapData.MapQuestInfoGUI;
import GUI.FileList.FileList;
import SystemDataManagers.MenuDB.MapData.MapQuestInfo;
import bFM.Settings;

@SuppressWarnings("serial")
public class MapQuestFileList extends FileList
{
	MapQuestManagerFileList parent;
	MapQuestInfo Quest;
	public MapQuestFileList(MapQuestInfo Quest, MapQuestManagerFileList parent)
	{
		this.parent = parent;
		padding = parent.getPadding() + Settings.indentSize;
		this.Quest = Quest;
		file = Quest;
		initializeAll();
	}
	protected void initializeAll()
	{
		initializeListGUI("\"" + Quest.getName() + "\"");
		addActions();
	}
	protected void initializeInfoGUI()
	{
		infoGUI = new MapQuestInfoGUI(Quest);
	}
	protected void addActions()
	{
		addDeleteAction("Delete Quest", parent, parent.manager.getQuests());
		addMoveUpAction(parent, parent.manager.getQuests());
		addMoveDownAction(parent, parent.manager.getQuests());
		add(actions);
		addMouseListener();
	}
	public void update()
	{
		fileName.setText("\"" + Quest.getName() + "\"");
		super.update();
	}
}
