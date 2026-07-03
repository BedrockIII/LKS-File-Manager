package GUI.FileInfo.MenuDB.CameraZone;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JLabel;

import GUI.LabeledInputBox;
import GUI.FileInfo.GenericFileInfoGUI;
import SystemDataManagers.MenuDB.CameraData.CameraZoneList;
import bFM.Settings;

@SuppressWarnings("serial")
public class CameraZoneListInfoGUI extends GenericFileInfoGUI
{
	CameraZoneList manager;
	JLabel zoneCount;
	public CameraZoneListInfoGUI(CameraZoneList manager)
	{
		this.manager = manager;
		makeGUI();
		addGUI();
	}
	private void makeGUI()
	{
		zoneCount = new JLabel("" + manager.getZones().size());
	}
	private void addGUI()
	{
		removeAll();
		setLayout(new GridBagLayout());
		GridBagConstraints layout = Settings.getDefaultConstraints();
		layout.weighty = 1.0;
		add(new LabeledInputBox("Zone Count: ", zoneCount), layout);
	}
	public void update() 
	{
		zoneCount.setText("" + manager.getZones().size());
		super.update();
	}
}
