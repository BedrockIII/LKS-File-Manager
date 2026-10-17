package GUI.FileInfo.MenuDB.Name;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import javax.swing.JLabel;

import GUI.LabeledInputBox;
import GUI.FileInfo.GenericFileInfoGUI;
import SystemDataManagers.MenuDB.Name.NameManager;
import bFM.Settings;

@SuppressWarnings("serial")
public class NameMenuInfoGUI extends GenericFileInfoGUI
{
	NameManager manager;
	JLabel FirstNameCount;
	JLabel LastNameCount;
	JLabel NGNameCount;
	public NameMenuInfoGUI(NameManager manager)
	{
		this.manager = manager;
		makeGUI();
		addGUI();
	}
	private void makeGUI()
	{
		FirstNameCount = new JLabel("" + manager.getFirstNames().getNames().size());
		LastNameCount = new JLabel("" + manager.getLastNames().getNames().size());
		NGNameCount = new JLabel("" + manager.getNGName().getNames().size());
	}
	private void addGUI()
	{
		removeAll();
		setLayout(new GridBagLayout());
		GridBagConstraints layout = Settings.getDefaultConstraints();
		add(new LabeledInputBox("First Name Count: ", FirstNameCount), layout);
		add(new LabeledInputBox("Last Name Count: ", LastNameCount), layout);
		layout.weighty = 1.0;
		add(new LabeledInputBox("Unused NG Name Count: ", NGNameCount), layout);
	}
	public void update() 
	{
		FirstNameCount.setText("" + manager.getFirstNames().getNames().size());
		LastNameCount.setText("" + manager.getLastNames().getNames().size());
		NGNameCount.setText("" + manager.getNGName().getNames().size());
		super.update();
	}
}
