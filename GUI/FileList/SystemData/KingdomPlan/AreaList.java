package GUI.FileList.SystemData.KingdomPlan;

import java.util.ArrayList;

import javax.swing.JMenuItem;

import GUI.FileInfo.MenuDB.KingdomPlan.KingdomPlanAreaGUI;
import GUI.FileList.CollapseableFileList;
import SystemDataManagers.MenuDB.KingdomPlanManager.KingdomPlanArea;
import SystemDataManagers.MenuDB.KingdomPlanManager.KingdomPlanElement;
import bFM.Settings;

@SuppressWarnings("serial")
public class AreaList extends CollapseableFileList
{
	KingdomPlanFileList parent;
	KingdomPlanArea area;
	ArrayList<KingdomPlanElement> elements;
	JMenuItem moveUp = null;
	JMenuItem moveDown = null;
	public AreaList(KingdomPlanArea area, KingdomPlanFileList parent) 
	{
		this.parent = parent;
		this.area = area;
		this.padding = parent.getPadding() + Settings.indentSize;
		file = area;
		initializeAll();
	}
	protected void initializeAll() 
	{
		initializeListGUI("Area: \"" + area.getName() + "\"");
		//initializeInfoGUI();
		initializeSubGUI();
		addActions();
		reAddComponents();
	}
	public void initializeSubGUI() 
	{
		subEntries.removeAll(subEntries);
		elements = area.getElements();
		for(KingdomPlanElement element : elements)
		{
			subEntries.add(new ElementList(element, this));
		}
	}
	protected void initializeInfoGUI() 
	{
		this.infoGUI = new KingdomPlanAreaGUI(area);
	}
	protected void addActions() 
	{
		addDeleteAction("Delete Area", parent, parent.planManager.getAreas());
		addMoveUpAction(parent, parent.planManager.getAreas());
		addMoveDownAction(parent, parent.planManager.getAreas());
		addChildAction("Add Element", area.getElements(), KingdomPlanElement::new, ElementList::new);
		add(actions);
		addMouseListener();
	}
	public void update()
	{
		fileName.setText("Area: \"" + area.getName() + "\"");
		super.update();
	}
}