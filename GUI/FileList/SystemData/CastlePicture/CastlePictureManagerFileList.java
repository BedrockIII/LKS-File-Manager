package GUI.FileList.SystemData.CastlePicture;

import java.util.ArrayList;

import javax.swing.JMenuItem;
import javax.swing.filechooser.FileNameExtensionFilter;

import GUI.FileInfo.MenuDB.CastlePicture.CastlePictureManagerInfoGUI;
import GUI.FileList.CollapseableFileList;
import SystemDataManagers.MenuDB.CastlePicture.CastlePicture;
import SystemDataManagers.MenuDB.CastlePicture.CastlePictureManager;
import bFM.Settings;

@SuppressWarnings("serial")
public class CastlePictureManagerFileList extends CollapseableFileList
{
	private ArrayList<CastlePicture> Entries = new ArrayList<CastlePicture>();
	CastlePictureManager manager;
	public CastlePictureManagerFileList(CastlePictureManager file, int padding) 
	{
		manager = file;
		this.padding = padding;
		this.file = file;
		initializeAll();
	}
	protected void initializeAll() 
	{
		System.out.print("Opening Castle Picture Binary File: █");
		fileTypes = new FileNameExtensionFilter("Castle Picture Binary File", "bin");
		initializeListGUI("Castle Picture Manager");
		System.out.print("█");
		initializeSubGUI();
		System.out.print("█");
		initializeInfoGUI();
		System.out.print("█");
		addActions();
		System.out.print("█");
		reAddComponents();
		System.out.println("█\nComplete!");
	}
	public void initializeSubGUI()
	{
		subEntries.removeAll(subEntries);
		Entries = manager.getEntries();
		for(CastlePicture object : Entries)
		{
			subEntries.add(new CastlePictureFileList(object, padding + Settings.indentSize, this));
		}
	}
	protected void initializeInfoGUI()
	{
		infoGUI = new CastlePictureManagerInfoGUI(manager);
	}
	protected void addActions() 
	{
		addExportAction();
		addZoneAction();
		add(actions);
		addMouseListener();
	}
	private void addZoneAction()
	{
		JMenuItem newZone = new JMenuItem("Create New Castle Picture");
		newZone.addActionListener(e -> 
		{
			CastlePicture entry = new CastlePicture();
			Entries.add(entry);
			subEntries.add(new CastlePictureFileList(entry, padding + Settings.indentSize, this));
			reAddComponents();
		});
		//actions.add(newZone);
	}
}
