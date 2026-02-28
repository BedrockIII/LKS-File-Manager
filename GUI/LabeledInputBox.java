package GUI;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class LabeledInputBox extends JPanel
{
	Component comp;
	int height = 0;
	public LabeledInputBox(String labelText, Component comp, double yFactor)
	{
		this.comp = comp;
		height = (int)(GUI.assetHeight*yFactor);
		GridBagConstraints layout = new GridBagConstraints();
		setLayout(new GridBagLayout());
		layout.anchor = GridBagConstraints.NORTHWEST;
		layout.fill = GridBagConstraints.HORIZONTAL;
		layout.weightx = 1.0;
		layout.weighty = 1.0;
		layout.gridwidth = GridBagConstraints.REMAINDER;
		setLayout(new GridBagLayout());
		JLabel text = new JLabel(labelText);
		text.setPreferredSize(GUI.buttonSize);
		add(text);
		add(comp, layout);
		update();
	}
	public void update()
	{
		comp.setPreferredSize(new Dimension((int) GUI.getRightSize().getWidth() - GUI.buttonWidth, height));
		comp.setMinimumSize(new Dimension(250 - GUI.buttonWidth, height));
		setPreferredSize(new Dimension((int) GUI.getRightSize().getWidth(), height));
		setMinimumSize(new Dimension(250, height));
		repaint();
	}
	public void replaceComponent(Component comp) 
	{
		this.comp = comp;
		update();
	}
}
