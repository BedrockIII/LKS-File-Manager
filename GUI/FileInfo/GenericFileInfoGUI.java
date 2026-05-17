package GUI.FileInfo;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;

import GUI.LabeledInputBox;
import PCKGManager.OpenedFile;

@SuppressWarnings("serial")
public class GenericFileInfoGUI extends JPanel
{
	OpenedFile file = null;
	LabeledInputBox fileSize = null;
	protected GenericFileInfoGUI() 
	{
		fileSize = new LabeledInputBox("File Size: ",  new JLabel("0"), 1.5);
		addGUI();
	}
	public GenericFileInfoGUI(OpenedFile file) 
	{
		this.file = file;
		makeGUI();
		addGUI();
	}
	private void makeGUI()
	{
		fileSize = new LabeledInputBox("File Size: ",  new JLabel("" + file.getData().length), 1.5);
	}
	private void addGUI()
	{
		removeAll();
		setLayout(new GridBagLayout());
		GridBagConstraints layout = new GridBagConstraints();
		layout.anchor = GridBagConstraints.NORTHWEST;
		layout.gridwidth = GridBagConstraints.REMAINDER;
		layout.weighty = 1.0;
		layout.weightx = 1.0;
		add(fileSize, layout);
	}
	public void updateGUI(byte[] data)
	{
		file.setData(data);
		//System.out.println(data.length);
		fileSize.replaceComponent(new JLabel("" + data.length));
		//repaint();
	}
	public byte[] getBytes() 
	{
		return file.getData();
	}
	public void update() 
	{
		if(file==null) fileSize.replaceComponent(new JLabel("0"));
		else fileSize.replaceComponent(new JLabel("" + file.getData().length));
	}
}
