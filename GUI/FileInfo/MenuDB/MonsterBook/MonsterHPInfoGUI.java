package GUI.FileInfo.MenuDB.MonsterBook;

import java.awt.GridBagConstraints;

import javax.swing.JTextField;

import GUI.LabeledInputBox;
import GUI.FileInfo.GenericFileInfoGUI;
import SystemDataManagers.MenuDB.Books.MonsterBook.MonsterMod;
import bFM.Settings;

@SuppressWarnings("serial")
public class MonsterHPInfoGUI extends GenericFileInfoGUI
{
	MonsterMod file;
	JTextField Name = null;
	JTextField Flag1 = null;
	public MonsterHPInfoGUI(MonsterMod hp) 
	{
		this.file = hp;
		makeGUI();
		addGUI();
	}
	private void addGUI() 
	{
		GridBagConstraints layout = Settings.getDefaultConstraints();
		removeAll();
		add(new LabeledInputBox("Name: ", Name), layout);
		layout.weighty = 1.0;
		add(new LabeledInputBox("Value: ", Flag1), layout);
	}
	private void makeGUI()
	{
		Name = bFM.GUIUtils.createNameTextField(file.getName(), file::setName);
		Flag1 = bFM.GUIUtils.createIntTextField(file.getVal(), file::setVal);
	}
}
