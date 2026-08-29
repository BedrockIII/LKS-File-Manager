package GUI.FileInfo;

import java.awt.Rectangle;

import javax.swing.JScrollPane;
import javax.swing.JViewport;

import GUI.GUI;
import bFM.Settings;

@SuppressWarnings("serial")
public class FileInfoPanel extends JScrollPane
{
	JViewport panel = null;
	static final int SCROLL_STRENGTH = 10;
	static final double MIN_SCROLL_PERCENT = .01;
	
	public FileInfoPanel()
	{
		super(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		getVerticalScrollBar().setUnitIncrement(Settings.assetHeight*SCROLL_STRENGTH);
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