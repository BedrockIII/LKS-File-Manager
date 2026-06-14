package GUI;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

@SuppressWarnings("serial")
public class LabeledInputBox extends JPanel
{
	Component comp;
	JLabel text;
	int height = 0;
	GridBagConstraints layout = new GridBagConstraints();
	public LabeledInputBox(String labelText, Component comp, double yFactor)
	{
		this.comp = comp;
		height = (int)(GUI.assetHeight*yFactor);
		
		setLayout(new GridBagLayout());
		layout.anchor = GridBagConstraints.NORTHWEST;
		layout.fill = GridBagConstraints.HORIZONTAL;
		layout.weighty = 1.0;
		layout.gridwidth = 1;
		text = new JLabel(labelText);
		update();
	}
	public void update()
	{
		removeAll();
		layout.weightx = 0.0;
		add(text, layout);
		layout.weightx = 1.0;
		add(comp, layout);
		
		
		text.setPreferredSize(new Dimension((int) GUI.getRightSize().getWidth() / 2, height));
		text.setMinimumSize(new Dimension(GUI.buttonWidth, height));
		
		comp.setPreferredSize(new Dimension((int) GUI.getRightSize().getWidth() / 2 - GUI.buttonWidth, height));
		comp.setMinimumSize(new Dimension(250 - GUI.buttonWidth-5, height));
		setPreferredSize(new Dimension((int) GUI.getRightSize().getWidth(), height));
		setMinimumSize(new Dimension(250, height));
		repaint();
	}
	public void replaceComponent(Component comp) 
	{
		this.comp = comp;
		update();
	}
	public void replaceText(String text) 
	{
		this.text.setText(text);
	}
}
