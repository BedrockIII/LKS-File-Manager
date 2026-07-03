package GUI.FileInfo.MenuDB.KingdomPlan;

import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import javax.swing.JTextField;
import GUI.LabeledInputBox;
import GUI.FileInfo.GenericFileInfoGUI;
import SystemDataManagers.MenuDB.KingdomPlanManager.KingdomPlanArea;
import bFM.Settings;

@SuppressWarnings("serial")
public class KingdomPlanAreaGUI extends GenericFileInfoGUI
{
	GridBagConstraints layout = new GridBagConstraints();
	JTextField Name = null;
	JTextField Description = null;
	JTextField Image = null;
	JLabel ElementCount;
	KingdomPlanArea area = null;
	public KingdomPlanAreaGUI(KingdomPlanArea area) 
	{
		setLayout(new GridBagLayout());
		this.area = area;
		makeGUI();
		addGUI();
	}
	private void makeGUI() 
	{
		Name = bFM.Utils.createFormattedTextField(area.getName(), area::setName);
		Description = bFM.Utils.createFormattedTextField(area.getDescription(), area::setDescription);
		Image = bFM.Utils.createFormattedTextField(area.getImage(), area::setImage);
		ElementCount = new JLabel("" + area.getElements().size());
	}
	private void addGUI()
	{
		removeAll();
		layout = Settings.getDefaultConstraints();
		add(new LabeledInputBox("Area Name: ", Name), layout);
		add(new LabeledInputBox("Area Description: ", Description), layout);
		add(new LabeledInputBox("Area Image Name: ", Image), layout);
		layout.weighty = 1.0;
		add(new LabeledInputBox("Element Count: ", ElementCount), layout);
	}
	public void update() 
	{
		ElementCount.setText("" + area.getElements().size());
		for(Component c : getComponents())
		{
			if(c instanceof LabeledInputBox)
			{
				((LabeledInputBox) c).update();
			}
		}
	}
}
