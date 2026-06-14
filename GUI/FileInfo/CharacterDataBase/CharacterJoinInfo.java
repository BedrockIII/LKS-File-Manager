package GUI.FileInfo.CharacterDataBase;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import GUI.GUI;
import GUI.LabeledInputBox;
import GUI.FileInfo.GenericFileInfoGUI;
import ResourceManagers.CharacterDatabaseManager.JoinBinList.join;

@SuppressWarnings("serial")
public class CharacterJoinInfo extends GenericFileInfoGUI
{
	join object;
	JTextField indexText;
	JTextField num1Text;
	JTextField num2Text;
	JTextField num3Text;
	JTextField num4Text;
	JTextField num5Text;
	JTextField num6Text;
	JTextField num7Text;
	JTextField num8Text;
	JTextField num9Text;
	JTextField xPosText;
	JTextField yPosText;
	JTextField zPosText;
	public CharacterJoinInfo(join object) 
	{
		this.object = object;
		makeGUI();
		addGUI();
	}
	private void makeGUI()
	{
		indexText = new JTextField("" + object.getIndex());
		indexText.getDocument().addDocumentListener(new DocumentListener() 
		{
			public void insertUpdate(DocumentEvent e) 
			{
				object.setIndex(bFM.Utils.strToInt(indexText.getText()));
				GUI.update();
			}
			public void removeUpdate(DocumentEvent e) 
			{
				object.setIndex(bFM.Utils.strToInt(indexText.getText()));
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

		num6Text = new JTextField("" + object.getNum6());
		num6Text.getDocument().addDocumentListener(new DocumentListener() 
		{
			public void insertUpdate(DocumentEvent e) 
			{
				object.setNum6(bFM.Utils.strToInt(num6Text.getText()));
				GUI.update();
			}
			public void removeUpdate(DocumentEvent e) 
			{
				object.setNum6(bFM.Utils.strToInt(num6Text.getText()));
				GUI.update();
			}
			public void changedUpdate(DocumentEvent e) {}
		});

		num7Text = new JTextField("" + object.getNum7());
		num7Text.getDocument().addDocumentListener(new DocumentListener() 
		{
			public void insertUpdate(DocumentEvent e) 
			{
				object.setNum7(bFM.Utils.strToInt(num7Text.getText()));
				GUI.update();
			}
			public void removeUpdate(DocumentEvent e) 
			{
				object.setNum7(bFM.Utils.strToInt(num7Text.getText()));
				GUI.update();
			}
			public void changedUpdate(DocumentEvent e) {}
		});

		num8Text = new JTextField("" + object.getNum8());
		num8Text.getDocument().addDocumentListener(new DocumentListener() 
		{
			public void insertUpdate(DocumentEvent e) 
			{
				object.setNum8(bFM.Utils.strToInt(num8Text.getText()));
				GUI.update();
			}
			public void removeUpdate(DocumentEvent e) 
			{
				object.setNum8(bFM.Utils.strToInt(num8Text.getText()));
				GUI.update();
			}
			public void changedUpdate(DocumentEvent e) {}
		});

		num9Text = new JTextField("" + object.getNum9());
		num9Text.getDocument().addDocumentListener(new DocumentListener() 
		{
			public void insertUpdate(DocumentEvent e) 
			{
				object.setNum9(bFM.Utils.strToInt(num9Text.getText()));
				GUI.update();
			}
			public void removeUpdate(DocumentEvent e) 
			{
				object.setNum9(bFM.Utils.strToInt(num9Text.getText()));
				GUI.update();
			}
			public void changedUpdate(DocumentEvent e) {}
		});

		xPosText = new JTextField("" + object.getXPos());
		xPosText.getDocument().addDocumentListener(new DocumentListener() 
		{
			public void insertUpdate(DocumentEvent e) 
			{
				object.setXPos(bFM.Utils.strToFloat(xPosText.getText()));
				GUI.update();
			}
			public void removeUpdate(DocumentEvent e) 
			{
				object.setXPos(bFM.Utils.strToFloat(xPosText.getText()));
				GUI.update();
			}
			public void changedUpdate(DocumentEvent e) {}
		});

		yPosText = new JTextField("" + object.getYPos());
		yPosText.getDocument().addDocumentListener(new DocumentListener() 
		{
			public void insertUpdate(DocumentEvent e) 
			{
				object.setYPos(bFM.Utils.strToFloat(yPosText.getText()));
				GUI.update();
			}
			public void removeUpdate(DocumentEvent e) 
			{
				object.setYPos(bFM.Utils.strToFloat(yPosText.getText()));
				GUI.update();
			}
			public void changedUpdate(DocumentEvent e) {}
		});

		zPosText = new JTextField("" + object.getZPos());
		zPosText.getDocument().addDocumentListener(new DocumentListener() 
		{
			public void insertUpdate(DocumentEvent e) 
			{
				object.setZPos(bFM.Utils.strToFloat(zPosText.getText()));
				GUI.update();
			}
			public void removeUpdate(DocumentEvent e) 
			{
				object.setZPos(bFM.Utils.strToFloat(zPosText.getText()));
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
		layout.weightx = 1.0;
		add(new LabeledInputBox("Index", indexText, 1.5), layout);
		add(new LabeledInputBox("Num 1", num1Text, 1.5), layout);
		add(new LabeledInputBox("Num 2", num2Text, 1.5), layout);
		add(new LabeledInputBox("Num 3", num3Text, 1.5), layout);
		add(new LabeledInputBox("Num 4", num4Text, 1.5), layout);
		add(new LabeledInputBox("Num 5", num5Text, 1.5), layout);
		add(new LabeledInputBox("Num 6", num6Text, 1.5), layout);
		add(new LabeledInputBox("Num 7", num7Text, 1.5), layout);
		add(new LabeledInputBox("Num 8", num8Text, 1.5), layout);
		add(new LabeledInputBox("Num 9", num9Text, 1.5), layout);
		add(new LabeledInputBox("X Position", xPosText, 1.5), layout);
		add(new LabeledInputBox("Y Position", yPosText, 1.5), layout);
		
		layout.weighty = 1.0;
		layout.weightx = 1.0;
		add(new LabeledInputBox("Z Position", zPosText, 1.5), layout);
	}
}