package GUI.FileInfo.MenuDB.ChairCamera;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import javax.swing.JTextField;

import GUI.LabeledInputBox;
import GUI.FileInfo.GenericFileInfoGUI;
import SystemDataManagers.MenuDB.ChairCamera.ChairCamera;
import bFM.GUIUtils;
import bFM.Settings;

@SuppressWarnings("serial")
public class ChairCameraInfoGUI extends GenericFileInfoGUI
{
	ChairCamera cam;
	JTextField num1;
	JTextField num2;
	JTextField num3;
	JTextField num4;
	JTextField num5;
	JTextField num6;
	JTextField num7;
	JTextField num8;
	JTextField num9;
	JTextField num10;
	JTextField num11;
	JTextField num12;
	JTextField num13;
	public ChairCameraInfoGUI(ChairCamera cam) 
	{
		this.cam = cam;
		makeGUI();
		addGUI();
	}
	private void makeGUI()
	{
		num1 = GUIUtils.createIntTextField(cam.getNum1(), cam::setNum1);
		num2 = GUIUtils.createIntTextField(cam.getNum2(), cam::setNum2);
		num3 = GUIUtils.createIntTextField(cam.getNum3(), cam::setNum3);
		num4 = GUIUtils.createIntTextField(cam.getNum4(), cam::setNum4);
		num5 = GUIUtils.createIntTextField(cam.getNum5(), cam::setNum5);
		num6 = GUIUtils.createIntTextField(cam.getNum6(), cam::setNum6);
		num7 = GUIUtils.createIntTextField(cam.getNum7(), cam::setNum7);
		num8 = GUIUtils.createIntTextField(cam.getNum8(), cam::setNum8);
		num9 = GUIUtils.createIntTextField(cam.getNum9(), cam::setNum9);
		num10 = GUIUtils.createIntTextField(cam.getNum10(), cam::setNum10);
		num11 = GUIUtils.createIntTextField(cam.getNum11(), cam::setNum11);
		num12 = GUIUtils.createIntTextField(cam.getNum12(), cam::setNum12);
		num13 = GUIUtils.createIntTextField(cam.getNum13(), cam::setNum13);
	}
	private void addGUI()
	{
		removeAll();
		setLayout(new GridBagLayout());
		GridBagConstraints layout = Settings.getDefaultConstraints();
		add(new LabeledInputBox("Num 1: ", num1), layout);
		add(new LabeledInputBox("Num 2: ", num2), layout);
		add(new LabeledInputBox("Num 3: ", num3), layout);
		add(new LabeledInputBox("Num 4: ", num4), layout);
		add(new LabeledInputBox("Num 5: ", num5), layout);
		add(new LabeledInputBox("Num 6: ", num6), layout);
		add(new LabeledInputBox("Num 7: ", num7), layout);
		add(new LabeledInputBox("Num 8: ", num8), layout);
		add(new LabeledInputBox("Num 9: ", num9), layout);
		add(new LabeledInputBox("Num 10: ", num10), layout);
		add(new LabeledInputBox("Num 11: ", num11), layout);
		add(new LabeledInputBox("Num 12: ", num12), layout);
		layout.weighty = 1.0;
		add(new LabeledInputBox("Num 13: ", num13), layout);
	}
}
