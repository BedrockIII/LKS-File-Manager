package GUI.FileInfo;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JLabel;
import javax.swing.JTextField;

import GUI.LabeledInputBox;
import PCKGManager.PCKGManager;
import bFM.GUIUtils;

@SuppressWarnings("serial")
public class PackageInfoGUI extends GenericFileInfoGUI
{
	PCKGManager packageFile = null;
	JTextField name = null;
	JLabel fileCount = null;
	JLabel pacSize = null;
	public PackageInfoGUI(PCKGManager packageFile) 
	{
		this.packageFile = packageFile;
		makeGUI();
		addGUI();
		update();
	}
	private void makeGUI()
	{
		name = GUIUtils.createNameTextField(packageFile.getName(), packageFile::setName);
		pacSize = new JLabel("" + packageFile.getSize());
		fileCount = new JLabel("" + packageFile.getFileAmount());
	}
	private void addGUI()
	{
		removeAll();
		setLayout(new GridBagLayout());
		GridBagConstraints layout = new GridBagConstraints();
		layout.anchor = GridBagConstraints.NORTHWEST;
		layout.gridwidth = GridBagConstraints.REMAINDER;
		layout.fill = GridBagConstraints.HORIZONTAL;
		layout.weightx = 1.0;
		add(new LabeledInputBox("File Name: ",  name), layout);
		add(new LabeledInputBox("File Size: ",  pacSize), layout);
		layout.weighty = 1.0;
		add(new LabeledInputBox("Packed File Count: ",  fileCount), layout);
		layout.weighty = 0.0;
	}
	public void update()
	{
		pacSize.setText("" + packageFile.getSize());
		fileCount.setText("" + packageFile.getFileAmount());
		super.update();
	}
}
