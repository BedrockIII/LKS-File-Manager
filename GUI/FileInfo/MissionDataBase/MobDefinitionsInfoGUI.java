package GUI.FileInfo.MissionDataBase;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import GUI.LabeledInputBox;
import GUI.FileInfo.GenericFileInfoGUI;
import ResourceManagers.MSDBManager.Definition.MissionObjectDefinitionManager;
import bFM.Settings;

@SuppressWarnings("serial")
public class MobDefinitionsInfoGUI extends GenericFileInfoGUI 
{
	MissionObjectDefinitionManager object = null;
	JLabel attackCount;
	JLabel damageCount;
	JLabel resCount;
	JLabel modCount;
	JLabel aiCount;
	JLabel tableCount;
	public MobDefinitionsInfoGUI(MissionObjectDefinitionManager definitions) 
	{
		object = definitions;
		makeGUI();
		addGUI();
	}
	private void makeGUI()
	{
		attackCount = new JLabel("" + object.getAttacks().getAttacks().size());
		resCount = new JLabel("" + object.getResources().getResources().size());
		modCount = new JLabel("" + object.getDefinitions().getSize());
		aiCount = new JLabel("" + object.getAis().getSize());
		tableCount = new JLabel("" + object.getPresetTable().getSize());
		damageCount = new JLabel("" + object.getDamageCols().getSize());
	}
	private void addGUI()
	{
		removeAll();
		setLayout(new GridBagLayout());
		GridBagConstraints layout = Settings.getDefaultConstraints();
		add(new LabeledInputBox("Attack Count: ",  attackCount), layout);
		add(new LabeledInputBox("Resource Count: ",  resCount), layout);
		add(new LabeledInputBox("Definitions Count: ",  modCount), layout);
		add(new LabeledInputBox("AI Type Count: ",  aiCount), layout);
		add(new LabeledInputBox("Preset Tables Count: ",  tableCount), layout);
		layout.weighty = 1.0;
		add(new LabeledInputBox("Damage Collisions Count: ",  damageCount), layout);
	}
	public void update()
	{
		attackCount.setText("" + object.getAttacks().getAttacks().size());
		resCount.setText("" + object.getResources().getResources().size());
		modCount.setText("" + object.getDefinitions().getSize());
		aiCount.setText("" + object.getAis().getSize());
		tableCount.setText("" + object.getPresetTable().getSize());
		damageCount.setText("" + object.getDamageCols().getSize());
		super.update();
	}
}