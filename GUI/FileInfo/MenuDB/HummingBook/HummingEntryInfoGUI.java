package GUI.FileInfo.MenuDB.HummingBook;

import java.awt.GridBagConstraints;

import javax.swing.JTextField;

import GUI.LabeledInputBox;
import GUI.FileInfo.GenericFileInfoGUI;
import SystemDataManagers.MenuDB.Books.HummingBookEntry;
import bFM.Settings;

@SuppressWarnings("serial")
public class HummingEntryInfoGUI extends GenericFileInfoGUI
{
	HummingBookEntry file;
	JTextField Name = null;
	JTextField Description = null;
	JTextField Image = null;
	JTextField Flag = null;
	public HummingEntryInfoGUI(HummingBookEntry file) 
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
		add(new LabeledInputBox("Humming Book Activation Flag: ", Flag), layout);
	}
	private void makeGUI()
	{
		Name = bFM.GUIUtils.createNameTextField(file.getName(), file::setName);
		Description = bFM.GUIUtils.createStringTextField(file.getText(), file::setText);
		Image = bFM.GUIUtils.createStringTextField(file.getImage(), file::setImage);
		Flag = bFM.GUIUtils.createIntTextField(file.getHummingBookFlag(), file::setHummingBookFlag);
	}
}
