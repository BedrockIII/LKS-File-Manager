package GUI.FileInfo.MenuDB.MonsterBook;

import java.awt.GridBagConstraints;

import javax.swing.JTextField;

import GUI.LabeledInputBox;
import GUI.FileInfo.GenericFileInfoGUI;
import SystemDataManagers.MenuDB.Books.MonsterBook.MonsterBook;
import bFM.Settings;

@SuppressWarnings("serial")
public class MonsterBookEntryInfoGUI extends GenericFileInfoGUI
{
	MonsterBook file;
	JTextField Name = null;
	JTextField Description = null;
	JTextField Image = null;
	public MonsterBookEntryInfoGUI(MonsterBook entry) 
	{
		this.file = entry;
		makeGUI();
		addGUI();
	}
	private void addGUI() 
	{
		GridBagConstraints layout = Settings.getDefaultConstraints();
		removeAll();
		add(new LabeledInputBox("Name: ", Name), layout);
		add(new LabeledInputBox("Description: ", Description), layout);
		layout.weighty = 1.0;
		add(new LabeledInputBox("Image: ", Image), layout);
	}
	private void makeGUI()
	{
		Name = bFM.GUIUtils.createNameTextField(file.getName(), file::setName);
		Description = bFM.GUIUtils.createStringTextField(file.getDescription(), file::setDescription);
		Image = bFM.GUIUtils.createStringTextField(file.getImage(), file::setImage);
	}
}
