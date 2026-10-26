package GUI.FileInfo.CharacterDataBase;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.ArrayList;

import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import GUI.CollapseablePanel;
import GUI.LabeledInputBox;
import GUI.FileInfo.GenericFileInfoGUI;
import ResourceManagers.CharacterDatabaseManager.JobChangePriceList;
import ResourceManagers.CharacterDatabaseManager.JobChangePriceList.JobPrices;
import bFM.GUIUtils;
import bFM.Settings;

@SuppressWarnings("serial")
public class JobChangePriceListInfoGUI extends GenericFileInfoGUI
{
	JTextField index;
	JobChangePriceList parent;
	JobPrices object;
	CollapseablePanel pricesPanel;
	ArrayList<Price> prices;
	public JobChangePriceListInfoGUI(JobPrices object, JobChangePriceList parent) 
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
	}
	private void makePanelGUI()
	{
		pricesPanel.removeAll();
		for(int i = 0; i < object.getPriceAmount(); i++)
		{
			Price price = new Price(object.getPrice(i), object.getJobCode());
			prices.add(price);
			pricesPanel.add(new LabeledInputBox("Price to Become " + object.getNameByIndex(i), price.getLabel()));
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
	}
	private record Price(int price, int jobCode)
	{
		private LabeledInputBox getLabel()
		{
			return null;
		}
	}
}
