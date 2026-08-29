package GUI.PopupWindows;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;

import bFM.Settings;

@SuppressWarnings("serial")
public abstract class GenericPopupWindow extends JDialog
{
	private JPanel contentPanel;
	private GridBagConstraints layout;
	@SuppressWarnings("unused")
	private GenericPopupWindow()
	{
		//Call with header name
	}
	public GenericPopupWindow(String name)
	{
		setTheming(name);
		addGUI();
		addButtons();
	}
	private void setTheming(String name)
	{
		setVisible(true);  
		setSize(220, 125);  
		setLocationRelativeTo(GUI.GUI.frame);
		setPreferredSize(new Dimension(200, 150)); 
		setResizable(false);
		setVisible(true);  
		setAlwaysOnTop(true);
		setTitle(name);  
        contentPanel = new JPanel();  
        contentPanel.setLayout(new GridBagLayout());
        getContentPane().add(contentPanel);  
	}
	public Component add(Component c)
	{
		layout = Settings.getDefaultConstraints();
		contentPanel.add(c, layout);
		return this;
	}
	private void addButtons()
	{
		layout = Settings.getDefaultConstraints();
		layout.gridwidth =2;
        
        JButton Cancel = new JButton();
        Cancel.setText("Cancel");
        Cancel.addActionListener(g -> 
        {
        	dispose();
        });
        contentPanel.add(Cancel, layout);
        
        layout.gridwidth =GridBagConstraints.REMAINDER;
        
        JButton Confirm = new JButton();
        Confirm.setText("Confirm");
        Confirm.addActionListener(g -> 
        {
        	execute();
        	
        	dispose();
        });
        contentPanel.add(Confirm, layout);
	}
	protected abstract void addGUI();
	protected abstract void execute();
}