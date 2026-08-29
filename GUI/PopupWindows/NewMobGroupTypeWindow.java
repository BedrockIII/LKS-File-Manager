package GUI.PopupWindows;

import javax.swing.JTextField;

import GUI.LabeledInputBox;
import GUI.FileList.Resources.MOPlacementListGUI.GroupTypesListGUI;
import bFM.Utils;

@SuppressWarnings("serial")
public class NewMobGroupTypeWindow extends GenericPopupWindow
{
	GroupTypesListGUI gui;
	JTextField code;
	public NewMobGroupTypeWindow(GroupTypesListGUI gui)
	{
		super("Create new Group Category");
		this.gui = gui;
	}
	protected void addGUI()
	{
		code = new JTextField("");
		add(new LabeledInputBox("Group Code", code));
	}
	protected void execute()
	{
		gui.newGroupCategory(Utils.strToInt(code.getText()));
	}
}
