 package colReader;


import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

import CameraData.CameraZoneList;
import LZ10Convertor.LZ10Manager;
import MSDBManager.ConstantEnemyManager;
import MSDBManager.MobModList;
import PCKGManager.PCKGManager;
import ResourceManagers.CharacterDatabaseManager.CharacterDataBaseManager;
import ResourceManagers.ItemDatabaseManager.itemDatabaseManager;
import ResourceManagers.ItemDatabaseManager.itemPlaceManager;
import ResourceManagers.MapDatabaseManager.BuildingResourceList;
import ResourceManagers.MapDatabaseManager.exteriorPlaceList;
import ResourceManagers.MapDatabaseManager.mapDataBaseManager;
import WorldFileManager.fpInterpreter;
import SystemDataManagers.CockpitLogManager;
import SystemDataManagers.MailManager;
import SystemDataManagers.KingdomPlanManager.kingdomPlanManager;
import VMC.VMCConverter;
@SuppressWarnings("unused")
public class Main 
{
	public static boolean grid = true;
	final public static String importPath = "D:\\LKS Mod\\";
	final public static String outputPath = "D:\\Dolphin_Emulator\\Load\\Riivolution\\LKSMapTesting\\";
	static String name = "0202";//0510 soba
	static byte [] colData;
	static byte [] fpData;
	static String fpType = ".vfp";	
	public static void main(String[] args) 
	{
		bFM.Utils.setDebugOutput(true);
		bFM.Utils.DebugPrint("Debug Data Enabled");
		//{tester();} catch (IOException e) {e.printStackTrace();}
		//decodeCollision();
		//encodeCollision();
		//encodeFixedPoints();
		//decodeFixedPoints();
		//menuDBManager();
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
		encodeVMC("CH03_EVT_001");
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
			MonsterDataPack = new PCKGManager(Files.readAllBytes(Paths.get(outputPath+"msDB26.pac")));
		}
		catch (IOException e)
		{
			bFM.Utils.DebugPrint("Failed to Locate Monster Database Pack at: " + outputPath+"msDB27.pac");
			bFM.Utils.DebugPrint("Program will return as it cannot continue.");
			return;
		}
		ConstantEnemyManager bMos = new ConstantEnemyManager(MonsterDataPack.getFile("MOP_14_CONST_PLACE.lst"), 
				MonsterDataPack.getFile("MOP_14_GROUP.lst"), MonsterDataPack.getFile("MOP_14_OBJECT.lst"), 
				MonsterDataPack.getFile("MOP_14_RANDOM_AREA.lst"), MonsterDataPack.getFile("MOP_14_RANDOM_POINT.lst"), 
				MonsterDataPack.getFile("MOP_14_AREA_DATA.lst"));
		
		if(modCode!=-1)
		{
			bMos.setFilterCode(modCode);
		}

		try 
		{
			bFM.Utils.DebugPrint("Attempting to write raw text at: " + importPath+outputFileName);
			Files.write(Paths.get(importPath+outputFileName) , bMos.toString().getBytes());
		} catch (IOException e) 
		{
			bFM.Utils.DebugPrint("Failed to write bMos file at: " + importPath+outputFileName);
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
			mail = new MailManager(Files.readAllLines(Paths.get(importPath+"NormalMail.txt")), importPath);
		} catch (IOException e) 
		{
			System.out.println("Failed to locate the Normal Quest File in the directory: " + importPath);
			try 
			{
				Files.write(Paths.get(importPath+"NormalMail.txt"), mail.normalToString().getBytes());
			} catch (IOException q) 
			{
				System.out.println("Failed to write Normal Quest File at: " + importPath+"NormalMail.txt");
			}
			
			
			return;
		}
		mail.toPac(1);

		
	}
	private static void decodeLightZones() 
	{
		String mapBootPath = outputPath + "mapBoot2.pac";
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
			Files.write(Paths.get(importPath+"AllLightZones.blfp"), lightingFixedPoints.toBFP().getBytes());
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
		String outputPath = Main.outputPath + "System Data\\Menu Data\\";
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
		
		//menuDatabase.replaceFile("CockpitLog.bin",encodeCockpitLog(menuDatabase.getFile("CockpitLog.bin")));
		menuDatabase.replaceFile("KingdomPlan.bin",encodeKingdomPlan(menuDatabase.getFile("KingdomPlan.bin"))); // TODO
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
	private static byte[] encodeKingdomPlan(byte[] data) 
	{
		bFM.Utils.DebugPrint("Attempting to Encode Kingdom Plan Data");
		kingdomPlanManager kingdomPlanData;
		try {
			kingdomPlanData = new kingdomPlanManager(Files.readAllLines(Paths.get(importPath+"KingdomPlan.txt")));
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
			bFM.Utils.DebugPrint("Attempting to write Kingdom Plan file at: " + importPath+"KingdomPlan.txt");
			Files.write(Paths.get(importPath+"KingdomPlan.txt"), kingdomPlanData.toString().getBytes());
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
			CockpitLogData = new CockpitLogManager(Files.readAllLines(Paths.get(importPath+"CockpitLog.txt")));
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
			bFM.Utils.DebugPrint("Attempting to write Cockpit Log file at: " + importPath+"CockpitLog.txt");
			Files.write(Paths.get(importPath+"CockpitLog.txt"), CockpitLogData.toString().getBytes());
		} catch (IOException e) 
		{
			bFM.Utils.DebugPrint("Failed to write file");
		}
	}
	private static byte[] MovieManager(byte[] data)
	{
		PCKGManager MovieBookPack = new PCKGManager(data);
		File directory = new File(importPath+"Menu Database\\Movie Book");
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
			bFM.Utils.DebugPrint("Attempting to read Light Zone File at: " + importPath+"LightZones.blfp");
			fixedPoints = new fpInterpreter(Files.readAllLines(Paths.get(importPath+"LightZones.blfp")),"LFP");
		} catch (IOException e) 
		{
			bFM.Utils.DebugPrint("Failed to read Light Zone File. Will attempt to decode from a pack.");
			decodeLightZones();
			return;
		}
		byte[] lfpData = fixedPoints.getBytes();
		PCKGManager mapBootPack = new PCKGManager("mapBoot");
		try 
		{
			bFM.Utils.DebugPrint("Attempting to read Map Boot pack at: " + outputPath + "mapBoot2.pac");
			mapBootPack = new PCKGManager(Files.readAllBytes(Paths.get(outputPath + "mapBoot2.pac")));
		} catch (IOException e) 
		{
			bFM.Utils.DebugPrint("Failed to read Map Boot Pack");
			return;
		}
		mapBootPack.addFile("all_field.lfp", lfpData);
		try 
		{
			bFM.Utils.DebugPrint("Attempting to write Map Boot pack");
			Files.write(Paths.get(outputPath + "mapBoot2.pac"), mapBootPack.getFile());
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
			bFM.Utils.DebugPrint("Attempting to write Camera Zone file at: " + importPath+"CameraZones.bcz");
			Files.write(Paths.get(importPath+"CameraZones.bcz"), cameraZones.toString().getBytes());
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
			cameraZonesPack = new CameraZoneList(Files.readAllLines(Paths.get(importPath+"CameraZones.bcz")));
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
		String outputPath = Main.outputPath + "Resources\\";
		String importPath = Main.importPath + "Resources\\Item Database.txt";
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
		String outputPath = Main.outputPath + "Resources\\";
		String importPath = Main.importPath + "Resources\\";
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
			Files.write(Paths.get(importPath+"Item Database.txt") , items.toString().getBytes(Charset.forName("Shift_JIS")));
		} catch (IOException e) 
		{
			bFM.Utils.DebugPrint("Failed to write decoded file");
		}
	}
	private static void encodeEnemyData(String difficulty)	
	{
		String outputPath = Main.outputPath + "Resources\\";
		String importPath = Main.importPath + "Resources\\Monster Database\\";
		bFM.Utils.DebugPrint("Encoding Enemies "+ difficulty);
		ConstantEnemyManager bMos;
		MobModList enemyModifications = null;
		try
		{
			bMos = new ConstantEnemyManager(Files.readAllLines(Paths.get(importPath+"Enemies.bMos")));
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
		
		byte[] vmcData = Files.readAllBytes(Paths.get("D:\\Dolphin_Emulator\\Load\\Riivolution\\LKSMapTesting\\Events\\New folder\\CH01_EVT_001.vmc0"));
		//byte[] vmcData = Files.readAllBytes(Paths.get("D:\\LKS Debug!!!!1\\ROMs\\Extracted\\event\\script\\event\\CH02_EVT_101.vmc0"));
		VMCConverter vmcData1 = new VMCConverter(vmcData);
		bFM.Utils.testDifferences(vmcData, vmcData1.toBytes());
		Files.write(Paths.get("D:\\Dolphin_Emulator\\Load\\Riivolution\\LKSMapTesting\\eventTest.txt"), vmcData1.toString().getBytes(Charset.forName("Shift-JIS")));
		VMCConverter vmcData2 = new VMCConverter(Files.readAllLines(Paths.get("D:\\Dolphin_Emulator\\Load\\Riivolution\\LKSMapTesting\\eventTest.txt"), Charset.forName("Shift-JIS")));
		bFM.Utils.testDifferences(vmcData, vmcData2.toBytes());
		Files.write(Paths.get("D:\\Dolphin_Emulator\\Load\\Riivolution\\LKSMapTesting\\eventTest.vmc"), vmcData1.toBytes());
		Files.write(Paths.get("D:\\Dolphin_Emulator\\Load\\Riivolution\\LKSMapTesting\\eventTest2.vmc"), vmcData2.toBytes());
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
		//test.addFile("cb0010.pcha", tests.getFile());
		//Files.write(Paths.get("D:\\Dolphin_Emulator\\Load\\Riivolution\\LKSMapTesting\\Characters\\cbData1.pac"), test.getFile());
	}
	private static String getLanguage(int languageCode)
	{
		//Return the name of the anguage based off number. default to english as i speak that
		switch(languageCode)
		{
		case 0: return "Japanese\\";
		case 1: return "English\\";
		case 2: return "French\\";
		case 3: return "Italian\\";
		case 4: return "German\\";
		case 5: return "Spanish\\";
		}
		return "English\\";
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
		String importPath = Main.importPath + "Event\\Text\\" + getLanguage(languageCode);
		String outputPath = Main.outputPath + "Message\\" + getLanguage(languageCode);
		
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
			// TODO Auto-generated catch block
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
		
		String importPath = Main.importPath + "Events\\Text\\" + getLanguage(languageCode);
		String outputPath = Main.outputPath + "Message\\" + getLanguage(languageCode);
		
		
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
		String importDirectory = importPath + "Resources\\Character Database\\";
		CharacterDataBaseManager cdb = new CharacterDataBaseManager(outputPath + "Resources\\chrDB0.pac");
		cdb.importIndex(importDirectory);
		cdb.importJoin(importDirectory);
		cdb.importCharacters(importDirectory);
		cdb.importPrice(importDirectory);
		
		cdb.writeFile(outputPath + "Resources\\chrDB0.pac");
	}
	private static void decodeCollision()
	{
		String importPath = Main.importPath;
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
			Files.write(Paths.get((importPath+ name+".obj")), colFile.toString().getBytes());
		} catch (IOException e) 
		{
			bFM.Utils.DebugPrint("Failed to write extracted Collision file at: " + importPath+ name+".obj");
		}
		
		colData =colFile.getBytes();
		bFM.Utils.DebugPrint("Collision Finished Sucessfully");
	}
	private static void encodeCollision()
	{
		String importPath = Main.importPath;
		if(grid)
		{
			importPath += name + "\\";
		}
		else
		{
			importPath += "Building\\";
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
		String importPath = Main.importPath;
		String outputPath = Main.outputPath + "Map\\";
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
	private static void message()
	{
		PCKGManager message = new PCKGManager("mes0.pac");
		try 
		{
			bFM.Utils.DebugPrint("Attempting to read system text file at: "+importPath+"sys.txt");
			message.addFile("sys.txt", Files.readAllBytes(Paths.get(importPath+"sys.txt")));
		} catch (IOException e) 
		{
			bFM.Utils.DebugPrint("Failed to read system text file");
			return;
		}
		bFM.Utils.DebugPrint("Success");
		try 
		{
			bFM.Utils.DebugPrint("Attempting to write system text pack at: "+outputPath + "Message\\mes0.pac");
			Files.write(Paths.get(outputPath + "Message\\mes0.pac") , message.getFile());
		} catch (IOException e) 
		{
			bFM.Utils.DebugPrint("Failed to write system text pac");
			return;
		}
		bFM.Utils.DebugPrint("Success");
	}
	private static void mapDataBase()
	{
		String mapDBPath = outputPath + "Resources\\mapDB0.pac";
		String inputPath = importPath + "Resources\\Map Database\\";
		mapDataBaseManager mapData = new mapDataBaseManager(mapDBPath);
		mapData.addFiles(inputPath);
		mapData.writeFile(mapDBPath);
	}
	private static void decodeFixedPoints()
	{
		fpInterpreter fixedPoints = null;
		
		try 
		{
			bFM.Utils.DebugPrint("Attempting to read Fixed Points file pack at: " + importPath+name+'\\'+name+fpType);
			fixedPoints = new fpInterpreter(Files.readAllBytes(Paths.get(importPath+name+'\\'+name+fpType)));
		} catch (IOException e) 
		{
			bFM.Utils.DebugPrint("Failed to read Fixed Point Pack.");
			return;
		}
		try 
		{
			bFM.Utils.DebugPrint("Attempting to write Fixed Point file at: " + importPath+name+'\\'+name+".bfp");
			Files.write(Paths.get(importPath+name+'\\'+name+".bfp"), fixedPoints.toBFP().getBytes());
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
			bFM.Utils.DebugPrint("Attempting to read Fixed Points File at: " + importPath+name+'\\'+name+".bfp");
			fixedPoints = new fpInterpreter(Files.readAllLines(Paths.get(importPath+name+'\\'+name+".bfp")),fpType.toUpperCase().substring(1));
		} catch (IOException e) 
		{
			bFM.Utils.DebugPrint("Failed to read Fixed Point File. Will attempt to decode from a pack.");
			decodeFixedPoints();
			return;
		}
		 
		fpData = fixedPoints.getBytes();

		try 
		{
			bFM.Utils.DebugPrint("Attempting to write Fixed Points file at: " + importPath+name+'\\'+name+fpType);
			Files.write(Paths.get(importPath+name+'\\'+name+fpType), fpData);
		} catch (IOException e) 
		{
			bFM.Utils.DebugPrint("Failed to write Fixed Point File");
			return;
		}
	}
	private static void encodeVMC(String VMC_Code)
	{
		String outputPath = Main.outputPath + "Events\\"+VMC_Code+".vmc0";
		String importPath = Main.importPath + "Events\\"+VMC_Code+".txt";
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
			bFM.Utils.DebugPrintF(bFM.DebugStrings.ReadFileFailureContinue, "Event Code", "decode", "VMC", outputPath);
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
		String outputPath = Main.outputPath + "Events\\"+VMC_Code+".vmc0";
		String importPath = Main.importPath + "Events\\"+VMC_Code+".txt";
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
			Files.write(Paths.get(importPath), eventData.toString().getBytes());
			bFM.Utils.DebugPrintF(bFM.DebugStrings.WriteFileSuccess, "Event Code");
		} catch (IOException e) 
		{
			bFM.Utils.DebugPrintF(bFM.DebugStrings.WriteFileFailureEnd, "Event Code");
		}
		
	}
}
