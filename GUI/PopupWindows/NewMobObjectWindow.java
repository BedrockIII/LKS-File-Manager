package GUI.PopupWindows;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JPanel;
import GUI.LabeledInputBox;
import GUI.FileList.Resources.MOPlacementListGUI.GroupTypesListGUI.GroupsListGUI.GroupListGUI;
import ResourceManagers.MSDBManager.Placement.MobObject.ObjectDefault;
import bFM.Settings;

@SuppressWarnings("serial")
public class NewMobObjectWindow extends JDialog
{
	JPanel contentPanel;
	public NewMobObjectWindow(GroupListGUI gui)
	{
		setTheming("Create new Object");
		
		GridBagConstraints layout = Settings.getDefaultConstraints();
		layout.weightx = 1.0;
		layout.weighty = 1.0;
		JComboBox<ObjectDefault> types = new JComboBox<ObjectDefault>();
		types.addItem(ObjectDefault.OBJECT);
		types.addItem(ObjectDefault.STILLUMA);
		types.addItem(ObjectDefault.WANDERUMA);
		
		LabeledInputBox type = new LabeledInputBox("Object Defaults", types);
	    
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
        	gui.newObject((ObjectDefault)types.getSelectedItem());
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
