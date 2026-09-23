package GUI.FileList;

import java.util.ArrayList;

import javax.swing.JMenuItem;
import javax.swing.filechooser.FileNameExtensionFilter;

import GUI.GUI;
import GUI.FileInfo.FileInfoFactory;
import GUI.FileInfo.FixedPointObjectInfoGUI;
import WorldFileManager.FixedPointManager;
import bFM.GUIUtils;
import bFM.Nameable;
import bFM.OpenedFile;
import bFM.Settings;
import WorldFileManager.FixedPointObject;

@SuppressWarnings("serial")
public class FixedPoint extends CollapseableFileList
{
	int padding = 0;
	FixedPointManager data;
	CollapseableFileList parent= null;
	ArrayList<FixedPointObject> objects = new ArrayList<FixedPointObject>();
	public FixedPoint(OpenedFile file, int padding, CollapseableFileList parent) 
	{
		this.file = file;
		data = (FixedPointManager) file;
		this.padding = padding;
		this.parent = parent;
		initializeAll(padding);
	}
	protected void initializeAll(int padding)
	{
		fileTypes = new FileNameExtensionFilter("LKS Fixed Placement File", "fp", "vfp", "sfp", "lfp", "plfp");
		initializeListGUI(padding);
		initializeInfoGUI();
		fileName.setText(data.getName());
		initializeSubGUI();
		addActions();
		reAddComponents();
	} 
	protected void addActions()
	{
		addReplaceButton();
		addExpandAllAction();
		addReplaceAsBFPButton();
		addExportAction();
		addExportBFPAction();
		addDeleteAction();
		//addClearEmptyNodes();
		addMouseListener();
		add(actions);
	}
	protected void addClearEmptyNodes()
	{
		//TODO Fix This
		if(objects.size()==0) return;
		JMenuItem replace = new JMenuItem("Remove Empty Data Nodes");
		replace.addActionListener(e -> 
		{
			data.clearEmptyNodes();
			subEntries.removeAll(subEntries);
			for(FixedPointObject object : objects)
			{
				if(object.isParentNode())
				{
					subEntries.add(new FixedPointObjectListGUI(object, padding + Settings.indentSize, data));
				}
			}
		});
		actions.add(replace);
	}
	protected void addDeleteAction()
	{
		if(parent==null || padding == 0) return;
		JMenuItem replace = new JMenuItem("Delete File");
		replace.addActionListener(e -> 
		{
			parent.removeFile(this);
			GUI.update();
		});
		actions.add(replace);
	}
	protected void addReplaceButton()
	{
		actions.add(GUIUtils.createReplaceAction("Replace With Raw Data", ((Nameable) file).getName(), ((FixedPointManager)file).getExtension(),file::setData, ((Nameable)file)::setName, parent));
	}
	protected void addReplaceAsBFPButton()
	{
		actions.add(GUIUtils.createImportAction("Replace From BFP", "Bedrock's Intermediate FP Text File", "bfp", ((FixedPointManager)file)::replaceFromBFP, this));
	}
	private void addExportBFPAction() 
	{
		actions.add(GUIUtils.createExportAction("Export As BFP", ((Nameable) file).getName().substring(0, ((Nameable) file).getName().lastIndexOf('.')) + ".bfp", "Bedrock's Intermediate FP Text File", ((FixedPointManager)file)::toBFPBytes));
	}
	public void initializeSubGUI() 
	{
		subEntries.removeAll(subEntries);
		objects = ((FixedPointManager)file).getObjects();
		for(FixedPointObject object : objects)
		{
			if(object.isParentNode())
			{
				subEntries.add(new FixedPointObjectListGUI(object, padding + Settings.indentSize, data));
			}
		}
	}
	public void update()
	{
		fileName.setText(((FixedPointManager)file).getName());
		super.update();
	}
	public void removeObject(FixedPointObjectListGUI file) 
	{
		remove(file);
		data.removePoint(file.object);
		subEntries.remove(file);
	}
	public static class FixedPointObjectListGUI extends CollapseableFileList
	{
		int padding;
		FixedPointObject object;
		FixedPointManager data;
		ArrayList<FixedPointObject> children;
		public FixedPointObjectListGUI(FixedPointObject object, int padding, FixedPointManager data) 
		{
			this.object = object;
			this.data = data;
			this.padding = padding;
			children = object.getChildren();
			initializeAll(padding);
		}
		protected void initializeAll(int padding)
		{
			initializeListGUI(padding, object.getName());
			initializeSubGUI();
			addActions();
			reAddComponents();
		}
		protected void addActions()
		{
			addDeleteAction();
			addNewAction();
			addExpandAllAction();
			addMouseListener();
			add(actions);
		}
		protected void addNewAction()
		{
			JMenuItem replace = new JMenuItem("Create New Sub-Object");
			replace.addActionListener(e -> 
			{
				addNewObject();
				reAddComponents();
			});
			actions.add(replace);
		}
		private void addNewObject()
		{
			FixedPointObject child = object.addChild();
			subEntries.add(new FixedPointObjectListGUI(child, padding + Settings.indentSize, data));
		}
		protected void addDeleteAction()
		{
			if(object.isParentNode())
			{
				return;
			}
			JMenuItem replace = new JMenuItem("Delete Object");
			replace.addActionListener(e -> 
			{
				if(getParent() instanceof FixedPoint)
				{
					((FixedPoint)getParent()).removeObject(this);
				}
				else
				if(getParent() instanceof FixedPointObjectListGUI)
				{
					((FixedPointObjectListGUI)getParent()).removeObject(this);
				}
				GUI.update();
			});
			actions.add(replace);
		}
		protected void initializeInfoGUI() 
		{
			this.infoGUI = new FixedPointObjectInfoGUI(object, this);
		}
		public void update()
		{
			fileName.setText(object.getName());
			super.update();
		}
		public void initializeSubGUI()
		{
			subEntries.removeAll(subEntries);
			for(FixedPointObject child : children)
			{
				subEntries.add(new FixedPointObjectListGUI(child, padding + Settings.indentSize, data));
			}
		}
		public void removeObject(FixedPointObjectListGUI file) 
		{
			remove(file);
			data.removePoint(file.object);
			subEntries.remove(file);
		}
	}
	protected void initializeInfoGUI() 
	{
		infoGUI = FileInfoFactory.makeInfoGUI(file);
	}
}
