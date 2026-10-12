package GUI.FileList.SystemData.ChairCamera;

import javax.swing.filechooser.FileNameExtensionFilter;

import GUI.FileList.CollapseableFileList;
import SystemDataManagers.MenuDB.ChairCamera.ChairCameraManager;
import bFM.GUIUtils;
import bFM.Settings;

@SuppressWarnings("serial")
public class ChairCameraManagerFileList extends CollapseableFileList
{
	ChairCameraManager manager;
	ChairCameraLevelListGUI Level1;
	ChairCameraLevelListGUI Level2;
	ChairCameraLevelListGUI Level3;
	public ChairCameraManagerFileList(ChairCameraManager file, int padding) 
	{
		manager = file;
		this.padding = padding;
		this.file = file;
		initializeAll();
	}
	protected void initializeAll() 
	{
		System.out.print("Opening Chair Camera Binary File: █");
		fileTypes = new FileNameExtensionFilter("Chair Camera Binary File", "bin");
		initializeListGUI("Chair Camera Manager");
		System.out.print("█");
		initializeSubGUI();
		System.out.print("█");
		//initializeInfoGUI();
		System.out.print("█");
		addActions();
		System.out.print("█");
		reAddComponents();
		System.out.println("█\nComplete!");
	}
	public void initializeSubGUI()
	{
		subEntries.removeAll(subEntries);
		Level1 = new ChairCameraLevelListGUI(manager.getLevel1Entries(), padding + Settings.indentSize, 1);
		Level2 = new ChairCameraLevelListGUI(manager.getLevel2Entries(), padding + Settings.indentSize, 2);
		Level3 = new ChairCameraLevelListGUI(manager.getLevel3Entries(), padding + Settings.indentSize, 3);
		subEntries.add(Level1);
		subEntries.add(Level2);
		subEntries.add(Level3);
	}
	protected void initializeInfoGUI()
	{
		//TODO
		//infoGUI = new PublicArtManagerInfoGUI(manager);
	}
	protected void addActions() 
	{
		addExportAction();
		addReplaceRawAction();
		add(actions);
		addMouseListener();
	}
	private void addReplaceRawAction()
	{
		actions.add(GUIUtils.createImportAction("Replace Entries from raw Humming.bin file", "Humming Book Database Binary File", "bin", manager::replaceFromData, this));
	}
}
