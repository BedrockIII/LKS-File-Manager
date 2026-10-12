package GUI.FileList.SystemData.PublicArt;

import javax.swing.JMenuItem;

import GUI.GUI;
import GUI.FileInfo.MenuDB.PublicArt.PublicArtInfoGUI;
import GUI.FileList.FileList;
import SystemDataManagers.MenuDB.PublicArt.PublicArt;

@SuppressWarnings("serial")
public class PublicArtListGUI extends FileList
{
	PublicArt file;
	PublicArtManagerFileList parent;
	public PublicArtListGUI(PublicArt entry, int padding, PublicArtManagerFileList parent)
	{
		this.padding = padding;
		this.file = entry;
		this.parent = parent;
		initializeAll();
	}
	protected void initializeAll() 
	{
		initializeListGUI("Entry: \"" + file.getName() + "\"");
		initializeInfoGUI();
		addActions();
	}
	protected void initializeInfoGUI() 
	{
		this.infoGUI = new PublicArtInfoGUI(file);
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
		JMenuItem replace = new JMenuItem("Delete Art Entry");
		replace.addActionListener(e -> 
		{
			parent.removeEntry(this);
			GUI.update();
		});
		actions.add(replace);
	}
}