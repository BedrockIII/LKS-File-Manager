package GUI.FileList.SystemData.Explanation;

import GUI.FileInfo.MenuDB.Explanation.ExplanationInfoGUI;
import GUI.FileList.FileList;
import SystemDataManagers.MenuDB.Explanation.Explanation;
import bFM.Settings;

@SuppressWarnings("serial")
public class ExplanationListGUI extends FileList
{
	Explanation file;
	ExplanationManagerFileList parent;
	public ExplanationListGUI(Explanation entry, ExplanationManagerFileList parent)
	{
		this.padding = parent.getPadding() + Settings.indentSize;
		this.file = entry;
		super.file = entry;
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
		addDeleteAction("Delete Explanation", parent, parent.manager.getEntries());
		addMoveUpAction(parent, parent.manager.getEntries());
		addMoveDownAction(parent, parent.manager.getEntries());
		add(actions);
		addMouseListener();
	}
	public void update()
	{
		fileName.setText("Explanation: \"" + file.getName() + "\"");
		super.update();
	}
}