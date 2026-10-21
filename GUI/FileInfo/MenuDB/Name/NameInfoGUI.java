package GUI.FileInfo.MenuDB.Name;

import java.awt.GridBagConstraints;

import javax.swing.JTextField;

import GUI.LabeledInputBox;
import GUI.FileInfo.GenericFileInfoGUI;
import SystemDataManagers.MenuDB.Name.GeneratedName;
import bFM.Settings;

@SuppressWarnings("serial")
public class NameInfoGUI extends GenericFileInfoGUI
{
	GeneratedName file;
	JTextField Name = null;
	public NameInfoGUI(GeneratedName name) 
	{
		this.file = name;
		makeGUI();
		addGUI();
	}
	private void addGUI() 
	{
		GridBagConstraints layout = Settings.getDefaultConstraints();
		removeAll();
		layout.weighty = 1.0;
		add(new LabeledInputBox("Name: ", Name), layout);
	}
	private void makeGUI()
	{
		Name = bFM.GUIUtils.createNameTextField(file.getName(), file::setName);
	}
}
