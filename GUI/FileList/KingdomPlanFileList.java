package GUI.FileList;

import java.util.ArrayList;

import javax.swing.JMenuItem;
import javax.swing.filechooser.FileNameExtensionFilter;

import GUI.GUI;
import GUI.FileInfo.FileInfoFactory;
import GUI.FileInfo.MenuDB.KingdomPlan.KingdomPlanAreaGUI;
import GUI.FileInfo.MenuDB.KingdomPlan.KingdomPlanElementGUI;
import SystemDataManagers.MenuDB.KingdomPlanManager.KingdomPlanArea;
import SystemDataManagers.MenuDB.KingdomPlanManager.KingdomPlanElement;
import SystemDataManagers.MenuDB.KingdomPlanManager.kingdomPlanManager;
import bFM.Settings;
import bFM.Utils;

@SuppressWarnings("serial")
public class KingdomPlanFileList extends CollapseableFileList
{
	kingdomPlanManager planManager;
	ArrayList<KingdomPlanArea> areas;
	int padding;
	public KingdomPlanFileList(kingdomPlanManager file, int padding)
	{
		this.file = file;
		planManager = file;
		this.padding = padding;
		initializeAll(padding);
	}
	protected void initializeAll(int padding)
	{
		System.out.print("Opening Kingdom Plan Binary File: █");
		fileTypes = new FileNameExtensionFilter("Kingdom Plan Binary File", "bin");
		initializeListGUI(padding, "Kingdom Plan Config");
		System.out.print("█");
		initializeSubGUI();
		System.out.print("█");
		initializeInfoGUI();
		System.out.print("█");
		addActions();
		System.out.print("█");
		reAddComponents();
		System.out.println("█ Complete!\n");
	}
	public void initializeSubGUI() 
	{
		subEntries.removeAll(subEntries);
		areas = planManager.getAreas();
		for(KingdomPlanArea area : areas)
		{
			subEntries.add(new AreaList(area, padding + Settings.indentSize, this));
		}
	}
	protected void initializeInfoGUI() 
	{
		infoGUI = FileInfoFactory.makeInfoGUI(file);
	}
	public void addActions()
	{
		super.addExportAction();
		addExportAction();
		addReplaceRawAction();
		addReplaceAction();
		addMouseListener();
		addNewAreaAction();
		add(actions);
	}
	protected void addExportAction()
	{
		actions.add(Utils.createExportAction("Export as .txt", "Kingdom Plan Config.txt", 
				"Bedrock's Intermediate Kingdom Plan DB Format", planManager::toStringBytes));
	}
	private void addReplaceRawAction()
	{
		actions.add(Utils.createImportAction("Replace from Binary", "Kingdom Plan Binary Format", 
				"bin", planManager::replaceFromBytes, this));
	}
	private void addReplaceAction()
	{
		actions.add(Utils.createImportAction("Replace from text", "Bedrock's Intermediate Kingdom Plan DB Format", 
				"txt", planManager::importFromText, this));
	}
	private void addNewAreaAction()
	{
		JMenuItem newElem = new JMenuItem("Add New Area");
		newElem.addActionListener(e -> {
			KingdomPlanArea Area = new KingdomPlanArea();
			areas.add(Area);
			AreaList AreaGUI = new AreaList(Area, padding + Settings.indentSize, this);
			subEntries.add(AreaGUI);
			reAddComponents();
		});
		actions.add(newElem);
	}
	public void removeArea(AreaList area) 
	{
		areas.remove(area.area);
		subEntries.remove(area);
		reAddComponents();
	}
	public class AreaList extends CollapseableFileList
	{
		KingdomPlanFileList parent;
		KingdomPlanArea area;
		ArrayList<KingdomPlanElement> elements;
		JMenuItem moveUp = null;
		JMenuItem moveDown = null;
		int padding;
		public AreaList(KingdomPlanArea area, int padding, KingdomPlanFileList parent) 
		{
			this.parent = parent;
			this.area = area;
			this.padding = padding;
			initializeAll(padding);
		}
		protected void initializeAll(int padding) 
		{
			initializeListGUI(padding, "Area: \"" + area.getName() + "\"");
			initializeInfoGUI();
			initializeSubGUI();
			addActions();
			reAddComponents();
		}
		public void initializeSubGUI() 
		{
			subEntries.removeAll(subEntries);
			elements = area.getElements();
			for(KingdomPlanElement element : elements)
			{
				subEntries.add(new ElementList(element, padding + Settings.indentSize, this));
			}
		}
		protected void initializeInfoGUI() 
		{
			this.infoGUI = new KingdomPlanAreaGUI(area);
		}
		protected void addActions() 
		{
			addMoveDownAction();
			addMoveUpAction();
			addDeleteAction();
			addNewElementAction();
			add(actions);
			addMouseListener();
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
		private void addNewElementAction()
		{
			JMenuItem newElem = new JMenuItem("Add New Element");
			newElem.addActionListener(e -> {
				KingdomPlanElement Element = new KingdomPlanElement();
				area.addElement(Element);
				ElementList ElementGUI = new ElementList(Element, padding + Settings.indentSize, this);
				subEntries.add(ElementGUI);
				reAddComponents();
			});
			actions.add(newElem);
		}
		protected void addDeleteAction()
		{
			JMenuItem removeArea = new JMenuItem("Delete Area");
			removeArea.addActionListener(e -> {
				parent.removeArea(this);
				GUI.update();
			});
			actions.add(removeArea);
		}
		public void update()
		{
			fileName.setText("Area: \"" + area.getName() + "\"");
			addMoveDownAction();
			addMoveUpAction();
			super.update();
		}
		public class ElementList extends FileList
		{
			AreaList parent;
			KingdomPlanElement element;
			JMenuItem moveUp = null;
			JMenuItem moveDown = null;
			int padding;
			public ElementList(KingdomPlanElement element, int padding, AreaList parent) 
			{
				this.parent = parent;
				this.element = element;
				this.padding = padding;
				initializeAll(padding);
			}
			protected void initializeAll(int padding) 
			{
				initializeListGUI(padding, "Element: \"" + element.getName() + "\"");
				initializeInfoGUI();
				addActions();
				reAddComponents();
			}
			protected void initializeInfoGUI() 
			{
				this.infoGUI = new KingdomPlanElementGUI(element);
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
				JMenuItem removeElem = new JMenuItem("Delete Element");
				removeElem.addActionListener(e -> {
					parent.removeElement(this);
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
				fileName.setText("Element: \"" + element.getName() + "\"");
				super.update();
			}
		}
		public void removeElement(ElementList element) 
		{
			elements.remove(element.element);
			subEntries.remove(element);
			reAddComponents();
		}
		public boolean isLast(ElementList elementList) 
		{
			return subEntries.indexOf(elementList) == subEntries.size()-1;
		}
		public void moveDown(ElementList elementList) 
		{
			if(isLast(elementList)) return;
			int index = subEntries.indexOf(elementList);
			if(index == -1) this.removeElement(elementList);
			subEntries.remove(elementList);
			subEntries.add(index + 1, elementList);
			index = elements.indexOf(elementList.element);
			if(index == -1) this.removeElement(elementList);
			elements.remove(elementList.element);
			elements.add(index + 1, elementList.element);
			reAddComponents();
		}
		public boolean isFirst(ElementList elementList) 
		{
			return subEntries.indexOf(elementList) == 0;
		}
		public void moveUp(ElementList elementList)
		{
			if(isFirst(elementList)) return;
			int index = subEntries.indexOf(elementList);
			if(index == -1) this.removeElement(elementList);
			subEntries.remove(elementList);
			subEntries.add(index - 1, elementList);
			index = elements.indexOf(elementList.element);
			if(index == -1) this.removeElement(elementList);
			elements.remove(elementList.element);
			elements.add(index - 1, elementList.element);
			reAddComponents();
		}
	}
	public boolean isFirst(AreaList areaList) 
	{
		return subEntries.indexOf(areaList) == 0;
	}
	public void moveDown(AreaList areaList) 
	{
		if(isLast(areaList)) return;
		int index = subEntries.indexOf(areaList);
		if(index == -1) this.removeArea(areaList);
		subEntries.remove(areaList);
		subEntries.add(index + 1, areaList);
		index = areas.indexOf(areaList.area);
		if(index == -1) this.removeArea(areaList);
		areas.remove(areaList.area);
		areas.add(index + 1, areaList.area);
		reAddComponents();
	}
	public void moveUp(AreaList areaList) 
	{
		if(isFirst(areaList)) return;
		int index = subEntries.indexOf(areaList);
		if(index == -1) this.removeArea(areaList);
		subEntries.remove(areaList);
		subEntries.add(index - 1, areaList);
		index = areas.indexOf(areaList.area);
		if(index == -1) this.removeArea(areaList);
		areas.remove(areaList.area);
		areas.add(index - 1, areaList.area);
		reAddComponents();
	}
	public boolean isLast(AreaList areaList) 
	{
		return subEntries.indexOf(areaList) == subEntries.size()-1;
	}
}
