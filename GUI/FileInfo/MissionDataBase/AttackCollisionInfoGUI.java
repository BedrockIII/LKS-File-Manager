package GUI.FileInfo.MissionDataBase;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import javax.swing.JTextField;

import GUI.CollapseablePanel;
import GUI.LabeledInputBox;
import GUI.FileInfo.GenericFileInfoGUI;
import ResourceManagers.MSDBManager.Definition.MobAttackCol;
import bFM.GUIUtils;
import bFM.Settings;

@SuppressWarnings("serial")
public class AttackCollisionInfoGUI extends GenericFileInfoGUI 
{
	MobAttackCol object = null;
	CollapseablePanel PlacementInfo;
	JTextField name;
	JTextField num1;
	JTextField num2;
	JTextField num3;
	JTextField num4;
	JTextField num5;
	public AttackCollisionInfoGUI(MobAttackCol data) 
	{
		object = data;
		makeGUI();
		addGUI();
	}
	private void makeGUI()
	{
		name = GUIUtils.createNameTextField(object.getName(), object::setName);
		num1 = GUIUtils.createIntTextField(object.getNum1(), object::setNum1);
		num2 = GUIUtils.createFloatTextField(object.getNum2(), object::setNum2);
		num3 = GUIUtils.createFloatTextField(object.getNum3(), object::setNum3);
		num4 = GUIUtils.createFloatTextField(object.getNum4(), object::setNum4);
		num5 = GUIUtils.createFloatTextField(object.getNum5(), object::setNum5);
	}
	private void addGUI()
	{
		removeAll();
		setLayout(new GridBagLayout());
		GridBagConstraints layout = Settings.getDefaultConstraints();
		add(new LabeledInputBox("Reference Bone: ",  name), layout);
		add(new LabeledInputBox("Number 1: ",  num1), layout);
		add(new LabeledInputBox("Number 2: ",  num2), layout);
		add(new LabeledInputBox("Number 3: ",  num3), layout);
		add(new LabeledInputBox("Number 4: ",  num4), layout);
		layout.weighty = 1.0;
		add(new LabeledInputBox("Number 5: ",  num5), layout);
	}
}