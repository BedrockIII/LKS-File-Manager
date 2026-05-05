package ResourceManagers.MapDatabaseManager;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;

import PCKGManager.PCKGManager;

public class mapDataBaseManager 
{
	PCKGManager MapDataBase = new PCKGManager("mapDB0");
	public mapDataBaseManager(String mapDBPath)
	{
		try 
		{
			bFM.Utils.DebugPrint("Attempting to read mapDB package at: " + mapDBPath);
			MapDataBase = new PCKGManager(Files.readAllBytes(Paths.get(mapDBPath)));
		} catch (IOException e) 
		{
			bFM.Utils.DebugPrint("Failed to read package. Program will skip this step for now");
		}
	}
	private void addGroundConfig(String inputPath)
	{
		try 
		{
			MapDataBase.addFile("grnd2.cfg", Files.readAllBytes(Paths.get(inputPath+"grnd2.cfg")));
			bFM.Utils.DebugPrint("Sucessfully added Ground Configuration File");
		} catch (IOException e) 
		{
			bFM.Utils.DebugPrint("Failed to locate Ground Configuration File at: " + inputPath+"grnd2.cfg");
			try 
			{
				bFM.Utils.DebugPrint("Attempting to extract file");
				Files.write(Paths.get(inputPath+"grnd2.cfg") , MapDataBase.getFile("grnd2.cfg"));
			} catch (IOException i) 
			{
				bFM.Utils.DebugPrint("Failed to extract file");
			}
		}
	}
	private void addInteractionPositions(String inputPath)
	{
		try 
		{
			MapDataBase.addFile("buildPos0.lst", Files.readAllBytes(Paths.get(inputPath+"buildPos0.lst")));
			bFM.Utils.DebugPrint("Sucessfully added Building Position File");
		} catch (IOException e) 
		{
			bFM.Utils.DebugPrint("Failed to locate Building Position File at: " + inputPath+"buildPos0.lst");
			try 
			{
				bFM.Utils.DebugPrint("Attempting to extract file");
				Files.write(Paths.get(inputPath+"buildPos0.lst") , MapDataBase.getFile("buildPos0.lst"));
			} catch (IOException i) 
			{
				bFM.Utils.DebugPrint("Failed to extract file");
			}
		}
	}
	private void addBuildingList(String inputPath)
	{
		try 
		{
			BuildingResourceList buildingList = new BuildingResourceList(Files.readAllLines(Paths.get(inputPath+"building0.lst"), Charset.forName("Shift_JIS")));
			MapDataBase.addFile("building0.lst", buildingList.toBytes());
			//MapDataBase.addFile("building0.lst", Files.readAllBytes(Paths.get(inputPath+"building0.lst")));
			//bFM.Utils.DebugPrint("Sucessfully added Building Configuration File");
		} catch (IOException e) 
		{
			bFM.Utils.DebugPrint("Failed to locate Building Configuration File at: " + inputPath + "building0.lst");
			try 
			{
				bFM.Utils.DebugPrint("Attempting to extract file");
				Files.write(Paths.get(inputPath+"building0.lst") , MapDataBase.getFile("building0.lst"));
			} catch (IOException i) 
			{
				bFM.Utils.DebugPrint("Failed to extract file");
			}
		}
	}
	private void addBuildingPlacement(String inputPath)
	{
		try 
		{
			MapDataBase.addFile("extPlace1.lst", Files.readAllBytes(Paths.get(inputPath+"extPlace1.lst")));
			bFM.Utils.DebugPrint("Sucessfully added Exterior Places Configuration File");
		} catch (IOException e) 
		{
			bFM.Utils.DebugPrint("Failed to locate Exterior Places File at: " + inputPath + "extPlace1.lst");
			try 
			{
				bFM.Utils.DebugPrint("Attempting to extract file");
				Files.write(Paths.get(inputPath+"extPlace1.lst") , MapDataBase.getFile("extPlace1.lst"));
			} catch (IOException i) 
			{
				bFM.Utils.DebugPrint("Failed to extract file");
			}
		}
	}
	public void addFiles(String inputPath)
	{
		addGroundConfig(inputPath);
		addInteractionPositions(inputPath);
		addBuildingList(inputPath);
		addBuildingPlacement(inputPath);
	}
	public void writeFile(String mapDBPath)
	{
		try 
		{
			Files.write(Paths.get(mapDBPath) , MapDataBase.getFile());
		} catch (IOException e) 
		{
			bFM.Utils.DebugPrint("Failed to write Map Data Base File");
		}
	}
}
