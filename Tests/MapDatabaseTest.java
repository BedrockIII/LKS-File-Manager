package Tests;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import PCKGManager.PCKGManager;
import ResourceManagers.MapDatabaseManager.BuildingResourceList;
import ResourceManagers.MapDatabaseManager.exteriorPlaceList;
import ResourceManagers.MapDatabaseManager.GroundConfiguration.GroundMemoryAssignmentManager;
import ResourceManagers.MapDatabaseManager.InteractionPoints.InteractionPointManager;
import ResourceManagers.MapDatabaseManager.PlaceObject.PlacementObjectManager;
import bFM.Testing;
import bFM.Utils;

public class MapDatabaseTest
{
	public static void main(String[] args)
	{
		String path = "D:\\Dolphin_Emulator\\Load\\Riivolution\\LKSMapTesting\\Resources";
		try {
			byte[] file = Files.readAllBytes(Paths.get(path).resolve("mapDB0.pac"));
			PCKGManager mapDB = new PCKGManager(file);
			TestBuildingResource(mapDB.getFile("building0.lst"));
			TestExteriorPlacement(mapDB.getFile("extPlace1.lst"));
			TestGroundConfig(mapDB.getFile("grnd2.cfg"));
			TestPlaceObject(mapDB.getFile("placeObj3.bin"));
			TestInteractionPoint(mapDB.getFile("buildPos0.lst"));
			//
		} catch (IOException e)
		{
			Testing.Assert("Could Not Read File at: " + path);
			e.printStackTrace();
		}
	}
	private static void TestBuildingResource(byte[] src)
	{
		BuildingResourceList buildingList = new BuildingResourceList(Utils.bytesToStrs(src));
		if(Testing.TestPercentDifference(src, buildingList.toBytes()) == false)
		{
			Testing.saveFiles(src, buildingList.toBytes(), "building0.lst");
			Testing.Assert("Building Resource List is Not the same!\n");
		}
	}
	private static void TestExteriorPlacement(byte[] src)
	{
		exteriorPlaceList exteriorPlaces = new exteriorPlaceList(Utils.bytesToStrs(src));
		if(Testing.TestPercentDifference(src, exteriorPlaces.toBytes()) == false)
		{
			Testing.saveFiles(src, exteriorPlaces.toBytes(), "extPlace1.lst");
			Testing.Assert("Exterior Placement List is Not the same!\n");
		}
	}
	private static void TestGroundConfig(byte[] src)
	{
		GroundMemoryAssignmentManager grnd = new GroundMemoryAssignmentManager(src);
		if(Testing.TestPercentDifference(src, grnd.toBytes()) == false)
		{
			Testing.saveFiles(src, grnd.toBytes(), "grnd2.cfg");
			Testing.Assert("Ground Configuration List is Not the same!\n");
		}
	}
	private static void TestPlaceObject(byte[] src)
	{
		PlacementObjectManager placeObj = new PlacementObjectManager(src);
		if(Testing.TestPercentDifference(src, placeObj.toBytes()) == false)
		{
			Testing.saveFiles(src, placeObj.toBytes(), "placeObj3.bin");
			Testing.Assert("Placement Object Binary is Not the same!\n");
		}
	}
	private static void TestInteractionPoint(byte[] src)
	{
		InteractionPointManager buildPos = new InteractionPointManager(src);
		if(Testing.TestPercentDifference(src, buildPos.toBytes()) == false)
		{
			Testing.saveFiles(src, buildPos.toBytes(), "buildPos0.lst");
			Testing.Assert("Interaction Point List is Not the same!\n");
		}
	}
}
