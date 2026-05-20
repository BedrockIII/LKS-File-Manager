package GUI.FileList;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JPanel;
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
			addActions();
			add(actions);
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
