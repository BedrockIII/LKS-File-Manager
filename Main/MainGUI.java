package Main;
import java.io.IOException;
import java.util.Scanner;

import GUI.GUI;

public class MainGUI 
{
	public static GUI fileManager = null;
	static Scanner input = new Scanner(System.in);	
	
	public static void main(String args[]) throws IOException 
	{
		PCKGManagerMain.printVersionData();
		bFM.Utils.debugOutput = true;
		fileManager = new GUI();
		if(args.length > 0)
		{
			fileManager.setOpenFile(args[0]);
		}
	}
}