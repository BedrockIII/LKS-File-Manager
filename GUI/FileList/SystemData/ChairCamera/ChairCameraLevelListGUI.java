package GUI.FileList.SystemData.ChairCamera;

import java.util.ArrayList;

import javax.swing.JMenuItem;
import javax.swing.filechooser.FileNameExtensionFilter;

import GUI.FileList.CollapseableFileList;
import SystemDataManagers.MenuDB.ChairCamera.ChairCamera;
import bFM.Settings;

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
			subEntries.add(new ChairCameraListGUI(object, padding + Settings.indentSize, this));
		}
	}
	protected void initializeInfoGUI()
	{
		//TODO
		//infoGUI = new PublicArtManagerInfoGUI(manager);
	}
	protected void addActions() 
	{
		addZoneAction();
		add(actions);
		addMouseListener();
	}
	private void addZoneAction()
	{
		JMenuItem newZone = new JMenuItem("Create New Camera");
		newZone.addActionListener(e -> 
		{
			ChairCamera entry = new ChairCamera();
			Entries.add(entry);
			subEntries.add(new ChairCameraListGUI(entry, padding + Settings.indentSize, this));
			reAddComponents();
		});
		actions.add(newZone);
	}
	public void removeEntry(ChairCameraListGUI gui) 
	{
		subEntries.remove(gui);
		Entries.remove(gui.file);
		reAddComponents();
	}
}
