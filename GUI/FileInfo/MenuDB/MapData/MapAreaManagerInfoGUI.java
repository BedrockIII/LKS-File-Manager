package GUI.FileInfo.MenuDB.MapData;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import javax.swing.JLabel;

import GUI.LabeledInputBox;
import GUI.FileInfo.GenericFileInfoGUI;
import SystemDataManagers.MenuDB.MapData.MapAreaManager;
import bFM.Settings;

@SuppressWarnings("serial")
public class MapAreaManagerInfoGUI extends GenericFileInfoGUI
{
	MapAreaManager manager;
	JLabel EntryCount;
	public MapAreaManagerInfoGUI(MapAreaManager manager)
	{
		this.manager = manager;
		makeGUI();
		addGUI();
	}
	private void makeGUI()
	{
		EntryCount = new JLabel("" + manager.getAreas().size());
	}
	private void addGUI()
	{
		removeAll();
		setLayout(new GridBagLayout());
		GridBagConstraints layout = Settings.getDefaultConstraints();
		layout.weighty = 1.0;
		add(new LabeledInputBox("Area Count: ", EntryCount), layout);
	}
	public void update() 
	{
		EntryCount.setText("" + manager.getAreas().size());
		super.update();
	}
}
