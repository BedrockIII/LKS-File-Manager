package GUI.FileList.SystemData.WonderSpot;

import javax.swing.JMenuItem;

import GUI.GUI;
import GUI.FileInfo.MenuDB.WonderSpot.WonderSpotInfoGUI;
import GUI.FileList.FileList;
import SystemDataManagers.MenuDB.Books.WonderSpotBook.WonderSpot;

@SuppressWarnings("serial")
public class WonderSpotGUI extends FileList
{
	WonderSpot file;
	WonderSpotFileList parent;
	public WonderSpotGUI(WonderSpot file, int padding, WonderSpotFileList parent)
	{
		this.padding = padding;
		this.file = file;
		this.parent = parent;
		initializeAll();
	}
	protected void initializeAll() 
	{
		initializeListGUI("Spot: \"" + file.getName() + "\"");
		initializeInfoGUI();
		addActions();
	}
	protected void initializeInfoGUI() 
	{
		this.infoGUI = new WonderSpotInfoGUI(file);
	}
	protected void addActions() 
	{
		addDeleteAction();
		add(actions);
		addMouseListener();
	}
	public void update()
	{
		fileName.setText("Spot: \"" + file.getName() + "\"");
		super.update();
	}
	protected void addDeleteAction()
	{
		JMenuItem replace = new JMenuItem("Delete Wonder Spot");
		replace.addActionListener(e -> 
		{
			parent.removeSpot(this);
			GUI.update();
		});
		actions.add(replace);
	}
}