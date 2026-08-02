package GUI.PopupWindows;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JTextField;

import GUI.LabeledInputBox;
import GUI.FileList.Resources.MOPlacementListGUI.RandomAreasListGUI;
import bFM.Settings;
import bFM.Utils;

@SuppressWarnings("serial")
public class NewMobRandomAreaWindow extends JDialog
{
	JPanel contentPanel;
	public NewMobRandomAreaWindow(RandomAreasListGUI gui)
	{
		setTheming("Create new Random Area");
		
		GridBagConstraints layout = Settings.getDefaultConstraints();
		layout.weightx = 1.0;
		layout.weighty = 1.0;
		JTextField code = new JTextField("");
		
		LabeledInputBox type = new LabeledInputBox("Area Code", code);
	    
	    contentPanel.add(type, layout);
	    
        
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
        	gui.newArea(Utils.strToInt(code.getText()));
        	dispose();
        });
        contentPanel.add(Confirm, layout);
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
}
