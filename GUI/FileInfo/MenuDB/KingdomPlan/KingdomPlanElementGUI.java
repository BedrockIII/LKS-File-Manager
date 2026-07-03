package GUI.FileInfo.MenuDB.KingdomPlan;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JTextField;
import GUI.LabeledInputBox;
import GUI.FileInfo.GenericFileInfoGUI;
import SystemDataManagers.MenuDB.KingdomPlanManager.KingdomPlanElement;
import bFM.Settings;

@SuppressWarnings("serial")
public class KingdomPlanElementGUI extends GenericFileInfoGUI
{
	KingdomPlanElement Element = null;
	JTextField Name = null;
	JTextField Description = null;
	JTextField Image = null;
	JTextField Price = null;
	JTextField ActivationFlag = null;
	JTextField AltActivationFlag = null;
	JTextField flag5 = null;
	JTextField flag6 = null;
	JTextField flag7 = null;
	JTextField flag8 = null;
	JTextField PopulationMinimum = null;
	JTextField PrereqPurchaseFlag = null;
	JTextField PurchaseFlag = null;
	JTextField flag12 = null;
	JTextField flag13 = null;
	JTextField flag14 = null;
	JTextField cockpitLogCode = null;
	JTextField flag16 = null;
	GridBagConstraints layout;
	public KingdomPlanElementGUI(KingdomPlanElement element) 
	{
		setLayout(new GridBagLayout());
		
		Element = element;
		
		makeGUI();
		addGUI();
	}
	private void addGUI() 
	{
		layout = Settings.getDefaultConstraints();
		removeAll();
		add(new LabeledInputBox("Element Name: ", Name), layout);
		add(new LabeledInputBox("Element Description: ", Description), layout);
		add(new LabeledInputBox("Element Image Name: ", Image), layout);
		add(new LabeledInputBox("Price: ", Price), layout);
		add(new LabeledInputBox("Activation Flag: ", ActivationFlag), layout);
		add(new LabeledInputBox("Alternate Activation Flag: ", AltActivationFlag), layout);
		add(new LabeledInputBox("Unknown Number 1: ", flag5), layout);
		add(new LabeledInputBox("Unknown Number 2: ", flag6), layout);
		add(new LabeledInputBox("Unknown Number 3: ", flag7), layout);
		add(new LabeledInputBox("Unknown Number 4: ", flag8), layout);
		add(new LabeledInputBox("Population Minimum: ", PopulationMinimum), layout);
		add(new LabeledInputBox("Previous Plan Flag: ", PrereqPurchaseFlag), layout);
		add(new LabeledInputBox("Purchase Flag: ", PurchaseFlag), layout);
		add(new LabeledInputBox("Special Variable Type: ", flag12), layout);
		add(new LabeledInputBox("Special Variable 1: ", flag13), layout);
		add(new LabeledInputBox("Special Variable 2: ", flag14), layout);
		add(new LabeledInputBox("Cockpit Log Code: ", cockpitLogCode), layout);
		layout.weighty = 1.0;
		add(new LabeledInputBox("Unknown Number 8: ", flag16), layout);
	}
	private void makeGUI()
	{
		Name = bFM.Utils.createFormattedTextField(Element.getName(), Element::setName);
		Description = bFM.Utils.createFormattedTextField(Element.getDescription(), Element::setDescription);
		Image = bFM.Utils.createFormattedTextField(Element.getImage(), Element::setImage);
		Price = bFM.Utils.createIntTextField(Element.getPrice(), Element::setPrice);
		ActivationFlag = bFM.Utils.createIntTextField(Element.getActivationFlag(), Element::setActivationFlag);
		AltActivationFlag = bFM.Utils.createIntTextField(Element.getAltActivationFlag(), Element::setAltActivationFlag);
		flag5 = bFM.Utils.createIntTextField(Element.getFlag5(), Element::setFlag5);
		flag6 = bFM.Utils.createIntTextField(Element.getFlag6(), Element::setFlag6);
		flag7 = bFM.Utils.createIntTextField(Element.getFlag7(), Element::setFlag7);
		flag8 = bFM.Utils.createIntTextField(Element.getFlag8(), Element::setFlag8);
		PopulationMinimum = bFM.Utils.createIntTextField(Element.getPopulationMinimum(), Element::setPopulationMinimum);
		PrereqPurchaseFlag = bFM.Utils.createIntTextField(Element.getPrereqPurchaseFlag(), Element::setPrereqPurchaseFlag);
		PurchaseFlag = bFM.Utils.createIntTextField(Element.getPurchaseFlag(), Element::setPurchaseFlag);
		flag12 = bFM.Utils.createIntTextField(Element.getSpecialVariableType(), Element::setSpecialVariableType);
		flag13 = bFM.Utils.createIntTextField(Element.getSpecialVar1(), Element::setSpecialVar1);
		flag14 = bFM.Utils.createIntTextField(Element.getSpecialVar2(), Element::setSpecialVar2);
		cockpitLogCode = bFM.Utils.createIntTextField(Element.getCockpitLogCode(), Element::setCockpitLogCode);
		flag16 = bFM.Utils.createIntTextField(Element.getFlag16(), Element::setFlag16);
	}
}
