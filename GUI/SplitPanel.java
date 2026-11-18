package GUI;

import java.awt.Dimension;
<<<<<<< Updated upstream
=======
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
>>>>>>> Stashed changes

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
	double yWeight = 1.0;
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
		height = (int) (Settings.assetHeight*yWeight);
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
	public SplitPanel(JComponent left, JComponent right, double xWeight, double yWeight)
	{
		this.left = left;
		this.right = right;
		this.xWeight = xWeight;
		this.yWeight = yWeight;
		height = (int) (Settings.assetHeight*yWeight);
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
<<<<<<< Updated upstream
		setBorder(null);
		setContinuousLayout(true);
		if(left != null) left.setMinimumSize(new Dimension(Settings.buttonWidth, height));
		if(right != null) right.setMinimumSize(new Dimension(Settings.buttonWidth, height));
		
		setDividerSize(0);
		setDividerLocation(xWeight);
		setResizeWeight(xWeight);
		
		super.setLeftComponent(left);
		super.setRightComponent(right);
=======
		removeAll();
		setLayout(new GridLayout(1, 2));
		/*
		GridBagConstraints layout = Settings.getDefaultConstraints();
		layout.gridwidth = GridBagConstraints.RELATIVE;
		layout.insets = new Insets(0,0,0,0);
		layout.weighty = 1;
		layout.weightx = xWeight;
		layout.fill = GridBagConstraints.HORIZONTAL;
		*/
		
		if(left != null)
		{
			left.setPreferredSize(new Dimension(Settings.buttonWidth,height));
			add(left);
		}
		else add(nullLeft);
		
		//layout.weightx = (1-xWeight);
		
		if(right != null)
		{
			right.setPreferredSize(new Dimension(Settings.buttonWidth,height));
			add(right);
		}
		else add(nullRight);
		
		revalidate();
		repaint();
>>>>>>> Stashed changes
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
		//add(right, 1);
		this.right = right;
<<<<<<< Updated upstream
		super.setRightComponent(right);
=======
		addGUI();
>>>>>>> Stashed changes
		update();
		setTheme();
	}
	public void setLeftComponent(JComponent left) 
	{
		//add(left, 0);
		this.left = left;
<<<<<<< Updated upstream
		super.setLeftComponent(left);
=======
		addGUI();
>>>>>>> Stashed changes
		update();
		setTheme();
	}
}
