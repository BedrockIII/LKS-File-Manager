package GUI.FileInfo.MenuDB.KingdomPlan;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;

import javax.swing.Box;
import javax.swing.JComboBox;
import javax.swing.JLabel;

import GUI.FileInfo.GenericFileInfoGUI;
import SystemDataManagers.KingdomPlanManager.kingdomPlanManager;
import SystemDataManagers.KingdomPlanManager.KingdomPlanArea;

@SuppressWarnings("serial")
public class KingdomPlanAreaSelectorGUI extends GenericFileInfoGUI
{
	kingdomPlanManager plans = null;
	ArrayList<KingdomPlanArea> Areas = new ArrayList<KingdomPlanArea>();
	ArrayList<KingdomPlanAreaGUI> AreaGUIs = new ArrayList<KingdomPlanAreaGUI>();
	JComboBox<KingdomPlanAreaGUI> AreaList = new JComboBox<KingdomPlanAreaGUI>();
	private void initializeGUI()
	{
		removeAll();
		setPreferredSize(GUI.GUI.getRightSize());
		setLayout(new GridBagLayout());
		GridBagConstraints layout = new GridBagConstraints();
		layout.anchor = GridBagConstraints.NORTHWEST;
		layout.fill = GridBagConstraints.HORIZONTAL;
		layout.weightx = 1.0;
		layout.weighty = 0.0;
		layout.gridwidth = GridBagConstraints.REMAINDER;
		//System.out.println(plans);
		//System.out.println(plans.getAreas());
		Areas = plans.getAreas();
		AreaList = new JComboBox<KingdomPlanAreaGUI>();
		
		for(int i = 0; i < Areas.size(); i++)
		{
			KingdomPlanArea area = Areas.get(i);
			AreaList.addItem(new KingdomPlanAreaGUI(area, this));
		}
		//AreaList.setPreferredSize(new Dimension((int)(GUI.GUI.assetHeight*1.5), 100));
		AreaList.addItemListener(new ItemListener()
		{
			public void itemStateChanged(ItemEvent e) 
			{
				  if (e.getStateChange() == ItemEvent.SELECTED) 
				  {
					  update();
			      }
			}
			
		});
		add(AreaList, layout);
		if(AreaList.getSelectedItem() != null) add((KingdomPlanAreaGUI) AreaList.getSelectedItem(), layout);
		else add(new JLabel("No Kingdom Plan Areas Found"), layout);
		layout.weighty = 1.0;
		add(Box.createVerticalGlue(), layout);
		layout.weighty = 0.0;
	}
	public KingdomPlanAreaSelectorGUI(kingdomPlanManager file) 
	{
		plans = file;
		initializeGUI();
	}
	//have a dropdown of all areas and then an area panel
	public void updateList() 
	{
		AreaList.repaint();
	}
	public byte[] getKPData() 
	{
		return plans.toString().getBytes();
	}
	public void update() 
	{
		GridBagConstraints layout = new GridBagConstraints();
		layout.anchor = GridBagConstraints.NORTHWEST;
		layout.fill = GridBagConstraints.HORIZONTAL;
		layout.weightx = 1.0;
		layout.weighty = 0.0;
		layout.gridwidth = GridBagConstraints.REMAINDER;
		//System.out.println(plans);
		removeAll();
		add(AreaList, layout);
		add((KingdomPlanAreaGUI) AreaList.getSelectedItem(), layout);
		layout.weighty = 1.0;
		add(Box.createVerticalGlue(), layout);
		layout.weighty = 0.0;
		setPreferredSize(new Dimension(GUI.GUI.getRightSize().width-10, getHeight()));
		for(KingdomPlanAreaGUI area : AreaGUIs)
		{
			area.update();;
		}
		//file.setData(getBytes());
		repaint();
	}
	public byte[] toBytes() 
	{
		return plans.toBytes();
	}
	public void replaceData(byte[] data) 
	{
		plans = new kingdomPlanManager(data);
		initializeGUI();
	}
}
