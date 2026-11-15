package ResourceManagers.CharacterDatabaseManager;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.MalformedInputException;
import java.nio.file.Files;
import java.nio.file.Paths;

import PCKGManager.PCKGManager;
import ResourceManagers.CharacterDatabaseManager.CharacterResources.CharacterResourceList;
import ResourceManagers.CharacterDatabaseManager.Index.ind;
import ResourceManagers.CharacterDatabaseManager.Index.indBinList;
import ResourceManagers.CharacterDatabaseManager.JobPrice.JobChangePriceList;
import ResourceManagers.CharacterDatabaseManager.Join.JoinBinList;
import ResourceManagers.CharacterDatabaseManager.SoundEffectCoordinate.SoundEffectCoordinateList;
import ResourceManagers.CharacterDatabaseManager.TextureAnimation.TextureAnimationList;
import bFM.OpenedFile;

public class CharacterDataBaseManager implements OpenedFile
{
	public static final String FileName = "chrDB0.pac";
	CharacterResourceList chrDB2 = null;
	indBinList ind3 = null;
	JobChangePriceList JobChangePrice = null;
	JoinBinList join = null;
	SoundEffectCoordinateList seCrd = null;
	TextureAnimationList texanim = null;
	public CharacterDataBaseManager(String characterDBPath)
	{
		try 
		{
			bFM.Utils.DebugPrint("Attempting to read chrDB package at: " + characterDBPath);
			initializeFromBytes(Files.readAllBytes(Paths.get(characterDBPath)));
		} catch (IOException e) 
		{
			bFM.Utils.DebugPrint("Failed to read package. Program will skip this step for now");
		}
	}
	public CharacterDataBaseManager(byte[] data) 
	{
		initializeFromBytes(data);
	}
	public CharacterDataBaseManager() 
	{
		chrDB2 = new CharacterResourceList();
		ind3 = new indBinList();
		JobChangePrice = new JobChangePriceList(this);
		join = new JoinBinList();
		seCrd = new SoundEffectCoordinateList();
		texanim = new TextureAnimationList();
	}
	private void initializeFromBytes(byte[] data) 
	{
		PCKGManager CharacterDataBase = new PCKGManager(data);
		chrDB2 = new CharacterResourceList(CharacterDataBase.getFile("chrDB2.lst"));
		ind3 = new indBinList(CharacterDataBase.getFile("ind3.bin"));
		JobChangePrice = new JobChangePriceList(CharacterDataBase.getFile("JobChangePrice.cfg"), this);
		join = new JoinBinList(CharacterDataBase.getFile("join.bin"));
		seCrd = new SoundEffectCoordinateList(CharacterDataBase.getFile("seCrd.bin"));
		texanim = new TextureAnimationList(CharacterDataBase.getFile("texanim.bin"));
	}
	public void importIndex(String importDirectory)
	{
		try 
		{
			ind3 = new indBinList(Files.readAllLines(Paths.get(importDirectory+"indexList.csv"), Charset.forName("Shift-JIS")));
			bFM.Utils.DebugPrint("Sucessfully added Character Configuration File");
		} catch (IOException e) 
		{
			bFM.Utils.DebugPrint("Failed to locate Character Configuration File at: " + importDirectory+"indexList.csv");
			exportIndex(importDirectory);
			e.printStackTrace();
		}
	}
	public void exportIndex(String exportDirectory)
	{
		try 
		{
			bFM.Utils.DebugPrint("Attempting to extract file");
			Files.write(Paths.get(exportDirectory+"indexList.csv") , ind3.toString().getBytes(Charset.forName("Shift-JIS")));
		} catch (IOException i) 
		{
			bFM.Utils.DebugPrint("Failed to extract file");
		}
	}
	public JobChangePriceList getJobChangePriceList()
	{
		return JobChangePrice;
	}
	public byte[] getFile() 
	{
		PCKGManager CharacterDataBase = new PCKGManager();
		CharacterDataBase.addFile("chrDB2.lst", chrDB2.toBytes());
		CharacterDataBase.addFile("ind3.bin", ind3.toBytes());
		CharacterDataBase.addFile("join.bin", join.toBytes());
		CharacterDataBase.addFile("seCrd.bin", seCrd.toBytes());
		CharacterDataBase.addFile("texanim.bin", texanim.toBytes());
		CharacterDataBase.addFile("JobChangePrice.cfg", JobChangePrice.toBytes());
		return CharacterDataBase.getFile();
	}
	public void writeFile(String outputPath) 
	{
		try 
		{
			Files.write(Paths.get(outputPath) , getFile());
		} catch (IOException e) 
		{
			bFM.Utils.DebugPrint("Failed to write Character Database File");
		}
	}
	public void importJoin(String importDirectory) 
	{
		try 
		{
			join = new JoinBinList(Files.readAllLines(Paths.get(importDirectory+"joinList.csv"), Charset.forName("Shift-JIS")));
			bFM.Utils.DebugPrint("Sucessfully added Character Join File");
		} catch (IOException e) 
		{
			bFM.Utils.DebugPrint("Failed to locate Character Join File at: " + importDirectory+"joinList.csv");
			exportJoin(importDirectory);
			e.printStackTrace();
		}
	}
	public void exportJoin(String exportDirectory)
	{
		try 
		{
			bFM.Utils.DebugPrint("Attempting to extract file");
			Files.write(Paths.get(exportDirectory+"joinList.csv") , join.toString().getBytes(Charset.forName("Shift-JIS")));
		} catch (IOException i) 
		{
			bFM.Utils.DebugPrint("Failed to extract file");
		}
	}
	public void importCharacters(String importDirectory) 
	{
		try 
		{
			chrDB2 = new CharacterResourceList(Files.readAllLines(Paths.get(importDirectory+"characterList.csv"), Charset.forName("Shift-JIS")));
			//System.out.println(chrDB2.toString());
			//bFM.Utils.testDifferences(CharacterDataBase.getFile("chrDB2.lst"), chrDB2.toString().getBytes(Charset.forName("Shift-JIS")));
			bFM.Utils.DebugPrint("Sucessfully added Character Definition File");
		}
		catch (MalformedInputException e) 
		{
			try 
			{
				chrDB2 = new CharacterResourceList(Files.readAllLines(Paths.get(importDirectory+"characterList.csv")));
			} catch (IOException e1) 
			{
				bFM.Utils.DebugPrint("Failed to locate Character Definition File at: " + importDirectory+"characterList.csv");
				exportCharacters(importDirectory);
				e1.printStackTrace();
			}
		}
		catch (IOException e) 
		{
			bFM.Utils.DebugPrint("Failed to locate Character Definition File at: " + importDirectory+"characterList.csv");
			exportCharacters(importDirectory);
			e.printStackTrace();
		}
	}
	public void exportCharacters(String exportDirectory)
	{
		try 
		{
			bFM.Utils.DebugPrint("Attempting to extract file");
			Files.write(Paths.get(exportDirectory+"characterList.csv") , chrDB2.toCSV().getBytes(Charset.forName("Shift-JIS")));
		} catch (IOException i) 
		{
			bFM.Utils.DebugPrint("Failed to extract file");
		}
	}
	public void importPrice(String importDirectory) 
	{
		try 
		{
			JobChangePrice = new JobChangePriceList(Files.readAllLines(Paths.get(importDirectory+"JobChangePrice.csv"), Charset.forName("Shift-JIS")), this);
			bFM.Utils.DebugPrint("Sucessfully added Job Change Price File");
		} catch (IOException e) 
		{
			bFM.Utils.DebugPrint("Failed to locate Job Change Price File at: " + importDirectory+"JobChangePrice.csv");
			exportPrice(importDirectory);
			e.printStackTrace();
		}
	}
	public void exportPrice(String exportDirectory)
	{
		try 
		{
			bFM.Utils.DebugPrint("Attempting to extract file");
			Files.write(Paths.get(exportDirectory+"JobChangePrice.csv") , JobChangePrice.toString().getBytes(Charset.forName("Shift-JIS")));
		} catch (IOException i) 
		{
			bFM.Utils.DebugPrint("Failed to extract file");
		}
	}
	public void setData(byte[] data)
	{
		
		initializeFromBytes(data);
	}
	public byte[] toBytes()
	{
		return getFile();
	}
	public String getName()
	{
		return FileName;
	}
	public String toString()
	{
		return "File: " + FileName + " File Size: " + getFile().length + "\n";
	}
	public int getSize()
	{
		return getFile().length;
	}
	public void setName(String name) 
	{
		throw new UnsupportedOperationException("setName(String name) should not be called on type " + this.getClass());
	}
	public CharacterResourceList getCharacterResource() 
	{
		return chrDB2;
	}
	public indBinList getCharacterIndex() 
	{
		return ind3;
	}
	public JoinBinList getCharacterJoin() 
	{
		return join;
	}
	public SoundEffectCoordinateList getCharacterSoundEffect()
	{
		return seCrd;
	}
	public TextureAnimationList getTextureAnimations()
	{
		return texanim;
	}
	public String getNameByCode(int jobCode) 
	{
		return ind3.getNameByCode(jobCode);
	}
	public ind getCharacterIndex(int jobCode) 
	{
		return ind3.getByCode(jobCode);
	}
	public boolean equals(String name)
	{
		throw new UnsupportedOperationException("equals() should not be called on type " + this.getClass());
	}
}
