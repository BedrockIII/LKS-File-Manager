package GUI;

import javax.swing.JPanel;
import javax.swing.JScrollPane;

@SuppressWarnings("serial")
public class FileListPanel extends JScrollPane
{
	public FileListPanel()
	{
		super(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		
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
