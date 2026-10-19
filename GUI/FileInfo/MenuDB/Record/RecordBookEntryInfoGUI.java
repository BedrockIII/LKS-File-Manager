package GUI.FileInfo.MenuDB.Record;

import java.awt.GridBagConstraints;

import javax.swing.JTextField;

import GUI.LabeledInputBox;
import GUI.FileInfo.GenericFileInfoGUI;
import SystemDataManagers.MenuDB.Books.RecordBook.RecordBookEntry;
import bFM.Settings;

@SuppressWarnings("serial")
public class RecordBookEntryInfoGUI extends GenericFileInfoGUI
{
	RecordBookEntry file;
	JTextField Name = null;
	JTextField Description0 = null;
	JTextField Description1 = null;
	JTextField Description2 = null;
	JTextField Description3 = null;
	JTextField Image = null;
	JTextField Description1Count = null;
	JTextField Description2Count = null;
	JTextField Description3Count = null;
	public RecordBookEntryInfoGUI(RecordBookEntry file) 
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
		add(new LabeledInputBox("Level 0 Text: ", Description0), layout);
		add(new LabeledInputBox("Level 1 Unlock Amount: ", Description1Count), layout);
		add(new LabeledInputBox("Level 1 Text: ", Description1), layout);
		add(new LabeledInputBox("Level 2 Unlock Amount: ", Description2Count), layout);
		add(new LabeledInputBox("Level 2 Text: ", Description2), layout);
		add(new LabeledInputBox("Level 3 Unlock Amount: ", Description3Count), layout);
		add(new LabeledInputBox("Level 3 Text: ", Description3), layout);
		layout.weighty = 1.0;
		add(new LabeledInputBox("Image: ", Image), layout);
	}
	private void makeGUI()
	{
		Name = bFM.GUIUtils.createNameTextField(file.getName(), file::setName);
		Description0 = bFM.GUIUtils.createStringTextField(file.getLevel0Text(), file::setLevel0Text);
		Description1 = bFM.GUIUtils.createStringTextField(file.getLevel1Text(), file::setLevel1Text);
		Description2 = bFM.GUIUtils.createStringTextField(file.getLevel2Text(), file::setLevel2Text);
		Description3 = bFM.GUIUtils.createStringTextField(file.getLevel3Text(), file::setLevel3Text);
		Image = bFM.GUIUtils.createStringTextField(file.getImage(), file::setImage);
		Description1Count = bFM.GUIUtils.createIntTextField(file.getLevel1Count(), file::setLevel1Count);
		Description2Count = bFM.GUIUtils.createIntTextField(file.getLevel2Count(), file::setLevel2Count);
		Description3Count = bFM.GUIUtils.createIntTextField(file.getLevel3Count(), file::setLevel3Count);
	}
}
