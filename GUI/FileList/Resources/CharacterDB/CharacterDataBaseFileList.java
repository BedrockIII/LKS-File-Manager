package GUI.FileList.Resources.CharacterDB;

import javax.swing.filechooser.FileNameExtensionFilter;

import GUI.FileInfo.CharacterDataBase.CharacterDataBaseInfoGUI;
import GUI.FileList.CollapseableFileList;
import GUI.FileList.Resources.CharacterDB.Index.IndexFileList;
import GUI.FileList.Resources.CharacterDB.JobPrices.JobPriceChangeFileList;
import GUI.FileList.Resources.CharacterDB.Join.CharacterJoinFileList;
import GUI.FileList.Resources.CharacterDB.Resource.CharacterResourceAssignmentList;
import GUI.FileList.Resources.CharacterDB.SoundEffect.SoundEffectFileList;
import GUI.FileList.Resources.CharacterDB.TextAnimations.CharacterTextAnimationList;
import ResourceManagers.CharacterDatabaseManager.CharacterDataBaseManager;
import bFM.Settings;

@SuppressWarnings("serial")
public class CharacterDataBaseFileList extends CollapseableFileList
{
	public CharacterDataBaseManager file;
	private CharacterResourceAssignmentList chrList;
	private IndexFileList chrIndex;
	private CharacterJoinFileList chrJoin;
	private SoundEffectFileList chrSeCrd;
	private CharacterTextAnimationList chrTexAnim;
	private JobPriceChangeFileList chrJobPrc;
	public CharacterDataBaseFileList(CharacterDataBaseManager file, int padding) 
	{
		this.padding = padding;
		this.file = file;
		super.file = file;
		initializeAll();
	}
	protected void initializeAll()
	{
		System.out.print("Opening Character DB Package File: ");
		fileTypes = new FileNameExtensionFilter("Character DB Package File", "pac");
		initializeListGUI("Character Database Manager");
		initializeSubGUI();
		addActions();
		reAddComponents();
	}
	protected void addActions()
	{
		addExportAction();
		addMouseListener();
		add(actions);
		update();
	}
	protected void initializeInfoGUI() 
	{
		this.infoGUI = new CharacterDataBaseInfoGUI((CharacterDataBaseManager)file);
	}
	public void initializeSubGUI()  
	{
		chrList = new CharacterResourceAssignmentList(file.getCharacterResource(), padding + Settings.indentSize);
		chrIndex = new IndexFileList(file.getCharacterIndex(), padding + Settings.indentSize);
		chrJoin = new CharacterJoinFileList(file.getCharacterJoin(), padding + Settings.indentSize);
		chrSeCrd = new SoundEffectFileList(file.getCharacterSoundEffect(), padding + Settings.indentSize);
		chrTexAnim = new CharacterTextAnimationList(file.getTextureAnimations(), padding + Settings.indentSize);
		chrJobPrc = new JobPriceChangeFileList(file.getJobChangePriceList(), padding + Settings.indentSize, this);
		
		subEntries.add(chrIndex);
		subEntries.add(chrList);
		subEntries.add(chrJobPrc);
		subEntries.add(chrJoin);
		subEntries.add(chrSeCrd);
		subEntries.add(chrTexAnim);
	}
}
