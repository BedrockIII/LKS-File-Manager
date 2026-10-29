package GUI;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.BorderFactory;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.BevelBorder;
import bFM.Settings;

@SuppressWarnings("serial")
public class SplitPanel extends JPanel
{
	final JLabel nullLeft = new JLabel("Null Left");
	final JLabel nullRight = new JLabel("Null Right");
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
		removeAll();
		setLayout(new GridBagLayout());
		GridBagConstraints layout = Settings.getDefaultConstraints();
		layout.gridwidth = GridBagConstraints.RELATIVE;
		layout.insets = new Insets(0,0,0,0);
		layout.weighty = 1;
		layout.weightx = xWeight;
		layout.fill = GridBagConstraints.HORIZONTAL;
		
		if(left != null)
		{
			left.setPreferredSize(new Dimension(Settings.buttonWidth,height));
			add(left, layout);
		}
		else add(nullLeft, layout);
		
		layout.weightx = (1-xWeight);
		
		if(right != null)
		{
			right.setPreferredSize(new Dimension(Settings.buttonWidth,height));
			add(right, layout);
		}
		else add(nullRight, layout);
		
		revalidate();
		repaint();
	}
	public void update()
	{
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
		revalidate();
		repaint();
		//if(left != null) left.setBorder(new LineBorder(Color.BLACK));
		//if(right != null) right.setBorder(new LineBorder(Color.BLACK));
		//setBorder(new LineBorder(Color.BLACK));
	}
	public void setRightComponent(JComponent right) 
	{
		this.right = right;
		update();
		setTheme();
	}
	public void setLeftComponent(JComponent left) 
	{
		this.left = left;
		update();
		setTheme();
	}
	public JComponent getLeftComponent()
	{
		return left;
	}
	public JComponent getRightComponent()
	{
		return right;
	}
}
