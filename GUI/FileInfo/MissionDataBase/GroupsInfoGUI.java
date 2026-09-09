package GUI.FileInfo.MissionDataBase;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JTextField;

import GUI.CollapseablePanel;
import GUI.LabeledInputBox;
import GUI.FileInfo.GenericFileInfoGUI;
import ResourceManagers.MSDBManager.Placement.MobGroup;
import bFM.GUIUtils;
import bFM.GroupCategoryManager;
import bFM.Settings;

@SuppressWarnings("serial")
public class GroupsInfoGUI extends GenericFileInfoGUI 
{
	ArrayList<MobGroup> object = null;
	CollapseablePanel PlacementInfo;
	JTextField name;
	JLabel objectCount;
	public GroupsInfoGUI(ArrayList<MobGroup> data) 
	{
		object = data;
		makeGUI();
		addGUI();
	}
	private void makeGUI()
	{
		name = GUIUtils.createNameTextField(GroupCategoryManager.getCategory(object.get(0).getGroupNumber()).name().toString(), 
				GroupCategoryManager.getCategory(object.get(0).getGroupNumber())::setName);
		objectCount = new JLabel("" + object.size());
	}
	private void addGUI()
	{
		removeAll();
		setLayout(new GridBagLayout());
		GridBagConstraints layout = Settings.getDefaultConstraints();
		add(new LabeledInputBox("Group Category: ",  name), layout);
		layout.weighty = 1.0;
		add(new LabeledInputBox("Group Count: ",  objectCount), layout);
	}
	public void update()
	{
		objectCount.setText("" + object.size());
		super.update();
	}
}