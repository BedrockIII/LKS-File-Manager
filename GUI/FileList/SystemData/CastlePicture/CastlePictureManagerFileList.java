package GUI.FileList.SystemData.CastlePicture;

import javax.swing.filechooser.FileNameExtensionFilter;

import GUI.FileInfo.MenuDB.CastlePicture.CastlePictureManagerInfoGUI;
import GUI.FileList.CollapseableFileList;
import SystemDataManagers.MenuDB.CastlePicture.CastlePicture;
import SystemDataManagers.MenuDB.CastlePicture.CastlePictureManager;
import bFM.BITFileList;
import bFM.GUIUtils;

@SuppressWarnings("serial")
public class CastlePictureManagerFileList extends CollapseableFileList implements BITFileList
{
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
		for(CastlePicture object : manager.getEntries())
		{
			subEntries.add(new CastlePictureFileList(object, this));
		}
	}
	protected void initializeInfoGUI()
	{
		infoGUI = new CastlePictureManagerInfoGUI(manager);
	}
	protected void addActions() 
	{
		addExportAction();
		addChildAction("Add Castle Picture", manager.getEntries(), CastlePicture::new, CastlePictureFileList::new);
		addReplaceRawAction();
		addExportBITAction();
		addImportBITAction();
		addReplaceBITAction();
		add(actions);
		addMouseListener();
	}
	private void addReplaceRawAction()
	{
		actions.add(GUIUtils.createImportAction("Replace Art from raw CastlePicture.bin file", "Castle Picture Database Binary File", "bin", manager::setData, this));
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
