package GUI.FileList.SystemData.Unused.NPCMemo;

import GUI.FileInfo.MenuDB.NPCMemo.ActionInfoGUI;
import GUI.FileList.FileList;
import SystemDataManagers.MenuDB.Unused.NPCMemo.ActionMemo;
import bFM.Settings;

@SuppressWarnings("serial")
public class ActionListGUI extends FileList
{
	ActionManagerListGUI parent;
	ActionMemo Action;
	public ActionListGUI(ActionMemo Action, ActionManagerListGUI parent)
	{
		padding = parent.getPadding() + Settings.indentSize;
		this.Action = Action;
		super.file = Action;
		this.parent = parent;
		initializeAll();
	}
	protected void initializeAll()
	{
		initializeListGUI("\"" + Action.getName() + "\"");
		addActions();
	}
	protected void initializeInfoGUI()
	{
		infoGUI = new ActionInfoGUI(Action);
	}
	protected void addActions()
	{
		addDeleteAction("Delete NPC Action", parent, parent.manager.getEntries());
		addMoveUpAction(parent, parent.manager.getEntries());
		addMoveDownAction(parent, parent.manager.getEntries());
		add(actions);
		addMouseListener();
	}
	public void update()
	{
		fileName.setText("\"" + Action.getName() + "\"");
		super.update();
	}
}
