package GUI.FileList;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.filechooser.FileNameExtensionFilter;

import GUI.GUI;
import GUI.FileInfo.CharacterDataBaseInfoGUI;
import GUI.FileInfo.CharacterDataBase.CharacterAnimationInfoGUI;
import GUI.FileInfo.CharacterDataBase.CharacterAnimationListInfoGUI;
import GUI.FileInfo.CharacterDataBase.CharacterAnimationPartInfoGUI;
import GUI.FileInfo.CharacterDataBase.CharacterAnimationPatternInfoGUI;
import GUI.FileInfo.CharacterDataBase.CharacterBodyInfoGUI;
import GUI.FileInfo.CharacterDataBase.CharacterFaceAnimationInfoGUI;
import GUI.FileInfo.CharacterDataBase.CharacterFaceInfoGUI;
import GUI.FileInfo.CharacterDataBase.CharacterIndexInfoGUI;
import GUI.FileInfo.CharacterDataBase.CharacterIndexListInfoGUI;
import GUI.FileInfo.CharacterDataBase.CharacterJoinInfo;
import GUI.FileInfo.CharacterDataBase.CharacterJoinListInfoGUI;
import GUI.FileInfo.CharacterDataBase.CharacterPartPatternInfoGUI;
import GUI.FileInfo.CharacterDataBase.CharacterPatternInfoGUI;
import GUI.FileInfo.CharacterDataBase.CharacterPatternListInfoGUI;
import GUI.FileInfo.CharacterDataBase.CharacterPatternMaterialInfoGUI;
import GUI.FileInfo.CharacterDataBase.CharacterPatternPartInfoGUI;
import GUI.FileInfo.CharacterDataBase.CharacterResourceListGUI;
import GUI.FileInfo.CharacterDataBase.CharacterSoundEffectInfoGUI;
import GUI.FileInfo.CharacterDataBase.JobChangePriceListInfoGUI;
import GUI.FileInfo.CharacterDataBase.JopChangePriceInfoGUI;
import GUI.FileInfo.CharacterDataBase.SoundEffectListInfoGUI;
import GUI.PopupWindows.NewBodyGUI;
import GUI.PopupWindows.NewFaceGUI;
import GUI.PopupWindows.NewIndexWindow;
import GUI.PopupWindows.NewJobChangePriceWindow;
import GUI.PopupWindows.NewJoinWindow;
import GUI.PopupWindows.RetypeJobWindow;
import ResourceManagers.CharacterDatabaseManager.CharacterDataBaseManager;
import ResourceManagers.CharacterDatabaseManager.CharacterResourceList;
import ResourceManagers.CharacterDatabaseManager.CharacterResourceList.CharacterBody;
import ResourceManagers.CharacterDatabaseManager.CharacterResourceList.CharacterFace;
import ResourceManagers.CharacterDatabaseManager.JobChangePriceList;
import ResourceManagers.CharacterDatabaseManager.JobChangePriceList.JobPrices;
import ResourceManagers.CharacterDatabaseManager.JoinBinList;
import ResourceManagers.CharacterDatabaseManager.JoinBinList.join;
import ResourceManagers.CharacterDatabaseManager.SoundEffectCoordinateList;
import ResourceManagers.CharacterDatabaseManager.SoundEffectCoordinateList.SoundEffectCoordinate;
import ResourceManagers.CharacterDatabaseManager.TextAnimationList;
import ResourceManagers.CharacterDatabaseManager.TextAnimationList.Animation;
import ResourceManagers.CharacterDatabaseManager.TextAnimationList.AnimationList;
import ResourceManagers.CharacterDatabaseManager.TextAnimationList.AnimationPattern;
import ResourceManagers.CharacterDatabaseManager.TextAnimationList.Material;
import ResourceManagers.CharacterDatabaseManager.TextAnimationList.Part;
import ResourceManagers.CharacterDatabaseManager.TextAnimationList.Part2;
import ResourceManagers.CharacterDatabaseManager.TextAnimationList.Pattern;
import ResourceManagers.CharacterDatabaseManager.TextAnimationList.PatternList;
import ResourceManagers.CharacterDatabaseManager.TextAnimationList.PatternPart;
import ResourceManagers.CharacterDatabaseManager.indBinList;
import ResourceManagers.CharacterDatabaseManager.indBinList.ind;
import bFM.OpenedFile;

@SuppressWarnings("serial")
public class CharacterDataBaseList extends CollapseableFileList
{
	private CharacterResourceAssignmentList chrList;
	private CharacterIndexList chrIndex;
	private CharacterJoinList chrJoin;
	private CharacterSoundEffectList chrSeCrd;
	private CharacterTextAnimationList chrTexAnim;
	private CharacterJobPriceChangeList chrJobPrc;
	public CharacterDataBaseList(OpenedFile file, int padding) 
	{
		this.file = file;
		initializeAll(padding);
	}
	protected void initializeAll(int padding)
	{
		System.out.print("Opening Character DB Package File: ");
		fileTypes = new FileNameExtensionFilter("Character DB Package File", "pac");
		initializeListGUI(padding);
		initializeSubGUI(padding);
		initializeInfoGUI();
		System.out.println("█\nComplete!");
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
	protected void addExportAction()
	{
		JMenuItem export = new JMenuItem("Export Character Database");
		export.addActionListener(e -> {
			JFileChooser chooseFile = new JFileChooser();
			if(GUI.lastFileSavePath != null) 
			{
				chooseFile.setCurrentDirectory(Paths.get(GUI.lastFileSavePath).toFile().getParentFile());
			}
			chooseFile.setSelectedFile(new File("chrDB0.pac"));
			if(chooseFile.showDialog(null, "Save File")==JFileChooser.APPROVE_OPTION)
			{
				try 
				{
					Files.write(chooseFile.getSelectedFile().toPath(),file.toBytes());
					GUI.lastFileSavePath = chooseFile.getSelectedFile().toString();
				}
				catch(IOException i)
				{
					System.out.println("Failed to Export Raw File");
					i.printStackTrace();
				}
				System.out.println("Exported Raw File");
			}
		});
		actions.add(export);
	}
	protected void initializeInfoGUI() 
	{
		this.infoGUI = new CharacterDataBaseInfoGUI((CharacterDataBaseManager)file);
	}
	private void initializeSubGUI(int padding) 
	{
		chrList = new CharacterResourceAssignmentList(((CharacterDataBaseManager)file).getCharacterResource(), padding + GUI.indentSize);
		subEntries.add(chrList);
		System.out.print("█");
		chrIndex = new CharacterIndexList(((CharacterDataBaseManager)file).getCharacterIndex(), padding + GUI.indentSize);
		subEntries.add(chrIndex);
		System.out.print("█");
		chrJoin = new CharacterJoinList(((CharacterDataBaseManager)file).getCharacterJoin(), padding + GUI.indentSize);
		subEntries.add(chrJoin);
		System.out.print("█");
		chrSeCrd = new CharacterSoundEffectList(((CharacterDataBaseManager)file).getCharacterSoundEffect(), padding + GUI.indentSize);
		subEntries.add(chrSeCrd);
		System.out.print("█");
		chrTexAnim = new CharacterTextAnimationList(((CharacterDataBaseManager)file).getTextureAnimations(), padding + GUI.indentSize);
		subEntries.add(chrTexAnim);
		System.out.print("█");
		chrJobPrc = new CharacterJobPriceChangeList(((CharacterDataBaseManager)file).getJobChangePriceList(), padding + GUI.indentSize);
		subEntries.add(chrJobPrc);
		System.out.print("█");
	}
	public class CharacterResourceAssignmentList extends CollapseableFileList
	{
		int padding = 0;
		ArrayList<CharacterBody> bodies = new ArrayList<CharacterBody>();
		ArrayList<CharacterFace> faces = new ArrayList<CharacterFace>();
		public CharacterResourceAssignmentList(CharacterResourceList file, int padding) 
		{
			this.padding = padding;
			this.file = file;
			initializeAll(padding);
		}
		protected void initializeAll(int padding)
		{
			fileTypes = new FileNameExtensionFilter("List File", "lst");
			initializeListGUI(padding);
			initializeSubGUI(padding);
			initializeInfoGUI();
			addActions();
			reAddComponents();
		}
		protected void initializeInfoGUI() 
		{
			this.infoGUI = new CharacterResourceListGUI((CharacterResourceList) file);
		}
		private void initializeSubGUI(int padding) 
		{
			bodies = ((CharacterResourceList)file).getBodies();
			faces = ((CharacterResourceList)file).getFaces();
			for(CharacterBody object : bodies)
			{
				subEntries.add(new CharacterBodyListGUI(object, padding + GUI.indentSize, this));
			}
			for(CharacterFace object : faces)
			{
				subEntries.add(new CharacterFaceListGUI(object, padding + GUI.indentSize, this));
			}
		}
		protected void addActions()
		{
			addNewBodyAction();
			addNewFaceAction();
			addMouseListener();
			add(actions);
			update();
		}
		private void addNewBodyAction() 
		{
			JMenuItem replace = new JMenuItem("Create New Body");
			replace.addActionListener(e -> 
			{
				new NewBodyGUI(this);
				GUI.update();
			});
			actions.add(replace);
		}
		private void addNewFaceAction() 
		{
			JMenuItem replace = new JMenuItem("Create New Face");
			replace.addActionListener(e -> 
			{
				new NewFaceGUI(this);
				GUI.update();
			});
			actions.add(replace);
		}
		public class CharacterBodyListGUI extends Generic
		{
			CharacterResourceAssignmentList parent;
			CharacterBody object;
			public CharacterBodyListGUI(CharacterBody object, int padding, CharacterResourceAssignmentList parent) 
			{
				this.parent = parent;
				this.object = object;
				this.initializeAll(padding);
			}
			protected void initializeAll(int padding)
			{
				this.initializeListGUI(padding);
				addActions();
			}
			protected void initializeListGUI(int padding) 
			{
				//setBorder(BorderFactory.createLineBorder(Color.GREEN));
				setBackground(GUI.bgColor);
				GridBagConstraints constraints = new GridBagConstraints();  
				constraints.weightx = 0.0;
				constraints.anchor = GridBagConstraints.NORTHWEST;
				infoGUI = new CharacterBodyInfoGUI(object);
				setPreferredSize(new Dimension(GUI.rowWidth, getHeight()));
				//setBounds(40+parentX,GUI.assetHeight+parentY,GUI.rowWidth,GUI.assetHeight);
				setLayout(new GridBagLayout());
				//setMaximumSize(new Dimension(100000,GUI.assetHeight));
				JPanel spacer = new JPanel();
				spacer.setPreferredSize(new Dimension(padding, GUI.assetHeight));
				spacer.setMinimumSize(new Dimension(padding, GUI.assetHeight));
				spacer.setBackground(GUI.bgColor);
				add(spacer, constraints);
				constraints.weightx = 1.0;
				fileName = new JLabel(object.getName(), SwingConstants.LEFT);
				fileName.setPreferredSize(new Dimension(GUI.rowWidth-padding, GUI.assetHeight));
				fileName.setBackground(GUI.bgColor);
				add(fileName, constraints);
			}
			protected void addActions()
			{
				addDeleteAction();
				addMouseListener();
				add(actions);
				update();
			}
			protected void addDeleteAction()
			{
				JMenuItem replace = new JMenuItem("Delete Body");
				replace.addActionListener(e -> 
				{
					(this.getParent()).removeFile(this);
					GUI.update();
				});
				actions.add(replace);
			}
			public CharacterResourceAssignmentList getParent()
			{
				return parent;
			}
			public void update()
			{
				fileName.setText(object.getName());
			}
			public CharacterBody getFile()
			{
				return object;
			}
		}
		public class CharacterFaceListGUI extends Generic
		{
			CharacterResourceAssignmentList parent;
			CharacterFace object;
			public CharacterFaceListGUI(CharacterFace object, int padding, CharacterResourceAssignmentList parent) 
			{
				this.parent = parent;
				this.object = object;
				this.initializeAll(padding);
			}
			protected void initializeAll(int padding)
			{
				this.initializeListGUI(padding);
				addActions();
			}
			protected void initializeListGUI(int padding) 
			{
				//setBorder(BorderFactory.createLineBorder(Color.GREEN));
				setBackground(GUI.bgColor);
				GridBagConstraints constraints = new GridBagConstraints();  
				constraints.weightx = 0.0;
				constraints.anchor = GridBagConstraints.NORTHWEST;
				infoGUI = new CharacterFaceInfoGUI(object);
				setPreferredSize(new Dimension(GUI.rowWidth, getHeight()));
				//setBounds(40+parentX,GUI.assetHeight+parentY,GUI.rowWidth,GUI.assetHeight);
				setLayout(new GridBagLayout());
				//setMaximumSize(new Dimension(100000,GUI.assetHeight));
				JPanel spacer = new JPanel();
				spacer.setPreferredSize(new Dimension(padding, GUI.assetHeight));
				spacer.setMinimumSize(new Dimension(padding, GUI.assetHeight));
				spacer.setBackground(GUI.bgColor);
				add(spacer, constraints);
				constraints.weightx = 1.0;
				fileName = new JLabel(object.getName(), SwingConstants.LEFT);
				fileName.setPreferredSize(new Dimension(GUI.rowWidth-padding, GUI.assetHeight));
				fileName.setBackground(GUI.bgColor);
				add(fileName, constraints);
			}
			protected void addActions()
			{
				addDeleteAction();
				addMouseListener();
				add(actions);
				update();
			}
			protected void addDeleteAction()
			{
				JMenuItem replace = new JMenuItem("Delete Face");
				replace.addActionListener(e -> 
				{
					((CharacterResourceAssignmentList)getParent()).removeFile(this);
					GUI.update();
				});
				actions.add(replace);
			}
			public CharacterFace getFile()
			{
				return object;
			}
			public void update()
			{
				fileName.setText(object.getName());
			}
		}
		public void removeFile(Generic file) 
		{
			remove(file);
			subEntries.remove(file);
			if(file instanceof CharacterBodyListGUI)
			{
				((CharacterResourceList)(this.file)).removeBody((CharacterBody)file.getFile());
			}
			else if(file instanceof CharacterFaceListGUI)
			{
				((CharacterResourceList)(this.file)).removeFace((CharacterFace)file.getFile());
			}
			
		}
		public void createFace(String name, int jobCode) 
		{
			((CharacterResourceList)file).addFace(name, jobCode);
			subEntries.add(new CharacterFaceListGUI(((CharacterResourceList)file).getLastFace(), padding + GUI.indentSize, this));
			reAddComponents();
			GUI.update();
		}
		public void createBody(String name, int jobCode) 
		{
			((CharacterResourceList)file).addBody(name, jobCode);
			subEntries.add(new CharacterBodyListGUI(((CharacterResourceList)file).getLastBody(), padding + GUI.indentSize, this));
			reAddComponents();
			GUI.update();
		}
	}
	public class CharacterIndexList extends CollapseableFileList
	{
		int padding = 0;
		ArrayList<ind> indicies = new ArrayList<ind>();
		public CharacterIndexList(indBinList file, int padding) 
		{
			this.padding = padding;
			this.file = file;
			initializeAll(padding);
		}
		protected void initializeAll(int padding)
		{
			fileTypes = new FileNameExtensionFilter("List File", "lst");
			initializeListGUI(padding);
			initializeInfoGUI();
			initializeSubGUI(padding);
			addActions();
			reAddComponents();
		}
		protected void initializeInfoGUI() 
		{
			this.infoGUI = new CharacterIndexListInfoGUI((indBinList) file);
		}
		private void initializeSubGUI(int padding) 
		{
			indicies = ((indBinList)file).getIndicies();
			for(ind object : indicies)
			{
				subEntries.add(new IndexListGUI(object, padding + GUI.indentSize, this));
			}
		}
		protected void addActions()
		{
			addNewJobAction();
			addMouseListener();
			add(actions);
			update();
		}
		private void addNewJobAction() 
		{
			JMenuItem replace = new JMenuItem("Create New Job");
			replace.addActionListener(e -> 
			{
				new NewIndexWindow(this);
				GUI.update();
			});
			actions.add(replace);
		}
		private class IndexListGUI extends Generic
		{
			ind object;
			CharacterIndexList parent;
			public IndexListGUI(ind object, int padding, CharacterIndexList parent) 
			{
				this.parent = parent;
				this.object = object;
				this.initializeAll(padding);
			}
			protected void initializeAll(int padding)
			{
				this.initializeListGUI(padding);
				addActions();
			}
			protected void addActions()
			{
				addDeleteAction();
				addMouseListener();
				add(actions);
				update();
			}
			public CharacterIndexList getParent()
			{
				return parent;
			}
			protected void initializeListGUI(int padding) 
			{
				//setBorder(BorderFactory.createLineBorder(Color.GREEN));
				setBackground(GUI.bgColor);
				GridBagConstraints constraints = new GridBagConstraints();  
				constraints.weightx = 0.0;
				constraints.anchor = GridBagConstraints.NORTHWEST;
				infoGUI = new CharacterIndexInfoGUI(object);
				setPreferredSize(new Dimension(GUI.rowWidth, getHeight()));
				//setBounds(40+parentX,GUI.assetHeight+parentY,GUI.rowWidth,GUI.assetHeight);
				setLayout(new GridBagLayout());
				//setMaximumSize(new Dimension(100000,GUI.assetHeight));
				JPanel spacer = new JPanel();
				spacer.setPreferredSize(new Dimension(padding, GUI.assetHeight));
				spacer.setMinimumSize(new Dimension(padding, GUI.assetHeight));
				spacer.setBackground(GUI.bgColor);
				add(spacer, constraints);
				constraints.weightx = 1.0;
				fileName = new JLabel(object.getName(), SwingConstants.LEFT);
				fileName.setPreferredSize(new Dimension(GUI.rowWidth-padding, GUI.assetHeight));
				fileName.setBackground(GUI.bgColor);
				add(fileName, constraints);
			}
			public void update()
			{
				fileName.setText(object.getName());
			}
			public ind getFile()
			{
				return object;
			}
			protected void addDeleteAction()
			{
				JMenuItem replace = new JMenuItem("Delete Index");
				replace.addActionListener(e -> 
				{
					((CharacterIndexList)getParent()).removeFile(this);
					GUI.update();
				});
				actions.add(replace);
			}
		}
		public void createIndex(String name, int jobCode) 
		{
			((indBinList)file).addJob(jobCode, name);;
			subEntries.add(new IndexListGUI(((indBinList)file).getLastObject(), padding + GUI.indentSize, this));
			reAddComponents();
			GUI.update();
		}
		public void removeFile(IndexListGUI file) 
		{
			remove(file);
			subEntries.remove(file);
			((indBinList)(this.file)).removeIndex(file.getFile());
		}
	}
	public class CharacterJoinList extends CollapseableFileList
	{
		int padding = 0;
		ArrayList<join> objects = new ArrayList<join>();
		public CharacterJoinList(JoinBinList file, int padding) 
		{
			this.padding = padding;
			this.file = file;
			initializeAll(padding);
		}
		protected void initializeAll(int padding)
		{
			fileTypes = new FileNameExtensionFilter("List File", "lst");
			initializeListGUI(padding);
			initializeInfoGUI();
			initializeSubGUI(padding);
			addActions();
			reAddComponents();
		}
		protected void initializeInfoGUI() 
		{
			this.infoGUI = new CharacterJoinListInfoGUI((JoinBinList) file);
		}
		private void initializeSubGUI(int padding) 
		{
			objects = ((JoinBinList)file).getObjects();
			for(join object : objects)
			{
				subEntries.add(new JoinListGUI(object, padding + GUI.indentSize, this));
			}
		}
		protected void addActions()
		{
			addNewJoinAction();
			addMouseListener();
			add(actions);
			update();
		}
		private void addNewJoinAction() 
		{
			JMenuItem replace = new JMenuItem("Create New Join");
			replace.addActionListener(e -> 
			{
				new NewJoinWindow(this);
				GUI.update();
			});
			actions.add(replace);
		}
		private class JoinListGUI extends Generic
		{
			CharacterJoinList parent;
			join object;
			public JoinListGUI(join object, int padding, CharacterJoinList parent) 
			{
				 this.parent = parent;
				this.object = object;
				this.initializeAll(padding);
			}
			protected void initializeAll(int padding)
			{
				this.initializeListGUI(padding);
				addActions();
			}
			protected void initializeListGUI(int padding) 
			{
				//setBorder(BorderFactory.createLineBorder(Color.GREEN));
				setBackground(GUI.bgColor);
				GridBagConstraints constraints = new GridBagConstraints();  
				constraints.weightx = 0.0;
				constraints.anchor = GridBagConstraints.NORTHWEST;
				infoGUI = new CharacterJoinInfo(object);
				setPreferredSize(new Dimension(GUI.rowWidth, getHeight()));
				//setBounds(40+parentX,GUI.assetHeight+parentY,GUI.rowWidth,GUI.assetHeight);
				setLayout(new GridBagLayout());
				//setMaximumSize(new Dimension(100000,GUI.assetHeight));
				JPanel spacer = new JPanel();
				spacer.setPreferredSize(new Dimension(padding, GUI.assetHeight));
				spacer.setMinimumSize(new Dimension(padding, GUI.assetHeight));
				spacer.setBackground(GUI.bgColor);
				add(spacer, constraints);
				constraints.weightx = 1.0;
				fileName = new JLabel(object.getName(), SwingConstants.LEFT);
				fileName.setPreferredSize(new Dimension(GUI.rowWidth-padding, GUI.assetHeight));
				fileName.setBackground(GUI.bgColor);
				add(fileName, constraints);
			}
			protected void addActions()
			{
				addDeleteAction();
				addMouseListener();
				add(actions);
				update();
			}
			protected void addDeleteAction()
			{
				JMenuItem replace = new JMenuItem("Delete Join");
				replace.addActionListener(e -> 
				{
					((CharacterJoinList)getParent()).removeFile(this);
					GUI.update();
				});
				actions.add(replace);
			}
			public void update()
			{
				fileName.setText(object.getName());
			}
			public CharacterJoinList getParent()
			{
				return parent;
			}
			public join getFile()
			{
				return object;
			}
		}
		public void createJoin(int index) 
		{
			((JoinBinList)file).addJob(index);;
			subEntries.add(new JoinListGUI(((JoinBinList)file).getLastObject(), padding + GUI.indentSize, this));
			reAddComponents();
			GUI.update();
		}
		public void removeFile(JoinListGUI file) 
		{
			System.out.println(subEntries.size());
			remove(file);
			subEntries.remove(file);
			((JoinBinList)(this.file)).removeJoin(file.getFile());
			update();
			System.out.println(subEntries.size());
		}
	}
	public class CharacterSoundEffectList extends CollapseableFileList
	{
		int padding = 0;
		ArrayList<SoundEffectCoordinate> objects = new ArrayList<SoundEffectCoordinate>();
		public CharacterSoundEffectList(SoundEffectCoordinateList file, int padding) 
		{
			this.padding = padding;
			this.file = file;
			initializeAll(padding);
		}
		
		protected void initializeAll(int padding)
		{
			fileTypes = new FileNameExtensionFilter("List File", "lst");
			initializeListGUI(padding);
			initializeInfoGUI();
			initializeSubGUI(padding);
			addActions();
			reAddComponents();
		}
		protected void initializeInfoGUI() 
		{
			this.infoGUI = new SoundEffectListInfoGUI((SoundEffectCoordinateList) file);
		}
		private void initializeSubGUI(int padding) 
		{
			objects = ((SoundEffectCoordinateList)file).getObjects();
			for(SoundEffectCoordinate object : objects)
			{
				subEntries.add(new SoundEffectListGUI(object, padding + GUI.indentSize));
			}
		}
		protected void addActions()
		{
			addNewCoordinateAction();
			addMouseListener();
			add(actions);
			update();
		}
		public void removeFile(Generic file) 
		{
			remove(file);
			subEntries.remove(file);
			((SoundEffectCoordinateList)(this.file)).removeCoordinate((SoundEffectCoordinate)file.getFile());
			
		}
		private void addNewCoordinateAction() 
		{
			JMenuItem replace = new JMenuItem("Create New Coordinate");
			replace.addActionListener(e -> 
			{
				((SoundEffectCoordinateList)file).addCoordinate();;
				subEntries.add(new SoundEffectListGUI(((SoundEffectCoordinateList)file).getLastObject(), padding + GUI.indentSize));
				reAddComponents();
				GUI.update();
			});
			actions.add(replace);
		}
		private class SoundEffectListGUI extends Generic
		{
			SoundEffectCoordinate object;
			public SoundEffectListGUI(SoundEffectCoordinate object, int padding) 
			{
				this.object = object;
				this.initializeAll(padding);
			}
			protected void initializeAll(int padding)
			{
				this.initializeListGUI(padding);
				addActions();
			}
			protected void initializeListGUI(int padding) 
			{
				//setBorder(BorderFactory.createLineBorder(Color.GREEN));
				setBackground(GUI.bgColor);
				GridBagConstraints constraints = new GridBagConstraints();  
				constraints.weightx = 0.0;
				constraints.anchor = GridBagConstraints.NORTHWEST;
				infoGUI = new CharacterSoundEffectInfoGUI(object);
				setPreferredSize(new Dimension(GUI.rowWidth, getHeight()));
				//setBounds(40+parentX,GUI.assetHeight+parentY,GUI.rowWidth,GUI.assetHeight);
				setLayout(new GridBagLayout());
				//setMaximumSize(new Dimension(100000,GUI.assetHeight));
				JPanel spacer = new JPanel();
				spacer.setPreferredSize(new Dimension(padding, GUI.assetHeight));
				spacer.setMinimumSize(new Dimension(padding, GUI.assetHeight));
				spacer.setBackground(GUI.bgColor);
				add(spacer, constraints);
				constraints.weightx = 1.0;
				fileName = new JLabel(object.getName(), SwingConstants.LEFT);
				fileName.setPreferredSize(new Dimension(GUI.rowWidth-padding, GUI.assetHeight));
				fileName.setBackground(GUI.bgColor);
				add(fileName, constraints);
			}
			protected void addActions()
			{
				addDeleteAction();
				addMouseListener();
				add(actions);
				update();
			}
			public SoundEffectCoordinate getFile()
			{
				return object;
			}
			protected void addDeleteAction()
			{
				JMenuItem replace = new JMenuItem("Delete Coordinate");
				replace.addActionListener(e -> 
				{
					((CharacterSoundEffectList)getParent()).removeFile(this);
					GUI.update();
				});
				actions.add(replace);
			}
		}
	}
	public class CharacterJobPriceChangeList extends CollapseableFileList
	{
		int padding = 0;
		ArrayList<JobPrices> objects = new ArrayList<JobPrices>();
		public CharacterJobPriceChangeList(JobChangePriceList file, int padding) 
		{
			this.file = file;
			initializeAll(padding);
		}
		protected void initializeAll(int padding)
		{
			this.padding = padding;
			fileTypes = new FileNameExtensionFilter("List File", "lst");
			initializeListGUI(padding);
			initializeInfoGUI();
			initializeSubGUI(padding);
			addActions();
			reAddComponents();
		}
		protected void addActions()
		{
			addNewJobAction();
			addMouseListener();
			add(actions);
			update();
		}
		private void addNewJobAction() 
		{
			JMenuItem replace = new JMenuItem("Create New Job");
			replace.addActionListener(e -> 
			{
				new NewJobChangePriceWindow(this);
				GUI.update();
			});
			actions.add(replace);
		}
		protected void initializeInfoGUI() 
		{
			this.infoGUI = new JopChangePriceInfoGUI((JobChangePriceList) file);
		}
		private void initializeSubGUI(int padding) 
		{
			objects = ((JobChangePriceList)file).getObjects();
			for(JobPrices object : objects)
			{
				subEntries.add(new JobChangePriceListGUI(object, padding + GUI.indentSize));
			}
		}
		public void removeFile(JobChangePriceListGUI file) 
		{
			remove(file);
			subEntries.remove(file);
			((JobChangePriceList)(this.file)).removePrice(file.getFile());
		}
		public class JobChangePriceListGUI extends Generic
		{
			JobPrices object;
			public JobChangePriceListGUI(JobPrices object, int padding) 
			{
				this.object = object;
				this.initializeAll(padding);
			}
			protected void initializeAll(int padding)
			{
				this.initializeListGUI(padding);
				addActions();
			}
			public JobPrices getFile()
			{
				return object;
			}
			protected void initializeListGUI(int padding) 
			{
				//setBorder(BorderFactory.createLineBorder(Color.GREEN));
				setBackground(GUI.bgColor);
				GridBagConstraints constraints = new GridBagConstraints();  
				constraints.weightx = 0.0;
				constraints.anchor = GridBagConstraints.NORTHWEST;
				infoGUI = new JobChangePriceListInfoGUI(object);
				setPreferredSize(new Dimension(GUI.rowWidth, getHeight()));
				//setBounds(40+parentX,GUI.assetHeight+parentY,GUI.rowWidth,GUI.assetHeight);
				setLayout(new GridBagLayout());
				//setMaximumSize(new Dimension(100000,GUI.assetHeight));
				JPanel spacer = new JPanel();
				spacer.setPreferredSize(new Dimension(padding, GUI.assetHeight));
				spacer.setMinimumSize(new Dimension(padding, GUI.assetHeight));
				spacer.setBackground(GUI.bgColor);
				add(spacer, constraints);
				constraints.weightx = 1.0;
				fileName = new JLabel(object.getName(), SwingConstants.LEFT);
				fileName.setPreferredSize(new Dimension(GUI.rowWidth-padding, GUI.assetHeight));
				fileName.setBackground(GUI.bgColor);
				add(fileName, constraints);
			}
			protected void addActions()
			{
				addChangeJobAction();
				addDeleteAction();
				addMouseListener();
				add(actions);
				update();
			}
			private void addChangeJobAction() 
			{
				JMenuItem replace = new JMenuItem("Change Job");
				replace.addActionListener(e -> 
				{
					new RetypeJobWindow(this);
					GUI.update();
				});
				actions.add(replace);
			}
			protected void addDeleteAction()
			{
				JMenuItem replace = new JMenuItem("Delete Job");
				replace.addActionListener(e -> 
				{
					((CharacterJobPriceChangeList)getParent()).removeFile(this);
					GUI.update();
				});
				actions.add(replace);
			}
			public void setCode(String text) 
			{
				object.setCode(bFM.Utils.strToInt(text));
				fileName.setText(object.getName());
			}
			public void update()
			{
				object.updateCode();
				fileName.setText(object.getName());
			}
		}
		public void createJob(String code, String price) 
		{
			((JobChangePriceList)file).addPrice(bFM.Utils.strToInt(price));
			((JobChangePriceList)file).addJob(bFM.Utils.strToInt(code));;
			subEntries.add(new JobChangePriceListGUI(((JobChangePriceList)file).getLastObject(), padding + GUI.indentSize));
			reAddComponents();
			GUI.update();
		}
	}
	public class CharacterTextAnimationList extends CollapseableFileList
	{
		int padding = 0;
		public CharacterTextAnimationList(TextAnimationList file, int padding) 
		{
			this.file = file;
			initializeAll(padding);
		}
		protected void initializeAll(int padding)
		{
			this.padding = padding;
			fileTypes = new FileNameExtensionFilter("Character Texture Animation File", "bin");
			initializeListGUI(padding);
			initializeInfoGUI();
			initializeSubGUI(padding);
			addActions();
			reAddComponents();
		}
		protected void addActions()
		{
			addMouseListener();
			add(actions);
			update();
		}
		protected void initializeInfoGUI() 
		{
			this.infoGUI = new CharacterFaceAnimationInfoGUI((TextAnimationList) file);
		}
		private void initializeSubGUI(int padding) 
		{
			subEntries.add(new CharacterAnimationList(((TextAnimationList)file).getAnimations(), padding + GUI.indentSize));
			subEntries.add(new CharacterPatternList(((TextAnimationList)file).getPatterns(), padding + GUI.indentSize));
		}
		public class CharacterAnimationList extends CollapseableFileList
		{
			ArrayList<Animation> animations;
			int padding = 0;
			public CharacterAnimationList(AnimationList file, int padding) 
			{
				this.file = file;
				initializeAll(padding);
			}
			protected void initializeAll(int padding)
			{
				this.padding = padding;
				initializeListGUI(padding);
				initializeSubGUI(padding);
				initializeInfoGUI();
				addActions();
				reAddComponents();
			}
			protected void initializeListGUI(int padding)
			{
				initializeListGUI(padding, "Animations List");
			}
			protected void initializeInfoGUI() 
			{
				this.infoGUI = new CharacterAnimationListInfoGUI((AnimationList) file);
			}
			private void initializeSubGUI(int padding) 
			{
				animations = ((AnimationList)file).getAnimations();
				for(Animation animation : animations)
				{
					subEntries.add(new CharacterAnimationGUI(animation, padding + GUI.indentSize));
				}
			}
			protected void addActions()
			{
				addMouseListener();
				add(actions);
			}
			public class CharacterAnimationGUI extends CollapseableFileList
			{
				int padding = 0;
				Part part;
				public CharacterAnimationGUI(Animation file, int padding) 
				{
					this.file = file;
					initializeAll(padding);
				}
				protected void initializeAll(int padding)
				{
					this.padding = padding;
					initializeListGUI(padding);
					initializeSubGUI(padding);
					initializeInfoGUI();
					addActions();
					reAddComponents();
				}
				protected void initializeListGUI(int padding)
				{
					initializeListGUI(padding, "Animation: " + file.getName());
				}
				protected void initializeInfoGUI() 
				{
					this.infoGUI = new CharacterAnimationInfoGUI((Animation) file);
				}
				public void update()
				{
					fileName.setText("Animation: " + file.getName());
					super.update();
				}
				private void initializeSubGUI(int padding) 
				{
					part = ((Animation)file).getPart();
					subEntries.add(new CharacterAnimationPartList(part, padding + GUI.indentSize));
				}
				protected void addActions()
				{
					addMouseListener();
					add(actions);
				}
				public class CharacterAnimationPartList extends CollapseableFileList
				{
					int padding = 0;
					ArrayList<AnimationPattern> patterns = new ArrayList<AnimationPattern>();
					public CharacterAnimationPartList(Part file, int padding) 
					{
						this.file = file;
						initializeAll(padding);
					}
					protected void initializeAll(int padding) 
					{
						initializeListGUI(padding);
						initializeSubGUI(padding);
						initializeInfoGUI();
						addActions();
						reAddComponents();
					}
					protected void initializeListGUI(int padding)
					{
						initializeListGUI(padding, "Part: " + file.getName());
					}
					protected void initializeInfoGUI() 
					{
						this.infoGUI = new CharacterAnimationPartInfoGUI((Part) file);
					}
					public void update()
					{
						fileName.setText("Part: " + file.getName());
						super.update();
					}
					private void initializeSubGUI(int padding) 
					{
						patterns = ((Part)file).getPatterns();
						for(AnimationPattern pattern : patterns)
						{
							subEntries.add(new CharacterAnimationPatternGUI(pattern, padding + GUI.indentSize));
						}
					}
					protected void addActions() 
					{
						addMouseListener();
						add(actions);
					}
					public class CharacterAnimationPatternGUI extends FileList
					{
						public CharacterAnimationPatternGUI(AnimationPattern file, int padding) 
						{
							this.file = file;
							initializeAll(padding);
						}
						protected void initializeAll(int padding) 
						{
							initializeListGUI(padding);
							initializeInfoGUI();
							addActions();
						}
						protected void initializeListGUI(int padding) 
						{
							initializeListGUI(padding, "Pattern: " + file.getName());
						}
						protected void initializeInfoGUI() 
						{
							this.infoGUI = new CharacterAnimationPatternInfoGUI((AnimationPattern) file);
						}
						protected void addActions() 
						{
							addMouseListener();
							add(actions);
						}
						public void update()
						{
							fileName.setText("Pattern: " + file.getName());
						}
					}
				}
			}
		}
		public class CharacterPatternList extends CollapseableFileList
		{
			ArrayList<PatternPart> Patterns = new ArrayList<PatternPart>();
			public CharacterPatternList(PatternList file, int padding) 
			{
				this.file = file;
				initializeAll(padding);
			}
			protected void initializeAll(int padding) 
			{
				initializeListGUI(padding);
				initializeInfoGUI();
				initializeSubGUI(padding);
				addActions();
				reAddComponents();
			}
			protected void initializeListGUI(int padding)
			{
				initializeListGUI(padding, "Pattern List");
			}
			protected void initializeInfoGUI() 
			{
				this.infoGUI = new CharacterPatternListInfoGUI((PatternList) file);
			}
			protected void addActions() 
			{
				addMouseListener();
				add(actions);
			}
			private void initializeSubGUI(int padding) 
			{
				Patterns = ((PatternList)file).getPatterns();
				for(PatternPart pattern : Patterns)
				{
					subEntries.add(new CharacterPatternGUI(pattern, padding + GUI.indentSize));
				}
			}
			public class CharacterPatternGUI extends CollapseableFileList
			{
				ArrayList<Part2> Parts = new ArrayList<Part2>();
				public CharacterPatternGUI(PatternPart file, int padding) 
				{
					this.file = file;
					initializeAll(padding);
				}
				protected void initializeAll(int padding) 
				{
					initializeListGUI(padding, "Pattern: " + file.getName());
					initializeInfoGUI();
					initializeSubGUI(padding);
					addActions();
					reAddComponents();
				}
				protected void initializeInfoGUI() 
				{
					this.infoGUI = new CharacterPatternInfoGUI((PatternPart) file);
				}
				protected void addActions() 
				{
					addMouseListener();
					add(actions);
				}
				protected void initializeSubGUI(int padding)
				{
					Parts = ((PatternPart)file).getParts();
					for(Part2 part : Parts)
					{
						subEntries.add(new CharacterPartGUI(part, padding + GUI.indentSize));
					}
				}
				public void update()
				{
					fileName.setText("Pattern: " + file.getName());
					super.update();
				}
				public class CharacterPartGUI extends CollapseableFileList
				{
					Material material;
					ArrayList<Pattern> patterns = new ArrayList<Pattern>();
					public CharacterPartGUI(Part2 file, int padding) 
					{
						this.file = file;
						initializeAll(padding);
					}
					protected void initializeAll(int padding) 
					{
						initializeListGUI(padding, "Part: " + file.getName());
						initializeInfoGUI();
						initializeSubGUI(padding);
						addActions();
						reAddComponents();
					}
					protected void initializeInfoGUI() 
					{
						this.infoGUI = new CharacterPatternPartInfoGUI((Part2) file);
					}
					protected void addActions() 
					{
						addMouseListener();
						add(actions);
					}
					protected void initializeSubGUI(int padding)
					{
						material = ((Part2)file).getMaterial();
						patterns = ((Part2)file).getPatterns();
						subEntries.add(new CharacterMaterialGUI(material, padding + GUI.indentSize));
						for(Pattern part : patterns)
						{
							subEntries.add(new CharacterPatternDetailGUI(part, padding + GUI.indentSize));
						}
					}
					public void update()
					{
						fileName.setText("Part: " + file.getName());
						super.update();
					}
					public class CharacterMaterialGUI extends FileList
					{
						public CharacterMaterialGUI(Material file, int padding) 
						{
							this.file = file;
							initializeAll(padding);
						}
						protected void initializeAll(int padding) 
						{
							initializeListGUI(padding);
							initializeInfoGUI();
							addActions();
						}
						protected void initializeListGUI(int padding) 
						{
							initializeListGUI(padding, "Material: " + file.getName());
						}
						protected void initializeInfoGUI() 
						{
							this.infoGUI = new CharacterPatternMaterialInfoGUI((Material) file);
						}
						protected void addActions() 
						{
							addMouseListener();
							add(actions);
						}
						public void update()
						{
							fileName.setText("Material: " + file.getName());
						}
					}
					public class CharacterPatternDetailGUI extends FileList
					{

						public CharacterPatternDetailGUI(Pattern file, int padding) 
						{
							this.file = file;
							initializeAll(padding);
						}
						protected void initializeAll(int padding) 
						{
							initializeListGUI(padding);
							initializeInfoGUI();
							addActions();
						}
						protected void initializeListGUI(int padding) 
						{
							initializeListGUI(padding, "Pattern: " + file.getName());
						}
						protected void initializeInfoGUI() 
						{
							this.infoGUI = new CharacterPartPatternInfoGUI((Pattern) file);
						}
						protected void addActions() 
						{
							addMouseListener();
							add(actions);
						}
						public void update()
						{
							fileName.setText("Pattern: " + file.getName());
						}
					}
				}
			}
		}
	}	
}
