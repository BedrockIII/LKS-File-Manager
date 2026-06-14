package GUI.FileInfo.CharacterDataBase;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import GUI.GUI;
import GUI.LabeledInputBox;
import GUI.FileInfo.GenericFileInfoGUI;
import ResourceManagers.CharacterDatabaseManager.TextAnimationList.AnimationPattern;

@SuppressWarnings("serial")
public class CharacterAnimationPatternInfoGUI extends GenericFileInfoGUI
{
	AnimationPattern object;
	JTextField nameText;
	JTextField num1Text;
	JTextField num2Text;
	public CharacterAnimationPatternInfoGUI(AnimationPattern object)
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

		num1Text = new JTextField("" + object.getNum1());
		num1Text.getDocument().addDocumentListener(new DocumentListener() 
		{
			public void insertUpdate(DocumentEvent e) 
			{
				object.setNum1(bFM.Utils.strToInt(num1Text.getText()));
				GUI.update();
			}
			public void removeUpdate(DocumentEvent e) 
			{
				object.setNum1(bFM.Utils.strToInt(num1Text.getText()));
				GUI.update();
			}
			public void changedUpdate(DocumentEvent e) {}
		});

		num2Text = new JTextField("" + object.getNum2());
		num2Text.getDocument().addDocumentListener(new DocumentListener() 
		{
			public void insertUpdate(DocumentEvent e) 
			{
				object.setNum2(bFM.Utils.strToInt(num2Text.getText()));
				GUI.update();
			}
			public void removeUpdate(DocumentEvent e) 
			{
				object.setNum2(bFM.Utils.strToInt(num2Text.getText()));
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
		add(new LabeledInputBox("Num 1", num1Text, 1.5), layout);
		layout.weighty = 1.0;
		layout.weightx = 1.0;
		add(new LabeledInputBox("Num 2", num2Text, 1.5), layout);;
	}
}
