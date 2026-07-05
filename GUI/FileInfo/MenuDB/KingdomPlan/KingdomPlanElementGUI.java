package GUI.FileInfo.MenuDB.KingdomPlan;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComboBox;
import javax.swing.JTextField;

import GUI.CollapseablePanel;
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
	CollapseablePanel ProgramInfo;
	JComboBox<String> flag12 = null;
	JTextField flag13 = null;
	JTextField flag14 = null;
	LabeledInputBox var1;
	LabeledInputBox var2;
	JTextField cockpitLogCode = null;
	JTextField flag16 = null;
	GridBagConstraints layout;
	final static String[] ProgramTypes = {"No Special Action","Flower","Develop Area","Reinforcement Pot","Increase HP Modifier",
			"Increase Attack Modifier","Change Job","Change Job (Man)","Change Job (Woman)","Grant Item","Add Citizen (Removed)",
			"Increase Badge Count"};
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
		add(new LabeledInputBox("Purchase Flag: ", PurchaseFlag), layout);
		
		CollapseablePanel Flags = new CollapseablePanel("Activation Requirements");
		CollapseablePanel OrFlags = new CollapseablePanel("Logical OR Activation Flags");
		CollapseablePanel AndFlags = new CollapseablePanel("Logical AND Activation Flags");
		Flags.add(new LabeledInputBox("Exclusive Activation Flag : ", PrereqPurchaseFlag));
		Flags.add(new LabeledInputBox("Population Minimum: ", PopulationMinimum));
		Flags.add(OrFlags);
		Flags.add(AndFlags);
		OrFlags.add(new LabeledInputBox("Or Activation Flag 1: ", ActivationFlag));
		OrFlags.add(new LabeledInputBox("Or Activation Flag 2: ", AltActivationFlag));
		OrFlags.add(new LabeledInputBox("Or Activation Flag 3: ", flag5));
		OrFlags.add(new LabeledInputBox("Or Activation Flag 4: ", flag6));
		AndFlags.add(new LabeledInputBox("And Activation Flag 1: ", flag7));
		AndFlags.add(new LabeledInputBox("And Activation Flag 2: ", flag8));
		Flags.isExtended(true);
		add(Flags, layout);
		
		ProgramInfo = new CollapseablePanel("Program Execution Info");
		ProgramInfo.add(new LabeledInputBox("Program Type: ", flag12));
		var1 = new LabeledInputBox("Special Variable 1: ", flag13);
		var2 = new LabeledInputBox("Special Variable 2: ", flag14);
		ProgramInfo.add(var1);
		ProgramInfo.add(var2);
		add(ProgramInfo, layout);
		updateProgramInfo();
		
		add(new LabeledInputBox("Cockpit Log Code: ", cockpitLogCode), layout);
		layout.weighty = 1.0;
		add(new LabeledInputBox("Unknown Number 8: ", flag16), layout);
	}
	private void makeGUI()
	{
		Name = bFM.Utils.createNameTextField(Element.getName(), Element::setName);
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
		flag12 = new JComboBox<String>(ProgramTypes);
		flag12.setSelectedIndex(Element.getSpecialVariableType());
		flag12.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				updateProgramInfo();
			}
			
		});
		flag13 = bFM.Utils.createIntTextField(Element.getSpecialVar1(), Element::setSpecialVar1);
		flag14 = bFM.Utils.createIntTextField(Element.getSpecialVar2(), Element::setSpecialVar2);
		cockpitLogCode = bFM.Utils.createIntTextField(Element.getCockpitLogCode(), Element::setCockpitLogCode);
		flag16 = bFM.Utils.createIntTextField(Element.getFlag16(), Element::setFlag16);
	}
	private void updateProgramInfo()
	{
		int progType = flag12.getSelectedIndex();
		Element.setSpecialVariableType(progType);
		ProgramInfo.remove(var1);
		ProgramInfo.remove(var2);
		switch (progType)
		{
		case 0://NOP
			Element.setSpecialVar1(-1);
			Element.setSpecialVar2(-1);
			break;
		case 1: // 1 Kingdom Plan -> Flower [--]
			Element.setSpecialVar1(-1);
			var1.replaceText("Flower Code");
			ProgramInfo.add(var1);
			break;
		case 2:// 2 Kingdom Plan -> Development addValue[SpecialVar2], cmpValue[SpecialVar1]
			var2.replaceText("addValue");
			ProgramInfo.add(var2);
			var1.replaceText("cmpValue");
			ProgramInfo.add(var1);
			break;
		case 3:// 3 Kingdom Plan -> Reinforcement Pot [SpecialVar2]
			var2.replaceText("Change in Pots");
			ProgramInfo.add(var2);
			var1.replaceText("Total Pots");
			ProgramInfo.add(var1);
			break;
		case 4:// 4 Kingdom Plan -> HP +[SpecialVar2]"
			var2.replaceText("Change in HP");
			ProgramInfo.add(var2);
			var1.replaceText("Starting Value");
			ProgramInfo.add(var1);
			break;
		case 5:// 5 Kingdom Plan -> AT +[SpecialVar2]"
			var2.replaceText("Attack Modifier Change");
			ProgramInfo.add(var2);
			var1.replaceText("Starting Value");
			ProgramInfo.add(var1);
			break;
		case 6:// 6 Kingdom Plan -> Job Change [SpecialVar1] -> [SpecialVar2]"
			var1.replaceText("Starting Job Code");
			ProgramInfo.add(var1);
			var2.replaceText("New Job Code");
			ProgramInfo.add(var2);
			break;
		case 7:// 7 Kingdom Plan -> Job Change Man [SpecialVar1] -> [SpecialVar2]"
			var1.replaceText("Starting Job Code");
			ProgramInfo.add(var1);
			var2.replaceText("New Job Code");
			ProgramInfo.add(var2);
			break;
		case 8:// 8 Kingdom Plan -> Job Change (Female) [SpecialVar1] -> [SpecialVar2]"
			var1.replaceText("Starting Job Code");
			ProgramInfo.add(var1);
			var2.replaceText("New Job Code");
			ProgramInfo.add(var2);
			break;
		case 9:// 9 Kingdom Plan -> Item Obtained [SpecialVar2]\n"
			var1.replaceText("Amount Granted");
			ProgramInfo.add(var1);
			var2.replaceText("Item Code");
			ProgramInfo.add(var2);
			break;
		case 10:// 10 removed NOP
			var1.replaceText("New Job Code");
			ProgramInfo.add(var1);
			var2.replaceText("Amount");
			ProgramInfo.add(var2);
			break;
		case 11:// 11 Kingdom Plan -> Badges Gained [SpecialVar2]"
			Element.setSpecialVar1(-1);
			var2.replaceText("Badge Change");
			ProgramInfo.add(var2);
			break;
		}
		revalidate();
		update();
	}
}
