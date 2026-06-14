package GUI;

import java.nio.file.Paths;

import javax.swing.JFileChooser;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.filechooser.FileNameExtensionFilter;

@SuppressWarnings("serial")
public class FileDropDownBox extends JMenu
{
	//JPanel openedFileList = null;
	public FileDropDownBox()
	{
		super("File");
		setPreferredSize(GUI.buttonSize);
		setMinimumSize(GUI.buttonSize);
		JMenuItem openButton = new JMenuItem("Open File");
		openButton.addActionListener(e -> {
			JFileChooser chooseFile = new JFileChooser();
			chooseFile.setFileFilter(new FileNameExtensionFilter("Package File", "pac", "pcha", "bin", "pac0"));
			chooseFile.addChoosableFileFilter(new FileNameExtensionFilter("Collision File", "col"));
			if(GUI.lastFileOpenPath != null) 
			{
				chooseFile.setSelectedFile(Paths.get(GUI.lastFileOpenPath).toFile());
			}
			if(chooseFile.showOpenDialog(null)==JFileChooser.APPROVE_OPTION)
			{
				Main.MainGUI.fileManager.setOpenFile(chooseFile.getSelectedFile().toString());
			}
		});
		add(openButton);
	}
		
}
