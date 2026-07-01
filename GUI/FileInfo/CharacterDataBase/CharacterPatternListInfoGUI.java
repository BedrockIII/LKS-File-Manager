package GUI.FileInfo.CharacterDataBase;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JLabel;
import GUI.LabeledInputBox;
import GUI.FileInfo.GenericFileInfoGUI;
import ResourceManagers.CharacterDatabaseManager.TextAnimationList.PatternList;
import bFM.Settings;

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
		GridBagConstraints layout = Settings.getDefaultConstraints();
		layout.weighty = 1.0;
		add(new LabeledInputBox("Patterns Count", partCountText), layout);;
	}
	public void update() 
	{
		partCountText.setText("" + object.getPatterns().size());
		addGUI();
	}
}