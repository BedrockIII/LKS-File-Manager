package GUI.FileList.SystemData.ChairMessage;

import java.util.ArrayList;

import javax.swing.JMenuItem;
import GUI.FileList.CollapseableFileList;
import SystemDataManagers.MenuDB.ChairMessage.ChairMessage;
import bFM.Settings;

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
			subEntries.add(new ThroneMessageListGUI(object, padding + Settings.indentSize, this));
		}
	}
	protected void initializeInfoGUI()
	{
		//TODO
		//infoGUI = new HummingBookListInfoGUI(manager);
	}
	protected void addActions() 
	{
		addExportAction();
		addMessageAction();
		add(actions);
		addMouseListener();
	}
	private void addMessageAction()
	{
		JMenuItem newZone = new JMenuItem("Create New Message");
		newZone.addActionListener(e -> 
		{
			ChairMessage entry = new ChairMessage("New Throne Dialog", -1);
			Messages.add(entry);
			subEntries.add(new ThroneMessageListGUI(entry, padding + Settings.indentSize, this));
			reAddComponents();
		});
		actions.add(newZone);
	}
}
