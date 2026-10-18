package GUI.FileList.SystemData.WonderSpot;

import java.util.ArrayList;

import javax.swing.filechooser.FileNameExtensionFilter;

import GUI.FileInfo.MenuDB.WonderSpot.WonderSpotListInfoGUI;
import GUI.FileList.CollapseableFileList;
import SystemDataManagers.MenuDB.Books.WonderSpotBook.WonderSpot;
import SystemDataManagers.MenuDB.Books.WonderSpotBook.WonderSpotManager;
import bFM.BITFileList;
import bFM.GUIUtils;

@SuppressWarnings("serial")
public class WonderSpotFileList extends CollapseableFileList implements BITFileList
{
	private ArrayList<WonderSpot> Spots = new ArrayList<WonderSpot>();
	WonderSpotManager manager;
	public WonderSpotFileList(WonderSpotManager file, int padding) 
	{
		manager = file;
		this.padding = padding;
		this.file = file;
		initializeAll();
	}
	protected void initializeAll() 
	{
		System.out.print("Opening Wonder Spot Binary File: █");
		fileTypes = new FileNameExtensionFilter("Wonder Spot Binary File", "bin");
		initializeListGUI("Wonder Spot Manager");
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
		Spots = manager.getSpots();
		for(WonderSpot object : Spots)
		{
			subEntries.add(new WonderSpotGUI(object, this));
		}
	}
	protected void initializeInfoGUI()
	{
		infoGUI = new WonderSpotListInfoGUI(manager);
	}
	protected void addActions() 
	{
		addExportAction();
		addChildAction("Add Wonder Spot", manager.getSpots(), WonderSpot::new, WonderSpotGUI::new);
		addReplaceRawAction();
		addExportBITAction();
		addImportBITAction();
		addReplaceBITAction();
		add(actions);
		addMouseListener();
	}
	private void addReplaceRawAction()
	{
		actions.add(GUIUtils.createImportAction("Replace Spots from raw Album.bin file", "Wonder Spot Database Binary File", "bin", manager::replaceFromData, this));
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
