package GUI.FileList.SystemData.ChairMessage;

import java.util.ArrayList;

import javax.swing.JMenuItem;
import javax.swing.filechooser.FileNameExtensionFilter;

import GUI.FileInfo.MenuDB.MenuString.MenuStringInfo;
import GUI.FileList.CollapseableFileList;
import GUI.FileList.FileList;
import SystemDataManagers.MenuDB.MenuStringManager;
import SystemDataManagers.MenuDB.MenuStringManager.MenuString;
import SystemDataManagers.MenuDB.ChairMessage.ChairMessageManager;
import bFM.Settings;

@SuppressWarnings("serial")
public class ThroneMessageFileList extends CollapseableFileList
{
	private int padding = 0;
	private ArrayList<MenuString> Entries = new ArrayList<MenuString>();
	ChairMessageManager manager;
	public ThroneMessageFileList(ChairMessageManager file, int padding) 
	{
		manager = file;
		this.padding = padding;
		this.file = file;
		initializeAll(padding);
	}
	protected void initializeAll(int padding) 
	{
		System.out.print("Opening Chair Message Binary File: █");
		fileTypes = new FileNameExtensionFilter("Chair Message Binary File", "bin");
		initializeListGUI(padding, "Throne Message Manager");
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
		Entries = manager.getEntries();
		for(MenuString object : Entries)
		{
			subEntries.add(new ThroneMessageListGUI(object, padding + Settings.indentSize, this));
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
		JMenuItem newZone = new JMenuItem("Create New Menu String");
		newZone.addActionListener(e -> 
		{
			MenuString entry = new MenuString("New Menu String");
			Entries.add(entry);
			subEntries.add(new MenuStringListGUI(entry, padding + Settings.indentSize, this));
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
