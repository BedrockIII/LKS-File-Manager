package GUI.FileList;

import java.io.IOException;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

import javax.swing.JFileChooser;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.filechooser.FileNameExtensionFilter;

import GUI.GUI;
import GUI.FileInfo.FileInfoFactory;
import PCKGManager.PCKGManager;
import bFM.GUIUtils;
import bFM.OpenedFile;
import bFM.Settings;

public class Package extends CollapseableFileList
{
	int padding = 0;
	private static final long serialVersionUID = 1L;
	PCKGManager packageFile;
	public Package(PCKGManager packageFile, int padding)
	{
		this.packageFile = packageFile;
		this.file = packageFile;
		initializeAll(padding);
	}
	public Package(PCKGManager packageFile)
	{
		this.packageFile = packageFile;
		this.file = packageFile;
		initializeAll();
	}
	protected void initializeAll(int padding)
	{
		this.padding = padding;
		fileTypes = new FileNameExtensionFilter("Package File", "pac", "pcha", "bin", "pac0");
		initializeListGUI(padding);
		addMouseListener();
		addActions();
		initializeSubGUI();
		reAddComponents();
	}
	public Package() 
	{
		file = new PCKGManager("New Package");
	}
	public void initializeSubGUI()
	{
		infoGUI = FileInfoFactory.makeInfoGUI(file);
		subEntries = new ArrayList<FileList>();		
		for(int i =0; i<((PCKGManager)file).getFileAmount(); i++)
		{
			subEntries.add(FileListFactory.makeListGUI(((PCKGManager)file).getPackedFile(i), Settings.indentSize + padding, this));
		}
	}
	protected void addActions()
	{
		addExportAction();
		addFileButton();
		addNewFileButton();
		addExportAllButton();
		add(actions);
	}
	private void addFileButton()
	{ 
		JMenuItem addFile = new JMenuItem("Add New File");
		addFile.addActionListener(e -> {
			JFileChooser chooseFile = new JFileChooser();
			if(Settings.lastFileImportPath != null) 
			{
				chooseFile.setSelectedFile(Paths.get(Settings.lastFileImportPath).toFile());
				chooseFile.setSelectedFile(Paths.get("").toFile());
			}
			chooseFile.setFileSelectionMode(JFileChooser.FILES_ONLY);
			
			int num =chooseFile.showOpenDialog(null);
			if(num==JFileChooser.APPROVE_OPTION)
			{
				try 
				{
					byte[] data = Files.readAllBytes(chooseFile.getSelectedFile().toPath());
					String name = chooseFile.getSelectedFile().getName().toString();
					boolean newFile = !((PCKGManager)file).hasFile(name);
					((PCKGManager)file).addFile(name, data);
					if(newFile)
					{
						subEntries.add(FileListFactory.makeListGUI(((PCKGManager)file).getPackedFile(((PCKGManager)file).getFileAmount()-1), padding + Settings.indentSize, this));
						reAddComponents();
					}
					infoGUI.update();
					GUI.update();
				} catch (IOException i) 
				{
					i.printStackTrace();
				}
			}
		});
		actions.add(addFile);
	}
	private void addNewFileButton()
	{ 
		JMenu chooseFileType = GUIUtils.createNewFileAction(this::addFile, 1);
		
		actions.add(chooseFileType);
	}
	private void addFile(OpenedFile newFile)
	{
		packageFile.addFile(newFile);
		subEntries.add(FileListFactory.makeListGUI(newFile, padding + Settings.indentSize, this));
		reAddComponents();
	}
	private void addExportAllButton()
	{
		JMenuItem exportAll = new JMenuItem("Export All Files");
		exportAll.addActionListener(e -> {
			JFileChooser chooseFile = new JFileChooser();
			chooseFile.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
			if(Settings.lastFileSavePath != null) 
			{
				chooseFile.setCurrentDirectory(Paths.get(Settings.lastFileSavePath).toFile().getParentFile());
			}
			if(chooseFile.showSaveDialog(null)==JFileChooser.APPROVE_OPTION)
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
				for(OpenedFile file : packageFile.getFiles())
				{
					try 
					{
						Files.write(Paths.get(directory.toString() + "\\" + file.getName()), file.toBytes());
						Settings.lastFileSavePath = chooseFile.getSelectedFile().toString();
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
	public void removeFile(FileList fileList) 
	{
		remove(fileList);
		subEntries.remove(fileList);
		packageFile.removeFile(fileList.getFile().getName());
		reAddComponents();
	}
	public void update()
	{
		fileName.setText(file.getName());
		super.update();
	}
	protected void initializeInfoGUI() 
	{
		infoGUI = FileInfoFactory.makeInfoGUI(file);
	}
}
