package GUI.FileList.SystemData.RecordBook;

import javax.swing.JMenuItem;

import GUI.GUI;
import GUI.FileList.FileList;
import SystemDataManagers.MenuDB.Books.RecordBook.RecordBookEntry;

@SuppressWarnings("serial")
public class RecordBookEntryFileList extends FileList
	{
		RecordBookEntry file;
		RecordBookManagerFileList parent;
		public RecordBookEntryFileList(RecordBookEntry entry, int padding, RecordBookManagerFileList parent)
		{
			this.padding = padding;
			this.file = entry;
			this.parent = parent;
			initializeAll();
		}
		protected void initializeAll() 
		{
			initializeListGUI("Entry: \"" + file.getName() + "\"");
			initializeInfoGUI();
			addActions();
		}
		protected void initializeInfoGUI() 
		{
			//TODO
			//this.infoGUI = new HummingEntryInfoGUI(file);
		}
		protected void addActions() 
		{
			//TODO
			addDeleteAction();
			add(actions);
			addMouseListener();
		}
		public void update()
		{
			fileName.setText("Entry: \"" + file.getName() + "\"");
			super.update();
		}
		protected void addDeleteAction()
		{
			JMenuItem replace = new JMenuItem("Delete Record Entry");
			replace.addActionListener(e -> 
			{
				parent.removeEntry(this);
				GUI.update();
			});
			actions.add(replace);
		}
	}