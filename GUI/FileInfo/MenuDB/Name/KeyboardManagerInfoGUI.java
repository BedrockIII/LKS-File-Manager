package GUI.FileInfo.MenuDB.Name;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import javax.swing.JLabel;

import GUI.FileInfo.GenericFileInfoGUI;
import bFM.Settings;

@SuppressWarnings("serial")
public class KeyboardManagerInfoGUI extends GenericFileInfoGUI
{
	String name;
	JLabel EntryCount;
	public KeyboardManagerInfoGUI(String name)
	{
		this.name = name;
		addGUI();
	}
	private void addGUI()
	{
		removeAll();
		setLayout(new GridBagLayout());
		GridBagConstraints layout = Settings.getDefaultConstraints();
		layout.weighty = 1.0;
		add(new JLabel(name + " Language Keyboards"), layout);
	}
}
