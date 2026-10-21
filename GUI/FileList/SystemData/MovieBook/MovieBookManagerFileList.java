package GUI.FileList.SystemData.MovieBook;

import javax.swing.filechooser.FileNameExtensionFilter;

import GUI.FileInfo.MenuDB.MovieBook.MovieBookManagerInfoGUI;
import GUI.FileList.CollapseableFileList;
import SystemDataManagers.MenuDB.Books.MovieBook.MovieBook;
import SystemDataManagers.MenuDB.Books.MovieBook.MovieBookManager;
import bFM.BITFileList;
import bFM.GUIUtils;

@SuppressWarnings("serial")
public class MovieBookManagerFileList extends CollapseableFileList implements BITFileList
{
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
		for(MovieBook object : manager.getEntries())
		{
			subEntries.add(new MovieBookListGUI(object, this));
		}
	}
	protected void initializeInfoGUI()
	{
		infoGUI = new MovieBookManagerInfoGUI(manager);
	}
	protected void addActions() 
	{
		addExportAction();
		addChildAction("Add Movie", manager.getEntries(), MovieBook::new, MovieBookListGUI::new);
		addReplaceRawAction();
		addExportBITAction();
		addImportBITAction();
		addReplaceBITAction();
		add(actions);
		addMouseListener();
	}
	private void addReplaceRawAction()
	{
		actions.add(GUIUtils.createImportAction("Replace Entries from raw Movie.bin file", "Movie Book Database Binary File", "bin", manager::replaceFromData, this));
	}
	public void addExportBITAction()
	{
		actions.add(createExportBITAction(manager));
	}
	public void addImportBITAction()
	{
		actions.add(createImportBITAction(manager, this));
	}
	public void addReplaceBITAction()
	{
		actions.add(createReplaceBITAction(manager, this));
	}
}
