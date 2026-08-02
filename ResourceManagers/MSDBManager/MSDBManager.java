package ResourceManagers.MSDBManager;

import PCKGManager.PCKGManager;
import ResourceManagers.MSDBManager.CollisionRectangle.MissionObjectCollisionRectangles;
import ResourceManagers.MSDBManager.Definition.MissionObjectManager;
import ResourceManagers.MSDBManager.MissionObjectBV.MissionObjectBV;
import ResourceManagers.MSDBManager.Placement.MissionObjectPlacementManager;
import bFM.OpenedFile;

public class MSDBManager implements OpenedFile
{
	protected MissionObjectPlacementManager MOP;
	protected MissionObjectManager MOB;
	protected MobDropTableList ItemDrops;
	protected MissionObjectCollisionRectangles MOCR;
	protected MissionObjectBV BV;
	public MSDBManager(byte[] data)
	{
		initializeFromBytes(data);
	}
	private void initializeFromBytes(byte[] data)
	{
		PCKGManager msDB = new PCKGManager(data);
		MOP = new MissionObjectPlacementManager(msDB.getFile("MOP_14_CONST_PLACE.lst"), 
				msDB.getFile("MOP_14_GROUP.lst"), 
				msDB.getFile("MOP_14_OBJECT.lst"), 
				msDB.getFile("MOP_14_RANDOM_AREA.lst"), 
				msDB.getFile("MOP_14_RANDOM_POINT.lst"), 
				msDB.getFile("MOP_14_AREA_DATA.lst"));
		
		MOB = new MissionObjectManager(msDB.getFile("MOB_24_AI.lst"),
				msDB.getFile("MOB_24_RES_ASN.lst"),
				msDB.getFile("MOB_24_MOD.lst"),
				msDB.getFile("MOB_24_ATK_COL.lst"),
				msDB.getFile("MOB_24_ATK_ELM.lst"),
				msDB.getFile("MOB_24_ATK_INFO.lst"),
				msDB.getFile("MOB_24_DMG_COL.lst"),
				msDB.getFile("MOB_24_PRESET_TABLE.lst"));
		
		MOCR = new MissionObjectCollisionRectangles(msDB.getFile("MOCR_0_RECT_LIST.lst"), 
				msDB.getFile("MOCR_0_RANDOM_WALL.lst"), 
				msDB.getFile("MOCR_0_RANDOM_GROUND.lst"), 
				msDB.getFile("MOCR_0_CONST_WALL.lst"), 
				msDB.getFile("MOCR_0_CONST_GROUND.lst"));
		
		BV = new MissionObjectBV(msDB.getFile("BV_3_ASSIGN.lst"), 
				msDB.getFile("BV_3_HEADER.lst"), 
				msDB.getFile("BV_3_MAPJUMP.lst"), 
				msDB.getFile("BV_3_NPCPRESET.lst"));
		
		ItemDrops = new MobDropTableList(msDB.getFile("MDITM_00.bin"));
	}
	public boolean equals(String name) 
	{
		throw new UnsupportedOperationException("equals() should not be called on type " + this.getClass());
	}
	public void setData(byte[] data) 
	{
		throw new UnsupportedOperationException("setData(byte[] data) should not be called on type " + this.getClass());
	}
	public byte[] toBytes() 
	{
		PCKGManager msDB = new PCKGManager();
		
		msDB.addFile("MOB_24_MOD.lst", MOB.getDefinition());
		msDB.addFile("MOB_24_ATK_INFO.lst", MOB.getAttackInfo());
		msDB.addFile("MOB_24_ATK_ELM.lst", MOB.getAttackElement());
		msDB.addFile("MOB_24_ATK_COL.lst", MOB.getAttackCol());
		msDB.addFile("MOB_24_DMG_COL.lst", MOB.getDamageCol());
		msDB.addFile("MOB_24_RES_ASN.lst", MOB.getResource());
		msDB.addFile("MOB_24_AI.lst", MOB.getAI());
		msDB.addFile("MOB_24_PRESET_TABLE.lst", MOB.getTable());
		
		msDB.addFile("MOP_14_GROUP.lst", MOP.getGroups());
		msDB.addFile("MOP_14_OBJECT.lst", MOP.getObjects());
		msDB.addFile("MOP_14_CONST_PLACE.lst", MOP.getConstantPlaces());
		msDB.addFile("MOP_14_RANDOM_AREA.lst", MOP.getAreas());
		msDB.addFile("MOP_14_RANDOM_POINT.lst", MOP.getPoints());
		msDB.addFile("MOP_14_AREA_DATA.lst", MOP.getAreaDatas());
		
		msDB.addFile("BV_3_HEADER.lst", BV.getHeader());
		msDB.addFile("BV_3_NPCPRESET.lst", BV.getNPCPreset());
		msDB.addFile("BV_3_MAPJUMP.lst", BV.getMapJump()); 
		msDB.addFile("BV_3_ASSIGN.lst", BV.getAssign()); 
		
		msDB.addFile("MOCR_0_CONST_WALL.lst", MOCR.getConstWall());
		msDB.addFile("MOCR_0_CONST_GROUND.lst", MOCR.getConstGround());
		msDB.addFile("MOCR_0_RANDOM_WALL.lst", MOCR.getRandWall());
		msDB.addFile("MOCR_0_RANDOM_GROUND.lst", MOCR.getRandGround());
		msDB.addFile("MOCR_0_RECT_LIST.lst", MOCR.getRectList());
		
		msDB.addFile("MDITM_00.bin", ItemDrops.toBytes());
		
		
		
		
		
		return msDB.getFile();
	}
	public void setName(String name) 
	{
		throw new UnsupportedOperationException("setName(String name) should not be called on type " + this.getClass());
	}
	public String getName() 
	{
		return "msDB27.pac";
	}
	public int getSize() 
	{
		return toBytes().length;
	}
	public MissionObjectPlacementManager getPlacement() 
	{
		return MOP;
	}
	public String getModCodeByName(int code) 
	{
		return MOB.getModCodeByName(code);
	}
}
