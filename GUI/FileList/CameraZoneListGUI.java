package GUI.FileList;

import java.util.ArrayList;

import javax.swing.JMenuItem;
import javax.swing.filechooser.FileNameExtensionFilter;

import GUI.GUI;
import GUI.FileInfo.MenuDB.CameraZone.CameraZoneInfoGUI;
import GUI.FileInfo.MenuDB.CameraZone.CameraZoneListInfoGUI;
import SystemDataManagers.MenuDB.CameraData.CameraZone;
import SystemDataManagers.MenuDB.CameraData.CameraZoneList;
import bFM.Settings;
import bFM.Utils;

@SuppressWarnings("serial")
public class CameraZoneListGUI extends CollapseableFileList
{
	private int padding = 0;
	private ArrayList<CameraZone> cameraZones = new ArrayList<CameraZone>();
	CameraZoneList manager;
	public CameraZoneListGUI(CameraZoneList file, int padding) 
	{
		manager = file;
		this.padding = padding;
		this.file = file;
		initializeAll(padding);
	}
	protected void initializeAll(int padding) 
	{
		System.out.print("Opening Camera Zone Binary File: █");
		fileTypes = new FileNameExtensionFilter("Camera Zone Binary File", "bin");
		initializeListGUI(padding, "Camera Zone Manager");
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
		cameraZones = manager.getZones();
		for(CameraZone object : cameraZones)
		{
			subEntries.add(new CameraZoneGUI(object, padding + Settings.indentSize, this));
		}
	}
	protected void initializeInfoGUI() 
	{
		this.infoGUI = new CameraZoneListInfoGUI(manager);
	}
	protected void addActions() 
	{
		//TODO add Zone, Export BCZ, Import BCZ, replace
		addExportAction();
		addReplaceRawAction();
		addExportBCZAction();
		addImportBCZAction();
		addReplaceBCZAction();
		addZoneAction();
		add(actions);
		addMouseListener();
	}
	private void addZoneAction()
	{
		JMenuItem newZone = new JMenuItem("Create New Zone");
		newZone.addActionListener(e -> 
		{
			CameraZone zone = new CameraZone();
			cameraZones.add(zone);
			subEntries.add(new CameraZoneGUI(zone, padding + Settings.indentSize, this));
			reAddComponents();
		});
		actions.add(newZone);
	}
	private void addExportBCZAction()
	{
		actions.add(Utils.createExportAction("Export Zones as .bcz text file", "CameraZones.bcz", "Bedrock's Camera Zone Text File", manager::toBCZ));
	}
	private void addImportBCZAction()
	{
		actions.add(Utils.createImportAction("Import Zones from .bcz text file", "Bedrock's Camera Zone Text File", "bcz", manager::importFromBCZ, this));
	}
	private void addReplaceBCZAction()
	{
		actions.add(Utils.createImportAction("Replace Zones from .bcz text file", "Bedrock's Camera Zone Text File", "bcz", manager::replaceFromBCZ, this));
	}
	private void addReplaceRawAction()
	{
		actions.add(Utils.createImportAction("Replace Zones from raw CameraData.bin file", "Camera Zone Database Binary File", "bin", manager::replaceFromData, this));
	}
	public class CameraZoneGUI extends FileList
	{
		CameraZone file;
		int padding;
		CameraZoneListGUI parent;
		public CameraZoneGUI(CameraZone file, int padding, CameraZoneListGUI parent) 
		{
			this.padding = padding;
			this.file = file;
			this.parent = parent;
			initializeAll(padding);
		}
		protected void initializeAll(int padding) 
		{
			initializeListGUI(padding, file.getName());
			initializeInfoGUI();
			addActions();
		}
		protected void initializeInfoGUI() 
		{
			this.infoGUI = new CameraZoneInfoGUI(file);
		}
		protected void addActions() 
		{
			addDeleteAction();
			add(actions);
			addMouseListener();
		}
		protected void addDeleteAction()
		{
			JMenuItem replace = new JMenuItem("Delete Zone");
			replace.addActionListener(e -> 
			{
				parent.removeZone(this);
				GUI.update();
			});
			actions.add(replace);
		}
		public void update()
		{
			fileName.setText(file.getName());
			super.update();
		}
	}
	public void removeZone(CameraZoneGUI cameraZoneGUI) 
	{
		subEntries.remove(cameraZoneGUI);
		cameraZones.remove(cameraZoneGUI.file);
		reAddComponents();
	}
}
