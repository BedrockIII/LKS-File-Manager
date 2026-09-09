package GUI.FileInfo.MissionDataBase;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import GUI.LabeledInputBox;
import GUI.FileInfo.GenericFileInfoGUI;
import ResourceManagers.MSDBManager.Definition.MobAttackList;
import bFM.Settings;

@SuppressWarnings("serial")
public class AttacksInfoGUI extends GenericFileInfoGUI 
{
	MobAttackList object = null;
	JLabel objectCount;
	public AttacksInfoGUI(MobAttackList attacks) 
	{
		object = attacks;
		makeGUI();
		addGUI();
	}
	private void makeGUI()
	{
		objectCount = new JLabel("" + object.getAttacks().size());
	}
	private void addGUI()
	{
		removeAll();
		setLayout(new GridBagLayout());
		GridBagConstraints layout = Settings.getDefaultConstraints();
		layout.weighty = 1.0;
		add(new LabeledInputBox("Attack Count: ",  objectCount), layout);
	}
	public void update()
	{
		objectCount.setText("" + object.getAttacks().size());
		super.update();
	}
}