package GUI.FileInfo.MenuDB.CastlePicture;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import javax.swing.JLabel;

import GUI.LabeledInputBox;
import GUI.FileInfo.GenericFileInfoGUI;
import SystemDataManagers.MenuDB.CastlePicture.CastlePictureManager;
import bFM.Settings;

@SuppressWarnings("serial")
public class CastlePictureManagerInfoGUI extends GenericFileInfoGUI
{
	CastlePictureManager manager;
	JLabel EntryCount;
	public CastlePictureManagerInfoGUI(CastlePictureManager manager)
	{
		this.manager = manager;
		makeGUI();
		addGUI();
	}
	private void makeGUI()
	{
		EntryCount = new JLabel("" + manager.getEntries().size());
	}
	private void addGUI()
	{
		removeAll();
		setLayout(new GridBagLayout());
		GridBagConstraints layout = Settings.getDefaultConstraints();
		layout.weighty = 1.0;
		add(new LabeledInputBox("Entry Count: ", EntryCount), layout);
	}
	public void update() 
	{
		EntryCount.setText("" + manager.getEntries().size());
		super.update();
	}
}
