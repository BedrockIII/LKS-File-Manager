package GUI.FileInfo.ItemDataBase;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JCheckBox;
import javax.swing.JTextField;
import GUI.LabeledInputBox;
import GUI.FileInfo.GenericFileInfoGUI;
import ResourceManagers.ItemDatabaseManager.Placement;
import bFM.Settings;
import bFM.Utils;

@SuppressWarnings("serial")
public class ItemPlacementInfoGUI extends GenericFileInfoGUI
{
	Placement placement;
	JTextField xPos;
	JTextField yPos;
	JTextField zPos;
	JTextField activationFlag;
	JTextField deactivationFlag;
	JCheckBox indoors;
	JTextField buildingCode;
	public ItemPlacementInfoGUI(Placement placement) 
	{
		this.placement = placement;
		makeGUI();
		addGUI();
	}
	private void makeGUI()
	{
		xPos = Utils.createFloatTextField(placement.getX(), placement::setX);
		yPos = Utils.createFloatTextField(placement.getY(), placement::setY);
		zPos = Utils.createFloatTextField(placement.getZ(), placement::setZ);
		
		activationFlag = Utils.createIntTextField(placement.getActivationFlag(), placement::setActivationFlag);
		deactivationFlag = Utils.createIntTextField(placement.getDeactivationFlag(), placement::setDeactivationFlag);
		indoors = Utils.createCheckBox(placement.getIsIndoors(), placement::setIsIndoors);
		buildingCode = Utils.createIntTextField(placement.getBuildingCode(), placement::setBuildingCode);
	}
	private void addGUI()
	{
		removeAll();
		setLayout(new GridBagLayout());
		GridBagConstraints layout = Settings.getDefaultConstraints();
		add(new LabeledInputBox("X Position", xPos), layout);
		add(new LabeledInputBox("Y Position", yPos), layout);
		add(new LabeledInputBox("Z Position", zPos), layout);
		add(new LabeledInputBox("Activation Flag", activationFlag), layout);
		add(new LabeledInputBox("Deactivation Flag", deactivationFlag), layout);
		add(new LabeledInputBox("Is Indoors", indoors), layout);
		layout.weighty = 1.0;
		add(new LabeledInputBox("Building Code (if Indoors)", buildingCode), layout);
	}
}
