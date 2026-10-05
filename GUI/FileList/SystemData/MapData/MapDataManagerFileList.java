package GUI.FileList.SystemData.MapData;

import javax.swing.filechooser.FileNameExtensionFilter;

import GUI.FileList.CollapseableFileList;
import SystemDataManagers.MenuDB.MapData.MapDataManager;
import bFM.Settings;

@SuppressWarnings("serial")
public class MapDataManagerFileList extends CollapseableFileList
{
	MapDataManager manager;
	public MapDataManagerFileList(MapDataManager file, int padding) 
	{
		manager = file;
		this.padding = padding;
		this.file = file;
		initializeAll();
	}
	protected void initializeAll() 
	{
		System.out.print("Opening Map Data Binary File: █");
		fileTypes = new FileNameExtensionFilter("Map Data Binary File", "bin");
		initializeListGUI("Map Data Manager");
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
		subEntries.add(new MapPOIManagerFileList(manager.getPOILevel0(), padding + Settings.indentSize, "Level 0"));
		subEntries.add(new MapPOIManagerFileList(manager.getPOILevel1(), padding + Settings.indentSize, "Level 1"));
		subEntries.add(new MapPOIManagerFileList(manager.getPOILevel2(), padding + Settings.indentSize, "Level 2"));
		subEntries.add(new MapPOIManagerFileList(manager.getPOILevel3(), padding + Settings.indentSize, "Level 3"));
		subEntries.add(new MapAreaManagerFileList(manager.getAreaData(), padding + Settings.indentSize));
		subEntries.add(new MapQuestManagerFileList(manager.getQuestData(), padding + Settings.indentSize));
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
		addExportBITAction();
		addImportBITAction();
		addReplaceBITAction();
		add(actions);
		addMouseListener();
	}
	private void addExportBITAction()
	{
		//actions.add(GUIUtils.createExportAction("Export Entries as .bit text file", "HummingBook.bit", "Bedrock's Intermediate Text File", manager::toIntermediateText));
	}
	private void addImportBITAction()
	{
		//actions.add(GUIUtils.createImportAction("Import Entries from .bit text file", "Bedrock's Intermediate Text File", "bit", manager::importFromIntermediateText, this));
	}
	private void addReplaceBITAction()
	{
		//actions.add(GUIUtils.createImportAction("Replace Entries from .bit text file", "Bedrock's Intermediate Text File", "bit", manager::replaceFromIntermediateText, this));
	}
	private void addReplaceRawAction()
	{
		//actions.add(GUIUtils.createImportAction("Replace Entries from raw Humming.bin file", "Humming Book Database Binary File", "bin", manager::replaceFromData, this));
	}
}
