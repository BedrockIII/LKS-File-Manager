package GUI.FileList.SystemData.Unused.NPCMemo;

import GUI.FileInfo.MenuDB.NPCMemo.ActionInfoGUI;
import GUI.FileList.FileList;
import SystemDataManagers.MenuDB.Unused.NPCMemo.ActionMemo;

@SuppressWarnings("serial")
public class ActionListGUI extends FileList
{
	ActionManagerListGUI parent;
	ActionMemo Action;
	public ActionListGUI(ActionMemo Action, int padding, ActionManagerListGUI parent)
	{
		this.parent = parent;
		this.padding = padding;
		this.Action = Action;
		file = Action;
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
		// TODO Add Delete Action
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
