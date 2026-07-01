package GUI.FileInfo.CharacterDataBase;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JLabel;

import GUI.LabeledInputBox;
import GUI.FileInfo.GenericFileInfoGUI;
import ResourceManagers.CharacterDatabaseManager.TextAnimationList.AnimationList;
import bFM.Settings;

@SuppressWarnings("serial")
public class CharacterAnimationListInfoGUI extends GenericFileInfoGUI
{
	AnimationList object;
	JLabel animationCountText;
	public CharacterAnimationListInfoGUI(AnimationList object)
	{
		this.object = object;
		makeGUI();
		addGUI();
	}
	private void makeGUI()
	{
		animationCountText = new JLabel("" + object.getAnimations().size());
	}
	private void addGUI()
	{
		removeAll();
		setLayout(new GridBagLayout());
		GridBagConstraints layout = Settings.getDefaultConstraints();
		layout.weighty = 1.0;
		add(new LabeledInputBox("Animations Count", animationCountText), layout);;
	}
	public void update() 
	{
		animationCountText.setText("" + object.getAnimations().size());
		addGUI();
	}
}