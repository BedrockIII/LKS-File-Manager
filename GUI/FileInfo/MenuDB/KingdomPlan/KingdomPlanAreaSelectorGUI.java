package GUI.FileInfo.MenuDB.KingdomPlan;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;

import javax.swing.Box;
import javax.swing.JComboBox;
import javax.swing.JLabel;

import GUI.LabeledInputBox;
import GUI.FileInfo.GenericFileInfoGUI;
import SystemDataManagers.KingdomPlanManager.kingdomPlanManager;
import bFM.Settings;
import SystemDataManagers.KingdomPlanManager.KingdomPlanArea;

@SuppressWarnings("serial")
public class KingdomPlanAreaSelectorGUI extends GenericFileInfoGUI
{
	kingdomPlanManager plans = null;
	JLabel numAreas;
	ArrayList<KingdomPlanArea> Areas = new ArrayList<KingdomPlanArea>();
	public KingdomPlanAreaSelectorGUI(kingdomPlanManager file) 
	{
		plans = file;
		makeGUI();
		addGUI();
	}
	private void makeGUI()
	{
		numAreas = new JLabel("" + plans.getAreas().size());
	}
	private void addGUI()
	{
		removeAll();
		setLayout(new GridBagLayout());
		GridBagConstraints layout = Settings.getDefaultConstraints();
		layout.weighty = 1.0;
		add(new LabeledInputBox("Area Count: ", numAreas), layout);
	}
	public void update() 
	{
		numAreas.setText("" + plans.getAreas().size());
		for(Component c : getComponents())
		{
			if(c instanceof LabeledInputBox)
			{
				((LabeledInputBox) c).update();
			}
		}
	}
}
