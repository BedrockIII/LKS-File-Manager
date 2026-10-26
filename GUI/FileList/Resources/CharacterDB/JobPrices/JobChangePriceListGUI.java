package GUI.FileList.Resources.CharacterDB.JobPrices;

import javax.swing.JMenuItem;

import GUI.GUI;
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
		infoGUI = new JobChangePriceListInfoGUI((JobPrices) object);
	}
	protected void addActions()
	{
		//addChangeJobAction();
		addDeleteAction();
		addMouseListener();
		add(actions);
		update();
	}
	protected void addDeleteAction()
	{
		JMenuItem replace = new JMenuItem("Delete Job");
		replace.addActionListener(e -> 
		{
			((JobPriceChangeFileList)getParent()).removeFile(this);
			GUI.update();
		});
		actions.add(replace);
	}
	public void setCode(int num) 
	{
		object.setCode(num);
		//fileName.setText(((CharacterDataBaseManager)(parent.file)).getNameByCode(num) + " (" + num + ")");
	}
	public void update()
	{
		object.updateCode();
		fileName.setText(((parent.parent.file)).getNameByCode(object.getJobCode()) + " (" + object.getJobCode() + ")");
	}
}