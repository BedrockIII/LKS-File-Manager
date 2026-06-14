package GUI.FileInfo.CharacterDataBase;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JLabel;
import GUI.LabeledInputBox;
import GUI.FileInfo.GenericFileInfoGUI;
import ResourceManagers.CharacterDatabaseManager.TextAnimationList.PatternList;

@SuppressWarnings("serial")
public class CharacterPatternListInfoGUI extends GenericFileInfoGUI
{
	PatternList object;
	JLabel partCountText;
	public CharacterPatternListInfoGUI(PatternList object)
	{
		this.object = object;
		makeGUI();
		addGUI();
	}
	private void makeGUI()
	{
		partCountText = new JLabel("" + object.getPatterns().size());
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
		add(new LabeledInputBox("Patterns Count", partCountText, 1.5), layout);;
	}
	public void update() 
	{
		partCountText.setText("" + object.getPatterns().size());
	}
}