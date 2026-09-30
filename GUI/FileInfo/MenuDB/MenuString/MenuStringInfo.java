package GUI.FileInfo.MenuDB.MenuString;

import java.awt.GridBagConstraints;

import javax.swing.JTextField;

import GUI.LabeledInputBox;
import GUI.FileInfo.GenericFileInfoGUI;
import SystemDataManagers.MenuDB.MenuStringManager.MenuString;
import bFM.Settings;

@SuppressWarnings("serial")
public class MenuStringInfo extends GenericFileInfoGUI
{
	MenuString file;
	JTextField Name = null;
	public MenuStringInfo(MenuString file) 
	{
		this.file = file;
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
		Name = bFM.GUIUtils.createNameTextField(file.getText(), file::setText);
	}
}
