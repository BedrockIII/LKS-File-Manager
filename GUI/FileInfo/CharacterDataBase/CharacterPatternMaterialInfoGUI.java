package GUI.FileInfo.CharacterDataBase;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import GUI.GUI;
import GUI.LabeledInputBox;
import GUI.FileInfo.GenericFileInfoGUI;
import ResourceManagers.CharacterDatabaseManager.TextAnimationList.Material;

@SuppressWarnings("serial")
public class CharacterPatternMaterialInfoGUI extends GenericFileInfoGUI
{
	Material object;
	JTextField nameText;
	JTextField widthText;
	JTextField heightText;
	public CharacterPatternMaterialInfoGUI(Material object)
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

		widthText = new JTextField("" + object.getWidth());
		widthText.getDocument().addDocumentListener(new DocumentListener() 
		{
			public void insertUpdate(DocumentEvent e) 
			{
				object.setWidth(bFM.Utils.strToInt(widthText.getText()));
				GUI.update();
			}
			public void removeUpdate(DocumentEvent e) 
			{
				object.setWidth(bFM.Utils.strToInt(widthText.getText()));
				GUI.update();
			}
			public void changedUpdate(DocumentEvent e) {}
		});

		heightText = new JTextField("" + object.getHeight());
		heightText.getDocument().addDocumentListener(new DocumentListener() 
		{
			public void insertUpdate(DocumentEvent e) 
			{
				object.setHeight(bFM.Utils.strToInt(heightText.getText()));
				GUI.update();
			}
			public void removeUpdate(DocumentEvent e) 
			{
				object.setHeight(bFM.Utils.strToInt(heightText.getText()));
				GUI.update();
			}
			public void changedUpdate(DocumentEvent e) {}
		});
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
		add(new LabeledInputBox("Width", widthText, 1.5), layout);
		layout.weighty = 1.0;
		layout.weightx = 1.0;
		add(new LabeledInputBox("Height", heightText, 1.5), layout);;
	}
}