package GUI.FileList.SystemData.CastlePicture;

import GUI.FileInfo.MenuDB.CastlePicture.CastlePictureInfoGUI;
import GUI.FileList.FileList;
import SystemDataManagers.MenuDB.CastlePicture.CastlePicture;
import bFM.Settings;

@SuppressWarnings("serial")
public class CastlePictureFileList extends FileList
	{
	CastlePicture file;
		CastlePictureManagerFileList parent;
		public CastlePictureFileList(CastlePicture entry, CastlePictureManagerFileList parent)
		{
			padding = parent.getPadding() + Settings.indentSize;
			this.file = entry;
			super.file = entry;
			this.parent = parent;
			initializeAll();
		}
		protected void initializeAll() 
		{
			initializeListGUI("Picture: \"" + file.getText() + "\"");
			addActions();
		}
		protected void initializeInfoGUI() 
		{
			infoGUI = new CastlePictureInfoGUI(file);
		}
		protected void addActions() 
		{
			addDeleteAction("Delete Castle Picture", parent, parent.manager.getEntries());
			addMoveUpAction(parent, parent.manager.getEntries());
			addMoveDownAction(parent, parent.manager.getEntries());
			add(actions);
			addMouseListener();
		}
		public void update()
		{
			fileName.setText("Picture: \"" + file.getText() + "\"");
			super.update();
		}
	}