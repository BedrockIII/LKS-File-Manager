package GUI.FileList.Resources.CharacterDB.JobPrices;

import javax.swing.JMenuItem;
import javax.swing.filechooser.FileNameExtensionFilter;

import GUI.GUI;
import GUI.FileInfo.CharacterDataBase.JopChangePriceInfoGUI;
import GUI.FileList.CollapseableFileList;
import GUI.FileList.Resources.CharacterDB.CharacterDataBaseFileList;
import GUI.PopupWindows.NewJobChangePriceWindow;
import ResourceManagers.CharacterDatabaseManager.JobChangePriceList;
import ResourceManagers.CharacterDatabaseManager.JobChangePriceList.JobPrices;

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
		fileTypes = new FileNameExtensionFilter("List File", "lst");
		initializeListGUI(padding);
		//initializeInfoGUI();
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
			new NewJobChangePriceWindow(this);
			GUI.update();
		});
		actions.add(replace);
	}
	protected void initializeInfoGUI() 
	{
		this.infoGUI = new JopChangePriceInfoGUI((JobChangePriceList) file);
	}
	public void initializeSubGUI()  
	{
		for(JobPrices object : file.getObjects())
		{
			subEntries.add(new JobChangePriceListGUI(object, this));
		}
	}
	public void removeFile(JobChangePriceListGUI file) 
	{
		remove(file);
		subEntries.remove(file);
		((JobChangePriceList)(this.file)).removePrice(file.getFile());
	}
	public void createJob(String code, String price) 
	{
		((JobChangePriceList)file).addPrice(bFM.Utils.strToInt(price));
		((JobChangePriceList)file).addJob(bFM.Utils.strToInt(code));;
		subEntries.add(new JobChangePriceListGUI(((JobChangePriceList)file).getLastObject(), this));
		reAddComponents();
		GUI.update();
	}
}