package GUI.FileList.SystemData.Explanation;

import java.util.ArrayList;

import javax.swing.filechooser.FileNameExtensionFilter;

import GUI.FileInfo.MenuDB.Explanation.ExplanationManagerInfoGUI;
import GUI.FileList.CollapseableFileList;
import SystemDataManagers.MenuDB.Explanation.Explanation;
import SystemDataManagers.MenuDB.Explanation.ExplanationManager;
import bFM.BITFileList;
import bFM.GUIUtils;

@SuppressWarnings("serial")
public class ExplanationManagerFileList extends CollapseableFileList implements BITFileList
{
	private ArrayList<Explanation> Entries = new ArrayList<Explanation>();
	ExplanationManager manager;
	public ExplanationManagerFileList(ExplanationManager file, int padding) 
	{
		manager = file;
		this.padding = padding;
		this.file = file;
		initializeAll();
	}
	protected void initializeAll() 
	{
		System.out.print("Opening Explanation Binary File: █");
		fileTypes = new FileNameExtensionFilter("Explanation Binary File", "bin");
		initializeListGUI("Explanation Manager");
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
		Entries = manager.getEntries();
		for(Explanation object : Entries)
		{
			subEntries.add(new ExplanationListGUI(object, this));
		}
	}
	protected void initializeInfoGUI()
	{
		infoGUI = new ExplanationManagerInfoGUI(manager);
	}
	protected void addActions() 
	{
		addExportAction();
		addChildAction("Add Explanation", manager.getEntries(), Explanation::new, ExplanationListGUI::new);
		addReplaceRawAction();
		addExportBITAction();
		addImportBITAction();
		addReplaceBITAction();
		add(actions);
		addMouseListener();
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
	private void addReplaceRawAction()
	{
		actions.add(GUIUtils.createImportAction("Replace Entries from raw Humming.bin file", "Explanation Database Binary File", "bin", manager::replaceFromData, this));
	}
}
