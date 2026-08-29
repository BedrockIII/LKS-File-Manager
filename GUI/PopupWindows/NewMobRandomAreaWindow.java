package GUI.PopupWindows;

import javax.swing.JTextField;

import GUI.LabeledInputBox;
import GUI.FileList.Resources.MOPlacementListGUI.RandomAreasListGUI;
import bFM.Utils;

@SuppressWarnings("serial")
public class NewMobRandomAreaWindow extends GenericPopupWindow
{
	RandomAreasListGUI gui;
	JTextField code;
	public NewMobRandomAreaWindow(RandomAreasListGUI gui)
	{
		super("Create new Random Area");
		this.gui = gui;
	}
	protected void addGUI() 
	{
		code = new JTextField("");
	    add(new LabeledInputBox("Area Code", code));
	}
	protected void execute()
	{
		gui.newArea(Utils.strToInt(code.getText()));
	}
	
}

