package GUI.FileList.SystemData.ChairMessage;


import GUI.FileInfo.MenuDB.ChairMessage.ThroneMessageInfoGUI;
import GUI.FileList.FileList;
import SystemDataManagers.MenuDB.ChairMessage.ChairMessage;
import bFM.Settings;

@SuppressWarnings("serial")
public class ThroneMessageListGUI extends FileList
{
	ThroneSpeakerFileListGUI parent;
	ChairMessage Message;
	public ThroneMessageListGUI(ChairMessage Message, ThroneSpeakerFileListGUI parent)
	{
		this.parent = parent;
		this.padding = parent.getPadding() + Settings.indentSize;
		this.Message = Message;
		file = Message;
		initializeAll();
	}
	protected void initializeAll()
	{
		initializeListGUI("\"" + Message.getText() + "\"");
		addActions();
	}
	protected void initializeInfoGUI()
	{
		infoGUI = new ThroneMessageInfoGUI(Message);
	}
	protected void addActions()
	{
		addDeleteAction("Delete Throne Message", parent, parent.Messages);
		addMoveUpAction(parent, parent.Messages);
		addMoveDownAction(parent, parent.Messages);
		add(actions);
		addMouseListener();
	}

}
