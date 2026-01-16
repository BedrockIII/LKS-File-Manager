package SystemDataManagers;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import PCKGManager.PCKGManager;

public class RandomMailManager 
{
	PCKGManager questData;
	//deal with:	RandomEnemyTeams(EnemyGroupID, EnemyGroup)
	//				RandomEnemyLocations(EnemyPlace, EnemyPlacePos, EnemyPlaceID, EnemyID)
	//				Letter Data(TitleEnemy, Message, MessageAEnemy, MessageBEnemy)
	//				Tone Data(Tone1-9)
	ArrayList<ArrayList<RandomEnemyTeam>> Enemies = new ArrayList<ArrayList<RandomEnemyTeam>>();
	ArrayList<ArrayList<RandomQuestLocation>> Locations = new ArrayList<ArrayList<RandomQuestLocation>>();

	public RandomMailManager(byte[] data)
	{
		questData = new PCKGManager(data);
		instanceEnemies(1);
		instanceEnemies(2);
		instanceEnemies(3);
		instanceEnemies(4);
		instanceEnemies(5);
		//RandomEnemyLocations
		instanceLocations(1);
		instanceLocations(2);
		instanceLocations(3);
		instanceLocations(4);
		instanceLocations(5);
		//LetterData
		
		//Tones
	} 
	private void instanceEnemies(int difficulty)
	{
		ArrayList<String> enemyNames = extractStrings(questData.getFile("EnemyGroup" + difficulty));
		int[] groupCodes = extractShorts(questData.getFile("EnemyGroupID" + difficulty));
		Enemies.add(new ArrayList<RandomEnemyTeam>());
		for(int i = 0; i < enemyNames.size(); i++)
		{
			Enemies.get(difficulty-1).add(new RandomEnemyTeam(enemyNames.get(i), groupCodes[i]));
		}
	}
	private void instanceLocations(int difficulty)
	{
		ArrayList<String> locationNames = extractStrings(questData.getFile("EnemyPlace" + difficulty));
		int[] coords = extractShorts(questData.getFile("EnemyPlacePos" + difficulty));
		Locations.add(new ArrayList<RandomQuestLocation>());
		for(int i = 0; i < locationNames.size(); i++)
		{
			Locations.get(difficulty-1).add(new RandomQuestLocation(locationNames.get(i), coords[3*i],coords[3*i+1],coords[3*i+2]));
		}
	}
	
	
	public RandomMailManager(String randomMailPath) 
	{
		questData = new PCKGManager("randamquestdata.bin");
		try
		{
			questData = new PCKGManager(new PCKGManager(Files.readAllBytes(Paths.get(colReader.Main.outputPath + "System Data\\Menu Data\\questdata4_1.bin"))).getFile("randamquestdata.bin"));
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		try {
			randomEnemies(randomMailPath);
		} catch (IOException e) 
		{
			bFM.Utils.DebugPrint("Could not find extracted text files for enemies, attempting to extract them from pack now.");
			instanceEnemies(1);
			instanceEnemies(2);
			instanceEnemies(3);
			instanceEnemies(4);
			instanceEnemies(5);
			try {
				writeGroupString(randomMailPath);
			} catch (IOException e1) {
				bFM.Utils.DebugPrint("Failed to extract files. I'm sorry I hope you can forgive me for my incompetence");
			}
		}
		try {
			randomLocations(randomMailPath);
		} catch (IOException e) 
		{
			bFM.Utils.DebugPrint("Could not find extracted text files for locations, attempting to extract them from pac now.");
			instanceLocations(1);
			instanceLocations(2);
			instanceLocations(3);
			instanceLocations(4);
			instanceLocations(5);
			try {
				writePlaceString(randomMailPath);
			} catch (IOException e1) 
			{
				bFM.Utils.DebugPrint("Failed to extract files. I'm sorry I hope you can forgive me for my incompetence");
			}
		}
		//letter
		//tones
	}
	private void randomEnemies(String randomMailPath) throws IOException
	{
		for(int diff = 1; diff<6; diff++)
		{
			ArrayList<RandomEnemyTeam> enemies = new ArrayList<RandomEnemyTeam>();
			List<String> lines = Files.readAllLines(Paths.get(randomMailPath + "EnemyTeams" + diff + ".txt"));
			RandomEnemyTeam enemy = new RandomEnemyTeam();
			for(int i = 0; i<lines.size(); i++)
			{
				String line = lines.get(i);
				if(line.indexOf("<Monster Team>")!=-1)
				{
					if(enemy.getName().equals("Enemy")==false)
					{
						enemies.add(enemy);
						enemy = new RandomEnemyTeam();
					}
					enemy = new RandomEnemyTeam(line);
				}
				else if(line.indexOf("<Group Code>")!=-1)
				{
					enemy.addGroup(line);		
				}
			}
			enemies.add(enemy);
			enemy = new RandomEnemyTeam();
			Enemies.add(enemies);
		}
	}
	private void randomLocations(String randomMailPath) throws IOException
	{
		for(int diff = 1; diff<6; diff++)
		{
			ArrayList<RandomQuestLocation> places = new ArrayList<RandomQuestLocation>();
			
			List<String> lines = Files.readAllLines(Paths.get(randomMailPath + "RandomLocations" + diff + ".txt"));
			RandomQuestLocation place = new RandomQuestLocation();
			for(int i = 0; i<lines.size(); i++)
			{
				String line = lines.get(i);
				if(line.indexOf("<Position Name>")!=-1)
				{
					if(place.getNameString().equals("Place")==false)
					{
						places.add(place);
						place = new RandomQuestLocation();
					}
					place = new RandomQuestLocation(line);
				}
				else if(line.indexOf("<Position Coordinates>")!=-1)
				{
					place.setCoords(line);
						
				}
			}
			places.add(place);
			place = new RandomQuestLocation();
			
			Locations.add(places);
		}
	}
	private int[] extractShorts(byte[] file) 
	{
		int[] ret = new int[file.length/2];
		for(int i = 0; i<file.length; i+=2)
		{
			byte[] int1 = {0, 0, file[i], file[i+1]};
			ret[i/2] = ByteBuffer.wrap(int1).order(ByteOrder.BIG_ENDIAN).getInt(0);;
		}
		return ret;
	}
	private static ArrayList<String> extractStrings(byte[] data)
	{
		ArrayList<String> Strings = new ArrayList<String>();
		String temp = "";
		for(int i = 0; i<data.length; i++)
		{
			if(data[i]==0x00)
			{
				Strings.add(temp);
				temp = "";
			}
			else 
			{
				temp+=(char)data[i];
			}
		}
		return Strings;
	}
	
	
	
	
	public byte[] toBytes()
	{
		for(int i = 1; i < 6; i++)
		{
			questData.addFile("EnemyGroup" + i, getEnemyNames(i));
			questData.addFile("Enemy" + i, getEnemyNames(i));
			questData.addFile("EnemyGroupID" + i, getEnemyGroups(i));
			questData.addFile("EnemyPlaceID" + i, getEnemyGroups(i));
			questData.addFile("EnemyPlace" + i, getEnemyPlaces(i));
			questData.addFile("EnemyPlacePos" + i, getEnemyPlaceCoords(i));
		}
		return questData.getFile();
	}
	private byte[] getEnemyGroups(int diff) 
	{
		ArrayList<RandomEnemyTeam> enemies = Enemies.get(diff-1);
		byte[] ret = new byte[0];
		for(int i = 0; i < enemies.size(); i++)
		{
			ret = bFM.Utils.mergeArrays(ret, enemies.get(i).getGroupCode());
		}
		return ret;
	}
	private byte[] getEnemyNames(int diff)
	{
		ArrayList<RandomEnemyTeam> enemies = Enemies.get(diff-1);
		byte[] ret = new byte[0];
		for(int i = 0; i < enemies.size(); i++)
		{
			ret = bFM.Utils.mergeArrays(ret, bFM.Utils.mergeArrays(enemies.get(i).getName().getBytes(),new byte[1]));
		}
		return ret;
	}
	private byte[] getEnemyPlaces(int diff) 
	{
		ArrayList<RandomQuestLocation> places = Locations.get(diff-1);
		byte[] ret = new byte[0];
		for(int i = 0; i < places.size(); i++)
		{
			ret = bFM.Utils.mergeArrays(ret, places.get(i).getName());
		}
		return ret;
	}
	private byte[] getEnemyPlaceCoords(int diff) 
	{
		ArrayList<RandomQuestLocation> places = Locations.get(diff-1);
		byte[] ret = new byte[0];
		for(int i = 0; i < places.size(); i++)
		{
			ret = bFM.Utils.mergeArrays(ret, places.get(i).getCoords());
		}
		return ret;
	}
	
	
	//To text data
	public void writeEnemyString(String path) throws IOException
	{
		bFM.Utils.DebugPrint("Writing Extracted Random Quest Files");
		writeGroupString(path);
		writePlaceString(path);
	}
	private void writeGroupString(String path) throws IOException
	{
		bFM.Utils.DebugPrint("Writing Extracted Random Quest Group Files");
		Files.write(Paths.get(path + "EnemyTeams1.txt"), getEnemyString(1).getBytes());
		Files.write(Paths.get(path + "EnemyTeams2.txt"), getEnemyString(2).getBytes());
		Files.write(Paths.get(path + "EnemyTeams3.txt"), getEnemyString(3).getBytes());
		Files.write(Paths.get(path + "EnemyTeams4.txt"), getEnemyString(4).getBytes());
		Files.write(Paths.get(path + "EnemyTeams5.txt"), getEnemyString(5).getBytes());
	}
	private void writePlaceString(String path) throws IOException
	{
		bFM.Utils.DebugPrint("Writing Extracted Random Quest Location Files");
		Files.write(Paths.get(path + "RandomLocations1.txt"), getLocationString(1).getBytes());
		Files.write(Paths.get(path + "RandomLocations2.txt"), getLocationString(2).getBytes());
		Files.write(Paths.get(path + "RandomLocations3.txt"), getLocationString(3).getBytes());
		Files.write(Paths.get(path + "RandomLocations4.txt"), getLocationString(4).getBytes());
		Files.write(Paths.get(path + "RandomLocations5.txt"), getLocationString(5).getBytes());	
	}
	private String getEnemyString(int difficulty)
	{
		String ret = "";
		for(int i = 0; i < Enemies.get(difficulty-1).size(); i++)
		{
			ret += Enemies.get(difficulty-1).get(i).toString();
		}
		return ret;
	}
	private String getLocationString(int difficulty)
	{
		String ret = "";
		for(int i = 0; i < Locations.get(difficulty-1).size(); i++)
		{
			ret += Locations.get(difficulty-1).get(i).toString();
		}
		return ret;
	}
}
