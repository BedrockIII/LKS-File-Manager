package GUI.FileInfo;

import java.awt.Rectangle;

import javax.swing.JScrollPane;
import javax.swing.JViewport;

import GUI.GUI;

@SuppressWarnings("serial")
public class FileInfoPanel extends JScrollPane
{
	JViewport panel = null;
	
	
	public FileInfoPanel()
	{
		super(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		//setBackground(Settings.bgColor);
		panel = getViewport();
		Rectangle topRect = new Rectangle(0, 0, 1, 1);
		panel.scrollRectToVisible(topRect);
		//panel.setBackground(Settings.bgColor);
	}
	public void setFile(FileInfoPanel panel) 
	{
		setViewportView(panel);
		GUI.update();
	}
}