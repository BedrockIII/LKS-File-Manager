package GUI.FileInfo.MenuDB.NPCMemo;

import java.awt.GridBagConstraints;

import javax.swing.JTextField;

import GUI.LabeledInputBox;
import GUI.FileInfo.GenericFileInfoGUI;
import SystemDataManagers.MenuDB.Unused.NPCMemo.ActionMemo;
import bFM.Settings;

@SuppressWarnings("serial")
public class ActionInfoGUI extends GenericFileInfoGUI
{
	ActionMemo file;
	JTextField Name = null;
	public ActionInfoGUI(ActionMemo action) 
	{
		this.file = action;
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
