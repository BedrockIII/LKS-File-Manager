package Main;
import java.io.IOException;

import GUI.GUI;
import bFM.Settings;

public class MainGUI 
{
	public static GUI fileManager = null;
	public static void main(String args[]) throws IOException 
	{
		PCKGManagerMain.printVersionData();
		fileManager = new GUI();
		if(args.length > 0)
		{
			String BadPath = args[0];
			String GoodPath = "";
			for(int i = 0; i < BadPath.length(); i++)
			{
				if(i + 1 < BadPath.length() && BadPath.charAt(i) == '\\' && BadPath.charAt(i+1) == '\\')
				{
					GoodPath += '\\';
					i++;
				}
				else if(BadPath.charAt(i) == '\"')
				{}
				else
				{
					GoodPath += BadPath.charAt(i);
				}
			}
			while(GoodPath.charAt(GoodPath.length()-1)==' ')
			{
				GoodPath = GoodPath.substring(0, GoodPath.length()-1);
			}
			Settings.setSettingsFile(GoodPath);
		}
		if(args.length > 1)
		{
			String BadPath = args[1];
			String GoodPath = "";
			for(int i = 0; i < BadPath.length(); i++)
			{
				if(i + 1 < BadPath.length() && BadPath.charAt(i) == '\\' && BadPath.charAt(i+1) == '\\')
				{
					GoodPath += '\\';
					i++;
				}
				else if(BadPath.charAt(i) == '\"')
				{}
				else
				{
					GoodPath += BadPath.charAt(i);
				}
			}
			fileManager.setOpenFile(GoodPath);
		}
	}
}