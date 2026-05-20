package GUI.FileList;

import java.io.IOException;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

import javax.swing.JFileChooser;
import javax.swing.JMenuItem;
import javax.swing.filechooser.FileNameExtensionFilter;

import GUI.GUI;
import GUI.FileInfo.FileInfoFactory;
import PCKGManager.OpenedFile;
import PCKGManager.PCKGManager;

public class Package extends CollapseableGeneric
{
	private static final long serialVersionUID = 1L;
	public Package(PCKGManager packageFile, int padding)
	{
		this.file = packageFile;
		initializeAll(padding);
	}
	public Package(PCKGManager packageFile)
	{
		this.file = packageFile;
		initializeAll();
	}
	private void initializeAll(int padding)
	{
		fileTypes = new FileNameExtensionFilter("Package File", "pac", "pcha", "bin", "pac0");
		initializeGUI(padding);
		addRenameAction();
		addExportAction();
		addFileButton(padding);
		addExportAllButton();
		addActions();
		initializeSubGUI();
		add(actions);
		isExtended.setSelected(true);
	}
	public Package() 
	{
		file = new PCKGManager("New Package");
	}
	private void initializeSubGUI()
	{
		infoGUI = FileInfoFactory.makeInfoGUI(file);
		subEntries = new ArrayList<Generic>();		
		for(int i =0; i<((PCKGManager)file).getFileAmount(); i++)
		{
			subEntries.add(FileListFactory.makeListGUI(((PCKGManager)file).getPackedFile(i), GUI.indentSize));
		}
	}
	private void addFileButton(int padding)
	{ 
		JMenuItem addFile = new JMenuItem("Add New File");
		addFile.addActionListener(e -> {
			JFileChooser chooseFile = new JFileChooser();
			try 
			{
				chooseFile.setFileSelectionMode(JFileChooser.FILES_ONLY);
				chooseFile.showOpenDialog(null);
				byte[] data = Files.readAllBytes(chooseFile.getSelectedFile().toPath());
				String name = chooseFile.getSelectedFile().getName().toString();
				boolean newFile = !((PCKGManager)file).hasFile(name);
				((PCKGManager)file).addFile(name, data);
				if(newFile)subEntries.add(FileListFactory.makeListGUI(((PCKGManager)file).getPackedFile(((PCKGManager)file).getFileAmount()-1), padding + GUI.indentSize));
				infoGUI.update();
				GUI.update();
			} catch (IOException i) 
			{
				i.printStackTrace();
			}
		});
		actions.add(addFile);
	}
	private void addExportAllButton()
	{
		JMenuItem exportAll = new JMenuItem("Export All Files");
		exportAll.addActionListener(e -> {
			JFileChooser chooseFile = new JFileChooser();
			chooseFile.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
			int num = chooseFile.showSaveDialog(null);
			if(num==JFileChooser.APPROVE_OPTION)
			{
				Path directory = Paths.get(chooseFile.getSelectedFile().toPath().toString()+ "\\" + file.getName());
				try 
				{
					Files.createDirectories(directory);
				}
				catch(FileAlreadyExistsException e1)
				{
					System.out.println("Failed to create directory at: " + directory.toString() + " File Already Exists!!!");
					System.out.println("Attempting to create directory without file extension");
					directory = Paths.get(chooseFile.getSelectedFile().toPath().toString()+ "\\" + file.getName().substring(0, file.getName().lastIndexOf('.')));
					try 
					{
						Files.createDirectories(directory);
						System.out.println("Sucess!");
					}
					catch (IOException e2) 
					{
						System.out.println("Failed to create directory at: " + directory.toString());
						e2.printStackTrace();
						return;
					}
				} 
				catch (IOException e1) 
				{
					System.out.println("Failed to create directory at: " + directory.toString());
					e1.printStackTrace();
					return;
				}
				for(OpenedFile file : ((PCKGManager)file).getFiles())
				{
					try 
					{
						Files.write(Paths.get(directory.toString() + "\\" + file.getName()), file.getData());
					} catch (IOException e1) 
					{
						System.out.println("Failed to write file at: " + directory.toString() + "\\" + file.getName());
						e1.printStackTrace();
					}
				}
			}
		});
		actions.add(exportAll);
	}
}
