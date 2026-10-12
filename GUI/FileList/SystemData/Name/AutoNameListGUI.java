package GUI.FileList.SystemData.Name;

import GUI.FileList.FileList;

@SuppressWarnings("serial")
public class AutoNameListGUI extends FileList
{
	String name;
	NameListListGUI parent;
	public AutoNameListGUI(String name, int padding, NameListListGUI parent)
	{
		this.name = name;
		this.padding = padding;
		this.parent = parent;
		initializeAll();
	}
	protected void initializeAll()
	{
		initializeListGUI(name);
		addActions();
	}
	protected void initializeInfoGUI()
	{
		//TODO
	}
	protected void addActions()
	{
		add(actions);
		addMouseListener();
	}
}
