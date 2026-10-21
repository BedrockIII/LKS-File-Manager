package GUI.FileInfo.MenuDB.Development;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import javax.swing.JTextField;

import GUI.LabeledInputBox;
import GUI.FileInfo.GenericFileInfoGUI;
import SystemDataManagers.MenuDB.Unused.Development.Development;
import bFM.GUIUtils;
import bFM.Settings;

@SuppressWarnings("serial")
public class DevelopmentInfoGUI extends GenericFileInfoGUI
{
	Development Development;
	JTextField Icon;
	JTextField Image;
	JTextField BuildCode;
	JTextField ID;
	JTextField XPos;
	JTextField YPos;
	public DevelopmentInfoGUI(Development Development) 
	{
		this.Development = Development;
		makeGUI();
		addGUI();
	}
	private void makeGUI()
	{
		Icon = GUIUtils.createNameTextField(Development.getIcon(), Development::setIcon);
		Image = GUIUtils.createStringTextField(Development.getImage(), Development::setImage);
		BuildCode = GUIUtils.createIntTextField(Development.getBuildingCode(), Development::setBuildingCode);
		ID = GUIUtils.createIntTextField(Development.getId(), Development::setId);
		XPos = GUIUtils.createIntTextField(Development.getxPos(), Development::setxPos);
		YPos = GUIUtils.createIntTextField(Development.getyPos(), Development::setyPos);
	}
	private void addGUI()
	{
		removeAll();
		setLayout(new GridBagLayout());
		GridBagConstraints layout = Settings.getDefaultConstraints();
		add(new LabeledInputBox("Icon: ", Icon), layout);
		add(new LabeledInputBox("Image: ", Image), layout);
		add(new LabeledInputBox("Building Code: ", BuildCode), layout);
		add(new LabeledInputBox("ID: ", ID), layout);
		add(new LabeledInputBox("X Position: ", XPos), layout);
		layout.weighty = 1.0;
		add(new LabeledInputBox("Y Position: ", YPos), layout);
	}
}
