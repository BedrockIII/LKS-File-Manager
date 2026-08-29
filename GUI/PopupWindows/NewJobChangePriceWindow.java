package GUI.PopupWindows;

import javax.swing.JTextField;

import GUI.LabeledInputBox;
import GUI.FileList.Resources.CharacterDataBaseList.CharacterJobPriceChangeList;

@SuppressWarnings("serial")
public class NewJobChangePriceWindow extends GenericPopupWindow
{
	CharacterJobPriceChangeList gui;
	JTextField newTitle;
	JTextField newTitle2;
	public NewJobChangePriceWindow(CharacterJobPriceChangeList gui)
	{
		super("Create Job Prices");
		this.gui = gui;
	}
	protected void addGUI()
	{
		newTitle = new JTextField("-1");
		add(new LabeledInputBox("New Job Code", newTitle));
		
		newTitle2 = new JTextField("-1");
		add(new LabeledInputBox("New Job Price", newTitle2));
	}
	protected void execute()
	{
		gui.createJob(newTitle.getText(), newTitle2.getText());
	}
}