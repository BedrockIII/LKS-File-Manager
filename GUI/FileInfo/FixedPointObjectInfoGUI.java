package GUI.FileInfo;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JLabel;
import javax.swing.JTextField;
import GUI.LabeledInputBox;
import WorldFileManager.FixedPointObject;
import bFM.GUIUtils;
import bFM.Settings;

@SuppressWarnings("serial")
public class FixedPointObjectInfoGUI extends GenericFileInfoGUI
{
	FixedPointObject object = null;
	JTextField fileName = null;
	JLabel refIndexText = null;
	JLabel indexText = null;
	JTextField xOffsetText = null;
	JTextField yOffsetText = null;
	JTextField zOffsetText = null;
	JTextField xRotationText = null;
	JTextField yRotationText = null;
	JTextField zRotationText = null;
	JTextField xScaleText = null;
	JTextField yScaleText = null;
	JTextField zScaleText = null;
	
	public FixedPointObjectInfoGUI(FixedPointObject object) 
	{
		this.object = object;
		makeGUI();
		addGUI();
	}
	private void makeGUI()
	{
		fileName = GUIUtils.createNameTextField(object.getName(), object::setName);
		//refIndexText =  new JLabel("" + object.getReferenceIndex());
		//indexText =  new JLabel("" + object.getIndex());
		
		xOffsetText = GUIUtils.createFloatTextField(object.getXPos(), object::setXPos);
		yOffsetText = GUIUtils.createFloatTextField(object.getYPos(), object::setYPos);
		zOffsetText = GUIUtils.createFloatTextField(object.getZPos(), object::setZPos);
		xRotationText = GUIUtils.createFloatTextField(object.getXRot(), object::setXRotation);
		yRotationText = GUIUtils.createFloatTextField(object.getYRot(), object::setYRotation);
		zRotationText = GUIUtils.createFloatTextField(object.getZRot(), object::setZRotation);
		xScaleText = GUIUtils.createFloatTextField(object.getXScale(), object::setXScale);
		yScaleText = GUIUtils.createFloatTextField(object.getYScale(), object::setYScale);
		zScaleText = GUIUtils.createFloatTextField(object.getZScale(), object::setZScale);
	}
	private void addGUI()
	{
		removeAll();
		setLayout(new GridBagLayout());
		GridBagConstraints layout = Settings.getDefaultConstraints();
		add(new LabeledInputBox("File Name: ", fileName), layout);
		
		add(new LabeledInputBox("Index: ", indexText), layout);
		add(new LabeledInputBox("Reference Index: ", refIndexText), layout);
		add(new LabeledInputBox("X Position: ",  xOffsetText), layout);
		add(new LabeledInputBox("Y Position: ",  yOffsetText), layout);
		add(new LabeledInputBox("Z Position: ",  zOffsetText), layout);
		add(new LabeledInputBox("X Rotation: ",  xRotationText), layout);
		add(new LabeledInputBox("Y Rotation: ",  yRotationText), layout);
		add(new LabeledInputBox("Z Rotation: ",  zRotationText), layout);
		add(new LabeledInputBox("X Scale: ",  xScaleText), layout);
		add(new LabeledInputBox("Y Scale: ",  yScaleText), layout);
		layout.weighty = 1.0;
		
		add(new LabeledInputBox("Z Scale: ",  zScaleText), layout);
	}
}
