package GUI.FileList.SystemData.ChairMessage;

import javax.swing.filechooser.FileNameExtensionFilter;

import GUI.FileInfo.MenuDB.ChairMessage.ThroneMessageFileInfo;
import GUI.FileList.CollapseableFileList;
import SystemDataManagers.MenuDB.ChairMessage.ChairMessageManager;
import bFM.BITFileList;
import bFM.Settings;

@SuppressWarnings("serial")
public class ThroneMessageFileList extends CollapseableFileList implements BITFileList
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
		infoGUI = new ThroneMessageFileInfo(manager);
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
	public void addExportBITAction()
	{
		//actions.add(createExportBITAction(manager));
	}
	public void addImportBITAction()
	{
		//actions.add(createImportBITAction(manager, this));
	}
	public void addReplaceBITAction()
	{
		//actions.add(createReplaceBITAction(manager, this));
	}
	private void addReplaceRawAction()
	{
		//actions.add(GUIUtils.createImportAction("Replace Entries from raw Humming.bin file", "Humming Book Database Binary File", "bin", manager::replaceFromData, this));
	}
}
