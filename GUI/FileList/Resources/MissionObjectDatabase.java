package GUI.FileList.Resources;

import javax.swing.filechooser.FileNameExtensionFilter;

import GUI.FileList.CollapseableFileList;
import GUI.FileList.FileListFactory;
import PCKGManager.PCKGManager;
import ResourceManagers.MSDBManager.Placement.MissionObjectPlacementManager;
import bFM.Settings;

@SuppressWarnings("serial")
public class MissionObjectDatabase extends CollapseableFileList
{
	PCKGManager MonsterDataPack;
	int padding;
	public MissionObjectDatabase(PCKGManager file, int padding)
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
		subEntries.add(FileListFactory.makeListGUI((MonsterDataPack).getPackedFile("MOB_24_MOD.lst"), Settings.indentSize + padding));
		subEntries.add(FileListFactory.makeListGUI((MonsterDataPack).getPackedFile("MOB_24_ATK_INFO.lst"), Settings.indentSize + padding));
		subEntries.add(FileListFactory.makeListGUI((MonsterDataPack).getPackedFile("MOB_24_ATK_ELM.lst"), Settings.indentSize + padding));
		subEntries.add(FileListFactory.makeListGUI((MonsterDataPack).getPackedFile("MOB_24_ATK_COL.lst"), Settings.indentSize + padding));
		subEntries.add(FileListFactory.makeListGUI((MonsterDataPack).getPackedFile("MOB_24_DMG_COL.lst"), Settings.indentSize + padding));
		subEntries.add(FileListFactory.makeListGUI((MonsterDataPack).getPackedFile("MOB_24_RES_ASN.lst"), Settings.indentSize + padding));
		subEntries.add(FileListFactory.makeListGUI((MonsterDataPack).getPackedFile("MOB_24_AI.lst"), Settings.indentSize + padding));
		subEntries.add(FileListFactory.makeListGUI((MonsterDataPack).getPackedFile("MOB_24_PRESET_TABLE.lst"), Settings.indentSize + padding));
		subEntries.add(FileListFactory.makeListGUI((MonsterDataPack).getPackedFile("BV_3_HEADER.lst"), Settings.indentSize + padding));
		subEntries.add(FileListFactory.makeListGUI((MonsterDataPack).getPackedFile("BV_3_NPCPRESET.lst"), Settings.indentSize + padding));
		subEntries.add(FileListFactory.makeListGUI((MonsterDataPack).getPackedFile("BV_3_MAPJUMP.lst"), Settings.indentSize + padding));
		subEntries.add(FileListFactory.makeListGUI((MonsterDataPack).getPackedFile("BV_3_ASSIGN.lst"), Settings.indentSize + padding));
		subEntries.add(FileListFactory.makeListGUI((MonsterDataPack).getPackedFile("MOCR_0_CONST_WALL.lst"), Settings.indentSize + padding));
		subEntries.add(FileListFactory.makeListGUI((MonsterDataPack).getPackedFile("MOCR_0_CONST_GROUND.lst"), Settings.indentSize + padding));
		subEntries.add(FileListFactory.makeListGUI((MonsterDataPack).getPackedFile("MOCR_0_RANDOM_WALL.lst"), Settings.indentSize + padding));
		subEntries.add(FileListFactory.makeListGUI((MonsterDataPack).getPackedFile("MOCR_0_RANDOM_GROUND.lst"), Settings.indentSize + padding));
		subEntries.add(FileListFactory.makeListGUI((MonsterDataPack).getPackedFile("MOCR_0_RECT_LIST.lst"), Settings.indentSize + padding));
		subEntries.add(FileListFactory.makeListGUI((MonsterDataPack).getPackedFile("MDITM_00.bin"), Settings.indentSize + padding));
		
		subEntries.add(new MOPlacementListGUI(new MissionObjectPlacementManager(MonsterDataPack.getFile("MOP_14_CONST_PLACE.lst"), 
				MonsterDataPack.getFile("MOP_14_GROUP.lst"), MonsterDataPack.getFile("MOP_14_OBJECT.lst"), 
				MonsterDataPack.getFile("MOP_14_RANDOM_AREA.lst"), MonsterDataPack.getFile("MOP_14_RANDOM_POINT.lst"), 
				MonsterDataPack.getFile("MOP_14_AREA_DATA.lst"))));
	}
	protected void initializeInfoGUI()
	{
		// TODO Auto-generated method stub
	}
	protected void addActions() 
	{
		// TODO Auto-generated method stub
	}

}
