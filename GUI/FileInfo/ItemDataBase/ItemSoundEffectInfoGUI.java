package GUI.FileInfo.ItemDataBase;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JTextField;

import GUI.LabeledInputBox;
import GUI.FileInfo.GenericFileInfoGUI;
import ResourceManagers.ItemDatabaseManager.Item;
import bFM.Settings;
import bFM.Utils;

@SuppressWarnings("serial")
public class ItemSoundEffectInfoGUI extends GenericFileInfoGUI
{
	Item item;
	private JTextField SoundEffect1;
	private JTextField SoundEffect2;
	public ItemSoundEffectInfoGUI(Item item) 
	{
		this.item = item;
		makeGUI();
		addGUI();
	}
	private void makeGUI()
	{
		SoundEffect1 = Utils.createNameTextField(item.getSoundEffect1(), item::setSoundEffect1);
		SoundEffect2 = Utils.createNameTextField(item.getSoundEffect2(), item::setSoundEffect2);
	}
	private void addGUI()
	{
		removeAll();
		setLayout(new GridBagLayout());
		GridBagConstraints layout = Settings.getDefaultConstraints();
		add(new LabeledInputBox("Sound Effect 1", SoundEffect1), layout);
		layout.weighty = 1.0;
		add(new LabeledInputBox("Sound Effect 2", SoundEffect2), layout);
	}
}
