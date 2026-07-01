package GUI.FileInfo;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JLabel;

import GUI.LabeledInputBox;
import bFM.Settings;
import colReader.ColReader;

@SuppressWarnings("serial")
public class CollisionInfoGUI extends GenericFileInfoGUI 
{
	JLabel fileSizeText = null;
	JLabel objectCountText = null;
	public CollisionInfoGUI(ColReader file) 
	{
		this.file = file;
		makeGUI();
		addGUI();
	}
	private void makeGUI()
	{
		fileSizeText = new JLabel("" + file.toBytes().length);
		objectCountText = new JLabel("" + ((ColReader)file).getObjects().size());
	}
	private void addGUI()
	{
		removeAll();
		setLayout(new GridBagLayout());
		GridBagConstraints layout = Settings.getDefaultConstraints();
		add(new LabeledInputBox("File Size: ", fileSizeText), layout);
		
		layout.weighty = 1.0;
		add(new LabeledInputBox("Collision Object Count: ", objectCountText), layout);
	}
}
