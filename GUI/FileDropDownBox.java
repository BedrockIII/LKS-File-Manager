package GUI;

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
			int num = chooseFile.showOpenDialog(null);
			if(num==JFileChooser.APPROVE_OPTION)
			{
				Main.MainGUI.fileManager.setOpenFile(chooseFile.getSelectedFile().toString());
			}
		});
		add(openButton);
		GUI.update();
	}
		
}
