package GUI.FileList;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

import GUI.GUI;
import GUI.FileInfo.FileInfoFactory;
import PCKGManager.OpenedFile;
import PCKGManager.PCKGManager;

@SuppressWarnings("serial")
public class CollapseableGeneric extends Generic
{
	protected ArrayList<Generic> subEntries = new ArrayList<Generic>();
	JCheckBox isExtended = null;
	JPanel headerPanel = new JPanel();
	protected CollapseableGeneric()
	{
		
	}
	public CollapseableGeneric(PCKGManager pac, int parentX, int index)
	{
		file = pac.getPackedFile(index);
		initializeGUI(parentX);
		addExportAction();
		addReplaceButton();
		addActions();
		add(actions);
		update();
	}
	public CollapseableGeneric(String name, byte[] data, int padding)
	{
		file = OpenedFile.makeFile(name, data);
		initializeGUI(padding);
		addExportAction();
		addReplaceButton();
		addActions();
		add(actions);
		update();
	}
	public CollapseableGeneric(OpenedFile file, int padding)
	{
		this.file = file;
		initializeGUI(padding);
		addExportAction();
		addReplaceButton();
		addActions();
		add(actions);
		update();
	}
	public CollapseableGeneric(String name, int padding) 
	{
		file = OpenedFile.makeFile(name, new byte[0]);
		initializeGUI(padding);
		addExportAction();
		addReplaceButton();
		addActions();
		add(actions);
		update();
	}
	protected void initializeGUI(int padding) 
	{
		//setBorder(BorderFactory.createLineBorder(Color.GREEN));
		setLayout(new GridBagLayout());
		layout = new GridBagConstraints();
	    layout.weightx = 0.0;
	    layout.anchor = GridBagConstraints.NORTHWEST;
	    
	    
		infoGUI = FileInfoFactory.makeInfoGUI(file);
		setPreferredSize(new Dimension(GUI.rowWidth, getHeight()));
		setLayout(new GridBagLayout());
		headerPanel.setLayout(new GridBagLayout());
		JPanel spacer = new JPanel();
		//spacer.setBorder(BorderFactory.createLineBorder(Color.RED));
		spacer.setPreferredSize(new Dimension(padding, GUI.assetHeight));
		spacer.setMinimumSize(new Dimension(padding, GUI.assetHeight));
		spacer.setBackground(GUI.bgColor);
		headerPanel.add(spacer, layout);
		
		isExtended = new JCheckBox();
		isExtended.setSelected(false);
		isExtended.setPreferredSize(new Dimension(GUI.assetHeight, GUI.assetHeight));
		isExtended.addItemListener(new ItemListener() {
		    @Override
		    public void itemStateChanged(ItemEvent e) 
		    {
		    	GUI.update();
		    }
		});
		headerPanel.add(isExtended, layout);
		
		layout.weightx = 1.0;
		fileName = new JLabel(file.getName(), SwingConstants.LEFT);
		fileName.setPreferredSize(new Dimension(GUI.rowWidth-padding-GUI.assetHeight, GUI.assetHeight));
		//fileName.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		headerPanel.add(fileName, layout);
	}
	public int getHeight()
	{
		int increment = GUI.assetHeight;
		if(isExtended!=null && isExtended.isSelected() == false) return increment;
		int ret = increment;
		for(Generic entry : subEntries)
		{
			ret += entry.getHeight();
		}
		return ret;
	}
	public void deselect()
	{
		headerPanel.setBackground(GUI.bgColor);
	}
	public void deselectAll()
	{
		deselect();
		for(Generic object : subEntries)
		{
			object.deselectAll();
		}
	}
	public void update()
	{
		removeAll();
		layout.gridwidth = GridBagConstraints.REMAINDER;
		if(isExtended.isSelected()) updateFull();
		else updateHeader();
		setPreferredSize(new Dimension(GUI.rowWidth, getHeight()));
		setMinimumSize(new Dimension(GUI.rowWidth, getHeight()));
		if(infoGUI != null)infoGUI.update();
		else System.out.println(("CollapseableGeneric: " + fileName.getText() + " lacks an info GUI"));
	}
	protected void updateFull() 
	{
		removeAll();
		layout.weighty = 0.0;
		layout.weightx = 1.0;
		add(headerPanel, layout);
		for(int j = 0; j<subEntries.size();j++)
		{
			if(subEntries.get(j) instanceof CollapseableGeneric)
			{
				subEntries.get(j).update();
			}
			if(subEntries.size()-1==j)
			{
				layout.weighty = 1.0;
			}
			add(subEntries.get(j), layout);
		}
		setPreferredSize(new Dimension(GUI.rowWidth, getHeight()));
		setSize(new Dimension(GUI.rowWidth, getHeight()));
		//System.out.println(new Dimension(GUI.rowWidth, getHeight()));
	}
	protected void updateHeader() 
	{
		layout.weightx = 1.0;
		layout.weighty = 1.0;
		removeAll();
		add(headerPanel, layout);
		setPreferredSize(new Dimension(GUI.rowWidth, getHeight()));
		setSize(new Dimension(GUI.rowWidth, getHeight()));
	}
	protected void addActions()
	{
		addMouseListener(new MouseAdapter() {
		    public void mousePressed(MouseEvent e) {
		    	//System.out.println("aaa");
		        if (e.isPopupTrigger()) showMenu(e);
		        else if(SwingUtilities.isLeftMouseButton(e))
		        {
		        	GUI.deselectAll();
		        	headerPanel.setBackground(GUI.selectedColor);
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
}
