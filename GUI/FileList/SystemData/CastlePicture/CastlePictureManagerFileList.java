package GUI.FileList.SystemData.CastlePicture;

import java.util.ArrayList;

import javax.swing.JMenuItem;
import javax.swing.filechooser.FileNameExtensionFilter;

import GUI.FileList.CollapseableFileList;
import GUI.FileList.FileList;
import SystemDataManagers.MenuDB.CockpitLogManager;
import SystemDataManagers.MenuDB.LogEntry;
import SystemDataManagers.MenuDB.CastlePicture.CastlePicture;
import SystemDataManagers.MenuDB.CastlePicture.CastlePictureManager;
import bFM.Settings;

@SuppressWarnings("serial")
public class CastlePictureManagerFileList extends CollapseableFileList
{
	private ArrayList<CastlePicture> Entries = new ArrayList<CastlePicture>();
	CastlePictureManager manager;
	public CastlePictureManagerFileList(CastlePictureManager file, int padding) 
	{
		manager = file;
		this.padding = padding;
		this.file = file;
		initializeAll();
	}
	protected void initializeAll() 
	{
		System.out.print("Opening Castle Picture Binary File: █");
		fileTypes = new FileNameExtensionFilter("Castle Picture Binary File", "bin");
		initializeListGUI("Castle Picture Manager");
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
		Entries = manager.getEntries();
		for(CastlePicture object : Entries)
		{
			subEntries.add(new CastlePictureFileList(object, padding + Settings.indentSize, this));
		}
	}
	protected void initializeInfoGUI()
	{
		//TODO
		//infoGUI = new HummingBookListInfoGUI(manager);
	}
	protected void addActions() 
	{
		addExportAction();
		addReplaceRawAction();
		addExportBJBAction();
		addImportBJBAction();
		addReplaceBJBAction();
		addZoneAction();
		add(actions);
		addMouseListener();
	}
	private void addZoneAction()
	{
		JMenuItem newZone = new JMenuItem("Create New Castle Picture");
		newZone.addActionListener(e -> 
		{
			CastlePicture entry = new CastlePicture();
			Entries.add(entry);
			subEntries.add(new CastlePictureFileList(entry, padding + Settings.indentSize, this));
			reAddComponents();
		});
		//actions.add(newZone);
	}
	private void addExportBJBAction()
	{
		//actions.add(GUIUtils.createExportAction("Export Entries as .bit text file", "HummingBook.bit", "Bedrock's Intermediate Text File", manager::toIntermediateText));
	}
	private void addImportBJBAction()
	{
		//actions.add(GUIUtils.createImportAction("Import Entries from .bit text file", "Bedrock's Intermediate Text File", "bit", manager::importFromIntermediateText, this));
	}
	private void addReplaceBJBAction()
	{
		//actions.add(GUIUtils.createImportAction("Replace Entries from .bit text file", "Bedrock's Intermediate Text File", "bit", manager::replaceFromIntermediateText, this));
	}
	private void addReplaceRawAction()
	{
		//actions.add(GUIUtils.createImportAction("Replace Entries from raw Humming.bin file", "Humming Book Database Binary File", "bin", manager::replaceFromData, this));
	}
}
