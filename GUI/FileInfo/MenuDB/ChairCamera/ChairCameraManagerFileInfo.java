package GUI.FileInfo.MenuDB.ChairCamera;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import javax.swing.JLabel;

import GUI.LabeledInputBox;
import GUI.FileInfo.GenericFileInfoGUI;
import SystemDataManagers.MenuDB.ChairCamera.ChairCameraManager;
import bFM.Settings;

@SuppressWarnings("serial")
public class ChairCameraManagerFileInfo extends GenericFileInfoGUI
{
	ChairCameraManager manager;
	JLabel numCams;
	JLabel numCams1;
	JLabel numCams2;
	JLabel numCams3;
	public ChairCameraManagerFileInfo(ChairCameraManager manager) 
	{
		this.manager = manager;
		makeGUI();
		addGUI();
	}
	private void makeGUI()
	{
		numCams = new JLabel("" + (manager.getLevel1Entries().size() + manager.getLevel2Entries().size() + manager.getLevel3Entries().size()));
		numCams1 = new JLabel("" + manager.getLevel1Entries().size());
		numCams2 = new JLabel("" + manager.getLevel2Entries().size());
		numCams3 = new JLabel("" + manager.getLevel3Entries().size());
	}
	private void addGUI()
	{
		removeAll();
		setLayout(new GridBagLayout());
		GridBagConstraints layout = Settings.getDefaultConstraints();
		add(new LabeledInputBox("Total Camera Count: ", numCams), layout);
		add(new LabeledInputBox("Castle 1 Camera Count: ", numCams1), layout);
		add(new LabeledInputBox("Castle 2 Camera Count: ", numCams2), layout);
		layout.weighty = 1.0;
		add(new LabeledInputBox("Castle 3 Camera Count: ", numCams3), layout);
	}
	public void update() 
	{
		numCams.setText("" + (manager.getLevel1Entries().size() + manager.getLevel2Entries().size() + manager.getLevel3Entries().size()));
		numCams1.setText("" + manager.getLevel1Entries().size());
		numCams2.setText("" + manager.getLevel2Entries().size());
		numCams3.setText("" + manager.getLevel3Entries().size());
		super.update();
	}
}
