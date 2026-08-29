package GUI.FileList;

import javax.swing.JScrollPane;
import javax.swing.JViewport;
import javax.swing.SwingUtilities;

import GUI.GUI;
import bFM.Settings;

@SuppressWarnings("serial")
public class FileListPanel extends JScrollPane
{
	static final int SCROLL_STRENGTH = 10;
	static final double MIN_SCROLL_PERCENT = .01;
	JViewport panel = null;
	FileList file;
	public FileListPanel()
	{
		super(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		getVerticalScrollBar().setUnitIncrement(Settings.assetHeight*SCROLL_STRENGTH);
		setBackground(null);
		panel = getViewport();
		panel.setBackground(null);
	}
	public void setFile(FileList panel) 
	{
		file = panel;
		setViewportView(panel);
		GUI.update();
		update();
	}
	public int getHeight()
	{
		if(getViewport()==null||getViewport().getView()==null)
		{
			return 0;
		}
		return Math.max(getViewport().getView().getHeight(),0);
	}
	//bar.
	//frame.add(bar);
	//frame.setVisible(true);
	public void update() 
	{
		if(getViewport()==null||getViewport().getView()==null) return;
		int scroll = getVerticalScrollBar().getValue();
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
			getVerticalScrollBar().setUnitIncrement(Math.min(Settings.assetHeight*SCROLL_STRENGTH,Math.floorDiv((int)(getHeight()*MIN_SCROLL_PERCENT), Settings.assetHeight)*Settings.assetHeight));
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
	public byte[] getFile() 
	{
		return file.getBytes();
	}
}
