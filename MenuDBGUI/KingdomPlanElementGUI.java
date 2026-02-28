package MenuDBGUI;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import GUI.GUI;
import GUI.LabeledInputBox;
import SystemDataManagers.KingdomPlanElement;

@SuppressWarnings("serial")
public class KingdomPlanElementGUI extends JPanel
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
	KingdomPlanAreaGUI parent;
	GridBagConstraints layout = new GridBagConstraints();
	public KingdomPlanElementGUI(KingdomPlanElement element, KingdomPlanAreaGUI parent) 
	{
		setLayout(new GridBagLayout());
		layout.anchor = GridBagConstraints.NORTHWEST;
		layout.fill = GridBagConstraints.HORIZONTAL;
		layout.weightx = 1.0;
		layout.weighty = 1.0;
		layout.gridwidth = GridBagConstraints.REMAINDER;
		
		this.parent =parent;
		Element = element;
		
		makeGUI();
		addGUI();
	}
	private void addGUI() 
	{
		removeAll();
		add(new LabeledInputBox("Element Name: ", Name, 1.5), layout);
		add(new LabeledInputBox("Element Description: ", Description, 1.5), layout);
		add(new LabeledInputBox("Element Image Name: ", Image, 1.5), layout);
		add(new LabeledInputBox("Price: ", Price, 1.5), layout);
		add(new LabeledInputBox("Activation Flag: ", ActivationFlag, 1.5), layout);
		add(new LabeledInputBox("Alternate Activation Flag: ", AltActivationFlag, 1.5), layout);
		add(new LabeledInputBox("Unknown Number 1: ", flag5, 1.5), layout);
		add(new LabeledInputBox("Unknown Number 2: ", flag6, 1.5), layout);
		add(new LabeledInputBox("Unknown Number 3: ", flag7, 1.5), layout);
		add(new LabeledInputBox("Unknown Number 4: ", flag8, 1.5), layout);
		add(new LabeledInputBox("Population Minimum: ", PopulationMinimum, 1.5), layout);
		add(new LabeledInputBox("Previous Plan Flag: ", PrereqPurchaseFlag, 1.5), layout);
		add(new LabeledInputBox("Purchase Flag: ", PurchaseFlag, 1.5), layout);
		add(new LabeledInputBox("Unknown Number 5: ", flag12, 1.5), layout);
		add(new LabeledInputBox("Unknown Number 6: ", flag13, 1.5), layout);
		add(new LabeledInputBox("Unknown Number 7: ", flag14, 1.5), layout);
		add(new LabeledInputBox("Cockpit Log Code: ", cockpitLogCode, 1.5), layout);
		add(new LabeledInputBox("Unknown Number 8: ", flag16, 1.5), layout);
	}
	private void makeGUI()
	{
		Name = new JTextField(Element.getName());
		Name.getDocument().addDocumentListener(new DocumentListener() 
		{
			public void insertUpdate(DocumentEvent e) 
			{
				updateName();
			}
			public void removeUpdate(DocumentEvent e) 
			{
				updateName();
			}
			public void changedUpdate(DocumentEvent e) {}
		});

		Description = new JTextField(Element.getDescription());
		Description.getDocument().addDocumentListener(new DocumentListener() 
		{
			public void insertUpdate(DocumentEvent e) 
			{
				Element.setDescription(bFM.Utils.formatStringChars(Description.getText()));
			}
			public void removeUpdate(DocumentEvent e) 
			{
				Element.setDescription(bFM.Utils.formatStringChars(Description.getText()));
			}
			public void changedUpdate(DocumentEvent e) {}
		});
		
		Image = new JTextField(Element.getImage());
		Image.getDocument().addDocumentListener(new DocumentListener() 
		{
			public void insertUpdate(DocumentEvent e) 
			{
				Element.setImage(bFM.Utils.formatStringChars(Image.getText()));
			}
			public void removeUpdate(DocumentEvent e) 
			{
				Element.setImage(bFM.Utils.formatStringChars(Image.getText()));
			}
			public void changedUpdate(DocumentEvent e) {}
		});
		
		Price = new JTextField("" + Element.getPrice());
		Price.getDocument().addDocumentListener(new DocumentListener() 
		{
			public void insertUpdate(DocumentEvent e) 
			{
				Element.setPrice(bFM.Utils.strToInt(Price.getText()));
			}
			public void removeUpdate(DocumentEvent e) 
			{
				Element.setPrice(bFM.Utils.strToInt(Price.getText()));
			}
			public void changedUpdate(DocumentEvent e) {}
		});
		
		ActivationFlag = new JTextField("" + Element.getActivationFlag());
		ActivationFlag.getDocument().addDocumentListener(new DocumentListener() 
		{
			public void insertUpdate(DocumentEvent e) 
			{
				Element.setActivationFlag(bFM.Utils.strToInt(ActivationFlag.getText()));
			}
			public void removeUpdate(DocumentEvent e) 
			{
				Element.setActivationFlag(bFM.Utils.strToInt(ActivationFlag.getText()));
			}
			public void changedUpdate(DocumentEvent e) {}
		});
		
		AltActivationFlag = new JTextField("" + Element.getAltActivationFlag());
		AltActivationFlag.getDocument().addDocumentListener(new DocumentListener() 
		{
			public void insertUpdate(DocumentEvent e) 
			{
				Element.setAltActivationFlag(bFM.Utils.strToInt(AltActivationFlag.getText()));
			}
			public void removeUpdate(DocumentEvent e) 
			{
				Element.setAltActivationFlag(bFM.Utils.strToInt(AltActivationFlag.getText()));
			}
			public void changedUpdate(DocumentEvent e) {}
		});
		
		flag5 = new JTextField("" + Element.getFlag5());
		flag5.getDocument().addDocumentListener(new DocumentListener() 
		{
			public void insertUpdate(DocumentEvent e) 
			{
				Element.setFlag5(bFM.Utils.strToInt(flag5.getText()));
			}
			public void removeUpdate(DocumentEvent e) 
			{
				Element.setFlag5(bFM.Utils.strToInt(flag5.getText()));
			}
			public void changedUpdate(DocumentEvent e) {}
		});
		
		flag6 = new JTextField("" + Element.getFlag6());
		flag6.getDocument().addDocumentListener(new DocumentListener() 
		{
			public void insertUpdate(DocumentEvent e) 
			{
				Element.setFlag6(bFM.Utils.strToInt(flag6.getText()));
			}
			public void removeUpdate(DocumentEvent e) 
			{
				Element.setFlag6(bFM.Utils.strToInt(flag6.getText()));
			}
			public void changedUpdate(DocumentEvent e) {}
		});
		
		flag7 = new JTextField("" + Element.getFlag7());
		flag7.getDocument().addDocumentListener(new DocumentListener() 
		{
			public void insertUpdate(DocumentEvent e) 
			{
				Element.setFlag7(bFM.Utils.strToInt(flag7.getText()));
			}
			public void removeUpdate(DocumentEvent e) 
			{
				Element.setFlag7(bFM.Utils.strToInt(flag7.getText()));
			}
			public void changedUpdate(DocumentEvent e) {}
		});
		
		flag8 = new JTextField("" + Element.getFlag8());
		flag8.getDocument().addDocumentListener(new DocumentListener() 
		{
			public void insertUpdate(DocumentEvent e) 
			{
				Element.setFlag8(bFM.Utils.strToInt(flag8.getText()));
			}
			public void removeUpdate(DocumentEvent e) 
			{
				Element.setFlag8(bFM.Utils.strToInt(flag8.getText()));
			}
			public void changedUpdate(DocumentEvent e) {}
		});
		
		PopulationMinimum = new JTextField("" + Element.getPopulationMinimum());
		PopulationMinimum.getDocument().addDocumentListener(new DocumentListener() 
		{
			public void insertUpdate(DocumentEvent e) 
			{
				Element.setPopulationMinimum(bFM.Utils.strToInt(PopulationMinimum.getText()));
			}
			public void removeUpdate(DocumentEvent e) 
			{
				Element.setPopulationMinimum(bFM.Utils.strToInt(PopulationMinimum.getText()));
			}
			public void changedUpdate(DocumentEvent e) {}
		});
		
		PrereqPurchaseFlag = new JTextField("" + Element.getPrereqPurchaseFlag());
		PrereqPurchaseFlag.getDocument().addDocumentListener(new DocumentListener() 
		{
			public void insertUpdate(DocumentEvent e) 
			{
				Element.setPrereqPurchaseFlag(bFM.Utils.strToInt(PrereqPurchaseFlag.getText()));
			}
			public void removeUpdate(DocumentEvent e) 
			{
				Element.setPrereqPurchaseFlag(bFM.Utils.strToInt(PrereqPurchaseFlag.getText()));
			}
			public void changedUpdate(DocumentEvent e) {}
		});
		
		PurchaseFlag = new JTextField("" + Element.getPurchaseFlag());
		PurchaseFlag.getDocument().addDocumentListener(new DocumentListener() 
		{
			public void insertUpdate(DocumentEvent e) 
			{
				Element.setPurchaseFlag(bFM.Utils.strToInt(PurchaseFlag.getText()));
			}
			public void removeUpdate(DocumentEvent e) 
			{
				Element.setPurchaseFlag(bFM.Utils.strToInt(PurchaseFlag.getText()));
			}
			public void changedUpdate(DocumentEvent e) {}
		});
		
		flag12 = new JTextField("" + Element.getFlag12());
		flag12.getDocument().addDocumentListener(new DocumentListener() 
		{
			public void insertUpdate(DocumentEvent e) 
			{
				Element.setFlag12(bFM.Utils.strToInt(flag12.getText()));
			}
			public void removeUpdate(DocumentEvent e) 
			{
				Element.setFlag12(bFM.Utils.strToInt(flag12.getText()));
			}
			public void changedUpdate(DocumentEvent e) {}
		});
		
		flag13 = new JTextField("" + Element.getFlag13());
		flag13.getDocument().addDocumentListener(new DocumentListener() 
		{
			public void insertUpdate(DocumentEvent e) 
			{
				Element.setFlag13(bFM.Utils.strToInt(flag13.getText()));
			}
			public void removeUpdate(DocumentEvent e) 
			{
				Element.setFlag13(bFM.Utils.strToInt(flag13.getText()));
			}
			public void changedUpdate(DocumentEvent e) {}
		});
		
		flag14 = new JTextField("" + Element.getFlag14());
		flag14.getDocument().addDocumentListener(new DocumentListener() 
		{
			public void insertUpdate(DocumentEvent e) 
			{
				Element.setFlag14(bFM.Utils.strToInt(flag14.getText()));
			}
			public void removeUpdate(DocumentEvent e) 
			{
				Element.setFlag14(bFM.Utils.strToInt(flag14.getText()));
			}
			public void changedUpdate(DocumentEvent e) {}
		});
		
		cockpitLogCode = new JTextField("" + Element.getCockpitLogCode());
		cockpitLogCode.getDocument().addDocumentListener(new DocumentListener() 
		{
			public void insertUpdate(DocumentEvent e) 
			{
				Element.setCockpitLogCode(bFM.Utils.strToInt(cockpitLogCode.getText()));
			}
			public void removeUpdate(DocumentEvent e) 
			{
				Element.setCockpitLogCode(bFM.Utils.strToInt(cockpitLogCode.getText()));
			}
			public void changedUpdate(DocumentEvent e) {}
		});
		
		flag16 = new JTextField("" + Element.getFlag16());
		flag16.getDocument().addDocumentListener(new DocumentListener() 
		{
			public void insertUpdate(DocumentEvent e) 
			{
				Element.setFlag16(bFM.Utils.strToInt(flag16.getText()));
			}
			public void removeUpdate(DocumentEvent e) 
			{
				Element.setFlag16(bFM.Utils.strToInt(flag16.getText()));
			}
			public void changedUpdate(DocumentEvent e) {}
		});
	}
	public String toString()
	{
		if(Element==null) return "Unknown Name";
		return Element.getName();
	}
	public void saveChanges()
	{
		Element.setName(Name.getText());
	}
	protected void updateName()
	{
		if(Name!=null)
		{
			
			setName(bFM.Utils.formatStringChars(Name.getText()));
			Element.setName(bFM.Utils.formatStringChars(Name.getText()));
			parent.updateList();
		}
		else 
		{
			System.out.println("Null Name");
		}
	}
	public void update() 
	{
		setPreferredSize(new Dimension((int) GUI.getRightSize().getWidth(), getHeight()));
		for(Component c : getComponents())
		{
			if(c instanceof LabeledInputBox)
			{
				((LabeledInputBox)c).update();
			}
		}
	}
}
