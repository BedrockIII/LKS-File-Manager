package GUI.FileList.SystemData.MapData;

import GUI.FileInfo.MenuDB.MapData.MapQuestInfoGUI;
import GUI.FileList.FileList;
import SystemDataManagers.MenuDB.MapData.MapQuestInfo;

@SuppressWarnings("serial")
public class MapQuestFileList extends FileList
{
	MapQuestManagerFileList parent;
	MapQuestInfo Quest;
	public MapQuestFileList(MapQuestInfo Quest, int padding, MapQuestManagerFileList parent)
	{
		this.parent = parent;
		this.padding = padding;
		this.Quest = Quest;
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
		// TODO Add Delete Action
		add(actions);
		addMouseListener();
	}
	public void update()
	{
		fileName.setText("\"" + Quest.getName() + "\"");
		super.update();
	}
}
