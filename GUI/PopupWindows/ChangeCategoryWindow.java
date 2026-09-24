package GUI.PopupWindows;

import javax.swing.JComboBox;
import GUI.LabeledInputBox;
import GUI.FileList.Resources.MOPlacementListGUI.GroupCategoriesListGUI.GroupCategoryListGUI.GroupListGUI;
import bFM.GroupCategoryManager;
import bFM.GroupCategoryManager.Category;

@SuppressWarnings("serial")
public class ChangeCategoryWindow extends GenericPopupWindow
{
	GroupListGUI gui;
	JComboBox<Category> CategoryList = new JComboBox<Category>();;
	public ChangeCategoryWindow(GroupListGUI gui)
	{
		super("Set Group Category");
		this.gui = gui;
		
	}
	protected void addGUI()
	{
		for(Category c : GroupCategoryManager.getCategories())
		{
			CategoryList.addItem(c);
		}
		CategoryList.setSelectedItem(GroupCategoryManager.getCategory(gui.data.getGroupCategoryID()));
		add(new LabeledInputBox("Category", CategoryList, 0.5, 1.5));
	}
	protected void execute()
	{
		gui.data.setGroupCategoryID(((Category)CategoryList.getSelectedItem()).id());
		System.out.println("Category ID:" + ((Category)CategoryList.getSelectedItem()).id());
		gui.parent.parent.addGroupGUI(gui);
	}

}
