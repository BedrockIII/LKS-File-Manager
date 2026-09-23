package GUI.FileInfo.MenuDB.AnimalBook;

import java.awt.GridBagConstraints;

import javax.swing.JTextField;

import GUI.LabeledInputBox;
import GUI.FileInfo.GenericFileInfoGUI;
import SystemDataManagers.MenuDB.AnimalManager.AnimalEntry;
import bFM.Settings;

@SuppressWarnings("serial")
public class AnimalEntryInfoGUI extends GenericFileInfoGUI
{
	AnimalEntry file;
	JTextField Name = null;
	JTextField Description = null;
	JTextField Image = null;
	JTextField DebugDescription = null;
	public AnimalEntryInfoGUI(AnimalEntry file) 
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
		add(new LabeledInputBox("Description: ", Description), layout);
		add(new LabeledInputBox("Image: ", Image), layout);
		layout.weighty = 1.0;
		add(new LabeledInputBox("Debug Detail: ", DebugDescription), layout);
	}
	private void makeGUI()
	{
		Name = bFM.GUIUtils.createNameTextField(file.getName(), file::setName);
		Description = bFM.GUIUtils.createStringTextField(file.getText(), file::setText);
		Image = bFM.GUIUtils.createStringTextField(file.getImage(), file::setImage);
		DebugDescription = bFM.GUIUtils.createStringTextField(file.getDebugText(), file::setDebugText);
	}
}
