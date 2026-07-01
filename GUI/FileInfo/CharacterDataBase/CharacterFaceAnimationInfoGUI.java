package GUI.FileInfo.CharacterDataBase;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JLabel;

import GUI.LabeledInputBox;
import GUI.FileInfo.GenericFileInfoGUI;
import ResourceManagers.CharacterDatabaseManager.TextAnimationList;
import bFM.Settings;

@SuppressWarnings("serial")
public class CharacterFaceAnimationInfoGUI extends GenericFileInfoGUI
{
	TextAnimationList object;
	JLabel animationCountText;
	JLabel partCountText;
	public CharacterFaceAnimationInfoGUI(TextAnimationList object)
	{
		this.object = object;
		makeGUI();
		addGUI();
	}
	private void makeGUI()
	{
		partCountText = new JLabel("" + object.getPatterns().getPatterns().size());
		animationCountText = new JLabel("" + object.getAnimations().getAnimations().size());
	}
	private void addGUI()
	{
		removeAll();
		setLayout(new GridBagLayout());
		GridBagConstraints layout = Settings.getDefaultConstraints();
		add(new LabeledInputBox("Animations Count", animationCountText), layout);
		layout.weighty = 1.0;
		add(new LabeledInputBox("Patterns Count", partCountText), layout);
	}
	public void update() 
	{
		partCountText.setText("" + object.getPatterns().getPatterns().size());
		animationCountText.setText("" + object.getAnimations().getAnimations().size());
		addGUI();
	}
}