package GUI.FileInfo.MenuDB.MapData;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import javax.swing.JLabel;

import GUI.LabeledInputBox;
import GUI.FileInfo.GenericFileInfoGUI;
import SystemDataManagers.MenuDB.MapData.MapDataManager;
import bFM.Settings;

@SuppressWarnings("serial")
public class MapDataManagerFileInfo extends GenericFileInfoGUI
{
	MapDataManager manager;
	JLabel Level0Count;
	JLabel Level1Count;
	JLabel Level2Count;
	JLabel Level3Count;
	JLabel AreaCount;
	JLabel QuestCount;
	public MapDataManagerFileInfo(MapDataManager manager)
	{
		this.manager = manager;
		makeGUI();
		addGUI();
	}
	private void makeGUI()
	{
		Level0Count = new JLabel("" + manager.getPOILevel0().getPOIs().size());
		Level1Count = new JLabel("" + manager.getPOILevel1().getPOIs().size());
		Level2Count = new JLabel("" + manager.getPOILevel2().getPOIs().size());
		Level3Count = new JLabel("" + manager.getPOILevel3().getPOIs().size());
		AreaCount = new JLabel("" + manager.getAreaData().getAreas().size());
		QuestCount = new JLabel("" + manager.getQuestData().getQuests().size());
	}
	private void addGUI()
	{
		removeAll();
		setLayout(new GridBagLayout());
		GridBagConstraints layout = Settings.getDefaultConstraints();
		add(new LabeledInputBox("Points of Interest Level 0 Count: ", Level0Count), layout);
		add(new LabeledInputBox("Points of Interest Level 1 Count: ", Level1Count), layout);
		add(new LabeledInputBox("Points of Interest Level 2 Count: ", Level2Count), layout);
		add(new LabeledInputBox("Points of Interest Level 3 Count: ", Level3Count), layout);
		add(new LabeledInputBox("Area Count: ", AreaCount), layout);
		layout.weighty = 1.0;
		add(new LabeledInputBox("Quest Count: ", QuestCount), layout);
	}
	public void update() 
	{
		Level0Count.setText("" + manager.getPOILevel0().getPOIs().size());
		Level1Count.setText("" + manager.getPOILevel1().getPOIs().size());
		Level2Count.setText("" + manager.getPOILevel2().getPOIs().size());
		Level3Count.setText("" + manager.getPOILevel3().getPOIs().size());
		AreaCount.setText("" + manager.getAreaData().getAreas().size());
		QuestCount.setText("" + manager.getQuestData().getQuests().size());
		super.update();
	}
}
