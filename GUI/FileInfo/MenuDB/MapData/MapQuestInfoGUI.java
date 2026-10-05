package GUI.FileInfo.MenuDB.MapData;

import java.awt.GridBagConstraints;

import javax.swing.JTextField;

import GUI.LabeledInputBox;
import GUI.FileInfo.GenericFileInfoGUI;
import SystemDataManagers.MenuDB.MapData.MapQuestInfo;
import bFM.Settings;

@SuppressWarnings("serial")
public class MapQuestInfoGUI extends GenericFileInfoGUI
{
	MapQuestInfo file;
	JTextField Name = null;
	JTextField Flag1 = null;
	JTextField Flag2 = null;
	JTextField Flag3 = null;
	JTextField Flag4 = null;
	JTextField Flag5 = null;
	JTextField Flag6 = null;
	JTextField Flag7 = null;
	JTextField Flag8 = null;
	JTextField Flag9 = null;
	JTextField Flag10 = null;
	public MapQuestInfoGUI(MapQuestInfo file) 
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
		add(new LabeledInputBox("Flag 1: ", Flag1), layout);
		add(new LabeledInputBox("Flag 2: ", Flag2), layout);
		add(new LabeledInputBox("Flag 3: ", Flag3), layout);
		add(new LabeledInputBox("Flag 4: ", Flag4), layout);
		add(new LabeledInputBox("Flag 5: ", Flag5), layout);
		add(new LabeledInputBox("Flag 6: ", Flag6), layout);
		add(new LabeledInputBox("Flag 7: ", Flag7), layout);
		add(new LabeledInputBox("Flag 8: ", Flag8), layout);
		add(new LabeledInputBox("Flag 9: ", Flag9), layout);
		layout.weighty = 1.0;
		add(new LabeledInputBox("Flag 10: ", Flag10), layout);
	}
	private void makeGUI()
	{
		Name = bFM.GUIUtils.createNameTextField(file.getName(), file::setName);
		Flag1 = bFM.GUIUtils.createIntTextField(file.getFlag1(), file::setFlag1);
		Flag2 = bFM.GUIUtils.createIntTextField(file.getFlag2(), file::setFlag2);
		Flag3 = bFM.GUIUtils.createIntTextField(file.getFlag3(), file::setFlag3);
		Flag4 = bFM.GUIUtils.createIntTextField(file.getFlag4(), file::setFlag4);
		Flag5 = bFM.GUIUtils.createIntTextField(file.getFlag5(), file::setFlag5);
		Flag6 = bFM.GUIUtils.createIntTextField(file.getFlag6(), file::setFlag6);
		Flag7 = bFM.GUIUtils.createIntTextField(file.getFlag7(), file::setFlag7);
		Flag8 = bFM.GUIUtils.createIntTextField(file.getFlag8(), file::setFlag8);
		Flag9 = bFM.GUIUtils.createIntTextField(file.getFlag9(), file::setFlag9);
		Flag10 = bFM.GUIUtils.createIntTextField(file.getFlag10(), file::setFlag10);
	}
}
