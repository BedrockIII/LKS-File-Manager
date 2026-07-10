package GUI.FileList;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.filechooser.FileNameExtensionFilter;

import GUI.GUI;
import GUI.FileInfo.CollisionObjectInfoGUI;
import GUI.FileInfo.FileInfoFactory;
import bFM.OpenedFile;
import bFM.Settings;
import bFM.Utils;
import colReader.ColReader;
import colReader.CollisionObject;

@SuppressWarnings("serial")
public class Collision extends CollapseableFileList
{
	private int padding = 0;
	ArrayList<CollisionObject> objects = new ArrayList<CollisionObject>();
	public Collision(OpenedFile file, int padding) 
	{
		this.file = file;
		initializeAll(padding);
	}
	protected void initializeAll(int padding)
	{
		this.padding = padding;
		fileTypes = new FileNameExtensionFilter("LKS Collision File", "col");
		initializeListGUI(padding);
		
		addActions();
		reAddComponents();
	}
	protected void initializeListGUI(int padding)
	{
		initializeSubGUI();
		super.initializeListGUI(padding);
		initializeInfoGUI();
	}
	private void addExportOBJAction() 
 	{
		actions.add(Utils.createExportAction("Export as OBJ", file.getName().substring(0, file.getName().lastIndexOf('.')) + ".obj", "Collison File as OBJ", ((ColReader)file)::toOBJ));
	}
	protected void addActions()
	{
		addReplaceButton();
		replaceAsOBJAction();
		addExportAction();
		addExportOBJAction();
		
		addMouseListener();
		add(actions);
	}
	public void initializeSubGUI() 
	{
		objects = ((ColReader)file).getObjects();
		for(CollisionObject object : objects)
		{
			if(object != null) subEntries.add(new ColObjectListGUI(object, padding + Settings.indentSize));
		}
	}
	protected void replaceAsOBJAction()
	{
		actions.add(Utils.createImportAction("Replace File (From .obj)", "Wavefront OBJ File", "obj", ((ColReader)file)::replaceFromOBJ, this));
	}
	public void removeFile(FileList file) 
	{
		remove(file);
		objects.remove(((ColObjectListGUI)file).getObject());
		subEntries.remove(file);
	}
	public class ColObjectListGUI extends FileList
	{
		CollisionObject object = null;
		public ColObjectListGUI(CollisionObject object, int padding) 
		{
			file = object;
			this.object = object;
			this.initializeAll(padding);
		}
		protected void initializeAll(int padding)
		{
			this.initializeListGUI(padding);
			this.initializeInfoGUI();
			addActions();
		}
		private void addExportOBJAction() 
	 	{
			actions.add(Utils.createExportAction("Export as OBJ", object.getName() + ".obj", "Collison File as OBJ", ((CollisionObject)object)::toOBJBytes));
		}
		protected void addActions()
		{
			if(object.getReferenceIndex()>0)
			{
				this.addRenameAction();
				this.addDeleteAction();
				this.addExportOBJAction();
			}
			this.addMouseListener();
			this.add(actions);
			this.update();
		}
		private CollisionObject getObject()
		{
			return object;
		}
		protected void addRenameAction()
		{
			JMenuItem rename = new JMenuItem("Rename");
			rename.addActionListener(e -> 
			{
				JDialog renameWindow = new JDialog();
				
				renameWindow.setVisible(true);  
				renameWindow.setSize(200, 100);  
				renameWindow.setPreferredSize(new Dimension(200, 100));  
				renameWindow.setVisible(true);  
				renameWindow.setTitle("Rename File");  
		        JPanel contentPanel = new JPanel();  
		        contentPanel.setLayout(new BorderLayout());  
		        renameWindow.getContentPane().add(contentPanel);  
				
				contentPanel.setLayout(new GridBagLayout());
				GridBagConstraints layout = new GridBagConstraints();
				layout.weightx = 1.0;
				layout.weighty = 1.0;
				
		        JLabel labelOptions = new JLabel("Rename File:");  
		        labelOptions.setPreferredSize(new Dimension(75, 20));  
		        contentPanel.add(labelOptions, layout);  
		        final JTextField newTitle = new JTextField(object.getName()); 
		        newTitle.setEditable(true);
		        newTitle.setPreferredSize(new Dimension(100, 20));  
		        
		        layout.gridwidth =GridBagConstraints.REMAINDER;
		        
		        contentPanel.add(newTitle, layout);
		        
		        layout.gridwidth =2;
		        
		        JButton Cancel = new JButton();
		        Cancel.setText("Cancel");
		        Cancel.addActionListener(g -> 
		        {
		        	renameWindow.dispose();
		        });
		        contentPanel.add(Cancel, layout);
		        
		        layout.gridwidth =GridBagConstraints.REMAINDER;
		        
		        JButton Confirm = new JButton();
		        Confirm.setText("Confirm");
		        Confirm.addActionListener(g -> 
		        {
		        	setName(newTitle.getText());
		        	renameWindow.dispose();
		        });
		        contentPanel.add(Confirm, layout);
			});
			actions.add(rename);
		}
		public void setName(String name)
		{
			object.setName(name);
			fileName.setText(name);
			GUI.update();
		}
		protected void initializeListGUI(int padding) 
		{
			initializeListGUI(padding, object.getName());
		}
		protected void initializeInfoGUI() 
		{
			this.infoGUI = new CollisionObjectInfoGUI(object);
		}
		protected void replaceAsOBJAction()
		{
			JMenuItem replace = new JMenuItem("Replace File (from obj)");
			replace.addActionListener(e -> {
				JFileChooser chooseFile = new JFileChooser();
				chooseFile.setFileSelectionMode(JFileChooser.FILES_ONLY);
				fileTypes = new FileNameExtensionFilter("Wavefront OBJ File", "obj");
				if(getFileExtensions()!=null) chooseFile.setFileFilter(getFileExtensions());
				
				int num =chooseFile.showOpenDialog(null);
				if(num==JFileChooser.APPROVE_OPTION)
				{
					try 
					{
						object.replaceFromOBJ(Files.readAllLines(chooseFile.getSelectedFile().toPath()), object.getReferenceIndex(), 0, object.getIndexSize());
						//System.out.println(data.length);
						object.setName(chooseFile.getSelectedFile().toPath().getFileName().toString());
						fileName.setText(object.getName());
						GUI.update();
					} catch (IOException i) 
					{
						i.printStackTrace();
						System.out.println("Failed to Import OBJ File");
					}
					System.out.println("Imported OBJ File");
				}
			});
			actions.add(replace);
		}
	}
	protected void initializeInfoGUI() 
	{
		infoGUI = FileInfoFactory.makeInfoGUI(file);
	}
}
