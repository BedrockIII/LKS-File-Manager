package GUI.FileList.SystemData.Unused.Development;

import javax.swing.JMenuItem;

import GUI.GUI;
import GUI.FileInfo.MenuDB.Development.DevelopmentInfoGUI;
import GUI.FileList.FileList;
import SystemDataManagers.MenuDB.Unused.Development.Development;

@SuppressWarnings("serial")
public class DevelopmentListGUI extends FileList
{
	Development file;
	DevelopmentManagerFileList parent;
	public DevelopmentListGUI(Development entry, int padding, DevelopmentManagerFileList parent)
	{
		this.padding = padding;
		this.file = entry;
		this.parent = parent;
		initializeAll();
	}
	protected void initializeAll() 
	{
		initializeListGUI("Development: \"" + file.getIcon() + "\"");
		initializeInfoGUI();
		addActions();
	}
	protected void initializeInfoGUI() 
	{
		this.infoGUI = new DevelopmentInfoGUI(file);
	}
	protected void addActions() 
	{
		addDeleteAction();
		add(actions);
		addMouseListener();
	}
	public void update()
	{
		fileName.setText("Development: \"" + file.getIcon() + "\"");
		super.update();
	}
	protected void addDeleteAction()
	{
		JMenuItem replace = new JMenuItem("Delete Development");
		replace.addActionListener(e -> 
		{
			parent.removeEntry(this);
			GUI.update();
		});
		actions.add(replace);
	}
}