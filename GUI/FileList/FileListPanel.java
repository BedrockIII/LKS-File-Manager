package GUI.FileList;

import java.awt.BorderLayout;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import GUI.GUI;
import bFM.GUIUtils;
import bFM.Settings;

@SuppressWarnings("serial")
public class FileListPanel extends JScrollPane
{
	static final int SCROLL_STRENGTH = 10;
	static final double MIN_SCROLL_PERCENT = .01;
	static JTextField filter;
	JPanel panel = new JPanel();
	FileList file;
	public FileListPanel()
	{
		super(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		getVerticalScrollBar().setUnitIncrement(Settings.assetHeight*SCROLL_STRENGTH);
		setBackground(null);
		initializePanel();
		setViewportView(panel);
	}
	private void initializePanel()
	{
		panel.setLayout(new BorderLayout());
		filter = GUIUtils.createStringTextField("", this::filterFiles);
	}
	private void filterFiles(String filter)
	{
		file.filterFiles(filter);
	}
	private void repackPanel()
	{
		panel.removeAll();
		panel.add(filter, BorderLayout.NORTH);
		if(file!=null)panel.add(file, BorderLayout.CENTER);
	}
	public void setFile(FileList file) 
	{
		this.file = file;
		repackPanel();
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
	public void update() 
	{
		repackPanel();
		if(getViewport()==null||getViewport().getView()==null) return;
		int scroll = getVerticalScrollBar().getValue();
		getViewport().setSize(getWidth(), getHeight());
		if(file!=null) file.update();
		int finalS = Math.min(getVerticalScrollBar().getMaximum(), scroll);
		SwingUtilities.invokeLater(() -> {
			getVerticalScrollBar().setValue(finalS);
			getVerticalScrollBar().setUnitIncrement(Math.min(Settings.assetHeight*SCROLL_STRENGTH,Math.floorDiv((int)(getHeight()*MIN_SCROLL_PERCENT), Settings.assetHeight)*Settings.assetHeight));
		});
	}
	public void deselectAll() 
	{
		int scroll = getVerticalScrollBar().getValue();
		file.deselectAll();
		getVerticalScrollBar().setValue(Math.min(getVerticalScrollBar().getMaximum(), scroll));
	}
	public byte[] getFile() 
	{
		return file.getBytes();
	}
}
