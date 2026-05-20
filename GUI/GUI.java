package GUI;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.GridBagConstraints;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;

import GUI.FileInfo.GenericFileInfoGUI;
import GUI.FileList.FileListFactory;
import GUI.FileList.FileListPanel;
import GUI.FileList.Generic;
import PCKGManager.OpenedFile;
import colReader.ColReader;

public class GUI extends JFrame
{
	private static final long serialVersionUID = 8648666561327794462L;
	public enum Actions 
	{
	    OPENFILE,
	    //Pac
	    EXPORTPAC,
	    IMPORT,
	    EXPORTALL,
	    //Generic
	    EXPORT,
	    REPLACE,
	    //FP
	    EXPORTFIXEDPOINTEXTRACTED,
	    IMPORTFIXEDPOINTEXTRACTED,
	    //Collision
	    EXPORTCOLASOBJ,
	    IMPORTCOLASOBJ,
	    //Unused
	    EXTRACTLZ10,
	    EXTRACT
	}
	//private static JPanel openedFile = new Package();
	private static FileListPanel openedFileList = new FileListPanel();
	private static JScrollPane fileInfoPanel = new JScrollPane(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
	private static GenericFileInfoGUI fileInfo = null;
	static JSplitPane contents = new JSplitPane();
	public static JFrame frame = new JFrame();
	public static final int assetHeight = 20;
	public static final int buttonWidth = 150;
	public static final int pacOffset = 50;
	public static final int rowWidth = 300;
	public static final int indentSize = 15;
	public static Color bgColor = new Color(169, 169, 169);
	JPanel fileArea = new JPanel();
	public static final Dimension buttonSize = new Dimension(GUI.buttonWidth,GUI.assetHeight);
	public static final Color selectedColor = Color.YELLOW;
	public GUI()
	{
		frame.setName("LKS File Manager");
		getSettings();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//frame.setLayout(new GridBagLayout());
		frame.setLayout(new BorderLayout());
		bgColor = frame.getBackground();
		GridBagConstraints layout = new GridBagConstraints();
		layout.anchor = GridBagConstraints.NORTH;
		layout.weighty = 1.0;
	    layout.gridwidth = GridBagConstraints.REMAINDER;
		MenuBar menuBar = new MenuBar();
        frame.add(menuBar, BorderLayout.NORTH);
        //openedFile = new Package();
        //frame.add(openedFile, layout);
        frame.setBackground(GUI.bgColor);
       
        
        openedFileList.setBackground(GUI.bgColor);
        fileInfoPanel.setBackground(GUI.bgColor);
        openedFileList.setMinimumSize(buttonSize);
        contents.setTopComponent(openedFileList);
        contents.setDividerSize(3);
        contents.setResizeWeight(0);
        contents.setBottomComponent(fileInfoPanel);
        
        frame.add(contents);
        frame.setSize(new Dimension(rowWidth*2, assetHeight*25));
        
        //frame.pack();
        update();
		frame.setVisible(true);
		frame.addWindowListener(new WindowAdapter()
		{
			public void windowClosing(WindowEvent e)
			{
				System.out.println("Thanks for Using this!");
				setSettings();
				dispose();
				System.exit(0);
			}
		});
	}
	private void setSettings() 
	{
		String ret = "Little King's Story File Manager Settings\n";
		ret += bFM.Utils.getAsSetting("Debug Output", bFM.Utils.debugOutput);
		ret += bFM.Utils.getAsSetting("Optimize Collision", ColReader.optimizeCollision);
		try 
		{
			System.out.println(Paths.get("LKS File Manager Config.cfg").toAbsolutePath());
			Files.write(Paths.get("LKS File Manager Config.cfg"), ret.getBytes());
		} catch (IOException e) 
		{
			System.out.println("Failed to save settings file");
			e.printStackTrace();
		}
	}
	private static void getSettings()
	{
		try
		{
			List<String> lines = Files.readAllLines(Paths.get("LKS File Manager Config.cfg"));
			for(String line : lines)
			{
				if(line.indexOf("Optimize Collision")!=-1)
				{
					ColReader.optimizeCollision = bFM.Utils.getSettingValue(line);
				}
				else if(line.indexOf("Debug Output")!=-1)
				{
					bFM.Utils.debugOutput = bFM.Utils.getSettingValue(line);
				}
			}
		}
		catch (IOException e)
		{
			System.out.println("Failed to read User Settings, assuming defaults");
		}
	}
	
	public static void testGUI(Component c)
	{
		frame.removeAll();
		frame.add(c);
		frame.repaint();
		frame.pack();
		frame.setVisible(true);
	}
	public static FileListPanel getFileListPanel()
	{
		return openedFileList;
	}
	public static void update() 
	{
		openedFileList.update();
		if(fileInfoPanel.getViewport().getView()!=null)fileInfoPanel.getViewport().getView().repaint();
		if(fileInfo!=null)
		{
			fileInfo.update();
			fileInfo.repaint();
		}
		
		frame.repaint();
		//System.out.println(openedFileList.getHeight());
		if(frame.getExtendedState() != Frame.MAXIMIZED_BOTH)
		frame.setSize(Math.max(Math.max(rowWidth+20, 300), frame.getWidth()), Math.max(openedFileList.getHeight()+45+assetHeight, frame.getHeight()));
	}
	public static void setFileList(Generic generic) 
	{
		openedFileList.setFile(generic);
		contents.setDividerLocation(.33);
		//JScrollPane LeftWindow = new JScrollPane((Package)package1, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		//LeftWindow.setMinimumSize(buttonSize);
		//frame.setContentPane(LeftWindow);
        //contents.setTopComponent(LeftWindow);
	}
	public static void setFileInfo(GenericFileInfoGUI gui) 
	{
		fileInfo = gui;
		fileInfoPanel.setViewportView(gui);
	}
	public static Dimension getRightSize() 
	{
		//System.out.println(leftPanel.getSize());
		return fileInfoPanel.getSize();
	}
	public static void deselectAll() 
	{
		openedFileList.deselectAll();
	}
	public void setOpenFile(String path) 
	{
		Path filePath = Paths.get(path);
		byte[] data;
		try {
			data = Files.readAllBytes(filePath);
			OpenedFile file = OpenedFile.makeFile(filePath.getFileName().toString(), data);
			GUI.setFileList(FileListFactory.makeListGUI(file, 0));
			update();
		} catch (IOException e) 
		{
			System.out.println("Failed to read file: " + filePath);
		}
		
	}
}
