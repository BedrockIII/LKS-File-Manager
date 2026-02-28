package GUI;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class GenericFileInfoGUI extends JPanel
{
	byte[] data = null;
	LabeledInputBox fileSize = null;
	protected GenericFileInfoGUI() 
	{
		fileSize = new LabeledInputBox("File Size: ",  new JLabel("0"), 1.5);
		addGUI();
	}
	public GenericFileInfoGUI(byte[] data) 
	{
		this.data = data;
		makeGUI();
		addGUI();
	}
	private void makeGUI()
	{
		fileSize = new LabeledInputBox("File Size: ",  new JLabel("" + data.length), 1.5);
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
		this.data = data;
		fileSize.replaceComponent(new JLabel("" + data.length));
	}
	public byte[] getBytes() 
	{
		return data;
	}
}
