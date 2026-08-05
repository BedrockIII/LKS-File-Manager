package GUI.FileInfo.MissionDataBase;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import javax.swing.JTextField;

import GUI.BitFlagPanel;
import GUI.LabeledInputBox;
import GUI.FileInfo.GenericFileInfoGUI;
import ResourceManagers.MSDBManager.Placement.MobRandomPoint;
import bFM.Settings;

@SuppressWarnings("serial")
public class RandomPositionGUI extends GenericFileInfoGUI 
{
	MobRandomPoint object = null;
	JTextField xPos;
	JTextField yPos;
	JTextField zPos;
	JTextField rotation;
	BitFlagPanel ActivationFlag;
	BitFlagPanel DeactivationFlag;
	public RandomPositionGUI(MobRandomPoint data) 
	{
		object = data;
		makeGUI();
		addGUI();
	}
	private void makeGUI()
	{
		xPos = bFM.GUIUtils.createFloatTextField(object.getxPos(), object::setxPos);
		yPos = bFM.GUIUtils.createFloatTextField(object.getyPos(), object::setyPos);
		zPos = bFM.GUIUtils.createFloatTextField(object.getzPos(), object::setzPos);
		rotation = bFM.GUIUtils.createFloatTextField(object.getRotation(), object::setRotation);
		ActivationFlag = new BitFlagPanel("Activation Flag", object.getActivationFlag(), object::setActivationFlag);
		DeactivationFlag = new BitFlagPanel("Deactivation Flag", object.getDeactivationFlag(), object::setDeactivationFlag);
	}
	private void addGUI()
	{
		removeAll();
		setLayout(new GridBagLayout());
		GridBagConstraints layout = Settings.getDefaultConstraints();
		add(new LabeledInputBox("X Position: ",  xPos), layout);
		add(new LabeledInputBox("Y Position: ",  yPos), layout);
		add(new LabeledInputBox("Z Position: ",  zPos), layout);
		add(new LabeledInputBox("Rotation: ",  rotation), layout);
		add(ActivationFlag, layout);
		layout.weighty = 1.0;
		add(DeactivationFlag, layout);
	}
}