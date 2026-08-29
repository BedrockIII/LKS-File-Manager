package ResourceManagers.MSDBManager;

import java.awt.Rectangle;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

import PCKGManager.PCKGManager;
import ResourceManagers.ItemDatabaseManager.itemDatabaseManager;
import ResourceManagers.MSDBManager.BetaManager.BetaConstPlace;
import ResourceManagers.MSDBManager.BetaManager.BetaGroup;
import ResourceManagers.MSDBManager.BetaManager.BetaObject;
import ResourceManagers.MSDBManager.CollisionRectangle.MobRectangleList;
import ResourceManagers.MSDBManager.Definition.MobAiList;
import ResourceManagers.MSDBManager.Definition.MobAttackColList;
import ResourceManagers.MSDBManager.Definition.MobAttackElemList;
import ResourceManagers.MSDBManager.Definition.MobAttackInfoList;
import ResourceManagers.MSDBManager.Definition.MobDamageColList;
import ResourceManagers.MSDBManager.Definition.MobModList;
import ResourceManagers.MSDBManager.Definition.MobPresetTableList;
import ResourceManagers.MSDBManager.Definition.MobResAsn;
import ResourceManagers.MSDBManager.Placement.MissionObjectPlacementManager;
import ResourceManagers.MSDBManager.Placement.MobAreaDataList;
import ResourceManagers.MSDBManager.Placement.MobConstantPlace;
import ResourceManagers.MSDBManager.Placement.MobGroup;
import ResourceManagers.MSDBManager.Placement.MobGroupList;
import ResourceManagers.MSDBManager.Placement.MobObject;
import ResourceManagers.MSDBManager.Placement.MobObjectList;
import ResourceManagers.MSDBManager.Placement.MobRandomAreaList;
import ResourceManagers.MSDBManager.Placement.MobRandomPointList;
import bFM.Settings;
import bFM.Utils;

/**
 * 
 */
@SuppressWarnings("unused")
public class ExtractionTester 
{
	public static itemDatabaseManager items = null;;
	static String outputPath = "D:\\ModTest\\";
	static String inputPath = "D:\\ModTest\\"; 
	//static String outputPath = "D:\\ws - Copy\\pack\\mount\\msbdnlks\\Output\\";
	//static String inputPath = "D:\\ws - Copy\\pack\\mount\\msbdnlks\\msDB27.pac"; 
	//static String inputPath = "D:\\LKS Debug!!!!1\\ROMs\\onetri1\\DATA\\files\\res\\test\\";
	public static void main(String[] args) 
	{
		
		try {
			Utils.setDebugOutput(true);
			//testAll();
			//testMod();
			//RandomMonster();
			//testBeta1()
			//testPlacement();
			//testItems(); 
			//testDefinition();
			testRepacDefinition();
			//testItemDropTableExtract();
			throw new IOException("all okay");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	private static void testItemDropTableExtract() throws IOException
	{
		MobDropTableList items = new MobDropTableList(Files.readAllBytes(Paths.get(inputPath+"MDITM00.bin")));
		Files.write(Paths.get(outputPath+"MobDropTables.bMos"), items.toBMos().getBytes("Shift-JIS"));
		Utils.testDifferences(new MobDropTableList(Files.readAllLines(Paths.get(outputPath+"MobDropTables.bMos"))).toBytes(), Files.readAllBytes(Paths.get(inputPath+"MDITM00.bin")));
	}
	private static void testPlacement() 
	{
		int modCode = -1; 
		Rectangle filterZone = null;
		filterZone = new Rectangle(300, 514, 710, 850); // WIDTH AND HEIGHT ARE ACTUALLY JUST SECOND OFFSETS
		boolean keepOnlyOutside = false; // if false keep inside
		String outputFileName = "PlacementTest.bmos";
		String extractedPath = "D:\\";
		String resPath = "D:\\LKS Debug!!!!1\\ROMs\\Release Game\\DATA\\files\\res\\";
		bFM.Utils.DebugPrint("Decoding Enemy Data into raw text");
		PCKGManager MonsterDataPack = new PCKGManager("MSDB");
		try
		{
			bFM.Utils.DebugPrint("Attempting to read msDB27.pac");
			MonsterDataPack = new PCKGManager(Files.readAllBytes(Paths.get(resPath+"msDB27.pac")));
		}
		catch (IOException e)
		{
			bFM.Utils.DebugPrint("Failed to Locate Monster Database Pack at: " + resPath+"msDB27.pac");
			bFM.Utils.DebugPrint("Program will return as it cannot continue.");
			return;
		}
		MissionObjectPlacementManager bMos = new MissionObjectPlacementManager(MonsterDataPack.getFile("MOP_14_CONST_PLACE.lst"), 
				MonsterDataPack.getFile("MOP_14_GROUP.lst"), MonsterDataPack.getFile("MOP_14_OBJECT.lst"), 
				MonsterDataPack.getFile("MOP_14_RANDOM_AREA.lst"), MonsterDataPack.getFile("MOP_14_RANDOM_POINT.lst"), 
				MonsterDataPack.getFile("MOP_14_AREA_DATA.lst"));
		//Utils.testDifferences(MonsterDataPack.getFile("MOP_14_CONST_PLACE.lst"), bMos.getConstantPlaces());
		//Utils.testDifferences(MonsterDataPack.getFile("MOP_14_GROUP.lst"), bMos.getGroups());
		//Utils.testDifferences(MonsterDataPack.getFile("MOP_14_OBJECT.lst"), bMos.getObjects());
		//Utils.testDifferences(MonsterDataPack.getFile("MOP_14_RANDOM_AREA.lst"), bMos.getAreas());
		//Utils.testDifferences(MonsterDataPack.getFile("MOP_14_RANDOM_POINT.lst"), bMos.getPoints());
		//Utils.testDifferences(MonsterDataPack.getFile("MOP_14_AREA_DATA.lst"), bMos.getAreaDatas());
		
		//Test Bedrock's Intermediatary Mission Objects File:
		
		byte[] bmos = bMos.toString().getBytes(Charset.forName("Ascii"));
		MissionObjectPlacementManager bMos2 = new MissionObjectPlacementManager(Utils.bytesToStrs(bmos));
		
		//Utils.testDifferences(MonsterDataPack.getFile("MOP_14_CONST_PLACE.lst"), bMos2.getConstantPlaces());
		//Utils.testDifferences(MonsterDataPack.getFile("MOP_14_GROUP.lst"), bMos2.getGroups());
		//Utils.testDifferences(MonsterDataPack.getFile("MOP_14_OBJECT.lst"), bMos2.getObjects());
		//Utils.testDifferences(MonsterDataPack.getFile("MOP_14_RANDOM_AREA.lst"), bMos2.getAreas());
		//Utils.testDifferences(MonsterDataPack.getFile("MOP_14_RANDOM_POINT.lst"), bMos2.getPoints());
		//Utils.testDifferences(MonsterDataPack.getFile("MOP_14_AREA_DATA.lst"), bMos2.getAreaDatas());
		
		/*
		if(filterZone!=null)
		{
			bMos.setConstraints(filterZone.x, filterZone.width, filterZone.y, filterZone.height, keepOnlyOutside, false);
		}
		if(modCode!=-1)
		{
			bMos.setFilterCode(modCode);
		}

		try 
		{
			bFM.Utils.DebugPrint("Attempting to write raw text at: " + extractedPath+outputFileName);
			Files.write(Paths.get(extractedPath+outputFileName) , Utils.encodeStringToBytes(bMos.toString()));
		} catch (IOException e) 
		{
			bFM.Utils.DebugPrint("Failed to write bMos file at: " + extractedPath+outputFileName);
			return;
		}
		*/
		System.exit(0);
	}
	private static void testItems() 
	{
		try {
			items = new itemDatabaseManager(Files.readAllBytes(Paths.get("D:\\Dolphin_Emulator\\Load\\Riivolution\\LKSMapTesting\\Resources\\itemDB3_1.pac")));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		int modCode = -1; 
		Rectangle filterZone = null;
		filterZone = new Rectangle(0, 704, 704, 1000); // WIDTH AND HEIGHT ARE ACTUALLY JUST SECOND OFFSETS
		boolean keepOnlyOutside = false; // if false keep inside
		String outputFileName = "PlacementTest.bmos";
		String extractedPath = "D:\\";
		String resPath = "D:\\LKS Debug!!!!1\\ROMs\\Release Game\\DATA\\files\\res\\";
		bFM.Utils.DebugPrint("Decoding Enemy Data into raw text");
		PCKGManager MonsterDataPack = new PCKGManager("MSDB");
		try
		{
			bFM.Utils.DebugPrint("Attempting to read msDB27.pac");
			MonsterDataPack = new PCKGManager(Files.readAllBytes(Paths.get(resPath+"msDB27.pac")));
		}
		catch (IOException e)
		{
			bFM.Utils.DebugPrint("Failed to Locate Monster Database Pack at: " + resPath+"msDB27.pac");
			bFM.Utils.DebugPrint("Program will return as it cannot continue.");
			return;
		}
		MissionObjectPlacementManager bMos = new MissionObjectPlacementManager(MonsterDataPack.getFile("MOP_14_CONST_PLACE.lst"), 
				MonsterDataPack.getFile("MOP_14_GROUP.lst"), MonsterDataPack.getFile("MOP_14_OBJECT.lst"), 
				MonsterDataPack.getFile("MOP_14_RANDOM_AREA.lst"), MonsterDataPack.getFile("MOP_14_RANDOM_POINT.lst"), 
				MonsterDataPack.getFile("MOP_14_AREA_DATA.lst"));
		
		if(filterZone!=null)
		{
			bMos.setConstraints(filterZone.x, filterZone.width, filterZone.y, filterZone.height, keepOnlyOutside, false);
		}
		if(modCode!=-1)
		{
			bMos.setFilterCode(modCode);
		}

		try 
		{
			bFM.Utils.DebugPrint("Attempting to write raw text at: " + extractedPath+outputFileName);
			Files.write(Paths.get(extractedPath+outputFileName) , Utils.encodeStringToBytes(bMos.toStringItems()));
		} catch (IOException e) 
		{
			bFM.Utils.DebugPrint("Failed to write bMos file at: " + extractedPath+outputFileName);
			return;
		}
		System.exit(0);
	}
	private static void testDefinition() throws IOException
	{
		MobAttackElemList AttackElem;
		boolean fromString = true;
		if(!fromString)
		{
			MobAiList ai = new MobAiList(Files.readAllBytes(Paths.get(inputPath+"MOB_24_AI.lst")));
			//Files.write(Paths.get(outputPath+"MobAiList.lst"), ai.toString().getBytes("Shift-JIS"));
			MobResAsn res = new MobResAsn(Files.readAllBytes(Paths.get(inputPath+"MOB_24_RES_ASN.lst")));
			//Files.write(Paths.get(outputPath+"MobResList.bMos"), res.toBMos().getBytes("Shift-JIS"));
			MobModList mod = new MobModList(Files.readAllBytes(Paths.get(inputPath+"MOB_24_MOD.lst")));
			//Files.write(Paths.get(outputPath+"MobModList.bMos"), mod.toBMos().getBytes("Shift-JIS"));
			MobAttackColList AttackCol = new MobAttackColList(Files.readAllBytes(Paths.get(inputPath+"MOB_24_ATK_COL.lst")));
			//Files.write(Paths.get(outputPath+"MobAttackCollisionList.lst"), AttackCol.toString().getBytes("Shift-JIS"));
			AttackElem = new MobAttackElemList(Files.readAllBytes(Paths.get(inputPath+"MOB_24_ATK_ELM.lst")));
			//Files.write(Paths.get(outputPath+"MobAttackElementList.lst"), AttackElem.toString().getBytes("Shift-JIS"));
			MobAttackInfoList AttackInfo = new MobAttackInfoList(Files.readAllBytes(Paths.get(inputPath+"MOB_24_ATK_INFO.lst")));
			//Files.write(Paths.get(outputPath+"MobAttackInfoList.lst"), AttackInfo.toString().getBytes("Shift-JIS"));
			MobDamageColList DamageCol = new MobDamageColList(Files.readAllBytes(Paths.get(inputPath+"MOB_24_DMG_COL.lst")));
			//Files.write(Paths.get(outputPath+"MobDamageCollisionList.lst"), DamageCol.toString().getBytes("Shift-JIS"));
			MobPresetTableList PresetTable = new MobPresetTableList(Files.readAllBytes(Paths.get(inputPath+"MOB_24_PRESET_TABLE.lst")));
			//Files.write(Paths.get(outputPath+"MobPresetTableList.lst"), PresetTable.toString().getBytes("Shift-JIS"));
		}
		else
		{
			AttackElem = new MobAttackElemList(Files.readAllLines(Paths.get(inputPath+"MobAttackElementList.lst")));
			Files.write(Paths.get(outputPath+"MOB_24_ATK_ELM.lst2"), AttackElem.toBytes());
		}

		//Test
		
		//Utils.testDifferences(ai.toBytes(), Files.readAllBytes(Paths.get(inputPath+"MOB_24_AI.lst"))); //100
		//Utils.testDifferences(res.toBytes(), Files.readAllBytes(Paths.get(inputPath+"MOB_24_RES_ASN.lst"))); //100
		//Utils.testDifferences(mod.toBytes(), Files.readAllBytes(Paths.get(inputPath+"MOB_24_MOD.lst"))); //100
		//Utils.testDifferences(AttackCol.toBytes(), Files.readAllBytes(Paths.get(inputPath+"MOB_24_ATK_COL.lst"))); //100
		Utils.testDifferences(AttackElem.toBytes(), Files.readAllBytes(Paths.get(inputPath+"MOB_24_ATK_ELM.lst"))); //100"
		//Utils.testDifferences(AttackInfo.toBytes(), Files.readAllBytes(Paths.get(inputPath+"MOB_24_ATK_INFO.lst"))); //100
		//Utils.testDifferences(DamageCol.toBytes(), Files.readAllBytes(Paths.get(inputPath+"MOB_24_DMG_COL.lst"))); //100
		//Utils.testDifferences(PresetTable.toBytes(), Files.readAllBytes(Paths.get(inputPath+"MOB_24_PRESET_TABLE.lst"))); //100
		
	}
	public static void testRepacDefinition() throws IOException
	{
		MobModList mod = new MobModList(Utils.bytesToStrs(Files.readAllBytes(Paths.get("D:\\ModTest\\MobModList.bMos"))), true);
		MobResAsn res = new MobResAsn(Utils.bytesToStrs(Files.readAllBytes(Paths.get("D:\\ModTest\\MobResList.bMos"))));
		MobAttackElemList AttackElem = new MobAttackElemList(Files.readAllLines(Paths.get(inputPath+"MobAttackElementList.lst")));
		PCKGManager MonsterDataBase = new PCKGManager("MSDB");
		try
		{
			MonsterDataBase = new PCKGManager(Files.readAllBytes(Paths.get(Settings.outputPath+"Resources\\msDB27.pac")));
		}
		catch (IOException e)
		{
			bFM.Utils.DebugPrint("Failed to locate package file at: "+Settings.outputPath+"Resources\\msDB27.pac");
			bFM.Utils.DebugPrint("Program will now return.");
			return;
		}
		//Utils.testDifferences(mod.toBytes(), MonsterDataBase.getFile("MOB_24_MOD.lst"));
		//Utils.testDifferences(res.toBytes(), MonsterDataBase.getFile("MOB_24_RES_ASN.lst"));
		//Files.write(Paths.get("D:\\ModTest\\MobResfile.bin"),res.toBytes());
		//Utils.testDifferences(mod.toBytes(), Files.readAllBytes(Paths.get(inputPath+"MOB_24_MOD.lst")));
		MonsterDataBase.addFile("MOB_24_ATK_ELM.lst", AttackElem.toBytes());
		MonsterDataBase.addFile("MOB_24_MOD.lst", mod.toBytes());
		MonsterDataBase.addFile("MOB_24_RES_ASN.lst", res.toBytes());
		Files.write(Paths.get(Settings.outputPath+"Resources\\msDB27.pac"), MonsterDataBase.toBytes());
	}
	private static void testBeta1() throws IOException
	{
		int dbNum = 18;
		String outputPath = "D:\\LKS Debug!!!!1\\ROMs\\BetaResources\\msDB"+dbNum+".pac\\";
		String inputPath = "D:\\ModTest\\BetaMonsters"+dbNum+".txt"; 
		int version = 11;
		ByteBuffer groupData = ByteBuffer.wrap(Files.readAllBytes(Paths.get(outputPath+"MOP_" + version + "_GROUP.lst")));
		groupData.position(4);
		ByteBuffer placeData = ByteBuffer.wrap(Files.readAllBytes(Paths.get(outputPath+"MOP_" + version + "_CONST_PLACE.lst")));
		placeData.position(4);
		ByteBuffer objectData = ByteBuffer.wrap(Files.readAllBytes(Paths.get(outputPath+"MOP_" + version + "_OBJECT.lst")));
		objectData.position(4);
		ArrayList<MobConstantPlace> Places = new ArrayList<MobConstantPlace>();
		ArrayList<MobGroup> Groups = new ArrayList<MobGroup>();
		ArrayList<MobObject> Objects = new ArrayList<MobObject>();
		while(placeData.hasRemaining())
		{
			Places.add(new BetaConstPlace(placeData, version));
		}
		while(groupData.hasRemaining())
		{
			Groups.add(new BetaGroup(groupData, version));
		}
		while(objectData.hasRemaining())
		{
			Objects.add(new BetaObject(objectData, version));
		}
		MissionObjectPlacementManager aaa = new MissionObjectPlacementManager(Places, Groups, Objects);
		Files.write(Paths.get(inputPath), Utils.encodeStringToBytes(aaa.toString()));
	}
	@SuppressWarnings("unused")
	private static void testMod() throws IOException
	{
		//MobModList mod = new MobModList(Files.readAllBytes(Paths.get(inputPath+"MOB_24_MOD.lst")));
		//Files.write(Paths.get(outputPath+"MobModList.lst"), mod.toString().getBytes("Shift-JIS"));
		MobModList mod2 = new MobModList(Files.readAllLines(Paths.get(inputPath+"MobModList.lst"), Charset.forName("Shift-JIS")), false);
		Files.write(Paths.get(outputPath+"MOB25MOD.lst"), mod2.toBytes());
		//bFM.Utils.testDifferences(mod.toBytes(), mod2.toBytes());
	}
	public static void RandomMonster() throws IOException
	{
		//PCKGManager test = new PCKGManager(Files.readAllBytes(Paths.get("D:\\Dolphin_Emulator\\Load\\Riivolution\\LKSMapTesting\\msDB27.pac")));
		PCKGManager test = new PCKGManager(Files.readAllBytes(Paths.get(inputPath)));
		
		//test.setLKSMode(true);
		
		
		MissionObjectPlacementManager BRM = new MissionObjectPlacementManager(test.getFile("MOP_14_CONST_PLACE.lst"), test.getFile("MOP_14_GROUP.lst"), test.getFile("MOP_14_OBJECT.lst"), test.getFile("MOP_14_RANDOM_AREA.lst"), test.getFile("MOP_14_RANDOM_POINT.lst"), test.getFile("MOP_14_AREA_DATA.lst"));
		//ConstantEnemyManager BRM = new ConstantEnemyManager(Files.readAllLines(Paths.get("C:\\Users\\benow\\OneDrive\\Documents\\LKS Mod\\Enemies.bMos")));
		//ConstantEnemyManager BRM = new ConstantEnemyManager(Files.readAllLines(Paths.get("C:\\Users\\benow\\OneDrive\\Documents\\LKS Mod\\EnemiesNew4.bMos")));
		//BRM.setConstraints(704,896,704,896,false, false);
		//BRM.setConstraints(702,768,702,768,false, true);
		
		//System.out.println("Testing Groups");
		//testDifferences(test.getFile("MOP_14_GROUP.lst"),BRM.getGroups());
		//System.out.println("Testing Objects");
		//testDifferences(test.getFile("MOP_14_OBJECT.lst"),BRM.getObjects());
		//System.out.println("Testing Constant Places");
		//testDifferences(test.getFile("MOP_14_CONST_PLACE.lst"),BRM.getConstantPlaces());
		System.out.println("Testing Area Data");
		bFM.Utils.testDifferences(test.getFile("MOP_14_AREA_DATA.lst"),BRM.getAreaDatas());
		System.out.println("Testing Random Area");
		bFM.Utils.testDifferences(test.getFile("MOP_14_RANDOM_AREA.lst"),BRM.getAreas());
		System.out.println("Testing Random Points");
		bFM.Utils.testDifferences(test.getFile("MOP_14_RANDOM_POINT.lst"),BRM.getPoints());
		
		
		
		//Files.write(Paths.get("D:\\LKS Mod\\EnemiesNew4.bMos"), BRM.toString().getBytes("Shift-JIS"));
		Files.write(Paths.get(outputPath+"MOP_14_GROUP.lst"), BRM.getGroups());
		Files.write(Paths.get(outputPath+"MOP_14_OBJECT.lst"), BRM.getObjects());
		Files.write(Paths.get(outputPath+"MOP_14_RANDOM_AREA.lst"), BRM.getAreas());
		Files.write(Paths.get(outputPath+"MOP_14_RANDOM_POINT.lst"), BRM.getPoints());
		Files.write(Paths.get(outputPath+"MOP_14_AREA_DATA.lst"), BRM.getAreaDatas());
		Files.write(Paths.get(outputPath+"MOP_14_CONST_PLACE.lst"), BRM.getConstantPlaces());
		testAll3(test);
		
		
		test.addFile("MOP_14_RANDOM_AREA.lst", BRM.getAreas());
		test.addFile("MOP_14_RANDOM_POINT.lst", BRM.getPoints());
		test.addFile("MOP_14_AREA_DATA.lst", BRM.getAreaDatas());
		test.addFile("MOP_14_CONST_PLACE.lst", BRM.getConstantPlaces());
		test.addFile("MOP_14_GROUP.lst", BRM.getGroups());
		test.addFile("MOP_14_OBJECT.lst", BRM.getObjects());
		//Files.write(Paths.get(outputPath+"test.pac") , test.getFile());
	}
	public static void testAll() throws IOException
	{
			MobAiList ai = new MobAiList(Files.readAllBytes(Paths.get(inputPath+"MOB_24_AI.lst")));
			Files.write(Paths.get(outputPath+"MobAiList.lst"), ai.toString().getBytes("Shift-JIS"));
			MobResAsn res = new MobResAsn(Files.readAllBytes(Paths.get(inputPath+"MOB_24_RESASN.lst")));
			Files.write(Paths.get(outputPath+"MobResList.lst"), res.toCSV().getBytes("Shift-JIS"));
			MobModList mod = new MobModList(Files.readAllBytes(Paths.get(inputPath+"MOB_24_MOD.lst")));
			Files.write(Paths.get(outputPath+"MobModList.lst"), mod.toString().getBytes("Shift-JIS"));
			MobGroupList group = new MobGroupList(Files.readAllBytes(Paths.get(inputPath+"MOP14GROUP.lst")));
			Files.write(Paths.get(outputPath+"MobGroupList.lst"), group.toString().getBytes("Shift-JIS"));
			MobObjectList object = new MobObjectList(Files.readAllBytes(Paths.get(inputPath+"MOP14OBJECT.lst")));
			Files.write(Paths.get(outputPath+"MobObjectList.lst"), object.toString().getBytes("Shift-JIS"));
			//MobConstantPlaceList cp = new MobConstantPlaceList(Files.readAllBytes(Paths.get(inputPath+"MOP14CONSTPLACE.lst")));
			//Files.write(Paths.get(outputPath+"MobConstantPlaceList.lst"), cp.toString().getBytes("Shift-JIS"));
			MobRandomPointList rp = new MobRandomPointList(Files.readAllBytes(Paths.get(inputPath+"MOP14RANDOMPOINT.lst")));
			Files.write(Paths.get(outputPath+"MobRandomPointList.lst"), rp.toString().getBytes("Shift-JIS"));
			MobRandomAreaList ra = new MobRandomAreaList(Files.readAllBytes(Paths.get(inputPath+"MOP14RANDOMAREA.lst")));
			Files.write(Paths.get(outputPath+"MobRandomAreaList.lst"), ra.toString().getBytes("Shift-JIS"));
			MobAreaDataList ad = new MobAreaDataList(Files.readAllBytes(Paths.get(inputPath+"MOP14AREADATA.lst")));
			Files.write(Paths.get(outputPath+"MobAreaDataList.lst"), ad.toString().getBytes("Shift-JIS"));
			MobRectangleList rect = new MobRectangleList(Files.readAllBytes(Paths.get(inputPath+"MOCR0RECTLIST.lst")));
			Files.write(Paths.get(outputPath+"MobRectangleList.lst"), rect.toString().getBytes("Shift-JIS"));
			MobAttackColList AttackCol = new MobAttackColList(Files.readAllBytes(Paths.get(inputPath+"MOB_24_ATKCOL.lst")));
			Files.write(Paths.get(outputPath+"MobAttackCollisionList.lst"), AttackCol.toString().getBytes("Shift-JIS"));
			
			Files.write(Paths.get(outputPath+"MonsterHP.lst"), mod.toHP().getBytes("Shift-JIS"));
	}
	public static void testAll2() throws IOException
	{
			MobGroupList group = new MobGroupList(Files.readAllBytes(Paths.get(inputPath+"MOP_01_GROUPLIST.lst")));
			Files.write(Paths.get(outputPath+"MobGroupList.lst"), group.toString().getBytes("Shift-JIS"));
			
	}
	public static void testAll3(PCKGManager src) throws IOException
	{
			MobAiList ai = new MobAiList(src.getFile("MOB__24__AI.lst"));
			Files.write(Paths.get(outputPath+"MobAiList.lst"), ai.toString().getBytes("Shift-JIS"));
			MobResAsn res = new MobResAsn(src.getFile("MOB__24__RES_ASN.lst"));
			Files.write(Paths.get(outputPath+"MobResList.lst"), res.toString().getBytes("Shift-JIS"));
			MobModList mod = new MobModList(src.getFile("MOB__24__MOD.lst"));
			Files.write(Paths.get(outputPath+"MobModList.lst"), mod.toString().getBytes("Shift-JIS"));
			MobGroupList group = new MobGroupList(src.getFile("MOP_14_GROUP.lst"));
			Files.write(Paths.get(outputPath+"MobGroupList.lst"), group.toString().getBytes("Shift-JIS"));
			MobObjectList object = new MobObjectList(src.getFile("MOP_14_OBJECT.lst"));
			Files.write(Paths.get(outputPath+"MobObjectList.lst"), object.toString().getBytes("Shift-JIS"));
			//MobConstantPlaceList cp = new MobConstantPlaceList(src.getFile("MOP14CONSTPLACE.lst")));
			//Files.write(Paths.get(outputPath+"MobConstantPlaceList.lst"), cp.toString().getBytes("Shift-JIS"));
			MobRandomPointList rp = new MobRandomPointList(src.getFile("MOP_14_RANDOM_POINT.lst"));
			Files.write(Paths.get(outputPath+"MobRandomPointList.lst"), rp.toString().getBytes("Shift-JIS"));
			MobRandomAreaList ra = new MobRandomAreaList(src.getFile("MOP_14_RANDOM_AREA.lst"));
			Files.write(Paths.get(outputPath+"MobRandomAreaList.lst"), ra.toString().getBytes("Shift-JIS"));
			MobAreaDataList ad = new MobAreaDataList(src.getFile("MOP_14_AREA_DATA.lst"));
			Files.write(Paths.get(outputPath+"MobAreaDataList.lst"), ad.toString().getBytes("Shift-JIS"));
			MobRectangleList rect = new MobRectangleList(src.getFile("MOCR0RECTLIST.lst"));
			Files.write(Paths.get(outputPath+"MobRectangleList.lst"), rect.toString().getBytes("Shift-JIS"));
			MobAttackColList AttackCol = new MobAttackColList(src.getFile("MOB_24_ATKCOL.lst"));
			Files.write(Paths.get(outputPath+"MobAttackCollisionList.lst"), AttackCol.toString().getBytes("Shift-JIS"));
			
			Files.write(Paths.get(outputPath+"MonsterHP.lst"), mod.toHP().getBytes("Shift-JIS"));
	}
}

