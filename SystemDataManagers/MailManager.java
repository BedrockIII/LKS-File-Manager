package SystemDataManagers;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import PCKGManager.PCKGManager;

public class MailManager 
{
	static String questPath = colReader.Main.outputPath + "System Data\\Menu Data\\";
	NormalMailManager normalMail;
	RandomMailManager randomMail;
	public MailManager(int languageCode) 
	{
		PCKGManager questData = new PCKGManager("Error");
		try
		{
			questData = new PCKGManager(Files.readAllBytes(Paths.get(questPath + "questdata4_" + languageCode + ".bin")));
		}
		catch(IOException e)
		{
			bFM.Utils.DebugPrint("Could not locate questdata pack at: " + questPath);
		}
		normalMail = new NormalMailManager(questData.getFile("normalquestdata.bin"));
		randomMail = new RandomMailManager(questData.getFile("randamquestdata.bin"));
	}
	public MailManager(List<String> normalMailLines, String randomMailPath)
	{
		normalMail = new NormalMailManager(normalMailLines);
		randomMail = new RandomMailManager(randomMailPath);
	}
	public String normalToString()
	{
		return normalMail.toString();
	}
	public void randomToString(String importPath)
	{
		try {
			randomMail.writeEnemyString(importPath);
		} catch (IOException e) 
		{//couldnt find them
			
		}
	}
	public void toPac(int languageCode) 
	{
		PCKGManager questData = new PCKGManager("questdata4_" + languageCode + ".bin");
		try 
		{
			questData = new PCKGManager(Files.readAllBytes(Paths.get(questPath + "questdata4_" + languageCode + ".bin")));
		} catch (IOException e) 
		{
			bFM.Utils.DebugPrint("Could not read questdata pack at: " + questPath);
		}
		questData.addFile("normalquestdata.bin", normalMail.toBytes());
		questData.addFile("randamquestdata.bin", randomMail.toBytes());
		try 
		{
			Files.write(Paths.get(questPath + "questdata4_" + languageCode + ".bin"),questData.getFile());
		} catch (IOException e) 
		{
			bFM.Utils.DebugPrint("Could not write questdata pack at: " + questPath);
		}
	}
}
