package GUI.FileInfo.MenuDB.PublicArt;

import java.awt.GridBagConstraints;

import javax.swing.JTextField;

import GUI.LabeledInputBox;
import GUI.FileInfo.GenericFileInfoGUI;
import SystemDataManagers.MenuDB.PublicArt.PublicArt;
import bFM.Settings;

@SuppressWarnings("serial")
public class PublicArtInfoGUI extends GenericFileInfoGUI
{
	PublicArt file;
	JTextField Name = null;
	JTextField Text = null;
	JTextField Description = null;
	JTextField Image = null;
	JTextField Flag = null;
	public PublicArtInfoGUI(PublicArt file) 
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
		add(new LabeledInputBox("Text: ", Text), layout);
		add(new LabeledInputBox("Description: ", Description), layout);
		add(new LabeledInputBox("Image: ", Image), layout);
		layout.weighty = 1.0;
		add(new LabeledInputBox("Activation Flag: ", Flag), layout);
	}
	private void makeGUI()
	{
		Name = bFM.GUIUtils.createNameTextField(file.getName(), file::setName);
		Text = bFM.GUIUtils.createStringTextField(file.getText(), file::setText);
		Description = bFM.GUIUtils.createStringTextField(file.getDescription(), file::setDescription);
		Image = bFM.GUIUtils.createStringTextField(file.getImage(), file::setImage);
		Flag = bFM.GUIUtils.createIntTextField(file.getActivationFlag(), file::setActivationFlag);
	}
}
