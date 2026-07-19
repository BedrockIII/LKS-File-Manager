package GUI.FileInfo.MenuDB.WonderSpot;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JLabel;

import GUI.LabeledInputBox;
import GUI.FileInfo.GenericFileInfoGUI;
import SystemDataManagers.MenuDB.WonderSpotManager;
import bFM.Settings;

@SuppressWarnings("serial")
public class WonderSpotListInfoGUI extends GenericFileInfoGUI
{
	WonderSpotManager manager;
	JLabel spotCount;
	public WonderSpotListInfoGUI(WonderSpotManager manager)
	{
		this.manager = manager;
		makeGUI();
		addGUI();
	}
	private void makeGUI()
	{
		spotCount = new JLabel("" + manager.getSpots().size());
	}
	private void addGUI()
	{
		removeAll();
		setLayout(new GridBagLayout());
		GridBagConstraints layout = Settings.getDefaultConstraints();
		layout.weighty = 1.0;
		add(new LabeledInputBox("Wonder Spot Count: ", spotCount), layout);
	}
	public void update() 
	{
		spotCount.setText("" + manager.getSpots().size());
		super.update();
	}
}
