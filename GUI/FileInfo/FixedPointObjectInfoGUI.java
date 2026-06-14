package GUI.FileInfo;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import GUI.LabeledInputBox;
import WorldFileManager.FixedPointObject;

@SuppressWarnings("serial")
public class FixedPointObjectInfoGUI extends GenericFileInfoGUI
{
	FixedPointObject object = null;
	LabeledInputBox refIndex = null;
	LabeledInputBox xOffset = null;
	LabeledInputBox yOffset = null;
	LabeledInputBox zOffset = null;
	JTextField xOffsetText = null;
	JTextField yOffsetText = null;
	JTextField zOffsetText = null;
	LabeledInputBox xAxisRotation = null;
	LabeledInputBox yAxisRotation = null;
	LabeledInputBox zAxisRotation = null;
	JTextField xRotationText = null;
	JTextField yRotationText = null;
	JTextField zRotationText = null;
	LabeledInputBox xScale = null;
	LabeledInputBox yScale = null;
	LabeledInputBox zScale = null;
	JTextField xScaleText = null;
	JTextField yScaleText = null;
	JTextField zScaleText = null;
	
	public FixedPointObjectInfoGUI(FixedPointObject object) 
	{
		this.object = object;
		makeGUI();
		addGUI();
	}
	private void makeGUI()
	{
		refIndex = new LabeledInputBox("Reference Index: ",  new JLabel("" + object.getReferenceIndex()), 1.5);
		
		xOffsetText = new JTextField("" + object.getXPos());
		xOffsetText.getDocument().addDocumentListener(new DocumentListener() 
		{
			public void insertUpdate(DocumentEvent e) 
			{
				object.setXPos(bFM.Utils.strToFloat(xOffsetText.getText()));
			}
			public void removeUpdate(DocumentEvent e) 
			{
				object.setXPos(bFM.Utils.strToFloat(xOffsetText.getText()));
			}
			public void changedUpdate(DocumentEvent e) {}
		});
		
		yOffsetText = new JTextField("" + object.getYPos());
		yOffsetText.getDocument().addDocumentListener(new DocumentListener() 
		{
			public void insertUpdate(DocumentEvent e) 
			{
				object.setYPos(bFM.Utils.strToFloat(yOffsetText.getText()));
			}
			public void removeUpdate(DocumentEvent e) 
			{
				object.setYPos(bFM.Utils.strToFloat(yOffsetText.getText()));
			}
			public void changedUpdate(DocumentEvent e) {}
		});
		
		zOffsetText = new JTextField("" + object.getZPos());
		zOffsetText.getDocument().addDocumentListener(new DocumentListener() 
		{
			public void insertUpdate(DocumentEvent e) 
			{
				object.setZPos(bFM.Utils.strToFloat(zOffsetText.getText()));
			}
			public void removeUpdate(DocumentEvent e) 
			{
				object.setZPos(bFM.Utils.strToFloat(zOffsetText.getText()));
			}
			public void changedUpdate(DocumentEvent e) {}
		});
		
		xRotationText = new JTextField("" + object.getXRot());
		xRotationText.getDocument().addDocumentListener(new DocumentListener() 
		{
			public void insertUpdate(DocumentEvent e) 
			{
				object.setXRotation(bFM.Utils.strToFloat(xRotationText.getText()));
			}
			public void removeUpdate(DocumentEvent e) 
			{
				object.setXRotation(bFM.Utils.strToFloat(xRotationText.getText()));
			}
			public void changedUpdate(DocumentEvent e) {}
		});
		
		yRotationText = new JTextField("" + object.getYRot());
		yRotationText.getDocument().addDocumentListener(new DocumentListener() 
		{
			public void insertUpdate(DocumentEvent e) 
			{
				object.setYRotation(bFM.Utils.strToFloat(yRotationText.getText()));
			}
			public void removeUpdate(DocumentEvent e) 
			{
				object.setYRotation(bFM.Utils.strToFloat(yRotationText.getText()));
			}
			public void changedUpdate(DocumentEvent e) {}
		});
		
		zRotationText = new JTextField("" + object.getZRot());
		zRotationText.getDocument().addDocumentListener(new DocumentListener() 
		{
			public void insertUpdate(DocumentEvent e) 
			{
				object.setZRotation(bFM.Utils.strToFloat(zRotationText.getText()));
			}
			public void removeUpdate(DocumentEvent e) 
			{
				object.setZRotation(bFM.Utils.strToFloat(zRotationText.getText()));
			}
			public void changedUpdate(DocumentEvent e) {}
		});
		
		xScaleText = new JTextField("" + object.getXScale());
		xScaleText.getDocument().addDocumentListener(new DocumentListener() 
		{
			public void insertUpdate(DocumentEvent e) 
			{
				object.setXScale(bFM.Utils.strToFloat(xScaleText.getText()));
			}
			public void removeUpdate(DocumentEvent e) 
			{
				object.setXScale(bFM.Utils.strToFloat(xScaleText.getText()));
			}
			public void changedUpdate(DocumentEvent e) {}
		});
		
		yScaleText = new JTextField("" + object.getYScale());
		yScaleText.getDocument().addDocumentListener(new DocumentListener() 
		{
			public void insertUpdate(DocumentEvent e) 
			{
				object.setYScale(bFM.Utils.strToFloat(yScaleText.getText()));
			}
			public void removeUpdate(DocumentEvent e) 
			{
				object.setYScale(bFM.Utils.strToFloat(yScaleText.getText()));
			}
			public void changedUpdate(DocumentEvent e) {}
		});
		
		zScaleText = new JTextField("" + object.getZScale());
		zScaleText.getDocument().addDocumentListener(new DocumentListener() 
		{
			public void insertUpdate(DocumentEvent e) 
			{
				object.setZScale(bFM.Utils.strToFloat(zScaleText.getText()));
			}
			public void removeUpdate(DocumentEvent e) 
			{
				object.setZScale(bFM.Utils.strToFloat(zScaleText.getText()));
			}
			public void changedUpdate(DocumentEvent e) {}
		});
		
		xOffset = new LabeledInputBox("X Position: ",  xOffsetText, 1.5);
		yOffset = new LabeledInputBox("Y Position: ",  yOffsetText, 1.5);
		zOffset = new LabeledInputBox("Z Position: ",  zOffsetText, 1.5);
		xAxisRotation = new LabeledInputBox("X Rotation: ",  xRotationText, 1.5);
		yAxisRotation = new LabeledInputBox("Y Rotation: ",  yRotationText, 1.5);
		zAxisRotation = new LabeledInputBox("Z Rotation: ",  zRotationText, 1.5);
		xScale = new LabeledInputBox("X Scale: ",  xScaleText, 1.5);
		yScale = new LabeledInputBox("Y Scale: ",  yScaleText, 1.5);
		zScale = new LabeledInputBox("Z Scale: ",  zScaleText, 1.5);
	}
	private void addGUI()
	{
		removeAll();
		setLayout(new GridBagLayout());
		GridBagConstraints layout = new GridBagConstraints();
		layout.anchor = GridBagConstraints.NORTHWEST;
		layout.gridwidth = GridBagConstraints.REMAINDER;
		layout.weighty = 0.0;
		layout.weightx = 1.0;
		add(refIndex, layout);
		add(xOffset, layout);
		add(yOffset, layout);
		add(zOffset, layout);
		add(xAxisRotation, layout);
		add(yAxisRotation, layout);
		add(zAxisRotation, layout);
		add(xScale, layout);
		add(yScale, layout);
		layout.weighty = 1.0;
		layout.weightx = 1.0;
		
		add(zScale, layout);
	}
	public void update() 
	{
		refIndex.replaceComponent(new JLabel("" + object.getReferenceIndex()));
		xOffsetText.setText("" + object.getXPos());
		yOffsetText.setText("" + object.getYPos());
		zOffsetText.setText("" + object.getZPos());
		xRotationText.setText("" + object.getXRot());
		yRotationText.setText("" + object.getYRot());
		zRotationText.setText("" + object.getZRot());
		xScaleText.setText("" + object.getXScale());
		yScaleText.setText("" + object.getYScale());
		zScaleText.setText("" + object.getZScale());
		repaint();
	}
}
