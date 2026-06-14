package GUI.FileList;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
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
import GUI.FileInfo.FileInfoFactory;
import GUI.FileInfo.FixedPointObjectInfoGUI;
import WorldFileManager.fpInterpreter;
import bFM.OpenedFile;
import WorldFileManager.FixedPointObject;

@SuppressWarnings("serial")
public class FixedPoint extends CollapseableFileList
{
	ArrayList<FixedPointObject> objects = new ArrayList<FixedPointObject>();
	public FixedPoint(OpenedFile file, int padding) 
	{
		this.file = file;
		initializeAll(padding);
	}
	protected void initializeAll(int padding)
	{
		fileTypes = new FileNameExtensionFilter("LKS Fixed Placement File", "fp", "vfp", "sfp", "lfp", "plfp");
		initializeListGUI(padding);
		fileName.setText(file.getName());
		initializeSubGUI(padding);
		addActions();
		reAddComponents();
	} 
	protected void addActions()
	{
		addExportAction();
		addReplaceButton();
		addExportBFPAction();
		add(actions);
	}
	private void addExportBFPAction() 
	{
		JMenuItem export = new JMenuItem("Export As BFP");
		export.addActionListener(e -> {
			JFileChooser chooseFile = new JFileChooser();
			if(GUI.lastFileSavePath != null) 
			{
				chooseFile.setCurrentDirectory(Paths.get(GUI.lastFileSavePath).toFile().getParentFile());
			}
			chooseFile.setSelectedFile(new File(file.getName().substring(0, file.getName().lastIndexOf('.')) + ".bfp"));
			if(chooseFile.showSaveDialog(null)==JFileChooser.APPROVE_OPTION)
			{
				try 
				{
					Files.write(chooseFile.getSelectedFile().toPath(),((fpInterpreter)file).toBFP().getBytes());
					GUI.lastFileSavePath = chooseFile.getSelectedFile().toString();
				}
				catch(IOException i)
				{
					System.out.println("Failed to Export Fixed Point File as BFP");
					i.printStackTrace();
				}
				System.out.println("Exported Fixed Point File as BFP");
			}
		});
		actions.add(export);
	}
	private void initializeSubGUI(int padding) 
	{
		objects = ((fpInterpreter)file).getObjects();
		for(FixedPointObject object : objects)
		{
			System.out.println(object.getName());
			subEntries.add(new FixedPointObjectListGUI(object, padding + GUI.indentSize));
		}
	}
	public void removeFile(Generic file) 
	{
		remove(file);
		objects.remove(((FixedPointObjectListGUI)file).getObject());
		subEntries.remove(file);
	}
	private class FixedPointObjectListGUI extends Generic
	{
		FixedPointObject object;
		public FixedPointObjectListGUI(FixedPointObject object, int padding) 
		{
			this.object = object;
			initializeAll(padding);
		}
		protected void initializeAll(int padding)
		{
			this.initializeListGUI(padding);
			addActions();
		}
		protected void addActions()
		{
			if(object.getReferenceIndex()>0)
			{
				this.addRenameAction();
				this.addDeleteAction();
				//this.addExportBFPAction();
			}
			this.addMouseListener();
			this.add(actions);
			this.update();
		}
		public Object getObject() 
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
			//setBorder(BorderFactory.createLineBorder(Color.GREEN));
			setBackground(GUI.bgColor);
			GridBagConstraints constraints = new GridBagConstraints();  
			constraints.weightx = 0.0;
			constraints.anchor = GridBagConstraints.NORTHWEST;
			infoGUI = new FixedPointObjectInfoGUI(object);
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
	}
	protected void initializeInfoGUI() 
	{
		infoGUI = FileInfoFactory.makeInfoGUI(file);
	}
}
