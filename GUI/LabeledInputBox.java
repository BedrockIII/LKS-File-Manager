package GUI;

import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JSplitPane;
import javax.swing.SwingUtilities;
import javax.swing.border.BevelBorder;

import bFM.Settings;

@SuppressWarnings("serial")
public class LabeledInputBox extends JSplitPane
{
	JComponent comp;
	JLabel text;
	int height = 0;
	double xWeight = .5;
	protected LabeledInputBox() {}
	protected LabeledInputBox(String labelText)
	{
		height = (int) (Settings.assetHeight*1.5);
		text = new JLabel(labelText);
		setTheme();
	}
	public LabeledInputBox(String labelText, JComponent comp)
	{
		this.comp = comp;
		height = (int) (Settings.assetHeight*1.5);
		text = new JLabel(labelText);
		addGUI();
		update();
		setTheme();
	}
	public LabeledInputBox(String labelText, JComponent comp, double xWeight) 
	{
		this.comp = comp;
		height = (int) (Settings.assetHeight*1.5);
		text = new JLabel(labelText);
		this.xWeight = xWeight;
		addGUI();
		update();
		setTheme();
	}
	private void setTheme()
	{
		setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED, Settings.LighterColor, Settings.DarkerColor));
		text.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED, Settings.LighterColor, Settings.DarkerColor));
		//comp.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED, Settings.LighterColor, Settings.DarkerColor));
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
		revalidate();
		repaint();
	}
	public void replaceComponent(JComponent comp) 
	{
		this.comp = comp;
		setRightComponent(comp);
		update();
		setTheme();
	}
	public void replaceText(String text) 
	{
		this.text.setText(text);
	}
}
