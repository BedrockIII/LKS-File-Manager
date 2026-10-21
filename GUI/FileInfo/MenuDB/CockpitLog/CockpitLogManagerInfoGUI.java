package GUI.FileInfo.MenuDB.CockpitLog;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import javax.swing.JLabel;

import GUI.LabeledInputBox;
import GUI.FileInfo.GenericFileInfoGUI;
import SystemDataManagers.MenuDB.CockpitLog.CockpitLogManager;
import bFM.Settings;

@SuppressWarnings("serial")
public class CockpitLogManagerInfoGUI extends GenericFileInfoGUI
{
	CockpitLogManager manager;
	JLabel EntryCount;
	public CockpitLogManagerInfoGUI(CockpitLogManager manager)
	{
		this.manager = manager;
		makeGUI();
		addGUI();
	}
	private void makeGUI()
	{
		EntryCount = new JLabel("" + manager.getEntries().size());
	}
	private void addGUI()
	{
		removeAll();
		setLayout(new GridBagLayout());
		GridBagConstraints layout = Settings.getDefaultConstraints();
		layout.weighty = 1.0;
		add(new LabeledInputBox("Entry Count: ", EntryCount), layout);
	}
	public void update() 
	{
		EntryCount.setText("" + manager.getEntries().size());
		super.update();
	}
}
