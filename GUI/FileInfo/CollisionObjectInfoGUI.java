package GUI.FileInfo;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JLabel;
import javax.swing.JTextField;
import GUI.LabeledInputBox;
import bFM.Settings;
import colReader.CollisionObject;

@SuppressWarnings("serial")
public class CollisionObjectInfoGUI extends GenericFileInfoGUI 
{
	CollisionObject object = null;
	JLabel faceCount = null;
	JLabel vertexCount = null;
	JTextField xOffsetText = null;
	JTextField yOffsetText = null;
	JTextField zOffsetText = null;
	
	public CollisionObjectInfoGUI(CollisionObject object) 
	{
		this.object = object;
		makeGUI();
		addGUI();
	}
	private void makeGUI()
	{
		faceCount = new JLabel("" + object.getFaceAmount());
		vertexCount = new JLabel("" + object.getVertexAmount());
		xOffsetText = bFM.Utils.createFloatTextField(object.getXOffset(), object::setXOffset);
		yOffsetText = bFM.Utils.createFloatTextField(object.getYOffset(), object::setYOffset);
		zOffsetText = bFM.Utils.createFloatTextField(object.getZOffset(), object::setZOffset);
	}
	private void addGUI()
	{
		removeAll();
		setLayout(new GridBagLayout());
		GridBagConstraints layout = Settings.getDefaultConstraints();
		
		add(new LabeledInputBox("Face Count: ",  faceCount), layout);
		add(new LabeledInputBox("Vertex Count: ",  vertexCount), layout);
		add(new LabeledInputBox("X Offset: ",  xOffsetText), layout);
		add(new LabeledInputBox("Y Offset: ",  yOffsetText), layout);
		layout.weighty = 1.0;
		add(new LabeledInputBox("Z Offset: ",  zOffsetText), layout);
		
	}
}