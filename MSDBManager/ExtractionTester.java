/**
 * 
 */
package MSDBManager;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import PCKGManager.PCKGManager;

/**
 * 
 */
public class ExtractionTester 
{
	static String outputPath = "D:\\ws - Copy\\pack\\mount\\msbdnlks\\Output\\";
	static String inputPath = "D:\\ws - Copy\\pack\\mount\\msbdnlks\\msDB27.pac"; 
	//static String inputPath = "D:\\LKS Debug!!!!1\\ROMs\\onetri1\\DATA\\files\\res\\test\\";
	public static void main(String[] args) 
	{
		
		try {
			//testAll();
			RandomMonster();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static void RandomMonster() throws IOException
	{
		//PCKGManager test = new PCKGManager(Files.readAllBytes(Paths.get("D:\\Dolphin_Emulator\\Load\\Riivolution\\LKSMapTesting\\msDB27.pac")));
		PCKGManager test = new PCKGManager(Files.readAllBytes(Paths.get(inputPath)),true);
		
		//test.setLKSMode(true);
		
		
		ConstantEnemyManager BRM = new ConstantEnemyManager(test.getFile("MOP_14_CONST_PLACE.lst"), test.getFile("MOP_14_GROUP.lst"), test.getFile("MOP_14_OBJECT.lst"), test.getFile("MOP_14_RANDOM_AREA.lst"), test.getFile("MOP_14_RANDOM_POINT.lst"), test.getFile("MOP_14_AREA_DATA.lst"));
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
		testDifferences(test.getFile("MOP_14_AREA_DATA.lst"),BRM.getAreaDatas());
		System.out.println("Testing Random Area");
		testDifferences(test.getFile("MOP_14_RANDOM_AREA.lst"),BRM.getAreas());
		System.out.println("Testing Random Points");
		testDifferences(test.getFile("MOP_14_RANDOM_POINT.lst"),BRM.getPoints());
		
		
		
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
	private static void testDifferences(byte[] file1, byte[] file2) 
	{
		System.out.println("File 1 size: " + file1.length);
		System.out.println("File 2 size: " + file2.length);
		for(int i = 0; i<Math.min(file1.length, file2.length); i++)
		{
			if(file1[i]!=file2[i])
			{
				System.out.println("Difference at: " + i + ". File 1 is: " + file1[i] + ". File 2 is: " + file2[i] + ".");
			}
		}
	}
	public static void testAll() throws IOException
	{
			MobAiList ai = new MobAiList(Files.readAllBytes(Paths.get(inputPath+"MOB24AI.lst")));
			Files.write(Paths.get(outputPath+"MobAiList.lst"), ai.toString().getBytes("Shift-JIS"));
			MobResAsn res = new MobResAsn(Files.readAllBytes(Paths.get(inputPath+"MOB24RESASN.lst")));
			Files.write(Paths.get(outputPath+"MobResList.lst"), res.toString().getBytes("Shift-JIS"));
			MobModList mod = new MobModList(Files.readAllBytes(Paths.get(inputPath+"MOB24MOD.lst")));
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
			MobAttackColList AttackCol = new MobAttackColList(Files.readAllBytes(Paths.get(inputPath+"MOB24ATKCOL.lst")));
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
			MobAiList ai = new MobAiList(src.getFile("MOB_24_AI.lst"));
			Files.write(Paths.get(outputPath+"MobAiList.lst"), ai.toString().getBytes("Shift-JIS"));
			MobResAsn res = new MobResAsn(src.getFile("MOB_24_RES_ASN.lst"));
			Files.write(Paths.get(outputPath+"MobResList.lst"), res.toString().getBytes("Shift-JIS"));
			MobModList mod = new MobModList(src.getFile("MOB_24_MOD.lst"));
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
			MobAttackColList AttackCol = new MobAttackColList(src.getFile("MOB24ATKCOL.lst"));
			Files.write(Paths.get(outputPath+"MobAttackCollisionList.lst"), AttackCol.toString().getBytes("Shift-JIS"));
			
			Files.write(Paths.get(outputPath+"MonsterHP.lst"), mod.toHP().getBytes("Shift-JIS"));
	}
}

