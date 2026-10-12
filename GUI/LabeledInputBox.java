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
	int MaxWidth = -1;
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
			double weightedPreferredSizeText = text.getMinimumSize().width * 1/xWeight;
			double weightedPreferredSizeComponent = comp.getPreferredSize().width * 1/(1-xWeight);
			double preferredSize = Math.max(weightedPreferredSizeText, weightedPreferredSizeComponent);
			if(MaxWidth!=-1)
			{
				text.setMaximumSize(new Dimension((int)(MaxWidth*xWeight), Settings.assetHeight));
				setMaximumSize(new Dimension((int) Math.min(preferredSize, MaxWidth), Settings.assetHeight));
				setPreferredSize(new Dimension((int)  Math.min(preferredSize, MaxWidth), Settings.assetHeight));
				setMinimumSize(new Dimension((int) Math.min(preferredSize, MaxWidth), Settings.assetHeight));
			}
			else
			{
				setPreferredSize(new Dimension((int)preferredSize, Settings.assetHeight));
				setMinimumSize(new Dimension((int)preferredSize, Settings.assetHeight));
			}
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
	public void setWidth(int width)
	{
		MaxWidth = width;
	}
}
