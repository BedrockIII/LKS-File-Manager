package GUI.PopupWindows;

import javax.swing.JTextField;

import GUI.LabeledInputBox;
import GUI.FileList.Resources.ItemDatabaseList;
import ResourceManagers.ItemDatabaseManager.Item;

@SuppressWarnings("serial")
public class NewItemPopup extends GenericPopupWindow
{
	ItemDatabaseList gui;
	JTextField newTitle;
	public NewItemPopup(ItemDatabaseList gui) 
	{
		super("Create New Item");
		this.gui = gui;
	}
	protected void addGUI()
	{
		newTitle = new JTextField("New Item");
		add(new LabeledInputBox("New Item Name", newTitle));
	}
	protected void execute()
	{
		gui.addItem(new Item(newTitle.getText()));
	}
}
