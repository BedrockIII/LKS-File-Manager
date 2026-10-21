package GUI.FileInfo.MenuDB.MovieBook;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import javax.swing.JLabel;

import GUI.LabeledInputBox;
import GUI.FileInfo.GenericFileInfoGUI;
import SystemDataManagers.MenuDB.Books.MovieBook.MovieBookManager;
import bFM.Settings;

@SuppressWarnings("serial")
public class MovieBookManagerInfoGUI extends GenericFileInfoGUI
{
	MovieBookManager manager;
	JLabel EntryCount;
	public MovieBookManagerInfoGUI(MovieBookManager manager)
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
