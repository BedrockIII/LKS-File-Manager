package GUI.FileInfo.CharacterDataBase;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.ArrayList;

import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import GUI.LabeledInputBox;
import GUI.FileInfo.GenericFileInfoGUI;
import ResourceManagers.CharacterDatabaseManager.JobChangePriceList.JobPrices;

@SuppressWarnings("serial")
public class JobChangePriceListInfoGUI extends GenericFileInfoGUI
{
	JobPrices object;
	ArrayList<LabeledInputBox> prices = new ArrayList<LabeledInputBox>();
	public JobChangePriceListInfoGUI(JobPrices object) 
	{
		this.object = object;
		makeGUI();
		addGUI();
	}
	private void makeGUI()
	{
		 prices = new ArrayList<LabeledInputBox>();
		for(int i = 0; i < object.getPriceAmount(); i++)
		{
			PriceText text = new PriceText("" + object.getPrice(i));
			text.index = i;
			text.getDocument().addDocumentListener(new DocumentListener() 
			{
				public void insertUpdate(DocumentEvent e) 
				{
					object.setPrice(text.index, bFM.Utils.strToInt(text.getText()));
				}
				public void removeUpdate(DocumentEvent e) 
				{
					object.setPrice(text.index, bFM.Utils.strToInt(text.getText()));
				}
				public void changedUpdate(DocumentEvent e) {}
			});
			prices.add(new LabeledInputBox("Price to Become " + object.getNameByIndex(i),  text, 1.5));
		}
	}
	private void addGUI()
	{
		removeAll();
		setLayout(new GridBagLayout());
		GridBagConstraints layout = new GridBagConstraints();
		layout.anchor = GridBagConstraints.NORTHWEST;
		layout.gridwidth = GridBagConstraints.REMAINDER;
		layout.weighty = 0.0;
		layout.weightx = 1.0;
		for(int i = 0; i < object.getPriceAmount()-1; i++)
		{
			add(prices.get(i), layout);
		}
		layout.weighty = 1.0;
		layout.weightx = 1.0;
		add(prices.get(object.getPriceAmount()-1), layout);
	}
	public void update() 
	{
		if(object.getPriceAmount() != prices.size()) makeGUI();
		for(int i = 0; i < object.getPriceAmount(); i++)
		{
			prices.get(i).replaceText("Price to Become " + object.getNameByIndex(i));
			PriceText text = new PriceText("" + object.getPrice(i));
			text.index = i;
			text.getDocument().addDocumentListener(new DocumentListener() 
			{
				public void insertUpdate(DocumentEvent e) 
				{
					object.setPrice(text.index, bFM.Utils.strToInt(text.getText()));
				}
				public void removeUpdate(DocumentEvent e) 
				{
					object.setPrice(text.index, bFM.Utils.strToInt(text.getText()));
				}
				public void changedUpdate(DocumentEvent e) {}
			});
			prices.get(i).replaceComponent(text);
		}
		addGUI();
	}
	private class PriceText extends JTextField
	{
		public PriceText(String text) 
		{
			super(text);
		}
		int index;
	}
}
