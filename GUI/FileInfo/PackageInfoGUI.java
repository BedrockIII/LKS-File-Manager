package GUI.FileInfo;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JLabel;

import GUI.LabeledInputBox;
import PCKGManager.PCKGManager;

@SuppressWarnings("serial")
public class PackageInfoGUI extends GenericFileInfoGUI
{
	PCKGManager packageFile = null;
	LabeledInputBox fileCount = null;
	LabeledInputBox pacSize = null;
	public PackageInfoGUI(PCKGManager packageFile) 
	{
		this.packageFile = packageFile;
		makeGUI();
		addGUI();
		update();
	}
	private void makeGUI()
	{
		pacSize = new LabeledInputBox("File Size: ",  new JLabel("" + packageFile.getSize()));
		fileCount = new LabeledInputBox("Packed File Count: ",  new JLabel("" + packageFile.getFileAmount()));
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
		add(pacSize, layout);
		layout.weighty = 1.0;
		add(fileCount, layout);
		layout.weighty = 0.0;
	}
	public void update()
	{
		pacSize.replaceComponent(new JLabel("" + packageFile.getSize()));
		fileCount.replaceComponent(new JLabel("" + packageFile.getFileAmount()));
		repaint();
	}
}
