package GUI.FileInfo.MenuDB.Explanation;

import java.awt.GridBagConstraints;

import javax.swing.JTextField;

import GUI.LabeledInputBox;
import GUI.FileInfo.GenericFileInfoGUI;
import SystemDataManagers.MenuDB.Explanation.Explanation;
import bFM.Settings;

@SuppressWarnings("serial")
public class ExplanationInfoGUI extends GenericFileInfoGUI
{
	Explanation file;
	JTextField Name = null;
	JTextField Text = null;
	JTextField Image1 = null;
	JTextField Image2 = null;
	JTextField Num1 = null;
	JTextField Num2 = null;
	JTextField Num3 = null;
	JTextField Num4 = null;
	JTextField Num5 = null;
	public ExplanationInfoGUI(Explanation file) 
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
		add(new LabeledInputBox("Image 1: ", Image1), layout);
		add(new LabeledInputBox("Image 2: ", Image2), layout);
		add(new LabeledInputBox("Num 1: ", Num1), layout);
		add(new LabeledInputBox("Num 2: ", Num2), layout);
		add(new LabeledInputBox("Num 3: ", Num3), layout);
		add(new LabeledInputBox("Num 4: ", Num4), layout);
		layout.weighty = 1.0;
		add(new LabeledInputBox("Num 5: ", Num5), layout);
	}
	private void makeGUI()
	{
		Name = bFM.GUIUtils.createNameTextField(file.getName(), file::setName);
		Text = bFM.GUIUtils.createStringTextField(file.getText(), file::setText);
		Image1 = bFM.GUIUtils.createStringTextField(file.getImage1(), file::setImage1);
		Image2 = bFM.GUIUtils.createStringTextField(file.getImage2(), file::setImage2);
		Num1 = bFM.GUIUtils.createIntTextField(file.getNum1(), file::setNum1);
		Num2 = bFM.GUIUtils.createIntTextField(file.getNum2(), file::setNum2);
		Num3 = bFM.GUIUtils.createIntTextField(file.getNum3(), file::setNum3);
		Num4 = bFM.GUIUtils.createIntTextField(file.getNum4(), file::setNum4);
		Num5 = bFM.GUIUtils.createIntTextField(file.getNum5(), file::setNum5);
	}
}
