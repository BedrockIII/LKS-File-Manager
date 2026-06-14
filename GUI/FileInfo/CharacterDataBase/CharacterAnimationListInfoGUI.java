package GUI.FileInfo.CharacterDataBase;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JLabel;

import GUI.LabeledInputBox;
import GUI.FileInfo.GenericFileInfoGUI;
import ResourceManagers.CharacterDatabaseManager.TextAnimationList.AnimationList;

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
		GridBagConstraints layout = new GridBagConstraints();
		layout.anchor = GridBagConstraints.NORTHWEST;
		layout.gridwidth = GridBagConstraints.REMAINDER;
		layout.weighty = 1.0;
		layout.weightx = 1.0;
		add(new LabeledInputBox("Animations Count", animationCountText, 1.5), layout);;
	}
	public void update() 
	{
		animationCountText.setText("" + object.getAnimations().size());
	}
}