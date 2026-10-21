package GUI.FileInfo.MenuDB.MonsterBook;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.ArrayList;

import javax.swing.JLabel;

import GUI.LabeledInputBox;
import GUI.FileInfo.GenericFileInfoGUI;
import SystemDataManagers.MenuDB.Books.MonsterBook.MonsterBook;
import bFM.Settings;

@SuppressWarnings("serial")
public class MonsterBookEntryManagerInfoGUI extends GenericFileInfoGUI
{
	ArrayList<MonsterBook> manager;
	JLabel EntryCount;
	public MonsterBookEntryManagerInfoGUI(ArrayList<MonsterBook> manager2)
	{
		this.manager = manager2;
		makeGUI();
		addGUI();
	}
	private void makeGUI()
	{
		EntryCount = new JLabel("" + manager.size());
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
		EntryCount.setText("" + manager.size());
		super.update();
	}
}
