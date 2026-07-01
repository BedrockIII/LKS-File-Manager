package GUI.FileInfo.CharacterDataBase;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import GUI.GUI;
import GUI.LabeledInputBox;
import GUI.FileInfo.GenericFileInfoGUI;
import ResourceManagers.CharacterDatabaseManager.CharacterResourceList.CharacterFace;

@SuppressWarnings("serial")
public class CharacterFaceInfoGUI extends GenericFileInfoGUI
{
	CharacterFace object;
	JTextField characterCodeText;
	JTextField jobCodeText;
	JTextField num1Text;
	JTextField genderCodeText;
	JTextField num2Text;
	JTextField modelText;
	JTextField modelTypeText;
	JTextField referenceCharacterCodeText;
	JTextField num3Text;
	JTextField num4Text;
	JTextField num5Text;
	JTextField nameText;
	public CharacterFaceInfoGUI(CharacterFace object) 
	{
		this.object = object;
		makeGUI();
		addGUI();
	}
	private void makeGUI()
	{
		characterCodeText = new JTextField("" + object.getCharacterCode());
		characterCodeText.getDocument().addDocumentListener(new DocumentListener()
		{
			public void insertUpdate(DocumentEvent e) 
			{
				object.setCharacterCode(bFM.Utils.strToInt(characterCodeText.getText()));
				GUI.update();
			}
			public void removeUpdate(DocumentEvent e) 
			{
				object.setCharacterCode(bFM.Utils.strToInt(characterCodeText.getText()));
				GUI.update();
			}
			public void changedUpdate(DocumentEvent e) {}
		});
		
		jobCodeText = new JTextField("" + object.getJobCode());
		jobCodeText.getDocument().addDocumentListener(new DocumentListener()
		{
			public void insertUpdate(DocumentEvent e) 
			{
				object.setJobCode(bFM.Utils.strToInt(jobCodeText.getText()));
				GUI.update();
			}
			public void removeUpdate(DocumentEvent e) 
			{
				object.setJobCode(bFM.Utils.strToInt(jobCodeText.getText()));
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

		genderCodeText = new JTextField("" + object.getGenderCode());
		genderCodeText.getDocument().addDocumentListener(new DocumentListener()
		{
			public void insertUpdate(DocumentEvent e) 
			{
				object.setGenderCode(bFM.Utils.strToInt(genderCodeText.getText()));
				GUI.update();
			}
			public void removeUpdate(DocumentEvent e) 
			{
				object.setGenderCode(bFM.Utils.strToInt(genderCodeText.getText()));
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

		modelText = new JTextField(object.getModel());
		modelText.getDocument().addDocumentListener(new DocumentListener()
		{
			public void insertUpdate(DocumentEvent e) 
			{
				object.setModel(modelText.getText());
				GUI.update();
			}
			public void removeUpdate(DocumentEvent e) 
			{
				object.setModel(modelText.getText());
				GUI.update();
			}
			public void changedUpdate(DocumentEvent e) {}
		});

		modelTypeText = new JTextField(object.getModelType());
		modelTypeText.getDocument().addDocumentListener(new DocumentListener()
		{
			public void insertUpdate(DocumentEvent e) 
			{
				object.setModelType(modelTypeText.getText());
				GUI.update();
			}
			public void removeUpdate(DocumentEvent e) 
			{
				object.setModelType(modelTypeText.getText());
				GUI.update();
			}
			public void changedUpdate(DocumentEvent e) {}
		});

		referenceCharacterCodeText = new JTextField("" + object.getReferenceCharacterCode());
		referenceCharacterCodeText.getDocument().addDocumentListener(new DocumentListener()
		{
			public void insertUpdate(DocumentEvent e) 
			{
				object.setReferenceCharacterCode(bFM.Utils.strToInt(referenceCharacterCodeText.getText()));
				GUI.update();
			}
			public void removeUpdate(DocumentEvent e) 
			{
				object.setReferenceCharacterCode(bFM.Utils.strToInt(referenceCharacterCodeText.getText()));
				GUI.update();
			}
			public void changedUpdate(DocumentEvent e) {}
		});

		num3Text = new JTextField("" + object.getNum3());
		num3Text.getDocument().addDocumentListener(new DocumentListener()
		{
			public void insertUpdate(DocumentEvent e) 
			{
				object.setNum3(bFM.Utils.strToInt(num3Text.getText()));
				GUI.update();
			}
			public void removeUpdate(DocumentEvent e) 
			{
				object.setNum3(bFM.Utils.strToInt(num3Text.getText()));
				GUI.update();
			}
			public void changedUpdate(DocumentEvent e) {}
		});

		num4Text = new JTextField("" + object.getNum4());
		num4Text.getDocument().addDocumentListener(new DocumentListener()
		{
			public void insertUpdate(DocumentEvent e) 
			{
				object.setNum4(bFM.Utils.strToInt(num4Text.getText()));
				GUI.update();
			}
			public void removeUpdate(DocumentEvent e) 
			{
				object.setNum4(bFM.Utils.strToInt(num4Text.getText()));
				GUI.update();
			}
			public void changedUpdate(DocumentEvent e) {}
		});

		num5Text = new JTextField("" + object.getNum5());
		num5Text.getDocument().addDocumentListener(new DocumentListener()
		{
			public void insertUpdate(DocumentEvent e) 
			{
				object.setNum5(bFM.Utils.strToInt(num5Text.getText()));
				GUI.update();
			}
			public void removeUpdate(DocumentEvent e) 
			{
				object.setNum5(bFM.Utils.strToInt(num5Text.getText()));
				GUI.update();
			}
			public void changedUpdate(DocumentEvent e) {}
		});
		
		nameText = new JTextField("" + object.getName());
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
	}
	private void addGUI()
	{
		removeAll();
		setLayout(new GridBagLayout());
		GridBagConstraints layout = new GridBagConstraints();
		layout.anchor = GridBagConstraints.NORTHWEST;
		layout.gridwidth = GridBagConstraints.REMAINDER;
		layout.weighty = 0.0;
		layout.fill = GridBagConstraints.HORIZONTAL;
		layout.weightx = 1.0;
		add(new LabeledInputBox("Name", nameText), layout);
		add(new LabeledInputBox("Character Code", characterCodeText), layout);
		add(new LabeledInputBox("Job Code", jobCodeText), layout);
		add(new LabeledInputBox("Num 1", num1Text), layout);
		add(new LabeledInputBox("Gender Code", genderCodeText), layout);
		add(new LabeledInputBox("Num 2", num2Text), layout);
		add(new LabeledInputBox("Model", modelText), layout);
		add(new LabeledInputBox("Model Type", modelTypeText), layout);
		add(new LabeledInputBox("Reference Character Code", referenceCharacterCodeText), layout);
		add(new LabeledInputBox("Num 3", num3Text), layout);
		add(new LabeledInputBox("Num 4", num4Text), layout);
		
		layout.weighty = 1.0;
		layout.weightx = 1.0;
		add(new LabeledInputBox("Num 5", num5Text), layout);
	}
}
