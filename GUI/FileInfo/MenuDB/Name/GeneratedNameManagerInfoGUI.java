package GUI.FileInfo.MenuDB.Name;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import javax.swing.JLabel;

import GUI.LabeledInputBox;
import GUI.FileInfo.GenericFileInfoGUI;
import SystemDataManagers.MenuDB.Name.NameList;
import bFM.Settings;

@SuppressWarnings("serial")
public class GeneratedNameManagerInfoGUI extends GenericFileInfoGUI
{
	NameList manager;
	JLabel EntryCount;
	public GeneratedNameManagerInfoGUI(NameList manager)
	{
		this.manager = manager;
		makeGUI();
		addGUI();
	}
	private void makeGUI()
	{
		EntryCount = new JLabel("" + manager.getNames().size());
	}
	private void addGUI()
	{
		removeAll();
		setLayout(new GridBagLayout());
		GridBagConstraints layout = Settings.getDefaultConstraints();
		layout.weighty = 1.0;
		add(new LabeledInputBox("Name Count: ", EntryCount), layout);
	}
	public void update() 
	{
		EntryCount.setText("" + manager.getNames().size());
		super.update();
	}
}
