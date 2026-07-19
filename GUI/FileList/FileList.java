package GUI.FileList;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.nio.file.Files;
import javax.swing.Box;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

import GUI.GUI;
import GUI.FileInfo.GenericFileInfoGUI;
import GUI.PopupWindows.RenameWindow;
import bFM.Data;
import bFM.GUIUtils;
import bFM.Settings;

@SuppressWarnings("serial")
public abstract class FileList extends JPanel
{
	protected Data file = null;
	protected GenericFileInfoGUI infoGUI = null;
	protected JLabel fileName = new JLabel();
	protected JPopupMenu actions = new JPopupMenu();
	protected FileNameExtensionFilter fileTypes = null;
	protected void initializeAll()
	{
		initializeAll(0);
	}
	protected abstract void initializeAll(int padding);
	protected void initializeListGUI(int padding, String name) 
	{
		setLayout(new GridBagLayout());
		GridBagConstraints layout = new GridBagConstraints();
		layout = new GridBagConstraints();
	    layout.weightx = 0.0;
	    layout.anchor = GridBagConstraints.NORTHWEST;
	    
		//setBorder(BorderFactory.createLineBorder(Color.GREEN));
	    
		
		setPreferredSize(new Dimension(Settings.rowWidth, getHeight()));
		//setBounds(40+parentX,GUI.assetHeight+parentY,GUI.rowWidth,GUI.assetHeight);
		setLayout(new GridBagLayout());
		//setMaximumSize(new Dimension(100000,GUI.assetHeight));
		
		add(Box.createHorizontalStrut(padding + 15), layout);
		
		layout.weightx = 1.0;
		layout.weighty = 1.0;
		fileName = new JLabel(name, SwingConstants.LEFT);
		fileName.setPreferredSize(new Dimension(Settings.rowWidth-padding, Settings.assetHeight));
		fileName.setBorder(new EmptyBorder(0, 3, 0, 3));
		add(fileName, layout);
	}
	protected abstract void initializeInfoGUI();
	protected abstract void addActions();
	protected void addMouseListener()
	{
		setFocusable(false);
		addMouseListener(new MouseAdapter() {
		    public void mousePressed(MouseEvent e) 
		    {
		    	//System.out.println("aaa");
		        if (e.isPopupTrigger()) showMenu(e);
		        else if(SwingUtilities.isLeftMouseButton(e))
		        {
		        	select();
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
	protected void select()
	{
		GUI.deselectAll();
		fileName.setBackground(Settings.selectedColor);
		fileName.setOpaque(true);
    	GUI.setFileInfo(infoGUI);
	}
	public int getHeight()
	{
		return Settings.assetHeight;
	}
	public byte[] getBytes() 
	{
		return file.toBytes();
	}
	public void deselect()
	{
		setBackground(Settings.bgColor);
		fileName.setBackground(Settings.bgColor);
		fileName.setOpaque(false);
	}
	public void update() 
	{
		repaint();
		if(infoGUI!=null)infoGUI.update();
	}
	public void deselectAll() 
	{
		deselect();
	}
	public Data getFile()
	{
		return file;
	}
	public FileNameExtensionFilter getFileExtensions()
	{
		return fileTypes;
	}
	protected void addRenameAction()
	{
		JMenuItem rename = new JMenuItem("Rename");
		rename.addActionListener(e -> 
		{
			new RenameWindow(this);
		});
		actions.add(rename);
	}
	protected void addExportAction()
	{
		actions.add(GUIUtils.createExportAction("Export Raw Data", file.getName(), "Raw File", file::toBytes));
	}
}
