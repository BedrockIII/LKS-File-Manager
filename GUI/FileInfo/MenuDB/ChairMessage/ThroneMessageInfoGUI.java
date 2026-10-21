package GUI.FileInfo.MenuDB.ChairMessage;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JTextArea;
import GUI.BitFlagPanel;
import GUI.LabeledInputBox;
import GUI.FileInfo.GenericFileInfoGUI;
import SystemDataManagers.MenuDB.ChairMessage.ChairMessage;
import bFM.GUIUtils;
import bFM.Settings;

@SuppressWarnings("serial")
public class ThroneMessageInfoGUI extends GenericFileInfoGUI
{
	ChairMessage mes;
	JTextArea Text;
	BitFlagPanel ActivationFlag;
	public ThroneMessageInfoGUI(ChairMessage mes) 
	{
		this.mes = mes;
		makeGUI();
		addGUI();
	}
	private void makeGUI()
	{
		Text = GUIUtils.createNameTextArea(mes.getText(), mes::setText);
		ActivationFlag = new BitFlagPanel("Activation Flag", mes.getFlag(), mes::setFlag);
	}
	private void addGUI()
	{
		removeAll();
		setLayout(new GridBagLayout());
		GridBagConstraints layout = Settings.getDefaultConstraints();
		add(new LabeledInputBox("Message: ", Text), layout);
		layout.weighty = 1.0;
		add(ActivationFlag, layout);
	}
}
