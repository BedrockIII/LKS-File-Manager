package GUI.FileList;

import java.util.ArrayList;

import javax.swing.JMenuItem;
import javax.swing.filechooser.FileNameExtensionFilter;

import GUI.GUI;
import GUI.FileInfo.ItemDataBase.ItemDataBaseInfoGUI;
import GUI.FileInfo.ItemDataBase.ItemInfoGUI;
import GUI.FileInfo.ItemDataBase.ItemPlacementInfoGUI;
import GUI.FileInfo.ItemDataBase.ItemSoundEffectInfoGUI;
import GUI.FileInfo.ItemDataBase.ItemWeaponInfoGUI;
import GUI.PopupWindows.NewItemPopup;
import ResourceManagers.ItemDatabaseManager.Item;
import ResourceManagers.ItemDatabaseManager.Placement;
import ResourceManagers.ItemDatabaseManager.itemDatabaseManager;
import bFM.Settings;
import bFM.Utils;

@SuppressWarnings("serial")
public class ItemDatabaseList extends CollapseableFileList
{
	private int padding = 0;
	private ArrayList<Item> items = new ArrayList<Item>();
	public ItemDatabaseList(itemDatabaseManager file, int padding) 
	{
		this.padding = padding;
		this.file = file;
		initializeAll(padding);
	}
	protected void initializeAll(int padding) 
	{
		System.out.print("Opening Item DB Package File: █");
		fileTypes = new FileNameExtensionFilter("Item DB Package File", "pac");
		initializeListGUI(padding, "Item Database Manager");
		System.out.print("█");
		initializeSubGUI();
		System.out.print("█");
		initializeInfoGUI();
		System.out.print("█");
		addActions();
		System.out.print("█");
		reAddComponents();
		System.out.println("█\nComplete!");
	}
	public void initializeSubGUI() 
	{
		subEntries.removeAll(subEntries);
		items = ((itemDatabaseManager)file).getItems();
		for(Item object : items)
		{
			subEntries.add(new ItemList(object, padding + Settings.indentSize, this));
		}
	}
	@Override
	protected void initializeInfoGUI() 
	{
		this.infoGUI = new ItemDataBaseInfoGUI((itemDatabaseManager)file);
	}
	private void removeItem(ItemList item)
	{
		subEntries.remove(item);
		items.remove(item.item);
		reAddComponents();
	}
	private void addNewItemAction()
	{
		JMenuItem addItem = new JMenuItem("Create New Item");
		addItem.addActionListener(e -> {
			new NewItemPopup(this);
		});
		actions.add(addItem);
	}
	private void addExportAsTextAction()
	{
		actions.add(Utils.createExportAction("Export as .txt", "Item Database.txt", "Bedrock's Intermediate ItemDB Format", ((itemDatabaseManager)file)::toItemBytes));
	}
	private void addImportFromTextAction()
	{
		actions.add(Utils.createImportAction("Replace From Text", "Bedrock's Intermediate ItemDB Format", "txt", ((itemDatabaseManager)file)::replaceFromText, this));
	}
	private void addExportLngAction()
	{
		actions.add(Utils.createExportAction("Export Translation Data", "Item Database " + Settings.getLanguage() + ".lng", "Bedrock's LKS Translation Format", ((itemDatabaseManager)file)::toLng));
	}
	private void addImportLngAction()
	{
		actions.add(Utils.createImportLngAction("Translate From Language File", "Bedrock's LKS Translation Format", ((itemDatabaseManager)file)::importLng, this));
	}
	protected void addActions() 
	{
		addNewItemAction();
		addExportAction();
		addExportAsTextAction();
		addImportFromTextAction();
		addExportLngAction();
		addImportLngAction();
		addMouseListener();
		add(actions);
		update();
	}
	public void addItem(Item item) 
	{
		items.add(item);
		subEntries.add(new ItemList(item, padding + Settings.indentSize, this));
		update();
	}
	public class ItemList extends CollapseableFileList
	{
		private Item item;
		private ItemDatabaseList parent;
		private ItemWeaponDataList weaponData;
		private ItemSoundEffectList seData;
		private int padding;
		public ItemList(Item item, int padding, ItemDatabaseList parent) 
		{
			file = item;
			this.item = item;
			this.padding = padding;
			this.parent = parent;
			initializeAll(padding);
		}
		protected void initializeAll(int padding) 
		{
			this.initializeListGUI(padding);
			this.initializeSubGUI();
			this.initializeInfoGUI();
			this.addActions();
			this.reAddComponents();
		}
		public void initializeSubGUI()
		{
			
			if(item.hasWeaponData())
			{
				weaponData = new ItemWeaponDataList(item, padding + Settings.indentSize, this);
				subEntries.add(weaponData);
			}
			if(item.hasSoundEffect())
			{
				seData = new ItemSoundEffectList(item, padding + Settings.indentSize, this);
				subEntries.add(seData);
			}
			if(item.hasPlacementData())
			{
				for(Placement placement : item.getPlacements())
				{
					if(placement != null) subEntries.add(new ItemPlacementDataList(placement, padding + Settings.indentSize, this));
				}
			}
		}
		protected void initializeInfoGUI() 
		{
			this.infoGUI = new ItemInfoGUI(item);
		}
		private void removePlacement(ItemPlacementDataList placement)
		{
			subEntries.remove(placement);
			item.removePlacement(placement.placement);
			reAddComponents();
		}
		protected void addDeleteAction()
		{
			JMenuItem replace = new JMenuItem("Delete Item");
			replace.addActionListener(e -> 
			{
				parent.removeItem(this);
				GUI.update();
			});
			actions.add(replace);
		}
		private void addNewPlacementAction()
		{
			JMenuItem addPlacement = new JMenuItem("Add New Placement");
			addPlacement.addActionListener(e -> {
				subEntries.add(new ItemPlacementDataList(item.addNewPlacement(), padding + Settings.indentSize, this));
				reAddComponents();
				update();
			});
			actions.add(addPlacement);
		}
		JMenuItem removeWD;
		private void addRemoveWeaponDataAction()
		{
			removeWD = new JMenuItem("Remove Weapon Data");
			removeWD.addActionListener(e -> {
				item.removeWeaponData();
				subEntries.remove(weaponData);
				reAddComponents();
				update();
				if(addWD != null) actions.add(addWD);
				else addAddWeaponDataAction();
				actions.remove(removeWD);
			});
			actions.add(removeWD);
		}
		JMenuItem removeSE;
		private void addRemoveSoundEffectAction()
		{
			removeSE = new JMenuItem("Remove Sound Effects");
			removeSE.addActionListener(e -> {
				item.removeSoundEffect();
				subEntries.remove(seData);
				reAddComponents();
				update();
				if(addSE != null) actions.add(addSE);
				else addAddSoundEffectAction();
				actions.remove(removeSE);
			});
			actions.add(removeSE);
		}
		JMenuItem addWD;
		private void addAddWeaponDataAction()
		{
			addWD = new JMenuItem("Add Weapon Data");
			addWD.addActionListener(e -> {
				item.addWeaponData();
				weaponData = new ItemWeaponDataList(item, padding + Settings.indentSize, this);
				subEntries.add(0, weaponData);
				reAddComponents();
				update();
				if(removeWD != null) actions.add(removeWD);
				else addRemoveWeaponDataAction();
				actions.remove(addWD);
			});
			actions.add(addWD);
		}
		JMenuItem addSE;
		private void addAddSoundEffectAction()
		{
			addSE = new JMenuItem("Add Sound Effects");
			addSE.addActionListener(e -> {
				item.addSoundEffect();
				seData = new ItemSoundEffectList(item, padding + Settings.indentSize, this);
				subEntries.add(subEntries.indexOf(weaponData) + 1, seData);
				reAddComponents();
				update();
				if(removeSE != null) actions.add(removeSE);
				else addRemoveSoundEffectAction();
				actions.remove(addSE);
			});
			actions.add(addSE);
		}
		protected void addActions() 
		{
			addDeleteAction();
			addNewPlacementAction();
			if(item.hasWeaponData())
			{
				addRemoveWeaponDataAction();
			}
			else
			{
				addAddWeaponDataAction();
			}
			if(item.hasSoundEffect())
			{
				addRemoveSoundEffectAction();
			}
			else
			{
				addAddSoundEffectAction();
			}
			this.addMouseListener();
			this.add(actions);
			this.update();
		}
		public void update()
		{
			fileName.setText(item.getName());
			super.update();
		}
		public class ItemWeaponDataList extends FileList
		{
			final int padding;
			ItemList parent;
			public ItemWeaponDataList(Item item, int padding, ItemList parent) 
			{
				this.padding = padding;
				this.parent = parent;
				initializeAll(padding);
			}
			protected void initializeAll(int padding) 
			{
				this.initializeListGUI(padding);
				this.initializeInfoGUI();
				this.addActions();
			}
			protected void initializeListGUI(int padding) 
			{
				initializeListGUI(padding, "Weapon Data");
			}
			protected void initializeInfoGUI() 
			{
				this.infoGUI = new ItemWeaponInfoGUI(item);
			}
			protected void addActions() 
			{
				this.addMouseListener();
				this.add(actions);
				this.update();
			}
		}
		public class ItemSoundEffectList extends FileList
		{
			final int padding;
			ItemList parent;
			public ItemSoundEffectList(Item item, int padding, ItemList parent) 
			{
				this.padding = padding;
				this.parent = parent;
				initializeAll(padding);
			}
			protected void initializeAll(int padding) 
			{
				initializeListGUI(padding);
				this.initializeInfoGUI();
				this.addActions();
			}
			protected void initializeListGUI(int padding) 
			{
				initializeListGUI(padding, "Sound Effect Data");
			}
			protected void initializeInfoGUI() 
			{
				this.infoGUI = new ItemSoundEffectInfoGUI(item);
			}
			protected void addActions() 
			{
				this.addMouseListener();
				this.add(actions);
				this.update();
			}
		}
		public class ItemPlacementDataList extends FileList
		{
			Placement placement;
			final int padding;
			ItemList parent;
			public ItemPlacementDataList(Placement placement, int padding, ItemList parent) 
			{
				this.placement = placement;
				this.padding = padding;
				this.parent = parent;
				initializeAll(padding);
			}
			protected void initializeAll(int padding) 
			{
				initializeListGUI(padding);
				initializeInfoGUI();
				addActions();
			}
			protected void initializeListGUI(int padding) 
			{
				initializeListGUI(padding, "Placement Data");
			}
			protected void initializeInfoGUI() 
			{
				this.infoGUI = new ItemPlacementInfoGUI(placement);
			}
			protected void addDeleteAction()
			{
				JMenuItem replace = new JMenuItem("Delete Placement");
				replace.addActionListener(e -> 
				{
					parent.removePlacement(this);
					GUI.update();
				});
				actions.add(replace);
			}
			protected void addActions() 
			{
				this.addDeleteAction();
				this.addMouseListener();
				this.add(actions);
				this.update();
			}
			
		}
	}
}
