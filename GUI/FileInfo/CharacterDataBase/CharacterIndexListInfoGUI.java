package GUI.FileInfo.CharacterDataBase;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JLabel;

import GUI.LabeledInputBox;
import GUI.FileInfo.GenericFileInfoGUI;
import ResourceManagers.CharacterDatabaseManager.indBinList;

@SuppressWarnings("serial")
public class CharacterIndexListInfoGUI extends GenericFileInfoGUI 
{
	indBinList object;
	LabeledInputBox JobCount;
	public CharacterIndexListInfoGUI(indBinList object) 
	{
		this.object = object;
		makeGUI();
		addGUI();
	}
	private void makeGUI()
	{
		JobCount = new LabeledInputBox("Index Count", new JLabel("" + object.getAmountOfIndicies()), 1.5);
	}
	private void addGUI()
	{
		removeAll();
		setLayout(new GridBagLayout());
		GridBagConstraints layout = new GridBagConstraints();
		layout.anchor = GridBagConstraints.NORTHWEST;
		layout.gridwidth = GridBagConstraints.REMAINDER;
		layout.weighty = 1.0;
		layout.weightx = 1.0;
		add(JobCount, layout);
	}
	public void update() 
	{
		JobCount.replaceComponent(new JLabel("" + object.getAmountOfIndicies()));
		addGUI();
	}
}
