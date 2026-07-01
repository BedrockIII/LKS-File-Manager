package GUI.FileInfo.CharacterDataBase;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import GUI.GUI;
import GUI.LabeledInputBox;
import GUI.FileInfo.GenericFileInfoGUI;
import ResourceManagers.CharacterDatabaseManager.indBinList.ind;

@SuppressWarnings("serial")
public class CharacterIndexInfoGUI extends GenericFileInfoGUI
{
	ind object;
	JTextField jobCodeText = null;
	JTextField num8Text = null;
	JTextField handheldItemCodeText = null;
	JTextField helmetItemCodeText = null;
	JTextField num11Text = null;
	JTextField num12Text = null;
	JTextField word1Text = null;
	JTextField word2Text = null;
	JTextField nameText = null;
	JTextField num13aText = null;
	JTextField num13bText = null;
	JTextField num14aText = null;
	JTextField num14bText = null;
	JTextField num15aText = null;
	JTextField num15bText = null;
	JTextField num16aText = null;
	JTextField num16bText = null;
	JTextField num17aText = null;
	JTextField num17bText = null;
	JTextField num18aText = null;
	JTextField num18bText = null;
	JTextField num19aText = null;
	JTextField num19bText = null;
	JTextField num20aText = null;
	JTextField num20bText = null;
	JTextField num21aText = null;
	JTextField num21bText = null;
	JTextField num22aText = null;
	JTextField num22bText = null;
	JTextField num23aText = null;
	JTextField num23bText = null;
	JTextField num24aText = null;
	JTextField num24bText = null;
	JTextField num25aText = null;
	JTextField num25bText = null;
	JTextField num26aText = null;
	JTextField num26bText = null;
	JTextField num27aText = null;
	JTextField num27bText = null;
	JTextField num28aText = null;
	JTextField num28bText = null;
	JTextField num29aText = null;
	JTextField num29bText = null;
	JTextField num30aText = null;
	JTextField num30bText = null;
	JTextField num31aText = null;
	JTextField num31bText = null;
	JTextField num32aText = null;
	JTextField num32bText = null;
	JTextField num33aText = null;
	JTextField num33bText = null;
	JTextField jobMinimumHPText = null;
	JTextField num34bText = null;
	JTextField num35aText = null;
	JTextField num35bText = null;
	JTextField num36aText = null;
	JTextField num36bText = null;
	JTextField num37aText = null;
	JTextField num37bText = null;
	JTextField num38aText = null;
	JTextField num38bText = null;
	JTextField num39aText = null;
	JTextField attackChargesText = null;
	JTextField num40aText = null;
	JTextField num40bText = null;
	JTextField word4Text = null;
	JTextField word5Text = null;
	
	public CharacterIndexInfoGUI(ind object) 
	{
		this.object = object;
		makeGUI();
		addGUI();
	}
	private void makeGUI()
	{
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
		
		num8Text = new JTextField("" + object.getNum8());
		num8Text.getDocument().addDocumentListener(new DocumentListener() 
		{
			public void insertUpdate(DocumentEvent e) 
			{
				object.setNum8(bFM.Utils.strToInt(num8Text.getText()));
			}
			public void removeUpdate(DocumentEvent e) 
			{
				object.setNum8(bFM.Utils.strToInt(num8Text.getText()));
			}
			public void changedUpdate(DocumentEvent e) {}
		});

		handheldItemCodeText = new JTextField("" + object.getHandheldItemCode());
		handheldItemCodeText.getDocument().addDocumentListener(new DocumentListener() 
		{
			public void insertUpdate(DocumentEvent e) 
			{
				object.setHandheldItemCode(bFM.Utils.strToInt(handheldItemCodeText.getText()));
			}
			public void removeUpdate(DocumentEvent e) 
			{
				object.setHandheldItemCode(bFM.Utils.strToInt(handheldItemCodeText.getText()));
			}
			public void changedUpdate(DocumentEvent e) {}
		});

		helmetItemCodeText = new JTextField("" + object.getHelmetItemCode());
		helmetItemCodeText.getDocument().addDocumentListener(new DocumentListener() 
		{
			public void insertUpdate(DocumentEvent e) 
			{
				object.setHelmetItemCode(bFM.Utils.strToInt(helmetItemCodeText.getText()));
			}
			public void removeUpdate(DocumentEvent e) 
			{
				object.setHelmetItemCode(bFM.Utils.strToInt(helmetItemCodeText.getText()));
			}
			public void changedUpdate(DocumentEvent e) {}
		});

		num11Text = new JTextField("" + object.getNum11());
		num11Text.getDocument().addDocumentListener(new DocumentListener() 
		{
			public void insertUpdate(DocumentEvent e) 
			{
				object.setNum11(bFM.Utils.strToInt(num11Text.getText()));
			}
			public void removeUpdate(DocumentEvent e) 
			{
				object.setNum11(bFM.Utils.strToInt(num11Text.getText()));
			}
			public void changedUpdate(DocumentEvent e) {}
		});

		num12Text = new JTextField("" + object.getNum12());
		num12Text.getDocument().addDocumentListener(new DocumentListener() 
		{
			public void insertUpdate(DocumentEvent e) 
			{
				object.setNum12(bFM.Utils.strToInt(num12Text.getText()));
			}
			public void removeUpdate(DocumentEvent e) 
			{
				object.setNum12(bFM.Utils.strToInt(num12Text.getText()));
			}
			public void changedUpdate(DocumentEvent e) {}
		});

		word1Text = new JTextField(object.getWord1());
		word1Text.getDocument().addDocumentListener(new DocumentListener() 
		{
			public void insertUpdate(DocumentEvent e) 
			{
				object.setWord1(word1Text.getText());
			}
			public void removeUpdate(DocumentEvent e) 
			{
				object.setWord1(word1Text.getText());
			}
			public void changedUpdate(DocumentEvent e) {}
		});

		word2Text = new JTextField(object.getWord2());
		word2Text.getDocument().addDocumentListener(new DocumentListener() 
		{
			public void insertUpdate(DocumentEvent e) 
			{
				object.setWord2(word2Text.getText());
			}
			public void removeUpdate(DocumentEvent e) 
			{
				object.setWord2(word2Text.getText());
			}
			public void changedUpdate(DocumentEvent e) {}
		});

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

		num13aText = new JTextField("" + object.getNum13a());
		num13aText.getDocument().addDocumentListener(new DocumentListener() 
		{
			public void insertUpdate(DocumentEvent e) 
			{
				object.setNum13a(bFM.Utils.strToInt(num13aText.getText()));
			}
			public void removeUpdate(DocumentEvent e) 
			{
				object.setNum13a(bFM.Utils.strToInt(num13aText.getText()));
			}
			public void changedUpdate(DocumentEvent e) {}
		});

		num13bText = new JTextField("" + object.getNum13b());
		num13bText.getDocument().addDocumentListener(new DocumentListener() 
		{
			public void insertUpdate(DocumentEvent e) 
			{
				object.setNum13b(bFM.Utils.strToInt(num13bText.getText()));
			}
			public void removeUpdate(DocumentEvent e) 
			{
				object.setNum13b(bFM.Utils.strToInt(num13bText.getText()));
			}
			public void changedUpdate(DocumentEvent e) {}
		});

		num14aText = new JTextField("" + object.getNum14a());
		num14aText.getDocument().addDocumentListener(new DocumentListener() 
		{
			public void insertUpdate(DocumentEvent e) 
			{
				object.setNum14a(bFM.Utils.strToInt(num14aText.getText()));
			}
			public void removeUpdate(DocumentEvent e) 
			{
				object.setNum14a(bFM.Utils.strToInt(num14aText.getText()));
			}
			public void changedUpdate(DocumentEvent e) {}
		});

		num14bText = new JTextField("" + object.getNum14b());
		num14bText.getDocument().addDocumentListener(new DocumentListener() 
		{
			public void insertUpdate(DocumentEvent e) 
			{
				object.setNum14b(bFM.Utils.strToInt(num14bText.getText()));
			}
			public void removeUpdate(DocumentEvent e) 
			{
				object.setNum14b(bFM.Utils.strToInt(num14bText.getText()));
			}
			public void changedUpdate(DocumentEvent e) {}
		});

		num15aText = new JTextField("" + object.getNum15a());
		num15aText.getDocument().addDocumentListener(new DocumentListener() 
		{
			public void insertUpdate(DocumentEvent e) 
			{
				object.setNum15a(bFM.Utils.strToInt(num15aText.getText()));
			}
			public void removeUpdate(DocumentEvent e) 
			{
				object.setNum15a(bFM.Utils.strToInt(num15aText.getText()));
			}
			public void changedUpdate(DocumentEvent e) {}
		});

		num15bText = new JTextField("" + object.getNum15b());
		num15bText.getDocument().addDocumentListener(new DocumentListener() 
		{
			public void insertUpdate(DocumentEvent e) 
			{
				object.setNum15b(bFM.Utils.strToInt(num15bText.getText()));
			}
			public void removeUpdate(DocumentEvent e) 
			{
				object.setNum15b(bFM.Utils.strToInt(num15bText.getText()));
			}
			public void changedUpdate(DocumentEvent e) {}
		});
		
		num16aText = new JTextField("" + object.getNum16a());
		num16aText.getDocument().addDocumentListener(new DocumentListener() 
		{
			public void insertUpdate(DocumentEvent e) 
			{
				object.setNum16a(bFM.Utils.strToInt(num16aText.getText()));
			}
			public void removeUpdate(DocumentEvent e) 
			{
				object.setNum16a(bFM.Utils.strToInt(num16aText.getText()));
			}
			public void changedUpdate(DocumentEvent e) {}
		});

		num16bText = new JTextField("" + object.getNum16b());
		num16bText.getDocument().addDocumentListener(new DocumentListener() 
		{
			public void insertUpdate(DocumentEvent e) 
			{
				object.setNum16b(bFM.Utils.strToInt(num16bText.getText()));
			}
			public void removeUpdate(DocumentEvent e) 
			{
				object.setNum16b(bFM.Utils.strToInt(num16bText.getText()));
			}
			public void changedUpdate(DocumentEvent e) {}
		});

		num17aText = new JTextField("" + object.getNum17a());
		num17aText.getDocument().addDocumentListener(new DocumentListener() 
		{
			public void insertUpdate(DocumentEvent e) 
			{
				object.setNum17a(bFM.Utils.strToInt(num17aText.getText()));
			}
			public void removeUpdate(DocumentEvent e) 
			{
				object.setNum17a(bFM.Utils.strToInt(num17aText.getText()));
			}
			public void changedUpdate(DocumentEvent e) {}
		});

		num17bText = new JTextField("" + object.getNum17b());
		num17bText.getDocument().addDocumentListener(new DocumentListener() 
		{
			public void insertUpdate(DocumentEvent e) 
			{
				object.setNum17b(bFM.Utils.strToInt(num17bText.getText()));
			}
			public void removeUpdate(DocumentEvent e) 
			{
				object.setNum17b(bFM.Utils.strToInt(num17bText.getText()));
			}
			public void changedUpdate(DocumentEvent e) {}
		});

		num18aText = new JTextField("" + object.getNum18a());
		num18aText.getDocument().addDocumentListener(new DocumentListener() 
		{
			public void insertUpdate(DocumentEvent e) 
			{
				object.setNum18a(bFM.Utils.strToInt(num18aText.getText()));
			}
			public void removeUpdate(DocumentEvent e) 
			{
				object.setNum18a(bFM.Utils.strToInt(num18aText.getText()));
			}
			public void changedUpdate(DocumentEvent e) {}
		});

		num18bText = new JTextField("" + object.getNum18b());
		num18bText.getDocument().addDocumentListener(new DocumentListener() 
		{
			public void insertUpdate(DocumentEvent e) 
			{
				object.setNum18b(bFM.Utils.strToInt(num18bText.getText()));
			}
			public void removeUpdate(DocumentEvent e) 
			{
				object.setNum18b(bFM.Utils.strToInt(num18bText.getText()));
			}
			public void changedUpdate(DocumentEvent e) {}
		});

		num19aText = new JTextField("" + object.getNum19a());
		num19aText.getDocument().addDocumentListener(new DocumentListener() 
		{
			public void insertUpdate(DocumentEvent e) 
			{
				object.setNum19a(bFM.Utils.strToInt(num19aText.getText()));
			}
			public void removeUpdate(DocumentEvent e) 
			{
				object.setNum19a(bFM.Utils.strToInt(num19aText.getText()));
			}
			public void changedUpdate(DocumentEvent e) {}
		});

		num19bText = new JTextField("" + object.getNum19b());
		num19bText.getDocument().addDocumentListener(new DocumentListener() 
		{
			public void insertUpdate(DocumentEvent e) 
			{
				object.setNum19b(bFM.Utils.strToInt(num19bText.getText()));
			}
			public void removeUpdate(DocumentEvent e) 
			{
				object.setNum19b(bFM.Utils.strToInt(num19bText.getText()));
			}
			public void changedUpdate(DocumentEvent e) {}
		});

		num20aText = new JTextField("" + object.getNum20a());
		num20aText.getDocument().addDocumentListener(new DocumentListener() 
		{
			public void insertUpdate(DocumentEvent e) 
			{
				object.setNum20a(bFM.Utils.strToInt(num20aText.getText()));
			}
			public void removeUpdate(DocumentEvent e) 
			{
				object.setNum20a(bFM.Utils.strToInt(num20aText.getText()));
			}
			public void changedUpdate(DocumentEvent e) {}
		});

		num20bText = new JTextField("" + object.getNum20b());
		num20bText.getDocument().addDocumentListener(new DocumentListener() 
		{
			public void insertUpdate(DocumentEvent e) 
			{
				object.setNum20b(bFM.Utils.strToInt(num20bText.getText()));
			}
			public void removeUpdate(DocumentEvent e) 
			{
				object.setNum20b(bFM.Utils.strToInt(num20bText.getText()));
			}
			public void changedUpdate(DocumentEvent e) {}
		});

		num21aText = new JTextField("" + object.getNum21a());
		num21aText.getDocument().addDocumentListener(new DocumentListener() 
		{
			public void insertUpdate(DocumentEvent e) 
			{
				object.setNum21a(bFM.Utils.strToInt(num21aText.getText()));
			}
			public void removeUpdate(DocumentEvent e) 
			{
				object.setNum21a(bFM.Utils.strToInt(num21aText.getText()));
			}
			public void changedUpdate(DocumentEvent e) {}
		});

		num21bText = new JTextField("" + object.getNum21b());
		num21bText.getDocument().addDocumentListener(new DocumentListener() 
		{
			public void insertUpdate(DocumentEvent e) 
			{
				object.setNum21b(bFM.Utils.strToInt(num21bText.getText()));
			}
			public void removeUpdate(DocumentEvent e) 
			{
				object.setNum21b(bFM.Utils.strToInt(num21bText.getText()));
			}
			public void changedUpdate(DocumentEvent e) {}
		});

		num22aText = new JTextField("" + object.getNum22a());
		num22aText.getDocument().addDocumentListener(new DocumentListener() 
		{
			public void insertUpdate(DocumentEvent e) 
			{
				object.setNum22a(bFM.Utils.strToInt(num22aText.getText()));
			}
			public void removeUpdate(DocumentEvent e) 
			{
				object.setNum22a(bFM.Utils.strToInt(num22aText.getText()));
			}
			public void changedUpdate(DocumentEvent e) {}
		});

		num22bText = new JTextField("" + object.getNum22b());
		num22bText.getDocument().addDocumentListener(new DocumentListener() 
		{
			public void insertUpdate(DocumentEvent e) 
			{
				object.setNum22b(bFM.Utils.strToInt(num22bText.getText()));
			}
			public void removeUpdate(DocumentEvent e) 
			{
				object.setNum22b(bFM.Utils.strToInt(num22bText.getText()));
			}
			public void changedUpdate(DocumentEvent e) {}
		});

		num23aText = new JTextField("" + object.getNum23a());
		num23aText.getDocument().addDocumentListener(new DocumentListener() 
		{
			public void insertUpdate(DocumentEvent e) 
			{
				object.setNum23a(bFM.Utils.strToInt(num23aText.getText()));
			}
			public void removeUpdate(DocumentEvent e) 
			{
				object.setNum23a(bFM.Utils.strToInt(num23aText.getText()));
			}
			public void changedUpdate(DocumentEvent e) {}
		});

		num23bText = new JTextField("" + object.getNum23b());
		num23bText.getDocument().addDocumentListener(new DocumentListener() 
		{
			public void insertUpdate(DocumentEvent e) 
			{
				object.setNum23b(bFM.Utils.strToInt(num23bText.getText()));
			}
			public void removeUpdate(DocumentEvent e) 
			{
				object.setNum23b(bFM.Utils.strToInt(num23bText.getText()));
			}
			public void changedUpdate(DocumentEvent e) {}
		});
		
		num24aText = new JTextField("" + object.getNum24a());
		num24aText.getDocument().addDocumentListener(new DocumentListener() 
		{
			public void insertUpdate(DocumentEvent e) 
			{
				object.setNum24a(bFM.Utils.strToInt(num24aText.getText()));
			}
			public void removeUpdate(DocumentEvent e) 
			{
				object.setNum24a(bFM.Utils.strToInt(num24aText.getText()));
			}
			public void changedUpdate(DocumentEvent e) {}
		});

		num24bText = new JTextField("" + object.getNum24b());
		num24bText.getDocument().addDocumentListener(new DocumentListener() 
		{
			public void insertUpdate(DocumentEvent e) 
			{
				object.setNum24b(bFM.Utils.strToInt(num24bText.getText()));
			}
			public void removeUpdate(DocumentEvent e) 
			{
				object.setNum24b(bFM.Utils.strToInt(num24bText.getText()));
			}
			public void changedUpdate(DocumentEvent e) {}
		});

		num25aText = new JTextField("" + object.getNum25a());
		num25aText.getDocument().addDocumentListener(new DocumentListener() 
		{
			public void insertUpdate(DocumentEvent e) 
			{
				object.setNum25a(bFM.Utils.strToInt(num25aText.getText()));
			}
			public void removeUpdate(DocumentEvent e) 
			{
				object.setNum25a(bFM.Utils.strToInt(num25aText.getText()));
			}
			public void changedUpdate(DocumentEvent e) {}
		});

		num25bText = new JTextField("" + object.getNum25b());
		num25bText.getDocument().addDocumentListener(new DocumentListener() 
		{
			public void insertUpdate(DocumentEvent e) 
			{
				object.setNum25b(bFM.Utils.strToInt(num25bText.getText()));
			}
			public void removeUpdate(DocumentEvent e) 
			{
				object.setNum25b(bFM.Utils.strToInt(num25bText.getText()));
			}
			public void changedUpdate(DocumentEvent e) {}
		});

		num26aText = new JTextField("" + object.getNum26a());
		num26aText.getDocument().addDocumentListener(new DocumentListener() 
		{
			public void insertUpdate(DocumentEvent e) 
			{
				object.setNum26a(bFM.Utils.strToInt(num26aText.getText()));
			}
			public void removeUpdate(DocumentEvent e) 
			{
				object.setNum26a(bFM.Utils.strToInt(num26aText.getText()));
			}
			public void changedUpdate(DocumentEvent e) {}
		});

		num26bText = new JTextField("" + object.getNum26b());
		num26bText.getDocument().addDocumentListener(new DocumentListener() 
		{
			public void insertUpdate(DocumentEvent e) 
			{
				object.setNum26b(bFM.Utils.strToInt(num26bText.getText()));
			}
			public void removeUpdate(DocumentEvent e) 
			{
				object.setNum26b(bFM.Utils.strToInt(num26bText.getText()));
			}
			public void changedUpdate(DocumentEvent e) {}
		});

		num27aText = new JTextField("" + object.getNum27a());
		num27aText.getDocument().addDocumentListener(new DocumentListener() 
		{
			public void insertUpdate(DocumentEvent e) 
			{
				object.setNum27a(bFM.Utils.strToInt(num27aText.getText()));
			}
			public void removeUpdate(DocumentEvent e) 
			{
				object.setNum27a(bFM.Utils.strToInt(num27aText.getText()));
			}
			public void changedUpdate(DocumentEvent e) {}
		});

		num27bText = new JTextField("" + object.getNum27b());
		num27bText.getDocument().addDocumentListener(new DocumentListener() 
		{
			public void insertUpdate(DocumentEvent e) 
			{
				object.setNum27b(bFM.Utils.strToInt(num27bText.getText()));
			}
			public void removeUpdate(DocumentEvent e) 
			{
				object.setNum27b(bFM.Utils.strToInt(num27bText.getText()));
			}
			public void changedUpdate(DocumentEvent e) {}
		});

		num28aText = new JTextField("" + object.getNum28a());
		num28aText.getDocument().addDocumentListener(new DocumentListener() 
		{
			public void insertUpdate(DocumentEvent e) 
			{
				object.setNum28a(bFM.Utils.strToInt(num28aText.getText()));
			}
			public void removeUpdate(DocumentEvent e) 
			{
				object.setNum28a(bFM.Utils.strToInt(num28aText.getText()));
			}
			public void changedUpdate(DocumentEvent e) {}
		});

		num28bText = new JTextField("" + object.getNum28b());
		num28bText.getDocument().addDocumentListener(new DocumentListener() 
		{
			public void insertUpdate(DocumentEvent e) 
			{
				object.setNum28b(bFM.Utils.strToInt(num28bText.getText()));
			}
			public void removeUpdate(DocumentEvent e) 
			{
				object.setNum28b(bFM.Utils.strToInt(num28bText.getText()));
			}
			public void changedUpdate(DocumentEvent e) {}
		});

		num29aText = new JTextField("" + object.getNum29a());
		num29aText.getDocument().addDocumentListener(new DocumentListener() 
		{
			public void insertUpdate(DocumentEvent e) 
			{
				object.setNum29a(bFM.Utils.strToInt(num29aText.getText()));
			}
			public void removeUpdate(DocumentEvent e) 
			{
				object.setNum29a(bFM.Utils.strToInt(num29aText.getText()));
			}
			public void changedUpdate(DocumentEvent e) {}
		});

		num29bText = new JTextField("" + object.getNum29b());
		num29bText.getDocument().addDocumentListener(new DocumentListener() 
		{
			public void insertUpdate(DocumentEvent e) 
			{
				object.setNum29b(bFM.Utils.strToInt(num29bText.getText()));
			}
			public void removeUpdate(DocumentEvent e) 
			{
				object.setNum29b(bFM.Utils.strToInt(num29bText.getText()));
			}
			public void changedUpdate(DocumentEvent e) {}
		});

		num30aText = new JTextField("" + object.getNum30a());
		num30aText.getDocument().addDocumentListener(new DocumentListener() 
		{
			public void insertUpdate(DocumentEvent e) 
			{
				object.setNum30a(bFM.Utils.strToInt(num30aText.getText()));
			}
			public void removeUpdate(DocumentEvent e) 
			{
				object.setNum30a(bFM.Utils.strToInt(num30aText.getText()));
			}
			public void changedUpdate(DocumentEvent e) {}
		});

		num30bText = new JTextField("" + object.getNum30b());
		num30bText.getDocument().addDocumentListener(new DocumentListener() 
		{
			public void insertUpdate(DocumentEvent e) 
			{
				object.setNum30b(bFM.Utils.strToInt(num30bText.getText()));
			}
			public void removeUpdate(DocumentEvent e) 
			{
				object.setNum30b(bFM.Utils.strToInt(num30bText.getText()));
			}
			public void changedUpdate(DocumentEvent e) {}
		});

		num31aText = new JTextField("" + object.getNum31a());
		num31aText.getDocument().addDocumentListener(new DocumentListener() 
		{
			public void insertUpdate(DocumentEvent e) 
			{
				object.setNum31a(bFM.Utils.strToInt(num31aText.getText()));
			}
			public void removeUpdate(DocumentEvent e) 
			{
				object.setNum31a(bFM.Utils.strToInt(num31aText.getText()));
			}
			public void changedUpdate(DocumentEvent e) {}
		});

		num31bText = new JTextField("" + object.getNum31b());
		num31bText.getDocument().addDocumentListener(new DocumentListener() 
		{
			public void insertUpdate(DocumentEvent e) 
			{
				object.setNum31b(bFM.Utils.strToInt(num31bText.getText()));
			}
			public void removeUpdate(DocumentEvent e) 
			{
				object.setNum31b(bFM.Utils.strToInt(num31bText.getText()));
			}
			public void changedUpdate(DocumentEvent e) {}
		});
		
		num32aText = new JTextField("" + object.getNum32a());
		num32aText.getDocument().addDocumentListener(new DocumentListener() 
		{
			public void insertUpdate(DocumentEvent e) 
			{
				object.setNum32a(bFM.Utils.strToInt(num32aText.getText()));
			}
			public void removeUpdate(DocumentEvent e) 
			{
				object.setNum32a(bFM.Utils.strToInt(num32aText.getText()));
			}
			public void changedUpdate(DocumentEvent e) {}
		});

		num32bText = new JTextField("" + object.getNum32b());
		num32bText.getDocument().addDocumentListener(new DocumentListener() 
		{
			public void insertUpdate(DocumentEvent e) 
			{
				object.setNum32b(bFM.Utils.strToInt(num32bText.getText()));
			}
			public void removeUpdate(DocumentEvent e) 
			{
				object.setNum32b(bFM.Utils.strToInt(num32bText.getText()));
			}
			public void changedUpdate(DocumentEvent e) {}
		});

		num33aText = new JTextField("" + object.getNum33a());
		num33aText.getDocument().addDocumentListener(new DocumentListener() 
		{
			public void insertUpdate(DocumentEvent e) 
			{
				object.setNum33a(bFM.Utils.strToInt(num33aText.getText()));
			}
			public void removeUpdate(DocumentEvent e) 
			{
				object.setNum33a(bFM.Utils.strToInt(num33aText.getText()));
			}
			public void changedUpdate(DocumentEvent e) {}
		});

		num33bText = new JTextField("" + object.getNum33b());
		num33bText.getDocument().addDocumentListener(new DocumentListener() 
		{
			public void insertUpdate(DocumentEvent e) 
			{
				object.setNum33b(bFM.Utils.strToInt(num33bText.getText()));
			}
			public void removeUpdate(DocumentEvent e) 
			{
				object.setNum33b(bFM.Utils.strToInt(num33bText.getText()));
			}
			public void changedUpdate(DocumentEvent e) {}
		});

		jobMinimumHPText = new JTextField("" + object.getJobMinHP());
		jobMinimumHPText.getDocument().addDocumentListener(new DocumentListener() 
		{
			public void insertUpdate(DocumentEvent e) 
			{
				object.setJobMinHP(bFM.Utils.strToInt(jobMinimumHPText.getText()));
			}
			public void removeUpdate(DocumentEvent e) 
			{
				object.setJobMinHP(bFM.Utils.strToInt(jobMinimumHPText.getText()));
			}
			public void changedUpdate(DocumentEvent e) {}
		});

		num34bText = new JTextField("" + object.getNum34b());
		num34bText.getDocument().addDocumentListener(new DocumentListener() 
		{
			public void insertUpdate(DocumentEvent e) 
			{
				object.setNum34b(bFM.Utils.strToInt(num34bText.getText()));
			}
			public void removeUpdate(DocumentEvent e) 
			{
				object.setNum34b(bFM.Utils.strToInt(num34bText.getText()));
			}
			public void changedUpdate(DocumentEvent e) {}
		});

		num35aText = new JTextField("" + object.getNum35a());
		num35aText.getDocument().addDocumentListener(new DocumentListener() 
		{
			public void insertUpdate(DocumentEvent e) 
			{
				object.setNum35a(bFM.Utils.strToInt(num35aText.getText()));
			}
			public void removeUpdate(DocumentEvent e) 
			{
				object.setNum35a(bFM.Utils.strToInt(num35aText.getText()));
			}
			public void changedUpdate(DocumentEvent e) {}
		});

		num35bText = new JTextField("" + object.getNum35b());
		num35bText.getDocument().addDocumentListener(new DocumentListener() 
		{
			public void insertUpdate(DocumentEvent e) 
			{
				object.setNum35b(bFM.Utils.strToInt(num35bText.getText()));
			}
			public void removeUpdate(DocumentEvent e) 
			{
				object.setNum35b(bFM.Utils.strToInt(num35bText.getText()));
			}
			public void changedUpdate(DocumentEvent e) {}
		});

		num36aText = new JTextField("" + object.getNum36a());
		num36aText.getDocument().addDocumentListener(new DocumentListener() 
		{
			public void insertUpdate(DocumentEvent e) 
			{
				object.setNum36a(bFM.Utils.strToInt(num36aText.getText()));
			}
			public void removeUpdate(DocumentEvent e) 
			{
				object.setNum36a(bFM.Utils.strToInt(num36aText.getText()));
			}
			public void changedUpdate(DocumentEvent e) {}
		});

		num36bText = new JTextField("" + object.getNum36b());
		num36bText.getDocument().addDocumentListener(new DocumentListener() 
		{
			public void insertUpdate(DocumentEvent e) 
			{
				object.setNum36b(bFM.Utils.strToInt(num36bText.getText()));
			}
			public void removeUpdate(DocumentEvent e) 
			{
				object.setNum36b(bFM.Utils.strToInt(num36bText.getText()));
			}
			public void changedUpdate(DocumentEvent e) {}
		});

		num37aText = new JTextField("" + object.getNum37a());
		num37aText.getDocument().addDocumentListener(new DocumentListener() 
		{
			public void insertUpdate(DocumentEvent e) 
			{
				object.setNum37a(bFM.Utils.strToInt(num37aText.getText()));
			}
			public void removeUpdate(DocumentEvent e) 
			{
				object.setNum37a(bFM.Utils.strToInt(num37aText.getText()));
			}
			public void changedUpdate(DocumentEvent e) {}
		});

		num37bText = new JTextField("" + object.getNum37b());
		num37bText.getDocument().addDocumentListener(new DocumentListener() 
		{
			public void insertUpdate(DocumentEvent e) 
			{
				object.setNum37b(bFM.Utils.strToInt(num37bText.getText()));
			}
			public void removeUpdate(DocumentEvent e) 
			{
				object.setNum37b(bFM.Utils.strToInt(num37bText.getText()));
			}
			public void changedUpdate(DocumentEvent e) {}
		});

		num38aText = new JTextField("" + object.getNum38a());
		num38aText.getDocument().addDocumentListener(new DocumentListener() 
		{
			public void insertUpdate(DocumentEvent e) 
			{
				object.setNum38a(bFM.Utils.strToInt(num38aText.getText()));
			}
			public void removeUpdate(DocumentEvent e) 
			{
				object.setNum38a(bFM.Utils.strToInt(num38aText.getText()));
			}
			public void changedUpdate(DocumentEvent e) {}
		});

		num38bText = new JTextField("" + object.getNum38b());
		num38bText.getDocument().addDocumentListener(new DocumentListener() 
		{
			public void insertUpdate(DocumentEvent e) 
			{
				object.setNum38b(bFM.Utils.strToInt(num38bText.getText()));
			}
			public void removeUpdate(DocumentEvent e) 
			{
				object.setNum38b(bFM.Utils.strToInt(num38bText.getText()));
			}
			public void changedUpdate(DocumentEvent e) {}
		});
		
		num39aText = new JTextField("" + object.getNum39a());
		num39aText.getDocument().addDocumentListener(new DocumentListener() 
		{
			public void insertUpdate(DocumentEvent e) 
			{
				object.setNum39a(bFM.Utils.strToInt(num39aText.getText()));
			}
			public void removeUpdate(DocumentEvent e) 
			{
				object.setNum39a(bFM.Utils.strToInt(num39aText.getText()));
			}
			public void changedUpdate(DocumentEvent e) {}
		});

		attackChargesText = new JTextField("" + object.getAttackCharges());
		attackChargesText.getDocument().addDocumentListener(new DocumentListener() 
		{
			public void insertUpdate(DocumentEvent e) 
			{
				object.setAttackCharges(bFM.Utils.strToInt(attackChargesText.getText()));
			}
			public void removeUpdate(DocumentEvent e) 
			{
				object.setAttackCharges(bFM.Utils.strToInt(attackChargesText.getText()));
			}
			public void changedUpdate(DocumentEvent e) {}
		});

		num40aText = new JTextField("" + object.getNum40a());
		num40aText.getDocument().addDocumentListener(new DocumentListener() 
		{
			public void insertUpdate(DocumentEvent e) 
			{
				object.setNum40a(bFM.Utils.strToInt(num40aText.getText()));
			}
			public void removeUpdate(DocumentEvent e) 
			{
				object.setNum40a(bFM.Utils.strToInt(num40aText.getText()));
			}
			public void changedUpdate(DocumentEvent e) {}
		});

		num40bText = new JTextField("" + object.getNum40b());
		num40bText.getDocument().addDocumentListener(new DocumentListener() 
		{
			public void insertUpdate(DocumentEvent e) 
			{
				object.setNum40b(bFM.Utils.strToInt(num40bText.getText()));
			}
			public void removeUpdate(DocumentEvent e) 
			{
				object.setNum40b(bFM.Utils.strToInt(num40bText.getText()));
			}
			public void changedUpdate(DocumentEvent e) {}
		});

		word4Text = new JTextField(object.getWord4());
		word4Text.getDocument().addDocumentListener(new DocumentListener() 
		{
			public void insertUpdate(DocumentEvent e) 
			{
				object.setWord4(word4Text.getText());
			}
			public void removeUpdate(DocumentEvent e) 
			{
				object.setWord4(word4Text.getText());
			}
			public void changedUpdate(DocumentEvent e) {}
		});

		word5Text = new JTextField(object.getWord5());
		word5Text.getDocument().addDocumentListener(new DocumentListener() 
		{
			public void insertUpdate(DocumentEvent e) 
			{
				object.setWord5(word5Text.getText());
			}
			public void removeUpdate(DocumentEvent e) 
			{
				object.setWord5(word5Text.getText());
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
		layout.fill = GridBagConstraints.HORIZONTAL;
		layout.weighty = 0.0;
		layout.weightx = 1.0;
		add(new LabeledInputBox("Job Code", jobCodeText), layout);
		add(new LabeledInputBox("Num 8", num8Text), layout);
		add(new LabeledInputBox("Handheld Item Code", handheldItemCodeText), layout);
		add(new LabeledInputBox("Helmet Item Code", helmetItemCodeText), layout);
		add(new LabeledInputBox("Num 11", num11Text), layout);
		add(new LabeledInputBox("Num 12", num12Text), layout);
		add(new LabeledInputBox("Word 1", word1Text), layout);
		add(new LabeledInputBox("Word 2", word2Text), layout);
		add(new LabeledInputBox("Name", nameText), layout);
		add(new LabeledInputBox("Num 13A", num13aText), layout);
		add(new LabeledInputBox("Num 13B", num13bText), layout);
		add(new LabeledInputBox("Num 14A", num14aText), layout);
		add(new LabeledInputBox("Num 14B", num14bText), layout);
		add(new LabeledInputBox("Num 15A", num15aText), layout);
		add(new LabeledInputBox("Num 15B", num15bText), layout);
		add(new LabeledInputBox("Num 16A", num16aText), layout);
		add(new LabeledInputBox("Num 16B", num16bText), layout);
		add(new LabeledInputBox("Num 17A", num17aText), layout);
		add(new LabeledInputBox("Num 17B", num17bText), layout);
		add(new LabeledInputBox("Num 18A", num18aText), layout);
		add(new LabeledInputBox("Num 18B", num18bText), layout);
		add(new LabeledInputBox("Num 19A", num19aText), layout);
		add(new LabeledInputBox("Num 19B", num19bText), layout);
		add(new LabeledInputBox("Num 20A", num20aText), layout);
		add(new LabeledInputBox("Num 20B", num20bText), layout);
		add(new LabeledInputBox("Num 21A", num21aText), layout);
		add(new LabeledInputBox("Num 21B", num21bText), layout);
		add(new LabeledInputBox("Num 22A", num22aText), layout);
		add(new LabeledInputBox("Num 22B", num22bText), layout);
		add(new LabeledInputBox("Num 23A", num23aText), layout);
		add(new LabeledInputBox("Num 23B", num23bText), layout);
		add(new LabeledInputBox("Num 24A", num24aText), layout);
		add(new LabeledInputBox("Num 24B", num24bText), layout);
		add(new LabeledInputBox("Num 25A", num25aText), layout);
		add(new LabeledInputBox("Num 25B", num25bText), layout);
		add(new LabeledInputBox("Num 26A", num26aText), layout);
		add(new LabeledInputBox("Num 26B", num26bText), layout);
		add(new LabeledInputBox("Num 27A", num27aText), layout);
		add(new LabeledInputBox("Num 27B", num27bText), layout);
		add(new LabeledInputBox("Num 28A", num28aText), layout);
		add(new LabeledInputBox("Num 28B", num28bText), layout);
		add(new LabeledInputBox("Num 29A", num29aText), layout);
		add(new LabeledInputBox("Num 29B", num29bText), layout);
		add(new LabeledInputBox("Num 30A", num30aText), layout);
		add(new LabeledInputBox("Num 30B", num30bText), layout);
		add(new LabeledInputBox("Num 31A", num31aText), layout);
		add(new LabeledInputBox("Num 31B", num31bText), layout);
		add(new LabeledInputBox("Num 32A", num32aText), layout);
		add(new LabeledInputBox("Num 32B", num32bText), layout);
		add(new LabeledInputBox("Num 33A", num33aText), layout);
		add(new LabeledInputBox("Num 33B", num33bText), layout);
		add(new LabeledInputBox("Job Minimum HP", jobMinimumHPText), layout);
		add(new LabeledInputBox("Num 34B", num34bText), layout);
		add(new LabeledInputBox("Num 35A", num35aText), layout);
		add(new LabeledInputBox("Num 35B", num35bText), layout);
		add(new LabeledInputBox("Num 36A", num36aText), layout);
		add(new LabeledInputBox("Num 36B", num36bText), layout);
		add(new LabeledInputBox("Num 37A", num37aText), layout);
		add(new LabeledInputBox("Num 37B", num37bText), layout);
		add(new LabeledInputBox("Num 38A", num38aText), layout);
		add(new LabeledInputBox("Num 38B", num38bText), layout);
		add(new LabeledInputBox("Num 39A", num39aText), layout);
		add(new LabeledInputBox("Attack Charges", attackChargesText), layout);
		add(new LabeledInputBox("Num 40A", num40aText), layout);
		add(new LabeledInputBox("Num 40B", num40bText), layout);
		add(new LabeledInputBox("Word 4", word4Text), layout);
		
		
		
		
		layout.weighty = 1.0;
		layout.weightx = 1.0;
		add(new LabeledInputBox("Word 5", word5Text), layout);
	}
}