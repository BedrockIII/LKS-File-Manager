package GUI.FileList.SystemData.MonsterBook;

import GUI.FileList.FileList;
import SystemDataManagers.MenuDB.Books.MonsterBook.MonsterMod;

@SuppressWarnings("serial")
public class MonsterHPListGUI extends FileList
{
	MonsterHPManagerListGUI parent;
	MonsterMod hp;
	public MonsterHPListGUI(MonsterMod hp, int padding, MonsterHPManagerListGUI parent)
	{
		this.parent = parent;
		this.padding = padding;
		this.hp = hp;
		file = hp;
		initializeAll();
	}
	protected void initializeAll()
	{
		initializeListGUI("\"" + hp.getName() + "\"");
		addActions();
	}
	protected void initializeInfoGUI()
	{
		//infoGUI = new MapAreaInfoGUI(hp);
	}
	protected void addActions()
	{
		// TODO Add Delete Action
		add(actions);
		addMouseListener();
	}
	public void update()
	{
		fileName.setText("\"" + hp.getName() + "\"");
		super.update();
	}
}
