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
import ResourceManagers.CharacterDatabaseManager.TextAnimationList.Part2;

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

		partCountText = new JLabel("" + object.getPatterns().size());
	}
	private void addGUI()
	{
		removeAll();
		setLayout(new GridBagLayout());
		GridBagConstraints layout = new GridBagConstraints();
		layout.anchor = GridBagConstraints.NORTHWEST;
		layout.gridwidth = GridBagConstraints.REMAINDER;
		layout.weighty = 0;
		add(new LabeledInputBox("Name", nameText, 1.5), layout);
		layout.weighty = 1.0;
		layout.weightx = 1.0;
		add(new LabeledInputBox("Pattern Count", partCountText, 1.5), layout);;
	}
	public void update() 
	{
		partCountText.setText("" + object.getPatterns().size());
	}
}