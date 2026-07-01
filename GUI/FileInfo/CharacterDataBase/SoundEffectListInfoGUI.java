package GUI.FileInfo.CharacterDataBase;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JLabel;

import GUI.LabeledInputBox;
import GUI.FileInfo.GenericFileInfoGUI;
import ResourceManagers.CharacterDatabaseManager.SoundEffectCoordinateList;

@SuppressWarnings("serial")
public class SoundEffectListInfoGUI extends GenericFileInfoGUI
{
	SoundEffectCoordinateList object;
	LabeledInputBox JobCount;
	public SoundEffectListInfoGUI(SoundEffectCoordinateList object) 
	{
		this.object = object;
		makeGUI();
		addGUI();
	}
	private void makeGUI()
	{
		JobCount = new LabeledInputBox("Coordinate Count", new JLabel("" + object.getAmountOfCoordinates()));
	}
	private void addGUI()
	{
		removeAll();
		setLayout(new GridBagLayout());
		GridBagConstraints layout = new GridBagConstraints();
		layout.anchor = GridBagConstraints.NORTHWEST;
		layout.gridwidth = GridBagConstraints.REMAINDER;
		layout.weighty = 1.0;
		layout.fill = GridBagConstraints.HORIZONTAL;
		layout.weightx = 1.0;
		add(JobCount, layout);
	}
	public void update() 
	{
		JobCount.replaceComponent(new JLabel("" + object.getAmountOfCoordinates()));
		addGUI();
	}
}