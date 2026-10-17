package GUI.FileList.SystemData.Unused.NPCMemo;

import javax.swing.filechooser.FileNameExtensionFilter;

import GUI.FileList.CollapseableFileList;
import SystemDataManagers.MenuDB.Unused.NPCMemo.NPCMemoManager;
import bFM.GUIUtils;
import bFM.Settings;

@SuppressWarnings("serial")
public class NPCMemoManagerFileList extends CollapseableFileList
{
	NPCMemoManager manager;
	MemoryManagerListGUI ManMemories;
	MemoryManagerListGUI WomanMemories;
	ActionManagerListGUI ManActions;
	ActionManagerListGUI WomanActions;
	KingLevelManagerListGUI KingLevels;
	public NPCMemoManagerFileList(NPCMemoManager file, int padding) 
	{
		manager = file;
		this.padding = padding;
		this.file = file;
		initializeAll();
	}
	protected void initializeAll() 
	{
		System.out.print("Opening (Unused) NPC Memo Binary File: █");
		fileTypes = new FileNameExtensionFilter("NPC Memo Binary File", "bin");
		initializeListGUI("(Unused) NPC Memo Manager");
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
		ManMemories = new MemoryManagerListGUI(manager.getManMemoryManager(), padding + Settings.indentSize, "Male");
		WomanMemories = new MemoryManagerListGUI(manager.getWomanMemoryManager(), padding + Settings.indentSize, "Female");
		ManActions = new ActionManagerListGUI(manager.getManActionManager(), padding + Settings.indentSize, "Male");
		WomanActions = new ActionManagerListGUI(manager.getWomanActionManager(), padding + Settings.indentSize, "Female");
		KingLevels = new KingLevelManagerListGUI(manager.getLevelManager(), padding + Settings.indentSize);
		subEntries.add(ManMemories);
		subEntries.add(WomanMemories);
		subEntries.add(ManActions);
		subEntries.add(WomanActions);
		subEntries.add(KingLevels);
	}
	protected void initializeInfoGUI()
	{
		//infoGUI = new DevelopmentManagerFileInfo(manager);
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
		actions.add(GUIUtils.createImportAction("Replace Entries from raw NPCMemo.bin file", "Movie Book Database Binary File", "bin", manager::replaceFromData, this));
	}
}
