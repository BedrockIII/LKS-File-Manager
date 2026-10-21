package GUI.FileInfo.MenuDB.NPCMemo;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import javax.swing.JLabel;

import GUI.LabeledInputBox;
import GUI.FileInfo.GenericFileInfoGUI;
import SystemDataManagers.MenuDB.Unused.NPCMemo.NPCMemoManager;
import bFM.Settings;

@SuppressWarnings("serial")
public class NPCMemoManagerInfoGUI extends GenericFileInfoGUI
{
	NPCMemoManager manager;
	JLabel ManA;
	JLabel ManM;
	JLabel WomanA;
	JLabel WomanM;
	JLabel King;
	public NPCMemoManagerInfoGUI(NPCMemoManager manager)
	{
		this.manager = manager;
		makeGUI();
		addGUI();
	}
	private void makeGUI()
	{
		ManA = new JLabel("" + manager.getManActionManager().getEntries().size());
		WomanA = new JLabel("" + manager.getWomanActionManager().getEntries().size());
		ManM = new JLabel("" + manager.getManMemoryManager().getEntries().size());
		WomanM = new JLabel("" + manager.getWomanMemoryManager().getEntries().size());
		King = new JLabel("" + manager.getLevelManager().getEntries().size());
	}
	private void addGUI()
	{
		removeAll();
		setLayout(new GridBagLayout());
		GridBagConstraints layout = Settings.getDefaultConstraints();
		add(new LabeledInputBox("Men Actions: ", ManA), layout);
		add(new LabeledInputBox("Women Actions: ", WomanA), layout);
		add(new LabeledInputBox("Men Memories: ", ManM), layout);
		add(new LabeledInputBox("Women Memories: ", WomanM), layout);
		layout.weighty = 1.0;
		add(new LabeledInputBox("King Level Count: ", King), layout);
	}
	public void update() 
	{
		ManA.setText("" + manager.getManActionManager().getEntries().size());
		WomanA.setText("" + manager.getWomanActionManager().getEntries().size());
		ManM.setText("" + manager.getManMemoryManager().getEntries().size());
		WomanM.setText("" + manager.getWomanMemoryManager().getEntries().size());
		King.setText("" + manager.getLevelManager().getEntries().size());
		super.update();
	}
}
