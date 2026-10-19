package GUI.FileList.SystemData.RecordBook;

import GUI.FileInfo.MenuDB.Record.RecordBookEntryInfoGUI;
import GUI.FileList.FileList;
import SystemDataManagers.MenuDB.Books.RecordBook.RecordBookEntry;
import bFM.Settings;

@SuppressWarnings("serial")
public class RecordBookEntryFileList extends FileList
	{
		RecordBookEntry file;
		RecordBookManagerFileList parent;
		public RecordBookEntryFileList(RecordBookEntry entry, RecordBookManagerFileList parent)
		{
			this.padding = parent.getPadding() + Settings.indentSize;
			this.file = entry;
			super.file = entry;
			this.parent = parent;
			initializeAll();
		}
		protected void initializeAll() 
		{
			initializeListGUI("Entry: \"" + file.getName() + "\"");
			//initializeInfoGUI();
			addActions();
		}
		protected void initializeInfoGUI() 
		{
			infoGUI = new RecordBookEntryInfoGUI(file);
		}
		protected void addActions() 
		{
			addDeleteAction("Delete Record", parent, parent.manager.getEntries());
			addMoveUpAction(parent, parent.manager.getEntries());
			addMoveDownAction(parent, parent.manager.getEntries());
			add(actions);
			addMouseListener();
		}
		public void update()
		{
			fileName.setText("Entry: \"" + file.getName() + "\"");
			super.update();
		}
	}