package GUI.FileList;

import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;

import javax.swing.JFileChooser;
import javax.swing.JMenuItem;
import GUI.GUI;
import GUI.FileInfo.FileInfoFactory;
import PCKGManager.PCKGManager;

public class Package extends CollapseableGeneric
{
	private static final long serialVersionUID = 1L;
	public Package(PCKGManager packageFile, int padding)
	{
		this.file = packageFile;
	    //System.out.println("Package depth: " + ((padding/5)+1));
	    initializeGUI(padding);
		addRenameAction();
		addExportAction();
		addFileButton(padding);
		addExportAllButton();
		addActions();
		add(actions);
		initializeSubGUI();
		isExtended.setSelected(true);
		update();
		
	}
	public Package(PCKGManager packageFile)
	{
		this.file = packageFile;
		
		
		initializeGUI(0);
		addRenameAction();
		addExportAction();
		addFileButton(0);
		addExportAllButton();
		addActions();
		add(actions);
		initializeSubGUI();
		update();
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
				//updatePacContents();
				((PCKGManager)file).extractAll(chooseFile.getSelectedFile().toPath().toString()+ "/");
				System.out.println("Exported all files");
			}
		});
		actions.add(exportAll);
	}
}
