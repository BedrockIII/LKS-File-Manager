package GUI.FileInfo;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import GUI.LabeledInputBox;
import colReader.CollisionObject;

@SuppressWarnings("serial")
public class CollisionObjectInfoGUI extends GenericFileInfoGUI 
{
	CollisionObject object = null;
	LabeledInputBox FaceCount = null;
	LabeledInputBox VertexCount = null;
	LabeledInputBox xOffset = null;
	LabeledInputBox yOffset = null;
	LabeledInputBox zOffset = null;
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
		FaceCount = new LabeledInputBox("Face Count: ",  new JLabel("" + object.getFaceAmount()), 1.5);
		VertexCount = new LabeledInputBox("Vertex Count: ",  new JLabel("" + object.getVertexAmount()), 1.5);
		
		xOffsetText = new JTextField("" + object.getXOffset());
		xOffsetText.getDocument().addDocumentListener(new DocumentListener() 
		{
			public void insertUpdate(DocumentEvent e) 
			{
				object.setXOffset(bFM.Utils.strToFloat(xOffsetText.getText()));
			}
			public void removeUpdate(DocumentEvent e) 
			{
				object.setXOffset(bFM.Utils.strToFloat(xOffsetText.getText()));
			}
			public void changedUpdate(DocumentEvent e) {}
		});
		
		yOffsetText = new JTextField("" + object.getYOffset());
		yOffsetText.getDocument().addDocumentListener(new DocumentListener() 
		{
			public void insertUpdate(DocumentEvent e) 
			{
				object.setYOffset(bFM.Utils.strToFloat(yOffsetText.getText()));
			}
			public void removeUpdate(DocumentEvent e) 
			{
				object.setYOffset(bFM.Utils.strToFloat(yOffsetText.getText()));
			}
			public void changedUpdate(DocumentEvent e) {}
		});
		
		zOffsetText = new JTextField("" + object.getZOffset());
		zOffsetText.getDocument().addDocumentListener(new DocumentListener() 
		{
			public void insertUpdate(DocumentEvent e) 
			{
				object.setZOffset(bFM.Utils.strToFloat(zOffsetText.getText()));
			}
			public void removeUpdate(DocumentEvent e) 
			{
				object.setZOffset(bFM.Utils.strToFloat(zOffsetText.getText()));
			}
			public void changedUpdate(DocumentEvent e) {}
		});
		
		xOffset = new LabeledInputBox("X Offset: ",  xOffsetText, 1.5);
		yOffset = new LabeledInputBox("Y Offset: ",  yOffsetText, 1.5);
		zOffset = new LabeledInputBox("Z Offset: ",  zOffsetText, 1.5);
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
		xOffsetText.setText("" + object.getXOffset());
		yOffsetText.setText("" + object.getYOffset());
		zOffsetText.setText("" + object.getZOffset());
		repaint();
	}
}