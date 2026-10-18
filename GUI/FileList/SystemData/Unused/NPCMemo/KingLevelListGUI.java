package GUI.FileList.SystemData.Unused.NPCMemo;

import GUI.FileInfo.MenuDB.NPCMemo.KingLevelInfoGUI;
import GUI.FileList.FileList;
import SystemDataManagers.MenuDB.Unused.NPCMemo.KingLevel;

@SuppressWarnings("serial")
public class KingLevelListGUI extends FileList
{
	KingLevelManagerListGUI parent;
	KingLevel Level;
	public KingLevelListGUI(KingLevel Level, int padding, KingLevelManagerListGUI parent)
	{
		this.parent = parent;
		this.padding = padding;
		this.Level = Level;
		file = Level;
		initializeAll();
	}
	protected void initializeAll()
	{
		initializeListGUI("\"" + Level.getName() + "\"");
		addActions();
	}
	protected void initializeInfoGUI()
	{
		infoGUI = new KingLevelInfoGUI(Level);
	}
	protected void addActions()
	{
		// TODO Add Delete Action
		addMoveUpAction(parent, parent.manager.getEntries());
		addMoveDownAction(parent, parent.manager.getEntries());
		add(actions);
		addMouseListener();
	}
	public void update()
	{
		fileName.setText("\"" + Level.getName() + "\"");
		super.update();
	}
}
