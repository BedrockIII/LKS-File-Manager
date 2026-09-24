package GUI.FileInfo.MenuDB.JewelBook;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JLabel;

import GUI.LabeledInputBox;
import GUI.FileInfo.GenericFileInfoGUI;
import SystemDataManagers.MenuDB.JewelBookManager;
import bFM.Settings;

@SuppressWarnings("serial")
public class JewelBookListInfoGUI extends GenericFileInfoGUI
{
	JewelBookManager manager;
	JLabel entryCount;
	public JewelBookListInfoGUI(JewelBookManager manager)
	{
		this.manager = manager;
		makeGUI();
		addGUI();
	}
	private void makeGUI()
	{
		entryCount = new JLabel("" + manager.getEntries().size());
	}
	private void addGUI()
	{
		removeAll();
		setLayout(new GridBagLayout());
		GridBagConstraints layout = Settings.getDefaultConstraints();
		layout.weighty = 1.0;
		add(new LabeledInputBox("Jewel Entry Count: ", entryCount), layout);
	}
	public void update() 
	{
		entryCount.setText("" + manager.getEntries().size());
		super.update();
	}
}
