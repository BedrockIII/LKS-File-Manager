package GUI.FileInfo.MenuDB.MapData;

import java.awt.GridBagConstraints;

import javax.swing.JTextField;

import GUI.BitFlagPanel;
import GUI.LabeledInputBox;
import GUI.FileInfo.GenericFileInfoGUI;
import SystemDataManagers.MenuDB.MapData.MapPOIData;
import bFM.Settings;

@SuppressWarnings("serial")
public class MapPOIInfoGUI extends GenericFileInfoGUI
{
	MapPOIData file;
	JTextField Name = null;
	JTextField Message = null;
	JTextField Image = null;
	JTextField PosX = null;
	JTextField PosY = null;
	JTextField Flag1 = null;
	BitFlagPanel Flag2 = null;
	BitFlagPanel Flag3 = null;
	BitFlagPanel Flag4 = null;
	BitFlagPanel Flag5 = null;
	JTextField Flag6 = null;
	public MapPOIInfoGUI(MapPOIData file) 
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
		add(new LabeledInputBox("Message: ", Message), layout);
		add(new LabeledInputBox("Image: ", Image), layout);
		add(new LabeledInputBox("X Position: ", PosX), layout);
		add(new LabeledInputBox("Y Position: ", PosY), layout);
		add(new LabeledInputBox("Unknown Map POI Number 1: ", Flag1), layout);
		add(Flag2, layout);
		add(Flag3, layout);
		add(Flag4, layout);
		add(Flag5, layout);
		layout.weighty = 1.0;
		add(new LabeledInputBox("Area Code: ", Flag6), layout);
	}
	private void makeGUI()
	{
		Name = bFM.GUIUtils.createNameTextField(file.getName(), file::setName);
		Message = bFM.GUIUtils.createStringTextField(file.getMessage(), file::setMessage);
		Image = bFM.GUIUtils.createStringTextField(file.getImage(), file::setImage);
		PosX = bFM.GUIUtils.createIntTextField(file.getPosX(), file::setPosX);
		PosY = bFM.GUIUtils.createIntTextField(file.getPosY(), file::setPosY);
		Flag1 = bFM.GUIUtils.createIntTextField(file.getFlag1(), file::setFlag1);
		Flag2 = new BitFlagPanel("Activation Flag 1: ", file.getFlag2(), file::setFlag2);
		Flag3 = new BitFlagPanel("Activation Flag 2: ", file.getFlag3(), file::setFlag3);
		Flag4 = new BitFlagPanel("Deactivation Flag 1: ", file.getFlag4(), file::setFlag4);
		Flag5 = new BitFlagPanel("Deactivation Flag 2: ", file.getFlag5(), file::setFlag5);
		Flag6 = bFM.GUIUtils.createIntTextField(file.getFlag6(), file::setFlag6);
	}
}
