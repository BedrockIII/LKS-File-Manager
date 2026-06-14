package GUI.FileList;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import GUI.GUI;

@SuppressWarnings("serial")
public abstract class CollapseableFileList extends FileList
{
	protected ArrayList<FileList> subEntries = new ArrayList<FileList>();
	JCheckBox isExtended = null;
	JPanel headerPanel = new JPanel();
	protected void initializeListGUI(int padding)
	{
		initializeListGUI(padding, file.getName());
	}
	protected void initializeListGUI(int padding, String name) 
	{
		//setBorder(BorderFactory.createLineBorder(Color.GREEN));
		setLayout(new GridBagLayout());
		GridBagConstraints layout = new GridBagConstraints();
		layout = new GridBagConstraints();
	    layout.weightx = 0.0;
	    layout.anchor = GridBagConstraints.NORTHWEST;
	    
	    
		setPreferredSize(new Dimension(GUI.rowWidth, getHeight()));
		setLayout(new GridBagLayout());
		headerPanel.setLayout(new GridBagLayout());
		JPanel spacer = new JPanel();
		//spacer.setBorder(BorderFactory.createLineBorder(Color.RED));
		spacer.setPreferredSize(new Dimension(padding, GUI.assetHeight));
		spacer.setMinimumSize(new Dimension(padding, GUI.assetHeight));
		spacer.setBackground(null);
		headerPanel.add(spacer, layout);
		
		
		
		isExtended = new JCheckBox();
		isExtended.setMargin(new Insets(0,0,0,0));
		isExtended.setBorderPainted(false);
		isExtended.setContentAreaFilled(false);
		isExtended.setBackground(GUI.bgColor);
		isExtended.setSelected(false);
		isExtended.setPreferredSize(new Dimension(15, GUI.assetHeight));
		try {
			//ImageIcon grown = );
			//Image scalar = grown.getImage();
			isExtended.setSelectedIcon(new ImageIcon(ClassLoader.getSystemResourceAsStream("Grown.png").readAllBytes()));
			//System.out.println(grown.getIconHeight());
			isExtended.setIcon(new ImageIcon(ClassLoader.getSystemResourceAsStream("Shrunk.png").readAllBytes()));
			
		} catch (IOException e) 
		{
			System.out.println("Failed to locate +/- Images");
		} catch (NullPointerException e)
		{
			isExtended.setSelectedIcon(new ImageIcon("Grown.png"));
			isExtended.setIcon(new ImageIcon("Shrunk.png"));
		}
		
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
		fileName.setPreferredSize(new Dimension(GUI.rowWidth-padding-GUI.assetHeight, GUI.assetHeight));
		//fileName.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		fileName.setBackground(GUI.bgColor);
		headerPanel.add(fileName, layout);
	}
	public int getHeight()
	{
		int increment = GUI.assetHeight;
		if(isExtended!=null && isExtended.isSelected() == false) return increment;
		int ret = increment;
		if(subEntries == null) return ret;
		for(FileList entry : subEntries)
		{
			ret += entry.getHeight();
		}
		return ret;
	}
	protected void select()
	{
		GUI.deselectAll();
    	headerPanel.setBackground(GUI.selectedColor);
    	GUI.setFileInfo(infoGUI);
	}
	public void deselect()
	{
		headerPanel.setBackground(GUI.bgColor);
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
		setSize(new Dimension(GUI.rowWidth, getHeight()));
		setPreferredSize(new Dimension(GUI.rowWidth, getHeight()));
		setMinimumSize(new Dimension(GUI.rowWidth, getHeight()));
		if(infoGUI != null)infoGUI.update();
		else System.out.println(("Collapseable File List: " + fileName.getText() + " lacks an info GUI"));
	}
	protected void reAddComponents()
	{
		removeAll();
		GridBagConstraints layout = new GridBagConstraints();
		layout.gridwidth = GridBagConstraints.REMAINDER;
		layout.weighty = 0.0;
		layout.weightx = 1.0;
		layout.anchor = GridBagConstraints.NORTHWEST;
		if(isExtended!=null && !isExtended.isSelected()) 
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
				add(subEntries.get(j), layout);
			}
		}
		setSize(new Dimension(GUI.rowWidth, getHeight()));
		setPreferredSize(new Dimension(GUI.rowWidth, getHeight()));
		setMinimumSize(new Dimension(GUI.rowWidth, getHeight()));
		update();
	}
	protected void addCollapseAllAction()
	{
		JMenuItem collapseAll = new JMenuItem("Collapse All");
		collapseAll.addActionListener(e -> {
			collapseAll();
		});
		actions.add(collapseAll);
	}
	protected void addExpandAllAction()
	{
		JMenuItem expandAll = new JMenuItem("Collapse All");
		expandAll.addActionListener(e -> {
			expandAll();
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
		reAddComponents();
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
		reAddComponents();
	}
	public void removeFile(FileList fileList) 
	{
		remove(fileList);
		subEntries.remove(fileList);
	}
	public void setExpanded(boolean val) 
	{
		isExtended.setSelected(val);
		reAddComponents();
	}
}
