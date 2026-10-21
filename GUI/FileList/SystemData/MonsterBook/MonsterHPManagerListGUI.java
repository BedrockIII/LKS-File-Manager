package GUI.FileList.SystemData.MonsterBook;

import java.util.ArrayList;

import GUI.FileInfo.MenuDB.MonsterBook.MonsterHPManagerInfoGUI;
import GUI.FileList.CollapseableFileList;
import SystemDataManagers.MenuDB.Books.MonsterBook.MonsterMod;

@SuppressWarnings("serial")
public class MonsterHPManagerListGUI extends CollapseableFileList
{
	ArrayList<MonsterMod> manager;
	public MonsterHPManagerListGUI(ArrayList<MonsterMod> manager, int padding) 
	{
		this.manager = manager;
		this.padding = padding;
		initializeAll();
	}
	protected void initializeAll() 
	{
		initializeListGUI("Monster Mod Log Data");
		initializeSubGUI();
		addActions();
		reAddComponents();
	}
	public void initializeSubGUI()
	{
		subEntries.removeAll(subEntries);
		for(MonsterMod a : manager)
		{
			subEntries.add(new MonsterHPListGUI(a, this));
		}
		
	}
	protected void initializeInfoGUI()
	{
		infoGUI = new MonsterHPManagerInfoGUI(manager);
	}
	protected void addActions() 
	{
		addChildAction("Add Monster Mod Log Name", manager, MonsterMod::new, MonsterHPListGUI::new);
		add(actions);
		addMouseListener();
	}
}
