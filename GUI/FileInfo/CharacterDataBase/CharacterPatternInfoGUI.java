package GUI.FileInfo.CharacterDataBase;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import GUI.GUI;
import GUI.LabeledInputBox;
import GUI.FileInfo.GenericFileInfoGUI;
import ResourceManagers.CharacterDatabaseManager.TextAnimationList.PatternPart;
import bFM.Settings;

@SuppressWarnings("serial")
public class CharacterPatternInfoGUI extends GenericFileInfoGUI
{
	PatternPart object;
	JTextField nameText;
	JLabel partCountText;
	public CharacterPatternInfoGUI(PatternPart object)
	{
		this.object = object;
		makeGUI();
		addGUI();
	}
	private void makeGUI()
	{
		nameText = new JTextField(object.getName());
		nameText.getDocument().addDocumentListener(new DocumentListener() 
		{
			public void insertUpdate(DocumentEvent e) 
			{
				object.setName(nameText.getText());
				GUI.update();
			}
			public void removeUpdate(DocumentEvent e) 
			{
				object.setName(nameText.getText());
				GUI.update();
			}
			public void changedUpdate(DocumentEvent e) {}
		});

		partCountText = new JLabel("" + object.getParts().size());
	}
	private void addGUI()
	{
		removeAll();
		setLayout(new GridBagLayout());
		GridBagConstraints layout = Settings.getDefaultConstraints();
		add(new LabeledInputBox("Name", nameText), layout);
		layout.weighty = 1.0;
		add(new LabeledInputBox("Part Count", partCountText), layout);
	}
	public void update() 
	{
		partCountText.setText("" + object.getParts().size());
		addGUI();
	}
}