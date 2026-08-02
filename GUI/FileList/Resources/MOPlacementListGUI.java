package GUI.FileList.Resources;

import java.util.ArrayList;

import javax.swing.JMenuItem;

import GUI.GUI;
import GUI.FileInfo.GroupInfoGUI;
import GUI.FileInfo.GroupListInfoGUI;
import GUI.FileInfo.GroupsInfoGUI;
import GUI.FileInfo.ObjectInfoGUI;
import GUI.FileInfo.PlacementInfoGUI;
import GUI.FileInfo.RandomAreaInfoGUI;
import GUI.FileInfo.RandomAreaListInfoGUI;
import GUI.FileInfo.RandomPositionGUI;
import GUI.FileList.CollapseableFileList;
import GUI.FileList.FileList;
import GUI.PopupWindows.NewMobGroupTypeWindow;
import GUI.PopupWindows.NewMobObjectWindow;
import GUI.PopupWindows.NewMobRandomAreaWindow;
import ResourceManagers.MSDBManager.Placement.MissionObjectPlacementManager;
import ResourceManagers.MSDBManager.Placement.MobConstantPlace;
import ResourceManagers.MSDBManager.Placement.MobGroup;
import ResourceManagers.MSDBManager.Placement.MobObject;
import ResourceManagers.MSDBManager.Placement.MobObject.ObjectDefault;
import ResourceManagers.MSDBManager.Placement.MobRandomArea;
import ResourceManagers.MSDBManager.Placement.MobRandomPoint;
import bFM.GUIUtils;
import bFM.Settings;
import bFM.Utils;

@SuppressWarnings("serial")
public class MOPlacementListGUI extends CollapseableFileList
{
	MissionObjectPlacementManager data;
	MissionObjectDatabase parent;
	
	//Things
	RandomAreasListGUI RandomPlaces;
	GroupTypesListGUI Groups;
	
	int padding = 0;
	public MOPlacementListGUI(MissionObjectPlacementManager file, int padding, MissionObjectDatabase parent) 
	{
		this.parent = parent;
		this.file = file;
		data = file;
		this.padding = padding;
		initializeAll(padding);
	}
	public void initializeSubGUI() 
	{
		Groups = new GroupTypesListGUI(data, padding + Settings.indentSize, this);
		subEntries.add(Groups);
		RandomPlaces = new RandomAreasListGUI(data.getAreaList(), padding + Settings.indentSize, this);
		subEntries.add(RandomPlaces);
	}
	protected void initializeAll(int padding) 
	{
		initializeListGUI(padding, "Mission Object Placements");
		initializeSubGUI();
		initializeInfoGUI();
		addActions();
		reAddComponents();
	}
	protected void initializeInfoGUI() 
	{
		infoGUI = new PlacementInfoGUI(data);
	}
	protected void addActions() 
	{
		//TODO import bMos, export bMos, replace bMos
		addImportBMosAction();
		addReplaceBMosAction();
		exportAsBMosAction();
		add(actions);
		addMouseListener();
	}
	private void addImportBMosAction()
	{
		actions.add(GUIUtils.createImportAction("Replace from text", "Bedrock's Intermediate Mission Object Format", 
				"bMos", data::importBMos, this));
	}
	private void addReplaceBMosAction()
	{
		actions.add(GUIUtils.createImportAction("Replace from text", "Bedrock's Intermediate Mission Object Format", 
				"bMos", data::replaceBMos, this));
	}
	public void exportAsBMosAction() 
	{
		actions.add(GUIUtils.createExportAction("Export as bMos", "Enemies.bMos", "bMos", data::toBMos));
	}
	public class RandomAreasListGUI extends CollapseableFileList
	{
		ArrayList<MobRandomArea> data;
		MOPlacementListGUI parent;
		int padding = 0;
		public RandomAreasListGUI(ArrayList<MobRandomArea> file, int padding, MOPlacementListGUI parent) 
		{
			this.parent = parent;
			data = file;
			this.padding = padding;
			initializeAll(padding);
		}
		public void initializeSubGUI() 
		{
			for(MobRandomArea Area : data)
			{
				subEntries.add(new RandomAreaListGUI(Area, padding + Settings.indentSize, this));
			}
		}
		protected void initializeAll(int padding) 
		{
			initializeListGUI(padding, "Random Spawning Areas");
			initializeSubGUI();
			//initializeInfoGUI();
			addActions();
			reAddComponents();
		}
		protected void initializeInfoGUI() 
		{
			infoGUI = new RandomAreaListInfoGUI(data);
		}
		protected void addActions() 
		{
			addAreaAction();
			add(actions);
			addMouseListener();
		}
		protected void addAreaAction()
		{
			JMenuItem removeElem = new JMenuItem("Add Area");
			removeElem.addActionListener(e -> {
				new NewMobRandomAreaWindow(this);
				GUI.update();
			});
			actions.add(removeElem);
		}
		public class RandomAreaListGUI extends CollapseableFileList
		{
			MobRandomArea data;
			RandomAreasListGUI parent;
			int padding = 0;
			public RandomAreaListGUI(MobRandomArea file, int padding, RandomAreasListGUI parent) 
			{
				this.parent = parent;
				data = file;
				this.padding = padding;
				initializeAll(padding);
			}
			public void initializeSubGUI() 
			{
				for(MobRandomPoint Point : data.getPoints())
				{
					subEntries.add(new RandomPointListGUI(Point, padding + Settings.indentSize, this));
				}
			}
			protected void initializeAll(int padding) 
			{
				initializeListGUI(padding, "Random Area " + data.getCode());
				initializeSubGUI();
				//initializeInfoGUI();
				addActions();
				reAddComponents();
			}
			protected void initializeInfoGUI() 
			{
				infoGUI = new RandomAreaInfoGUI(data, this);
			}
			protected void addActions() 
			{
				//TODO move up, move down, Add Point
				addPointAction();
				addDeleteAction();
				add(actions);
				addMouseListener();
			}
			protected void addPointAction()
			{
				JMenuItem removeElem = new JMenuItem("Add Point");
				removeElem.addActionListener(e -> {
					MobRandomPoint p = new MobRandomPoint();
					data.getPoints().add(p);
					subEntries.add(new RandomPointListGUI(p, padding + Settings.indentSize, this));
					GUI.update();
				});
				actions.add(removeElem);
			}
			protected void addDeleteAction()
			{
				JMenuItem removeElem = new JMenuItem("Delete Area");
				removeElem.addActionListener(e -> {
					parent.removeArea(this);
					GUI.update();
				});
				actions.add(removeElem);
			}
			public class RandomPointListGUI extends FileList
			{
				MobRandomPoint data;
				RandomAreaListGUI parent;
				int padding = 0;
				public RandomPointListGUI(MobRandomPoint file, int padding, RandomAreaListGUI parent) 
				{
					this.parent = parent;
					data = file;
					this.padding = padding;
					initializeAll(padding);
				}
				protected void initializeAll(int padding) 
				{
					initializeListGUI(padding, "Random Point");
					addActions();
					reAddComponents();
				}
				protected void initializeInfoGUI() 
				{
					infoGUI = new RandomPositionGUI(data);
				}
				protected void addActions() 
				{
					//TODO move up, move down
					addDeleteAction();
					add(actions);
					addMouseListener();
				}
				protected void addDeleteAction()
				{
					JMenuItem removeElem = new JMenuItem("Delete Point");
					removeElem.addActionListener(e -> {
						parent.removePoint(this);
						GUI.update();
					});
					actions.add(removeElem);
				}
			}
			public void removePoint(RandomPointListGUI point) 
			{
				data.getPoints().remove(point.data);
				subEntries.remove(point);
				reAddComponents();
			}
		}
		public void removeArea(RandomAreaListGUI area) 
		{
			data.remove(area.data);
			subEntries.remove(area);
			reAddComponents();
		}
		public void newArea(int code) 
		{
			MobRandomArea a = new MobRandomArea(code);
			data.add(a);
			subEntries.add(new RandomAreaListGUI(a, padding + Settings.indentSize, this));
			reAddComponents();
		}
	}
	public class GroupTypesListGUI extends CollapseableFileList
	{
		MissionObjectPlacementManager data;
		MOPlacementListGUI parent;
		int padding = 0;
		public GroupTypesListGUI(MissionObjectPlacementManager file, int padding, MOPlacementListGUI parent) 
		{
			this.parent = parent;
			data = file;
			this.padding = padding;
			initializeAll(padding);
		}
		public void initializeSubGUI() 
		{
			for(int code : data.getGroupCodes())
			{
				subEntries.add(new GroupsListGUI(data.getGroupsByCode(code), padding + Settings.indentSize, this));
			}
		}
		protected void initializeAll(int padding) 
		{
			initializeListGUI(padding, "Mission Groups");
			initializeSubGUI();
			addActions();
			reAddComponents();
		}
		protected void initializeInfoGUI() 
		{
			infoGUI = new GroupListInfoGUI(data);
		}
		protected void addActions() 
		{
			addGroupCategoryAction();
			add(actions);
			addMouseListener();
		}
		private void addGroupCategoryAction()
		{
			JMenuItem removeElem = new JMenuItem("Add Group Category");
			removeElem.addActionListener(e -> {
				new NewMobGroupTypeWindow(this);
				GUI.update();
			});
			actions.add(removeElem);
		}
		public void newGroupCategory(int code)
		{
			subEntries.add(new GroupsListGUI(code, padding + Settings.indentSize, this));
			reAddComponents();
		}
		public class GroupsListGUI extends CollapseableFileList
		{
			ArrayList<MobGroup> data;
			GroupTypesListGUI parent;
			int padding = 0;
			private GroupsListGUI(int code, int padding, GroupTypesListGUI parent) 
			{
				this.parent = parent;
				data = new ArrayList<MobGroup>();
				data.add(new MobGroup(parent.data.getIndexForGroup(code), code));
				this.padding = padding;
				initializeAll(padding);
			}
			public GroupsListGUI(ArrayList<MobGroup> file, int padding, GroupTypesListGUI parent) 
			{
				this.parent = parent;
				data = file;
				this.padding = padding;
				initializeAll(padding);
			}
			public void initializeSubGUI() 
			{
				for(MobGroup Group : data)
				{
					subEntries.add(new GroupListGUI(Group, padding + Settings.indentSize, this));
				}
			}
			protected void initializeAll(int padding) 
			{
				initializeListGUI(padding, "Group Category " + data.get(0).getGroupNumber());
				initializeSubGUI();
				//initializeInfoGUI();
				addActions();
				reAddComponents();
			}
			protected void initializeInfoGUI() 
			{
				infoGUI = new GroupsInfoGUI(data);
			}
			protected void addActions() 
			{
				exportAsBMosAction();
				addDeleteAction();
				addGroupAction();
				add(actions);
				addMouseListener();
			}
			protected void addDeleteAction()
			{
				JMenuItem removeElem = new JMenuItem("Delete Group Category");
				removeElem.addActionListener(e -> {
					for(int i = 0; i < subEntries.size();  i++)
					{
						if(subEntries.get(i) instanceof GroupListGUI)
							removeGroup((GroupListGUI) subEntries.get(i));
					}
					parent.subEntries.remove(this);
					parent.reAddComponents();
					GUI.update();
				});
				actions.add(removeElem);
			}
			public void exportAsBMosAction() 
			{
				actions.add(GUIUtils.createExportAction("Export as bMos", "Group " + data.get(0).getGroupNumber() + ".bMos", "bMos", this::toBMos));
			}
			private byte[] toBMos()
			{
				String ret = "";
				for(MobGroup g : data)
				{
					ret += g.toString();
				}
				return Utils.encodeStringToBytes(ret);
			}
			protected void addGroupAction()
			{
				JMenuItem removeElem = new JMenuItem("Add Group");
				removeElem.addActionListener(e -> 
				{
					int index = parent.data.getIndexForGroup(data.get(0).getGroupNumber());
					MobGroup g = new MobGroup(index, data.get(0).getGroupNumber());
					parent.data.getMobGroups().add(g);
					data.add(g);
					subEntries.add(new GroupListGUI(g, padding + Settings.indentSize, this));
					GUI.update();
				});
				actions.add(removeElem);
			}
			public class GroupListGUI extends CollapseableFileList
			{
				MobGroup data;
				GroupsListGUI parent;
				JMenuItem moveUp = null;
				JMenuItem moveDown = null;
				int padding = 0;
				public GroupListGUI(MobGroup file, int padding, GroupsListGUI parent) 
				{
					this.parent = parent;
					data = file;
					this.file = file;
					this.padding = padding;
					initializeAll(padding);
				}
				public void initializeSubGUI() 
				{
					for(MobObject Object : data.getObjects())
					{
						subEntries.add(new ObjectListGUI(Object, padding + Settings.indentSize, this));
					}
				}
				protected void initializeAll(int padding) 
				{
					initializeListGUI(padding, "Group " + data.getCode());
					initializeSubGUI();
					//initializeInfoGUI();
					addActions();
					reAddComponents();
				}
				protected void initializeInfoGUI() 
				{
					infoGUI = new GroupInfoGUI(data, this);
				}
				protected void addActions() 
				{
					if(data.getPlacement() == null) addConstantPlaceAction();
					else removeConstantPlaceAction();
					addObjectAction();
					addDeleteAction();
					addMoveDownAction();
					addMoveUpAction();
					add(actions);
					addMouseListener();
				}
				protected void addObjectAction()
				{
					JMenuItem removeElem = new JMenuItem("Add Object");
					removeElem.addActionListener(e -> {
						new NewMobObjectWindow(this);
						GUI.update();
					});
					actions.add(removeElem);
				}
				protected void addDeleteAction()
				{
					JMenuItem removeElem = new JMenuItem("Delete Group");
					removeElem.addActionListener(e -> {
						parent.removeGroup(this);
						GUI.update();
					});
					actions.add(removeElem);
				}
				private void addMoveUpAction()
				{
					if(moveUp == null)
					{
						moveUp = new JMenuItem("Move Up");
						moveUp.addActionListener(e -> {
							parent.moveUp(this);
							addMoveDownAction();
							addMoveUpAction();
							GUI.update();
						});
					}
					if(!parent.isFirst(this))
					{
						actions.add(moveUp);
					}
					else actions.remove(moveUp);
				}
				private void addMoveDownAction()
				{
					if(moveDown == null)
					{
						moveDown = new JMenuItem("Move Down");
						moveDown.addActionListener(e -> {
							parent.moveDown(this);
							addMoveDownAction();
							addMoveUpAction();
							GUI.update();
						});
					}
					if(!parent.isLast(this))
					{
						actions.add(moveDown);
					}
					else actions.remove(moveDown);
				}
				JMenuItem addPlacement = null;
				JMenuItem removePlacement = null;
				private void addConstantPlaceAction()
				{
					if(addPlacement==null)
					{
						addPlacement = new JMenuItem("Add Placement");
						addPlacement.addActionListener(e -> 
						{
							if(infoGUI == null) initializeInfoGUI();
							actions.remove(addPlacement);
							MobConstantPlace c = new MobConstantPlace();
							data.registerPlacement(c);
							((GroupInfoGUI) infoGUI).addPlace(c);
							removeConstantPlaceAction();
							infoGUI.update();
						});
					}
					actions.add(addPlacement, 0);
				}
				private void removeConstantPlaceAction()
				{
					if(removePlacement==null)
					{
						removePlacement = new JMenuItem("Remove Placement");
						removePlacement.addActionListener(e -> 
						{
							if(infoGUI == null) initializeInfoGUI();
							actions.remove(removePlacement);
							data.unregisterPlacement();
							((GroupInfoGUI) infoGUI).removePlace();
							addConstantPlaceAction();
							infoGUI.update();
						});
					}
					actions.add(removePlacement, 0);
				}
				public void update()
				{
					fileName.setText("Group " + data.getCode());
					super.update();
				}
				public class ObjectListGUI extends FileList
				{
					MobObject data;
					GroupListGUI parent;
					JMenuItem moveUp = null;
					JMenuItem moveDown = null;
					int padding = 0;
					public ObjectListGUI(MobObject file, int padding, GroupListGUI parent) 
					{
						this.parent = parent;
						data = file;
						this.file = file;
						this.padding = padding;
						initializeAll(padding);
					}
					protected void initializeAll(int padding) 
					{
						initializeListGUI(padding, "Mission Object");
						addActions();
						reAddComponents();
					}
					protected void initializeInfoGUI() 
					{
						infoGUI = new ObjectInfoGUI(data, parent.parent.parent.parent.parent.MonsterDataPack, this);
					}
					protected void addActions() 
					{
						addMoveDownAction();
						addMoveUpAction();
						addDeleteAction();
						add(actions);
						addMouseListener();
					}
					protected void addDeleteAction()
					{
						JMenuItem removeElem = new JMenuItem("Delete Object");
						removeElem.addActionListener(e -> {
							parent.removeObject(this);
							GUI.update();
						});
						actions.add(removeElem);
					}
					private void addMoveUpAction()
					{
						if(moveUp == null)
						{
							moveUp = new JMenuItem("Move Up");
							moveUp.addActionListener(e -> {
								parent.moveUp(this);
								addMoveDownAction();
								addMoveUpAction();
								GUI.update();
							});
						}
						if(!parent.isFirst(this))
						{
							actions.add(moveUp, 0);
						}
						else actions.remove(moveUp);
					}
					private void addMoveDownAction()
					{
						if(moveDown == null)
						{
							moveDown = new JMenuItem("Move Down");
							moveDown.addActionListener(e -> {
								parent.moveDown(this);
								addMoveDownAction();
								addMoveUpAction();
								GUI.update();
							});
						}
						if(!parent.isLast(this))
						{
							actions.add(moveDown, 0);
						}
						else actions.remove(moveDown);
					}
					public void update()
					{
						fileName.setText(parent.parent.parent.parent.parent.MonsterDataPack.getModCodeByName(this.data.getModCode()));
						super.update();
					}
				}
				public void removeObject(ObjectListGUI object)
				{
					data.getObjects().remove(object.data);
					subEntries.remove(object);
					reAddComponents();
				}
				public boolean isLast(ObjectListGUI object) 
				{
					return data.getObjects().indexOf(object.data) == data.getObjects().size()-1;
				}
				public void moveDown(ObjectListGUI object) 
				{
					if(isLast(object)) return;
					int index = subEntries.indexOf(object);
					if(index == -1) this.removeObject(object);
					subEntries.remove(object);
					subEntries.add(index + 1, object);
					index = data.getObjects().indexOf(object.data);
					if(index == -1) this.removeObject(object);
					data.getObjects().remove(object.data);
					data.getObjects().add(index + 1, object.data);
					reAddComponents();
				}
				public boolean isFirst(ObjectListGUI object) 
				{
					return data.getObjects().indexOf(object.data) == 0;
				}
				public void moveUp(ObjectListGUI object)
				{
					if(isFirst(object)) return;
					int index = subEntries.indexOf(object);
					if(index == -1) this.removeObject(object);
					subEntries.remove(object);
					subEntries.add(index - 1, object);
					index = data.getObjects().indexOf(object.data);
					if(index == -1) this.removeObject(object);
					data.getObjects().remove(object.data);
					data.getObjects().add(index - 1, object.data);
					reAddComponents();
				}
				public void newObject(ObjectDefault objectType) 
				{
					MobObject o = data.addObject(objectType);
					subEntries.add(new ObjectListGUI(o, padding + Settings.indentSize, this));
					reAddComponents();
				}
			}
			public void removeGroup(GroupListGUI object)
			{
				data.remove(object.data);
				subEntries.remove(object);
				parent.parent.data.getMobGroups().remove(object.data);
				reAddComponents();
			}
			public boolean isLast(GroupListGUI object) 
			{
				return data.indexOf(object.data) == data.size()-1;
			}
			public void moveDown(GroupListGUI object) 
			{
				if(isLast(object)) return;
				int index = subEntries.indexOf(object);
				if(index == -1) this.removeGroup(object);
				subEntries.remove(object);
				subEntries.add(index + 1, object);
				index = data.indexOf(object.data);
				if(index == -1) this.removeGroup(object);
				data.remove(object.data);
				data.add(index + 1, object.data);
				reAddComponents();
			}
			public boolean isFirst(GroupListGUI object) 
			{
				return data.indexOf(object.data) == 0;
			}
			public void moveUp(GroupListGUI object)
			{
				if(isFirst(object)) return;
				int index = subEntries.indexOf(object);
				if(index == -1) this.removeGroup(object);
				subEntries.remove(object);
				subEntries.add(index - 1, object);
				index = data.indexOf(object.data);
				if(index == -1) this.removeGroup(object);
				data.remove(object.data);
				data.add(index - 1, object.data);
				reAddComponents();
			}
		}
	}
}
