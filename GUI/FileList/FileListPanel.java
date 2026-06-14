package GUI.FileList;

import javax.swing.JScrollPane;
import javax.swing.JViewport;
import javax.swing.SwingUtilities;

import GUI.GUI;

@SuppressWarnings("serial")
public class FileListPanel extends JScrollPane
{
	JViewport panel = null;
	public FileListPanel()
	{
		super(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		setBackground(null);
		panel = getViewport();
		panel.setBackground(null);
	}
	public void setFile(FileList panel) 
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
		int scroll = getVerticalScrollBar().getValue();
		//setMaximumSize(new Dimension(GUI.rowWidth,((Package)getViewport().getView()).getHeight()));
		getViewport().setSize(getWidth(), getHeight());
		if(getViewport().getView() instanceof Generic)
		{
			((FileList)getViewport().getView()).update();
		}
		else
		{
			((FileList)getViewport().getView()).update();
		}
		int finalS = Math.min(getVerticalScrollBar().getMaximum(), scroll);
		SwingUtilities.invokeLater(() -> {
			getVerticalScrollBar().setValue(finalS);
		});
	}
	public void deselectAll() 
	{
		int scroll = getVerticalScrollBar().getValue();
		if(getViewport().getView() instanceof Generic)
		{
			((FileList)getViewport().getView()).deselectAll();
		}
		else
		{
			((FileList)getViewport().getView()).deselectAll();
		}
		getVerticalScrollBar().setValue(Math.min(getVerticalScrollBar().getMaximum(), scroll));
	}
}
