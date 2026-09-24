package GUI.PopupWindows;

import javax.swing.JTextField;

import GUI.LabeledInputBox;
import GUI.FileList.Resources.MOPlacementListGUI.GroupCategoriesListGUI;
import bFM.Utils;

@SuppressWarnings("serial")
public class NewMobGroupTypeWindow extends GenericPopupWindow
{
	GroupCategoriesListGUI gui;
	JTextField code;
	public NewMobGroupTypeWindow(GroupCategoriesListGUI gui)
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
