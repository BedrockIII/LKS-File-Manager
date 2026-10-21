package GUI.FileInfo.MenuDB.MonsterBook;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import javax.swing.JLabel;

import GUI.LabeledInputBox;
import GUI.FileInfo.GenericFileInfoGUI;
import SystemDataManagers.MenuDB.Books.MonsterBook.MonsterBookManager;
import bFM.Settings;

@SuppressWarnings("serial")
public class MonsterBookManagerInfoGUI extends GenericFileInfoGUI
{
	MonsterBookManager manager;
	JLabel PageEntryCount;
	JLabel LogEntryCount;
	JLabel ModEntryCount;
	public MonsterBookManagerInfoGUI(MonsterBookManager manager)
	{
		this.manager = manager;
		makeGUI();
		addGUI();
	}
	private void makeGUI()
	{
		PageEntryCount = new JLabel("" + manager.getBookEntries().size());
		LogEntryCount = new JLabel("" + manager.getLogEntries().size());
		ModEntryCount = new JLabel("" + manager.getHpEntries().size());
	}
	private void addGUI()
	{
		removeAll();
		setLayout(new GridBagLayout());
		GridBagConstraints layout = Settings.getDefaultConstraints();
		add(new LabeledInputBox("Page Entry Count: ", PageEntryCount), layout);
		add(new LabeledInputBox("Log Entry Count: ", LogEntryCount), layout);
		layout.weighty = 1.0;
		add(new LabeledInputBox("HP Entry Count: ", ModEntryCount), layout);
	}
	public void update() 
	{
		PageEntryCount.setText("" + manager.getBookEntries().size());
		LogEntryCount.setText("" + manager.getLogEntries().size());
		ModEntryCount.setText("" + manager.getHpEntries().size());
		super.update();
	}
}
