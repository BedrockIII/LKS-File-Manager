package GUI.FileList.SystemData.MonsterBook;

import GUI.FileInfo.MenuDB.MonsterBook.MonsterHPInfoGUI;
import GUI.FileList.FileList;
import SystemDataManagers.MenuDB.Books.MonsterBook.MonsterMod;
import bFM.Settings;

@SuppressWarnings("serial")
public class MonsterHPListGUI extends FileList
{
	MonsterHPManagerListGUI parent;
	MonsterMod hp;
	public MonsterHPListGUI(MonsterMod hp, MonsterHPManagerListGUI parent)
	{
		padding = parent.getPadding() + Settings.indentSize;
		this.hp = hp;
		super.file = hp;
		this.parent = parent;
		initializeAll();
	}
	protected void initializeAll()
	{
		initializeListGUI("\"" + hp.getName() + "\"");
		addActions();
	}
	protected void initializeInfoGUI()
	{
		infoGUI = new MonsterHPInfoGUI(hp);
	}
	protected void addActions()
	{
		addDeleteAction("Delete Monster Mod Log Name", parent, parent.manager);
		addMoveUpAction(parent, parent.manager);
		addMoveDownAction(parent, parent.manager);
		add(actions);
		addMouseListener();
	}
	public void update()
	{
		fileName.setText("\"" + hp.getName() + "\"");
		super.update();
	}
}
