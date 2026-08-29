package bFM;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.function.Consumer;
import java.util.function.Supplier;

import javax.swing.JCheckBox;
import javax.swing.JFileChooser;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.filechooser.FileNameExtensionFilter;

import GUI.GUI;
import GUI.FileList.CollapseableFileList;
import PCKGManager.PCKGManager;
import ResourceManagers.CharacterDatabaseManager.CharacterDataBaseManager;
import ResourceManagers.ItemDatabaseManager.itemDatabaseManager;
import WorldFileManager.fpInterpreter;
import colReader.ColReader;

public abstract class GUIUtils 
{
	public static JMenu createNewFileAction(Consumer<OpenedFile> function, int level)
	{
		//Level 1 = basic files, TODO: VMC
		//Level 2 = all files including DBs
		//Level 3 = all files and subfiles including parts of menu DB
		JMenu chooseFileType = new JMenu("Create New File");
		//Create and add options for all file types
		
		if(level >= 2)
		{
			JMenuItem newPack = new JMenuItem("Package File");
			newPack.addActionListener(e -> {
				OpenedFile newFile = new PCKGManager("New Package.pac");
				function.accept(newFile);
			});
			chooseFileType.add(newPack);
		}
		
		
		
		JMenuItem newGeneric = new JMenuItem("Raw Data");
		newGeneric.addActionListener(e -> {
			OpenedFile newFile = new GenericFile("New File.txt", new byte[0]);
			function.accept(newFile);
		});
		chooseFileType.add(newGeneric);
		
		JMenuItem newCol = new JMenuItem("Collision (.col)");
		newCol.addActionListener(e -> {
			OpenedFile newFile = new ColReader();
			function.accept(newFile);
		});
		chooseFileType.add(newCol);
		
		JMenu newFP = new JMenu("Fixed Placement (.*fp)");
		JMenuItem fp = new JMenuItem("Fixed Placement (.fp)");
		fp.addActionListener(e -> {
			OpenedFile newFile = new fpInterpreter("fp");
			function.accept(newFile);
		});
		newFP.add(fp);
		JMenuItem vfp = new JMenuItem("Visual Fixed Placement (.vfp)");
		vfp.addActionListener(e -> {
			OpenedFile newFile = new fpInterpreter("vfp");
			function.accept(newFile);
		});
		newFP.add(vfp);
		JMenuItem sfp = new JMenuItem("Sound Zone Fixed Placement (.sfp)");
		sfp.addActionListener(e -> {
			OpenedFile newFile = new fpInterpreter("sfp");
			function.accept(newFile);
		});
		newFP.add(sfp);
		JMenuItem lfp = new JMenuItem("Light Zone Fixed Placement (.lfp)");
		lfp.addActionListener(e -> {
			OpenedFile newFile = new fpInterpreter("lfp");
			function.accept(newFile);
		});
		newFP.add(lfp);
		JMenuItem plfp = new JMenuItem("??? Fixed Placement (.plfp)");
		plfp.addActionListener(e -> {
			OpenedFile newFile = new fpInterpreter("plfp");
			function.accept(newFile);
		});
		newFP.add(plfp);
		chooseFileType.add(newFP);
		if(level==1)
		{
			return chooseFileType;
		}
		if(level >= 2)
		{
			JMenuItem newChrDB = new JMenuItem("Character Database File");
			newChrDB.addActionListener(e -> {
				OpenedFile newFile = new CharacterDataBaseManager();
				function.accept(newFile);
			});
			chooseFileType.add(newChrDB);
			JMenuItem newItemDB = new JMenuItem("Item Database File");
			newItemDB.addActionListener(e -> {
				OpenedFile newFile = new itemDatabaseManager();
				function.accept(newFile);
			});
			chooseFileType.add(newItemDB);
		}
		/*
		JMenuItem new = new JMenuItem(" (.)");
		new.addActionListener(e -> {
			OpenedFile newFile = new ;
			function.accept(newFile);
		});
		chooseFileType.add(new);
		 */
		return chooseFileType;
	}
	public static JMenuItem createImportLngAction(String name, String fileName, Consumer<byte[]> dataSource, CollapseableFileList gui)
	{
		JMenuItem importFile = new JMenuItem(name);
		importFile.addActionListener(e -> 
		{
			JFileChooser chooseFile = new JFileChooser();
			if(Settings.lastFileImportPath != null) 
			{
				chooseFile.setSelectedFile(Paths.get(Settings.lastFileImportPath).toFile());
				chooseFile.setSelectedFile(Paths.get("").toFile());
			}
			chooseFile.setFileSelectionMode(JFileChooser.FILES_ONLY);
			if(gui.getFileExtensions()!=null)chooseFile.setFileFilter(new FileNameExtensionFilter(fileName, "lng"));
			
			int num =chooseFile.showOpenDialog(null);
			if(num==JFileChooser.APPROVE_OPTION)
			{
				try 
				{
					dataSource.accept(Files.readAllBytes(chooseFile.getSelectedFile().toPath()));
					Settings.lastFileImportPath = chooseFile.getSelectedFile().toString();
					String Name = chooseFile.getSelectedFile().getName();
					Settings.LanguageCode = Utils.getLanguageCodeByName(Name);
					gui.initializeSubGUI();
					gui.reAddComponents();
					GUI.update();
				} catch (IOException i) 
				{
					i.printStackTrace();
					System.out.println("Failed to import Language Tranlation File");
				}
				System.out.println("Imported Language Tranlation File");
			}
		});
		return importFile;
	}

	public static JMenuItem createReplaceAction(String name, String fileName, String fileExtension, Consumer<byte[]> dataSource, Consumer<String> FileNameReplacer, CollapseableFileList gui)
	{
		JMenuItem replaceFile = new JMenuItem(name);
		replaceFile.addActionListener(e -> 
		{
			JFileChooser chooseFile = new JFileChooser();
			if(Settings.lastFileImportPath != null) 
			{
				chooseFile.setSelectedFile(Paths.get(Settings.lastFileImportPath).toFile());
				chooseFile.setSelectedFile(Paths.get("").toFile());
			}
			chooseFile.setFileSelectionMode(JFileChooser.FILES_ONLY);
			if(fileExtension!=null)chooseFile.setFileFilter(new FileNameExtensionFilter(fileName, fileExtension));
			
			int num =chooseFile.showOpenDialog(null);
			if(num==JFileChooser.APPROVE_OPTION)
			{
				try 
				{
					dataSource.accept(Files.readAllBytes(chooseFile.getSelectedFile().toPath()));
					FileNameReplacer.accept(chooseFile.getSelectedFile().getName());
					Settings.lastFileImportPath = chooseFile.getSelectedFile().toString();
					if(gui!= null)
					{
						gui.initializeSubGUI();
						gui.reAddComponents();
					}
					
					GUI.update();
				} catch (IOException i) 
				{
					i.printStackTrace();
					System.out.println("Failed to import" + fileName);
				}
				System.out.println("Imported " + fileName);
			}
		});
		return replaceFile;
	}

	public static JMenuItem createImportAction(String name, String fileName, String fileExtension, Consumer<byte[]> dataSource, CollapseableFileList gui)
	{
		JMenuItem importFile = new JMenuItem(name);
		importFile.addActionListener(e -> 
		{
			JFileChooser chooseFile = new JFileChooser();
			if(Settings.lastFileImportPath != null) 
			{
				chooseFile.setSelectedFile(Paths.get(Settings.lastFileImportPath).toFile());
				chooseFile.setSelectedFile(Paths.get("").toFile());
			}
			chooseFile.setFileSelectionMode(JFileChooser.FILES_ONLY);
			if(fileExtension!=null)chooseFile.setFileFilter(new FileNameExtensionFilter(fileName, fileExtension));
			
			int num =chooseFile.showOpenDialog(null);
			if(num==JFileChooser.APPROVE_OPTION)
			{
				try 
				{
					dataSource.accept(Files.readAllBytes(chooseFile.getSelectedFile().toPath()));
					Settings.lastFileImportPath = chooseFile.getSelectedFile().toString();
					if(gui!= null)
					{
						gui.initializeSubGUI();
						gui.reAddComponents();
					}
					
					GUI.update();
				} catch (IOException i) 
				{
					i.printStackTrace();
					System.out.println("Failed to import" + fileName);
				}
				System.out.println("Imported " + fileName);
			}
		});
		return importFile;
	}

	public static JMenuItem createExportAction(String name, String fileName, String fileType, Supplier<byte[]> dataSource)
	{
		JMenuItem export = new JMenuItem(name);
		export.addActionListener(e -> 
		{
			JFileChooser chooseFile = new JFileChooser();
			if(Settings.lastFileSavePath != null) 
			{
				chooseFile.setCurrentDirectory(Paths.get(Settings.lastFileSavePath).toFile().getParentFile());
			}
			chooseFile.setSelectedFile(new File(fileName));
			if(chooseFile.showDialog(null, "Save File")==JFileChooser.APPROVE_OPTION)
			{
				try 
				{
					Files.write(chooseFile.getSelectedFile().toPath(),dataSource.get());
					Settings.lastFileSavePath = chooseFile.getSelectedFile().toString();
				}
				catch(IOException i)
				{
					Utils.DebugPrint("Failed to Export " + fileType);
					i.printStackTrace();
				}
				Utils.DebugPrint("Exported " + fileType);
			}
		});
		return export;
	}

	public static JTextField createStringTextField(String value, Consumer<String> setterFunction)
	{
		JTextField field = new JTextField("" + value);
		
		field.getDocument().addDocumentListener(new DocumentListener()
		{
			public void insertUpdate(DocumentEvent e)
			{
				setterFunction.accept(field.getText());
				//GUI.update();
			}
			public void removeUpdate(DocumentEvent e)
			{
				setterFunction.accept(field.getText());
				//GUI.update();
			}
			public void changedUpdate(DocumentEvent e) {}
		});
		
		return field;
	}

	public static JTextField createFormattedTextField(String value, Consumer<String> setterFunction) 
	{
		JTextField field = new JTextField(Utils.toFormatedString(value));
		
		field.getDocument().addDocumentListener(new DocumentListener()
		{
			public void insertUpdate(DocumentEvent e)
			{
				setterFunction.accept(Utils.formatStringChars(field.getText()));
			}
			public void removeUpdate(DocumentEvent e)
			{
				setterFunction.accept(Utils.formatStringChars(field.getText()));
			}
			public void changedUpdate(DocumentEvent e) {}
		});
		
		return field;
	}

	public static JCheckBox createCheckBox(boolean value, Consumer<Boolean> setterFunction) 
	{
		JCheckBox checkBox = new JCheckBox();
		checkBox.setSelected(value);
		
		checkBox.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e) 
			{
				setterFunction.accept(checkBox.isSelected());
			}
			
		});
		
		return checkBox;
	}

	public static JTextField createNameTextField(String value, Consumer<String> setterFunction)
	{
		JTextField field = new JTextField("" + value);
		
		field.getDocument().addDocumentListener(new DocumentListener()
		{
			public void insertUpdate(DocumentEvent e)
			{
				setterFunction.accept(field.getText());
				GUI.update();
			}
			public void removeUpdate(DocumentEvent e)
			{
				setterFunction.accept(field.getText());
				GUI.update();
			}
			public void changedUpdate(DocumentEvent e) {}
		});
		
		return field;
	}

	public static JTextField createIntTextField(int value, Consumer<Integer> setterFunction)
	{
		JTextField field = new JTextField("" + value);
		
		field.getDocument().addDocumentListener(new DocumentListener()
		{
			public void insertUpdate(DocumentEvent e)
			{
				setterFunction.accept(Utils.strToInt(field.getText()));
			}
			public void removeUpdate(DocumentEvent e)
			{
				setterFunction.accept(Utils.strToInt(field.getText()));
			}
			public void changedUpdate(DocumentEvent e) {}
		});
		
		return field;
	}

	public static JTextField createFloatTextField(float value, Consumer<Float> setterFunction)
	{
		JTextField field = new JTextField("" + value);
		
		field.addFocusListener(new FocusAdapter() {
		    public void focusLost(FocusEvent e)
		    {
		    	try
				{
					setterFunction.accept(Utils.strToFloat(field.getText()));
				}
				catch(NumberFormatException d)
				{}
		    }
		});
		return field;
	}

}
