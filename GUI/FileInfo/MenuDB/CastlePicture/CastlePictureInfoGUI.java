package GUI.FileInfo.MenuDB.CastlePicture;

import java.awt.GridBagConstraints;

import javax.swing.JTextField;

import GUI.BitFlagPanel;
import GUI.LabeledInputBox;
import GUI.FileInfo.GenericFileInfoGUI;
import SystemDataManagers.MenuDB.CastlePicture.CastlePicture;
import bFM.Settings;

@SuppressWarnings("serial")
public class CastlePictureInfoGUI extends GenericFileInfoGUI
{
	CastlePicture file;
	JTextField Name = null;
	JTextField Num1 = null;
	JTextField Num2 = null;
	BitFlagPanel Num3 = null;
	JTextField Num4 = null;
	JTextField Num5 = null;
	JTextField Num6 = null;
	public CastlePictureInfoGUI(CastlePicture file) 
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
		add(new LabeledInputBox("Number: ", Num1), layout);
		add(new LabeledInputBox("Number: ", Num2), layout);
		add(Num3, layout);
		add(new LabeledInputBox("Picture Item Flag: ", Num4), layout);
		add(new LabeledInputBox("Number: ", Num5), layout);
		layout.weighty = 1.0;
		add(new LabeledInputBox("Number: ", Num6), layout);
	}
	private void makeGUI()
	{
		Name = bFM.GUIUtils.createNameTextField(file.getText(), file::setText);
		Num1 = bFM.GUIUtils.createIntTextField(file.getFlag1(), file::setFlag1);
		Num2 = bFM.GUIUtils.createIntTextField(file.getFlag2(), file::setFlag2);
		Num3 = new BitFlagPanel("Activation Flag: ", file.getFlag3(), file::setFlag3);
		Num4 = bFM.GUIUtils.createIntTextField(file.getFlag4(), file::setFlag4);
		Num5 = bFM.GUIUtils.createIntTextField(file.getFlag5(), file::setFlag5);
		Num6 = bFM.GUIUtils.createIntTextField(file.getFlag6(), file::setFlag6);
	}
}
