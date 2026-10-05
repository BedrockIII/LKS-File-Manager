package GUI.FileInfo.MenuDB.MapData;

import java.awt.GridBagConstraints;

import javax.swing.JTextField;

import GUI.LabeledInputBox;
import GUI.FileInfo.GenericFileInfoGUI;
import SystemDataManagers.MenuDB.MapData.MapArea;
import bFM.Settings;

@SuppressWarnings("serial")
public class MapAreaInfoGUI extends GenericFileInfoGUI
{
	MapArea file;
	JTextField Name = null;
	JTextField Image = null;
	JTextField Flag1 = null;
	JTextField Flag2 = null;
	JTextField Flag3 = null;
	JTextField Flag4 = null;
	JTextField Flag5 = null;
	public MapAreaInfoGUI(MapArea file) 
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
		add(new LabeledInputBox("Image: ", Image), layout);
		add(new LabeledInputBox("Map Level: ", Flag1), layout);
		add(new LabeledInputBox("Activation Flag 1: ", Flag2), layout);
		add(new LabeledInputBox("Activation Flag 2: ", Flag3), layout);
		add(new LabeledInputBox("Activation Flag 3: ", Flag4), layout);
		layout.weighty = 1.0;
		add(new LabeledInputBox("Unknown Area Data Number: ", Flag5), layout);
	}
	private void makeGUI()
	{
		Name = bFM.GUIUtils.createNameTextField(file.getName(), file::setName);
		Image = bFM.GUIUtils.createStringTextField(file.getImage(), file::setImage);
		Flag1 = bFM.GUIUtils.createIntTextField(file.getFlag1(), file::setFlag1);
		Flag2 = bFM.GUIUtils.createIntTextField(file.getFlag2(), file::setFlag2);
		Flag3 = bFM.GUIUtils.createIntTextField(file.getFlag3(), file::setFlag3);
		Flag4 = bFM.GUIUtils.createIntTextField(file.getFlag4(), file::setFlag4);
		Flag5 = bFM.GUIUtils.createIntTextField(file.getFlag5(), file::setFlag5);
	}
}
