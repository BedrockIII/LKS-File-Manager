package GUI;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.BevelBorder;

import bFM.Settings;

@SuppressWarnings("serial")
public class CollapseablePanel extends JPanel
{
	ArrayList<Component> components;
	JLabel headerText;
	JPanel header;
	JCheckBox isExtended;
	public CollapseablePanel(String headerName)
	{
		makeGUI(headerName);
		addGUI();
		setTheme();
	}
	private void setTheme()
	{
		setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED, Settings.LighterColor, Settings.DarkerColor));
		header.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED, Settings.LighterColor, Settings.DarkerColor));
	}
	private void makeGUI(String headerName)
	{
		headerText = new JLabel(headerName);
		header = new JPanel();
		isExtended = new JCheckBox();
		isExtended = new JCheckBox();
		isExtended.setMargin(new Insets(0,0,0,0));
		isExtended.setBorderPainted(false);
		isExtended.setContentAreaFilled(false);
		isExtended.setBackground(Settings.bgColor);
		isExtended.setSelected(false);
		isExtended.setPreferredSize(new Dimension(15, Settings.assetHeight));
		try {
			//ImageIcon grown = );
			//Image scalar = grown.getImage();
			isExtended.setSelectedIcon(new ImageIcon(ClassLoader.getSystemResourceAsStream("Grown.png").readAllBytes()));
			isExtended.setDisabledIcon(new ImageIcon(ClassLoader.getSystemResourceAsStream("Empty.png").readAllBytes()));
			//System.out.println(grown.getIconHeight());
			isExtended.setIcon(new ImageIcon(ClassLoader.getSystemResourceAsStream("Shrunk.png").readAllBytes()));
			
		} catch (IOException e) 
		{
			System.out.println("Failed to locate +/- Images");
		} catch (NullPointerException e)
		{
			isExtended.setSelectedIcon(new ImageIcon("Grown.png"));
			isExtended.setDisabledIcon(new ImageIcon("Empty.png"));
			isExtended.setIcon(new ImageIcon("Shrunk.png"));
		}
		isExtended.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e) 
			{
				update();
				GUI.update();
			}
			
		});
		components = new ArrayList<Component>();
	}
	private void addGUI()
	{
		addHeaderSubComponents();
		removeAll();
		setLayout(new GridBagLayout());
		GridBagConstraints layout = Settings.getDefaultConstraints();
		super.add(header, layout);
		if(isExtended.isSelected())
		{
			//Reset Layout
			layout = Settings.getDefaultConstraints();
			for(Component c : components)
			{
				super.add(c, layout);
			}
		}
	}
	private void addHeaderSubComponents()
	{
		header.setLayout(new GridBagLayout());
		GridBagConstraints layout = Settings.getDefaultConstraints();
		layout.gridwidth = 2;
		header.add(headerText, layout);
		layout.weightx = 0.0;
		layout.gridwidth = GridBagConstraints.REMAINDER;
		layout.anchor = GridBagConstraints.NORTHEAST;
		header.add(isExtended, layout);
	}
	public void update()
	{
		GridBagConstraints layout = Settings.getDefaultConstraints();
		for(Component c : components)
		{
			remove(c);
		}
		if(isExtended.isSelected())
		{
			for(Component c : components)
			{
				super.add(c, layout);
				if(c instanceof LabeledInputBox)
				{
					((LabeledInputBox) c).update();
				}
			}
		}
		revalidate();
		repaint();
	}
	public Component add(Component c)
	{
		components.add(c);
		return this;
	}
}
