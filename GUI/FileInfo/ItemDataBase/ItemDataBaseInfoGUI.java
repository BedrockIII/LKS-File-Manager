package GUI.FileInfo.ItemDataBase;

import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JLabel;

import GUI.LabeledInputBox;
import GUI.FileInfo.GenericFileInfoGUI;
import ResourceManagers.ItemDatabaseManager.itemDatabaseManager;
import bFM.Settings;

@SuppressWarnings("serial")
public class ItemDataBaseInfoGUI extends GenericFileInfoGUI
{
	itemDatabaseManager file;
	private JLabel itemCount;
	public ItemDataBaseInfoGUI(itemDatabaseManager file) 
	{
		this.file = file;
		makeGUI();
		addGUI();
	}
	private void makeGUI()
	{
		itemCount = new JLabel("" + file.getItems().size());
	}
	private void addGUI()
	{
		removeAll();
		setLayout(new GridBagLayout());
		GridBagConstraints layout = Settings.getDefaultConstraints();
		layout.weighty = 1.0;
		add(new LabeledInputBox("Item Count", itemCount), layout);
	}
	public void update()
	{
		itemCount.setText("" + file.getItems().size());
		for(Component c : getComponents())
		{
			if(c instanceof LabeledInputBox)
			{
				((LabeledInputBox) c).update();
			}
		}
	}
}
