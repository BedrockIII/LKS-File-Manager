package GUI.FileInfo;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JLabel;
import javax.swing.JTextField;

import GUI.LabeledInputBox;
import WorldFileManager.fpInterpreter;
import bFM.GUIUtils;
import bFM.Settings;

@SuppressWarnings("serial")
public class FixedPointInfoGUI extends GenericFileInfoGUI 
{
	JLabel fileSizeText = null;
	JTextField fileName = null;
	JLabel objectCountText = null;
	public FixedPointInfoGUI(fpInterpreter file) 
	{
		this.file = file;
		makeGUI();
		addGUI();
	}
	private void makeGUI()
	{
		fileName = GUIUtils.createNameTextField(file.getName(), file::setName);
		fileSizeText = new JLabel("" + file.toBytes().length);
		objectCountText = new JLabel("" + ((fpInterpreter)file).getObjects().size());
	}
	private void addGUI()
	{
		removeAll();
		setLayout(new GridBagLayout());
		GridBagConstraints layout = Settings.getDefaultConstraints();
		add(new LabeledInputBox("File Name: ", fileName), layout);
		add(new LabeledInputBox("File Size: ", fileSizeText), layout);
		
		layout.weighty = 1.0;
		add(new LabeledInputBox("Fixed Point Object Count: ", objectCountText), layout);
	}
}
