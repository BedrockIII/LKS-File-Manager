package GUI.PopupWindows;

import javax.swing.JTextField;

import GUI.LabeledInputBox;
import GUI.FileList.Resources.CharacterDataBaseList.CharacterJobPriceChangeList.JobChangePriceListGUI;

@SuppressWarnings("serial")
public class RetypeJobWindow extends GenericPopupWindow
{
	JobChangePriceListGUI gui;
	JTextField newTitle;
	public RetypeJobWindow(JobChangePriceListGUI gui)
	{
		super("Change Job");  
		this.gui = gui;
	}
	protected void addGUI()
	{
		newTitle = new JTextField("-1"); 
        add(new LabeledInputBox("New Job Code", newTitle));
	}
	protected void execute()
	{
		gui.setCode(bFM.Utils.strToInt(newTitle.getText()));
	}
}