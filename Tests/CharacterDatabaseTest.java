package Tests;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import PCKGManager.PCKGManager;
import ResourceManagers.CharacterDatabaseManager.CharacterDataBaseManager;
import ResourceManagers.CharacterDatabaseManager.CharacterResources.CharacterResourceList;
import ResourceManagers.CharacterDatabaseManager.Index.indBinList;
import ResourceManagers.CharacterDatabaseManager.JobPrice.JobChangePriceList;
import ResourceManagers.CharacterDatabaseManager.Join.JoinBinList;
import ResourceManagers.CharacterDatabaseManager.SoundEffectCoordinate.SoundEffectCoordinateList;
import ResourceManagers.CharacterDatabaseManager.TextureAnimation.TextureAnimationList;
import bFM.Testing;

public class CharacterDatabaseTest
{
	public static void main(String[] args)
	{
		String path = "D:\\Dolphin_Emulator\\Load\\Riivolution\\LKSMapTesting\\Resources";
		try {
			byte[] file = Files.readAllBytes(Paths.get(path).resolve("chrDB0.pac"));
			PCKGManager chrDB = new PCKGManager(file);
			TestCharacterResourceList(chrDB.getFile("chrDB2.lst"));
			TestIndex(chrDB.getFile("ind3.bin"));
			TestJoin(chrDB.getFile("join.bin"));
			TestSoundCoordinates(chrDB.getFile("seCrd.bin"));
			TestTextureAnimations(chrDB.getFile("texanim.bin"));
			TestJobChangePrice(chrDB.getFile("JobChangePrice.cfg"));
			TestManager(file);
		} catch (IOException e)
		{
			Testing.Assert("Could Not Read File at: " + path);
			e.printStackTrace();
		}
	}
	private static void TestCharacterResourceList(byte[] src)
	{
		CharacterResourceList characterList = new CharacterResourceList(src);
		if(Testing.TestPercentDifference(src, characterList.toBytes()) == false)
		{
			Testing.saveFiles(src, characterList.toBytes(), "chrDB2.lst");
			Testing.Assert("Character Resource List is Not the same!\n");
		}
	}
	private static void TestIndex(byte[] src)
	{
		indBinList ind = new indBinList(src);
		if(Testing.TestPercentDifference(src, ind.toBytes()) == false)
		{
			Testing.saveFiles(src, ind.toBytes(), "ind3.bin");
			Testing.Assert("Index List is Not the same!\n");
		}
	}
	private static void TestJobChangePrice(byte[] src)
	{
		JobChangePriceList jobs = new JobChangePriceList(src);
		if(Testing.TestPercentDifference(src, jobs.toBytes()) == false)
		{
			Testing.saveFiles(src, jobs.toBytes(), "JobChangePrice.cfg");
			Testing.Assert("Job Change Price List is Not the same!\n");
		}
	}
	private static void TestJoin(byte[] src)
	{
		JoinBinList join = new JoinBinList(src);
		if(Testing.TestPercentDifference(src, join.toBytes()) == false)
		{
			Testing.saveFiles(src, join.toBytes(), "join.bin");
			Testing.Assert("Join List is Not the same!\n");
		}
	}
	private static void TestSoundCoordinates(byte[] src)
	{
		SoundEffectCoordinateList seCrd = new SoundEffectCoordinateList(src);
		if(Testing.TestPercentDifference(src, seCrd.toBytes()) == false)
		{
			Testing.saveFiles(src, seCrd.toBytes(), "seCrd.bin");
			Testing.Assert("Sound Effect List is Not the same!\n");
		}
	}
	private static void TestTextureAnimations(byte[] src)
	{
		TextureAnimationList texanim = new TextureAnimationList(src);
		if(Testing.TestPercentDifference(src, texanim.toBytes()) == false)
		{
			Testing.saveFiles(src, texanim.toBytes(), "texanim.bin");
			Testing.Assert("Texture Animation List is Not the same!\n");
		}
	}
	private static void TestManager(byte[] src)
	{
		CharacterDataBaseManager chrDB = new CharacterDataBaseManager(src);
		if(Testing.TestPercentDifference(src, chrDB.toBytes()) == false)
		{
			Testing.saveFiles(src, chrDB.toBytes(), "chrDB0.pac");
			Testing.Assert("Character Database Manager is Not the same!\n");
		}
	}
}
