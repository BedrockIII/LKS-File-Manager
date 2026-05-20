package GUI.FileList;

import java.awt.Rectangle;

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
		setBackground(GUI.bgColor);
		panel = getViewport();
		Rectangle topRect = new Rectangle(0, 0, 1, 1);
		panel.setBackground(GUI.bgColor);
		panel.scrollRectToVisible(topRect);
	}
	public void setFile(Generic panel) 
	{
		setViewportView(panel);
		GUI.update();
	}
	public int getHeight()
	{
		if(getViewport()==null||getViewport().getView()==null)
		{
			return GUI.assetHeight*5;
		}
		//System.out.println(getViewport(). instanceof Package);
		return Math.max(getViewport().getView().getHeight(),GUI.assetHeight*5)+GUI.assetHeight;
	}
	//bar.
	//frame.add(bar);
	//frame.setVisible(true);
	public void update() 
	{
		if(getViewport()==null||getViewport().getView()==null) return;
		//setMaximumSize(new Dimension(GUI.rowWidth,((Package)getViewport().getView()).getHeight()));
		getViewport().setSize(getWidth(), getHeight());
		if(getViewport().getView() instanceof Package)
		{
			((Package)getViewport().getView()).update();
		}
		else
		{
			((Generic)getViewport().getView()).update();
		}
	}
	public void deselectAll() 
	{
		if(getViewport().getView() instanceof Package)
		{
			((Package)getViewport().getView()).deselectAll();
		}
		else
		{
			((Generic)getViewport().getView()).deselectAll();
		}
	}
}
