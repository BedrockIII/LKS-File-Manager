package GUI.FileInfo.CharacterDataBase;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import javax.swing.JTextField;
import GUI.CollapseablePanel;
import GUI.LabeledInputBox;
import GUI.FileInfo.GenericFileInfoGUI;
import ResourceManagers.CharacterDatabaseManager.JobPrice.JobChangePriceList;
import ResourceManagers.CharacterDatabaseManager.JobPrice.Job;
import bFM.GUIUtils;
import bFM.Settings;

@SuppressWarnings("serial")
public class JobChangePriceListInfoGUI extends GenericFileInfoGUI
{
	JTextField index;
	JobChangePriceList parent;
	Job object;
	CollapseablePanel pricesPanel;
	public JobChangePriceListInfoGUI(Job object, JobChangePriceList parent) 
	{
		this.object = object;
		this.parent = parent;
		makeGUI();
		addGUI();
	}
	private void makeGUI()
	{
		index = GUIUtils.createUpdatingIntTextField(object.getJobCode(), object::setJobCode);
		pricesPanel = new CollapseablePanel("Prices");
		makePanelGUI();
		pricesPanel.isExtended(true);
	}
	private void makePanelGUI()
	{
		pricesPanel.removeAll();
		for(int i = 0; i < object.getPrices().size(); i++)
		{
			pricesPanel.add(new LabeledInputBox("Price to Become " + parent.parent.getNameByCode(object.getPrices().get(i).getJob().getJobCode()), 
					GUIUtils.createIntTextField(object.getPrices().get(i).getPrice(), 
							object.getPrices().get(i)::setPrice)));
		}
	}
	private void addGUI()
	{
		removeAll();
		setLayout(new GridBagLayout());
		GridBagConstraints layout = Settings.getDefaultConstraints();
		add(new LabeledInputBox("Job Code", index), layout);
		layout.weighty = 1.0;
		add(pricesPanel, layout);
	}
	public void update() 
	{
		makePanelGUI();
		super.update();
	}
}
