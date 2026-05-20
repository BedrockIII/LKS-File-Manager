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
import javax.swing.SwingConstants;
import javax.swing.filechooser.FileNameExtensionFilter;

import GUI.GUI;
import GUI.FileInfo.CollisionObjectInfoGUI;
import PCKGManager.OpenedFile;
import colReader.ColReader;
import colReader.colObject;

@SuppressWarnings("serial")
public class Collision extends CollapseableGeneric
{
	ArrayList<colObject> objects = new ArrayList<colObject>();
	public Collision(OpenedFile file, int padding) 
	{
		this.file = file;
		initializeAll(padding);
	}
	private void initializeAll(int padding)
	{
		fileTypes = new FileNameExtensionFilter("LKS Collision File", "col");
		initializeGUI(padding);
		fileName.setText(file.getName());
		initializeSubGUI(padding);
		addExportAction();
		addReplaceButton();
		addActions();
		headerPanel.add(actions);
		//isExtended.setSelected(true);
		update();
	}
	private void initializeSubGUI(int padding) 
	{
		objects = ((ColReader)file).getObjects();
		for(colObject object : objects)
		{
			System.out.println(object.getName());
			subEntries.add(new ColObjectListGUI(object, padding + GUI.indentSize));
		}
	}
	public void deselect()
	{
		headerPanel.setBackground(GUI.bgColor);
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
					((ColReader)file).importOBJ(chooseFile.getSelectedFile().toPath());
					
					//System.out.println(data.length);
					file.setName(chooseFile.getSelectedFile().toPath().getFileName().toString());
					fileName.setText(file.getName());
					objects = ((ColReader)file).getObjects();
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
	private class ColObjectListGUI extends Generic
	{
		colObject object = null;
		public ColObjectListGUI(colObject object, int padding) 
		{
			this.object = object;
			this.initializeAll(padding);
		}
		private void initializeAll(int padding)
		{
			this.initializeGUI(padding);
			//replaceAsOBJAction();
			if(object.getReferenceIndex()>0)
			{
				this.addRenameAction();
			}
			addActions();
			add(actions);
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
		protected void initializeGUI(int padding) 
		{
			//setBorder(BorderFactory.createLineBorder(Color.GREEN));
			setBackground(GUI.bgColor);
			GridBagConstraints constraints = new GridBagConstraints();  
			constraints.weightx = 0.0;
			constraints.anchor = GridBagConstraints.NORTHWEST;
			infoGUI = new CollisionObjectInfoGUI(object);
			setPreferredSize(new Dimension(GUI.rowWidth, getHeight()));
			//setBounds(40+parentX,GUI.assetHeight+parentY,GUI.rowWidth,GUI.assetHeight);
			setLayout(new GridBagLayout());
			//setMaximumSize(new Dimension(100000,GUI.assetHeight));
			JPanel spacer = new JPanel();
			spacer.setPreferredSize(new Dimension(padding, GUI.assetHeight));
			spacer.setMinimumSize(new Dimension(padding, GUI.assetHeight));
			spacer.setBackground(GUI.bgColor);
			add(spacer, constraints);
			constraints.weightx = 1.0;
			fileName = new JLabel(object.getName(), SwingConstants.LEFT);
			fileName.setPreferredSize(new Dimension(GUI.rowWidth-padding, GUI.assetHeight));
			fileName.setBackground(GUI.bgColor);
			add(fileName, constraints);
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
}
