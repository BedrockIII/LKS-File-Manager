package GUI.FileList;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

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
		//setBackground(null);
		initializePanel();
		setViewportView(panel);
	}
	private void initializePanel()
	{
		panel.setLayout(new GridBagLayout());
		filter = GUIUtils.createStringTextField("", this::filterFiles);
	}
	private void filterFiles(String filter)
	{
		if(filter.length() <= 0 ) return;
		file.filterAddFiles(filter);
		GUI.update();
	}
	private void repackPanel()
	{
		GridBagConstraints layout = Settings.getDefaultConstraints();
		panel.removeAll();
		if(file==null) layout.weighty = 1.0;
		panel.add(filter, layout);
		layout.weighty = 1.0;
		if(file!=null)panel.add(file, layout);
	}
	public void setFile(FileList file) 
	{
		this.file = file;
		repackPanel();
		setViewportView(panel);
		//GUI.update();
		update();
	}
	public int getHeight()
	{
		int ret = filter.getHeight();
		if(file==null)
		{
			return ret;
		}
		int fileHeight = Math.max(file.getHeight(),0);
		return ret + fileHeight;
	}
	public void update() 
	{
		//repackPanel();
		if(file==null) return;
		int scroll = getVerticalScrollBar().getValue();
		//getViewport().setSize(getWidth(), getHeight());
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
