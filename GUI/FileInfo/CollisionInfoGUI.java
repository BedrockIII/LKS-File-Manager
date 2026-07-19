package GUI.FileInfo;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JLabel;
import javax.swing.JTextField;

import GUI.LabeledInputBox;
import bFM.GUIUtils;
import bFM.Settings;
import colReader.ColReader;

@SuppressWarnings("serial")
public class CollisionInfoGUI extends GenericFileInfoGUI 
{
	JLabel fileSizeText = null;
	JTextField fileName = null;
	JLabel objectCountText = null;
	public CollisionInfoGUI(ColReader file) 
	{
		this.file = file;
		makeGUI();
		addGUI();
	}
	private void makeGUI()
	{
		fileName = GUIUtils.createNameTextField(file.getName(), file::setName);
		fileSizeText = new JLabel("" + file.toBytes().length);
		objectCountText = new JLabel("" + ((ColReader)file).getObjects().size());
	}
	private void addGUI()
	{
		removeAll();
		setLayout(new GridBagLayout());
		GridBagConstraints layout = Settings.getDefaultConstraints();
		add(new LabeledInputBox("File Name: ", fileName), layout);
		add(new LabeledInputBox("File Size: ", fileSizeText), layout);
		
		layout.weighty = 1.0;
		add(new LabeledInputBox("Collision Object Count: ", objectCountText), layout);
	}
	public void update()
	{
		fileSizeText.setText("" + file.toBytes().length);
		objectCountText.setText("" + ((ColReader)file).getObjects().size());
		super.update();
	}
}
