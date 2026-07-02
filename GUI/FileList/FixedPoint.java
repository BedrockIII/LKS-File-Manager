package GUI.FileList;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.filechooser.FileNameExtensionFilter;

import GUI.GUI;
import GUI.FileInfo.FileInfoFactory;
import GUI.FileInfo.FixedPointObjectInfoGUI;
import WorldFileManager.fpInterpreter;
import bFM.OpenedFile;
import bFM.Settings;
import bFM.Utils;
import WorldFileManager.FixedPointObject;

@SuppressWarnings("serial")
public class FixedPoint extends CollapseableFileList
{
	int padding = 0;
	ArrayList<FixedPointObject> objects = new ArrayList<FixedPointObject>();
	public FixedPoint(OpenedFile file, int padding) 
	{
		this.file = file;
		this.padding = padding;
		initializeAll(padding);
	}
	protected void initializeAll(int padding)
	{
		fileTypes = new FileNameExtensionFilter("LKS Fixed Placement File", "fp", "vfp", "sfp", "lfp", "plfp");
		initializeListGUI(padding);
		initializeInfoGUI();
		fileName.setText(file.getName());
		initializeSubGUI();
		addActions();
		reAddComponents();
	} 
	protected void addActions()
	{
		addRenameAction();
		addReplaceButton();
		addReplaceAsBFPButton();
		addExportAction();
		addExportBFPAction();
		addMouseListener();
		add(actions);
	}
	protected void addReplaceAsBFPButton()
	{
		actions.add(Utils.createImportAction("Replace From BFP", "Bedrock's Intermediate FP Text File", "bfp", ((fpInterpreter)file)::replaceFromBFP, this));
	}
	private void addExportBFPAction() 
	{
		actions.add(Utils.createExportAction("Export As BFP", file.getName().substring(0, file.getName().lastIndexOf('.')) + ".bfp", "Bedrock's Intermediate FP Text File", ((fpInterpreter)file)::toBFPBytes));
	}
	public void initializeSubGUI() 
	{
		subEntries.removeAll(subEntries);
		objects = ((fpInterpreter)file).getObjects();
		for(FixedPointObject object : objects)
		{
			System.out.println(object.getName());
			subEntries.add(new FixedPointObjectListGUI(object, padding + Settings.indentSize));
		}
	}
	public void removeFile(FileList file) 
	{
		remove(file);
		objects.remove(((FixedPointObjectListGUI)file).getObject());
		subEntries.remove(file);
	}
	private class FixedPointObjectListGUI extends FileList
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
			this.initializeInfoGUI();
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
			initializeListGUI(padding, object.getName());
		}
		protected void initializeInfoGUI() 
		{
			this.infoGUI = new FixedPointObjectInfoGUI(object);
		}
	}
	protected void initializeInfoGUI() 
	{
		infoGUI = FileInfoFactory.makeInfoGUI(file);
	}
}
