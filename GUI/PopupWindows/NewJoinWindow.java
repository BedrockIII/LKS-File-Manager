package GUI.PopupWindows;

import javax.swing.JTextField;

import GUI.LabeledInputBox;
import GUI.FileList.Resources.CharacterDataBaseList.CharacterJoinList;

@SuppressWarnings("serial")
public class NewJoinWindow extends GenericPopupWindow
{
	CharacterJoinList gui;
	JTextField newTitle;
	JTextField newTitle2;
	public NewJoinWindow(CharacterJoinList gui)
	{
		super("Create New \'Join\'");
		this.gui = gui;
	}
	protected void addGUI()
	{
		newTitle = new JTextField("-1");
		add(new LabeledInputBox("New \'Join\' index", newTitle));
		
		newTitle2 = new JTextField("-1");
		add(new LabeledInputBox("New Job Code", newTitle2));
	}
	protected void execute()
	{
		gui.createJoin(bFM.Utils.strToInt(newTitle.getText()));
	}
}
