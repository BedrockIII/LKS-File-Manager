package GUI.FileInfo.MissionDataBase;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import javax.swing.JTextField;

import GUI.LabeledInputBox;
import GUI.FileInfo.GenericFileInfoGUI;
import GUI.FileList.Resources.MOPlacementListGUI.RandomAreasListGUI.RandomAreaListGUI;
import ResourceManagers.MSDBManager.Placement.MobRandomArea;
import bFM.GUIUtils;
import bFM.Settings;

@SuppressWarnings("serial")
public class RandomAreaInfoGUI extends GenericFileInfoGUI 
{
	MobRandomArea object = null;
	RandomAreaListGUI parent;
	JTextField areaCode;
	JTextField num;
	JTextField group1;
	JTextField group2;
	JTextField group3;
	JTextField group4;
	public RandomAreaInfoGUI(MobRandomArea data, RandomAreaListGUI parent)
	{
		object = data;
		this.parent = parent;
		makeGUI();
		addGUI();
	}
	private void makeGUI()
	{
		areaCode = GUIUtils.createIntTextField(object.getCode(), this::setCode);
		num = GUIUtils.createIntTextField(object.getAreaData().getNum1(), object.getAreaData()::setNum1);
		group1 = GUIUtils.createIntTextField(object.getAreaData().groupCode1(), object.getAreaData()::setGroupCode1);
		group2 = GUIUtils.createIntTextField(object.getAreaData().groupCode2(), object.getAreaData()::setGroupCode2);
		group3 = GUIUtils.createIntTextField(object.getAreaData().groupCode3(), object.getAreaData()::setGroupCode3);
		group4 = GUIUtils.createIntTextField(object.getAreaData().groupCode4(), object.getAreaData()::setGroupCode4);
	}
	private void setCode(int code)
	{
		 object.setCode(code);
		 parent.update();
	}
	private void addGUI()
	{
		removeAll();
		setLayout(new GridBagLayout());
		GridBagConstraints layout = Settings.getDefaultConstraints();
		add(new LabeledInputBox("Area Code: ",  areaCode), layout);
		add(new LabeledInputBox("Random Area Number 1: ",  num), layout);
		add(new LabeledInputBox("Group Catergory 1: ",  group1), layout);
		add(new LabeledInputBox("Group Catergory 2: ",  group2), layout);
		add(new LabeledInputBox("Group Catergory 3: ",  group3), layout);
		layout.weighty = 1.0;
		add(new LabeledInputBox("Group Catergory 4: ",  group4), layout);
	}
}