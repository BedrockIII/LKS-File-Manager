package GUI.FileInfo.MenuDB.MapData;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import javax.swing.JLabel;

import GUI.LabeledInputBox;
import GUI.FileInfo.GenericFileInfoGUI;
import SystemDataManagers.MenuDB.MapData.MapQuestInfoManager;
import bFM.Settings;

@SuppressWarnings("serial")
public class MapQuestManagerInfoGUI extends GenericFileInfoGUI
{
	MapQuestInfoManager manager;
	JLabel EntryCount;
	public MapQuestManagerInfoGUI(MapQuestInfoManager manager)
	{
		this.manager = manager;
		makeGUI();
		addGUI();
	}
	private void makeGUI()
	{
		EntryCount = new JLabel("" + manager.getQuests().size());
	}
	private void addGUI()
	{
		removeAll();
		setLayout(new GridBagLayout());
		GridBagConstraints layout = Settings.getDefaultConstraints();
		layout.weighty = 1.0;
		add(new LabeledInputBox("Quest Count: ", EntryCount), layout);
	}
	public void update() 
	{
		EntryCount.setText("" + manager.getQuests().size());
		super.update();
	}
}
