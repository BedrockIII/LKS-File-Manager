package GUI.FileList.SystemData.ChairCamera;

import javax.swing.JMenuItem;

import GUI.GUI;
import GUI.FileList.FileList;
import SystemDataManagers.MenuDB.ChairCamera.ChairCamera;

@SuppressWarnings("serial")
public class ChairCameraListGUI extends FileList
{
	ChairCamera file;
	ChairCameraLevelListGUI parent;
	public ChairCameraListGUI(ChairCamera entry, int padding, ChairCameraLevelListGUI parent)
	{
		this.padding = padding;
		this.file = entry;
		this.parent = parent;
		initializeAll();
	}
	protected void initializeAll() 
	{
		initializeListGUI("Camera");
		initializeInfoGUI();
		addActions();
	}
	protected void initializeInfoGUI() 
	{
		//TODO
		//this.infoGUI = new PublicArtInfoGUI(file);
	}
	protected void addActions() 
	{
		addDeleteAction();
		add(actions);
		addMouseListener();
	}
	protected void addDeleteAction()
	{
		JMenuItem replace = new JMenuItem("Delete Camera");
		replace.addActionListener(e -> 
		{
			parent.removeEntry(this);
			GUI.update();
		});
		actions.add(replace);
	}
}