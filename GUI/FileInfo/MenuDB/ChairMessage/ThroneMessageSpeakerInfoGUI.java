package GUI.FileInfo.MenuDB.ChairMessage;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.ArrayList;

import javax.swing.JLabel;

import GUI.LabeledInputBox;
import GUI.FileInfo.GenericFileInfoGUI;
import SystemDataManagers.MenuDB.ChairMessage.ChairMessage;
import bFM.Settings;

@SuppressWarnings("serial")
public class ThroneMessageSpeakerInfoGUI extends GenericFileInfoGUI
{
	ArrayList<ChairMessage> Entries;
	String name;
	JLabel MessageCount;
	public ThroneMessageSpeakerInfoGUI(ArrayList<ChairMessage> messages, String speakerName) 
	{
		this.Entries = messages;
		name = speakerName;
		makeGUI();
		addGUI();
	}
	private void makeGUI()
	{
		MessageCount = new JLabel("" + Entries.size());
	}
	private void addGUI()
	{
		removeAll();
		setLayout(new GridBagLayout());
		GridBagConstraints layout = Settings.getDefaultConstraints();
		layout.weighty = 1.0;
		add(new LabeledInputBox("Message Count: ", MessageCount), layout);
	}
	public void update() 
	{
		MessageCount.setText("" + Entries.size());
		super.update();
	}
}
