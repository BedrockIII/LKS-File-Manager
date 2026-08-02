package GUI.FileInfo;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.ArrayList;

import javax.swing.JLabel;
import GUI.LabeledInputBox;
import ResourceManagers.MSDBManager.Placement.MobRandomArea;
import bFM.Settings;

@SuppressWarnings("serial")
public class RandomAreaListInfoGUI extends GenericFileInfoGUI 
{
	ArrayList<MobRandomArea> object = null;
	JLabel objectCount;
	JLabel categoryCount;
	public RandomAreaListInfoGUI(ArrayList<MobRandomArea> data) 
	{
		object = data;
		makeGUI();
		addGUI();
	}
	private void makeGUI()
	{
		objectCount = new JLabel("" + object.size());
	}
	private void addGUI()
	{
		removeAll();
		setLayout(new GridBagLayout());
		GridBagConstraints layout = Settings.getDefaultConstraints();
		layout.weighty = 1.0;
		add(new LabeledInputBox("Random Area Count: ",  objectCount), layout);
	}
	public void update()
	{
		objectCount.setText("" + object.size());
		super.update();
	}
}