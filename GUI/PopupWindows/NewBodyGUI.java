package GUI.PopupWindows;

import javax.swing.JTextField;

import GUI.LabeledInputBox;
import GUI.FileList.Resources.CharacterDataBaseList.CharacterResourceAssignmentList;

@SuppressWarnings("serial")
public class NewBodyGUI extends GenericPopupWindow
{
	CharacterResourceAssignmentList gui;
	JTextField newTitle;
	JTextField newTitle2;
	public NewBodyGUI(CharacterResourceAssignmentList gui)
	{
		super("Create Body Definition");
		this.gui = gui;
	}
	protected void addGUI()
	{
	    newTitle = new JTextField(); 
	    add(new LabeledInputBox("New Body Name", newTitle));
	        
	    newTitle2 = new JTextField(); 
	    add(new LabeledInputBox("Job Code", newTitle2));
	}
	protected void execute()
	{
		gui.createBody(newTitle.getText(), bFM.Utils.strToInt(newTitle2.getText()));
	}
}
