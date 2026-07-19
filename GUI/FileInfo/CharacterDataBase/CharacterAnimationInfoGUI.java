package GUI.FileInfo.CharacterDataBase;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JTextField;
import GUI.LabeledInputBox;
import GUI.FileInfo.GenericFileInfoGUI;
import ResourceManagers.CharacterDatabaseManager.TextAnimationList.Animation;
import bFM.Settings;

@SuppressWarnings("serial")
public class CharacterAnimationInfoGUI extends GenericFileInfoGUI
{
	Animation object;
	JTextField nameText;
	public CharacterAnimationInfoGUI(Animation object)
	{
		this.object = object;
		makeGUI();
		addGUI();
	}
	private void makeGUI()
	{
		nameText = bFM.GUIUtils.createNameTextField(object.getName(), object::setName);
	}
	private void addGUI()
	{
		removeAll();
		setLayout(new GridBagLayout());
		GridBagConstraints layout = Settings.getDefaultConstraints();
		layout.weighty = 1.0;
		add(new LabeledInputBox("Name", nameText), layout);
	}
}