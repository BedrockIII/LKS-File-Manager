package GUI.FileInfo.MenuDB.MapData;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import javax.swing.JLabel;

import GUI.LabeledInputBox;
import GUI.FileInfo.GenericFileInfoGUI;
import SystemDataManagers.MenuDB.MapData.MapPOIDataManager;
import bFM.Settings;

@SuppressWarnings("serial")
public class MapPOIManagerInfoGUI extends GenericFileInfoGUI
{
	MapPOIDataManager manager;
	JLabel EntryCount;
	public MapPOIManagerInfoGUI(MapPOIDataManager manager)
	{
		this.manager = manager;
		makeGUI();
		addGUI();
	}
	private void makeGUI()
	{
		EntryCount = new JLabel("" + manager.getPOIs().size());
	}
	private void addGUI()
	{
		removeAll();
		setLayout(new GridBagLayout());
		GridBagConstraints layout = Settings.getDefaultConstraints();
		layout.weighty = 1.0;
		add(new LabeledInputBox("Points of Interest Count: ", EntryCount), layout);
	}
	public void update() 
	{
		EntryCount.setText("" + manager.getPOIs().size());
		super.update();
	}
}
