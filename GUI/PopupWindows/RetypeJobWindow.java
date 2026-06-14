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

import GUI.FileList.CharacterDataBaseList.CharacterJobPriceChangeList.JobChangePriceListGUI;

@SuppressWarnings("serial")
public class RetypeJobWindow extends JDialog
{
	public RetypeJobWindow(JobChangePriceListGUI file)
	{
		setVisible(true);  
		setSize(200, 100);  
		setLocationRelativeTo(GUI.GUI.frame);
		setPreferredSize(new Dimension(200, 100));  
		setVisible(true);  
		setTitle("Change Job");  
        JPanel contentPanel = new JPanel();  
        contentPanel.setLayout(new BorderLayout());  
        getContentPane().add(contentPanel);  
		
		contentPanel.setLayout(new GridBagLayout());
		GridBagConstraints layout = new GridBagConstraints();
		layout.weightx = 1.0;
		layout.weighty = 1.0;
		
        JLabel labelOptions = new JLabel("New Job Code:");  
        labelOptions.setPreferredSize(new Dimension(75, 20));  
        contentPanel.add(labelOptions, layout);  
        layout.fill = GridBagConstraints.HORIZONTAL;
        final JTextField newTitle = new JTextField(file.getFile().getJobCode()); 
        newTitle.setEditable(true);
        newTitle.setPreferredSize(new Dimension(100, 20));  
        
        layout.gridwidth =GridBagConstraints.REMAINDER;
        
        contentPanel.add(newTitle, layout);
        layout.fill = GridBagConstraints.NONE;
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
        	file.setCode(newTitle.getText());
        	dispose();
        });
        contentPanel.add(Confirm, layout);
	}
}