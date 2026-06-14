package GUI;

import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

@SuppressWarnings("serial")
public class ToolDropDownBox extends JMenu
{
	//JPanel openedFileList = null;
	public ToolDropDownBox()
	{
		super("Tools");
		setPreferredSize(GUI.buttonSize);
		setMinimumSize(GUI.buttonSize);
		JMenuItem ModDirectory = new JMenuItem("Create Extracted Mod Directory");
		ModDirectory.addActionListener(e -> {
			JFileChooser chooseFile = new JFileChooser();
			chooseFile.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
			File path = new File(colReader.Main.importPath);
			chooseFile.setCurrentDirectory(path);
			if(chooseFile.showDialog(null, "Choose Modding Directory")==JFileChooser.APPROVE_OPTION)
			{
				File newFile = chooseFile.getSelectedFile();
				colReader.Main.createRiivolutionDirectory(newFile.getParent(), newFile.getName());
				colReader.Main.importPath = newFile.toString() + "\\";
			}
		});
		add(ModDirectory);
		
		
		JMenuItem RiivolutionDirectory = new JMenuItem("Create Riivolution Mod Directory");
		RiivolutionDirectory.addActionListener(e -> {
			JFileChooser chooseFile = new JFileChooser();
			chooseFile.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
			File path = new File(colReader.Main.outputPath);
			chooseFile.setCurrentDirectory(path);
			if(chooseFile.showDialog(null, "Choose Riivolution Mod Directory")==JFileChooser.APPROVE_OPTION)
			{
				File newFile = chooseFile.getSelectedFile();
				colReader.Main.createRiivolutionDirectory(newFile.getParent(), newFile.getName());
				colReader.Main.outputPath = newFile.toString() + "\\";
			}
		});
		add(RiivolutionDirectory);
	}
		
}

