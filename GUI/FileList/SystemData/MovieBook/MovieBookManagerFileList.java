package GUI.FileList.SystemData.MovieBook;

import java.util.ArrayList;

import javax.swing.JMenuItem;
import javax.swing.filechooser.FileNameExtensionFilter;

import GUI.FileInfo.MenuDB.MovieBook.MovieBookManagerInfoGUI;
import GUI.FileList.CollapseableFileList;
import SystemDataManagers.MenuDB.Books.MovieBook.MovieBook;
import SystemDataManagers.MenuDB.Books.MovieBook.MovieBookManager;
import bFM.GUIUtils;
import bFM.Settings;

@SuppressWarnings("serial")
public class MovieBookManagerFileList extends CollapseableFileList
{
	private ArrayList<MovieBook> Entries = new ArrayList<MovieBook>();
	MovieBookManager manager;
	public MovieBookManagerFileList(MovieBookManager file, int padding) 
	{
		manager = file;
		this.padding = padding;
		this.file = file;
		initializeAll();
	}
	protected void initializeAll() 
	{
		System.out.print("Opening Movie Book Binary File: █");
		fileTypes = new FileNameExtensionFilter("Movie Book Binary File", "bin");
		initializeListGUI("Movie Book Manager");
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
		for(MovieBook object : Entries)
		{
			subEntries.add(new MovieBookListGUI(object, padding + Settings.indentSize, this));
		}
	}
	protected void initializeInfoGUI()
	{
		infoGUI = new MovieBookManagerInfoGUI(manager);
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
		JMenuItem newZone = new JMenuItem("Create New Movie");
		newZone.addActionListener(e -> 
		{
			MovieBook entry = new MovieBook();
			Entries.add(entry);
			subEntries.add(new MovieBookListGUI(entry, padding + Settings.indentSize, this));
			reAddComponents();
		});
		actions.add(newZone);
	}
	private void addExportBJBAction()
	{
		actions.add(GUIUtils.createExportAction("Export Entries as .bit text file", "MovieBook.bit", "Bedrock's Intermediate Text File", manager::toIntermediateText));
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
		actions.add(GUIUtils.createImportAction("Replace Entries from raw Movie.bin file", "Movie Book Database Binary File", "bin", manager::replaceFromData, this));
	}
	public void removeEntry(MovieBookListGUI gui) 
	{
		subEntries.remove(gui);
		Entries.remove(gui.file);
		reAddComponents();
	}
}
