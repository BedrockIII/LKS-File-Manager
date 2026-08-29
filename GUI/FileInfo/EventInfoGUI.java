package GUI.FileInfo;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.ComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JTextField;
import GUI.CollapseablePanel;
import GUI.LabeledInputBox;
import GUI.SplitPanel;
import VMC.VMCConverter;
import VMC.VMCDecompiler;
import VMC.VMString;
import VMC.vmInstruction;
import VMC.vmInstruction.vmAddress;
import VMC.vmInstruction.vmCall;
import VMC.vmInstruction.vmCompare;
import VMC.vmInstruction.vmCompare.ComparisonTypes;
import VMC.vmInstruction.vmCompareJump;
import VMC.vmInstruction.vmExternal;
import VMC.vmInstruction.vmJump;
import VMC.vmInstruction.vmLabel;
import VMC.vmInstruction.vmLoad;
import VMC.vmInstruction.vmPrint;
import VMC.vmInstruction.vmPush;
import bFM.GUIUtils;

@SuppressWarnings("serial")
public class EventInfoGUI extends GenericFileInfoGUI
{
	VMCConverter vmc;
	JTextField name;
	JTextField eventNumber;
	CollapseablePanel Code;
	CollapseablePanel Strings;
	ArrayList<vmLabel> labels = new ArrayList<vmLabel>();
	ArrayList<VirtualInstructionGUI> instructions = new ArrayList<VirtualInstructionGUI>();
	public EventInfoGUI(VMCConverter vmc) 
	{
		this.vmc = vmc;
		makeGUI();
		addGUI();
		update();
	}
	private void makeGUI()
	{
		name = GUIUtils.createNameTextField(vmc.getName(), vmc::setName);
		eventNumber = GUIUtils.createIntTextField(vmc.getNum(), vmc::setNum);
		makeCodeGUI();
		makeStringGUI();
		//updateJumpLabels();
	}
	private void makeCodeGUI()
	{
		Code = new CollapseablePanel("Raw Event Code");
		ArrayList<vmInstruction> instructions = VMCDecompiler.addLabels(vmc);
		for(vmInstruction instruction : instructions)
		{
			VirtualInstructionGUI i = new VirtualInstructionGUI(instruction);
			this.instructions.add(i);
			Code.add(i);
		}
	}
	private void updateJumpLabels()
	{
		for(VirtualInstructionGUI i : instructions)
		{
			switch(i.code.getInstructionType())
			{
			case vmInstruction.CODE_LABEL:
			case vmInstruction.CODE_LOAD:
			case vmInstruction.CODE_ADDRESS:
			case vmInstruction.CODE_PUSH:
			case vmInstruction.CODE_POP:
			case vmInstruction.CODE_ASSIGN:
			case vmInstruction.CODE_ADD:
			case vmInstruction.CODE_SUBTRACT:
			case vmInstruction.CODE_MULTIPLY:
			case vmInstruction.CODE_DIVIDE:
			case vmInstruction.CODE_MODULUS:
			case vmInstruction.CODE_INVERT:
			case vmInstruction.CODE_COMPARE:
				break;
			case vmInstruction.CODE_JUMP:
			case vmInstruction.CODE_COMPARE_JUMP:
				//JComboBox<vmLabel> labels = new JComboBox<vmLabel>((this.labels);
				//labels.setSelectedItem(i.code.getLabel());
				//i.replaceComponent(labels);
				break;
			case vmInstruction.CODE_CALL:
				
				break;
			case vmInstruction.CODE_RETURN:
			case vmInstruction.CODE_PRINT:
			case vmInstruction.CODE_EXTENSION:
			case vmInstruction.CODE_HALT:
			case vmInstruction.CODE_SUSPEND:
				break;
			default:
				throw new IllegalArgumentException("Invalid VM Op Code: " + i.code.getInstructionType());
			}
		}
	}
	private void makeStringGUI()
	{
		Strings = new CollapseablePanel("Reference Strings");
		ArrayList<VMString> strings = vmc.getStrings();
		for(VMString string : strings)
		{
			Strings.add(new SplitPanel(new JLabel(""), new JLabel(string.toString())));
		}
	}
	private void addGUI()
	{
		removeAll();
		setLayout(new GridBagLayout());
		GridBagConstraints layout = new GridBagConstraints();
		layout.anchor = GridBagConstraints.NORTHWEST;
		layout.gridwidth = GridBagConstraints.REMAINDER;
		layout.fill = GridBagConstraints.HORIZONTAL;
		layout.weightx = 1.0;
		add(new LabeledInputBox("File Name: ",  name), layout);
		add(new LabeledInputBox("Event Unknown Number: ",  eventNumber), layout);
		add(Code, layout);
		layout.weighty = 1.0;
		add(Strings, layout);
		layout.weighty = 0.0;
	}
	private class VirtualInstructionGUI extends LabeledInputBox
	{
		vmInstruction code;
		private VirtualInstructionGUI(vmInstruction i)
		{
			super("VM Instruction");
			code = i;
			initializeCodeGUI();
		}
		private void initializeCodeGUI()
		{
			SplitPanel vals = new SplitPanel();
			switch(code.getInstructionType())
			{
			case vmInstruction.CODE_LABEL:
				initializeLabel();
				return;
			case vmInstruction.CODE_LOAD:
				replaceText("Load");
				vals = new SplitPanel();
				vals.setLeftComponent(new JLabel("" + ((vmLoad)code).getValue()));
				vals.setRightComponent(new JLabel("" + ((vmLoad)code).getMode()));
				replaceComponent(vals);
				return;
			case vmInstruction.CODE_ADDRESS:
				replaceText("Address");
				vals = new SplitPanel();
				vals.setLeftComponent(new JLabel("" + ((vmAddress)code).getValue()));
				vals.setRightComponent(new JLabel("" + ((vmAddress)code).getMode()));
				replaceComponent(vals);
				return;
			case vmInstruction.CODE_PUSH:
				initializePush();
				return;
			case vmInstruction.CODE_POP:
				replaceText("Pop");
				replaceComponent(new JLabel(""));
				return;
			case vmInstruction.CODE_ASSIGN:
				replaceText("Assign");
				replaceComponent(new JLabel(""));
				return;
			case vmInstruction.CODE_ADD:
				replaceText("Add");
				replaceComponent(new JLabel(""));
				return;
			case vmInstruction.CODE_SUBTRACT:
				replaceText("Subtract");
				replaceComponent(new JLabel(""));
				return;
			case vmInstruction.CODE_MULTIPLY:
				replaceText("Multiply");
				replaceComponent(new JLabel(""));
				return;
			case vmInstruction.CODE_DIVIDE:
				replaceText("Divide");
				replaceComponent(new JLabel(""));
				return;
			case vmInstruction.CODE_MODULUS:
				replaceText("Modulus");
				replaceComponent(new JLabel(""));
				return;
			case vmInstruction.CODE_INVERT:
				replaceText("Invert");
				replaceComponent(new JLabel(""));
				return;
			case vmInstruction.CODE_COMPARE:
				initializeCompare();
				return;
			case vmInstruction.CODE_JUMP:
				initializeJump();
				return;
			case vmInstruction.CODE_COMPARE_JUMP:
				initializeConditionalJump();
				return;
			case vmInstruction.CODE_CALL:
				initializeCall();
				return;
			case vmInstruction.CODE_RETURN:
				replaceText("Return");
				replaceComponent(new JLabel(""));
				return;
			case vmInstruction.CODE_PRINT:
				replaceText("Print");
				replaceComponent(new JLabel("" + ((vmPrint)code).getValue()));
				return;
			case vmInstruction.CODE_EXTENSION:
				replaceText("Extension Function");
				replaceComponent(new JLabel("" + ((vmExternal)code).getValue()));
				return;
			case vmInstruction.CODE_HALT:
				replaceText("Halt");
				replaceComponent(new JLabel(""));
				return;
			case vmInstruction.CODE_SUSPEND:
				replaceText("Suspend");
				replaceComponent(new JLabel(""));
				return;
			default:
				throw new IllegalArgumentException("Invalid VM Op Code: " + code.getInstructionType());
			}
		}
		private void initializePush()
		{
			SplitPanel vals = new SplitPanel();
			replaceText("Push");
			vals = new SplitPanel();
			if(((vmPush)code).getMode()==vmInstruction.instructionCodeFloat)
			{
				vals.setLeftComponent(GUIUtils.createFloatTextField(((vmPush)code).getValueF(), ((vmPush)code)::setValue));
			}
			else if(((vmPush)code).getMode()==vmInstruction.instructionCodeString && ((vmPush)code).getValueS() != null)
			{
				vals.setLeftComponent(GUIUtils.createStringTextField(((vmPush)code).getValueS(), ((vmPush)code)::setValue));
			}
			else vals.setLeftComponent(GUIUtils.createIntTextField(((vmPush)code).getValueI(), ((vmPush)code)::setValue));
			vals.setRightComponent(new JLabel("" + ((vmPush)code).getMode()));
			replaceComponent(vals);
		}
		private void initializeCompare()
		{
			replaceText("Compare");
			ComparisonTypes[] options = {ComparisonTypes.LESS_THAN, ComparisonTypes.LESS_THAN_EQUAL, 
				ComparisonTypes.GREATER_THAN, ComparisonTypes.GREATER_THAN_EQUAL, ComparisonTypes.EQUAL, 
				ComparisonTypes.NOT_EQUAL, ComparisonTypes.BOOLEAN_AND, ComparisonTypes.BOOLEAN_OR};
			JComboBox<ComparisonTypes> ComparisonTypes = new JComboBox<ComparisonTypes>(options);
			ComparisonTypes.addActionListener(new ActionListener()
			{
				public void actionPerformed(ActionEvent e)
				{
					((vmCompare)code).setValue(((ComparisonTypes)ComparisonTypes.getSelectedItem()).id());
				}
			});
			ComparisonTypes.setSelectedIndex(((vmCompare)code).getValue());
			replaceComponent(ComparisonTypes);
		}
		private void initializeLabel()
		{
			replaceText("Label");
			replaceComponent(GUIUtils.createStringTextField(code.getName(), code::setName));
			labels.add(((vmLabel)code));
		}
		private void initializeJump()
		{
			replaceText("Jump");
			replaceComponent(((vmJump)code).getLabel().asLabel());
		}
		private void initializeConditionalJump()
		{
			replaceText("Conditional Jump");
			replaceComponent(((vmCompareJump)code).getLabel().asLabel());
		}
		private void initializeCall()
		{
			SplitPanel vals = new SplitPanel();
			replaceText("Call");
			vals = new SplitPanel();
			vals.setLeftComponent(((vmCall)code).getLabel().asLabel());
			vals.setRightComponent(new JLabel("" + ((vmCall)code).getMode()));
			replaceComponent(vals);
		}
	}
}