package GUI.PopupWindows;

import javax.swing.JCheckBox;
import javax.swing.JTextField;

import GUI.LabeledInputBox;
import GUI.FileList.Resources.MOPlacementListGUI.GroupCategoriesListGUI.GroupCategoryListGUI;

@SuppressWarnings("serial")
public class SetPlacementClearFlagsPopupWindow extends GenericPopupWindow
{
	GroupCategoryListGUI gui;
	JTextField flag;
	JCheckBox includeNullFlags;
	JCheckBox includeSetFlags;
	JCheckBox includeSetNameFlags;
	public SetPlacementClearFlagsPopupWindow(GroupCategoryListGUI gui)
	{
		super("Set Clear Flag");
		this.gui = gui;
	}
	protected void addGUI()
	{
		flag = new JTextField("-1");
        add(new LabeledInputBox("Starting Flag", flag));
        includeNullFlags = new JCheckBox();
        includeNullFlags.setSelected(true);
        includeSetFlags = new JCheckBox();
        includeSetNameFlags = new JCheckBox();
        includeSetNameFlags.setSelected(true);
        
        add(new LabeledInputBox("Include Flags set to -1", includeNullFlags));
        add(new LabeledInputBox("Include Flags not set to -1", includeSetFlags));
        add(new LabeledInputBox("Set Names by Category", includeSetNameFlags));
	}
	protected void execute()
	{
		int flagInt = bFM.Utils.strToInt(flag.getText());
		boolean nullFlag = includeNullFlags.isSelected();
		boolean setFlag = includeSetFlags.isSelected();
		boolean setNameFlag = includeSetNameFlags.isSelected();
		gui.setPlacementClearFlags(flagInt, nullFlag, setFlag, setNameFlag);
	}
}
