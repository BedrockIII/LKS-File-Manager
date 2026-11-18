package GUI.FileInfo;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JPanel;

import GUI.SplitPanel;
import bFM.Settings;

@SuppressWarnings("serial")
public class ButtonedInfoBox extends SplitPanel
{
	JButton actionButton = new JButton();
	JComponent left;
	JComponent right;
	JPanel contentAndButton;
	@SuppressWarnings("unused")
	private ButtonedInfoBox()
	{
		throw new IllegalArgumentException("Empty Constructor Should Not Be Used");
	}
	public ButtonedInfoBox(Runnable f, JComponent left, JComponent right)
	{
		this.left = left;
		this.right = right;
		initializeButton(f);
		initializeContent();
		
		setLeftComponent(left);
		setRightComponent(contentAndButton);
	}
	private void initializeButton(Runnable f)
	{
		actionButton.setPreferredSize(new Dimension(Settings.assetHeight, Settings.assetHeight));
		actionButton.setMaximumSize(new Dimension(Settings.assetHeight, Settings.assetHeight));
		actionButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e) 
			{
				f.run();
			}
			
		});
	}
	private void initializeContent()
	{
		contentAndButton = new JPanel();
		
		
		contentAndButton.removeAll();
		contentAndButton.setLayout(new GridBagLayout());
		GridBagConstraints layout = Settings.getDefaultConstraints();
		layout.gridwidth = GridBagConstraints.RELATIVE;
		layout.insets = new Insets(0,0,0,0);
		layout.weighty = 1;
		layout.weightx = 1;
		layout.fill = GridBagConstraints.BOTH;
		
		
		contentAndButton.add(right, layout);
		layout.weightx = 0;
		
		contentAndButton.add(actionButton, layout);
		
		contentAndButton.revalidate();
		contentAndButton.repaint();
		
	}
	public void setButtonEnabled(boolean isClickable)
	{
		actionButton.setEnabled(isClickable);
	}
	public Component getRightComponent()
	{
		return right;
	}
	public void update()
	{
		super.update();
	}
}
