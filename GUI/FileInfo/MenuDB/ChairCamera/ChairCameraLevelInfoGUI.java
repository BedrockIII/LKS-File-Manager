package GUI.FileInfo.MenuDB.ChairCamera;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.ArrayList;

import javax.swing.JLabel;

import GUI.LabeledInputBox;
import GUI.FileInfo.GenericFileInfoGUI;
import SystemDataManagers.MenuDB.ChairCamera.ChairCamera;
import bFM.Settings;

@SuppressWarnings("serial")
public class ChairCameraLevelInfoGUI extends GenericFileInfoGUI
{
	ArrayList<ChairCamera> Entries;
	JLabel numAreas;
	public ChairCameraLevelInfoGUI(ArrayList<ChairCamera> Entries) 
	{
		this.Entries = Entries;
		makeGUI();
		addGUI();
	}
	private void makeGUI()
	{
		numAreas = new JLabel("" + Entries.size());
	}
	private void addGUI()
	{
		removeAll();
		setLayout(new GridBagLayout());
		GridBagConstraints layout = Settings.getDefaultConstraints();
		layout.weighty = 1.0;
		add(new LabeledInputBox("Camera Count: ", numAreas), layout);
	}
	public void update() 
	{
		numAreas.setText("" + Entries.size());
		super.update();
	}
}
