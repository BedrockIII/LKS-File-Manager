package GUI;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JLabel;
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
		updateGUI();
	}
	private void makeGUI()
	{
		pacSize = new LabeledInputBox("File Size: ",  new JLabel("" + packageFile.getFile().length), 1.5);
		fileCount = new LabeledInputBox("Packed File Count: ",  new JLabel("" + packageFile.getFileAmount()), 1.5);
	}
	private void addGUI()
	{
		removeAll();
		setLayout(new GridBagLayout());
		GridBagConstraints layout = new GridBagConstraints();
		layout.anchor = GridBagConstraints.NORTHWEST;
		layout.gridwidth = GridBagConstraints.REMAINDER;
		layout.weightx = 1.0;
		add(pacSize, layout);
		layout.weighty = 1.0;
		add(fileCount, layout);
		layout.weighty = 0.0;
	}
	public void updateGUI()
	{
		pacSize.replaceComponent(new JLabel("" + packageFile.getFile().length));
		fileCount.replaceComponent(new JLabel("" + packageFile.getFileAmount()));
	}
}
