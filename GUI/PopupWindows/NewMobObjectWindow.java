package GUI.PopupWindows;

import javax.swing.JComboBox;
import GUI.LabeledInputBox;
import GUI.FileList.Resources.MOPlacementListGUI.GroupTypesListGUI.GroupsListGUI.GroupListGUI;
import ResourceManagers.MSDBManager.Placement.MobObject.ObjectDefault;

@SuppressWarnings("serial")
public class NewMobObjectWindow extends GenericPopupWindow
{
	GroupListGUI gui;
	JComboBox<ObjectDefault> types;
	public NewMobObjectWindow(GroupListGUI gui)
	{
		super("Create new Object");
		this.gui = gui;
	}
	protected void addGUI()
	{
		types = new JComboBox<ObjectDefault>();
		types.addItem(ObjectDefault.OBJECT);
		types.addItem(ObjectDefault.STILLUMA);
		types.addItem(ObjectDefault.WANDERUMA);
		
		add(new LabeledInputBox("Object Defaults", types));
	}
	protected void execute()
	{
		gui.newObject((ObjectDefault)types.getSelectedItem());
	}
}
