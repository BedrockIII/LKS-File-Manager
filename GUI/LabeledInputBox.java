package GUI;

import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.border.BevelBorder;

import bFM.Settings;

@SuppressWarnings("serial")
public class LabeledInputBox extends SplitPanel
{
	JComponent comp;
	JLabel text;
	protected LabeledInputBox(String labelText)
	{
		text = new JLabel(labelText);
		comp = new JLabel("");
		addGUI();
		update();
		setTheme();
	}
	public LabeledInputBox(String labelText, JComponent comp)
	{
		this.comp = comp;
		text = new JLabel(labelText);
		text.setMinimumSize(new Dimension(text.getFontMetrics(text.getFont()).stringWidth(labelText), Settings.assetHeight + 3 ));
		text.setPreferredSize(new Dimension(text.getFontMetrics(text.getFont()).stringWidth(labelText), Settings.assetHeight + 3));
		addGUI();
		update();
		setTheme();
	}
	public LabeledInputBox(String labelText, JComponent comp, double xWeight) 
	{
		this.comp = comp;
		text = new JLabel(labelText);
		this.xWeight = xWeight;
		addGUI();
		update();
		setTheme();
	}
	public LabeledInputBox(String labelText, JComponent comp, double xWeight, double yWeight) 
	{
		this.comp = comp;
		text = new JLabel(labelText);
		this.xWeight = xWeight;
		this.yWeight = yWeight;
		addGUI();
		update();
		setTheme();
	}
	protected void setTheme()
	{
		if(text != null)
		{
			setPreferredSize(new Dimension((int) Math.max(text.getMinimumSize().width * 1/xWeight , comp.getPreferredSize().width * 1/(1-xWeight)), Settings.assetHeight));
			setMinimumSize(new Dimension((int) Math.max(text.getMinimumSize().width * 1/xWeight , comp.getPreferredSize().width * 1/(1-xWeight)), Settings.assetHeight));
			text.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED, Settings.LighterColor, Settings.DarkerColor));
		}
		super.setTheme();
		//comp.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED, Settings.LighterColor, Settings.DarkerColor));
	}
	protected void addGUI()
	{
		left = text;
		right = comp;
		super.addGUI();
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
