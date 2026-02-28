package GUI;

import java.io.IOException;
import java.nio.file.Files;

import javax.swing.JFileChooser;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.filechooser.FileNameExtensionFilter;

import PCKGManager.PCKGManager;

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
			chooseFile.setFileFilter(new FileNameExtensionFilter("Pac File", "pac", "pcha", "bin", "pac0"));
			int num = chooseFile.showOpenDialog(null);
			if(num==JFileChooser.APPROVE_OPTION)
			{
				try {
						
					PCKGManager pac = new PCKGManager(Files.readAllBytes(chooseFile.getSelectedFile().toPath()), chooseFile.getSelectedFile().getName());
					//openedFileList.setFile(new Package(pac));
					
					GUI.setFileList(new Package(pac));
				} catch (IOException error) 
				{
					System.out.println("Failed to read File");
					error.printStackTrace();
				}
			}
		});
		add(openButton);
		GUI.update();
	}
		
}
