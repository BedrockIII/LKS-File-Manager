package GUI;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import javax.swing.JFileChooser;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.filechooser.FileNameExtensionFilter;

import bFM.Settings;

@SuppressWarnings("serial")
public class FileDropDownBox extends JMenu
{
	//JPanel openedFileList = null;
	public FileDropDownBox()
	{
		super("File");
		setPreferredSize(Settings.buttonSize);
		setMinimumSize(Settings.buttonSize);
		JMenuItem openButton = new JMenuItem("Open File");
		openButton.addActionListener(e -> {
			JFileChooser chooseFile = new JFileChooser();
			chooseFile.setFileFilter(new FileNameExtensionFilter("Package File", "pac", "pcha", "bin", "pac0"));
			chooseFile.addChoosableFileFilter(new FileNameExtensionFilter("Collision File", "col"));
			if(Settings.lastFileOpenPath != null) 
			{
				chooseFile.setSelectedFile(Paths.get(Settings.lastFileOpenPath).toFile());
			}
			if(chooseFile.showOpenDialog(null)==JFileChooser.APPROVE_OPTION)
			{
				Main.MainGUI.fileManager.setOpenFile(chooseFile.getSelectedFile().toString());
			}
		});
		add(openButton);
		
		
		
		JMenuItem saveButton = new JMenuItem("Save File");
		saveButton.addActionListener(e -> {
			try 
			{
				Files.write(Paths.get(Settings.lastFileOpenPath),GUI.getFile());
			}
			catch(IOException i)
			{
				System.out.println("Failed to Save Raw File");
				i.printStackTrace();
			}
			System.out.println("Saved Raw File");
		});
		add(saveButton);
	}
		
}
