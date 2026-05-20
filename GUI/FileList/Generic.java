package GUI.FileList;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.filechooser.FileNameExtensionFilter;

import GUI.GUI;
import GUI.FileInfo.FileInfoFactory;
import GUI.FileInfo.GenericFileInfoGUI;
import PCKGManager.OpenedFile;
import PCKGManager.PCKGManager;

@SuppressWarnings("serial")
public class Generic extends JPanel
{
	protected OpenedFile file = null;
	protected GenericFileInfoGUI infoGUI = null;
	JLabel fileName = new JLabel();
	JPopupMenu actions = new JPopupMenu();
	GridBagConstraints layout = new GridBagConstraints();
	static FileNameExtensionFilter fileTypes = null;
	protected Generic()
	{
	}
	public Generic(PCKGManager pac, int padding, int index)
	{
		file = pac.getPackedFile(index);
		initializeAll(padding);
	}
	public Generic(String name, byte[] data, int padding)
	{
		file = OpenedFile.makeFile(name, data);
		initializeAll(padding);
	}
	public Generic(OpenedFile file, int padding)
	{
		this.file = file;
		initializeAll(padding);
	}
	public Generic(String name, int padding) 
	{
		file = OpenedFile.makeFile(name, new byte[0]);
		initializeAll(padding);
	}
	protected void initializeAll()
	{
		initializeAll(0);
	}
	private void initializeAll(int padding)
	{
		initializeGUI(padding);
		addExportAction();
		addRenameAction();
		addReplaceButton();
		addActions();
		add(actions);
	}
	protected void initializeGUI(int padding) 
	{
		setLayout(new GridBagLayout());
		layout = new GridBagConstraints();
	    layout.weightx = 0.0;
	    layout.anchor = GridBagConstraints.NORTHWEST;
	    
		//setBorder(BorderFactory.createLineBorder(Color.GREEN));
	    
		infoGUI = FileInfoFactory.makeInfoGUI(file);
		setPreferredSize(new Dimension(GUI.rowWidth, getHeight()));
		//setBounds(40+parentX,GUI.assetHeight+parentY,GUI.rowWidth,GUI.assetHeight);
		setLayout(new GridBagLayout());
		//setMaximumSize(new Dimension(100000,GUI.assetHeight));
		JPanel spacer = new JPanel();
		spacer.setPreferredSize(new Dimension(padding, GUI.assetHeight));
		spacer.setMinimumSize(new Dimension(padding, GUI.assetHeight));
		spacer.setBackground(GUI.bgColor);
		add(spacer, layout);
		layout.weightx = 1.0;
		fileName = new JLabel(file.getName(), SwingConstants.LEFT);
		fileName.setPreferredSize(new Dimension(GUI.rowWidth-padding, GUI.assetHeight));
		fileName.setBackground(GUI.bgColor);
		add(fileName, layout);
	}
	public static FileNameExtensionFilter getFileExtensions()
	{
		return fileTypes;
	}
	protected void addExportAction()
	{
		JMenuItem export = new JMenuItem("Export Raw Data");
		export.addActionListener(e -> {
			JFileChooser chooseFile = new JFileChooser();
			chooseFile.setSelectedFile(new File(file.getName()));
			if(chooseFile.showSaveDialog(null)==JFileChooser.APPROVE_OPTION)
			{
				try 
				{
					Files.write(chooseFile.getSelectedFile().toPath(),file.getData());
				}
				catch(IOException i)
				{
					System.out.println("Failed to Export Raw File");
					i.printStackTrace();
				}
				System.out.println("Exported Raw File");
			}
		});
		actions.add(export);
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
	        final JTextField newTitle = new JTextField(file.getName()); 
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
	        	System.out.println(newTitle.getText());
	        	setName(newTitle.getText());
	        	renameWindow.dispose();
	        });
	        contentPanel.add(Confirm, layout);
		});
		actions.add(rename);
	}
	protected void addReplaceButton()
	{
		JMenuItem replace = new JMenuItem("Replace File");
		replace.addActionListener(e -> {
			JFileChooser chooseFile = new JFileChooser();
			chooseFile.setFileSelectionMode(JFileChooser.FILES_ONLY);
			if(getFileExtensions()!=null) chooseFile.setFileFilter(getFileExtensions());
			
			int num =chooseFile.showOpenDialog(null);
			if(num==JFileChooser.APPROVE_OPTION)
			{
				try 
				{
					file.setData(Files.readAllBytes(chooseFile.getSelectedFile().toPath()));
					//System.out.println(data.length);
					infoGUI.updateGUI(file.getData());
					file.setName(chooseFile.getSelectedFile().toPath().getFileName().toString());
					fileName.setText(file.getName());
					GUI.update();
				} catch (IOException i) 
				{
					i.printStackTrace();
					System.out.println("Failed to Import Generic File");
				}
				System.out.println("Imported Generic File");
			}
		});
		actions.add(replace);
	}
	protected void addActions()
	{
		addMouseListener(new MouseAdapter() {
		    public void mousePressed(MouseEvent e) 
		    {
		    	//System.out.println("aaa");
		        if (e.isPopupTrigger()) showMenu(e);
		        else if(SwingUtilities.isLeftMouseButton(e))
		        {
		        	GUI.deselectAll();
		        	setBackground(GUI.selectedColor);
		        	GUI.setFileInfo(infoGUI);
		        }
		    }
		    public void mouseReleased(MouseEvent e) {
		        if (e.isPopupTrigger()) showMenu(e);
		    }
		    private void showMenu(MouseEvent e) {
		    	actions.show(e.getComponent(), e.getX(), e.getY());
		    }
		});
	}
	public int getHeight()
	{
		return GUI.assetHeight;
	}
	public byte[] getBytes() 
	{
		System.out.println(file.getName());
		if(infoGUI==null) return new byte[0];
		return infoGUI.getBytes();
	}
	public void deselect()
	{
		setBackground(GUI.bgColor);
	}
	public void update() 
	{
		layout.gridwidth = GridBagConstraints.REMAINDER;
		repaint();
		infoGUI.update();
	}
	public void deselectAll() 
	{
		deselect();
	}
	public void setName(String name)
	{
		fileName.setText(name);
		file.setName(name);
		GUI.update();
	}
}
