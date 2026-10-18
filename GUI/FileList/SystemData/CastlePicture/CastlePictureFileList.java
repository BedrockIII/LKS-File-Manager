package GUI.FileList.SystemData.CastlePicture;

import GUI.FileInfo.MenuDB.CastlePicture.CastlePictureInfoGUI;
import GUI.FileList.FileList;
import SystemDataManagers.MenuDB.CastlePicture.CastlePicture;

@SuppressWarnings("serial")
public class CastlePictureFileList extends FileList
	{
	CastlePicture file;
		CastlePictureManagerFileList parent;
		public CastlePictureFileList(CastlePicture entry, int padding, CastlePictureManagerFileList parent)
		{
			this.padding = padding;
			this.file = entry;
			this.parent = parent;
			initializeAll();
		}
		protected void initializeAll() 
		{
			initializeListGUI("Picture: \"" + file.getText() + "\"");
			initializeInfoGUI();
			addActions();
		}
		protected void initializeInfoGUI() 
		{
			infoGUI = new CastlePictureInfoGUI(file);
		}
		protected void addActions() 
		{
			add(actions);
			addMouseListener();
		}
		public void update()
		{
			fileName.setText("Picture: \"" + file.getText() + "\"");
			super.update();
		}
	}