package GUI.PopupWindows;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import GUI.FileList.ItemDatabaseList;
import ResourceManagers.ItemDatabaseManager.Item;
import bFM.Settings;

@SuppressWarnings("serial")
public class NewItemPopup extends JDialog
{
	public NewItemPopup(ItemDatabaseList gui) 
	{
		setVisible(true);  
		setSize(220, 125);  
		setLocationRelativeTo(GUI.GUI.frame);
		setPreferredSize(new Dimension(200, 150)); 
		setResizable(false);
		setVisible(true);  
		setAlwaysOnTop(true);
		setTitle("Create New Item");  
        JPanel contentPanel = new JPanel();  
        contentPanel.setLayout(new BorderLayout());  
        getContentPane().add(contentPanel);  
		
		contentPanel.setLayout(new GridBagLayout());
		GridBagConstraints layout = Settings.getDefaultConstraints();
		layout.weighty = 1.0;
		layout.gridwidth =2;
		JLabel labelOptions = new JLabel("New Item Name:");  
	    labelOptions.setPreferredSize(new Dimension(100, 20));  
	    contentPanel.add(labelOptions, layout);  
	    final JTextField newTitle = new JTextField(); 
	    newTitle.setEditable(true);
	    newTitle.setPreferredSize(new Dimension(100, 20));  
	    
	    layout.gridwidth =GridBagConstraints.REMAINDER;
	    contentPanel.add(newTitle, layout);
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
        	gui.addItem(new Item(newTitle.getText()));
        	dispose();
        });
        contentPanel.add(Confirm, layout);
	}

}
