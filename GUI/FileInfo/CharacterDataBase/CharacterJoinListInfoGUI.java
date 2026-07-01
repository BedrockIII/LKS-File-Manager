package GUI.FileInfo.CharacterDataBase;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JLabel;

import GUI.LabeledInputBox;
import GUI.FileInfo.GenericFileInfoGUI;
import ResourceManagers.CharacterDatabaseManager.JoinBinList;

@SuppressWarnings("serial")
public class CharacterJoinListInfoGUI extends GenericFileInfoGUI 
{
	JoinBinList object;
	LabeledInputBox JoinCount;
	public CharacterJoinListInfoGUI(JoinBinList object) 
	{
		this.object = object;
		makeGUI();
		addGUI();
	}
	private void makeGUI()
	{
		JoinCount = new LabeledInputBox("Join Count", new JLabel("" + object.getAmountOfJoins()));
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
		add(JoinCount, layout);
	}
	public void update() 
	{
		JoinCount.replaceComponent(new JLabel("" + object.getAmountOfJoins()));
		addGUI();
	}
}
