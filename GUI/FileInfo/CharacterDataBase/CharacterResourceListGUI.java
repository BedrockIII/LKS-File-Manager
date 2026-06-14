package GUI.FileInfo.CharacterDataBase;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JLabel;

import GUI.LabeledInputBox;
import GUI.FileInfo.GenericFileInfoGUI;
import ResourceManagers.CharacterDatabaseManager.CharacterResourceList;

@SuppressWarnings("serial")
public class CharacterResourceListGUI extends GenericFileInfoGUI
{
	CharacterResourceList object;
	LabeledInputBox FaceCount;
	LabeledInputBox BodyCount;
	public CharacterResourceListGUI(CharacterResourceList object)
	{
		this.object = object;
		makeGUI();
		addGUI();
	}
	private void makeGUI()
	{
		BodyCount = new LabeledInputBox("Body Count", new JLabel("" + object.getAmountOfBodies()), 1.5);
		FaceCount = new LabeledInputBox("Face Count", new JLabel("" + object.getAmountOfFaces()), 1.5);
	}
	private void addGUI()
	{
		removeAll();
		setLayout(new GridBagLayout());
		GridBagConstraints layout = new GridBagConstraints();
		layout.anchor = GridBagConstraints.NORTHWEST;
		layout.gridwidth = GridBagConstraints.REMAINDER;
		layout.weighty = 0;
		add(BodyCount, layout);
		layout.weighty = 1.0;
		layout.weightx = 1.0;
		add(FaceCount, layout);
	}
	public void update() 
	{
		BodyCount.replaceComponent(new JLabel("" + object.getAmountOfBodies()));
		FaceCount.replaceComponent(new JLabel("" + object.getAmountOfFaces()));
		addGUI();
	}
}
