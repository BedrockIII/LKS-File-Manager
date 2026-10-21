package GUI.FileList.SystemData.ChairMessage;

import java.util.ArrayList;

import GUI.FileInfo.MenuDB.ChairMessage.ThroneMessageSpeakerInfoGUI;
import GUI.FileList.CollapseableFileList;
import SystemDataManagers.MenuDB.ChairMessage.ChairMessage;

@SuppressWarnings("serial")
public class ThroneSpeakerFileListGUI extends CollapseableFileList
{
	String SpeakerName;
	ArrayList<ChairMessage> Messages;
	public ThroneSpeakerFileListGUI(ArrayList<ChairMessage> Messages, int padding, String SpeakerName)
	{
		this.Messages = Messages;
		this.padding = padding;
		this.SpeakerName = SpeakerName;
		initializeAll();
	}
	protected void initializeAll() 
	{
		initializeListGUI(SpeakerName + " Message Data");
		initializeSubGUI();
		addActions();
		reAddComponents();
	}
	public void initializeSubGUI()
	{
		subEntries.removeAll(subEntries);
		for(ChairMessage object : Messages)
		{
			subEntries.add(new ThroneMessageListGUI(object, this));
		}
	}
	protected void initializeInfoGUI()
	{
		infoGUI = new ThroneMessageSpeakerInfoGUI(Messages, SpeakerName);
	}
	protected void addActions() 
	{
		addChildAction("Add New Message", Messages, ChairMessage::new, ThroneMessageListGUI::new);
		add(actions);
		addMouseListener();
	}
}
