package GUI.FileList.SystemData.ChairMessage;

import javax.swing.filechooser.FileNameExtensionFilter;

import GUI.FileList.CollapseableFileList;
import SystemDataManagers.MenuDB.ChairMessage.ChairMessageManager;
import bFM.Settings;

@SuppressWarnings("serial")
public class ThroneMessageFileList extends CollapseableFileList
{
	ChairMessageManager manager;
	public ThroneMessageFileList(ChairMessageManager file, int padding) 
	{
		manager = file;
		this.padding = padding;
		this.file = file;
		initializeAll();
	}
	protected void initializeAll() 
	{
		System.out.print("Opening Chair Message Binary File: █");
		fileTypes = new FileNameExtensionFilter("Chair Message Binary File", "bin");
		initializeListGUI("Throne Message Manager");
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
		subEntries.add(new ThroneSpeakerFileListGUI(manager.getHowserEntries(), padding + Settings.indentSize, "Howser"));
		subEntries.add(new ThroneSpeakerFileListGUI(manager.getVerdeEntries(), padding + Settings.indentSize, "Verde"));
		subEntries.add(new ThroneSpeakerFileListGUI(manager.getLiamEntries(), padding + Settings.indentSize, "Liam"));
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
