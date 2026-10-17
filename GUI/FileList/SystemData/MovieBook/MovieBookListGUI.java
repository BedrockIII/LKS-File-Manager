package GUI.FileList.SystemData.MovieBook;

import javax.swing.JMenuItem;

import GUI.GUI;
import GUI.FileInfo.MenuDB.MovieBook.MovieInfoGUI;
import GUI.FileList.FileList;
import SystemDataManagers.MenuDB.Books.MovieBook.MovieBook;

@SuppressWarnings("serial")
public class MovieBookListGUI extends FileList
{
	MovieBook file;
	MovieBookManagerFileList parent;
	public MovieBookListGUI(MovieBook entry, int padding, MovieBookManagerFileList parent)
	{
		this.padding = padding;
		this.file = entry;
		this.parent = parent;
		initializeAll();
	}
	protected void initializeAll() 
	{
		initializeListGUI("Movie: \"" + file.getName() + "\"");
		initializeInfoGUI();
		addActions();
	}
	protected void initializeInfoGUI() 
	{
		infoGUI = new MovieInfoGUI(file);
	}
	protected void addActions() 
	{
		addDeleteAction();
		add(actions);
		addMouseListener();
	}
	public void update()
	{
		fileName.setText("Movie: \"" + file.getName() + "\"");
		super.update();
	}
	protected void addDeleteAction()
	{
		JMenuItem replace = new JMenuItem("Delete Movie Entry");
		replace.addActionListener(e -> 
		{
			parent.removeEntry(this);
			GUI.update();
		});
		actions.add(replace);
	}
}