package GUI.FileList;

import GUI.FileInfo.EventInfoGUI;
import VMC.VMCConverter;

@SuppressWarnings("serial")
public class EventListGUI extends FileList
{
	VMCConverter vmc;
	int padding;
	public EventListGUI(VMCConverter file, int padding)
	{
		vmc = file;
		this.padding = padding;
		initializeAll(padding);
	}
	protected void initializeAll(int padding)
	{
		initializeListGUI(padding, vmc.getName());
		addActions();
	}
	protected void initializeInfoGUI()
	{
		infoGUI = new EventInfoGUI(vmc);
	}
	protected void addActions()
	{
		addMouseListener();
	}
	public void update()
	{
		fileName.setText(vmc.getName());
		super.update();
	}
}
