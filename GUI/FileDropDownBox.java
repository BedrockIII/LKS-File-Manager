package GUI;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import javax.swing.JFileChooser;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.filechooser.FileNameExtensionFilter;

import bFM.GUIUtils;
import bFM.Settings;
import bFM.Utils;

@SuppressWarnings("serial")
public class FileDropDownBox extends JMenu
{
	GUI parent;
	public FileDropDownBox(GUI parent)
	{
		super("File");
		this.parent = parent;
		setPreferredSize(Settings.buttonSize);
		setMinimumSize(Settings.buttonSize);
		JMenuItem openButton = new JMenuItem("Open File");
		openButton.addActionListener(e -> {
			JFileChooser chooseFile = new JFileChooser();
			chooseFile.setFileFilter(new FileNameExtensionFilter("Package File", "pac", "pcha", "bin", "pac0", "dat"));
			chooseFile.addChoosableFileFilter(new FileNameExtensionFilter("Collision File", "col"));
			chooseFile.addChoosableFileFilter(new FileNameExtensionFilter("Fixed Placement File", "fp", "vfp", "sfp", "lfp"));
			if(Settings.lastFileOpenPath != null) 
			{
				chooseFile.setSelectedFile(Paths.get(Settings.lastFileOpenPath).toFile());
			}
			if(chooseFile.showOpenDialog(null)==JFileChooser.APPROVE_OPTION)
			{
				parent.setOpenFile(chooseFile.getSelectedFile().toString());
			}
		});
		add(openButton);
		
		add(GUIUtils.createNewFileAction(parent::setOpenFile, 2));

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
		
		JMenuItem debugChangesButton = new JMenuItem("(DEBUG) Print Changes");
		
		debugChangesButton.addActionListener(e -> {
			boolean noDiffs = false;
			try 
			{
				byte[] file = GUI.getFile();
				noDiffs = Utils.testDifferences(file, Files.readAllBytes(Paths.get(Settings.lastFileOpenPath)));
				Files.write(Paths.get(Settings.lastFileOpenPath+'0'),file);
			}
			catch(IOException i)
			{
				System.err.println("Failed to read starting file for test");
				i.printStackTrace();
			}
			System.out.println("Test Passed: " + noDiffs);
		});
		add(debugChangesButton);
	}
		
}
