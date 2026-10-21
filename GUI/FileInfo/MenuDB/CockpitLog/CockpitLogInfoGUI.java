package GUI.FileInfo.MenuDB.CockpitLog;

import java.awt.GridBagConstraints;

import javax.swing.JTextField;

import GUI.LabeledInputBox;
import GUI.FileInfo.GenericFileInfoGUI;
import SystemDataManagers.MenuDB.CockpitLog.LogEntry;
import bFM.Settings;

@SuppressWarnings("serial")
public class CockpitLogInfoGUI extends GenericFileInfoGUI
{
	LogEntry file;
	JTextField Name = null;
	JTextField Num = null;
	public CockpitLogInfoGUI(LogEntry file) 
	{
		this.file = file;
		makeGUI();
		addGUI();
	}
	private void addGUI() 
	{
		GridBagConstraints layout = Settings.getDefaultConstraints();
		removeAll();
		add(new LabeledInputBox("Name: ", Name), layout);
		layout.weighty = 1.0;
		add(new LabeledInputBox("Number: ", Num), layout);
	}
	private void makeGUI()
	{
		Name = bFM.GUIUtils.createNameTextField(file.getText(), file::setText);
		Num = bFM.GUIUtils.createIntTextField(file.getFlag(), file::setFlag);
	}
}
