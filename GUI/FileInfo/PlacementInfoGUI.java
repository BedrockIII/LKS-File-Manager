package GUI.FileInfo;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import GUI.LabeledInputBox;
import ResourceManagers.MSDBManager.Placement.MissionObjectPlacementManager;
import bFM.Settings;

@SuppressWarnings("serial")
public class PlacementInfoGUI extends GenericFileInfoGUI 
{
	MissionObjectPlacementManager object = null;
	JLabel objectCount;
	JLabel areaCount;
	public PlacementInfoGUI(MissionObjectPlacementManager data) 
	{
		object = data;
		makeGUI();
		addGUI();
	}
	private void makeGUI()
	{
		objectCount = new JLabel("" + object.getMobGroups().size());
		areaCount = new JLabel("" + object.getAreaList().size());
	}
	private void addGUI()
	{
		removeAll();
		setLayout(new GridBagLayout());
		GridBagConstraints layout = Settings.getDefaultConstraints();
		add(new LabeledInputBox("Group Count: ",  objectCount), layout);
		layout.weighty = 1.0;
		add(new LabeledInputBox("Random Area Count: ",  areaCount), layout);
	}
	public void update()
	{
		objectCount.setText("" + object.getMobGroups().size());
		areaCount.setText("" + object.getAreaList().size());
		super.update();
	}
}