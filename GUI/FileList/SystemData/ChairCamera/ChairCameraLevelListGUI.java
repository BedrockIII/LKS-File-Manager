package GUI.FileList.SystemData.ChairCamera;

import java.util.ArrayList;

import GUI.FileInfo.MenuDB.ChairCamera.ChairCameraLevelInfoGUI;
import GUI.FileList.CollapseableFileList;
import SystemDataManagers.MenuDB.ChairCamera.ChairCamera;

@SuppressWarnings("serial")
public class ChairCameraLevelListGUI extends CollapseableFileList
{
	ArrayList<ChairCamera> Entries;
	int Level = -1;
	public ChairCameraLevelListGUI(ArrayList<ChairCamera> entries, int padding, int Level)
	{
		Entries = entries;
		this.padding = padding;
		this.Level = Level;
		initializeAll();
	}
	protected void initializeAll() 
	{
		initializeListGUI("Castle Level " + Level);
		initializeSubGUI();
		addActions();
		reAddComponents();
	}
	public void initializeSubGUI()
	{
		subEntries.removeAll(subEntries);
		for(ChairCamera object : Entries)
		{
			subEntries.add(new ChairCameraListGUI(object, this));
		}
	}
	protected void initializeInfoGUI()
	{
		infoGUI = new ChairCameraLevelInfoGUI(Entries);
	}
	protected void addActions() 
	{
		addChildAction("Add Chair Camera", Entries, ChairCamera::new, ChairCameraListGUI::new);
		add(actions);
		addMouseListener();
	}
}
