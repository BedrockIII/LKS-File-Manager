package GUI.FileInfo.MenuDB.MapData;

import java.awt.GridBagConstraints;

import javax.swing.JTextField;

import GUI.BitFlagPanel;
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
	BitFlagPanel Flag2 = null;
	BitFlagPanel Flag3 = null;
	BitFlagPanel Flag4 = null;
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
		add(Flag2, layout);
		add(Flag3, layout);
		add(Flag4, layout);
		layout.weighty = 1.0;
		add(new LabeledInputBox("World Control Percent: ", Flag5), layout);
	}
	private void makeGUI()
	{
		Name = bFM.GUIUtils.createNameTextField(file.getName(), file::setName);
		Image = bFM.GUIUtils.createStringTextField(file.getImage(), file::setImage);
		Flag1 = bFM.GUIUtils.createIntTextField(file.getFlag1(), file::setFlag1);
		Flag2 = new BitFlagPanel("Activation Flag 1: ", file.getFlag2(), file::setFlag2);
		Flag3 = new BitFlagPanel("Activation Flag 2: ", file.getFlag3(), file::setFlag3);
		Flag4 = new BitFlagPanel("Activation Flag 3: ", file.getFlag4(), file::setFlag4);
		Flag5 = bFM.GUIUtils.createIntTextField(file.getFlag5(), file::setFlag5);
	}
}
