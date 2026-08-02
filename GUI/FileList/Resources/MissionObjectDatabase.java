package GUI.FileList.Resources;

import javax.swing.filechooser.FileNameExtensionFilter;

import GUI.FileInfo.GenericFileInfoGUI;
import GUI.FileList.CollapseableFileList;
import ResourceManagers.MSDBManager.MSDBManager;
import bFM.Settings;

@SuppressWarnings("serial")
public class MissionObjectDatabase extends CollapseableFileList
{
	MSDBManager MonsterDataPack;
	int padding;
	public MissionObjectDatabase(MSDBManager file, int padding)
	{
		this.file = file;
		MonsterDataPack = file;
		this.padding = padding;
		initializeAll(padding);
	}
	protected void initializeAll(int padding)
	{
		System.out.print("Opening Mission Object Database File: ");
		fileTypes = new FileNameExtensionFilter("Mission Object Database File", "pac");
		initializeListGUI(padding, "Mission Object Database");
		System.out.print("█\n");
		initializeSubGUI();
		//System.out.print("█");
		initializeInfoGUI();
		//System.out.print("█");
		addActions();
		//System.out.print("█");
		reAddComponents();
		//System.out.println("█\nComplete!");
	}
	public void initializeSubGUI()
	{
		//TODO
		//subEntries.add(FileListFactory.makeListGUI((MonsterDataPack).getPackedFile("MOB_24_MOD.lst"), Settings.indentSize + padding, this));
		//subEntries.add(FileListFactory.makeListGUI((MonsterDataPack).getPackedFile("MOB_24_ATK_INFO.lst"), Settings.indentSize + padding, this));
		//subEntries.add(FileListFactory.makeListGUI((MonsterDataPack).getPackedFile("MOB_24_ATK_ELM.lst"), Settings.indentSize + padding, this));
		//subEntries.add(FileListFactory.makeListGUI((MonsterDataPack).getPackedFile("MOB_24_ATK_COL.lst"), Settings.indentSize + padding, this));
		//subEntries.add(FileListFactory.makeListGUI((MonsterDataPack).getPackedFile("MOB_24_DMG_COL.lst"), Settings.indentSize + padding, this));
		//subEntries.add(FileListFactory.makeListGUI((MonsterDataPack).getPackedFile("MOB_24_RES_ASN.lst"), Settings.indentSize + padding, this));
		//subEntries.add(FileListFactory.makeListGUI((MonsterDataPack).getPackedFile("MOB_24_AI.lst"), Settings.indentSize + padding, this));
		//subEntries.add(FileListFactory.makeListGUI((MonsterDataPack).getPackedFile("MOB_24_PRESET_TABLE.lst"), Settings.indentSize + padding, this));
		//subEntries.add(FileListFactory.makeListGUI((MonsterDataPack).getPackedFile("BV_3_HEADER.lst"), Settings.indentSize + padding, this));
		//subEntries.add(FileListFactory.makeListGUI((MonsterDataPack).getPackedFile("BV_3_NPCPRESET.lst"), Settings.indentSize + padding, this));
		//subEntries.add(FileListFactory.makeListGUI((MonsterDataPack).getPackedFile("BV_3_MAPJUMP.lst"), Settings.indentSize + padding, this));
		//subEntries.add(FileListFactory.makeListGUI((MonsterDataPack).getPackedFile("BV_3_ASSIGN.lst"), Settings.indentSize + padding, this));
		//subEntries.add(FileListFactory.makeListGUI((MonsterDataPack).getPackedFile("MOCR_0_CONST_WALL.lst"), Settings.indentSize + padding, this));
		//subEntries.add(FileListFactory.makeListGUI((MonsterDataPack).getPackedFile("MOCR_0_CONST_GROUND.lst"), Settings.indentSize + padding, this));
		//subEntries.add(FileListFactory.makeListGUI((MonsterDataPack).getPackedFile("MOCR_0_RANDOM_WALL.lst"), Settings.indentSize + padding, this));
		//subEntries.add(FileListFactory.makeListGUI((MonsterDataPack).getPackedFile("MOCR_0_RANDOM_GROUND.lst"), Settings.indentSize + padding, this));
		//subEntries.add(FileListFactory.makeListGUI((MonsterDataPack).getPackedFile("MOCR_0_RECT_LIST.lst"), Settings.indentSize + padding, this));
		//subEntries.add(FileListFactory.makeListGUI((MonsterDataPack).getPackedFile("MDITM_00.bin"), Settings.indentSize + padding, this));
		
		subEntries.add(new MOPlacementListGUI(MonsterDataPack.getPlacement(), padding + Settings.indentSize, this));
	}
	protected void initializeInfoGUI()
	{
		// TODO Auto-generated method stub
		infoGUI = new GenericFileInfoGUI(MonsterDataPack);
	}
	protected void addActions() 
	{
		// TODO Auto-generated method stub
		add(actions);
		addMouseListener();
	}

}
