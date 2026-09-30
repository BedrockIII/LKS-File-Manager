package GUI.FileList.SystemData.ChairMessage;

import GUI.FileList.FileList;
import SystemDataManagers.MenuDB.ChairMessage.ChairMessage;

@SuppressWarnings("serial")
public class ThroneMessageListGUI extends FileList
{
	ThroneSpeakerFileListGUI parent;
	ChairMessage Message;
	public ThroneMessageListGUI(ChairMessage Message, int padding, ThroneSpeakerFileListGUI parent)
	{
		this.parent = parent;
		this.padding = padding;
		this.Message = Message;
		initializeAll();
	}
	protected void initializeAll()
	{
		initializeListGUI("\"" + Message.getText() + "\"");
		addActions();
	}
	protected void initializeInfoGUI()
	{
		// TODO Auto-generated method stub

	}
	protected void addActions()
	{
		// TODO Add Delete Action
		add(actions);
		addMouseListener();
	}

}
