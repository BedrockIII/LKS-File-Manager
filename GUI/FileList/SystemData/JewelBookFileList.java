package GUI.FileList.SystemData;

import java.util.ArrayList;

import javax.swing.JMenuItem;
import javax.swing.filechooser.FileNameExtensionFilter;

import GUI.GUI;
import GUI.FileInfo.MenuDB.JewelBook.JewelBookListInfoGUI;
import GUI.FileInfo.MenuDB.JewelBook.JewelEntryInfoGUI;
import GUI.FileList.CollapseableFileList;
import GUI.FileList.FileList;
import SystemDataManagers.MenuDB.JewelBookManager;
import SystemDataManagers.MenuDB.JewelBookManager.JewelEntry;
import bFM.GUIUtils;
import bFM.Settings;

@SuppressWarnings("serial")
public class JewelBookFileList extends CollapseableFileList
{
	private int padding = 0;
	private ArrayList<JewelEntry> Entries = new ArrayList<JewelEntry>();
	JewelBookManager manager;
	public JewelBookFileList(JewelBookManager file, int padding) 
	{
		manager = file;
		this.padding = padding;
		this.file = file;
		initializeAll(padding);
	}
	protected void initializeAll(int padding) 
	{
		System.out.print("Opening Jewel Book Binary File: █");
		fileTypes = new FileNameExtensionFilter("Jewel Book Binary File", "bin");
		initializeListGUI(padding, "Jewel Book Manager");
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
		for(JewelEntry object : Entries)
		{
			subEntries.add(new JewelGUI(object, padding + Settings.indentSize, this));
		}
	}
	protected void initializeInfoGUI()
	{
		infoGUI = new JewelBookListInfoGUI(manager);
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
		JMenuItem newZone = new JMenuItem("Create New Jewel");
		newZone.addActionListener(e -> 
		{
			JewelEntry entry = new JewelEntry();
			Entries.add(entry);
			subEntries.add(new JewelGUI(entry, padding + Settings.indentSize, this));
			reAddComponents();
		});
		actions.add(newZone);
	}
	private void addExportBJBAction()
	{
		actions.add(GUIUtils.createExportAction("Export Entries as .bjb text file", "JewelBook.bjb", "Bedrock's Jewel Book Text File", manager::toBAB));
	}
	private void addImportBJBAction()
	{
		actions.add(GUIUtils.createImportAction("Import Entries from .bjb text file", "Bedrock's Jewel Book Text File", "bjb", manager::importFromBJB, this));
	}
	private void addReplaceBJBAction()
	{
		actions.add(GUIUtils.createImportAction("Replace Entries from .bab text file", "Bedrock's Jewel Book Text File", "bjb", manager::replaceFromBJB, this));
	}
	private void addReplaceRawAction()
	{
		actions.add(GUIUtils.createImportAction("Replace Entries from raw Jewel.bin file", "Jewel Book Database Binary File", "bin", manager::replaceFromData, this));
	}
	public class JewelGUI extends FileList
	{
		JewelEntry file;
		int padding;
		JewelBookFileList parent;
		public JewelGUI(JewelEntry entry, int padding, JewelBookFileList parent)
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
			this.infoGUI = new JewelEntryInfoGUI(file);
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
			JMenuItem replace = new JMenuItem("Delete Jewel Entry");
			replace.addActionListener(e -> 
			{
				parent.removeEntry(this);
				GUI.update();
			});
			actions.add(replace);
		}
	}
	public void removeEntry(JewelGUI gui) 
	{
		subEntries.remove(gui);
		Entries.remove(gui.file);
		reAddComponents();
	}
}
