package GUI;


import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JSplitPane;

import GUI.FileInfo.FileInfoPanel;
import GUI.FileInfo.GenericFileInfoGUI;
import GUI.FileList.FileList;
import GUI.FileList.FileListFactory;
import GUI.FileList.FileListPanel;
import bFM.OpenedFile;
import bFM.Settings;

public class GUI
{
	private static FileListPanel openedFileList = new FileListPanel();
	private static FileInfoPanel fileInfoPanel = new FileInfoPanel();
	private static GenericFileInfoGUI fileInfo = null;
	static JSplitPane contents = new JSplitPane();
	public static JFrame frame = new JFrame();
	JPanel fileArea = new JPanel();
	@SuppressWarnings("deprecation")
	public GUI()
	{
		frame.setName("LKS File Manager");
		Settings.getSettings();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//frame.setLayout(new GridBagLayout());
		frame.setLayout(new BorderLayout());
		Settings.bgColor = frame.getBackground();
		MenuBar menuBar = new MenuBar(this);
        frame.add(menuBar, BorderLayout.NORTH);
        frame.reshape(Settings.windowX, Settings.windowY, Settings.windowWidth, Settings.windowHeight);
        //openedFile = new Package();
        //frame.add(openedFile, layout);
        frame.setBackground(Settings.bgColor);
       
        
        openedFileList.setBackground(Settings.bgColor);
        fileInfoPanel.setBackground(Settings.bgColor);
        openedFileList.setMinimumSize(Settings.buttonSize);
        contents.setTopComponent(openedFileList);
        contents.setDividerSize(3);
        contents.setResizeWeight(0);
        contents.setContinuousLayout(true);
        contents.setBottomComponent(fileInfoPanel);
        contents.setDividerLocation((int)(Settings.buttonWidth * 1.5));
        
        frame.add(contents);
        frame.setMinimumSize(new Dimension(Settings.rowWidth*2, Settings.assetHeight*25));
        if(Settings.windowMaximized)
        {
        	frame.setExtendedState(Frame.MAXIMIZED_BOTH);
        }
        //frame.pack();
        update();
		frame.setVisible(true);
		frame.addWindowListener(new WindowAdapter()
		{
			public void windowClosing(WindowEvent e)
			{
				System.out.println("Thanks for Using this!");
				Settings.setSettings();
				frame.dispose();
				System.exit(0);
			}
		});
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
		//if(frame.getExtendedState() != Frame.MAXIMIZED_BOTH)
		//frame.setSize(Math.max(Math.max(rowWidth+20, 300), frame.getWidth()), Math.max(openedFileList.getHeight()+45+assetHeight, frame.getHeight()));
	}
	public static void setFileList(FileList generic) 
	{
		openedFileList.setFile(generic);
		//contents.setDividerLocation(.33);
		//JScrollPane LeftWindow = new JScrollPane((Package)package1, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		//LeftWindow.setMinimumSize(buttonSize);
		//frame.setContentPane(LeftWindow);
        //contents.setTopComponent(LeftWindow);
	}
	public static void setFileInfo(GenericFileInfoGUI gui) 
	{
		fileInfo = gui;
		fileInfoPanel.setViewportView(gui);
		gui.update();
		update();
	}
	public static int getRightWidth() 
	{
		return frame.getWidth() - contents.getDividerLocation();
	}
	public static void deselectAll() 
	{
		openedFileList.deselectAll();
	}
	public void setOpenFile(String path) 
	{
		Settings.lastFileOpenPath = path;
		Path filePath = Paths.get(path);
		byte[] data;
		try {
			data = Files.readAllBytes(filePath);
			OpenedFile file = OpenedFile.makeFile(filePath.getFileName().toString(), data);
			setOpenFile(file);
		} catch (IOException e) 
		{
			System.out.println("Failed to read file: " + filePath);
		}
		
	}
	public void setOpenFile(OpenedFile file) 
	{
		GUI.setFileList(FileListFactory.makeListGUI(file, 0, null));
		update();
	}
	public static byte[] getFile() 
	{
		return openedFileList.getFile();
	}
	
}
