 package colReader;


import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

import DebugModeManager.Event.EventViewer;
import LZ10Convertor.LZ10Manager;
import PCKGManager.PCKGManager;
import ResourceManagers.CharacterDatabaseManager.CharacterDataBaseManager;
import ResourceManagers.ItemDatabaseManager.itemDatabaseManager;
import ResourceManagers.ItemDatabaseManager.itemPlaceManager;
import ResourceManagers.MSDBManager.Definition.MobModList;
import ResourceManagers.MSDBManager.Placement.MissionObjectPlacementManager;
import ResourceManagers.MapDatabaseManager.BuildingResourceList;
import ResourceManagers.MapDatabaseManager.exteriorPlaceList;
import ResourceManagers.MapDatabaseManager.mapDataBaseManager;
import WorldFileManager.fpInterpreter;
import bFM.Data;
import bFM.Settings;
import bFM.Utils;
import SystemDataManagers.MenuDB.CockpitLogManager;
import SystemDataManagers.MenuDB.MenuStringManager;
import SystemDataManagers.MenuDB.CameraData.CameraZoneList;
import SystemDataManagers.MenuDB.KingdomPlanManager.kingdomPlanManager;
import SystemDataManagers.QuestDB.MailManager;
import VMC.VMCConverter;
@SuppressWarnings("unused")
public class Main 
{
	public static boolean grid = true;
	
	static String name = "0314";//0510 soba
	static byte [] colData;
	static byte [] fpData;
	static String fpType = ".vfp";	
	public static void main(String[] args) 
	{
		Settings.importPath = "D:\\LKS Mod\\";
		Settings.outputPath = "D:\\Dolphin_Emulator\\Load\\Riivolution\\LKSMapTesting\\";
		bFM.Utils.setDebugOutput(true);
		colReader.ColReader.optimizeCollision = false;
		//bFM.Utils.autoEditSubPackFile = false;
		bFM.Utils.DebugPrint("Debug Data Enabled");
		try{tester();} catch (IOException e) {e.printStackTrace();}
		//decodeCollision();
		//encodeCollision();
		//encodeFixedPoints();
		//decodeFixedPoints();
		//menuDBManager();
		//packBuilding("SurfaceToCaveHole");
		//packGrid();
		//mapDataBase();
		//decodeLightZones();
		//encodeLightZones();
		//message();				
		//enemyManagers();
		//decodeEnemyData("CoinPurse.bMos", 4010);
		//itemManager();
		//decodeItems(1);
		//encodeItems(1);
		//MailManager();
		//decodeCollision("D:\\LKS Debug!!!!1\\ROMs\\Extracted\\zpack\\mapBoot2.pac\\");
		//deCompressEventText();
		//compressEventText(1);
		//characterDataBaseManager();
		//encodeVMC("CH01_EVT_000");
		//encodeDebugEventViewer();
	}
	private static void decodeEnemyData(String outputFileName)	
	{
		decodeEnemyData(outputFileName, -1);
	}
	private static void decodeEnemyData(String outputFileName, int modCode)	
	{
		String outputPath = "D:\\LKS Debug!!!!1\\ROMs\\Release Game\\DATA\\files\\res\\";
		bFM.Utils.DebugPrint("Decoding Enemy Data into raw text");
		PCKGManager MonsterDataPack = new PCKGManager("MSDB");
		try
		{
			bFM.Utils.DebugPrint("Attempting to read msDB27.pac");
			MonsterDataPack = new PCKGManager(Files.readAllBytes(Paths.get(outputPath+"msDB27.pac")));
		}
		catch (IOException e)
		{
			bFM.Utils.DebugPrint("Failed to Locate Monster Database Pack at: " + outputPath+"msDB27.pac");
			bFM.Utils.DebugPrint("Program will return as it cannot continue.");
			return;
		}
		MissionObjectPlacementManager bMos = new MissionObjectPlacementManager(MonsterDataPack.getFile("MOP_14_CONST_PLACE.lst"), 
				MonsterDataPack.getFile("MOP_14_GROUP.lst"), MonsterDataPack.getFile("MOP_14_OBJECT.lst"), 
				MonsterDataPack.getFile("MOP_14_RANDOM_AREA.lst"), MonsterDataPack.getFile("MOP_14_RANDOM_POINT.lst"), 
				MonsterDataPack.getFile("MOP_14_AREA_DATA.lst"));
		
		if(modCode!=-1)
		{
			bMos.setFilterCode(modCode);
		}

		try 
		{
			bFM.Utils.DebugPrint("Attempting to write raw text at: " + Settings.importPath+outputFileName);
			Files.write(Paths.get(Settings.importPath+outputFileName) , Utils.encodeStringToBytes(bMos.toString()));
		} catch (IOException e) 
		{
			bFM.Utils.DebugPrint("Failed to write bMos file at: " + Settings.importPath+outputFileName);
			return;
		}
	}
	private static void MailManager() 
	{
		//MailManager(0);
		MailManager(1);
		//MailManager(2);
		//MailManager(3);
		//MailManager(4);
		//MailManager(5);
	}
	private static void MailManager(int languageCode) 
	{
		MailManager mail = new MailManager(languageCode);
		//System.out.println(mail.normalToString());
		try 
		{
			mail = new MailManager(Files.readAllLines(Paths.get(Settings.importPath+"NormalMail.txt")), Settings.importPath);
		} catch (IOException e) 
		{
			System.out.println("Failed to locate the Normal Quest File in the directory: " + Settings.importPath);
			try 
			{
				Files.write(Paths.get(Settings.importPath+"NormalMail.txt"), Utils.encodeStringToBytes(mail.normalToString()));
			} catch (IOException q) 
			{
				System.out.println("Failed to write Normal Quest File at: " + Settings.importPath+"NormalMail.txt");
			}
			
			
			return;
		}
		mail.toPac(1);

		
	}
	private static void decodeLightZones() 
	{
		String mapBootPath = Settings.outputPath + "mapBoot2.pac";
		PCKGManager mapBootPack = new PCKGManager("mapBoot2.pac");
		try
		{
			mapBootPack = new PCKGManager(Files.readAllBytes(Paths.get(mapBootPath)));
		}
		catch(IOException e) 
		{
			System.out.println("Could not locate mapBoot pack at: " + mapBootPath);
			e.printStackTrace();
		}
		//Extract allfield.lfp
		byte[] data = mapBootPack.getFile("allfield.lfp");
		fpInterpreter lightingFixedPoints = new fpInterpreter(data);
		try {
			Files.write(Paths.get(Settings.importPath+"AllLightZones.blfp"), Utils.encodeStringToBytes(lightingFixedPoints.toBFP()));
		} catch (IOException e) 
		{
			System.out.println("Failed to write .lfp output file");
			e.printStackTrace();
		}
	}
	private static void menuDBManager() 
	{
		//menuDBManager(0);
		menuDBManager(1);
		//menuDBManager(2);
		//menuDBManager(3);								
		//menuDBManager(4);
		//menuDBManager(5);
	}
	private static void menuDBManager(int languageCode)
	{
		String outputPath = Settings.outputPath + "System Data\\Menu Data\\";
		PCKGManager menuDatabase = new PCKGManager("menuDB");
		try 
		{
			bFM.Utils.DebugPrint("Attempting to read menuDB pack at: " + outputPath);
			menuDatabase = new PCKGManager(Files.readAllBytes(Paths.get(outputPath + "menuDB_6_"+languageCode+".pac")));
		} catch (IOException e) 
		{
			bFM.Utils.DebugPrint("Failed to read menuDB pack.");
			return;
		}
		
		//menuDatabase.replaceFile("MenuString.bin",encodeMenuString(menuDatabase.getFile("MenuString.bin")));
		//menuDatabase.replaceFile("CockpitLog.bin",encodeCockpitLog(menuDatabase.getFile("CockpitLog.bin")));
		menuDatabase.replaceFile("KingdomPlan.bin",encodeKingdomPlan(menuDatabase.getFile("KingdomPlan.bin")));
		//menuDatabase.replaceFile("Movie.bin", MovieManager(menuDatabase.getFile("Movie.bin")));
		menuDatabase.replaceFile("CameraData.bin", encodeCameraZones(menuDatabase.getFile("CameraData.bin")));
		
		
		
		try 
		{
			bFM.Utils.DebugPrint("Attempting to write menuDB pack");
			Files.write(Paths.get(outputPath + "menuDB_6_"+languageCode+".pac") , menuDatabase.getFile());
			bFM.Utils.DebugPrint("Success!");
		} catch (IOException e) 
		{
			bFM.Utils.DebugPrint("Failed to write menuDB pack");
			return;
		}
	}
	private static byte[] encodeMenuString(byte[] data) 
	{
		throw new IllegalArgumentException("This is broken");
		bFM.Utils.DebugPrint("Attempting to Encode Menu String Data");
		MenuStringManager MenuStringData;
		try {
			
			MenuStringData = new MenuStringManager(Files.readAllLines(Paths.get(Settings.importPath+"MenuString.txt")));
			bFM.Utils.DebugPrint("Success");
		} catch (IOException e) 
		{
			bFM.Utils.DebugPrint("Unable to find an extracted Menu String File. Attempting to extract one");
			decodeMenuString(data);
			return data;
		}
		return MenuStringData.toBytes();
	}
	private static void decodeMenuString(byte[] data)
	{
		MenuStringManager MenuStringData = new MenuStringManager(data);
		try 
		{
			bFM.Utils.DebugPrint("Attempting to write Menu String file at: " + Settings.importPath+"MenuString.txt");
			Files.write(Paths.get(Settings.importPath+"MenuString.txt"), MenuStringData.toString().getBytes(Charset.forName("Ascii")));
		} catch (IOException e) 
		{
			bFM.Utils.DebugPrint("Failed to write file");
		}
	}
	private static byte[] encodeKingdomPlan(byte[] data) 
	{
		bFM.Utils.DebugPrint("Attempting to Encode Kingdom Plan Data");
		kingdomPlanManager kingdomPlanData;
		try {
			kingdomPlanData = new kingdomPlanManager(Files.readAllLines(Paths.get(Settings.importPath+"KingdomPlan.txt")));
			bFM.Utils.DebugPrint("Success");
		} catch (IOException e) 
		{
			bFM.Utils.DebugPrint("Unable to find an extracted Kingdom Plan File. Attempting to extract one");
			decodeKingdomPlan(data);
			return data;
		}
		return kingdomPlanData.toBytes();
	}
	private static void decodeKingdomPlan(byte[] data)
	{
		kingdomPlanManager kingdomPlanData = new kingdomPlanManager(data);
		try 
		{
			bFM.Utils.DebugPrint("Attempting to write Kingdom Plan file at: " + Settings.importPath+"KingdomPlan.txt");
			Files.write(Paths.get(Settings.importPath+"KingdomPlan.txt"), Utils.encodeStringToBytes(kingdomPlanData.toString()));
		} catch (IOException e) 
		{
			bFM.Utils.DebugPrint("Failed to write file");
		}
	}
	private static byte[] encodeCockpitLog(byte[] data) 
	{
		bFM.Utils.DebugPrint("Attempting to Encode Cockpit Log Data");
		CockpitLogManager CockpitLogData;
		try {
			CockpitLogData = new CockpitLogManager(Files.readAllLines(Paths.get(Settings.importPath+"CockpitLog.txt")));
			bFM.Utils.DebugPrint("Success");
		} catch (IOException e) 
		{
			bFM.Utils.DebugPrint("Unable to find an extracted Cockpit Log File. Attempting to extract one");
			decodeCockpitLog(data);
			return data;
		}
		return CockpitLogData.toBytes();
	}
	private static void decodeCockpitLog(byte[] data)
	{
		CockpitLogManager CockpitLogData = new CockpitLogManager(data);
		try 
		{
			bFM.Utils.DebugPrint("Attempting to write Cockpit Log file at: " + Settings.importPath+"CockpitLog.txt");
			Files.write(Paths.get(Settings.importPath+"CockpitLog.txt"), Utils.encodeStringToBytes(CockpitLogData.toString()));
		} catch (IOException e) 
		{
			bFM.Utils.DebugPrint("Failed to write file");
		}
	}
	private static byte[] MovieManager(byte[] data)
	{
		PCKGManager MovieBookPack = new PCKGManager(data);
		File directory = new File(Settings.importPath+"Menu Database\\Movie Book");
		File[] fileList = directory.listFiles();
		for(int i = 0; i<fileList.length;i++)
		{
			try 
			{
				bFM.Utils.DebugPrint("Attempting to read file at: " + fileList[i].toPath());
				MovieBookPack.addFile(fileList[i].getName(), Files.readAllBytes(fileList[i].toPath()));
				bFM.Utils.DebugPrint("Success");
			} catch (IOException e) 
			{
				bFM.Utils.DebugPrint("Failed to read file");
			}
		}
		return MovieBookPack.getFile();
	}
	private static void enemyManagers()
	{
		encodeEnemyData("");
		encodeEnemyData("_EASY");
		encodeEnemyData("_HARD");
		encodeEnemyData("_HELL");
	}
	private static void encodeLightZones()
	{
		fpInterpreter fixedPoints = null;
		try 
		{
			bFM.Utils.DebugPrint("Attempting to read Light Zone File at: " + Settings.importPath+"LightZones.blfp");
			fixedPoints = new fpInterpreter(Files.readAllLines(Paths.get(Settings.importPath+"LightZones.blfp")),"LFP");
		} catch (IOException e) 
		{
			bFM.Utils.DebugPrint("Failed to read Light Zone File. Will attempt to decode from a pack.");
			decodeLightZones();
			return;
		}
		byte[] lfpData = fixedPoints.toBytes();
		PCKGManager mapBootPack = new PCKGManager("mapBoot");
		try 
		{
			bFM.Utils.DebugPrint("Attempting to read Map Boot pack at: " + Settings.outputPath + "mapBoot2.pac");
			mapBootPack = new PCKGManager(Files.readAllBytes(Paths.get(Settings.outputPath + "mapBoot2.pac")));
		} catch (IOException e) 
		{
			bFM.Utils.DebugPrint("Failed to read Map Boot Pack");
			return;
		}
		mapBootPack.addFile("all_field.lfp", lfpData);
		try 
		{
			bFM.Utils.DebugPrint("Attempting to write Map Boot pack");
			Files.write(Paths.get(Settings.outputPath + "mapBoot2.pac"), mapBootPack.getFile());
		} catch (IOException e) 
		{
			bFM.Utils.DebugPrint("Failed to write Map Boot Pack");
			return;
		}
	}
	private static void decodeCameraZones(byte[] data)
	{					
		
		PCKGManager cameraZonePack = new PCKGManager(data);
		CameraZoneList cameraZones = new CameraZoneList(cameraZonePack.getFile("List"),cameraZonePack.getFile("Name"));
		try 
		{
			bFM.Utils.DebugPrint("Attempting to write Camera Zone file at: " + Settings.importPath+"CameraZones.bcz");
			Files.write(Paths.get(Settings.importPath+"CameraZones.bcz"), Utils.encodeStringToBytes(cameraZones.toString()));
		} catch (IOException e) 
		{
			bFM.Utils.DebugPrint("Failed to write file");
		}
	}
	private static byte[] encodeCameraZones(byte[] data)
	{
		bFM.Utils.DebugPrint("Attempting to Encode Camera Zones");
		CameraZoneList cameraZonesPack;
		try {
			cameraZonesPack = new CameraZoneList(Files.readAllLines(Paths.get(Settings.importPath+"CameraZones.bcz")));
			bFM.Utils.DebugPrint("Success");
		} catch (IOException e) 
		{
			bFM.Utils.DebugPrint("Unable to find an extracted Camera Zone File. Attempting to extract one");
			decodeCameraZones(data);
			return data;
		}
		return cameraZonesPack.toPAC();
	}
	private static void itemManager()
	{
		encodeItems(0);
		encodeItems(1);
		encodeItems(2);
		encodeItems(3);
		encodeItems(4);
		encodeItems(5);
	}
	private static void encodeItems(int language)
	{
		bFM.Utils.DebugPrint("Attempting to encode item places");
		String outputPath = Settings.outputPath + "Resources\\";
		String importPath = Settings.importPath + "Resources\\Item Database.txt";
		List<String> Lines = null;
		byte[] data = new byte[0];
		try 
		{
			bFM.Utils.DebugPrint("Reading Item Database File at: " + importPath);
			Lines = Files.readAllLines(Paths.get(importPath), Charset.forName("Shift_JIS"));
		} catch (IOException e) 
		{
			bFM.Utils.DebugPrint("Failed to read Item Database File");
			e.printStackTrace();
			return;
		}
		itemDatabaseManager itemDB = new itemDatabaseManager(Lines);
		try 
		{
			bFM.Utils.DebugPrint("Attempting to write Item Database Pack.");
			Files.write(Paths.get(outputPath + "itemDB3_"+language+".pac") , itemDB.toBytes());
		} catch (IOException e) 
		{
			bFM.Utils.DebugPrint("Failed to write pack");
		}
		//Legacy code incase i need it to get the item placement data from the old mod
		//itemPlaceManager items = null;
		//try 
		//{
			//bFM.Utils.DebugPrint("Attempting to read item Places file at: " + importPath + "Item Places.txt");
			//items = new itemPlaceManager(Files.readAllLines(Paths.get(importPath+"Item Places.txt")));
		//} catch (IOException e) 
		//{
			//bFM.Utils.DebugPrint("Failed to read file");
		//}
		//itemDB.addFile("itemPlace.bin", items.toBytes());
		//try 
		//{
			//bFM.Utils.DebugPrint("Attempting to write Item Database Pack.");
			//Files.write(Paths.get(outputPath + "itemDB3_"+language+".pac") , itemDB.getFile());
		//} catch (IOException e) 
		//{
			//bFM.Utils.DebugPrint("Failed to write pack");
		//}
	}
	private static void decodeItems(int language)
	{
		bFM.Utils.DebugPrint("Attempting to decode item places");
		String outputPath = Settings.outputPath + "Resources\\";
		String importPath = Settings.importPath + "Resources\\";
		byte[] itemDB = new byte[0];
		try 
		{
			bFM.Utils.DebugPrint("Reading Item Pack at: " + outputPath+"itemDB3_"+language+".pac");
			itemDB = Files.readAllBytes(Paths.get(outputPath + "itemDB3_"+language+".pac"));
		} catch (IOException e) 
		{
			bFM.Utils.DebugPrint("Failed to read Item Pack");
		}
		itemDatabaseManager items = new itemDatabaseManager(itemDB);
		try 
		{
			bFM.Utils.DebugPrint("Attempting to write Item Database File.");
			Files.write(Paths.get(importPath+"Item Database.txt") , Utils.encodeStringToBytes(items.toString()));
		} catch (IOException e) 
		{
			bFM.Utils.DebugPrint("Failed to write decoded file");
		}
	}
	private static void encodeEnemyData(String difficulty)	
	{
		String outputPath = Settings.outputPath + "Resources\\";
		String importPath = Settings.importPath + "Resources\\Monster Database\\";
		bFM.Utils.DebugPrint("Encoding Enemies "+ difficulty);
		MissionObjectPlacementManager bMos;
		MobModList enemyModifications = null;
		try
		{
			bMos = new MissionObjectPlacementManager(Files.readAllLines(Paths.get(importPath+"Enemies.bMos")));
		}
		catch (IOException e)
		{
			bFM.Utils.DebugPrint("Failed to locate file at: "+importPath+"Enemies.bMos");
			bFM.Utils.DebugPrint("Program will now return.");
			return;
		}
		try
		{
			enemyModifications = new MobModList(Files.readAllLines(Paths.get(importPath+"MobModList"+difficulty+".lst"), Charset.forName("Shift-JIS")));
		}
		catch (IOException e)
		{
			bFM.Utils.DebugPrint("Failed to locate file at: "+importPath+"MobModList"+difficulty+".lst");
		}
		//bMos.setConstraints(704,896,704,896,false, true);
		
		PCKGManager MonsterDataBase = new PCKGManager("MSDB");
		try
		{
			MonsterDataBase = new PCKGManager(Files.readAllBytes(Paths.get(outputPath+"msDB27"+difficulty+".pac")));
		}
		catch (IOException e)
		{
			bFM.Utils.DebugPrint("Failed to locate package file at: "+outputPath+"msDB27"+difficulty+".pac");
			bFM.Utils.DebugPrint("Program will now return.");
			return;
		}
		MonsterDataBase.addFile("MOP_14_RANDOM_AREA.lst", bMos.getAreas());
		MonsterDataBase.addFile("MOP_14_RANDOM_POINT.lst", bMos.getPoints());
		MonsterDataBase.addFile("MOP_14_AREA_DATA.lst", bMos.getAreaDatas());
		MonsterDataBase.addFile("MOP_14_CONST_PLACE.lst", bMos.getConstantPlaces());
		MonsterDataBase.addFile("MOP_14_GROUP.lst", bMos.getGroups());
		MonsterDataBase.addFile("MOP_14_OBJECT.lst", bMos.getObjects());
		
		if(enemyModifications!=null)
		{
			MonsterDataBase.addFile("MOB_24_MOD.lst", enemyModifications.toBytes());
		}
		else
		{
			try 
			{
				Files.write(Paths.get(importPath+"MobModList"+difficulty+".lst") , new MobModList(MonsterDataBase.getFile("MOB_24_MOD.lst")).toString().getBytes(Charset.forName("Shift-JIS")));
			} catch (IOException e) 
			{
				bFM.Utils.DebugPrint("Failed to write Mod file at: "+importPath+"MobModList"+difficulty+".lst");
			}
		}
		
		try 
		{
			Files.write(Paths.get(outputPath+"msDB27"+difficulty+".pac") , MonsterDataBase.getFile());
		} catch (IOException e) 
		{
			bFM.Utils.DebugPrint("Failed to write Monster Data Base Pack at: " + outputPath+"msDB27"+difficulty+".pac");
			bFM.Utils.DebugPrint("Program will now return");
			return;
		}
		bFM.Utils.DebugPrint("Sucessfully encoded Monster data of difficulty: "+difficulty);
	}
	private static void tester() throws IOException
	{
		//fpInterpreter fixedPoints;
		//fixedPoints = new fpInterpreter(Files.readAllBytes(Paths.get(importPath+"allfield.lfp")));
		//Files.write(Paths.get(importPath+"darkvalley.bfp"), fixedPoints.toBFP(704,896,704,896).getBytes());
	
		
		//PCKGManager test2 = new PCKGManager(Files.readAllBytes(Paths.get(outputPath + "mapBoot2.pac")));
		//test2.addFile(name+".brres", Files.readAllBytes(Paths.get(importPath+"allfield.lfp")));
		//test2.removeFile("1211.lfp");
		//Files.write(Paths.get(outputPath + "mapBoot2.pac") , test2.getFile());
		
		//PCKGManager test3 = new PCKGManager(Files.readAllBytes(Paths.get(outputPath + "menuDB_6_1.pac")));
		//CameraZoneList test4 = new CameraZoneList(test3.getFile("List"),test3.getFile("Name"));
		//System.out.println(test4);
		
		//exteriorPlaceList.filter(704,896,704,896, true);
		//PCKGManager mapDB = new PCKGManager(Files.readAllBytes(Paths.get(outputPath + "mapDB0.pac")));
		//Files.write(Paths.get(importPath+"extPlaceTemp.lst"), mapDB.getFile("extPlace1.lst"));
		//exteriorPlaceList buildings = new exteriorPlaceList(Files.readAllLines(Paths.get(importPath+"extPlaceTemp.lst")));
		//Files.write(Paths.get(importPath+"extPlace1.lst"), buildings.toString().getBytes());
		
		//byte[] vmcData = Files.readAllBytes(Paths.get("D:\\Dolphin_Emulator\\Load\\Riivolution\\LKSMapTesting\\Events\\New folder\\CH01_EVT_001.vmc0"));
		//byte[] vmcData = Files.readAllBytes(Paths.get("D:\\LKS Debug!!!!1\\ROMs\\Extracted\\event\\script\\event\\CH02_EVT_101.vmc0"));
		//VMCConverter vmcData1 = new VMCConverter(vmcData);
		//bFM.Utils.testDifferences(vmcData, vmcData1.toBytes());
		//Files.write(Paths.get("D:\\Dolphin_Emulator\\Load\\Riivolution\\LKSMapTesting\\eventTest.txt"), vmcData1.toString().getBytes(Charset.forName("Shift-JIS")));
		//VMCConverter vmcData2 = new VMCConverter(Files.readAllLines(Paths.get("D:\\Dolphin_Emulator\\Load\\Riivolution\\LKSMapTesting\\eventTest.txt"), Charset.forName("Shift-JIS")));
		//bFM.Utils.testDifferences(vmcData, vmcData2.toBytes());
		//Files.write(Paths.get("D:\\Dolphin_Emulator\\Load\\Riivolution\\LKSMapTesting\\eventTest.vmc"), vmcData1.toBytes());
		//Files.write(Paths.get("D:\\Dolphin_Emulator\\Load\\Riivolution\\LKSMapTesting\\eventTest2.vmc"), vmcData2.toBytes());
		//byte[] file1 = new PCKGManager(LZ10Manager.decompress("D:\\LKS Debug!!!!1\\ROMs\\Extracted\\event\\message\\1\\mes_LZ.bin")).getFile(7);
		//byte[] file2 = LZ10Manager.compress(LZ10Manager.decompress(new PCKGManager(LZ10Manager.decompress("D:\\LKS Debug!!!!1\\ROMs\\Extracted\\event\\message\\1\\mes_LZ.bin")).getFile(7)));
		//byte[] file3
		//byte[] file4
		//Files.write(Paths.get("D:\\file3.bin"), file1);
		//Files.write(Paths.get("D:\\file4.bin"), file2);
		//bFM.Utils.testDifferences(file1, file2);
		//System.out.println(Arrays.toString(file1));
		//System.out.println(Arrays.toString(file2));
		
		
		//PCKGManager test = new PCKGManager(Files.readAllBytes(Paths.get("D:\\Dolphin_Emulator\\Load\\Riivolution\\LKSMapTesting\\Characters\\cbData1.pac")));
		//PCKGManager tests = new PCKGManager("cb0010.pac");
		//tests.addFile("cb0010.brres", Files.readAllBytes(Paths.get("D:\\LKS Mod\\cb0010.brres")));
		//tests.addFile("chr.cfg", Files.readAllBytes(Paths.get("D:\\LKS Mod\\chr.cfg")));
		//test.addFile("cb0150.pcha", Files.readAllBytes(Paths.get("D:\\LKS Mod\\cb0150.pcha")));
		//Files.write(Paths.get("D:\\Dolphin_Emulator\\Load\\Riivolution\\LKSMapTesting\\Characters\\cbData1.pac"), test.getFile());
		
		
		
		//final String outputPath = Main.outputPath + "Debug\\EventViewer\\";
		//final String importPath = Main.importPath + "Debug\\EventViewer\\";
		//final String NormalEvents = "evlist.bin";
		//final String NormalEventCSV = "Event List.csv";
		//EventViewer eventData = new EventViewer(Files.readAllBytes(Paths.get(outputPath + NormalEvents)));
		//EventViewer eventData = new EventViewer(Files.readAllLines(Paths.get(importPath + NormalEventCSV), Charset.forName("Shift-JIS")));
		//bFM.Utils.testDifferences(Files.readAllBytes(Paths.get(outputPath + NormalEvents)), eventData.toBytes());
		
		//Finally testing the col reader, the og file
		//ColReader.optimizeCollision(false);
		//ColReader colFile = new ColReader(Files.readAllBytes(Paths.get("D:\\LKS Debug!!!!1\\ROMs\\Extracted\\map\\wg\\wg0610.pac\\0610.col")));
		//byte[] data = colFile.getBytes();
		//bFM.Utils.testDifferences(Files.readAllBytes(Paths.get("D:\\LKS Debug!!!!1\\ROMs\\Extracted\\map\\wg\\wg0610.pac\\0610.col")), data);
		//Files.write(Paths.get("D:\\LKS Debug!!!!1\\ROMs\\Extracted\\map\\wg\\wg0610.pac\\0610.col2"), data);
		
		//bFM.Utils.testDifferences(Files.readAllBytes(Paths.get("D:\\Dolphin_Emulator\\Load\\Riivolution\\LKSMapTesting\\Resources\\chrDB0.pac")), Files.readAllBytes(Paths.get("D:\\Dolphin_Emulator\\Load\\Riivolution\\LKSMapTesting\\Resources\\chrDB0 - Copy.pac")));
		
		
		//List<String> Lines = Files.readAllLines(Paths.get(Settings.importPath + "Resources\\Item Database.txt"), Charset.forName("Shift_JIS"));
		//itemDatabaseManager itemDB = new itemDatabaseManager(Lines);
		//bFM.Utils.testDifferences(Files.readAllBytes(Paths.get(Settings.outputPath + "Resources\\" +  "itemDB3_"+1+".pac")) , itemDB.toBytes());
		
		MailManager mail = new MailManager(Files.readAllBytes(Paths.get("D:\\LKS Debug!!!!1\\ROMs\\Release Game\\DATA\\files\\sys\\menuDB\\questdata4_1.bin")));
		System.out.println(mail.normalToCSV());
	}
	private static void compressEventText()
	{
		for(int i = 0; i < 6; i++)
		{
			compressEventText(i);
		}
	}
	private static void compressEventText(int languageCode)
	{
		String importPath = Settings.importPath + "Events\\Text\\" + Utils.getLanguage(languageCode) + '\\';
		String outputPath = Settings.outputPath + "Message\\" + Utils.getLanguage(languageCode) + '\\';
		
		//read the compressed pack file
		PCKGManager eventTextPac = new PCKGManager(LZ10Manager.decompress(outputPath + "mes_LZ.bin"));
		
		
		File[] fileList = new File(importPath).listFiles();
		
		for(int i = 0; i < fileList.length; i++)
		{
			//change the file ending
			String fileName = fileList[i].getName().substring(0, fileList[i].getName().indexOf(".txt")) + "_LZ.bin";
			
			try 
			{
				eventTextPac.addFile(fileName, LZ10Manager.compress(Files.readAllBytes(fileList[i].toPath())));
			} catch (IOException e) 
			{
				bFM.Utils.DebugPrint("Failed to Read Event Text File");
			}
		}
		
		try {
			bFM.Utils.testDifferences(LZ10Manager.decompress(Files.readAllBytes(Paths.get(outputPath + "mes_LZ.bin")))
					, eventTextPac.getFile());
			//bFM.Utils.testDifferences(Files.readAllBytes(Paths.get(outputPath + "mes_LZ.bin")), LZ10Manager.compress(eventTextPac.getFile()));
			//Files.write(Paths.get(outputPath + "mes_LZ.bin1"), LZ10Manager.decompress(Files.readAllBytes(Paths.get(outputPath + "mes_LZ.bin"))));
			//Files.write(Paths.get(outputPath + "mes_LZ.bin2"), eventTextPac.getFile());
			Files.write(Paths.get(outputPath + "mes_LZ.bin"), LZ10Manager.compress(eventTextPac.getFile()));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		//Write the compressed package file
		
		
	}
	private static void deCompressEventText()
	{
		for(int i = 0; i < 6; i++)
		{
			deCompressEventText(i);
		}
	}
	private static void deCompressEventText(int languageCode)
	{
		//deCompress and extract and decompress the event text files
		
		String importPath = Settings.importPath + "Events\\Text\\" + Utils.getLanguage(languageCode) + '\\';
		String outputPath = Settings.outputPath + "Message\\" + Utils.getLanguage(languageCode) + '\\';
		
		
		PCKGManager eventTextPac = new PCKGManager(LZ10Manager.decompress(outputPath + "mes_LZ.bin"));
		
		
		for(int i = 0; i < eventTextPac.getFileAmount(); i++)
		{
			try
			{
				String fileName = eventTextPac.getName(i).substring(0, eventTextPac.getName(i).indexOf("_LZ"));
				Files.write(Paths.get(importPath+fileName+".txt"), LZ10Manager.decompress(eventTextPac.getFile(i)));
			}
			catch (IOException  e)
			{
				bFM.Utils.DebugPrint("Failed to write Event Text File");
			}
		}
		
		
	}
	private static void characterDataBaseManager()
	{
		String importDirectory = Settings.importPath + "Resources\\Character Database\\";
		CharacterDataBaseManager cdb = new CharacterDataBaseManager(Settings.outputPath + "Resources\\chrDB0.pac");
		//cdb.importIndex(importDirectory);
		//cdb.importJoin(importDirectory);
		//cdb.importCharacters(importDirectory);
		//cdb.importPrice(importDirectory);
		
		cdb.writeFile(Settings.outputPath + "Resources\\chrDB02.pac");
	}
	private static void decodeCollision()
	{
		String importPath = Settings.importPath;
		if(grid)
		{
			importPath += name + "\\";
		}
		else
		{
			importPath += "Buildings\\bl";
		}
		decodeCollision(importPath);
	}
	private static void decodeCollision(String importPath)
	{
		ColReader colFile = new ColReader();
		ColReader.optimizeCollision(true);
		try 
		{
			colFile = new ColReader(Files.readAllBytes(Paths.get(importPath+name+".col")));
		} catch (IOException e) 
		{
			bFM.Utils.DebugPrint("Failed to read Collision file at: " + importPath+name+".col");
		}
		try {
			Files.write(Paths.get((importPath+ name+".obj")), Utils.encodeStringToBytes(colFile.toString()));
		} catch (IOException e) 
		{
			bFM.Utils.DebugPrint("Failed to write extracted Collision file at: " + importPath+ name+".obj");
		}
		
		colData =colFile.getBytes();
		bFM.Utils.DebugPrint("Collision Finished Sucessfully");
	}
	private static void encodeCollision()
	{
		String importPath = Settings.importPath;
		if(grid)
		{
			importPath += name + "\\";
		}
		else
		{
			importPath += "Buildings\\";
		}
		ColReader colFile;
		ColReader.optimizeCollision(true);
		colFile = new ColReader(name);
		
		bFM.Utils.DebugPrint("Reading .obj file into collision data");
		try 
		{
			colFile.importOBJ(Paths.get(importPath+name+".obj"));
		} catch (IOException e) 
		{
			bFM.Utils.DebugPrint("Failed to read .obj file at: "+importPath+name+".obj");
		}
		try 
		{
			Files.write(Paths.get(importPath + name + ".col"), colFile.getBytes());
		} catch (IOException e) 
		{
			bFM.Utils.DebugPrint("Failed to write completed collision file at: " + importPath + name + ".col");
		}
		
		colData = colFile.getBytes();
	}
	private static void packGrid() 
	{
		String importPath = Settings.importPath;
		String outputPath = Settings.outputPath + "Map\\";
		String modelCode = "";
		if(grid)
		{
			importPath += name + "\\";
			outputPath += "World\\";
			modelCode = "wg";
		}
		else
		{
			importPath += "Buildings\\";
			outputPath += "Building\\";
			modelCode = "bl";
		}
		//Create .pac File
		PCKGManager packFile = new PCKGManager(modelCode+name+".pac");
		String PCKGPath = outputPath+modelCode+name+".pac";
		try
		{
			packFile = new PCKGManager(Files.readAllBytes(Paths.get(PCKGPath)));
		}
		catch(IOException e) 
		{
			bFM.Utils.DebugPrint("Could not find preexisting .pac file at \"" + PCKGPath + "\". Creating new one");
		}
		//Adding Model File
		String modelPath = importPath+modelCode + name + ".brres";
		boolean hasModel = false;
		try
		{
			if(packFile.getFile(modelCode + name + ".brres")!=null)
			{
				hasModel = true;
			}
			packFile.addFile(modelCode + name + ".brres", Files.readAllBytes(Paths.get(modelPath)));
		}
		catch(IOException e) 
		{
			bFM.Utils.DebugPrint("Warning Could not find .bress file at \"" + modelPath + "\". ");
			if(hasModel)
			{
				bFM.Utils.DebugPrint("The Program will continue as the .pac file already had this file.");
			}
			else
			{
				bFM.Utils.DebugPrint("The Program will now stop as this file type is required, and could not be found in the .pac File.");
				return;
			}
			
		}
		//Adding .col File
		String colPath = importPath + name + ".col";
		try
		{
			if(colData==null) packFile.addFile(name +".col", Files.readAllBytes(Paths.get(colPath)));
			else packFile.addFile(name +".col", colData);
		}
		catch(IOException e) 
		{
			bFM.Utils.DebugPrint("Warning Could not find .col file at \"" + colPath + "\" or in memory. Program will continue without one.");
		}
		//Adding .fp File
		String fpPath = importPath + name + ".fp";
		try
		{
			packFile.addFile(name +".fp", Files.readAllBytes(Paths.get(fpPath)));
		}
		catch(IOException e) 
		{
			bFM.Utils.DebugPrint("Warning Could not find .fp file at \"" + fpPath + "\". Program will continue without one.");
		}
		//Adding .vfp File
		String vfpPath = importPath + name + ".vfp";
		try
		{
			packFile.addFile(name +".vfp", Files.readAllBytes(Paths.get(vfpPath)));
		}
		catch(IOException e) 
		{
			bFM.Utils.DebugPrint("Warning Could not find .vfp file at \"" + vfpPath + "\". Program will continue without one.");
		}
		//Adding .sfp File
		String sfpPath = importPath + name + ".sfp";
		try
		{
			packFile.addFile(name +".sfp", Files.readAllBytes(Paths.get(sfpPath)));
		}
		catch(IOException e) 
		{
			bFM.Utils.DebugPrint("Warning Could not find .sfp file at \"" + sfpPath + "\". Program will continue without one.");
		}
		//Adding .lfp File
		String lfpPath = importPath + name + ".lfp";
		try
		{
			packFile.addFile(name +".lfp", Files.readAllBytes(Paths.get(lfpPath)));
		}
		catch(IOException e) 
		{
			bFM.Utils.DebugPrint("Warning Could not find .lfp file at \"" + lfpPath + "\". Program will continue without one.");
		}
		//Repacking File
		try
		{
			Files.write(Paths.get(PCKGPath) , packFile.getFile());
		}
		catch(IOException e) 
		{
			bFM.Utils.DebugPrint("Failed to create .pac file at \"" + PCKGPath + "\".");
		}
	}
	private static void packBuilding(String name) 
	{
		String fileName = "bl" + name + ".pac";
		String modelPath = Settings.importPath + "bl" + name + ".brres";
		String colPath = Settings.importPath + name + ".col";
		String fpPath = Settings.importPath + name + ".fp";
		String importPath = Settings.importPath;
		String outputPath = Settings.outputPath + "Map\\" + fileName;
		importPath += "Buildings\\";
		outputPath += "Building\\";
		//Create .pac File
		PCKGManager packFile = new PCKGManager(fileName);
		try 
		{
			bFM.Utils.DebugPrintF(bFM.DebugStrings.ReadFileAttempt, "Package", outputPath);
			packFile = new PCKGManager(Files.readAllBytes(Paths.get(outputPath)));
			bFM.Utils.DebugPrintF(bFM.DebugStrings.ReadFileSuccess, "Package");
		} catch (IOException e) 
		{
			bFM.Utils.DebugPrintF(bFM.DebugStrings.ReadFileFailureCreateNew, "Package", outputPath, "Package");
		}
		//Adding Model File
		try
		{
			bFM.Utils.DebugPrintF(bFM.DebugStrings.ReadFileAttempt, "Brres Model", modelPath);
			packFile = new PCKGManager(Files.readAllBytes(Paths.get(modelPath)));
			bFM.Utils.DebugPrintF(bFM.DebugStrings.ReadFileSuccess, "Brres Model");
		}
		catch(IOException e) 
		{
			bFM.Utils.DebugPrintF(bFM.DebugStrings.ReadFileFailureEnd, "Brres Model");
			if(packFile.indexOf("bl" + name + ".brres")==-1)
			{
				bFM.Utils.DebugPrintF(bFM.DebugStrings.RequiredFileNotInPackageEnd, "Brres Model");
				return;
			}
		}
		//Adding .col File
		try
		{
			if(colData==null) packFile.addFile(name +".col", Files.readAllBytes(Paths.get(colPath)));
			else packFile.addFile(name +".col", colData);
		}
		catch(IOException e) 
		{
			bFM.Utils.DebugPrint("Warning Could not find .col file at \"" + colPath + "\" or in memory. Program will continue without one.");
		}
		//Adding .fp File
		
		try
		{
			packFile.addFile(name +".fp", Files.readAllBytes(Paths.get(fpPath)));
		}
		catch(IOException e) 
		{
			bFM.Utils.DebugPrint("Warning Could not find .fp file at \"" + fpPath + "\". Program will continue without one.");
		}
		//Adding .vfp File
		String vfpPath = importPath + name + ".vfp";
		try
		{
			packFile.addFile(name +".vfp", Files.readAllBytes(Paths.get(vfpPath)));
		}
		catch(IOException e) 
		{
			bFM.Utils.DebugPrint("Warning Could not find .vfp file at \"" + vfpPath + "\". Program will continue without one.");
		}
		//Adding .sfp File
		String sfpPath = importPath + name + ".sfp";
		try
		{
			packFile.addFile(name +".sfp", Files.readAllBytes(Paths.get(sfpPath)));
		}
		catch(IOException e) 
		{
			bFM.Utils.DebugPrint("Warning Could not find .sfp file at \"" + sfpPath + "\". Program will continue without one.");
		}
		//Adding .lfp File
		String lfpPath = importPath + name + ".lfp";
		try
		{
			packFile.addFile(name +".lfp", Files.readAllBytes(Paths.get(lfpPath)));
		}
		catch(IOException e) 
		{
			bFM.Utils.DebugPrint("Warning Could not find .lfp file at \"" + lfpPath + "\". Program will continue without one.");
		}
		//Repacking File
		//try
		{
			//Files.write(Paths.get(PCKGPath) , packFile.getFile());
		}
		//catch(IOException e) 
		{
			//bFM.Utils.DebugPrint("Failed to create .pac file at \"" + PCKGPath + "\".");
		}
	}
	private static void message()
	{
		PCKGManager message = new PCKGManager("mes0.pac");
		try 
		{
			bFM.Utils.DebugPrint("Attempting to read system text file at: "+Settings.importPath+"sys.txt");
			message.addFile("sys.txt", Files.readAllBytes(Paths.get(Settings.importPath+"sys.txt")));
		} catch (IOException e) 
		{
			bFM.Utils.DebugPrint("Failed to read system text file");
			return;
		}
		bFM.Utils.DebugPrint("Success");
		try 
		{
			bFM.Utils.DebugPrint("Attempting to write system text pack at: "+Settings.outputPath + "Message\\mes0.pac");
			Files.write(Paths.get(Settings.outputPath + "Message\\mes0.pac") , message.getFile());
		} catch (IOException e) 
		{
			bFM.Utils.DebugPrint("Failed to write system text pac");
			return;
		}
		bFM.Utils.DebugPrint("Success");
	}
	private static void mapDataBase()
	{
		String mapDBPath = Settings.outputPath + "Resources\\mapDB0.pac";
		String inputPath = Settings.importPath + "Resources\\Map Database\\";
		mapDataBaseManager mapData = new mapDataBaseManager(mapDBPath);
		mapData.addFiles(inputPath);
		mapData.writeFile(mapDBPath);
	}
	private static void decodeFixedPoints()
	{
		fpInterpreter fixedPoints = null;
		
		try 
		{
			bFM.Utils.DebugPrint("Attempting to read Fixed Points file pack at: " + Settings.importPath+name+'\\'+name+fpType);
			fixedPoints = new fpInterpreter(Files.readAllBytes(Paths.get(Settings.importPath+name+'\\'+name+fpType)));
		} catch (IOException e) 
		{
			bFM.Utils.DebugPrint("Failed to read Fixed Point Pack.");
			return;
		}
		try 
		{
			bFM.Utils.DebugPrint("Attempting to write Fixed Point file at: " + Settings.importPath+name+'\\'+name+".bfp");
			Files.write(Paths.get(Settings.importPath+name+'\\'+name+".bfp"), Utils.encodeStringToBytes(fixedPoints.toBFP()));
		} catch (IOException e) 
		{
			bFM.Utils.DebugPrint("Failed to write Fixed Point File.");
		}
		
	}
	private static void encodeFixedPoints()
	{
		fpInterpreter fixedPoints = null;
		try 
		{
			bFM.Utils.DebugPrint("Attempting to read Fixed Points File at: " + Settings.importPath+name+'\\'+name+".bfp");
			fixedPoints = new fpInterpreter(Files.readAllLines(Paths.get(Settings.importPath+name+'\\'+name+".bfp")),fpType.toUpperCase().substring(1));
		} catch (IOException e) 
		{
			bFM.Utils.DebugPrint("Failed to read Fixed Point File. Will attempt to decode from a pack.");
			decodeFixedPoints();
			return;
		}
		 
		fpData = fixedPoints.toBytes();

		try 
		{
			bFM.Utils.DebugPrint("Attempting to write Fixed Points file at: " + Settings.importPath+name+'\\'+name+fpType);
			Files.write(Paths.get(Settings.importPath+name+'\\'+name+fpType), fpData);
		} catch (IOException e) 
		{
			bFM.Utils.DebugPrint("Failed to write Fixed Point File");
			return;
		}
	}
	private static void encodeVMC(String VMC_Code)
	{
		String outputPath = Settings.outputPath + "Events\\"+VMC_Code+".vmc0";
		String importPath = Settings.importPath + "Events\\"+VMC_Code+".txt";
		//This came to me in a series of visions.
		VMCConverter eventData = null;
		byte[] vmcData = null;
		try 
		{
			bFM.Utils.DebugPrintF(bFM.DebugStrings.ReadFileAttempt, "Event Code", importPath);
			eventData = new VMCConverter(Files.readAllLines(Paths.get(importPath)));
			bFM.Utils.DebugPrintF(bFM.DebugStrings.ReadFileSuccess, "Event Code");
		} catch (IOException e) 
		{
			bFM.Utils.DebugPrintF(bFM.DebugStrings.ReadFileFailureNewMode, "Event Code", "decode", "VMC", outputPath);
			decodeVMC(VMC_Code);
			return;
		}
		 
		vmcData = eventData.toBytes();
		
		try 
		{
			bFM.Utils.DebugPrintF(bFM.DebugStrings.WriteFileAttempt, "VMC", outputPath);
			Files.write(Paths.get(outputPath), vmcData);
			bFM.Utils.DebugPrintF(bFM.DebugStrings.WriteFileSuccess, "VMC");
		} catch (IOException e) 
		{
			bFM.Utils.DebugPrintF(bFM.DebugStrings.WriteFileFailureEnd, "VMC");
			return;
		}
	}
	private static void decodeVMC(String VMC_Code)
	{
		VMCConverter eventData = null;
		String outputPath = Settings.outputPath + "Events\\"+VMC_Code+".vmc0";
		String importPath = Settings.importPath + "Events\\"+VMC_Code+".txt";
		try 
		{
			bFM.Utils.DebugPrintF(bFM.DebugStrings.ReadFileAttempt, "VMC", outputPath);
			eventData = new VMCConverter(Files.readAllBytes(Paths.get(outputPath)));
			bFM.Utils.DebugPrintF(bFM.DebugStrings.ReadFileSuccess, "VMC");
		} catch (IOException e) 
		{
			bFM.Utils.DebugPrintF(bFM.DebugStrings.ReadFileFailureEnd, "VMC");
			return;
		}
		try 
		{
			bFM.Utils.DebugPrintF(bFM.DebugStrings.WriteFileAttempt, "Event Code", importPath);
			Files.write(Paths.get(importPath), Utils.encodeStringToBytes(eventData.toString()));
			bFM.Utils.DebugPrintF(bFM.DebugStrings.WriteFileSuccess, "Event Code");
		} catch (IOException e) 
		{
			bFM.Utils.DebugPrintF(bFM.DebugStrings.WriteFileFailureEnd, "Event Code");
		}
		
	}
	private static void encodeDebugEventViewer()
	{
		EventViewer eventData = null;
		final String outputPath = Settings.outputPath + "Debug\\EventViewer\\";
		final String importPath = Settings.importPath + "Debug\\EventViewer\\";
		final String NormalEvents = "evlist.bin";
		final String SubEvents = "evslist.bin";
		final String QuestEvents = "evqlist.bin";
		final String NormalEventCSV = "Event List.csv";
		final String SubEventCSV = "Sub Event List.csv";
		final String QuestEventCSV = "Quest Event List.csv";
		try 
		{
			bFM.Utils.DebugPrintF(bFM.DebugStrings.ReadFileAttempt, "Event List", importPath + NormalEventCSV);
			eventData = new EventViewer(Files.readAllLines(Paths.get(importPath + NormalEventCSV)));
			bFM.Utils.DebugPrintF(bFM.DebugStrings.ReadFileSuccess, "Event List");
		} catch (IOException e) 
		{
			bFM.Utils.DebugPrintF(bFM.DebugStrings.ReadFileFailureNewMode, "Event List", "decode", "Debug Event List", outputPath + NormalEvents);
			decodeDebugEventViewer();
			return;
		}
		try 
		{
			bFM.Utils.DebugPrintF(bFM.DebugStrings.WriteFileAttempt, "Debug Event List", outputPath + NormalEvents);
			Files.write(Paths.get(outputPath + NormalEvents), eventData.toBytes());
			bFM.Utils.DebugPrintF(bFM.DebugStrings.WriteFileSuccess, "Debug Event List");
		} catch (IOException e) 
		{
			bFM.Utils.DebugPrintF(bFM.DebugStrings.WriteFileFailureEnd, "Debug Event List");
			return;
		}
		try 
		{
			bFM.Utils.DebugPrintF(bFM.DebugStrings.ReadFileAttempt, "Event List", importPath + SubEventCSV);
			eventData = new EventViewer(Files.readAllLines(Paths.get(importPath + SubEventCSV)));
			bFM.Utils.DebugPrintF(bFM.DebugStrings.ReadFileSuccess, "Event List");
		} catch (IOException e) 
		{
			bFM.Utils.DebugPrintF(bFM.DebugStrings.ReadFileFailureNewMode, "Event List", "decode", "Debug Event List", outputPath + SubEvents);
			decodeDebugEventViewer();
			return;
		}
		try 
		{
			bFM.Utils.DebugPrintF(bFM.DebugStrings.WriteFileAttempt, "Debug Event List", outputPath + SubEvents);
			Files.write(Paths.get(outputPath + SubEvents), eventData.toBytes());
			bFM.Utils.DebugPrintF(bFM.DebugStrings.WriteFileSuccess, "Debug Event List");
		} catch (IOException e) 
		{
			bFM.Utils.DebugPrintF(bFM.DebugStrings.WriteFileFailureEnd, "Debug Event List");
			return;
		}
		try 
		{
			bFM.Utils.DebugPrintF(bFM.DebugStrings.ReadFileAttempt, "Event List", importPath + QuestEventCSV);
			eventData = new EventViewer(Files.readAllLines(Paths.get(importPath + QuestEventCSV)));
			bFM.Utils.DebugPrintF(bFM.DebugStrings.ReadFileSuccess, "Event List");
		} catch (IOException e) 
		{
			bFM.Utils.DebugPrintF(bFM.DebugStrings.ReadFileFailureNewMode, "Event List", "decode", "Debug Event List", outputPath + QuestEvents);
			decodeDebugEventViewer();
			return;
		}
		try 
		{
			bFM.Utils.DebugPrintF(bFM.DebugStrings.WriteFileAttempt, "Debug Event List", outputPath + QuestEvents);
			Files.write(Paths.get(outputPath + QuestEvents), eventData.toBytes());
			bFM.Utils.DebugPrintF(bFM.DebugStrings.WriteFileSuccess, "Debug Event List");
		} catch (IOException e) 
		{
			bFM.Utils.DebugPrintF(bFM.DebugStrings.WriteFileFailureEnd, "Debug Event List");
			return;
		}
	}
	private static void decodeDebugEventViewer()
	{
		EventViewer eventData = null;
		final String outputPath = Settings.outputPath + "Debug\\EventViewer\\";
		final String importPath = Settings.importPath + "Debug\\EventViewer\\";
		final String NormalEvents = "evlist.bin";
		final String SubEvents = "evslist.bin";
		final String QuestEvents = "evqlist.bin";
		final String NormalEventCSV = "Event List.csv";
		final String SubEventCSV = "Sub Event List.csv";
		final String QuestEventCSV = "Quest Event List.csv";
		try 
		{
			bFM.Utils.DebugPrintF(bFM.DebugStrings.ReadFileAttempt, "Debug Event List", outputPath + NormalEvents);
			eventData = new EventViewer(Files.readAllBytes(Paths.get(outputPath + NormalEvents)));
			bFM.Utils.DebugPrintF(bFM.DebugStrings.ReadFileSuccess, "Debug Event List");
		} catch (IOException e) 
		{
			bFM.Utils.DebugPrintF(bFM.DebugStrings.ReadFileFailureEnd, "Debug Event List");
			return;
		}
		try 
		{
			bFM.Utils.DebugPrintF(bFM.DebugStrings.WriteFileAttempt, "Event List", importPath + NormalEventCSV);
			Files.write(Paths.get(importPath + NormalEventCSV), eventData.toString().getBytes(Charset.forName("Shift-JIS")));
			bFM.Utils.DebugPrintF(bFM.DebugStrings.WriteFileSuccess, "Event List");
		} catch (IOException e) 
		{
			bFM.Utils.DebugPrintF(bFM.DebugStrings.WriteFileFailureEnd, "Event List");
		}
		try 
		{
			bFM.Utils.DebugPrintF(bFM.DebugStrings.ReadFileAttempt, "Debug Event List", outputPath + SubEvents);
			eventData = new EventViewer(Files.readAllBytes(Paths.get(outputPath + SubEvents)));
			bFM.Utils.DebugPrintF(bFM.DebugStrings.ReadFileSuccess, "Debug Event List");
		} catch (IOException e) 
		{
			bFM.Utils.DebugPrintF(bFM.DebugStrings.ReadFileFailureEnd, "Debug Event List");
			return;
		}
		try 
		{
			bFM.Utils.DebugPrintF(bFM.DebugStrings.WriteFileAttempt, "Event List", importPath + SubEventCSV);
			Files.write(Paths.get(importPath + SubEventCSV), eventData.toString().getBytes(Charset.forName("Shift-JIS")));
			bFM.Utils.DebugPrintF(bFM.DebugStrings.WriteFileSuccess, "Event List");
		} catch (IOException e) 
		{
			bFM.Utils.DebugPrintF(bFM.DebugStrings.WriteFileFailureEnd, "Event List");
		}
		try 
		{
			bFM.Utils.DebugPrintF(bFM.DebugStrings.ReadFileAttempt, "Debug Event List", outputPath + QuestEvents);
			eventData = new EventViewer(Files.readAllBytes(Paths.get(outputPath + QuestEvents)));
			bFM.Utils.DebugPrintF(bFM.DebugStrings.ReadFileSuccess, "Debug Event List");
		} catch (IOException e) 
		{
			bFM.Utils.DebugPrintF(bFM.DebugStrings.ReadFileFailureEnd, "Debug Event List");
			return;
		}
		try 
		{
			bFM.Utils.DebugPrintF(bFM.DebugStrings.WriteFileAttempt, "Event List", importPath + QuestEventCSV);
			Files.write(Paths.get(importPath + QuestEventCSV), eventData.toString().getBytes(Charset.forName("Shift-JIS")));
			bFM.Utils.DebugPrintF(bFM.DebugStrings.WriteFileSuccess, "Event List");
		} catch (IOException e) 
		{
			bFM.Utils.DebugPrintF(bFM.DebugStrings.WriteFileFailureEnd, "Event List");
		}
	}
	public static void createModdingDirectory(String modPath)
	{
		Settings.importPath = modPath;
		Path directory = Paths.get(modPath);
		directory.toFile().mkdirs();
		directory.resolve("Buildings").toFile().mkdirs();
		directory.resolve("Debug/EventViewer").toFile().mkdirs();
		directory.resolve("Events/Text").toFile().mkdirs();
		directory.resolve("Resource/Character Database").toFile().mkdirs();
		directory.resolve("Resource/Map Database").toFile().mkdirs();
		directory.resolve("Resource/Monster Database").toFile().mkdirs();
	}
	public static void createRiivolutionDirectory(String modPath, String modName)
	{
		Settings.outputPath = modPath;
		Path directory = Paths.get(modPath);
		directory.toFile().mkdirs();
		directory.resolve(modName + "/Characters").toFile().mkdirs();
		directory.resolve(modName + "/Debug/EventViewer").toFile().mkdirs();
		directory.resolve(modName + "/Events").toFile().mkdirs();
		directory.resolve(modName + "/Map/Building").toFile().mkdirs();
		directory.resolve(modName + "/Map/World").toFile().mkdirs();
		directory.resolve(modName + "/Message/English").toFile().mkdirs();
		directory.resolve(modName + "/Message/French").toFile().mkdirs();
		directory.resolve(modName + "/Message/German").toFile().mkdirs();
		directory.resolve(modName + "/Message/Italian").toFile().mkdirs();
		directory.resolve(modName + "/Message/Japanese").toFile().mkdirs();
		directory.resolve(modName + "/Message/Spanish").toFile().mkdirs();
		directory.resolve(modName + "/Movie").toFile().mkdirs();
		directory.resolve(modName + "/Resources").toFile().mkdirs();
		directory.resolve(modName + "/System Data/Menu Data").toFile().mkdirs();
		directory.resolve(modName + "/System Data/Menu Layout/Kingdom Plan").toFile().mkdirs();
		directory.resolve("riivolution").toFile().mkdirs();
		Path xmlPath = directory.resolve("riivolution/" + modName + ".xml");
		String xml = "<wiidisc version=\"1\">\r\n";
		xml += "\t<id game=\"RO3\" />\r\n";
		xml += "<options>\r\n"
				+ "\t\t<section name=\"LKS3\">\r\n"
				+ "\t\t\t<option name=\"/LKS3\">\r\n"
				+ "\t\t\t<choice name=\"Enabled\">\r\n"
				+ "\t\t\t\t\t<patch id=\"LKS Mods\" />\r\n"
				+ "\t\t\t\t</choice>\r\n"
				+ "\t\t\t</option>\r\n"
				+ "\t\t</section>\r\n"
				+ "\t</options>\r\n";
		xml += "\t<patch id=\"LKS Mods\">\r\n"
				+ "\t\t<savegame external=\"/riivolution/" + modName + "\" clone=\"true\" />\r\n"
				+ "\t\t<folder external=\"/" + modName + "/Map/World\" disc=\"/map/wg\" />\r\n"
				+ "\t\t<folder external=\"/" + modName + "/Movie\" disc=\"/0_movie\" create=\"true\"/>\r\n"
				+ "\t\t<file external=\"/" + modName + "/Characters/cbData1.pac\" disc=\"/chr/cbData1.pac\" />\r\n"
				+ "\t\t<folder external=\"/" + modName + "/Map/Building\" disc=\"/map/wb/\" create=\"true\"/>\r\n"
				+ "\t\t<file external=\"/" + modName + "/Message/English/mes_LZ.bin\" disc=\"/event/message/1/mes_LZ.bin\" />\r\n"
				+ "\t\t<folder external=\"/" + modName + "/Message/English\" disc=\"/event/message/1/\" create=\"true\"/>\r\n"
				+ "\t\t<folder external=\"/" + modName + "/Map/Building\" disc=\"/map/wb\" create=\"true\"/>\r\n"
				+ "\t\t<folder external=\"/" + modName + "/Resources\" disc=\"/res\" />\r\n"
				+ "\t\t<folder external=\"/" + modName + "/Events\" disc=\"/event/script/sub\" create=\"true\"/>/>\r\n"
				+ "\t\t<folder external=\"/" + modName + "/Events\" disc=\"/event/script/event\" create=\"true\"/>/>\r\n"
				+ "\t\t<folder external=\"/" + modName + "\" disc=\"/event/funcpoint\" />\r\n"
				+ "\t\t<folder external=\"/" + modName + "\" disc=\"/zpack\" />\r\n"
				+ "\t\t<folder external=\"/" + modName + "/Message\" disc=\"/mes/1\" />\r\n"
				+ "\t\t<folder external=\"/" + modName + "/System Data/Menu Data\" disc=\"/sys/menuDB\" />\r\n"
				+ "\t\t<folder external=\"/" + modName + "/Debug/EventViewer\" disc=\"/Debug/EventViewer\" />\r\n"
				+ "\t\t<folder external=\"/" + modName + "/System Data/Menu Layout/Kingdom Plan\" disc=\"/sys/Layout/KingdomPlanbrres\" create=\"true\"/>\r\n"
				+ "\t</patch>\r\n";
		xml += "</wiidisc>\r\n";
		try 
		{
			bFM.Utils.DebugPrintF(bFM.DebugStrings.WriteFileAttempt, "Riivolution XML File", xmlPath.toString());
			Files.write(xmlPath, Utils.encodeStringToBytes(xml));
			bFM.Utils.DebugPrintF(bFM.DebugStrings.WriteFileSuccess, "Riivolution XML File");
		} catch (IOException e) 
		{
			bFM.Utils.DebugPrintF(bFM.DebugStrings.WriteFileFailureEnd, "Riivolution XML File");
		}
	}
}
