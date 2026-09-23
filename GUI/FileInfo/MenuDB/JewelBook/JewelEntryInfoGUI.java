package GUI.FileInfo.MenuDB.JewelBook;

import java.awt.GridBagConstraints;

import javax.swing.JTextField;

import GUI.LabeledInputBox;
import GUI.FileInfo.GenericFileInfoGUI;
import SystemDataManagers.MenuDB.JewelBookManager.JewelEntry;
import bFM.Settings;

@SuppressWarnings("serial")
public class JewelEntryInfoGUI extends GenericFileInfoGUI
{
	JewelEntry file;
	JTextField Name = null;
	JTextField Description = null;
	JTextField Image = null;
	JTextField DebugDescription = null;
	public JewelEntryInfoGUI(JewelEntry file) 
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
		add(new LabeledInputBox("Gourmet Image: ", DebugDescription), layout);
	}
	private void makeGUI()
	{
		Name = bFM.GUIUtils.createNameTextField(file.getName(), file::setName);
		Description = bFM.GUIUtils.createStringTextField(file.getText(), file::setText);
		Image = bFM.GUIUtils.createStringTextField(file.getImage(), file::setImage);
		DebugDescription = bFM.GUIUtils.createStringTextField(file.getDebugText(), file::setDebugText);
	}
}
