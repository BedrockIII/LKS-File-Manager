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

import GUI.FileList.FileList;

@SuppressWarnings("serial")
public class RenameWindow extends JDialog
{
	public RenameWindow(FileList fileList)
	{
		setVisible(true);  
		setSize(200, 100);  
		setLocationRelativeTo(GUI.GUI.frame);
		setPreferredSize(new Dimension(200, 100));  
		setVisible(true);  
		setTitle("Rename File");  
        JPanel contentPanel = new JPanel();  
        contentPanel.setLayout(new BorderLayout());  
        getContentPane().add(contentPanel);  
		
		contentPanel.setLayout(new GridBagLayout());
		GridBagConstraints layout = new GridBagConstraints();
		layout.weightx = 1.0;
		layout.weighty = 1.0;
		
        JLabel labelOptions = new JLabel("Rename File:");  
        labelOptions.setPreferredSize(new Dimension(75, 20));  
        contentPanel.add(labelOptions, layout);  
        final JTextField newTitle = new JTextField(fileList.getName()); 
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
        	fileList.setName(newTitle.getText());
        	dispose();
        });
        contentPanel.add(Confirm, layout);
	}
}
