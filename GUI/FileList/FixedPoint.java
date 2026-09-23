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
	CollapseableFileList parent= null;
	ArrayList<FixedPointObject> objects = new ArrayList<FixedPointObject>();
	public FixedPoint(OpenedFile file, int padding, CollapseableFileList parent) 
	{
		this.file = file;
		this.padding = padding;
		this.parent = parent;
		initializeAll(padding);
	}
	protected void initializeAll(int padding)
	{
		fileTypes = new FileNameExtensionFilter("LKS Fixed Placement File", "fp", "vfp", "sfp", "lfp", "plfp");
		initializeListGUI(padding);
		initializeInfoGUI();
		fileName.setText(((Nameable) file).getName());
		initializeSubGUI();
		addActions();
		reAddComponents();
	} 
	protected void addActions()
	{
		addReplaceButton();
		addReplaceAsBFPButton();
		addExportAction();
		addExportBFPAction();
		addDeleteAction();
		addMouseListener();
		add(actions);
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
				subEntries.add(new FixedPointObjectListGUI(object, padding + Settings.indentSize));
			}
		}
	}
	public void update()
	{
		fileName.setText(((FixedPointManager)file).getName());
		super.update();
	}
	public void removeFile(FileList file) 
	{
		remove(file);
		objects.remove(((FixedPointObjectListGUI)file).object);
		subEntries.remove(file);
	}
	private class FixedPointObjectListGUI extends CollapseableFileList
	{
		int padding;
		FixedPointObject object;
		ArrayList<FixedPointObject> children;
		public FixedPointObjectListGUI(FixedPointObject object, int padding) 
		{
			this.object = object;
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
			if(children!=null && children.size() != 0)
			{
				addExpandAllAction();
			}
			addMouseListener();
			add(actions);
		}
		protected void addDeleteAction()
		{
			if(getParent()==null) return;
			JMenuItem replace = new JMenuItem("Delete File");
			replace.addActionListener(e -> 
			{
				((CollapseableFileList)getParent()).removeFile(this);
				GUI.update();
			});
			actions.add(replace);
		}
		protected void initializeInfoGUI() 
		{
			this.infoGUI = new FixedPointObjectInfoGUI(object);
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
				subEntries.add(new FixedPointObjectListGUI(child, padding + Settings.indentSize));
			}
		}
	}
	protected void initializeInfoGUI() 
	{
		infoGUI = FileInfoFactory.makeInfoGUI(file);
	}
}
