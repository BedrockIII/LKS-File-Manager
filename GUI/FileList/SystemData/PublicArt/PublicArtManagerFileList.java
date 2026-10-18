package GUI.FileList.SystemData.PublicArt;

import java.util.ArrayList;

import javax.swing.JMenuItem;
import javax.swing.filechooser.FileNameExtensionFilter;

import GUI.FileInfo.MenuDB.PublicArt.PublicArtManagerInfoGUI;
import GUI.FileList.CollapseableFileList;
import SystemDataManagers.MenuDB.PublicArt.PublicArt;
import SystemDataManagers.MenuDB.PublicArt.PublicArtManager;
import bFM.Settings;

@SuppressWarnings("serial")
public class PublicArtManagerFileList extends CollapseableFileList
{
	private ArrayList<PublicArt> Entries = new ArrayList<PublicArt>();
	PublicArtManager manager;
	public PublicArtManagerFileList(PublicArtManager file, int padding) 
	{
		manager = file;
		this.padding = padding;
		this.file = file;
		initializeAll();
	}
	protected void initializeAll() 
	{
		System.out.print("Opening Public Art Binary File: █");
		fileTypes = new FileNameExtensionFilter("Public Art Binary File", "bin");
		initializeListGUI("Public Art Manager");
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
		for(PublicArt object : Entries)
		{
			subEntries.add(new PublicArtListGUI(object, padding + Settings.indentSize, this));
		}
	}
	protected void initializeInfoGUI()
	{
		infoGUI = new PublicArtManagerInfoGUI(manager);
	}
	protected void addActions() 
	{
		addExportAction();
		addZoneAction();
		add(actions);
		addMouseListener();
	}
	private void addZoneAction()
	{
		JMenuItem newZone = new JMenuItem("Create New Art");
		newZone.addActionListener(e -> 
		{
			PublicArt entry = new PublicArt();
			Entries.add(entry);
			subEntries.add(new PublicArtListGUI(entry, padding + Settings.indentSize, this));
			reAddComponents();
		});
		actions.add(newZone);
	}
	public void removeEntry(PublicArtListGUI gui) 
	{
		subEntries.remove(gui);
		Entries.remove(gui.file);
		reAddComponents();
	}
}
