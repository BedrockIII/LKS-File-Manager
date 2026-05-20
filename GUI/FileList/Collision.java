package GUI.FileList;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

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
		System.out.println(file);
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
	protected void addActions()
	{
		addMouseListener(new MouseAdapter() {
		    public void mousePressed(MouseEvent e) {
		    	System.out.println("aaa");
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
	public void deselect()
	{
		headerPanel.setBackground(GUI.bgColor);
	}
	private class ColObjectListGUI extends Generic
	{
		colObject object = null;
		public ColObjectListGUI(colObject object, int padding) 
		{
			this.object = object;
			this.initializeGUI(padding);
			addActions();
		}
		protected void initializeGUI(int padding) 
		{
			//setBorder(BorderFactory.createLineBorder(Color.GREEN));
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
			add(spacer, constraints);
			constraints.weightx = 1.0;
			fileName = new JLabel(object.getName(), SwingConstants.LEFT);
			fileName.setPreferredSize(new Dimension(GUI.rowWidth-padding, GUI.assetHeight));
			add(fileName, constraints);
		}
	}
}
