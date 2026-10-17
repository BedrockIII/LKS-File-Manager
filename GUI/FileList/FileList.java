package GUI.FileList;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.Box;
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
import GUI.FileInfo.NullFileInfoGUI;
import bFM.Data;
import bFM.GUIUtils;
import bFM.Nameable;
import bFM.Settings;

@SuppressWarnings("serial")
public abstract class FileList extends JPanel
{
	protected JMenuItem moveUp = null;
	protected JMenuItem moveDown = null;
	protected int padding;
	protected Data file = null;
	protected GenericFileInfoGUI infoGUI = null;
	protected JLabel fileName = new JLabel();
	protected JPopupMenu actions = new JPopupMenu();
	protected FileNameExtensionFilter fileTypes = null;
	protected static GenericFileInfoGUI nullGUI = null;
	protected abstract void initializeAll();
	protected void initializeListGUI(String name) 
	{
		setLayout(new GridBagLayout());
		GridBagConstraints layout = new GridBagConstraints();
		layout = new GridBagConstraints();
	    layout.weightx = 0.0;
	    layout.anchor = GridBagConstraints.NORTHWEST;
		setPreferredSize(new Dimension(Settings.rowWidth, getHeight()));
		setLayout(new GridBagLayout());
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
		if(infoGUI == null)
		{
			initializeInfoGUI();
		}
		if(infoGUI == null)
		{
			nullGUI = new NullFileInfoGUI();
			GUI.setFileInfo(nullGUI);
			return;
		}
    	GUI.setFileInfo(infoGUI);
	}
	public int getHeight()
	{
		return Settings.assetHeight;
	}
	public byte[] getBytes() 
	{
		if(file == null)
		{
			System.err.println("Warning, File \"" + fileName.getText() + "\" is null!");
			return new byte[0];
		}
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
		if(infoGUI != null)infoGUI.update();
		//else System.out.println((this.getClass() + " " + fileName.getText() + " lacks an info GUI"));
		revalidate();
		repaint();
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
	protected void addExportAction()
	{
		if(!(file instanceof Nameable)) return;
		actions.add(GUIUtils.createExportAction("Export Raw Data", ((Nameable) file).getName(), "Raw File", file::toBytes));
	}
	protected boolean filterFiles(String filter)
	{
		if(filter.length()<=0) return true;
		String name = getSearchName().toLowerCase();
		boolean ret = name.indexOf(filter.toLowerCase())!=-1;
		return ret;
	}
	public String toString()
	{
		return fileName.getText();
	}
	public void filterAddFiles(String filter) 
	{
		filterFiles(filter);
	}
	public GenericFileInfoGUI getInfoGUI()
	{
		if(infoGUI == null)
		{
			initializeInfoGUI();
		}
		if(infoGUI == null)
		{
			nullGUI = new NullFileInfoGUI();
			return nullGUI;
		}
		return infoGUI;
	}
	public String getSearchName()
	{
		return fileName.getText();
	}
	protected <T> void addMoveUpAction(CollapseableFileList parent, List<T> children)
	{
		if(moveUp == null)
		{
			moveUp = new JMenuItem("Move Up");
			moveUp.addActionListener(e -> {
				parent.moveUp(this, children);
				addMoveDownAction(parent, children);
				addMoveUpAction(parent, children);
				GUI.update();
			});
		}
		if(!parent.isFirst(this, children))
		{
			actions.add(moveUp);
		}
		else actions.remove(moveUp);
	}
	protected <T> void addMoveDownAction(CollapseableFileList parent, List<T> children)
	{
		if(moveDown == null)
		{
			moveDown = new JMenuItem("Move Down");
			moveDown.addActionListener(e -> {
				parent.moveDown(this, children);
				addMoveDownAction(parent, children);
				addMoveUpAction(parent, children);
				GUI.update();
			});
		}
		if(!parent.isLast(this, children))
		{
			actions.add(moveDown);
		}
		else actions.remove(moveDown);
	}
	protected <T> void addDeleteAction(CollapseableFileList parent, List<T> children, String name)
	{
		JMenuItem Delete = new JMenuItem(name);
		Delete.addActionListener(e -> {
			parent.removeChild(this, children);
			GUI.update();
		});
		actions.add(Delete);
	}
	public int getPadding()
	{
		return padding;
	}
}
