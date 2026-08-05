package GUI.FileInfo.MissionDataBase;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JLabel;
import javax.swing.JTextField;

import GUI.BitFlagPanel;
import GUI.CollapseablePanel;
import GUI.LabeledInputBox;
import GUI.FileInfo.GenericFileInfoGUI;
import GUI.FileList.Resources.MOPlacementListGUI.GroupTypesListGUI.GroupsListGUI.GroupListGUI;
import ResourceManagers.MSDBManager.Placement.MobConstantPlace;
import ResourceManagers.MSDBManager.Placement.MobGroup;
import bFM.Settings;

@SuppressWarnings("serial")
public class GroupInfoGUI extends GenericFileInfoGUI 
{
	MobGroup object = null;
	CollapseablePanel PlacementInfo;
	JTextField groupIndex; //First 2 Bytes
	JTextField num1; //Next 2 Bytes
	JLabel objectCount; //Next 2 Bytes
	JTextField num4; //Next 2 Bytes
	JTextField num6; //Next 2 Bytes
	JTextField num7; //Next 2 Bytes
	JTextField num8; //Next 4 Bytes
	JTextField num9; //Next 2 Bytes
	
	//Placement Data:
	JTextField xPos; //First 2 Bytes
	JTextField yPos; //Next 2 Bytes
	JTextField zPos; //Next 2 Bytes
	JTextField rotation; //Next 2 Bytes
	JTextField spawnRadius; //Next 2 Bytes
	JTextField num5; //Next 2 Bytes DespawnRadius???
	BitFlagPanel activationFlag2; //Next 2 Bytes
	JTextField num12; //Next 2 Bytes
	BitFlagPanel activationFlag1; //Next 2 Bytes
	BitFlagPanel clearFlag; //Next 2 Bytes
	BitFlagPanel deactivationFlag; //Next 2 Bytes
	JTextField itemCode; 
	GroupListGUI parent;
	public GroupInfoGUI(MobGroup object, GroupListGUI parent) 
	{
		this.parent = parent;
		this.object = object;
		makeGUI();
		addGUI();
	}
	private void makeGUI()
	{
		groupIndex = bFM.GUIUtils.createIntTextField(object.getGroupIndex(), this::setIndex);
		num1 = bFM.GUIUtils.createIntTextField(object.getNum1(), object::setNum1);
		objectCount = new JLabel("" + object.getObjectCount());
		num4 = bFM.GUIUtils.createIntTextField(object.getNum4(), object::setNum4);
		num6 = bFM.GUIUtils.createIntTextField(object.getNum6(), object::setNum6);
		num7 = bFM.GUIUtils.createIntTextField(object.getNum7(), object::setNum7);
		num8 = bFM.GUIUtils.createFloatTextField(object.getNum8(), object::setNum8);
		num9 = bFM.GUIUtils.createIntTextField(object.getNum9(), object::setNum9);
		if(object.getPlacement() != null)
		{
			makePlacementGUI(this.object.getPlacement());
		}
	}
	private void setIndex(int index)
	{
		object.setGroupIndex(index);
		parent.update();
	}
	private void makePlacementGUI(MobConstantPlace object)
	{
		PlacementInfo = new CollapseablePanel("Placement Data");
		PlacementInfo.isExtended(true);
		xPos = bFM.GUIUtils.createFloatTextField(object.getxPos(), object::setxPos);
		yPos = bFM.GUIUtils.createFloatTextField(object.getyPos(), object::setyPos);
		zPos = bFM.GUIUtils.createFloatTextField(object.getzPos(), object::setzPos);
		rotation = bFM.GUIUtils.createFloatTextField(object.getRotation(), object::setRotation);
		spawnRadius = bFM.GUIUtils.createFloatTextField(object.getSpawnRadius(), object::setSpawnRadius);
		num5 = bFM.GUIUtils.createFloatTextField(object.getNum5(), object::setNum5);
		activationFlag2 = new BitFlagPanel("Activation Flag 2", object.getActivationFlag2(), object::setActivationFlag2);
		num12 = bFM.GUIUtils.createIntTextField(object.getNum12(), object::setNum12);
		activationFlag1 = new BitFlagPanel("Activation Flag 1", object.getActivationFlag1(), object::setActivationFlag1);
		clearFlag = new BitFlagPanel("Clear Flag", object.getClearFlag(), object::setClearFlag);
		deactivationFlag = new BitFlagPanel("Deactivation Flag", object.getDeactivationFlag(), object::setDeactivationFlag);
		itemCode = bFM.GUIUtils.createIntTextField(object.getItemCode(), object::setItemCode);
	}
	private void addGUI()
	{
		removeAll();
		setLayout(new GridBagLayout());
		GridBagConstraints layout = Settings.getDefaultConstraints();
		if(object.getPlacement() != null)
		{
			addPlacementGUI();
			add(PlacementInfo, layout);
		}
		add(new LabeledInputBox("Group ID: ",  groupIndex), layout);
		add(new LabeledInputBox("Object Count: ",  objectCount), layout);
		add(new LabeledInputBox("Group Num 1: ",  num1), layout);
		add(new LabeledInputBox("Group Num 4: ",  num4), layout);
		add(new LabeledInputBox("Group Num 5: ",  num6), layout);
		add(new LabeledInputBox("Group Num 6: ",  num7), layout);
		add(new LabeledInputBox("Group Num 8: ",  num8), layout);
		layout.weighty = 1.0;
		add(new LabeledInputBox("Group Num 9: ",  num9), layout);
		
	}
	private void addPlacementGUI()
	{
		PlacementInfo.removeAll();
		PlacementInfo.add(new LabeledInputBox("X Position: ",  xPos));
		PlacementInfo.add(new LabeledInputBox("Y Position: ",  yPos));
		PlacementInfo.add(new LabeledInputBox("Z Position: ",  zPos));
		PlacementInfo.add(new LabeledInputBox("Rotation: ",  rotation));
		PlacementInfo.add(new LabeledInputBox("Spawn Radius: ",  spawnRadius));
		PlacementInfo.add(new LabeledInputBox("Despawn Radius (?): ",  num5));
		PlacementInfo.add(activationFlag2);
		PlacementInfo.add(new LabeledInputBox("Constant Place Number 12: ",  num12));
		PlacementInfo.add(activationFlag1);
		PlacementInfo.add(clearFlag);
		PlacementInfo.add(deactivationFlag);
		PlacementInfo.add(new LabeledInputBox("Item Drop: ",  itemCode));
	}
	public void update()
	{
		super.update();
	}
	public void addPlace(MobConstantPlace c) 
	{
		makePlacementGUI(c);
		addPlacementGUI();
		GridBagConstraints layout = Settings.getDefaultConstraints();
		add(PlacementInfo, layout, 0);
		update();
	}
	public void removePlace() 
	{
		remove(PlacementInfo);
		PlacementInfo = null;
		update();
	}
}