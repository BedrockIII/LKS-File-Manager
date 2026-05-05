package GUI;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import GUI.FileList.Package;
import PCKGManager.PCKGManager;

public class guiTester {

	public static void main(String[] args) 
	{
		new GUI();
		PCKGManager pac;
		try {
			pac = new PCKGManager(Files.readAllBytes(Paths.get("C:\\Users\\benow\\OneDrive\\Documents\\menuDB_6_1.pac")), "menuDB_6_1.pac");
			GUI.setFileList(new Package(pac));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//openedFileList.setFile(new Package(pac));
		
	}

}
