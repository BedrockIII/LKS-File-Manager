package GUI.FileInfo;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JLabel;

import GUI.LabeledInputBox;
import colReader.colObject;

public class CollisionObjectInfoGUI extends GenericFileInfoGUI 
{
	colObject object = null;
	LabeledInputBox FaceCount = null;
	LabeledInputBox VertexCount = null;
	LabeledInputBox xOffset = null;
	LabeledInputBox yOffset = null;
	LabeledInputBox zOffset = null;
	
	public CollisionObjectInfoGUI(colObject object) 
	{
		this.object = object;
		makeGUI();
		addGUI();
	}
	private void makeGUI()
	{
		FaceCount = new LabeledInputBox("Face Count: ",  new JLabel("" + object.getFaceAmount()), 1.5);
		VertexCount = new LabeledInputBox("Vertex Count: ",  new JLabel("" + object.getVertexAmount()), 1.5);
		xOffset = new LabeledInputBox("X Offset: ",  new JLabel("" + object.getXOffset()), 1.5);
		yOffset = new LabeledInputBox("Y Offset: ",  new JLabel("" + object.getYOffset()), 1.5);
		zOffset = new LabeledInputBox("Z Offset: ",  new JLabel("" + object.getZOffset()), 1.5);
	}
	private void addGUI()
	{
		removeAll();
		setLayout(new GridBagLayout());
		GridBagConstraints layout = new GridBagConstraints();
		layout.anchor = GridBagConstraints.NORTHWEST;
		layout.gridwidth = GridBagConstraints.REMAINDER;
		layout.weighty = 0.0;
		layout.weightx = 0.0;
		add(FaceCount, layout);
		add(VertexCount, layout);
		add(xOffset, layout);
		add(yOffset, layout);
		layout.weighty = 1.0;
		layout.weightx = 1.0;
		add(zOffset, layout);
		
	}
	public void update() 
	{
		FaceCount.replaceComponent(new JLabel("" + object.getFaceAmount()));
		VertexCount.replaceComponent(new JLabel("" + object.getVertexAmount()));
		xOffset.replaceComponent(new JLabel("" + object.getXOffset()));
		yOffset.replaceComponent(new JLabel("" + object.getYOffset()));
		zOffset.replaceComponent(new JLabel("" + object.getZOffset()));
		repaint();
	}
}