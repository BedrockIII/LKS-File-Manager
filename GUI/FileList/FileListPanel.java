package GUI.FileList;

import java.awt.Rectangle;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JViewport;

import GUI.GUI;

@SuppressWarnings("serial")
public class FileListPanel extends JScrollPane
{
	JViewport panel = null;
	public FileListPanel()
	{
		super(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		
		panel = getViewport();
		Rectangle topRect = new Rectangle(0, 0, 1, 1);
		panel.scrollRectToVisible(topRect);
	}
	public void setFile(JPanel panel) 
	{
		setViewportView(panel);
		GUI.update();
	}
	public int getHeight()
	{
		//System.out.println(getViewport(). instanceof Package);
		try
		{
			return Math.max(getViewport().getView().getHeight(),GUI.assetHeight*5);
		}
		catch (NullPointerException e)
		{
			return GUI.assetHeight*5;
		}
	}
	//bar.
	//frame.add(bar);
	//frame.setVisible(true);
	public void update() 
	{
		try
		{
			//setMaximumSize(new Dimension(GUI.rowWidth,((Package)getViewport().getView()).getHeight()));
			getViewport().setSize(getWidth(), getHeight());
			((Package)getViewport().getView()).update();
		}
		catch (NullPointerException e)
		{
			
		}
	}
	public void deselectAll() 
	{
		((Package)getViewport().getView()).deselectAll();
	}
}
