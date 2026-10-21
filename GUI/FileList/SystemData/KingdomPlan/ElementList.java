package GUI.FileList.SystemData.KingdomPlan;

import GUI.FileInfo.MenuDB.KingdomPlan.KingdomPlanElementGUI;
import GUI.FileList.FileList;
import SystemDataManagers.MenuDB.KingdomPlanManager.KingdomPlanElement;
import bFM.Settings;

@SuppressWarnings("serial")
public class ElementList extends FileList
{
	/**
	 * 
	 */
	AreaList parent;
	KingdomPlanElement element;
	public ElementList(KingdomPlanElement element, AreaList parent) 
	{
		this.parent = parent;
		this.element = element;
		file = element;
		this.padding = parent.getPadding() + Settings.indentSize;
		initializeAll();
	}
	protected void initializeAll() 
	{
		initializeListGUI("Element: \"" + element.getName() + "\"");
		//initializeInfoGUI();
		addActions();
	}
	protected void initializeInfoGUI() 
	{
		this.infoGUI = new KingdomPlanElementGUI(element);
	}
	protected void addActions() 
	{
		addDeleteAction("Delete Element", parent, parent.area.getElements());
		addMoveUpAction(parent, parent.area.getElements());
		addMoveDownAction(parent, parent.area.getElements());
		add(actions);
		addMouseListener();
	}
	public void update()
	{
		fileName.setText("Element: \"" + element.getName() + "\"");
		super.update();
	}
}