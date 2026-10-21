package GUI.FileList.SystemData.Unused.NPCMemo;

import GUI.FileInfo.MenuDB.NPCMemo.KingLevelInfoGUI;
import GUI.FileList.FileList;
import SystemDataManagers.MenuDB.Unused.NPCMemo.KingLevel;
import bFM.Settings;

@SuppressWarnings("serial")
public class KingLevelListGUI extends FileList
{
	KingLevelManagerListGUI parent;
	KingLevel Level;
	public KingLevelListGUI(KingLevel Level, KingLevelManagerListGUI parent)
	{
		padding = parent.getPadding() + Settings.indentSize;
		this.Level = Level;
		super.file = Level;
		this.parent = parent;
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
		addDeleteAction("Delete King Level", parent, parent.manager.getEntries());
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
