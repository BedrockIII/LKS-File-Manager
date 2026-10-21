package GUI.FileList.SystemData.JewelBook;

import javax.swing.filechooser.FileNameExtensionFilter;

import GUI.FileInfo.MenuDB.JewelBook.JewelBookListInfoGUI;
import GUI.FileList.CollapseableFileList;
import SystemDataManagers.MenuDB.Books.JewelBook.JewelBookManager;
import SystemDataManagers.MenuDB.Books.JewelBook.JewelEntry;
import bFM.BITFileList;
import bFM.GUIUtils;

@SuppressWarnings("serial")
public class JewelBookFileList extends CollapseableFileList implements BITFileList
{
	JewelBookManager manager;

	public JewelBookFileList(JewelBookManager file, int padding) 
	{
		manager = file;
		this.padding = padding;
		this.file = file;
		super.file = file;
		initializeAll();
	}
	protected void initializeAll() 
	{
		System.out.print("Opening Jewel Book Binary File: █");
		fileTypes = new FileNameExtensionFilter("Jewel Book Binary File", "bin");
		initializeListGUI("Jewel Book Manager");
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
		for(JewelEntry object : manager.getEntries())
		{
			subEntries.add(new JewelGUI(object, this));
		}
	}
	protected void initializeInfoGUI()
	{
		infoGUI = new JewelBookListInfoGUI(manager);
	}
	protected void addActions() 
	{
		addExportAction();
		addChildAction("Add Jewel Entry", manager.getEntries(), JewelEntry::new, JewelGUI::new);
		addReplaceRawAction();
		addExportBITAction();
		addImportBITAction();
		addReplaceBITAction();
		add(actions);
		addMouseListener();
	}
	private void addReplaceRawAction()
	{
		actions.add(GUIUtils.createImportAction("Replace Spots from raw Album.bin file", "Wonder Spot Database Binary File", "bin", manager::replaceFromData, this));
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
