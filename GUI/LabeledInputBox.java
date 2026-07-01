package GUI;

import java.awt.Component;
import java.awt.Dimension;
import javax.swing.JLabel;
import javax.swing.JSplitPane;
import javax.swing.SwingUtilities;

import bFM.Settings;

@SuppressWarnings("serial")
public class LabeledInputBox extends JSplitPane
{
	Component comp;
	JLabel text;
	int height = 0;
	double xWeight = .5;
	public LabeledInputBox(String labelText, Component comp)
	{
		this.comp = comp;
		height = (int) (Settings.assetHeight*1.5);
		text = new JLabel(labelText);
		addGUI();
		update();
	}
	public LabeledInputBox(String labelText, Component comp, double xWeight) 
	{
		this.comp = comp;
		height = (int) (Settings.assetHeight*1.5);
		text = new JLabel(labelText);
		this.xWeight = xWeight;
		addGUI();
		update();
	}
	private void addGUI()
	{
		setBorder(null);
		setContinuousLayout(true);
		text.setMinimumSize(new Dimension(Settings.buttonWidth, height));
		comp.setMinimumSize(new Dimension(Settings.buttonWidth, height));
		
		setDividerSize(0);
		setDividerLocation(xWeight);
		setResizeWeight(xWeight);
		
		setLeftComponent(text);
		setRightComponent(comp);
	}
	public void update()
	{
		SwingUtilities.invokeLater(() -> 
		{
			setResizeWeight(xWeight);
			setDividerLocation(xWeight);
		});

		repaint();
	}
	public void replaceComponent(Component comp) 
	{
		this.comp = comp;
		update();
	}
	public void replaceText(String text) 
	{
		this.text.setText(text);
	}
}
