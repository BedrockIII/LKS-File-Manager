package GUI.FileList.SystemData.CameraZone;

import javax.swing.filechooser.FileNameExtensionFilter;

import GUI.FileInfo.MenuDB.CameraZone.CameraZoneListInfoGUI;
import GUI.FileList.CollapseableFileList;
import SystemDataManagers.MenuDB.CameraData.CameraZone;
import SystemDataManagers.MenuDB.CameraData.CameraZoneList;
import bFM.BITFileList;
import bFM.GUIUtils;

@SuppressWarnings("serial")
public class CameraZoneListGUI extends CollapseableFileList implements BITFileList
{
	CameraZoneList manager;
	public CameraZoneListGUI(CameraZoneList file, int padding) 
	{
		manager = file;
		this.padding = padding;
		this.file = file;
		initializeAll();
	}
	protected void initializeAll() 
	{
		System.out.print("Opening Camera Zone Binary File: █");
		fileTypes = new FileNameExtensionFilter("Camera Zone Binary File", "bin");
		initializeListGUI("Camera Zone Manager");
		System.out.print("█");
		initializeSubGUI();
		System.out.print("█");
		//initializeInfoGUI();
		System.out.print("█");
		addActions();
		System.out.print("█");
		reAddComponents();
		System.out.println("█\nComplete!");
	}
	public void initializeSubGUI()
	{
		subEntries.removeAll(subEntries);
		for(CameraZone object : manager.getZones())
		{
			subEntries.add(new CameraZoneGUI(object, this));
		}
	}
	protected void initializeInfoGUI() 
	{
		this.infoGUI = new CameraZoneListInfoGUI(manager);
	}
	protected void addActions() 
	{
		addExportAction();
		addChildAction("Add Camera Zone", manager.getZones(), CameraZone::new, CameraZoneGUI::new);
		addReplaceRawAction();
		addExportBITAction();
		addImportBITAction();
		addReplaceBITAction();
		add(actions);
		addMouseListener();
	}
	private void addReplaceRawAction()
	{
		actions.add(GUIUtils.createImportAction("Replace Zones from raw CameraData.bin file", "Camera Zone Database Binary File", "bin", manager::replaceFromData, this));
	}
	public void addExportBITAction()
	{
		actions.add(createExportBITAction(manager));
	}
	public void addImportBITAction()
	{
		actions.add(createImportBITAction(manager, this));
	}
	public void addReplaceBITAction()
	{
		actions.add(createReplaceBITAction(manager, this));
	}
}
