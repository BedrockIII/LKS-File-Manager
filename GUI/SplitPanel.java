package GUI;

import java.awt.Component;
import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JSplitPane;
import javax.swing.SwingUtilities;
import javax.swing.border.BevelBorder;

import bFM.Settings;

@SuppressWarnings("serial")
public class SplitPanel extends JSplitPane
{
	JComponent left;
	JComponent right;
	int height = 0;
	double xWeight = .5;
	public SplitPanel()
	{
		height = (int) (Settings.assetHeight*1.5);
		left = new JLabel("");
		right = new JLabel("");
		addGUI();
		update();
		setTheme();
	}
	public SplitPanel(JComponent left, JComponent right)
	{
		this.left = left;
		this.right = right;
		height = (int) (Settings.assetHeight*1.5);
		addGUI();
		update();
		setTheme();
	}
	public SplitPanel(JComponent left, JComponent right, double xWeight)
	{
		this.left = left;
		this.right = right;
		this.xWeight = xWeight;
		height = (int) (Settings.assetHeight*1.5);
		addGUI();
		update();
		setTheme();
	}
	protected void setTheme()
	{
		setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED, Settings.LighterColor, Settings.DarkerColor));
	}
	protected void addGUI()
	{
		setBorder(null);
		setContinuousLayout(true);
		if(left != null) left.setMinimumSize(new Dimension(Settings.buttonWidth, height));
		if(right != null) right.setMinimumSize(new Dimension(Settings.buttonWidth, height));
		
		setDividerSize(0);
		setDividerLocation(xWeight);
		setResizeWeight(xWeight);
		
		super.setLeftComponent(left);
		super.setRightComponent(right);
	}
	public void update()
	{
		SwingUtilities.invokeLater(() -> 
		{
			setResizeWeight(xWeight);
			setDividerLocation(xWeight);
			if(left instanceof SplitPanel)
			{
				((SplitPanel) left).update();
			}
			else if(left instanceof CollapseablePanel)
			{
				((CollapseablePanel) left).update();
			}
			else if(left instanceof BitFlagPanel)
			{
				((BitFlagPanel) left).update();
			}
			if(right instanceof SplitPanel)
			{
				((SplitPanel) right).update();
			}
			else if(right instanceof CollapseablePanel)
			{
				((CollapseablePanel) right).update();
			}
			else if(right instanceof BitFlagPanel)
			{
				((BitFlagPanel) right).update();
			}
		});
		revalidate();
		repaint();
	}
	public void setRightComponent(JComponent right) 
	{
		this.right = right;
		super.setRightComponent(right);
		update();
		setTheme();
	}
	public void setLeftComponent(JComponent left) 
	{
		this.left = left;
		super.setLeftComponent(left);
		update();
		setTheme();
	}
}
