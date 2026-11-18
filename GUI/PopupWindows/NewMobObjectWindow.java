package GUI.PopupWindows;

import java.awt.Dimension;

import javax.swing.JComboBox;
import javax.swing.JTextField;

import GUI.LabeledInputBox;
import GUI.FileList.Resources.MOPlacementListGUI.GroupCategoriesListGUI.GroupCategoryListGUI.GroupListGUI;
import ResourceManagers.MSDBManager.Placement.MobObject.ObjectDefault;
import bFM.GUIUtils;
import bFM.Settings;

@SuppressWarnings("serial")
public class NewMobObjectWindow extends GenericPopupWindow
{
	static GroupListGUI gui;
	static JComboBox<ObjectDefault> types;
	static JTextField modID;
	static int id = 0;
	static LabeledInputBox modIDLabel;
	static boolean initialized = false;
	public NewMobObjectWindow(GroupListGUI gui)
	{
		super("Create new Object");
		NewMobObjectWindow.gui = gui;
		setModID(1);
	}
	protected void addGUI()
	{
		if(!initialized)
		{
			types = new JComboBox<ObjectDefault>();
			types.addItem(ObjectDefault.OBJECT);
			types.addItem(ObjectDefault.STILLUMA);
			types.addItem(ObjectDefault.WANDERUMA);
			
			modID = GUIUtils.createIntTextField(1, this::setModID);
			
			modIDLabel = new LabeledInputBox("Mod Code", modID);
			modIDLabel.setMinimumSize(new Dimension(Settings.buttonWidth * 3, Settings.assetHeight));
			initialized = true;
		}
		
		add(modIDLabel);
		add(new LabeledInputBox("Object Defaults", types));
	}
	private void setModID(int id)
	{
		NewMobObjectWindow.id = id;
		String name = "Mod Code: (" + gui.parent.parent.parent.parent.MonsterDataPack.getModCodeByName(id) + ")";
		modIDLabel.replaceText(name);
	}
	protected void execute()
	{
		gui.newObject((ObjectDefault)types.getSelectedItem(), id);
	}
}
