package GUI.PopupWindows.SettingsPopups;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import GUI.GUI;
import GUI.LabeledInputBox;
import bFM.Settings;

@SuppressWarnings("serial")
public class PathsWindow extends JDialog
{
	final double xWeight = .25;
	public PathsWindow()
	{
		setVisible(true);  
		setMinimumSize(new Dimension(750, 40));  
		setLocationRelativeTo(GUI.frame);
		setResizable(false);
		setVisible(true);  
		setAlwaysOnTop(true);
		setTitle("Define File Manager Paths");  
        JPanel contentPanel = new JPanel();  
        contentPanel.setLayout(new GridBagLayout());
        GridBagConstraints layout = Settings.getDefaultConstraints();
        getContentPane().add(contentPanel);  
		
        JTextField importPath = new JTextField(Settings.importPath);
        importPath.getDocument().addDocumentListener(new DocumentListener()
		{
			public void insertUpdate(DocumentEvent e)
			{
				Settings.importPath = importPath.getText();
				GUI.update();
			}
			public void removeUpdate(DocumentEvent e)
			{
				Settings.importPath = importPath.getText();
				GUI.update();
			}
			public void changedUpdate(DocumentEvent e) {}
		});
        contentPanel.add(new LabeledInputBox("Bedrock's Extracted Mod Path: ", importPath, xWeight), layout);
        
        JTextField outputPath = new JTextField(Settings.outputPath);
        outputPath.getDocument().addDocumentListener(new DocumentListener()
		{
			public void insertUpdate(DocumentEvent e)
			{
				Settings.outputPath = outputPath.getText();
			}
			public void removeUpdate(DocumentEvent e)
			{
				Settings.outputPath = outputPath.getText();
			}
			public void changedUpdate(DocumentEvent e) {}
		});
        contentPanel.add(new LabeledInputBox("Riivolution Mod Path: ", outputPath, xWeight), layout);
        
        JTextField lastFileSavePath = new JTextField(Settings.lastFileSavePath);
        lastFileSavePath.getDocument().addDocumentListener(new DocumentListener()
		{
			public void insertUpdate(DocumentEvent e)
			{
				Settings.lastFileSavePath = lastFileSavePath.getText();
			}
			public void removeUpdate(DocumentEvent e)
			{
				Settings.lastFileSavePath = lastFileSavePath.getText();
			}
			public void changedUpdate(DocumentEvent e) {}
		});
        contentPanel.add(new LabeledInputBox("Default File Save Path: ", lastFileSavePath, xWeight), layout);

        JTextField lastFileOpenPath = new JTextField(Settings.lastFileOpenPath);
        lastFileOpenPath.getDocument().addDocumentListener(new DocumentListener()
		{
			public void insertUpdate(DocumentEvent e)
			{
				Settings.lastFileOpenPath = lastFileOpenPath.getText();
			}
			public void removeUpdate(DocumentEvent e)
			{
				Settings.lastFileOpenPath = lastFileOpenPath.getText();
			}
			public void changedUpdate(DocumentEvent e) {}
		});
        contentPanel.add(new LabeledInputBox("Default File Open Path: ", lastFileOpenPath, xWeight), layout);
        pack();
	}
	
}
