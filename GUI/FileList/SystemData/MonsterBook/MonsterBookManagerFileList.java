package GUI.FileList.SystemData.MonsterBook;

import javax.swing.filechooser.FileNameExtensionFilter;

import GUI.FileInfo.MenuDB.MonsterBook.MonsterBookManagerInfoGUI;
import GUI.FileList.CollapseableFileList;
import SystemDataManagers.MenuDB.Books.MonsterBook.MonsterBookManager;
import bFM.Settings;

@SuppressWarnings("serial")
public class MonsterBookManagerFileList extends CollapseableFileList
{
	MonsterBookManager manager;
	public MonsterBookManagerFileList(MonsterBookManager file, int padding) 
	{
		manager = file;
		this.padding = padding;
		this.file = file;
		initializeAll();
	}
	protected void initializeAll() 
	{
		System.out.print("Opening Monster Book Binary File: █");
		fileTypes = new FileNameExtensionFilter("Monster Book Binary File", "bin");
		initializeListGUI("Monster Book Manager");
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
		subEntries.add(new MonsterBookEntryManagerListGUI(manager.getBookEntries(), padding + Settings.indentSize));
		subEntries.add(new LogManagerListGUI(manager.getLogEntries(), padding + Settings.indentSize));
		subEntries.add(new MonsterHPManagerListGUI(manager.getHpEntries(), padding + Settings.indentSize));
	}
	protected void initializeInfoGUI()
	{
		infoGUI = new MonsterBookManagerInfoGUI(manager);
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
		//actions.add(GUIUtils.createImportAction("Replace Entries from raw Humming.bin file", "Humming Book Database Binary File", "bin", manager::replaceFromData, this));
	}
}
