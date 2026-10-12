package GUI.FileList.SystemData.Explanation;

import javax.swing.JMenuItem;

import GUI.GUI;
import GUI.FileInfo.MenuDB.Explanation.ExplanationInfoGUI;
import GUI.FileInfo.MenuDB.HummingBook.HummingEntryInfoGUI;
import GUI.FileList.FileList;
import SystemDataManagers.MenuDB.Explanation.Explanation;

@SuppressWarnings("serial")
public class ExplanationListGUI extends FileList
{
	Explanation file;
	ExplanationManagerFileList parent;
	public ExplanationListGUI(Explanation entry, int padding, ExplanationManagerFileList parent)
	{
		this.padding = padding;
		this.file = entry;
		this.parent = parent;
		initializeAll();
	}
	protected void initializeAll() 
	{
		initializeListGUI("Explanation: \"" + file.getName() + "\"");
		addActions();
	}
	protected void initializeInfoGUI() 
	{
		this.infoGUI = new ExplanationInfoGUI(file);
	}
	protected void addActions() 
	{
		addDeleteAction();
		add(actions);
		addMouseListener();
	}
	public void update()
	{
		fileName.setText("Explanation: \"" + file.getName() + "\"");
		super.update();
	}
	protected void addDeleteAction()
	{
		JMenuItem replace = new JMenuItem("Delete Explanation");
		replace.addActionListener(e -> 
		{
			parent.removeEntry(this);
			GUI.update();
		});
		actions.add(replace);
	}
}