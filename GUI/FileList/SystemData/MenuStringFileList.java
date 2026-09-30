package GUI.FileList.SystemData;

import java.util.ArrayList;

import javax.swing.JMenuItem;
import javax.swing.filechooser.FileNameExtensionFilter;

import GUI.FileInfo.MenuDB.MenuString.MenuStringInfo;
import GUI.FileList.CollapseableFileList;
import GUI.FileList.FileList;
import SystemDataManagers.MenuDB.MenuStringManager;
import SystemDataManagers.MenuDB.MenuStringManager.MenuString;
import bFM.Settings;

@SuppressWarnings("serial")
public class MenuStringFileList extends CollapseableFileList
{
	private ArrayList<MenuString> Entries = new ArrayList<MenuString>();
	MenuStringManager manager;
	public MenuStringFileList(MenuStringManager file, int padding) 
	{
		manager = file;
		this.padding = padding;
		this.file = file;
		initializeAll();
	}
	protected void initializeAll() 
	{
		System.out.print("Opening Menu String Binary File: █");
		fileTypes = new FileNameExtensionFilter("Menu String Binary File", "bin");
		initializeListGUI("Menu String Manager");
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
			subEntries.add(new MenuStringListGUI(object, padding + Settings.indentSize, this));
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
	public class MenuStringListGUI extends FileList
	{
		MenuString file;
		MenuStringFileList parent;
		public MenuStringListGUI(MenuString entry, int padding, MenuStringFileList parent)
		{
			this.padding = padding;
			this.file = entry;
			this.parent = parent;
			initializeAll();
		}
		protected void initializeAll() 
		{
			initializeListGUI("\""+ file.getText() + "\"");
			initializeInfoGUI();
			addActions();
		}
		protected void initializeInfoGUI() 
		{
			this.infoGUI = new MenuStringInfo(file);
		}
		protected void addActions() 
		{
			add(actions);
			addMouseListener();
		}
		public void update()
		{
			fileName.setText("\""+ file.getText() + "\"");
			super.update();
		}
	}
}
