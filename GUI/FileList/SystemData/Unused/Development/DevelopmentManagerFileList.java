package GUI.FileList.SystemData.Unused.Development;

import java.util.ArrayList;

import javax.swing.JMenuItem;
import javax.swing.filechooser.FileNameExtensionFilter;

import GUI.FileInfo.MenuDB.Development.DevelopmentManagerFileInfo;
import GUI.FileList.CollapseableFileList;
import SystemDataManagers.MenuDB.Unused.Development.Development;
import SystemDataManagers.MenuDB.Unused.Development.DevelopmentManager;
import bFM.GUIUtils;
import bFM.Settings;

@SuppressWarnings("serial")
public class DevelopmentManagerFileList extends CollapseableFileList
{
	private ArrayList<Development> Entries = new ArrayList<Development>();
	DevelopmentManager manager;
	public DevelopmentManagerFileList(DevelopmentManager file, int padding) 
	{
		manager = file;
		this.padding = padding;
		this.file = file;
		initializeAll();
	}
	protected void initializeAll() 
	{
		System.out.print("Opening (Unused) Development Binary File: █");
		fileTypes = new FileNameExtensionFilter("Development Binary File", "bin");
		initializeListGUI("(Unused) Development Manager");
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
		for(Development object : Entries)
		{
			subEntries.add(new DevelopmentListGUI(object, padding + Settings.indentSize, this));
		}
	}
	protected void initializeInfoGUI()
	{
		infoGUI = new DevelopmentManagerFileInfo(manager);
	}
	protected void addActions() 
	{
		addExportAction();
		addReplaceRawAction();
		addZoneAction();
		add(actions);
		addMouseListener();
	}
	private void addZoneAction()
	{
		JMenuItem newZone = new JMenuItem("Create New Development");
		newZone.addActionListener(e -> 
		{
			Development entry = new Development();
			Entries.add(entry);
			subEntries.add(new DevelopmentListGUI(entry, padding + Settings.indentSize, this));
			reAddComponents();
		});
		actions.add(newZone);
	}
	private void addReplaceRawAction()
	{
		actions.add(GUIUtils.createImportAction("Replace Entries from raw Movie.bin file", "Movie Book Database Binary File", "bin", manager::replaceFromData, this));
	}
	public void removeEntry(DevelopmentListGUI gui) 
	{
		subEntries.remove(gui);
		Entries.remove(gui.file);
		reAddComponents();
	}
}
