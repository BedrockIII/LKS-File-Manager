package GUI.FileInfo.MenuDB.Name;

import java.awt.GridLayout;

import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import GUI.FileInfo.GenericFileInfoGUI;
import SystemDataManagers.MenuDB.Name.Keyboard;

@SuppressWarnings("serial")
public class KeyboardInfoGUI extends GenericFileInfoGUI
{
	Keyboard keyboard;
	LetterField[] Letters = new LetterField[80];
	public KeyboardInfoGUI(Keyboard keyboard) 
	{
		this.keyboard = keyboard;
		makeGUI();
		addGUI();
	}
	private void addGUI() 
	{
		removeAll();
		setLayout(new GridLayout(8,10,3,3));
		for(LetterField l : Letters)
			add(l);
	}
	private void makeGUI()
	{
		for(int i = 0; i < 80; i++)
		{
			Letters[i] = new LetterField(keyboard.getLetter(i), i, keyboard);
		}
	}
	private class LetterField extends JTextField
	{
		private LetterField(String text, int index, Keyboard keyboard)
		{
			super(text);
			setHorizontalAlignment(JTextField.CENTER);
			getDocument().addDocumentListener(new DocumentListener()
			{
				public void insertUpdate(DocumentEvent e)
				{
					keyboard.setLetter(index, getText());
				}
				public void removeUpdate(DocumentEvent e)
				{
					keyboard.setLetter(index, getText());
				}
				public void changedUpdate(DocumentEvent e) {}
			});
		}
	}
}
