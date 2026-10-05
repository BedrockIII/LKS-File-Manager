package GUI.FileList.SystemData.MapData;

import GUI.FileList.CollapseableFileList;
import SystemDataManagers.MenuDB.MapData.MapArea;
import SystemDataManagers.MenuDB.MapData.MapAreaManager;
import bFM.Settings;

@SuppressWarnings("serial")
public class MapAreaManagerFileList extends CollapseableFileList
{
	MapAreaManager manager;
	public MapAreaManagerFileList(MapAreaManager mapAreaManager, int padding) 
	{
		manager = mapAreaManager;
		this.padding = padding;
		this.file = mapAreaManager;
		initializeAll();
	}
	protected void initializeAll() 
	{
		initializeListGUI("Area Data");
		initializeSubGUI();
		addActions();
		reAddComponents();
	}
	public void initializeSubGUI()
	{
		subEntries.removeAll(subEntries);
		for(MapArea a : manager.getAreas())
		{
			subEntries.add(new MapAreaFileList(a, padding + Settings.indentSize, this));
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
