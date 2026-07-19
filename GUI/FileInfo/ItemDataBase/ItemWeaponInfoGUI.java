package GUI.FileInfo.ItemDataBase;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JTextField;

import GUI.LabeledInputBox;
import GUI.FileInfo.GenericFileInfoGUI;
import ResourceManagers.ItemDatabaseManager.Item;
import bFM.GUIUtils;
import bFM.Settings;

@SuppressWarnings("serial")
public class ItemWeaponInfoGUI extends GenericFileInfoGUI
{
	Item item;
	private JTextField DigType;
	private JTextField DigSpeed;
	private JTextField BuildType;
	private JTextField BreakType;
	private JTextField AttackType;
	private JTextField BreakSpeed;
	private JTextField BuildSpeed;
	private JTextField AttackSpeed;
	public ItemWeaponInfoGUI(Item item) 
	{
		this.item = item;
		makeGUI();
		addGUI();
	}
	private void makeGUI()
	{
		DigType = GUIUtils.createNameTextField(item.getDigType(), item::setDigType);
		DigSpeed = GUIUtils.createIntTextField(item.getDigSpeed(), item::setDigSpeed);
		BuildType = GUIUtils.createNameTextField(item.getBuildType(), item::setBuildType);
		BuildSpeed = GUIUtils.createIntTextField(item.getBuildSpeed(), item::setBuildSpeed);
		BreakType = GUIUtils.createNameTextField(item.getBreakType(), item::setBreakType);
		BreakSpeed = GUIUtils.createIntTextField(item.getBreakSpeed(), item::setBreakSpeed);
		AttackType = GUIUtils.createNameTextField(item.getAttackType(), item::setAttackType);
		AttackSpeed = GUIUtils.createIntTextField(item.getAttackSpeed(), item::setAttackSpeed);
	}
	private void addGUI()
	{
		removeAll();
		setLayout(new GridBagLayout());
		GridBagConstraints layout = Settings.getDefaultConstraints();
		add(new LabeledInputBox("Dig Type", DigType), layout);
		add(new LabeledInputBox("Dig Speed", DigSpeed), layout);
		add(new LabeledInputBox("Build Type", BuildType), layout);
		add(new LabeledInputBox("Build Speed", BuildSpeed), layout);
		add(new LabeledInputBox("Break Type", BreakType), layout);
		add(new LabeledInputBox("Break Speed", BreakSpeed), layout);
		add(new LabeledInputBox("Attack Type", AttackType), layout);
		layout.weighty = 1.0;
		add(new LabeledInputBox("Attack Speed", AttackSpeed), layout);
	}
}
