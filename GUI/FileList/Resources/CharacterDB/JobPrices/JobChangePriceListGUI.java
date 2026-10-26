package GUI.FileList.Resources.CharacterDB.JobPrices;

import javax.swing.JMenuItem;

import GUI.FileInfo.CharacterDataBase.JobChangePriceListInfoGUI;
import GUI.FileList.FileList;
import ResourceManagers.CharacterDatabaseManager.JobChangePriceList.JobPrices;
import bFM.Settings;

public class JobChangePriceListGUI extends FileList
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1278940279786761889L;
	JobPriceChangeFileList parent;
	JobPrices object;
	public JobChangePriceListGUI(JobPrices object, JobPriceChangeFileList parent) 
	{
		this.parent = parent;
		this.object = object;
		file = object;
		padding = Settings.indentSize + parent.getPadding();
		this.initializeAll();
	}
	protected void initializeAll()
	{
		initializeListGUI(object.getName());
		addActions();
	}
	public JobPrices getFile()
	{
		return object;
	}
	protected void initializeInfoGUI() 
	{
		infoGUI = new JobChangePriceListInfoGUI(object, parent.file);
	}
	protected void addActions()
	{
		addDeleteAction();
		addMouseListener();
		add(actions);
	}
	protected void addDeleteAction()
	{
		JMenuItem replace = new JMenuItem("Delete Job");
		replace.addActionListener(e -> 
		{
			parent.removeFile(this);
			parent.update();
		});
		actions.add(replace);
	}
	public void update()
	{
		fileName.setText(((parent.parent.file)).getNameByCode(object.getJobCode()) + " (" + object.getJobCode() + ")");
		if(infoGUI!= null)infoGUI.update();
	}
}