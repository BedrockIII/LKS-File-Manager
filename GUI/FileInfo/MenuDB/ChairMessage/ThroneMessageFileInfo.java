package GUI.FileInfo.MenuDB.ChairMessage;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import javax.swing.JLabel;

import GUI.LabeledInputBox;
import GUI.FileInfo.GenericFileInfoGUI;
import SystemDataManagers.MenuDB.ChairMessage.ChairMessageManager;
import bFM.Settings;

@SuppressWarnings("serial")
public class ThroneMessageFileInfo extends GenericFileInfoGUI
{
	ChairMessageManager manager;
	JLabel MessageCount;
	JLabel VerdeMessageCount;
	JLabel LiamMessageCount;
	JLabel HowserMessageCount;
	public ThroneMessageFileInfo(ChairMessageManager manager) 
	{
		this.manager = manager;
		makeGUI();
		addGUI();
	}
	private void makeGUI()
	{
		MessageCount = new JLabel("" + (manager.getVerdeEntries().size() + manager.getLiamEntries().size() + manager.getHowserEntries().size()));
		VerdeMessageCount = new JLabel("" + manager.getVerdeEntries().size());
		LiamMessageCount = new JLabel("" + manager.getLiamEntries().size());
		HowserMessageCount = new JLabel("" + manager.getHowserEntries().size());
	}
	private void addGUI()
	{
		removeAll();
		setLayout(new GridBagLayout());
		GridBagConstraints layout = Settings.getDefaultConstraints();
		add(new LabeledInputBox("Total Messages Count: ", MessageCount), layout);
		add(new LabeledInputBox("Howser Message Count: ", HowserMessageCount), layout);
		add(new LabeledInputBox("Verde Message Count: ", VerdeMessageCount), layout);
		layout.weighty = 1.0;
		add(new LabeledInputBox("Liam Message Count: ", LiamMessageCount), layout);
	}
	public void update() 
	{
		MessageCount.setText("" + (manager.getVerdeEntries().size() + manager.getLiamEntries().size() + manager.getHowserEntries().size()));
		VerdeMessageCount.setText("" + manager.getVerdeEntries().size());
		LiamMessageCount.setText("" + manager.getLiamEntries().size());
		HowserMessageCount.setText("" + manager.getHowserEntries().size());
		super.update();
	}
}
