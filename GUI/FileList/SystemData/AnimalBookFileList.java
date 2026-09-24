package GUI.FileList.SystemData;

import java.util.ArrayList;

import javax.swing.JMenuItem;
import javax.swing.filechooser.FileNameExtensionFilter;

import GUI.GUI;
import GUI.FileInfo.MenuDB.AnimalBook.AnimalBookListInfoGUI;
import GUI.FileInfo.MenuDB.AnimalBook.AnimalEntryInfoGUI;
import GUI.FileList.CollapseableFileList;
import GUI.FileList.FileList;
import SystemDataManagers.MenuDB.AnimalManager;
import SystemDataManagers.MenuDB.AnimalManager.AnimalEntry;
import bFM.GUIUtils;
import bFM.Settings;

@SuppressWarnings("serial")
public class AnimalBookFileList extends CollapseableFileList
{
	private int padding = 0;
	private ArrayList<AnimalEntry> Entries = new ArrayList<AnimalEntry>();
	AnimalManager manager;
	public AnimalBookFileList(AnimalManager file, int padding) 
	{
		manager = file;
		this.padding = padding;
		this.file = file;
		initializeAll(padding);
	}
	protected void initializeAll(int padding) 
	{
		System.out.print("Opening Animal Book Binary File: █");
		fileTypes = new FileNameExtensionFilter("Animal Book Binary File", "bin");
		initializeListGUI(padding, "Animal Book Manager");
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
		for(AnimalEntry object : Entries)
		{
			subEntries.add(new AnimalGUI(object, padding + Settings.indentSize, this));
		}
	}
	protected void initializeInfoGUI()
	{
		infoGUI = new AnimalBookListInfoGUI(manager);
	}
	protected void addActions() 
	{
		addExportAction();
		addReplaceRawAction();
		addExportBABAction();
		addImportBABAction();
		addReplaceBABAction();
		addZoneAction();
		add(actions);
		addMouseListener();
	}
	private void addZoneAction()
	{
		JMenuItem newZone = new JMenuItem("Create New Animal");
		newZone.addActionListener(e -> 
		{
			AnimalEntry entry = new AnimalEntry();
			Entries.add(entry);
			subEntries.add(new AnimalGUI(entry, padding + Settings.indentSize, this));
			reAddComponents();
		});
		actions.add(newZone);
	}
	private void addExportBABAction()
	{
		actions.add(GUIUtils.createExportAction("Export Entries as .bab text file", "AnimalBook.bab", "Bedrock's Animal Book Text File", manager::toBAB));
	}
	private void addImportBABAction()
	{
		actions.add(GUIUtils.createImportAction("Import Entries from .bab text file", "Bedrock's Animal Book Text File", "bab", manager::importFromBAB, this));
	}
	private void addReplaceBABAction()
	{
		actions.add(GUIUtils.createImportAction("Replace Entries from .bab text file", "Bedrock's Animal Book Text File", "bab", manager::replaceFromBAB, this));
	}
	private void addReplaceRawAction()
	{
		actions.add(GUIUtils.createImportAction("Replace Entries from raw AnimalBook.bin file", "Animal Book Database Binary File", "bin", manager::replaceFromData, this));
	}
	public class AnimalGUI extends FileList
	{
		AnimalEntry file;
		int padding;
		AnimalBookFileList parent;
		public AnimalGUI(AnimalEntry entry, int padding, AnimalBookFileList parent)
		{
			this.padding = padding;
			this.file = entry;
			this.parent = parent;
			initializeAll(padding);
		}
		protected void initializeAll(int padding) 
		{
			initializeListGUI(padding, "Entry: \"" + file.getName() + "\"");
			initializeInfoGUI();
			addActions();
		}
		protected void initializeInfoGUI() 
		{
			this.infoGUI = new AnimalEntryInfoGUI(file);
		}
		protected void addActions() 
		{
			addDeleteAction();
			add(actions);
			addMouseListener();
		}
		public void update()
		{
			fileName.setText("Entry: \"" + file.getName() + "\"");
			super.update();
		}
		protected void addDeleteAction()
		{
			JMenuItem replace = new JMenuItem("Delete Animal Entry");
			replace.addActionListener(e -> 
			{
				parent.removeEntry(this);
				GUI.update();
			});
			actions.add(replace);
		}
	}
	public void removeEntry(AnimalGUI gui) 
	{
		subEntries.remove(gui);
		Entries.remove(gui.file);
		reAddComponents();
	}
}
