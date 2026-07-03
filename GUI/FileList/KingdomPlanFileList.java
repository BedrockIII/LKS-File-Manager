package GUI.FileList;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

import javax.swing.JFileChooser;
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
		System.out.println("█\nComplete!");
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
		addExportAction();
		addReplaceAction();
		addMouseListener();
		add(actions);
	}
	protected void addExportAction()
	{
		JMenuItem export = new JMenuItem("Export As .txt File");
		export.addActionListener(e -> {
			JFileChooser chooseFile = new JFileChooser();
			if(Settings.lastFileSavePath != null) 
			{
				chooseFile.setCurrentDirectory(Paths.get(Settings.lastFileSavePath).toFile().getParentFile());
			}
			chooseFile.setSelectedFile(new File("Kingdom Plan Config.txt"));
			if(chooseFile.showSaveDialog(null)==JFileChooser.APPROVE_OPTION)
			{
				try 
				{
					Files.write(chooseFile.getSelectedFile().toPath(),(planManager.toBytes()));
					Settings.lastFileSavePath = chooseFile.getSelectedFile().toString();
				}
				catch(IOException i)
				{
					System.out.println("Failed to Export Kingdom Plan Config File");
					i.printStackTrace();
				}
				System.out.println("Exported Kingdom Plan Config File");
			}
		});
		actions.add(export);
	}
	private void addReplaceAction()
	{
		JMenuItem replace = new JMenuItem("Replace File");
		replace.addActionListener(e -> {
			JFileChooser chooseFile = new JFileChooser();
			chooseFile.setFileSelectionMode(JFileChooser.FILES_ONLY);
			//chooseFile.setFileFilter(new FileNameExtensionFilter("Collision File", "col"));
			
			int num = chooseFile.showOpenDialog(null);
			if(num==JFileChooser.APPROVE_OPTION)
			{
				try 
				{
					planManager.replaceFromLines(Files.readAllLines(chooseFile.getSelectedFile().toPath()));
					initializeInfoGUI();
					initializeSubGUI();
					reAddComponents();
				} catch (IOException i) 
				{
					i.printStackTrace();
					System.out.println("Failed to Import Kingdom Plan Config File");
				}
				System.out.println("Imported Kingdom Plan Config File");
			}
		});
		actions.add(replace);
	}
	public class AreaList extends CollapseableFileList
	{
		KingdomPlanFileList parent;
		KingdomPlanArea area;
		ArrayList<KingdomPlanElement> elements;
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
			addDeleteAction();
			addNewElementAction();
			add(actions);
			addMouseListener();
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
		public class ElementList extends FileList
		{
			AreaList parent;
			KingdomPlanElement element;
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
		}
		public void removeElement(ElementList element) 
		{
			elements.remove(element.element);
			subEntries.remove(element);
			reAddComponents();
		}
	}
}
