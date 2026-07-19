package GUI.FileList.SystemData;

import java.util.ArrayList;

import javax.swing.JMenuItem;
import javax.swing.filechooser.FileNameExtensionFilter;

import GUI.GUI;
import GUI.FileInfo.MenuDB.WonderSpot.WonderSpotInfoGUI;
import GUI.FileInfo.MenuDB.WonderSpot.WonderSpotListInfoGUI;
import GUI.FileList.CollapseableFileList;
import GUI.FileList.FileList;
import SystemDataManagers.MenuDB.WonderSpotManager;
import SystemDataManagers.MenuDB.WonderSpotManager.WonderSpot;
import bFM.GUIUtils;
import bFM.Settings;

@SuppressWarnings("serial")
public class WonderSpotFileList extends CollapseableFileList
{
	private int padding = 0;
	private ArrayList<WonderSpot> Spots = new ArrayList<WonderSpot>();
	WonderSpotManager manager;
	public WonderSpotFileList(WonderSpotManager file, int padding) 
	{
		manager = file;
		this.padding = padding;
		this.file = file;
		initializeAll(padding);
	}
	protected void initializeAll(int padding) 
	{
		System.out.print("Opening Wonder Spot Binary File: █");
		fileTypes = new FileNameExtensionFilter("Wonder Spot Binary File", "bin");
		initializeListGUI(padding, "Wonder Spot Manager");
		System.out.print("█");
		initializeSubGUI();
		System.out.print("█");
		initializeInfoGUI();
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
			subEntries.add(new WonderSpotGUI(object, padding + Settings.indentSize, this));
		}
	}
	protected void initializeInfoGUI()
	{
		infoGUI = new WonderSpotListInfoGUI(manager);
	}
	protected void addActions() 
	{
		addExportAction();
		addReplaceRawAction();
		addExportBWSAction();
		addImportBWSAction();
		addReplaceBWSAction();
		addZoneAction();
		add(actions);
		addMouseListener();
	}
	private void addZoneAction()
	{
		JMenuItem newZone = new JMenuItem("Create New Wonder Spot");
		newZone.addActionListener(e -> 
		{
			WonderSpot spot = new WonderSpot();
			Spots.add(spot);
			subEntries.add(new WonderSpotGUI(spot, padding + Settings.indentSize, this));
			reAddComponents();
		});
		actions.add(newZone);
	}
	private void addExportBWSAction()
	{
		actions.add(GUIUtils.createExportAction("Export Spots as .bws text file", "WonderSpot.bws", "Bedrock's Wonder Spot Text File", manager::toBWS));
	}
	private void addImportBWSAction()
	{
		actions.add(GUIUtils.createImportAction("Import Spots from .bws text file", "Bedrock's Wonder Spot Text File", "bws", manager::importFromBWS, this));
	}
	private void addReplaceBWSAction()
	{
		actions.add(GUIUtils.createImportAction("Replace Spots from .bws text file", "Bedrock's Wonder Spot Text File", "bws", manager::replaceFromBWS, this));
	}
	private void addReplaceRawAction()
	{
		actions.add(GUIUtils.createImportAction("Replace Spots from raw Album.bin file", "Wonder Spot Database Binary File", "bin", manager::replaceFromData, this));
	}
	public class WonderSpotGUI extends FileList
	{
		WonderSpot file;
		int padding;
		WonderSpotFileList parent;
		public WonderSpotGUI(WonderSpot file, int padding, WonderSpotFileList parent)
		{
			this.padding = padding;
			this.file = file;
			this.parent = parent;
			initializeAll(padding);
		}
		protected void initializeAll(int padding) 
		{
			initializeListGUI(padding, "Spot: \"" + file.getName() + "\"");
			initializeInfoGUI();
			addActions();
		}
		protected void initializeInfoGUI() 
		{
			this.infoGUI = new WonderSpotInfoGUI(file);
		}
		protected void addActions() 
		{
			addDeleteAction();
			add(actions);
			addMouseListener();
		}
		public void update()
		{
			fileName.setText("Spot: \"" + file.getName() + "\"");
			super.update();
		}
		protected void addDeleteAction()
		{
			JMenuItem replace = new JMenuItem("Delete Wonder Spot");
			replace.addActionListener(e -> 
			{
				parent.removeSpot(this);
				GUI.update();
			});
			actions.add(replace);
		}
	}
	public void removeSpot(WonderSpotGUI wonderSpotGUI) 
	{
		subEntries.remove(wonderSpotGUI);
		Spots.remove(wonderSpotGUI.file);
		reAddComponents();
	}
}
