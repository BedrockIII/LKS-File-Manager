package ResourceManagers.MapDatabaseManager;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;

import PCKGManager.PCKGManager;
import bFM.OpenedFile;
import bFM.Utils;

public class mapDataBaseManager implements OpenedFile
{
	PCKGManager MapDataBase = new PCKGManager("mapDB0");
	BuildingResourceList buildingList = null;
	exteriorPlaceList exteriorPlaces = null;
	public mapDataBaseManager(byte[] data)
	{
		initializeFromBytes(data);
	}
	private void initializeFromBytes(byte[] data)
	{
		MapDataBase = new PCKGManager(data);
		buildingList = new BuildingResourceList(Utils.bytesToStrs(MapDataBase.getFile("building0.lst")));
		exteriorPlaces = new exteriorPlaceList(Utils.bytesToStrs(MapDataBase.getFile("extPlace1.lst")));
	}
	public mapDataBaseManager(String mapDBPath)
	{
		try 
		{
			bFM.Utils.DebugPrint("Attempting to read mapDB package at: " + mapDBPath);
			initializeFromBytes(Files.readAllBytes(Paths.get(mapDBPath)));
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
			buildingList = new BuildingResourceList(Files.readAllLines(Paths.get(inputPath+"building0.lst"), Charset.forName("Shift_JIS")));
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
			Files.write(Paths.get(mapDBPath) , toBytes());
		} catch (IOException e) 
		{
			bFM.Utils.DebugPrint("Failed to write Map Data Base File");
		}
	}
	public BuildingResourceList getBuildingDefinitions() 
	{
		if(buildingList == null) throw new IllegalArgumentException("Map Database has no defined Building List File.");
		return buildingList;
	}
	public exteriorPlaceList getExteriorPlacements() 
	{
		if(exteriorPlaces == null) throw new IllegalArgumentException("Map Database has no defined Exterior Placement File.");
		return exteriorPlaces;
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
		MapDataBase.addFile("building0.lst", buildingList.toBytes());
		MapDataBase.addFile("extPlace1.lst", exteriorPlaces.toBytes());
		return MapDataBase.getFile();
	}
	public void setName(String name) 
	{
		throw new UnsupportedOperationException("setName(String name) should not be called on type " + this.getClass());
	}
	public String getName() 
	{
		return "mapDB0.pac";
	}
	public int getSize() 
	{
		return toBytes().length;
	}
}
