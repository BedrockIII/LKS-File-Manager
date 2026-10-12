package GUI.FileList.SystemData.Explanation;

import java.util.ArrayList;

import javax.swing.JMenuItem;
import javax.swing.filechooser.FileNameExtensionFilter;

import GUI.FileList.CollapseableFileList;
import SystemDataManagers.MenuDB.Explanation.Explanation;
import SystemDataManagers.MenuDB.Explanation.ExplanationManager;
import bFM.GUIUtils;
import bFM.Settings;

@SuppressWarnings("serial")
public class ExplanationManagerFileList extends CollapseableFileList
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
			subEntries.add(new ExplanationListGUI(object, padding + Settings.indentSize, this));
		}
	}
	protected void initializeInfoGUI()
	{
		//infoGUI = new HummingBookListInfoGUI(manager);
	}
	protected void addActions() 
	{
		addExportAction();
		addReplaceRawAction();
		addExportBJBAction();
		addImportBJBAction();
		addReplaceBJBAction();
		addZoneAction();
		add(actions);
		addMouseListener();
	}
	private void addZoneAction()
	{
		JMenuItem newZone = new JMenuItem("Create New Explanation");
		newZone.addActionListener(e -> 
		{
			Explanation entry = new Explanation();
			Entries.add(entry);
			subEntries.add(new ExplanationListGUI(entry, padding + Settings.indentSize, this));
			reAddComponents();
		});
		actions.add(newZone);
	}
	private void addExportBJBAction()
	{
		actions.add(GUIUtils.createExportAction("Export Entries as .bit text file", "Explanation.bit", "Bedrock's Intermediate Text File", manager::toIntermediateText));
	}
	private void addImportBJBAction()
	{
		actions.add(GUIUtils.createImportAction("Import Entries from .bit text file", "Bedrock's Intermediate Text File", "bit", manager::importFromIntermediateText, this));
	}
	private void addReplaceBJBAction()
	{
		actions.add(GUIUtils.createImportAction("Replace Entries from .bit text file", "Bedrock's Intermediate Text File", "bit", manager::replaceFromIntermediateText, this));
	}
	private void addReplaceRawAction()
	{
		actions.add(GUIUtils.createImportAction("Replace Entries from raw Humming.bin file", "Explanation Database Binary File", "bin", manager::replaceFromData, this));
	}
	public void removeEntry(ExplanationListGUI gui) 
	{
		subEntries.remove(gui);
		Entries.remove(gui.file);
		reAddComponents();
	}
}
