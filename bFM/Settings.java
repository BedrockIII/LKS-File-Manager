package bFM;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.concurrent.TimeUnit;

import GUI.GUI;
import colReader.ColReader;

public class Settings 
{
	//GUI Settings
	public static final int assetHeight = 20;
	public static final int buttonWidth = 150;
	public static final int pacOffset = 50;
	public static final int rowWidth = 300;
	public static final int indentSize = 15;
	public static Color bgColor = new Color(169, 169, 169);
	public static int windowX = 0;
	public static int windowY = 0;
	public static int windowWidth = rowWidth*2;
	public static int windowHeight = assetHeight*25;
	public static boolean windowMaximized = false;
	public static final Dimension buttonSize = new Dimension(buttonWidth,assetHeight);
	public static final Color selectedColor = Color.YELLOW;
	public static final Color DarkerColor = new Color(192, 192, 192);
	public static final Color LighterColor = new Color(245, 245, 245);
	//Layout Settings
	private static GridBagConstraints defaultLayout = null;
	//Path Settings
	private static Path savePath = null; // Path of Settings, dont change
	public static String lastFileOpenPath = null;
	public static String lastFileSavePath = null;
	public static String lastFileImportPath = null;
	public static String importPath = "D:\\LKS Mod\\";
	public static String outputPath = "D:\\Dolphin_Emulator\\Load\\Riivolution\\LKSMapTesting\\";
	//Tool Settings
	public static int LanguageCode = 1;
	
	public static void printVersionData()
	{
		System.out.println("LKS File Manager");
		System.out.println("Version 3.8b");
		System.out.println("     Added: Bit Flag Editor");
		System.out.println("     		Automatically savesw in format compatible with Debug mode, and can also be loaded from debug bit flag list");
		System.out.println("     		VMC Editor (WIP)");
		System.out.println("     Fixed: FP Editor (WIP, Combining scaling and rotations doesn't work properly, but this may be an issue with the game not my code.");
		try {
			TimeUnit.MILLISECONDS.sleep(100);
		} catch (InterruptedException e)
		{
		}
		System.err.println("     Known Bugs: VMC Editor Fails to get Strings");
		System.err.println("     		 VMC Editor Fails to place some labels");
		System.err.println("   	 	 	 VMC Editor Fails to read some types of events properly");
		System.err.println("    	 	 VMC Editor Fails to get Strings");
		System.err.println("     		 FP Editor Fails to properly Extract rotation when there are scalars applied to perpendicular axes");
		System.err.println("     		 There is a fly in my room as I am writing this. Help.");
		System.err.println("     		 Generic File Text Editor Terminates at null strings");
		System.err.println("     		 Generic Text Editor doesn't save edits.");
		System.err.println("     		 Flag Viewer can only display same flag label in one place at a time");
		System.err.println("     		 Some Editors still load all sub-GUIs when they aren't neaded");
		System.err.println("     		 Some Editors dont load Bit Flags Labels and still use int flags");
	}
	
	public static void setSettings() 
	{
		String ret = "Little King's Story File Manager Settings\n";
		ret += bFM.Utils.getAsSetting("Debug Output", bFM.Utils.debugOutput);
		ret += bFM.Utils.getAsSetting("X Pos", GUI.frame.getX());
		ret += bFM.Utils.getAsSetting("Y Pos", GUI.frame.getY());
		ret += bFM.Utils.getAsSetting("Window Width", GUI.frame.getWidth());
		ret += bFM.Utils.getAsSetting("Window Height", GUI.frame.getHeight());
		ret += bFM.Utils.getAsSetting("Window Maximized", GUI.frame.getExtendedState() == Frame.MAXIMIZED_BOTH);
		ret += bFM.Utils.getAsSetting("Optimize Collision", ColReader.optimizeCollision);
		ret += bFM.Utils.getAsSetting("Modding Path", importPath);
		ret += bFM.Utils.getAsSetting("Riivolution Mod Path", outputPath);
		ret += bFM.Utils.getAsSetting("Last Save Path", lastFileSavePath);
		ret += bFM.Utils.getAsSetting("Last Open Path", lastFileOpenPath);
		ret += bFM.Utils.getAsSetting("Last Import Path", lastFileImportPath);
		ret += bFM.Utils.getAsSetting("Default Language Code", LanguageCode);
		try 
		{
			//System.out.println(Paths.get("LKS File Manager Config.cfg").toAbsolutePath());
			if(savePath==null)
			{
				Files.write(Paths.get("LKS File Manager Config.cfg"), Utils.encodeStringToBytes(ret));
				Files.write(Paths.get("BitFlgNameList.txt"), Utils.encodeStringToBytes(FlagManager.getBitFlagList()));
			}
			else
			{
				Files.write(savePath.resolve("LKS File Manager Config.cfg"), Utils.encodeStringToBytes(ret));
				Files.write(savePath.resolve("BitFlgNameList.txt"), Utils.encodeStringToBytes(FlagManager.getBitFlagList()));
			}
		} catch (IOException e) 
		{
			System.out.println("Failed to save settings file");
			e.printStackTrace();
		}
		
	}
	public static void getSettings()
	{
		try
		{
			List<String> lines;
			if(savePath==null)
			{
				lines = Files.readAllLines(Paths.get("LKS File Manager Config.cfg"));
			}
			else
			{
				lines = Files.readAllLines(savePath.resolve("LKS File Manager Config.cfg"));
			}
			
			for(String line : lines)
			{
				if(line.indexOf("Optimize Collision")!=-1)
				{
					ColReader.optimizeCollision = bFM.Utils.getSettingValue(line);
				}
				else if(line.indexOf("Debug Output")!=-1)
				{
					bFM.Utils.debugOutput = bFM.Utils.getSettingValue(line);
				}
				else if(line.indexOf("X Pos")!=-1)
				{
					windowX = bFM.Utils.getSettingValueInt(line);
				}
				else if(line.indexOf("Y Pos")!=-1)
				{
					windowY = bFM.Utils.getSettingValueInt(line);
				}
				else if(line.indexOf("Window Width")!=-1)
				{
					windowWidth = bFM.Utils.getSettingValueInt(line);
				}
				else if(line.indexOf("Window Height")!=-1)
				{
					windowHeight = bFM.Utils.getSettingValueInt(line);
				}
				else if(line.indexOf("Window Maximized")!=-1)
				{
					windowMaximized = bFM.Utils.getSettingValue(line);
				}
				else if(line.indexOf("Modding Path")!=-1)
				{
					importPath = bFM.Utils.getSettingValueString(line);
				}
				else if(line.indexOf("Riivolution Mod Path")!=-1)
				{
					Settings.outputPath = bFM.Utils.getSettingValueString(line);
				}
				else if(line.indexOf("Last Save Path")!=-1)
				{
					lastFileSavePath = bFM.Utils.getSettingValueString(line);
				}
				else if(line.indexOf("Last Open Path")!=-1)
				{
					lastFileOpenPath = bFM.Utils.getSettingValueString(line);
				}
				else if(line.indexOf("Last Import Path")!=-1)
				{
					lastFileImportPath = bFM.Utils.getSettingValueString(line);
				}
				else if(line.indexOf("Default Language Code")!=-1)
				{
					LanguageCode = bFM.Utils.getSettingValueInt(line);
				}
			}
		}
		catch (IOException e)
		{
			if(savePath == null) 
			{
				//System.out.println("Failed to read User Settings Due to Null Path assuming defaults");
				return;
			}
			System.out.println("Failed to read User Settings at " + savePath.toString() + ", assuming defaults");
		}
		try 
		{
			if(savePath==null)
			{
				FlagManager.importBitFlags(Files.readAllBytes(Paths.get("BitFlgNameList.txt")));
			}
			else
			{
				FlagManager.importBitFlags(Files.readAllBytes(savePath.resolve("BitFlgNameList.txt")));
			}
		} catch (IOException e) 
		{
			if(savePath == null) 
			{
				return;
			}
			System.out.println("Failed to read Bit Flag Definitions at " + savePath.toString() + ", assuming defaults");
		}
	}
	public static void setSettingsFile(String string) 
	{
		System.out.println("Setting Save Directory to: " + string);
		savePath = Paths.get(string);
		getSettings();
	}
	public static GridBagConstraints getDefaultConstraints()
	{
		if(defaultLayout == null)
		{
			defaultLayout = new GridBagConstraints();
			defaultLayout.anchor = GridBagConstraints.NORTHWEST;
			defaultLayout.gridwidth = GridBagConstraints.REMAINDER;
			defaultLayout.fill = GridBagConstraints.HORIZONTAL;
			defaultLayout.weighty = 0.0;
			defaultLayout.weightx = 1.0;
		}
		GridBagConstraints layout = new GridBagConstraints();
		layout.anchor = defaultLayout.anchor;
		layout.gridwidth = defaultLayout.gridwidth;
		layout.fill = defaultLayout.fill;
		layout.weighty = defaultLayout.weighty;
		layout.weightx = defaultLayout.weightx;
		layout.insets = new Insets(0, 3, 0, 0);
		return layout;
	}
	public static String getLanguage()
	{
		//Return the name of the language based off number. default to english as i speak that
		return Utils.getLanguage(LanguageCode);
	}
}
