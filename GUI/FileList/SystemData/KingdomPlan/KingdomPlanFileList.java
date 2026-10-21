package GUI.FileList.SystemData.KingdomPlan;

import javax.swing.filechooser.FileNameExtensionFilter;

import GUI.FileInfo.MenuDB.KingdomPlan.KingdomPlanAreaSelectorGUI;
import GUI.FileList.CollapseableFileList;
import SystemDataManagers.MenuDB.KingdomPlanManager.KingdomPlanArea;
import SystemDataManagers.MenuDB.KingdomPlanManager.kingdomPlanManager;
import bFM.BITFileList;
import bFM.GUIUtils;

@SuppressWarnings("serial")
public class KingdomPlanFileList extends CollapseableFileList implements BITFileList
{
	kingdomPlanManager planManager;
	public KingdomPlanFileList(kingdomPlanManager file, int padding)
	{
		this.file = file;
		planManager = file;
		this.padding = padding;
		initializeAll();
	}
	protected void initializeAll()
	{
		System.out.print("Opening Kingdom Plan Binary File: █");
		fileTypes = new FileNameExtensionFilter("Kingdom Plan Binary File", "bin");
		initializeListGUI("Kingdom Plan Manager");
		System.out.print("█");
		initializeSubGUI();
		System.out.print("█");
		//initializeInfoGUI();
		System.out.print("█");
		addActions();
		System.out.print("█");
		reAddComponents();
		System.out.println("█ \nComplete!");
	}
	public void initializeSubGUI() 
	{
		subEntries.removeAll(subEntries);
		for(KingdomPlanArea area : planManager.getAreas())
		{
			subEntries.add(new AreaList(area, this));
		}
	}
	protected void initializeInfoGUI() 
	{
		infoGUI = new KingdomPlanAreaSelectorGUI(planManager);
	}
	public void addActions()
	{
		addExportAction();
		addReplaceRawAction();
		addChildAction("Add Area", planManager.getAreas(), KingdomPlanArea::new, AreaList::new);
		add(actions);
		addMouseListener();
	}
	private void addReplaceRawAction()
	{
		actions.add(GUIUtils.createImportAction("Replace Kingdom Plan Data from raw KingdomPlan.bin file", "Kingdom Plan Database Binary File", "bin", planManager::replaceFromBytes, this));
	}
	public void addExportBITAction()
	{
		actions.add(createExportBITAction(planManager));
	}
	public void addImportBITAction()
	{
		actions.add(createImportBITAction(planManager, this));
	}
	public void addReplaceBITAction()
	{
		actions.add(createReplaceBITAction(planManager, this));
	}
}
