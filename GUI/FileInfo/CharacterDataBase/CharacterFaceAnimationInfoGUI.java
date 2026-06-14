package GUI.FileInfo.CharacterDataBase;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JLabel;

import GUI.LabeledInputBox;
import GUI.FileInfo.GenericFileInfoGUI;
import ResourceManagers.CharacterDatabaseManager.TextAnimationList;

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
		GridBagConstraints layout = new GridBagConstraints();
		layout.anchor = GridBagConstraints.NORTHWEST;
		layout.gridwidth = GridBagConstraints.REMAINDER;
		layout.weighty = 0.0;
		add(new LabeledInputBox("Animations Count", animationCountText, 1.5), layout);
		layout.weighty = 1.0;
		layout.weightx = 1.0;
		add(new LabeledInputBox("Patterns Count", partCountText, 1.5), layout);
	}
	public void update() 
	{
		partCountText.setText("" + object.getPatterns().getPatterns().size());
		animationCountText.setText("" + object.getAnimations().getAnimations().size());
	}
}