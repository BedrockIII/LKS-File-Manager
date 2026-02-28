package Main;
import java.io.File;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.IntBuffer;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;

import GUI.GUI;
import LZConverter.HexInputStream;
import LZConverter.JavaDSDecmp;
import PCKGManager.PCKGManager;

public class MainGUI 
{
	static Scanner input = new Scanner(System.in);	
	
	public static void main(String args[]) throws IOException 
	{
		new GUI();
		System.out.println("Pac File Manager");
		System.out.println("Version 2.3");
		System.out.println("     Fixed some bugs");
		System.out.println("     Extracting Pacs now tells which files were found");
		String inputName = "";
		String outputName;
		
		System.out.println("Enter the Path where you want to output");
		outputName = input.nextLine();
		if(outputName.charAt(0) == '\"') outputName = outputName.substring(1);
		if(outputName.charAt(outputName.length()-1) == '\"') outputName = outputName.substring(0,outputName.length()-1);
		if(!(outputName.charAt(outputName.length()-1) == '\\')) outputName = outputName + '\\';
		
		System.out.println("Type 1 use the PCKG Manager, Type 2 to extract all pacs in a directory, Type 5 to use the Col Manager");
		int whatToDo = input.nextInt();
		if(whatToDo == 2)whatToDo = 4;
		input.nextLine();
		if( whatToDo == 1)
		{
			PCKGManager pac;
			System.out.println("Enter the File Path of the PCKG file or press enter to create a New PCKG file.");
			inputName = input.nextLine();
			if(inputName.length()<1)
			{
				System.out.println("Enter the name of the PCKG file or press enter to use a default name (include file extension)");
				String name = "NewPac.pac";
				name = input.nextLine();
				pac = new PCKGManager(name);
			}
			else
			{
				if(inputName.charAt(0) == '\"') inputName = inputName.substring(1);
				if(inputName.charAt(inputName.length()-1) == '\"') inputName = inputName.substring(0,inputName.length()-1);
				pac = new PCKGManager(Files.readAllBytes(Paths.get(inputName)), (Paths.get(inputName).getFileName().toString()));
			}
			System.out.println("Type 1 to add a file, 2 to save the PCKG file, or 3 to extract all files from the PCKG");
			whatToDo = input.nextInt();
			input.nextLine();
			while(whatToDo == 1)
			{
				System.out.println("Enter Path of File to add");
				String newPac = input.nextLine();
				if(newPac.charAt(0) == '\"') newPac = newPac.substring(1);
				if(newPac.charAt(newPac.length()-1) == '\"') newPac = newPac.substring(0,newPac.length()-1);
				pac.addFile((Paths.get(newPac).getFileName().toString()), Files.readAllBytes(Paths.get(newPac)));
				System.out.println("Type 1 to continue, 2 to rePAC, or 3 to extract all");
				whatToDo = input.nextInt();
				input.nextLine();
			}
			if(whatToDo == 2)
			{
				pac.writePac(outputName);
				System.out.println("extracted");
			}
			else
			{
				pac.extractAll(outputName);
			}
		} else if (whatToDo == 2)
		{
			System.out.println("Name the file to Decompress:");
			inputName = input.nextLine();
			System.out.println("Where do you want to file to go to:");
			outputName = input.nextLine();
			HexInputStream decomp = new HexInputStream(inputName);
			int[] outData = JavaDSDecmp.Decompress(decomp, outputName);
			ByteBuffer byteBuffer = ByteBuffer.allocate(outData.length * 4);        
	        IntBuffer intBuffer = byteBuffer.asIntBuffer();
	        intBuffer.put(outData);
			byte[] array = byteBuffer.array();
	        byte[] arr2 = new byte[array.length/4];
	        for(int i = 1; i<arr2.length; i++)
	        {
	        	arr2[i-1] = array[i*4-1];
	        }
			Files.write(Paths.get(outputName), arr2);
			System.out.println("Done!");
		}else if (whatToDo ==3)
		{
			System.out.println("Name the file to Decompress:");
			inputName = input.nextLine();
			System.out.println("Where do you want to file to go to:");
			outputName = input.nextLine();
			File folder = new File(inputName);
			File[] listOfFiles = folder.listFiles();
			while(listOfFiles.length == 0)
			{
				listOfFiles = folder.listFiles();
			}
			try
			{
				for (int i = 0; i < listOfFiles.length; i++) 
				{
						HexInputStream decomp = new HexInputStream(listOfFiles[i].toString());
						int[] outData = JavaDSDecmp.Decompress(decomp, outputName);
						ByteBuffer byteBuffer = ByteBuffer.allocate(outData.length * 4);        
						IntBuffer intBuffer = byteBuffer.asIntBuffer();
						intBuffer.put(outData);
						byte[] array = byteBuffer.array();
						byte[] arr2 = new byte[array.length/4];
						for(int n = 1; n<arr2.length; n++)
						{
							arr2[n-1] = array[n*4-1];
						}
						Files.write(Paths.get(outputName), arr2);
						System.out.println("Done!");
				}
			}catch (IOException e) {
				e.printStackTrace();
			}
		} else if(whatToDo==4)
		{
			System.out.println("Enter the File Path of the Directory");
			
			inputName = input.nextLine();
			
			extractAllDir(true, true, true, inputName, outputName, 0);
		} 
	}
	public static void extractAllDir(boolean createFolders, boolean subDirectories, boolean copyExistingFiles, String inputName, String outputName, int iterations)
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
				String thisOutputName = outputName;
				if(createFolders)
				{
					thisOutputName = thisOutputName +   fileList[i].getName();
					try {
						if(PCKGManager.isPAC(Files.readAllBytes(fileList[i].toPath()))) 
							{
								File outputFolder = new File(thisOutputName);
								thisOutputName = thisOutputName + "\\";
								if (!outputFolder.exists())
								{
									outputFolder.mkdirs();
								}
							} else if(copyExistingFiles) Files.write(Paths.get(thisOutputName), Files.readAllBytes(fileList[i].toPath()));
					} catch (IOException e) {
						System.out.println("Couldn't Determine if file is PCKG");
					}
					
				}
				try 
				{
					new PCKGManager(Files.readAllBytes(Paths.get(inputName + fileList[i].getName())), fileList[i].getName()).extractAll(thisOutputName);
				} catch (IOException e) 
				{
					System.out.println("Could not read file: " + inputName + directory);
				}
			} else if(fileList[i].isDirectory()) 
			{
				File outputFolder = new File(outputName + "\\" +  fileList[i].getName());
				if (!outputFolder.exists())
				{
					outputFolder.mkdirs();
				}
				extractAllDir(createFolders, subDirectories, copyExistingFiles, inputName + "\\" + fileList[i].getName() + "\\", outputName + "\\" + fileList[i].getName(), iterations + 1);
			}
		}
	}
}