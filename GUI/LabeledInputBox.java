package GUI;

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
	protected void setTheme()
	{
		if(text != null) text.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED, Settings.LighterColor, Settings.DarkerColor));
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
