package GUI.FileInfo;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import bFM.Settings;

@SuppressWarnings("serial")
public class NullFileInfoGUI extends GenericFileInfoGUI
{
	JLabel file = null;
	public NullFileInfoGUI() 
	{
		makeGUI();
		addGUI();
	}
	private void makeGUI()
	{
		file = new JLabel("No File GUI Specified");
		
	}
	private void addGUI()
	{
		removeAll();
		setLayout(new GridBagLayout());
		GridBagConstraints layout = Settings.getDefaultConstraints();
		layout.weighty = 1.0;
		add(file, layout);
		
	}
	public void updateGUI(byte[] data)
	{
		
	}
	public byte[] getBytes() 
	{
		return null;
	}
	public void update() 
	{
		revalidate();
		repaint();
	}
}
