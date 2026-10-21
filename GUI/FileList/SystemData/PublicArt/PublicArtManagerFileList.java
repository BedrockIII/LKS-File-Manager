package GUI.FileList.SystemData.PublicArt;

import javax.swing.filechooser.FileNameExtensionFilter;

import GUI.FileInfo.MenuDB.PublicArt.PublicArtManagerInfoGUI;
import GUI.FileList.CollapseableFileList;
import SystemDataManagers.MenuDB.PublicArt.PublicArt;
import SystemDataManagers.MenuDB.PublicArt.PublicArtManager;
import bFM.BITFileList;
import bFM.GUIUtils;

@SuppressWarnings("serial")
public class PublicArtManagerFileList extends CollapseableFileList implements BITFileList
{
	PublicArtManager manager;
	public PublicArtManagerFileList(PublicArtManager file, int padding) 
	{
		manager = file;
		this.padding = padding;
		this.file = file;
		initializeAll();
	}
	protected void initializeAll() 
	{
		System.out.print("Opening Public Art Binary File: █");
		fileTypes = new FileNameExtensionFilter("Public Art Binary File", "bin");
		initializeListGUI("Public Art Manager");
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
		for(PublicArt object : manager.getEntries())
		{
			subEntries.add(new PublicArtListGUI(object, this));
		}
	}
	protected void initializeInfoGUI()
	{
		infoGUI = new PublicArtManagerInfoGUI(manager);
	}
	protected void addActions() 
	{
		addExportAction();
		addChildAction("Add Public Art", manager.getEntries(), PublicArt::new, PublicArtListGUI::new);
		addReplaceRawAction();
		addExportBITAction();
		addImportBITAction();
		addReplaceBITAction();
		add(actions);
		addMouseListener();
	}
	private void addReplaceRawAction()
	{
		actions.add(GUIUtils.createImportAction("Replace Art from raw PublicArt.bin file", "Public Art Database Binary File", "bin", manager::replaceFromData, this));
	}
	public void addExportBITAction()
	{
		actions.add(createExportBITAction(manager));
	}
	public void addImportBITAction()
	{
		actions.add(createImportBITAction(manager, this));
	}
	public void addReplaceBITAction()
	{
		actions.add(createReplaceBITAction(manager, this));
	}
}
