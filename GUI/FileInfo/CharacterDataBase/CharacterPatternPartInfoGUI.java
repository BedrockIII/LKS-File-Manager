package GUI.FileInfo.CharacterDataBase;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JLabel;
import javax.swing.JTextField;
import GUI.LabeledInputBox;
import GUI.FileInfo.GenericFileInfoGUI;
import ResourceManagers.CharacterDatabaseManager.TextAnimationList.Part2;
import bFM.Settings;

@SuppressWarnings("serial")
public class CharacterPatternPartInfoGUI extends GenericFileInfoGUI
{
	Part2 object;
	JTextField nameText;
	JLabel partCountText;
	public CharacterPatternPartInfoGUI(Part2 object)
	{
		this.object = object;
		makeGUI();
		addGUI();
	}
	private void makeGUI()
	{
		nameText = bFM.GUIUtils.createNameTextField(object.getName(), object::setName);
		partCountText = new JLabel("" + object.getPatterns().size());
	}
	private void addGUI()
	{
		removeAll();
		setLayout(new GridBagLayout());
		GridBagConstraints layout = Settings.getDefaultConstraints();
		add(new LabeledInputBox("Name: ", nameText), layout);
		layout.weighty = 1.0;
		add(new LabeledInputBox("Pattern Count: ", partCountText), layout);
	}
	public void update() 
	{
		partCountText.setText("" + object.getPatterns().size());
		addGUI();
	}
}