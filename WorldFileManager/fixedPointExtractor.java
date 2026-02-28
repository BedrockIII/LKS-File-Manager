package WorldFileManager;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class fixedPointExtractor 
{
	public static void main(String[] args) 
	{
		boolean subDirectories = true;
		boolean copyExistingFiles = false;
		String inputPath = "D:\\LKS Debug!!!!1\\ROMs\\Extracted\\map\\wg";
		String outputPath = "D:\\LKS Debug!!!!1\\AllGridFP";
		extractAllDir(subDirectories, copyExistingFiles, inputPath, outputPath, 0);
	}
	public static void extractAllDir(boolean subDirectories, boolean copyExistingFiles, String inputName, String outputName, int iterations)
	{
		if(iterations > 9) return;
		File directory = new File(inputName);
		File[] fileList = directory.listFiles();
		for(int i = 0; i < fileList.length; i++)
		{
			if(fileList[i].isFile())
			{
				if(outputName.charAt(outputName.length()-1)!='\\') outputName = outputName + "\\";
				if(inputName.charAt(inputName.length()-1)!='\\') inputName = inputName + "\\";
				if(fileList[i].getName().indexOf("fp")!=-1) decodeFixedPoints(inputName + fileList[i].getName(), fileList[i].getName(), outputName);
			} else if(fileList[i].isDirectory()) 
			{
				File outputFolder = new File(outputName + "\\" +  fileList[i].getName());
				if (!outputFolder.exists())
				{
					outputFolder.mkdirs();
				}
				extractAllDir(subDirectories, copyExistingFiles, inputName + "\\" + fileList[i].getName() + "\\", outputName + "\\" + fileList[i].getName(), iterations + 1);
			}
		}
	}
	private static void decodeFixedPoints(String importPath, String name, String outputPath)
	{
		fpInterpreter fixedPoints = null;
		
		try 
		{
			bFM.Utils.DebugPrint("Attempting to read Fixed Points file pack at: " + importPath);
			fixedPoints = new fpInterpreter(Files.readAllBytes(Paths.get(importPath)));
		} catch (IOException e) 
		{
			bFM.Utils.DebugPrint("Failed to read Fixed Point Pack.");
			return;
		}
		int index = name.indexOf('.');
		name = name.substring(0, index+1) + "b" + name.substring(index+1);
		try 
		{
			bFM.Utils.DebugPrint("Attempting to write Fixed Point file at: " + outputPath+'\\'+name);
			Files.write(Paths.get(outputPath+'\\'+name), fixedPoints.toBFP().getBytes());
		} catch (IOException e) 
		{
			bFM.Utils.DebugPrint("Failed to write Fixed Point File.");
		}
		
	}
}
