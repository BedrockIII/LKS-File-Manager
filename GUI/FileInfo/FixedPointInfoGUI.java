package GUI.FileInfo;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JLabel;

import GUI.LabeledInputBox;
import WorldFileManager.fpInterpreter;

@SuppressWarnings("serial")
public class FixedPointInfoGUI extends GenericFileInfoGUI 
{
	LabeledInputBox ObjectCount = null;
	public FixedPointInfoGUI(fpInterpreter file) 
	{
		this.file = file;
		makeGUI();
		addGUI();
	}
	private void makeGUI()
	{
		fileSize = new LabeledInputBox("File Size: ",  new JLabel("" + file.toBytes().length), 1.5);
		ObjectCount = new LabeledInputBox("Fixed Point Object Count: ",  new JLabel("" + ((fpInterpreter)file).getObjects().size()), 1.5);
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
		 
		add(ObjectCount, layout);
	}
	public void updateGUI(byte[] data)
	{
		file.setData(data);
		//System.out.println(data.length);
		fileSize.replaceComponent(new JLabel("" + data.length));
		ObjectCount.replaceComponent(new JLabel("" + ((fpInterpreter)file).getObjects().size()));
		//repaint();
	}
	public void update() 
	{
		if(file==null)
		{
			fileSize.replaceComponent(new JLabel("0"));
			ObjectCount.replaceComponent(new JLabel("0"));
		}
		else 
		{
			fileSize.replaceComponent(new JLabel("" + file.getSize()));
			ObjectCount.replaceComponent(new JLabel("" + ((fpInterpreter)file).getObjects().size()));
		}
	}
}
