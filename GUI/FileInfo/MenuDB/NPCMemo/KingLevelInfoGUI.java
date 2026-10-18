package GUI.FileInfo.MenuDB.NPCMemo;

import java.awt.GridBagConstraints;

import javax.swing.JTextField;

import GUI.LabeledInputBox;
import GUI.FileInfo.GenericFileInfoGUI;
import SystemDataManagers.MenuDB.Unused.NPCMemo.KingLevel;
import bFM.Settings;

@SuppressWarnings("serial")
public class KingLevelInfoGUI extends GenericFileInfoGUI
{
	KingLevel file;
	JTextField Name = null;
	JTextField Text = null;
	public KingLevelInfoGUI(KingLevel level) 
	{
		this.file = level;
		makeGUI();
		addGUI();
	}
	private void addGUI() 
	{
		GridBagConstraints layout = Settings.getDefaultConstraints();
		removeAll();
		add(new LabeledInputBox("Name: ", Name), layout);
		layout.weighty = 1.0;
		add(new LabeledInputBox("Description: ", Text), layout);
	}
	private void makeGUI()
	{
		Name = bFM.GUIUtils.createNameTextField(file.getName(), file::setName);
		Text = bFM.GUIUtils.createStringTextField(file.getText(), file::setText);
	}
}
