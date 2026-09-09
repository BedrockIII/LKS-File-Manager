package GUI.FileList.Resources;

import GUI.FileInfo.MissionDataBase.AttackCollisionInfoGUI;
import GUI.FileInfo.MissionDataBase.MobAttackInfoGUI;
import GUI.FileList.CollapseableFileList;
import GUI.FileList.FileList;
import ResourceManagers.MSDBManager.Definition.MissionObjectDefinitionManager;
import ResourceManagers.MSDBManager.Definition.MobAttackCol;
import ResourceManagers.MSDBManager.Definition.MobAttackList;
import ResourceManagers.MSDBManager.Definition.MobAttackList.MobAttack;
import bFM.Settings;

@SuppressWarnings("serial")
public class MODefinitionListGUI extends CollapseableFileList
{
	MissionObjectDatabase parent;
	MissionObjectDefinitionManager Definitions;
	
	
	//Things
	MobAttacksListGUI Attacks;
	
	int padding = 0;
	public MODefinitionListGUI(MissionObjectDefinitionManager file, int padding, MissionObjectDatabase parent) 
	{
		this.parent = parent;
		this.file = file;
		Definitions = file;
		this.padding = padding;
		initializeAll(padding);
	}
	@Override
	public void initializeSubGUI()
	{
		// TODO Auto-generated method stub
		//Res
		//    Mod
		//AI
		//DMG
		subEntries.removeAll(subEntries);
		Attacks = new MobAttacksListGUI(Definitions.getAttacks(), padding + Settings.indentSize, this);
		subEntries.add(Attacks);
	}
	@Override
	protected void initializeAll(int padding)
	{
		initializeListGUI(padding, "Mission Object Definitions");
		initializeSubGUI();
		addActions();
		reAddComponents();
	}
	protected void initializeInfoGUI()
	{
		// TODO Auto-generated method stub
		
	}
	protected void addActions()
	{
		addExpandAllAction();
		addCollapseAllAction();
		add(actions);
		addMouseListener();
	}
	@SuppressWarnings("unused")
	private class MobAttacksListGUI extends CollapseableFileList
	{
		/**
		 * 
		 */
		private static final long serialVersionUID = 5033204471168833590L;
		MODefinitionListGUI parent;
		MobAttackList Attacks;
		int padding = 0;
		public MobAttacksListGUI(MobAttackList file, int padding,
				MODefinitionListGUI parent)
		{
			this.parent = parent;
			this.file = file;
			Attacks = file;
			this.padding = padding;
			initializeAll(padding);
		}
		public void initializeSubGUI()
		{
			for(MobAttack Attack : Attacks.getAttacks())
			{
				subEntries.add(new MobAttackListGUI(Attack, padding + Settings.indentSize, this));
				//System.out.print(".");
			}
		}
		protected void initializeAll(int padding)
		{
			initializeListGUI(padding, "Mission Object Attacks");
			initializeSubGUI();
			addActions();
			reAddComponents();
		}
		protected void initializeInfoGUI()
		{
			// TODO Auto-generated method stub
			
		}
		protected void addActions()
		{
			addExpandAllAction();
			addCollapseAllAction();
			add(actions);
			addMouseListener();
		}
		private class MobAttackListGUI extends CollapseableFileList
		{
			/**
			 * 
			 */
			private static final long serialVersionUID = -6230692718491981233L;
			MobAttacksListGUI parent;
			int padding = 0;
			
			MobAttack Attack;
			
			public MobAttackListGUI(MobAttack file, int padding, MobAttacksListGUI parent)
			{
				this.parent = parent;
				this.file = file;
				Attack = file;
				this.padding = padding;
				initializeAll(padding);
			}
			public void initializeSubGUI()
			{
				for(MobAttackCol AttackCol : Attack.getCollisions())
				{
					subEntries.add(new MobAttackColListGUI(AttackCol, padding + Settings.indentSize, this));
					//System.out.print(".");
				}
			}
			protected void initializeAll(int padding)
			{
				initializeListGUI(padding, "Attack: " + Attack.getName());
				initializeSubGUI();
				addActions();
				reAddComponents();
			}
			protected void initializeInfoGUI()
			{
				infoGUI = new MobAttackInfoGUI(Attack);
			}
			protected void addActions()
			{
				add(actions);
				addMouseListener();
			}
			public void update()
			{
				fileName.setText("Attack: " + Attack.getName());
				super.update();
			}
			private class MobAttackColListGUI extends FileList
			{
				MobAttackCol Collision;
				MobAttackListGUI parent;
				int padding = 0;
				public MobAttackColListGUI(MobAttackCol file, int padding, MobAttackListGUI parent)
				{
					this.parent = parent;
					this.file = file;
					Collision = file;
					this.padding = padding;
					initializeAll(padding);
				}
				protected void initializeAll(int padding)
				{
					initializeListGUI(padding, "Bone: " + Collision.getName());
					addActions();
				}
				protected void initializeInfoGUI()
				{
					infoGUI = new AttackCollisionInfoGUI(Collision);
				}
				protected void addActions()
				{
					add(actions);
					addMouseListener();
				}
				public void update()
				{
					fileName.setText("Bone: " + Collision.getName());
					super.update();
				}
			}
		}
	}
}
