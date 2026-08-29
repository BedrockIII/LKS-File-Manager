package GUI.FileInfo;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import GUI.BitFlagPanel;
import GUI.CollapseablePanel;
import GUI.LabeledInputBox;
import GUI.SplitPanel;
import bFM.Data;
import bFM.Settings;
import bFM.Utils;

@SuppressWarnings("serial")
public class GenericFileInfoGUI extends JPanel
{
	Data file = null;
	LabeledInputBox fileSize = null;
	JTextArea data = new JTextArea();
	protected GenericFileInfoGUI() 
	{
		fileSize = new LabeledInputBox("File Size: ",  new JLabel("0"));
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
		fileSize = new LabeledInputBox("File Size: ",  new JLabel("" + file.getSize()));
		if(file.toBytes().length<50000)
		{
			data.setText(Utils.decodeBytesToString(file.toBytes()));
		}
		
	}
	private void addGUI()
	{
		removeAll();
		setLayout(new GridBagLayout());
		GridBagConstraints layout = Settings.getDefaultConstraints();
		add(fileSize, layout);
		layout.weighty = 1.0;
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
		for(Component c : this.getComponents())
		{
			if(c instanceof SplitPanel)
			{
				((SplitPanel) c).update();
			}
			else if(c instanceof CollapseablePanel)
			{
				((CollapseablePanel) c).update();
			}
			else if(c instanceof BitFlagPanel)
			{
				((BitFlagPanel) c).update();
			}
		}
		revalidate();
		repaint();
	}
}
