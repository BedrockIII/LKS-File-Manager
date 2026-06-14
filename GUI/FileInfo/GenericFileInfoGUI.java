package GUI.FileInfo;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.nio.charset.Charset;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import GUI.LabeledInputBox;
import bFM.Data;
import bFM.OpenedFile;

@SuppressWarnings("serial")
public class GenericFileInfoGUI extends JPanel
{
	Data file = null;
	LabeledInputBox fileSize = null;
	JTextArea data = new JTextArea();
	protected GenericFileInfoGUI() 
	{
		fileSize = new LabeledInputBox("File Size: ",  new JLabel("0"), 1.5);
		addGUI();
	}
	public GenericFileInfoGUI(Data file2) 
	{
		this.file = file2;
		makeGUI();
		addGUI();
	}
	private void makeGUI()
	{
		fileSize = new LabeledInputBox("File Size: ",  new JLabel("" + file.getSize()), 1.5);
		if(file.toBytes().length<50000)
		{
			data.setText(new String(file.toBytes(), Charset.forName("Shift-JIS")));
		}
		
	}
	private void addGUI()
	{
		removeAll();
		setLayout(new GridBagLayout());
		GridBagConstraints layout = new GridBagConstraints();
		layout.anchor = GridBagConstraints.NORTHWEST;
		layout.gridwidth = GridBagConstraints.REMAINDER;
		layout.weighty = 0.0;
		layout.weightx = 0.0;
		add(fileSize, layout);
		layout.weighty = 1.0;
		layout.weightx = 1.0;
		layout.fill = GridBagConstraints.BOTH;
		data.setMinimumSize(new Dimension(10,20));
		add(data, layout);
		
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
		return file.toBytes();
	}
	public void update() 
	{
		if(file==null) fileSize.replaceComponent(new JLabel("0"));
		else fileSize.replaceComponent(new JLabel("" + file.toBytes().length));
	}
}
