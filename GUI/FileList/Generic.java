package GUI.FileList;

import GUI.GUI;
import GUI.FileInfo.FileInfoFactory;
import PCKGManager.PCKGManager;
import bFM.Data;
import bFM.OpenedFile;
import bFM.Settings;

@SuppressWarnings("serial")
public class Generic extends FileList
{
	protected Generic()
	{
	}
	public Generic(PCKGManager pac, int padding, int index)
	{
		file = pac.getPackedFile(index);
		initializeAll(padding);
	}
	public Generic(String name, byte[] data, int padding)
	{
		file = OpenedFile.makeFile(name, data);
		initializeAll(padding);
	}
	public Generic(OpenedFile file, int padding)
	{
		this.file = file;
		initializeAll(padding);
	}
	public Generic(String name, int padding) 
	{
		file = OpenedFile.makeFile(name, new byte[0]);
		initializeAll(padding);
	}
	protected void initializeAll()
	{
		initializeAll(0);
	}
	protected void initializeAll(int padding)
	{
		initializeListGUI(padding);
		initializeInfoGUI();
		addExportAction();
		addRenameAction();
		addReplaceButton();
		addDeleteAction();
		addActions();
		add(actions);
	}
	protected void initializeListGUI(int padding)
	{
		initializeListGUI(padding, file.getName());
	}
	protected void initializeInfoGUI()
	{
		infoGUI = FileInfoFactory.makeInfoGUI(file);
	}
	protected void addActions()
	{
		addRenameAction();
		addDeleteAction();
		addMouseListener();
		add(actions);
		update();
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
		repaint();
		infoGUI.update();
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
