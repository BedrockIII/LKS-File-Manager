package GUI.FileList.SystemData.MovieBook;

import GUI.FileInfo.MenuDB.MovieBook.MovieInfoGUI;
import GUI.FileList.FileList;
import SystemDataManagers.MenuDB.Books.MovieBook.MovieBook;
import bFM.Settings;

@SuppressWarnings("serial")
public class MovieBookListGUI extends FileList
{
	MovieBook file;
	MovieBookManagerFileList parent;
	public MovieBookListGUI(MovieBook entry, MovieBookManagerFileList parent)
	{
		padding = parent.getPadding() + Settings.indentSize;
		this.file = entry;
		super.file = entry;
		this.parent = parent;
		initializeAll();
	}
	protected void initializeAll() 
	{
		initializeListGUI("Movie: \"" + file.getName() + "\"");
		addActions();
	}
	protected void initializeInfoGUI() 
	{
		infoGUI = new MovieInfoGUI(file);
	}
	protected void addActions() 
	{
		addDeleteAction("Delete Movie", parent, parent.manager.getEntries());
		addMoveUpAction(parent, parent.manager.getEntries());
		addMoveDownAction(parent, parent.manager.getEntries());
		add(actions);
		addMouseListener();
	}
	public void update()
	{
		fileName.setText("Movie: \"" + file.getName() + "\"");
		super.update();
	}
}