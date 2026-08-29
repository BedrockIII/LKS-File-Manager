package GUI.PopupWindows;

import javax.swing.JTextField;

import GUI.LabeledInputBox;
import GUI.FileList.Resources.CharacterDataBaseList.CharacterIndexList;

@SuppressWarnings("serial")
public class NewIndexWindow extends GenericPopupWindow
{
	CharacterIndexList gui;
	JTextField newTitle;
	JTextField newTitle2;
	public NewIndexWindow(CharacterIndexList gui)
	{
		super("Create Job Index");  
		this.gui = gui;
	}
	protected void addGUI()
	{
		newTitle = new JTextField("New Job");
		add(new LabeledInputBox("New Job Name", newTitle));
		
		newTitle2 = new JTextField("-1");
		add(new LabeledInputBox("New Job Code", newTitle2));
	}
	protected void execute()
	{
		gui.createIndex(newTitle.getText(), bFM.Utils.strToInt(newTitle2.getText()));
	}
}