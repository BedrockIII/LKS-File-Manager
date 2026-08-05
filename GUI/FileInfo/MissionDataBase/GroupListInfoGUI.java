package GUI.FileInfo.MissionDataBase;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import GUI.LabeledInputBox;
import GUI.FileInfo.GenericFileInfoGUI;
import ResourceManagers.MSDBManager.Placement.MissionObjectPlacementManager;
import bFM.Settings;

@SuppressWarnings("serial")
public class GroupListInfoGUI extends GenericFileInfoGUI 
{
	MissionObjectPlacementManager object = null;
	JLabel objectCount;
	JLabel categoryCount;
	public GroupListInfoGUI(MissionObjectPlacementManager data) 
	{
		object = data;
		makeGUI();
		addGUI();
	}
	private void makeGUI()
	{
		objectCount = new JLabel("" + object.getMobGroups().size());
		categoryCount = new JLabel("" + object.getGroupCodes().size());
	}
	private void addGUI()
	{
		removeAll();
		setLayout(new GridBagLayout());
		GridBagConstraints layout = Settings.getDefaultConstraints();
		add(new LabeledInputBox("Group Count: ",  objectCount), layout);
		layout.weighty = 1.0;
		add(new LabeledInputBox("Unique Group Types: ",  categoryCount), layout);
	}
	public void update()
	{
		objectCount.setText("" + object.getMobGroups().size());
		categoryCount.setText("" + object.getGroupCodes().size());
		super.update();
	}
}