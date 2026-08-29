package GUI.FileList;

import javax.swing.JMenuItem;

import GUI.GUI;
import GUI.FileInfo.FileInfoFactory;
import PCKGManager.PCKGManager;
import bFM.Data;
import bFM.GUIUtils;
import bFM.OpenedFile;
import bFM.Settings;

@SuppressWarnings("serial")
public class Generic extends FileList
{
	CollapseableFileList parent = null;
	int padding = 0;
	protected Generic()
	{
	}
	public Generic(PCKGManager pac, int padding, int index)
	{
		file = pac.getPackedFile(index);
		this.padding = padding;
		initializeAll(padding);
	}
	public Generic(String name, byte[] data, int padding)
	{
		file = OpenedFile.makeFile(name, data);
		initializeAll(padding);
	}
	public Generic(OpenedFile file, int padding, CollapseableFileList parent)
	{
		this.file = file;
		this.parent = parent;
		this.padding = padding;
		initializeAll(padding);
	}
	public Generic(String name, int padding, CollapseableFileList parent) 
	{
		file = OpenedFile.makeFile(name, new byte[0]);
		this.padding = padding;
		this.parent = parent;
		initializeAll(padding);
	}
	protected void initializeAll()
	{
		initializeAll(0);
	}
	protected void initializeAll(int padding)
	{
		this.padding = padding;
		initializeListGUI();
		initializeInfoGUI();
		addActions();
		add(actions);
	}
	protected void initializeListGUI()
	{
		initializeListGUI(padding, file.getName());
	}
	protected void initializeInfoGUI()
	{
		infoGUI = FileInfoFactory.makeInfoGUI(file);
	}
	protected void addActions()
	{
		addExportAction();
		addReplaceButton();
		addDeleteAction();
		addMouseListener();
		add(actions);
		update();
	}
	protected void addDeleteAction()
	{
		if(parent==null || padding == 0) return;
		JMenuItem replace = new JMenuItem("Delete File");
		replace.addActionListener(e -> 
		{
			parent.removeFile(this);
			GUI.update();
		});
		actions.add(replace);
	}
	protected void addReplaceButton()
	{
		actions.add(GUIUtils.createReplaceAction("Replace With Raw Data", "Generic File", null, file::setData, file::setName, parent));
	}
	protected void select()
	{
		GUI.deselectAll();
    	setBackground(Settings.selectedColor);
    	GUI.setFileInfo(infoGUI);
	}
	public int getHeight()
	{
		return Settings.assetHeight;
	}
	public byte[] getBytes() 
	{
		System.out.println(file.getName());
		if(infoGUI==null) return new byte[0];
		return infoGUI.getBytes();
	}
	public void deselect()
	{
		setBackground(Settings.bgColor);
	}
	public void update() 
	{
		fileName.setText(file.getName());
		super.update();
	}
	public void deselectAll() 
	{
		deselect();
	}
	public void setName(String name)
	{
		fileName.setText(name);
		file.setName(name);
		GUI.update();
	}
	public Data getFile() 
	{
		return file;
	}
}
