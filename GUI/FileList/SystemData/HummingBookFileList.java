package GUI.FileList.SystemData;

import java.util.ArrayList;

import javax.swing.JMenuItem;
import javax.swing.filechooser.FileNameExtensionFilter;

import GUI.GUI;
import GUI.FileInfo.MenuDB.HummingBook.HummingBookListInfoGUI;
import GUI.FileInfo.MenuDB.HummingBook.HummingEntryInfoGUI;
import GUI.FileList.CollapseableFileList;
import GUI.FileList.FileList;
import SystemDataManagers.MenuDB.Books.HummingBookEntry;
import SystemDataManagers.MenuDB.Books.HummingBookManager;
import bFM.GUIUtils;
import bFM.Settings;

@SuppressWarnings("serial")
public class HummingBookFileList extends CollapseableFileList
{
	private int padding = 0;
	private ArrayList<HummingBookEntry> Entries = new ArrayList<HummingBookEntry>();
	HummingBookManager manager;
	public HummingBookFileList(HummingBookManager file, int padding) 
	{
		manager = file;
		this.padding = padding;
		this.file = file;
		initializeAll(padding);
	}
	protected void initializeAll(int padding) 
	{
		System.out.print("Opening Humming Book Binary File: █");
		fileTypes = new FileNameExtensionFilter("Humming Book Binary File", "bin");
		initializeListGUI(padding, "Humming Book Manager");
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
		for(HummingBookEntry object : Entries)
		{
			subEntries.add(new HumGUI(object, padding + Settings.indentSize, this));
		}
	}
	protected void initializeInfoGUI()
	{
		infoGUI = new HummingBookListInfoGUI(manager);
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
		JMenuItem newZone = new JMenuItem("Create New Hum");
		newZone.addActionListener(e -> 
		{
			HummingBookEntry entry = new HummingBookEntry();
			Entries.add(entry);
			subEntries.add(new HumGUI(entry, padding + Settings.indentSize, this));
			reAddComponents();
		});
		actions.add(newZone);
	}
	private void addExportBJBAction()
	{
		actions.add(GUIUtils.createExportAction("Export Entries as .bit text file", "HummingBook.bit", "Bedrock's Intermediate Text File", manager::toIntermediateText));
	}
	private void addImportBJBAction()
	{
		actions.add(GUIUtils.createImportAction("Import Entries from .bit text file", "Bedrock's Intermediate Text File", "bit", manager::importFromIntermediateText, this));
	}
	private void addReplaceBJBAction()
	{
		actions.add(GUIUtils.createImportAction("Replace Entries from .bit text file", "Bedrock's Intermediate Text File", "bit", manager::replaceFromIntermediateText, this));
	}
	private void addReplaceRawAction()
	{
		actions.add(GUIUtils.createImportAction("Replace Entries from raw Humming.bin file", "Humming Book Database Binary File", "bin", manager::replaceFromData, this));
	}
	public class HumGUI extends FileList
	{
		HummingBookEntry file;
		int padding;
		HummingBookFileList parent;
		public HumGUI(HummingBookEntry entry, int padding, HummingBookFileList parent)
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
			this.infoGUI = new HummingEntryInfoGUI(file);
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
			JMenuItem replace = new JMenuItem("Delete Hum Entry");
			replace.addActionListener(e -> 
			{
				parent.removeEntry(this);
				GUI.update();
			});
			actions.add(replace);
		}
	}
	public void removeEntry(HumGUI gui) 
	{
		subEntries.remove(gui);
		Entries.remove(gui.file);
		reAddComponents();
	}
}
