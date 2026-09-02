package GUI.FileList;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import GUI.GUI;
import bFM.Settings;

@SuppressWarnings("serial")
public abstract class CollapseableFileList extends FileList
{
	protected ArrayList<FileList> subEntries = new ArrayList<FileList>();
	JCheckBox isExtended = null;
	JPanel headerPanel = new JPanel();
	String filter = "";
	protected void initializeListGUI(int padding)
	{
		initializeListGUI(padding, file.getName());
	}
	protected void initializeListGUI(int padding, String name) 
	{
		setLayout(new GridBagLayout());
		GridBagConstraints layout = new GridBagConstraints();
		layout = new GridBagConstraints();
	    layout.weightx = 0.0;
	    layout.anchor = GridBagConstraints.NORTHWEST;
	    
	    
		setPreferredSize(new Dimension(Settings.rowWidth, getHeight()));
		headerPanel.setLayout(new GridBagLayout());
		
		headerPanel.add(Box.createHorizontalStrut(padding), layout);
		
		
		
		isExtended = new JCheckBox();
		isExtended.setMargin(new Insets(0,0,0,0));
		isExtended.setBorderPainted(false);
		isExtended.setContentAreaFilled(false);
		isExtended.setBackground(Settings.bgColor);
		isExtended.setSelected(false);
		isExtended.setPreferredSize(new Dimension(15, Settings.assetHeight));
		try {
			//ImageIcon grown = );
			//Image scalar = grown.getImage();
			isExtended.setSelectedIcon(new ImageIcon(ClassLoader.getSystemResourceAsStream("Grown.png").readAllBytes()));
			isExtended.setDisabledIcon(new ImageIcon(ClassLoader.getSystemResourceAsStream("Empty.png").readAllBytes()));
			//System.out.println(grown.getIconHeight());
			isExtended.setIcon(new ImageIcon(ClassLoader.getSystemResourceAsStream("Shrunk.png").readAllBytes()));
			
		} catch (IOException e) 
		{
			System.out.println("Failed to locate +/- Images");
		} catch (NullPointerException e)
		{
			isExtended.setSelectedIcon(new ImageIcon("Grown.png"));
			isExtended.setDisabledIcon(new ImageIcon("Empty.png"));
			isExtended.setIcon(new ImageIcon("Shrunk.png"));
		}
		if(padding == 0) isExtended.setSelected(true);
		isExtended.addItemListener(new ItemListener() {
		    @Override
		    public void itemStateChanged(ItemEvent e) 
		    {
		    	reAddComponents();
		    	GUI.update();
		    }
		});
		headerPanel.add(isExtended, layout);
		
		layout.weightx = 1.0;
		fileName = new JLabel(name, SwingConstants.LEFT);
		fileName.setBorder(new EmptyBorder(0, 3, 0, 3));
		headerPanel.add(fileName, layout);
	}
	public int getHeight()
	{
		int increment = Settings.assetHeight;
		if(isExtended!=null && (!isExtended.isEnabled() || isExtended.isSelected() == false)) return increment;
		int ret = increment;
		if(subEntries == null) return ret;
		for(FileList entry : subEntries)
		{
			if(entry.filterFiles(filter))
			{
				ret += entry.getHeight();
			}
		}
		return ret;
	}
	public void deselectAll()
	{
		deselect();
		for(FileList object : subEntries)
		{
			object.deselectAll();
		}
	}
	public void update()
	{
		for(int j = 0; j<subEntries.size();j++)
		{
			if(subEntries.get(j) instanceof FileList)
			{
				subEntries.get(j).update();
			}
		}
		setSize(new Dimension(Settings.rowWidth, getHeight()));
		setPreferredSize(new Dimension(Settings.rowWidth, getHeight()));
		setMinimumSize(new Dimension(Settings.rowWidth, getHeight()));
		if(isExtended!=null)
		{
			if(subEntries.size() == 0) isExtended.setEnabled(false);
			else isExtended.setEnabled(true);
		}
		super.update();
	}
	public void reAddComponents()
	{
		removeAll();
		GridBagConstraints layout = new GridBagConstraints();
		layout.gridwidth = GridBagConstraints.REMAINDER;
		layout.weighty = 0.0;
		layout.weightx = 1.0;
		layout.anchor = GridBagConstraints.NORTHWEST;
		
		if(isExtended!=null)
		{
			if(subEntries.size() == 0) isExtended.setEnabled(false);
			else isExtended.setEnabled(true);
		}
		if(!isExtended.isEnabled() || isExtended!=null && !isExtended.isSelected()) 
		{
			//Update only header
			layout.weighty = 1.0;
			add(headerPanel, layout);
		}
		else
		{
			//Update Everything
			add(headerPanel, layout);
			for(int j = 0; j<subEntries.size();j++)
			{
				if(subEntries.size()-1==j)
				{
					layout.weighty = 1.0;
				}
				if(subEntries.get(j).filterFiles(filter))
				{
					add(subEntries.get(j), layout);
				}
			}
		}
		setSize(new Dimension(Settings.rowWidth, getHeight()));
		setPreferredSize(new Dimension(Settings.rowWidth, getHeight()));
		setMinimumSize(new Dimension(Settings.rowWidth, getHeight()));
		update();
	}
	protected void addCollapseAllAction()
	{
		JMenuItem collapseAll = new JMenuItem("Collapse All");
		collapseAll.addActionListener(e -> {
			collapseAll();
			reAddComponents();
		});
		actions.add(collapseAll);
	}
	protected void addExpandAllAction()
	{
		JMenuItem expandAll = new JMenuItem("Expand All");
		expandAll.addActionListener(e -> {
			expandAll();
			reAddComponents();
		});
		actions.add(expandAll);
	}
	protected void collapseAll()
	{
		for(FileList entry : subEntries)
		{
			if(entry instanceof CollapseableFileList)
			{
				((CollapseableFileList)entry).setExpanded(false);
				((CollapseableFileList)entry).collapseAll();
			}
		}
	}
	protected void expandAll()
	{
		for(FileList entry : subEntries)
		{
			if(entry instanceof CollapseableFileList)
			{
				((CollapseableFileList)entry).setExpanded(true);
				((CollapseableFileList)entry).expandAll();
			}
		}
	}
	public void removeFile(FileList fileList) 
	{
		remove(fileList);
		subEntries.remove(fileList);
		reAddComponents();
	}
	public void setExpanded(boolean val) 
	{
		isExtended.setSelected(val);
		reAddComponents();
	}
	public abstract void initializeSubGUI();
	protected boolean filterFiles(String filter)
	{
		this.filter = filter;
		reAddComponents();
		boolean ret = false;
		for(int j = 0; j<subEntries.size();j++)
		{
			ret = ret || subEntries.get(j).filterFiles(filter);
		}
		return ret || super.filterFiles(filter);
	}
}
