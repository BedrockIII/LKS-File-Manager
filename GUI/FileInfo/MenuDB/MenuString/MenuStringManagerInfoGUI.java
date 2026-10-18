package GUI.FileInfo.MenuDB.MenuString;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import javax.swing.JLabel;

import GUI.LabeledInputBox;
import GUI.FileInfo.GenericFileInfoGUI;
import SystemDataManagers.MenuDB.MenuString.MenuStringManager;
import bFM.Settings;

@SuppressWarnings("serial")
public class MenuStringManagerInfoGUI extends GenericFileInfoGUI
{
	MenuStringManager manager;
	JLabel EntryCount;
	public MenuStringManagerInfoGUI(MenuStringManager manager)
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
