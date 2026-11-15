package GUI.FileList.Resources.CharacterDB.JobPrices;

import javax.swing.JMenuItem;
import GUI.GUI;
import GUI.FileInfo.CharacterDataBase.JopChangePriceInfoGUI;
import GUI.FileList.CollapseableFileList;
import GUI.FileList.Resources.CharacterDB.CharacterDataBaseFileList;
import ResourceManagers.CharacterDatabaseManager.JobPrice.JobChangePriceList;
import ResourceManagers.CharacterDatabaseManager.JobPrice.Job;

public class JobPriceChangeFileList extends CollapseableFileList
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -5734614937896634051L;
	JobChangePriceList file;
	CharacterDataBaseFileList parent;
	public JobPriceChangeFileList(JobChangePriceList file, int padding, CharacterDataBaseFileList parent) 
	{
		this.file = file;
		this.padding = padding;
		super.file = file;
		this.parent = parent;
		initializeAll();
	}
	protected void initializeAll()
	{
		initializeListGUI("Job Price Manager");
		initializeSubGUI();
		addActions();
		reAddComponents();
	}
	protected void addActions()
	{
		addNewJobAction();
		addMouseListener();
		add(actions);
		update();
	}
	private void addNewJobAction() 
	{
		JMenuItem replace = new JMenuItem("Create New Job");
		replace.addActionListener(e -> 
		{
			Job job = new Job(file, -1, file.getJobs().size()+1);
			file.registerJob(job);
			subEntries.add(new JobChangePriceListGUI(job, this));
			reAddComponents();
			GUI.update();
		});
		actions.add(replace);
	}
	protected void initializeInfoGUI() 
	{
		infoGUI = new JopChangePriceInfoGUI(file);
	}
	public void initializeSubGUI()  
	{
		for(Job object : file.getJobs())
		{
			subEntries.add(new JobChangePriceListGUI(object, this));
		}
	}
	public void removeFile(JobChangePriceListGUI file) 
	{
		remove(file);
		subEntries.remove(file);
		this.file.unregisterJob(file.getFile());
	}
}