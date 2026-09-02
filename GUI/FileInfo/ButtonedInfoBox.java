package GUI.FileInfo;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.SwingUtilities;

import GUI.GUI;
import GUI.LabeledInputBox;
import GUI.SplitPanel;
import bFM.Settings;

@SuppressWarnings("serial")
public class ButtonedInfoBox extends SplitPanel
{
	JButton actionButton = new JButton();
	SplitPanel contents;
	@SuppressWarnings("unused")
	private ButtonedInfoBox()
	{
		throw new IllegalArgumentException("Empty Constructor Should Not Be Used");
	}
	public ButtonedInfoBox(Runnable f, JComponent left, JComponent right)
	{
		initializeButton(f);
		contents = new SplitPanel(right, actionButton);
		setLeftComponent(left);
		setRightComponent(contents);
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
	public void setButtonEnabled(boolean isClickable)
	{
		actionButton.setEnabled(isClickable);
	}
	public Component getRightComponent()
	{
		if(contents == null) return null;
		return contents.getLeftComponent();
	}
	public void update()
	{
		super.update();
	}
}
